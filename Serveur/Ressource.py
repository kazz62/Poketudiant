# les Types 
NUMBER_OF_TYPE = 4
NOISY = "NOISY"
MOTIVATED = "MOTIVATED"
TEACHER = "TEACHER"
LAZY = "LAZY"

#Les attaques 
BAVARDAGE = "BAVARDAGE"
GROBOUCAN = "GROBOUCAN"
MEGAPHONE_VOCAL = "MEGAPHONE-VOCAL"
BAILLEMENT = "BAILLEMENT"
PTISOMME = "PTI'SOMME"
SUPERDODO = "SUPERDODO"
RATELETRAIN = "RATELETRAIN"
OBOULO = "OBOULO"
EXOMAISON = "EXO-MAISON"
MAXREVIZ = "MAX-REVIZ"
TITQUESTION = "TIT'QUESTION"
POSERCOLLE = "POSE-COLLE"
FATALINTERRO = "FATAL-INTERRO"

# Les variétés
NO_EVOLUTION = -1
PARLFOR = "Parlfor"
ISMAR = "Ismar"
RIGOLAMOR = "Rigolamor"
PROCRASTINO = "Procrastino"
COUCHTAR = "Couchtar"
NUIDEBOU = "Nuidebou"
ALABOURRE = "Alabourre"
BUCHAFON = "Buchafon"
BELMENTION = "Belmention"
PROMOMAJOR = "Promomajor"
ENSEIGNANT = "Enseignant-dresseur"

class SkeletonPoketudiant:
    def __init__(self, var, type, cap, evo, atck, defense, maxhp):
        self.variete = var
        self.type = type 
        self.capturable = cap 
        self.evolution = evo 
        self.attack = atck
        self.defense = defense
        self.maxHP = maxhp


class SkeletonAttack:
    def __init__(self, atck, type, power):
        self.name = atck
        self.type = type
        self.power = power

# Les ressources sont gérées dans des tableaux différents. 
# On se base sur les indices des différents tableaux pour récupéré les données

# Ressources de poketudiants
Type = [NOISY, NOISY, NOISY, LAZY, LAZY, LAZY, LAZY, MOTIVATED, MOTIVATED, MOTIVATED, TEACHER]
Capturable = [True,False,False,True,True,False,True,True,True,False,False]
Evolution = [-1, 2,-1,-1,5,-1,-1,-1,9,-1,-1]
Attaque = [60,50,85,40,30,55,75,50,30,70,100]
Defense = [40,30,55,60,50,85,95,50,50,70,100]
PVmax = [60,40,70,60,40,70,65,60,40,70,100]

# Ressources des skills 
Atck = [BAVARDAGE, GROBOUCAN, MEGAPHONE_VOCAL, BAILLEMENT, PTISOMME, SUPERDODO, RATELETRAIN, OBOULO, EXOMAISON, MAXREVIZ, TITQUESTION, POSERCOLLE, FATALINTERRO]
TypeAtck = [NOISY, NOISY, NOISY, LAZY, LAZY, LAZY ,LAZY, MOTIVATED, MOTIVATED, MOTIVATED, TEACHER, TEACHER, TEACHER]
Puissance = [10,15,20,10,15,20,15,10,15,20,10,15,20]
T = [0,0,0,1,1,1,1,2,2,2,3,3,3]

Variete = [PARLFOR, ISMAR, RIGOLAMOR, PROCRASTINO, COUCHTAR, NUIDEBOU,ALABOURRE ,BUCHAFON, BELMENTION, PROMOMAJOR, ENSEIGNANT]

numerPoketudiant = 0
numberCatchable = Capturable.count(True)
allAttackInGameByType = []
allPoketudiantInGame = []
allPoketudiantCatchable = []

def getIndVar(v):
    for i in range(0, len(Variete)):
        if Variete[i] == v:
            return i

def initializeAllPoketudiant():

    for i in range(0,len(Type) - 1):
        p = SkeletonPoketudiant(Variete[i], Type[i], Capturable[i], Evolution[i], Attaque[i], Defense[i], PVmax[i])
        allPoketudiantInGame.append(p) 
        if p.capturable == True :
            allPoketudiantCatchable.append(p)
    

def initializeAllAttack():
    for i in range(0, 4):
        allAttackInGameByType.append([])

    for i in range(0, len(Atck) ):
        p = SkeletonAttack(Atck[i], TypeAtck[i], Puissance[i])
        allAttackInGameByType[T[i]].append(p)

def initializeRessources():
    initializeAllAttack()
    initializeAllPoketudiant()

def getEvolution(v):
    for i in range(0, len(Variete) - 1):
        if Variete[i] == v :
            return Evolution[i]

def getTypeFor(v):
    for i in range(0,len(Variete)):
        if Variete[i] == v :
            return Type[i]

# Verifie qu'un type est effectif ou non
def isEffective(fromt, to):
    if fromt == NOISY and to == LAZY :
        return True
    elif fromt == LAZY and to == MOTIVATED:
        return True
    elif fromt == MOTIVATED and to == NOISY:
        return True
    elif fromt == TEACHER and to != TEACHER:
        return True
    else:
        return False
    
def mystrtol(ch):
    res = "".join([ele for ele in ch if ele.isdigit()])
    return res