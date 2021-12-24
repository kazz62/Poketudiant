MINLVL = 1
MAXLVL = 10
BASE_XP = 500
XP_LVL_DIV = 2

class Experience:
    def __init__(self):
        self.totalExp = 0
        self.nextLvlXpAcc = 0
        self.level = MINLVL
        self.nextLvlXpNeeded = getNextLvlExperience(self.level)
    
    def addExperience(self, amount):
        earnedLvl = 0
        if self.level == MAXLVL :
            return 0
        
        while amount != 0 :
            requis = self.nextLvlXpNeeded - self.nextLvlXpAcc
            if amount < requis :
                self.nextLvlXpAcc += amount
                self.totalExp += int(amount) 
                amount = 0
            else :
                amount -= requis 
                self.nextLvlXpAcc = 0
                self.totalExp += requis 
                self.level += 1
                self.nextLvlXpNeeded = getNextLvlExperience(self.level)
                earnedLvl += 1

                if self.level == MAXLVL:
                    self.nextLvlXpNeeded = 0
                    return earnedLvl

        return earnedLvl
    
    def subExperience(self, amount):
        lostLvl = 0

        if self.level == MINLVL:
            return 0
        
        while amount != 0:
            requis = self.nextLvlXpAcc 
            if amount < requis :
                self.nextLvlXpAcc -= amount
                self.totalExp -= amount 
                amount = 0
            else :
                amount -= requis
                self.totalExp -= requis
                self.level -= 1
                self.nextLvlXpNeeded = getNextLvlExperience(self.level)
                self.nextLvlXpAcc = self.nextLvlXpNeeded - 1
                lostLvl += 1

                if self.level == MINLVL:
                    return lostLvl
        
        return lostLvl
    
def getNextLvlExperience(level):
    return  int(BASE_XP * level / XP_LVL_DIV)
    
