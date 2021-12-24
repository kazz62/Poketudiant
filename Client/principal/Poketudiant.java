 package principal;
 
 
 
 
 
 
 public class Poketudiant
 {
   private int num;
   private Attaque attaque1;
   private Attaque attaque2;
   private int pvMax;
   private int pvCurrent;
   private int attaque;
   private int defense;
   private int xpNextLvl;
   private int xpCurrent;
   private int lvl;
  private int pvPerCent = 100;
   
   private Variety variety;
   
   public void setAttaque1(Attaque attaque1) {
    this.attaque1 = attaque1;
   }
   
   public void setAttaque2(Attaque attaque2) {
     this.attaque2 = attaque2;
   }
   
   public void setAttaque(int attaque) {
     this.attaque = attaque;
   }
   
   public void setDefense(int defense) {
     this.defense = defense;
   }
   
   public void setPvCurrent(int pvCurrent) {
     this.pvCurrent = pvCurrent;
   }
   
   public void setPvMax(int pvMax) {
     this.pvMax = pvMax;
   }
   
   public void setVariety(Variety variety) {
    this.variety = variety;
   }
   
   public void setXpCurrent(int xpCurrent) {
     this.xpCurrent = xpCurrent;
   }
   
   public void setXpNextLvl(int xpNextLvl) {
     this.xpNextLvl = xpNextLvl;
   }
   
   public void setLvl(int lvl) {
     this.lvl = lvl;
   }
   
   public void setPvPerCent(float pvPerCent) {
    this.pvPerCent = (int)pvPerCent;
   }
   
   public void setNum(int num) {
     this.num = num;
   }
   
   public Attaque getAttaque1() {
    return this.attaque1;
   }
   
   public Attaque getAttaque2() {
     return this.attaque2;
   }
   
   public int getAttaque() {
     return this.attaque;
   }
   
   public int getDefense() {
     return this.defense;
   }
   
   public int getPvCurrent() {
     return this.pvCurrent;
   }
   
   public int getPvMax() {
     return this.pvMax;
   }
   
   public Variety getVariety() {
    return this.variety;
   }
   
   public int getXpCurrent() {
     return this.xpCurrent;
   }
   
   public int getXpNextLvl() {
    return this.xpNextLvl;
   }
 
   
   public int getLvl() {
     return this.lvl;
   }
   
   public int getPvPerCent() {
     return this.pvPerCent;
   }
   
   public int getNum() {
     return this.num;
   }
 }