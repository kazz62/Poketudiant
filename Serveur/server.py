import socket
import _thread

from Encounter import *
from Joueur import *
from Game import *
from Ressource import *
# Connection Data
ip = ''
porttcp = 9001
portudp = 9000

games = []

# Global Variables
FORMAT = "utf-8"
POKETUDIANT_SERVER = "i'm a poketudiant server\n"
UNABLE_CREATE_GAME = "cannot create game\n"
GAME_CREATED = "game created\n"
JOIN = "game joined\n"
CANNOT_JOIN = "cannot join game\n"
GAME_NOT_EXIST = "the game doesn't exist\n"

MOVE_UP = "map move up\n"
MOVE_DOWN = "map move down\n"
MOVE_LEFT = "map move left\n"
MOVE_RIGHT = "map move right\n"

ROWS = "38"
COLS = "99"

# Broadcast the server 
def broadcast():
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
        s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        s.bind(("", portudp))
        print('Le serveur écoute en UDP')
        while True:
            print('-- j\'attends --')
            data, client = s.recvfrom(4096)
            print(f'Données : {data.decode(FORMAT)} reçues de {client}')
            s.sendto(POKETUDIANT_SERVER.encode(FORMAT),client)

# Send to the client the number of game and the relative information
def envoi_games(client):
    taille = len(games)
    ch = "number of games " + str(taille)+"\n"
    if taille != 0 :
        for i in range(0, taille):
            print(games[i].name)
            ch = ch + str(games[i].nb_joueurs) + " "+ games[i].name+"\n"
    print(ch)
    client.send(ch.encode(FORMAT))

# Init the game
def create_game(client, name):
    if len(games) >= 4 :
        print(UNABLE_CREATE_GAME)
        client.send(UNABLE_CREATE_GAME.encode(FORMAT))
    else :
        games.append(Game(name))
        print(games[0].name)
        client.send(GAME_CREATED.encode(FORMAT))
        print(GAME_CREATED)

def join_game(client, name):
    trouve = False
    joueur = Joueur(client)
    for g in games:
        if g.name == name :
            trouve = True
            if g.nb_joueurs +1 <= g.nb_joueurs_max:
                g.add_joueur(joueur)
                client.send(JOIN.encode(FORMAT))
                print(JOIN)
                break
            else :
                client.send(CANNOT_JOIN.encode(FORMAT))
                print(CANNOT_JOIN)
    if not trouve :
        client.send(CANNOT_JOIN.encode(FORMAT))
        print(CANNOT_JOIN + GAME_NOT_EXIST)

def mouvementServ(joueur, dir, game):
    new = game.mouvement(joueur, dir)
    game.sendallmaps()

    if new == 2:
        print("HEALING\n")
        joueur.healTeam()
        game.sendTeamInfos(joueur.client)
        
    elif new == 3:
        # Entrée en combat contre un poketudiant sauvage
        print("SAUVAGE\n")
        joueur.enCombat = 1
        joueur.ps = 1
        #game.vanishPlayer(joueur)
        #game.sendOthersMaps(joueur)
        print("Entrer wildEncounter\n")
        startWildEncounter(joueur, game)

    elif new == 4 :
        print("RIVAL\n")
        rivalind = int(joueur.rival) #recupere l'indice du rival sur lequel on est
        rival = game.joueurs[rivalind]
        rival.enCombat = 1
        rival.ps = 2
        rival.rival = joueur
        joueur.ps = 2
        joueur.rival = rival
        joueur.enCombat = 1
        #game.vanishPlayer(joueur)
        #game.sendOthersMaps()
        print("Entrer rivalEncounter\n")
        startRivalEncounter(joueur, rival, game)


def handleCmdInGame(game, joueur, cmd):
    mots = cmd.split(" ")
    if cmd == MOVE_RIGHT or cmd == MOVE_LEFT or cmd == MOVE_UP or cmd == MOVE_DOWN:
        cmd = cmd.split(" ")
        print(f"Nouvelle cmd : {cmd}")
        mouvementServ(joueur, cmd[2], game)
    elif mots[0] == "poketudiant" and mots[2] == "move" and mots[3] == "up\n" :
        joueur.moveUpPoketudiant(mots[1])
        game.sendTeamInfos(joueur.client)
    elif mots[0] == "poketudiant" and mots[2] == "move" and mots[3] == "down\n" :
        joueur.moveDownPoketudiant(mots[1])
        game.sendTeamInfos(joueur.client)
    elif mots[0] == "poketudiant" and mots[2] == "free\n":
        joueur.releasePoketudiant(mots[1])
        game.sendTeamInfos(joueur.client)

# Handling Messages From Clients
def handle(client,address):
    while True:
        try:
            # Broadcasting Messages
            message = client.recv(1024)
        except ConnectionResetError:
            # Removing And Closing Clients
            client.close()
            print("Le client s\'est déconnecté \n")
            break
        # Check if the client disconnected with a signal
        if len(message) == 0:
            game.remove_joueur()
            j = game.getJoueur(client)
            game.joueurs.remove(j)
            if game.nb_joueurs == 0 : 
                games.remove(game)
            print('Le client s\'est déconnecté, j\'arrête')
            break
        msg_decode = message.decode(FORMAT)
        mots = msg_decode.split(" ")
        print(msg_decode)
        
        if msg_decode == "require game list\n":
            print("Entrer \n")
            envoi_games(client)
        elif mots[0] == "create" and mots[1] == "game":
            mots[2] = mots[2][:-1]
            create_game(client, mots[2])
            for i in games :
                if i.name == mots[2]:
                    i.init_map()
        elif mots[0] == "join" and mots[1] == "game":
            mots[2] = mots[2][:-1]
            join_game(client, mots[2])
            game = mots[2]
            for i in games :
                if i.name == mots[2]:
                    i.sendallmaps()
                    i.sendTeamInfos(client)
                    game = i                  
        elif mots[0] == "send" and mots[1] == "message":
            ch = "rival message " + str(address) + " "
            for i in range(2, len(mots)):
                ch += mots[i] + " "
            ch += "\n"
            for c in game.joueurs : 
                c.client.send(ch.encode(FORMAT))
        else :
            handleCmdInGame(game, game.getJoueur(client), msg_decode)
            #client.send(msg_decode.encode(FORMAT))

# Receiving 
def receive():
    # Starting Server
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind((ip, porttcp))
    server.listen()
    while True:
        # Accept Connection
        client, address = server.accept()
        print("Connected with {}".format(str(address)))
        # Start Handling Thread For Client
        _thread.start_new_thread(handle, (client, address))

#broadcast()
def main():
    _thread.start_new_thread(broadcast,())
    initializeRessources()
    receive()

if __name__ == "__main__":
    main()

   