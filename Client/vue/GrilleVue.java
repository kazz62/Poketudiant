 package vue;
 
 import java.awt.Color;
 import java.awt.Graphics;
 import java.awt.LayoutManager;
 import java.util.Observable;
 import java.util.Observer;
 import javax.swing.JPanel;
 import modele.CaseModele;
 import modele.GameModele;
 import principal.Ressources;
 import principal.TypeCase;
 
 
 
 public class GrilleVue
   extends JPanel
   implements Observer
 {
   private static final long serialVersionUID = 1L;
   private GameModele gameModele;
   private int var;
   private BoutonMenu[] joueurs;
   
   public GrilleVue(GameModele gameModele) {
    this.gameModele = gameModele;
    this.var = Ressources.var;
    setLayout((LayoutManager)null);
    setOpaque(false);
    setBackground(Color.RED);
    setBounds((int)gameModele.getPosMap().getX(), (int)gameModele.getPosMap().getY(), (gameModele.getNbColonnes() + 20) * 16 * this.var, (gameModele.getNbLignes() + 20) * 16 * this.var);
    this.joueurs = new BoutonMenu[5];
    for (int i = 0; i < 5; i++) {
      this.joueurs[i] = new BoutonMenu(gameModele.getSpriteJoueurs()[i], "joueur0");
      this.joueurs[i].setBounds((int)gameModele.getPosJoueurs()[i].getX(), (int)gameModele.getPosJoueurs()[i].getY(), 18 * this.var, 35 * this.var);
      this.joueurs[i].setOpaque(false);
     } 
    initDecors();
    initCases();
   }
 
 
 
 
   
   private void initDecors() {
     int x;
    for (x = 0; x < 4; x++) {
       
      for (int i = this.gameModele.getNbLignes() + 12; i >= 4; i--) {
        CaseModele newCaseModele = new CaseModele(i, x, giveArbre());
        CaseVue newCaseVue = new CaseVue(newCaseModele, x * 32 - 6, i * 32 - 64);
        newCaseModele.addObserver(newCaseVue);
        add(newCaseVue);
       } 
     } 
    for (x = 8; x < this.gameModele.getNbColonnes() + 8; x++) {
      CaseModele newCaseModele = new CaseModele(this.gameModele.getNbLignes() + 6, x, TypeCase.CLOTURES);
      CaseVue newCaseVue = new CaseVue(newCaseModele, x * 16, (this.gameModele.getNbLignes() + 6) * 16 - 10);
      newCaseModele.addObserver(newCaseVue);
      add(newCaseVue);
     } 
    for (x = 8; x < this.gameModele.getNbColonnes() + 8; x++) {
      for (int i = this.gameModele.getNbLignes() + 11; i >= this.gameModele.getNbLignes() + 6; i--) {
        CaseModele newCaseModele = new CaseModele(i, x, TypeCase.FLEUR);
        CaseVue newCaseVue = new CaseVue(newCaseModele, x * 16, i * 16 + 6);
        newCaseModele.addObserver(newCaseVue);
        add(newCaseVue);
       } 
     } 
     add(this.joueurs[0]);
     add(this.joueurs[1]);
     add(this.joueurs[2]);
     add(this.joueurs[3]);
     add(this.joueurs[4]);
     
     int y;
     for (y = 6; y >= 6; y--) {
       
       CaseModele newCaseModele = new CaseModele(y, this.gameModele.getNbColonnes() + 8, TypeCase.LAC_CORNOR);
       CaseVue newCaseVue = new CaseVue(newCaseModele, (this.gameModele.getNbColonnes() + 8) * 16, y * 16);
       newCaseModele.addObserver(newCaseVue);
       add(newCaseVue);
     } 
     for (y = this.gameModele.getNbLignes() + 11; y >= 7; y--) {
       
       CaseModele newCaseModele = new CaseModele(y, this.gameModele.getNbColonnes() + 8, TypeCase.LAC_BORDERLEFT);
       CaseVue newCaseVue = new CaseVue(newCaseModele, (this.gameModele.getNbColonnes() + 8) * 16, y * 16);
       newCaseModele.addObserver(newCaseVue);
       add(newCaseVue);
     } 
     for (x = this.gameModele.getNbColonnes() + 9; x < this.gameModele.getNbColonnes() + 16; x++) {
       
       CaseModele newCaseModele = new CaseModele(y, this.gameModele.getNbColonnes() + 8, TypeCase.LAC_BORDERTOP);
       CaseVue newCaseVue = new CaseVue(newCaseModele, x * 16, 96);
       newCaseModele.addObserver(newCaseVue);
       add(newCaseVue);
     } 
     for (x = 0; x < (this.gameModele.getNbColonnes() + 16) / 2; x++) {
       for (y = 3; y >= 0; y--) {
         CaseModele newCaseModele = new CaseModele(y, x, giveArbre());
         CaseVue newCaseVue = new CaseVue(newCaseModele, x * 32 - 6, y * 32 - 64);
         newCaseModele.addObserver(newCaseVue);
         add(newCaseVue);
       } 
     } 
   }
 
 
 
   
   private void initCases() {
     int x;
     for (x = 0; x < 8; x++) {   
       for (int i = 0; i < this.gameModele.getNbLignes() + 12; i++) {
         CaseModele newCaseModele = new CaseModele(x, i, TypeCase.NEUTRE);
         CaseVue newCaseVue = new CaseVue(newCaseModele);
         newCaseModele.addObserver(newCaseVue);
         add(newCaseVue);
       } 
     } 
 
     for (x = this.gameModele.getNbColonnes() + 8; x < this.gameModele.getNbColonnes() + 16; x++) {
       for (int i = 6; i < this.gameModele.getNbLignes() + 12; i++) {  
         CaseModele newCaseModele = new CaseModele(x, i, TypeCase.LAC);
         CaseVue newCaseVue = new CaseVue(newCaseModele);
         newCaseModele.addObserver(newCaseVue);
         add(newCaseVue);
       } 
     } 
    for (x = 8; x < this.gameModele.getNbColonnes() + 16; x++) {
      for (int i = 0; i < 6; i++) {
         CaseModele newCaseModele = new CaseModele(x, i, TypeCase.NEUTRE);
         CaseVue newCaseVue = new CaseVue(newCaseModele);
         newCaseModele.addObserver(newCaseVue);
         add(newCaseVue);
       } 
     } 
     for (x = 8; x < this.gameModele.getNbColonnes() + 8; x++) {
       
       for (int i = this.gameModele.getNbLignes() + 6; i < this.gameModele.getNbLignes() + 12; i++) {
         
         CaseModele newCaseModele = new CaseModele(x, i, TypeCase.NEUTRE);
         CaseVue newCaseVue = new CaseVue(newCaseModele);
         newCaseModele.addObserver(newCaseVue);
         add(newCaseVue);
       } 
     } 
 
     
     for (int y = 0; y < this.gameModele.getNbLignes(); y++) {
      for (x = 0; x < this.gameModele.getNbColonnes(); x++) {
         CaseModele newCaseModele = this.gameModele.getCases()[x][y];
         CaseVue newCaseVue = new CaseVue(newCaseModele);
         newCaseModele.addObserver(newCaseVue);
         add(newCaseVue);
       } 
     } 
   }
 
 
 
   
   public void paintComponents(Graphics g) {
     super.paintComponents(g);
   }
 
 
   
   public void update(Observable arg0, Object arg1) {
     if (arg1 != null && arg1 instanceof String) {
       
      String message = arg1.toString();
       
       if (message.equals("moveMap")) {
         
         setBounds((int)this.gameModele.getPosMap().getX(), (int)this.gameModele.getPosMap().getY(), (this.gameModele.getNbColonnes() + 20) * 16 * this.var, (this.gameModele.getNbLignes() + 20) * 16 * this.var);
         for (int i = 0; i < 5; i++)
         {
           this.joueurs[i].setBounds((int)this.gameModele.getPosJoueurs()[i].getX(), (int)this.gameModele.getPosJoueurs()[i].getY(), 18 * this.var, 35 * this.var);
         }
         validate();
         repaint();
       }
       else if (message.length() == 8 && message.substring(0, 6).equals("sprite")) {
         
         int num = Integer.parseInt(message.substring(7));
         this.joueurs[num].setBackground(this.gameModele.getSpriteJoueurs()[num]);
         this.joueurs[num].setBounds((int)this.gameModele.getPosJoueurs()[num].getX(), (int)this.gameModele.getPosJoueurs()[num].getY(), 18 * this.var, 35 * this.var);
         this.joueurs[num].update();
       } 
     } 
   }
 
 
   
   private TypeCase giveArbre() {
     int rand = (int)(Math.random() * 2.0D);
     if (rand == 0) return TypeCase.ARBRE1; 
     return TypeCase.ARBRE2;
   }
   
   public BoutonMenu getJoueur0() {
     return this.joueurs[0];
   }
 }