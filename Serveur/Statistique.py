from Ressource import *
from random import randint
class Statistique:
    def __init__(self, v):
        self.ratioMaxHP = getRandomRatio()
        self.ratioAttack = getRandomRatio()
        self.ratioDefense = getRandomRatio()

        x = getIndVar(v)
        self.basicAttack = Attaque[x]
        self.basicDefense = Defense[x]
        self.basicMaxHP = PVmax[x]

        self.attack = self.basicAttack
        self.defense = self.basicDefense
        self.maxHP = self.basicMaxHP

        self.maxHP *= self.ratioMaxHP
        self.attack *= self.ratioAttack
        self.defense *= self.ratioDefense
        
def getRandomRatio():
    r = randint(0,20) + 90
    return float(r/100)

