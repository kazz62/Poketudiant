 package modele;
 
 import java.awt.Point;
 import java.awt.image.BufferedImage;
 import java.util.Observable;
 import principal.Attaque;
 import principal.Main;
 import principal.Poketudiant;
 import principal.Ressources;
 import principal.TypeCase;
 import principal.Variety;
 
 
 public class GameModele extends Observable
 {
   private int var;
   private int nbLignes;
   private int nbColonnes;
   private int nbJoueurs;
   private boolean iAmMoving;
   private boolean fight;
   private PersonnageModele[] joueurs;
   private CaseModele[][] cases;
   private BufferedImage spriteNeige;
   private Point posMap;
   private BufferedImage[] spriteJoueurs;
   private Point[] posJoueurs;
   private Runnable[] animations;
   private Runnable moveClient;
   private Runnable[] moveAdversaire;
   private Point[] difPosJoueurs;
   
   public GameModele(TchatModele tchatModele) {
    this.fight = false;
    this.iAmMoving = false;
    this.nbJoueurs = 0;
    this.var = Ressources.var;
    this.joueurs = new PersonnageModele[5];
    this.posJoueurs = new Point[5];
    this.difPosJoueurs = new Point[5];
    this.spriteJoueurs = new BufferedImage[5];
    this.moveAdversaire = new Runnable[4];
    this.animations = new Runnable[5];
    init();
    initRunnableMoveClient();
    initRunnableAnimation(0);
    initRunnableAnimation(1);
    initRunnableAnimation(2);
    initRunnableAnimation(3);
    initRunnableAnimation(4);
    initRunnableMoveAdversaire(1);
    initRunnableMoveAdversaire(2);
    initRunnableMoveAdversaire(3);
    initRunnableMoveAdversaire(4);
   }

   
   public void init() {
    if (!Main.socket.getLastReadLine().substring(0, 3).equals("map")) Main.socket.readLine();
    String reponse = Main.socket.getLastReadLine();
    initMap(reponse);
   }
 
   private void moveMap() {
    setChanged();
    notifyObservers("moveMap");
   }
 
   public void equipeContains(int nb) {
    this.joueurs[0].removePoketudiantAll();
    for (int i = 0; i < nb; i++) {
      String[] tmp = Main.socket.readLine().split(" ");
      if (this.joueurs[0] == null) {
        PersonnageModele joueur = new PersonnageModele(0);
        this.nbJoueurs++;
        this.joueurs[joueur.getNum()] = joueur;
       } 
      Poketudiant poke = new Poketudiant();
      poke.setVariety(Variety.getVariety(tmp[0]));
      poke.setLvl(Integer.parseInt(tmp[2]));
      poke.setXpCurrent(Integer.parseInt(tmp[3]));
      poke.setXpNextLvl(Integer.parseInt(tmp[4]));
      poke.setPvCurrent(Integer.parseInt(tmp[5]));
      poke.setPvMax(Integer.parseInt(tmp[6]));
      poke.setAttaque(Integer.parseInt(tmp[7]));
      poke.setDefense(Integer.parseInt(tmp[8]));
      poke.setAttaque1(Attaque.getAttaque(tmp[9]));
      poke.setAttaque2(Attaque.getAttaque(tmp[11]));
      this.joueurs[0].addPoketudiant(poke);
     } 
    setChanged();
    notifyObservers(this.joueurs[0]);
   }
   
   public void moveClient(int i) {
    if (i == 0) Main.socket.sendTo("map move left\n"); 
    if (i == 1) Main.socket.sendTo("map move right\n"); 
    if (i == 2) Main.socket.sendTo("map move down\n"); 
    if (i == 3) Main.socket.sendTo("map move up\n");
   }
 
   public void initMap(String reponse) {
    String[] infosMap = reponse.split(" ");
    this.nbLignes = Integer.parseInt(infosMap[1]);
    this.nbColonnes = Integer.parseInt(infosMap[2]);
    this.cases = new CaseModele[this.nbColonnes][this.nbLignes];
    for (int y = 0; y < this.nbLignes; y++) {
      String ligne = Main.socket.readLine();
      for (int x = 0; x < this.nbColonnes; x++) {
        char tmp = ligne.charAt(x);
        if (TypeCase.isPlayer(tmp))
         {
          if (tmp != '0' || (tmp == '0' && this.joueurs[0] == null)) {
            PersonnageModele joueur = new PersonnageModele(Integer.parseInt(String.valueOf(tmp)));
            this.nbJoueurs++;
            joueur.setPos(x, y);
            this.joueurs[joueur.getNum()] = joueur;
           }
           else {
            this.joueurs[0].setPos(x, y);
           } 
         }
        this.cases[x][y] = new CaseModele(x + 8, y + 6, TypeCase.getTypeCase(tmp));
       } 
     } 
    this.posMap = new Point((int)((this.joueurs[0].getPosX() + 0.5D) * 16.0D * this.var * -1.0D), (int)((this.joueurs[0].getPosY() + 0.5D) * 16.0D * this.var * -1.0D));
    for (int l = 0; l < 5; l++) {
      this.difPosJoueurs[l] = new Point(0, 0);
      this.posJoueurs[l] = new Point(-1, -1);
      if (this.joueurs[l] != null) {
        this.posJoueurs[l].setLocation(((this.joueurs[l].getPosX() + 8) * 16 - 1) * this.var, ((this.joueurs[l].getPosY() + 6) * 16 - 22) * this.var);
        this.spriteJoueurs[l] = Ressources.joueurs[l].getSubimage(18, 37, 18, 35);
       }
       else {
         this.posJoueurs[l].setLocation(-1, -1);
        this.spriteJoueurs[l] = Ressources.neutre;
       } 
     } 
   }
 
   
   public void updateMap() {
    PersonnageModele[] persoTmp = new PersonnageModele[5];
    for (int i = 0; i < 5; ) { persoTmp[i] = null; i++; }
 
     
    for (int y = 0; y < this.nbLignes; y++) {
       
      String ligne = Main.socket.readLine();
       
      for (int x = 0; x < this.nbColonnes; x++) {
         
        char tmp = ligne.charAt(x);
        if (this.cases[x][y].getType() == TypeCase.NOPE)
         {
          if (!TypeCase.isPlayer(tmp))
           {
            this.cases[x][y].setType(TypeCase.getTypeCase(tmp));
           }
         }
        if (TypeCase.isPlayer(tmp)) {
          int num = Integer.parseInt(String.valueOf(tmp));
          PersonnageModele joueur = new PersonnageModele(Integer.parseInt(String.valueOf(tmp)));
          joueur.setPos(x, y);
          persoTmp[num] = joueur;
         } 
       } 
     } 
  
    for (int z = 0; z < 5; z++) {  
      if (persoTmp[z] == null && this.joueurs[z] != null) {   
        this.joueurs[z] = null;
        this.nbJoueurs--;
        this.posJoueurs[z].setLocation(-1, -1);
        this.spriteJoueurs[z] = Ressources.neutre;
        setChanged("sprite " + z);
       }
      else if (persoTmp[z] != null && this.joueurs[z] == null) {
        PersonnageModele joueur = new PersonnageModele(z);
        this.nbJoueurs++;
        joueur.setPos(persoTmp[z].getPosX(), persoTmp[z].getPosY());
        this.joueurs[joueur.getNum()] = joueur;
        this.posJoueurs[z].setLocation(((joueur.getPosX() + 8) * 16 - 1) * this.var, ((joueur.getPosY() + 6) * 16 - 22) * this.var);
        this.spriteJoueurs[z] = Ressources.joueurs[z].getSubimage(18, 37, 18, 35);
        setChanged("sprite " + z);
       }
      else if (persoTmp[z] != null && this.joueurs[z] != null) {
        if (z == 0) {
          if (persoTmp[z].posIsDifferent(this.joueurs[z]))
           {
            this.difPosJoueurs[0].setLocation(persoTmp[z].getPosX() - this.joueurs[z].getPosX(), persoTmp[z].getPosY() - this.joueurs[z].getPosY());
            this.joueurs[z].setPos(persoTmp[z].getPosX(), persoTmp[z].getPosY());
             
            Thread tmp = new Thread(this.moveClient);
            tmp.start();
            Thread t_animation = new Thread(this.animations[0]);
            t_animation.start();
           }
         }
        else if (persoTmp[z].posIsDifferent(this.joueurs[z])) {
          this.difPosJoueurs[z].setLocation(persoTmp[z].getPosX() - this.joueurs[z].getPosX(), persoTmp[z].getPosY() - this.joueurs[z].getPosY());
          this.joueurs[z].setPos(persoTmp[z].getPosX(), persoTmp[z].getPosY());
          Thread tmp = new Thread(this.moveAdversaire[z - 1]);
          tmp.start();
          Thread t_animation = new Thread(this.animations[z]);
          t_animation.start();
         } 
       } 
     } 
   }
 
   private void initRunnableAnimation(int n) {
    this.animations[n] = new Runnable()
       {
         private int num;
         private int y;
         public void run() {
           try {
            if (GameModele.this.difPosJoueurs[this.num].getX() == -1.0D) { this.y = 75; }
            else if (GameModele.this.difPosJoueurs[this.num].getX() == 1.0D) { this.y = 113; }
            else if (GameModele.this.difPosJoueurs[this.num].getY() == -1.0D) { this.y = 0; }
            else if (GameModele.this.difPosJoueurs[this.num].getY() == 1.0D) { this.y = 36; }
            GameModele.this.spriteJoueurs[this.num] = Ressources.joueurs[this.num].getSubimage(18, this.y, 18, 35);
            GameModele.this.setChanged("sprite " + this.num);
            GameModele.this.spriteJoueurs[this.num] = Ressources.joueurs[this.num].getSubimage(0, this.y, 18, 35);
            GameModele.this.setChanged("sprite " + this.num);
            Thread.sleep((24 * GameModele.this.var));
            GameModele.this.spriteJoueurs[this.num] = Ressources.joueurs[this.num].getSubimage(18, this.y, 18, 35);
            GameModele.this.setChanged("sprite " + this.num);
            Thread.sleep((24 * GameModele.this.var));
            GameModele.this.spriteJoueurs[this.num] = Ressources.joueurs[this.num].getSubimage(36, this.y, 18, 35);
            GameModele.this.setChanged("sprite " + this.num);
            Thread.sleep((24 * GameModele.this.var));
            GameModele.this.spriteJoueurs[this.num] = Ressources.joueurs[this.num].getSubimage(18, this.y, 18, 35);
            GameModele.this.setChanged("sprite " + this.num);
            Thread.sleep((24 * GameModele.this.var));
           }
          catch (InterruptedException e) {
            e.printStackTrace();
           } 
         }
       };
   }
  
   private void initRunnableMoveClient() {
    this.moveClient = new Runnable()
       {
         public void run() {
          for (int i = 0; i < 16 * GameModele.this.var / 2; i++) {
             
            GameModele.this.posMap.setLocation((int)GameModele.this.posMap.getX() - GameModele.this.difPosJoueurs[0].getX() * 2.0D, (int)GameModele.this.posMap.getY() - GameModele.this.difPosJoueurs[0].getY() * 2.0D);
            GameModele.this.posJoueurs[0].setLocation(GameModele.this.posJoueurs[0].getX() + GameModele.this.difPosJoueurs[0].getX() * 2.0D, GameModele.this.posJoueurs[0].getY() + GameModele.this.difPosJoueurs[0].getY() * 2.0D);
            GameModele.this.moveMap();
             
             try {
              Thread.sleep((int)(1.5D * GameModele.this.var * 2.0D));
            } catch (InterruptedException e) {
               e.printStackTrace();
             } 
           } 
           
          GameModele.this.iAmMoving = false;
         }
       };
   }

   private void initRunnableMoveAdversaire(int n) {
    if (n == 0 || n > 4)
      return;  
      this.moveAdversaire[n - 1] = new Runnable()
       {
         private int num;
         
         public void run() {
          for (int i = 0; i < 16 * GameModele.this.var; i++) {
             
            GameModele.this.posJoueurs[this.num].setLocation(GameModele.this.posJoueurs[this.num].getX() + GameModele.this.difPosJoueurs[this.num].getX(), GameModele.this.posJoueurs[this.num].getY() + GameModele.this.difPosJoueurs[this.num].getY());
            GameModele.this.moveMap();
            try {
              Thread.sleep((int)(1.5D * GameModele.this.var));
            } catch (InterruptedException e) {
               e.printStackTrace();
            } 
           } 
         }
       };
   }
   public void setChanged(String s) {
     setChanged();
     notifyObservers(s);
   }
   
   public CaseModele[][] getCases() {
    return this.cases;
   }
   
   public int getNbColonnes() {
    return this.nbColonnes;
   }
   
   public int getNbLignes() {
    return this.nbLignes;
   }
   
   public PersonnageModele[] getJoueurs() {
    return this.joueurs;
   }
   
   public int getNbJoueurs() {
    return this.nbJoueurs;
   }
   
   public Point getPosMap() {
     return this.posMap;
   }

   public boolean getIAmMoving() {
    return this.iAmMoving;
   }

   public void setIAmMoving(boolean b) {
    this.iAmMoving = b;
   }
 
   public void setSpriteNeige(BufferedImage spriteNeige) {
    this.spriteNeige = spriteNeige;
   }
   
   public Point[] getPosJoueurs() {
    return this.posJoueurs;
   }
   
   public BufferedImage[] getSpriteJoueurs() {
     return this.spriteJoueurs;
   }
   
   public BufferedImage getSpriteNeige() {
    return this.spriteNeige;
   }
 
   public boolean getFight() {
    return this.fight;
   }

   public void setFight(boolean b) {
    this.fight = b;
   }
 }