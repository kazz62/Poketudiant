import _thread
from Ressource import *
from Protocol import * 
from Poketudiant import *
from Joueur import *
from random import randint
import socket
PLAYER = "player"
IA = "ia"

ANY = "any"
SWITCH = "switch"
ATTACK = "attack"
CAPTURE = "capture"
LEAVE = "leave"

class Action:
    def __init__(self, actiontype, attaque, inds):
        self.type = actiontype
        self.a = attaque
        self.indSwitch = inds

    def actionPlayerOld(self, c, msg):
        if msg == CLIENT_ATTACK1 or msg == CLIENT_ATTACK2:
            self.type = ATTACK
            if msg == CLIENT_ATTACK1:
                self.a = c.currentPoketudiant.a1
            else:
                self.a = c.currentPoketudiant.a2
        elif msg == CLIENT_SWITCH :
            if c.joueur.stillOneAlive(c.indCurrentPoketudiant) == False :
                self.type = ANY
                return
            self.type = SWITCH
            self.indSwitch = c.demandSwitch()
        elif msg == CLIENT_CATCH:
            self.type = CAPTURE
        elif msg == CLIENT_LEAVE:
            self.type = LEAVE
        else :
            self.type = ANY

    def performAction(self, c1, c2):
        if self.type == ATTACK:
            ko = performAttack(c1, c2, self.a)

            if ko == 0: 
                indPoke = handleKO(c1, c2)

                if indPoke== -1:
                    handleWin(c1, c2)
                    handleLose(c2)
                    return True # fin de combat
                else :
                    updateCombatant(c2, indPoke)
                    informSwitch(c2, c1)
        elif self.type == SWITCH:
            updateCombatant(c1, self.indSwitch)
            informSwitch(c1, c2)
        elif self.type == CAPTURE:
            if c2.type == PLAYER:
                c1.envoyerInfos(SERVER_ACTION_FORBIDDEN)
                return False
            #capture réussie
            if tryCapture(c2.currentPoketudiant) == True:
                c1.envoyerInfos(SERVER_CATCH_OK)
                p = c2.currentPoketudiant.copyPoketudiant()
                c1.joueur.addPoketudiant(p)
                return True
            else :
                c1.envoyerInfos(SERVER_CATCH_FAIL)
        elif self.type == LEAVE:
            if c2.type == PLAYER:
                c1.envoyerInfos(SERVER_ACTION_FORBIDDEN)
                return False
            # S'il arrive à fuir 
            if c1.currentPoketudiant.tryEscape(c2.currentPoketudiant) == True:
                c1.envoyerInfos(SERVER_ESCAPE_OK)
                return True
            else :
                c1.envoyerInfos(SERVER_ESCAPE_FAIL)
        return False

def actionPlayer(c, msg):
    a = Attack("none","",-1)
    indSwitch = -1
    if msg == CLIENT_ATTACK1 or msg == CLIENT_ATTACK2:
        type = ATTACK
        if msg == CLIENT_ATTACK1:
            a = c.currentPoketudiant.a1
        else:
            a = c.currentPoketudiant.a2
    elif msg == CLIENT_SWITCH :
        if c.joueur.stillOneAlive(c.indCurrentPoketudiant) == False :
            type = ANY
            res = Action(type, "", -1)
            return res
        type = SWITCH
        indSwitch = c.demandSwitch()
    elif msg == CLIENT_CATCH:
        type = CAPTURE
    elif msg == CLIENT_LEAVE:
        type = LEAVE
    else :
        type = ANY
    res = Action(type, a, indSwitch)
    return res
    
class Combattant:
    def __init__(self, t, j):
        self.type = t #peut etre un PLAYER ou une IA
        if t != IA :
            self.joueur = j 
        else :
            self.joueur = createWildJoueur()
        self.currentPoketudiant = self.joueur.equipe[0]
        self.equipe = []
        for i in self.joueur.equipe :
            self.equipe.append(i)
        #self.equipe.append(self.currentPoketudiant)
        self.indCurrentPoketudiant = 0

    def envoyerInfosDebutCombat(self, opponent):
        self.envoyerInfosEquipeCombattant()
        msg = opponent.currentPoketudiant.getPokeOpponentInfos()
        self.envoyerInfos(msg)

    def envoyerInfosEquipeCombattant(self):
        msg = self.currentPoketudiant.getPokePlayerInfos()
        self.envoyerInfos(msg)
        msg = self.joueur.getTeamInfos()
        self.envoyerInfos(msg)
        

    def demandSwitch(self):
        while True:
            self.envoyerInfos(SERVER_DMD_POKETUDIANT_IND)
            msg = self.receiveMsg()

            indPoke = isPoketudiantIndexMsg(msg)
            print(f"indPoke : {indPoke}\n len equipe : {len(self.joueur.equipe)}\n hp : {self.joueur.equipe[indPoke].actualHP}\n")
            if indPoke != -1 and indPoke <= len(self.joueur.equipe) and self.joueur.equipe[indPoke].actualHP >= 1:
                break
            else :
                self.envoyerInfos(SERVER_POKETUDIANT_IND_INVALID)
        print(f"DEMANDE DE SWITCH AVEC {indPoke}\n")
        return indPoke
                
    def receiveMsg(self):
        buffer = self.joueur.client.recv(1024).decode("utf-8")
        return buffer

    def receiveAction(self):
        if self.type == PLAYER :
            while True :
                buffer = self.receiveMsg()
                if len(buffer) == 0: # a retirer
                    return "FIN_CONNEXION" # a retirer
                #print(f"Action reçue : {buffer}\n")
                ret = actionPlayer(self, buffer)
                if ret.type != ANY :
                    break 

        elif self.type == IA:
            ret = self.actionIA(ATTACK)
        return ret

    def envoyerInfos(self, infos):
        if infos is not None :
            print("Message envoyé : " + infos)
            if self.type == PLAYER :
                self.joueur.client.send(infos.encode("utf-8"))
        

    def actionIA(self, ra):
        r = randint(0, 2) + 1
        if r == 1 :
            res = Action(ra, self.currentPoketudiant.a1, -1)
        else :
            res = Action(ra, self.currentPoketudiant.a2, -1)
        res.type = ATTACK
        return res

def handleEncounterEnd(joueur, game):
    joueur.reviveDeadPoketudiantInTeam()
    joueur.ps = 0
    joueur.enCombat = 0
    #game.sendMapAfterEncounter(joueur, rival)
    game.sendallmaps()
    game.sendTeamInfos(joueur.client)

def handleWin(winner, loser):
    amountXp = 0.0
    nParticipants = len(winner.equipe)
    if winner.type == IA :
        return
    
    winner.envoyerInfos(SERVER_WIN)

    # Si victoire contre rival, pas d'xp gagnée
    if loser.type == PLAYER:
        return 
    
    print(f"Nombre participants : {nParticipants}\n")

    for lp in loser.equipe:
        print(f"l'xp total du loser : {lp.exp.totalExp}\n")
        amountXp += float(lp.exp.totalExp * 0.1)
    
    amountXp = amountXp / nParticipants
    print(f"amount xp après div participants : {amountXp}\n")
    if amountXp < 10.0:
        amountXp = 10.0
    
    for i in range(0,len(winner.equipe)):
        lvl,isEvolve = winner.equipe[i].earnExperience(int(amountXp))
        # Envoi de l'xp gagné par le poketudiant
        msg = getEarnExperienceStr(int(amountXp),i)
        winner.envoyerInfos(msg)

        if lvl > 0:
            msg = ""
            msg = getEarnLvlStr(lvl, i)
            winner.envoyerInfos(msg)
        
        if isEvolve == True:
            msg = getEvolutionStr(winner.equipe[i].variete, i)
            winner.envoyerInfos(msg)

def handleLose(c):
    if c.type == IA:
        return
    c.envoyerInfos(SERVER_LOSE)

    cpt = 0
    for i in c.joueur.equipe:
        amountXp = i.exp.totalExp * 0.2
        lvl = i.loseExperience(amountXp)
        msg = getEarnExperienceStr(int(amountXp * (-1)),cpt)
        c.envoyerInfos(msg)

        if lvl > 0 :
            msg = ""
            msg = getEarnLvlStr(lvl * (-1), cpt)
            c.envoyerInfos(msg)
        cpt +=1

def handleKO(withoutKo, withKo):
    withoutKo.envoyerInfos(SERVER_KO_OPPONENT)
    withKo.envoyerInfos(SERVER_KO_PLAYER)

    if withKo.joueur.stillOneAlive(withKo.indCurrentPoketudiant) == True :
        return withKo.demandSwitch()
    else :
        return -1

def performAttack(c1, c2, a):
    ko = c1.currentPoketudiant.attackOpponent(a, c2.currentPoketudiant)

    c2.envoyerInfosEquipeCombattant()
    msg = c2.currentPoketudiant.getPokeOpponentInfos()
    c1.envoyerInfos(msg)

    return ko

def enterEncounter(c1, c2):
    nj1 = len(c1.joueur.equipe)
    nj2 = len(c2.joueur.equipe)
    
    if c2.type == IA :
        rival = "wild"
    else :
        rival = "rival"
    
    msg = getEnterEncounter(nj2, rival)
    c1.envoyerInfos(msg)
    if rival == "rival":
        msg = getEnterEncounter(nj1, rival)
        c2.envoyerInfos(msg)
        c2.envoyerInfosDebutCombat(c1)
        
    c1.envoyerInfosDebutCombat(c2)
    

def informSwitch(c1, c2):
    msg = c1.currentPoketudiant.getPokePlayerInfos()
    c1.envoyerInfos(msg)
    msg = c1.joueur.getTeamInfos()
    c1.envoyerInfos(msg)
    msg = c1.currentPoketudiant.getPokeOpponentInfos()
    c2.envoyerInfos(msg)

def updateCombatant(c, indNewPoketudiant):
    c.indCurrentPoketudiant = indNewPoketudiant
    c.currentPoketudiant = c.joueur.equipe[indNewPoketudiant]
    c.equipe[indNewPoketudiant] = c.currentPoketudiant
    
def firstToPerform(c1, act1, c2, act2):
    if c1.type == IA :
        return 2
    if c2.type == IA:
        return 1
    
    if act1.type == ATTACK and act2.type == ATTACK :
        r = randint(0, 2) + 1
        return r
    
    elif act1.type == SWITCH:
        return 1
    elif act2.type == SWITCH:
        return 2
    
    return 1

def playEncounter(c1, c2, game):
    fin = False

    enterEncounter(c1, c2)

    while True :
        c1.envoyerInfos(SERVER_DMD_ACTION)
        if c2.type != IA :
            c2.envoyerInfos(SERVER_DMD_ACTION)

        act1 = c1.receiveAction()
        if act1 == "FIN_CONNEXION":
            handleWin(c2,c1)
            break
        act2 = c2.receiveAction()
        if act2 == "FIN_CONNEXION":
            handleWin(c1,c2)
            break
        i = firstToPerform(c1, act1, c2, act2)
        if i == 1:
            first = c1
            firstAct = act1
            second = c2
            secondAct = act2
        else :
            first = c2
            firstAct = act2
            second = c1 
            secondAct = act1 
        
        fin = firstAct.performAction(first, second)
        if fin == True :
            break
        fin = secondAct.performAction(second, first)
        if fin == True:
            break

    handleEncounterEnd(c1.joueur,game)
    if c2.type != IA :
        handleEncounterEnd(c2.joueur,game)

def startRivalEncounter(j1, j2, game):
    c1 = Combattant(PLAYER, j1)
    c2 = Combattant(PLAYER, j2)

    playEncounter(c1, c2, game)

def startWildEncounter(j, game):
    c1 = Combattant(PLAYER, j)
    c2 = Combattant(IA, IA)

    playEncounter(c1, c2, game)
    