 package modele;
 
 import java.util.Observable;
 import principal.Poketudiant;
 
 
 public class PersonnageModele extends Observable{
   private Poketudiant[] equipe;
   private int nbPoketudiant;
   private Poketudiant PokeCurrent;
   private int posX;
   private int posY;
   private int num;
   private int indexCurrentPoke;
   
   public PersonnageModele(int num) {
    this.equipe = new Poketudiant[3];
    this.nbPoketudiant = 0;
    this.indexCurrentPoke = 0;
    this.num = num;
   }
 
 
   
   public void addPoketudiant(Poketudiant p) {
    if (this.nbPoketudiant < 3) {
       
      this.equipe[this.nbPoketudiant] = p;
      this.nbPoketudiant++;
     } 
   }
 
   
   public void removePoketudiantAll() {
    this.nbPoketudiant = 0;
    for (int i = 0; i < 3; ) { this.equipe[i] = null; i++; }
   
   }
   
   public Poketudiant[] getEquipe() {
    return this.equipe;
   }
   
   public int getNbPoketudiant() {
    return this.nbPoketudiant;
   }
   
   public int getPosX() {
    return this.posX;
   }
   
   public int getPosY() {
    return this.posY;
   }
   
   public int getIndexCurrentPoke() {
    return this.indexCurrentPoke;
   }
   
   public void setIndexCurrentPoke(int indexCurrentPoke) {
    this.indexCurrentPoke = indexCurrentPoke;
   }
 
   
   public void setPos(int x, int y) {
    this.posX = x;
    this.posY = y;
   }
   
   public int getNum() {
      return this.num;
   }
 
   
   public boolean posIsDifferent(PersonnageModele p) {
    if (this.posX != p.getPosX() || this.posY != p.getPosY()) return true; 
    return false;
   }
   
   public void setNbPoketudiant(int nbPoketudiant) {
    this.nbPoketudiant = nbPoketudiant;
   }
   
   public Poketudiant getPokeCurrent() {
    return this.PokeCurrent;
   }
   
   public void setPokeCurrent(Poketudiant pokeCurrent) {
    this.PokeCurrent = pokeCurrent;
   }
 }