from Joueur import *
from random import randint
from server import ROWS
from server import COLS
ROWS = "38"
COLS = "99"

UP = "up\n"
DOWN = "down\n"
LEFT = "left\n"
RIGHT = "right\n"
HERBE = "*"
HEAL = "+"

FORMAT = "utf-8"

class Game :
    def __init__(self, name) :
        self.nb_joueurs_max = 4
        self.name = name
        self.nb_joueurs = 0
        self.joueurs = []
        self.posx = []
        self.posy = []
        self.map = []
        self.mapOrigine = []

    def add_joueur(self,joueur):
        self.nb_joueurs += 1
        self.joueurs.append(joueur)
        attributeIndToJoueur(self, joueur)
        print("indice joueur : " + str(joueur.ind))
        joueur.x, joueur.y = self.get_pos(str(joueur.ind))
        print(f"pos joueur : {joueur.x} ; {joueur.y}")
        return True

    def remove_joueur(self):
        if self.nb_joueurs -1 >= 0:
            self.nb_joueurs -= 1
            return True
        else :
            print("Il n'y a deja plus de joueurs dans la partie\n")
            return False

    def sendall(self,msg):
        for i in self.joueurs :
            i.client.send(msg.encode("utf-8"))

    def getJoueur(self,client):
        for i in self.joueurs :
            if i.client == client:
                return i

    def joueurExist(self, ind):
        for i in self.joueurs :
            if i.ind == ind :
                return True
        return False
        
    def sendallmaps(self):
        ch = ""
        ch += "map "+ROWS+" "+COLS+"\n"
        self.sendall(ch)
        print(ch)
        for i in self.joueurs :  
            ch = ""
            for j in range(0,len(self.map)):
                x_zero, y_zero = self.get_pos("0")
                x1,y1 = self.get_pos("1")
                x2,y2 = self.get_pos("2")
                x3,y3 = self.get_pos("3")
                for k in range(0,len(self.map[j])) :
                    if j == i.y and k == i.x:
                        ch += "0"
                    elif j == y_zero and k == x_zero :
                        ch += str(i.ind)
                    elif j == y1 and k == x1 and self.joueurExist(1) == False:
                        ch += self.mapOrigine[y1][x1]
                    elif j == y2 and k == x2 and self.joueurExist(2) == False:
                        ch += self.mapOrigine[y2][x2]
                    elif j == y3 and k == x3 and self.joueurExist(3) == False:
                        ch += self.mapOrigine[y3][x3]
                    else :
                        ch += self.map[j][k]
            i.client.send(ch.encode("utf-8"))
            print(ch)
    
    # Envoie la map a tous les joueurs sauf joueu
    def sendOthersMaps(self, joueu):
        ch = ""
        ch += "map "+ROWS+" "+COLS+"\n"
        for i in self.joueurs :
            i.client.send(ch.encode("utf-8"))
        print(ch)
        for i in self.joueurs :  
            ch = ""
            for j in range(0,len(self.map)):
                
                x1,y1 = self.get_pos("1")
                x2,y2 = self.get_pos("2")
                x3,y3 = self.get_pos("3")
                for k in range(0,len(self.map[j])) :
                    if j == i.y and k == i.x:
                        ch += "0"
                    elif j == y1 and k == x1 and not(self.joueurExist(1)):
                        ch += self.mapOrigine[y1][x1]
                    elif j == y2 and k == x2 and not(self.joueurExist(2)):
                        ch += self.mapOrigine[y2][x2]
                    elif j == y3 and k == x3 and not(self.joueurExist(3)):
                        ch += self.mapOrigine[y3][x3]
                    else :
                        ch += self.map[j][k]
            if i != joueu :
                i.client.send(ch.encode("utf-8"))
                print(ch)
    
    def sendToOthersMaps(self, joueu, rival):
        ch = ""
        ch += "map "+ROWS+" "+COLS+"\n"
        for i in self.joueurs :
            i.client.send(ch.encode("utf-8"))
        print(ch)
        for i in self.joueurs :  
            ch = ""
            for j in range(0,len(self.map)):
                if rival.ind != 1:
                    x1,y1 = self.get_pos("1")
                if rival.ind != 2 :
                    x2,y2 = self.get_pos("2")
                if rival.ind != 3 :
                    x3,y3 = self.get_pos("3")
                for k in range(0,len(self.map[j])) :
                    if j == i.y and k == i.x:
                        ch += "0"
                    elif j == y1 and k == x1 and not(self.joueurExist(1)) and rival.ind != 1:
                        ch += self.mapOrigine[y1][x1]
                    elif j == y2 and k == x2 and not(self.joueurExist(2)) and rival.ind != 2:
                        ch += self.mapOrigine[y2][x2]
                    elif j == y3 and k == x3 and not(self.joueurExist(3)) and rival.ind != 3:
                        ch += self.mapOrigine[y3][x3]
                    else :
                        ch += self.map[j][k]
            if i != joueu and i != rival:
                i.client.send(ch.encode("utf-8"))
                print(ch)

    def vanishPlayer(self,j1):
        self.map[j1.y][j1.x] = self.mapOrigine[j1.y][j1.x]

    def reappearPlayer(self,j1):
        self.map[j1.y][j1.x] = str(j1.ind)

    def init_map(self):
        ligne = []
        ligneOrigine = []
        l = ["0", "1", "2","3"]
        with open("World.map", "r") as f:
            for j in f :
                for i in j :
                    ligne.append(i)
                    if i in l : 
                        ligneOrigine.append(" ")
                    else :
                        ligneOrigine.append(i)
                    if i == "\n" :
                        self.map.append(ligne)
                        self.mapOrigine.append(ligneOrigine)
                        ligneOrigine = []
                        ligne = []
    
    def get_pos(self,indjoueur):
        for j in self.map :
            for i in j:
                if i == indjoueur :
                    return (j.index(i), self.map.index(j))
    
    def joueurSurJoueur(self,joueur):
        for j in self.joueurs:
            if j != joueur and joueur.enCombat == 0 and joueur.x == j.x and joueur.y == j.y:
                print("EST SUR UN AUTRE JOUEUR\n")
                return j.ind
        return -1

    def joueurSurHerbe(self,joueur):
        return self.mapOrigine[joueur.y][joueur.x] == HERBE

    def joueurSurHeal(self, joueur):
        return self.mapOrigine[joueur.y][joueur.x] == HEAL

    def mouvement(self, joueur, dir):
        l = ["0","1","2","3"]
        indj = joueur.ind
        l.remove(str(indj))
        rival = "-1"
        if dir == LEFT :
            if self.map[joueur.y][joueur.x -1] in l :
                rival = self.map[joueur.y][joueur.x -1]
            else :
                self.map[joueur.y][joueur.x] = self.mapOrigine[joueur.y][joueur.x] 
                if joueur.x - 1 < 0 :
                    joueur.x = int(COLS)-1
                else :
                    joueur.x -= 1
        elif dir == RIGHT:
            if self.map[joueur.y][joueur.x + 1] in l :
                rival = self.map[joueur.y][joueur.x + 1]
            else :
                self.map[joueur.y][joueur.x] = self.mapOrigine[joueur.y][joueur.x] 
                if joueur.x + 1 >= int(COLS) :
                    joueur.x = 0
                else :
                    joueur.x += 1
        elif dir == UP:
            if self.map[joueur.y - 1][joueur.x] in l :
                rival = self.map[joueur.y - 1][joueur.x]
            else :
                self.map[joueur.y][joueur.x] = self.mapOrigine[joueur.y][joueur.x] 
                joueur.y -= 1
        elif dir == DOWN:
            if self.map[joueur.y + 1][joueur.x] in l :
                rival = self.map[joueur.y + 1][joueur.x]
            else :
                self.map[joueur.y][joueur.x] = self.mapOrigine[joueur.y][joueur.x] 
                joueur.y += 1
            
        joueur.rival = rival
        self.map[joueur.y][joueur.x] = str(joueur.ind)

        #rival = self.joueurSurJoueur(joueur)
        if rival != "-1" :
            return 4
        elif self.joueurSurHerbe(joueur):
            if pkm_sauvage():
                return 3
        elif self.joueurSurHeal(joueur):
            return 2
        
        return 1

    def sendTeamInfos(self, client):
        j = self.getJoueur(client)
        ch = j.getTeamInfos()
        print(ch)
        client.send(ch.encode(FORMAT))

    def sendMapAfterEncounter(self, joueur, rival):
        self.reappearPlayer(joueur)
        self.sendToOthersMaps(joueur, rival)

def attributeIndToJoueur(game, joueur):
        l = [0,1,2,3]
        for j in game.joueurs :
            if j.ind in l :
                l.remove(j.ind)
        joueur.ind = l[0]
        joueur.x,joueur.y = game.get_pos(str(joueur.ind))

def pkm_sauvage():
        r = randint(1,100)
        return r < 30

