from random import *
from Poketudiant import *
from Ressource import *

MAX_EQUIPE = 3

class Joueur:
    def __init__(self,client):
        self.client = client
        self.name = ""
        self.equipe = []
        self.ind = -1
        self.x = -1
        self.y = -1
        self.enCombat = 0
        self.rival = "a"
        self.ps = 0 # 0 = deplacement 1 = combat poketudiant sauvage 2 = combat rival
        

        self.equipe.append(Poketudiant(ENSEIGNANT))

    def releasePoketudiant(self, indPoketudiant):
        indPoketudiant = int(indPoketudiant)
        if indPoketudiant < 0 or indPoketudiant >= MAX_EQUIPE or self.equipe[indPoketudiant] == False or self.equipe[indPoketudiant].variete == ENSEIGNANT :
            return False
        
        self.equipe.remove(self.equipe[indPoketudiant])
        return True
    
    def moveUpPoketudiant(self, indPoketudiant):
        indPoketudiant = int(indPoketudiant)
        i = indPoketudiant -1
        if indPoketudiant < 0 or indPoketudiant >= MAX_EQUIPE or i < 0 or self.equipe[indPoketudiant] == False :
            return False
        tmp = self.equipe[i]
        self.equipe[i] = self.equipe[indPoketudiant]
        self.equipe[indPoketudiant] = tmp 

        return True

    def moveDownPoketudiant(self, indPoketudiant):
        indPoketudiant = int(indPoketudiant)
        i = indPoketudiant -1
        if indPoketudiant < 0 or indPoketudiant >= MAX_EQUIPE or i >= MAX_EQUIPE or self.equipe[indPoketudiant] == False :
            return False
        if self.equipe[i] == False: 
            return False
        tmp = self.equipe[i]
        self.equipe[i] = self.equipe[indPoketudiant]
        self.equipe[indPoketudiant] = tmp
        
        return True

    def healTeam(self):
        for i in self.equipe :
            i.healPoketudiant()
    
    def reviveDeadPoketudiantInTeam(self):
        for i in self.equipe :
            if i is not None:
                if i.actualHP == 0:
                    i.actualHP = 1
    
    def stillOneAlive(self, exception):
        for i in range (0,len(self.equipe)) :
            if self.equipe[i].actualHP >= 1 and i != exception :
                return True
        
        return False
    
    def addPoketudiant(self, p):
        if len(self.equipe) < MAX_EQUIPE :
            self.equipe.append(p.copyPoketudiant())
            return True
        return False

    def getTeamInfos(self):
        ch = "team contains " + str(len(self.equipe)) + "\n"
        for i in self.equipe : 
            ch += str(i.variete) + " " 
            ch += str(i.type) + " "
            ch += str(i.exp.level) + " "
            ch += str(i.exp.nextLvlXpAcc) + " "
            ch += str(i.exp.nextLvlXpNeeded) + " "
            ch += str(int(i.actualHP)) + " "
            ch += str(int(i.stat.maxHP)) + " "
            ch += str(int(i.stat.attack)) + " "
            ch += str(int(i.stat.defense)) + " "
            ch += str(i.a1.name) + " "
            ch += str(i.a1.type) + " "
            ch += str(i.a2.name) + " "
            ch += str(i.a2.type)
            ch += "\n"
        return ch
    

def createWildJoueur():
    res = Joueur("wild")
    res.enCombat = 1
    res.equipe[0] = getWildPoketudiant()

    return res

def tryCapture(wild):
        basePct = 0.5
        k = 2
        c = basePct - (wild.actualHP / wild.stat.maxHP)
        if c < 0 :
            c1 = 0
        else :
            c1 = c * 100 * k
       
        r = randint(0,99)
        if r < c1 :
            return True
        else :
            return False