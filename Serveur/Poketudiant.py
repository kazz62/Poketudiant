from Ressource import *
from Statistique import *
from Experience import *
from Attaque import *
class Poketudiant:
    def __init__(self, variete):
        self.type = getTypeFor(variete)
        self.stat = Statistique(variete)
        self.actualHP = self.stat.maxHP
        self.exp = Experience()
        #print(variete)
        self.a1 = getRandomAttackST(variete)
        self.a2 = getRandomAttackOT(variete)
        self.variete = variete

    def tryEscape(self, wild):
        diff = self.exp.level - wild.exp.level
        prob = [25,40,50,75,90]

        if diff <= -3 :
            return False
        elif diff >= 3 :
            return True
        
        r = randint(0,99)
        
        if r < prob[diff + 2] :
            return True
        else :
            return False

    def attackOpponent(self, attack, opponent):
        power = attack.power
        if isAttackEffective(attack,opponent):
            power = power * 2
        k = getRandomRatio()
        dmg = k * float(self.stat.attack) / float(opponent.stat.defense) * float(power)
        if opponent.actualHP - dmg < 0:
            opponent.actualHP = 0
            return opponent.actualHP
        else :
            opponent.actualHP = opponent.actualHP - dmg 
        return opponent.actualHP
        
    def earnExperience(self, amount):
        earnedLvl =  self.exp.addExperience(amount)
        isEvolve = False
        if earnedLvl != 0:
            self.changeStatistics()
            isEvolve = self.tryEvolution()
            self.healPoketudiant()
        
        return earnedLvl,isEvolve
    
    def loseExperience(self, amount):
        lostLvl = self.exp.subExperience(amount)
        self.healPoketudiant()
        if lostLvl != 0:
            self.changeStatistics()
        
        return lostLvl

    def changeStatistics(self):
        increaseRatio = 1.0 + (0.1 *(self.exp.level -1))
        stat = self.stat
        if self.exp.level == 1 :
            return
        
        stat.attack = stat.basicAttack * increaseRatio
        stat.defense = stat.basicDefense * increaseRatio
        stat.maxHP = stat.basicMaxHP * increaseRatio

    def healPoketudiant(self):
        self.actualHP = self.stat.maxHP
    
    def tryEvolution(self):
        level = self.exp.level
        if getEvolution(self.variete) == -1 or level < 3 :
            return False
        if level == 3 :
            r = randint(0,5)
        elif level == 4:
            r = randint(0, 1000)
            if r < 375:
                r = 0
        else :
            r = 0
        
        if r == 0:
            self.evolvePoketudiant()
            return True
        
        return False

    def evolvePoketudiant(self):
        evolution = Variete[getEvolution(self.variete)]
        self.variete = evolution

        ind = getIndVar(self.variete)
        self.stat.basicAttack = allPoketudiantInGame[ind].attack * self.stat.ratioAttack
        self.stat.basicDefense = allPoketudiantInGame[ind].defense * self.stat.ratioDefense
        self.stat.basicMaxHP = allPoketudiantInGame[ind].maxHP * self.stat.ratioMaxHP
        self.changeStatistics()

    def copyPoketudiant(self):
        copy = Poketudiant(self.variete)
        copy.stat = self.stat
        copy.a1 = self.a1 
        copy.a2 = self.a2 
        copy.exp = self.exp 
        copy.actualHP = self.actualHP
        return copy

    def getPokePlayerInfos(self):
        ch = "encounter poketudiant player "
        pcpvt = (self.actualHP / (self.stat.maxHP))*100
        if pcpvt - int(pcpvt) > 0 :
            n = pcpvt + 1
        else :
            n = pcpvt
        
        ch += str(self.variete) + " "
        ch += str(self.exp.level) + " "
        ch += str(int(n)) + " "
        ch += str(self.a1.name) + " " + str(self.a1.type) + " "
        ch += str(self.a2.name) + " " + str(self.a2.type)
        ch += "\n"

        return ch 
    
    def getPokeOpponentInfos(self):
        pcpvt = (self.actualHP / self.stat.maxHP)*100
        if pcpvt - int(pcpvt) > 0 :
            n = pcpvt + 1
        else :
            n = pcpvt
        ch = "encounter poketudiant opponent "
        ch += self.variete + " "
        ch += str(self.exp.level) + " "
        ch += str(int(n)) + "\n"
        return ch
    
def getWildPoketudiant():
    r = randint(0, numberCatchable-1)
    wild = Poketudiant(allPoketudiantCatchable[r].variete)
    level = randint(0, 3)
    wild.exp.level += level 
    wild.exp.nextLvlXpNeeded = getNextLvlExperience(wild.exp.level)

    for i in range(1, wild.exp.level):
        wild.exp.totalExp += getNextLvlExperience(i)
        
    wild.changeStatistics()
    wild.healPoketudiant()
    print(f"Wild : {wild.variete}\n")
    return wild
