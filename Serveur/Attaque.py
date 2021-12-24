from Ressource import *
from random import randint 

class Attack:
    def __init__(self, name, type, power):
        self.name = name
        self.type = type 
        self.power = power

# Renvoie une attaque aléatoire du type de la variete v
def getRandomAttackST(t):
    typ = getTypeFor(t)
    an,power = getRandomAttackNamesByType(typ)
    a = Attack(an, typ, power)
    return a

# Renvoie une attaque de type différent du type de la variete t et différent de TEACHER
def getRandomAttackOT(v):
    typ = getTypeFor(v)
    r = randint(0,len(TypeAtck)-1)
    while TypeAtck[r] == typ or TypeAtck[r] == TEACHER:
        r = randint(0,len(TypeAtck)-1)
    
    name,power = getRandomAttackNamesByType(TypeAtck[r])
    an = Attack(name, TypeAtck[r], power)
    return an

def getRandomAttackNamesByTypeOld(v):

    typ = getTypeFor(v)
    n = TypeAtck.count(typ)
    r = randint(0, n-1)
    sa = allAttackInGameByType[n][r]
    return sa.name,sa.power

def getRandomAttackNamesByType(t):

    n = TypeAtck.count(t)
    r = randint(0, n-1)

    sa = allAttackInGameByType[getIndTypeAtck(t)][r]
    return sa.name,sa.power

def getIndTypeAtck(t):
    for i in range(0,len(TypeAtck) -1):
        if TypeAtck[i] == t:
            return T[i]

def isAttackEffective(a ,p):
    return isEffective(a.type, p.type)

