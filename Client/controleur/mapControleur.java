 package controleur;
 
 import java.awt.event.KeyEvent;
 import java.awt.event.KeyListener;
 import modele.GameModele;
 import principal.Main;
 import principal.Ressources;
 import vue.MapVue;
 
 
 
 
 
 
 public class mapControleur
 {
   private MapVue mapVue;
   private GameModele gameModele;
   private KeyListener keyListener;
   private Thread threadPartie;
   private Runnable runnablePartie;
   private Thread threadNeige;
   private Runnable runnableNeige;
   private TchatControleur tchatControleur;
   private QuitterGameControleur quitterControleur;
   private EquipeControleur equipeControleur;
   
   public mapControleur(MapVue mapVue, GameModele gameModele, TchatControleur tchatControleur) {
    this.mapVue = mapVue;
    this.gameModele = gameModele;
    this.tchatControleur = tchatControleur;
    this.quitterControleur = new QuitterGameControleur(mapVue.getQuitterVue());
    this.equipeControleur = new EquipeControleur(mapVue.getEquipeVue());
    init();
    this.threadPartie = new Thread(this.runnablePartie);
    this.threadPartie.start();
    this.threadNeige = new Thread(this.runnableNeige);
    this.threadNeige.start();
  }
 
   
  private void init() {
    initKeyListener();
    initRunnablePartie();
    initRunnableNeige();
  }
 
   
   public void startThreadPartie() {
    this.threadPartie = new Thread(this.runnablePartie);
    this.threadPartie.start();
   }
 
   
   private void initKeyListener() {
    this.keyListener = new KeyListener()
       {
         public void keyTyped(KeyEvent e) {}
  
         public void keyReleased(KeyEvent e) {}
  
         public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == 81) {
            if (!mapControleur.this.gameModele.getIAmMoving())
             {
              mapControleur.this.gameModele.setIAmMoving(true);
              mapControleur.this.mapVue.getGrilleVue().getJoueur0().setBackground(Ressources.joueurs[0].getSubimage(18, 75, 18, 35));
              mapControleur.this.mapVue.getGrilleVue().getJoueur0().update();
              mapControleur.this.gameModele.moveClient(0);
             }
          } else if (e.getKeyCode() == 68) {
            if (!mapControleur.this.gameModele.getIAmMoving())
             {
              mapControleur.this.gameModele.setIAmMoving(true);
              mapControleur.this.mapVue.getGrilleVue().getJoueur0().setBackground(Ressources.joueurs[0].getSubimage(18, 113, 18, 35));
              mapControleur.this.mapVue.getGrilleVue().getJoueur0().update();
              mapControleur.this.gameModele.moveClient(1);
             }
          } else if (e.getKeyCode() == 83) {
            if (!mapControleur.this.gameModele.getIAmMoving())
             {
              mapControleur.this.gameModele.setIAmMoving(true);
              mapControleur.this.mapVue.getGrilleVue().getJoueur0().setBackground(Ressources.joueurs[0].getSubimage(18, 37, 18, 35));
              mapControleur.this.mapVue.getGrilleVue().getJoueur0().update();
              mapControleur.this.gameModele.moveClient(2);
             }
          } else if (e.getKeyCode() == 90 && 
            !mapControleur.this.gameModele.getIAmMoving()) {
            mapControleur.this.gameModele.setIAmMoving(true);
            mapControleur.this.mapVue.getGrilleVue().getJoueur0().setBackground(Ressources.joueurs[0].getSubimage(18, 0, 18, 35));
            mapControleur.this.mapVue.getGrilleVue().getJoueur0().update();
            mapControleur.this.gameModele.moveClient(3);
           } 
         }
       };
 
    this.mapVue.addKeyListener(this.keyListener);
    this.mapVue.addKeyListener(this.tchatControleur.getKeyAdapterOutEntry());
    this.mapVue.addKeyListener(this.quitterControleur.getKeyAdapterOut());
   }
 
   
   private void initRunnablePartie() {
    this.runnablePartie = new Runnable() {
         private boolean run = true;
         
         public void run() {
          while (this.run) {
            String reponse = Main.socket.readLine();
            if (reponse == null) {
               
              mapControleur.this.gameModele.setChanged("menu broadcast");
              this.run = false;
              Main.socket.close();
               
               continue;
             } 
            if (reponse.length() > 4 && reponse.substring(0, 3).equals("map")) {
              mapControleur.this.gameModele.updateMap();
               continue;
             } 
            if (reponse.length() > 3 && reponse.substring(0, 14).equals("rival message ")) {
               
              String[] s = reponse.substring(14).split(" ", 2);
              String nomJoueur = s[0];
              String message = s[1];
              mapControleur.this.tchatControleur.getTchatModele().ajouterMessage(message, nomJoueur);
               continue;
             } 
            if (reponse.length() > 14 && reponse.substring(0, 13).equals("team contains")) {
               
              mapControleur.this.gameModele.equipeContains(Integer.parseInt(reponse.substring(14)));
               continue;
             } 
            if (reponse.length() == 20 && reponse.substring(0, 18).equals("encounter new wild")) {
               
              mapControleur.this.gameModele.setFight(true);
              mapControleur.this.gameModele.setChanged("new wild");
              this.run = false;
               continue;
             } 
            if (reponse.length() == 21 && reponse.substring(0, 19).equals("encounter new rival")) {
               
              mapControleur.this.gameModele.setFight(true);
              mapControleur.this.gameModele.setChanged("new rival");
              this.run = false;
             } 
           } 
         }
       };
   }
 
 
   
   private void initRunnableNeige() {
    this.runnableNeige = new Runnable()
       {
         
         public void run()
         {
           while (true) {
            for (int i = 0; i < 3; i++) {
               
              for (int j = 0; j < 10; j++) {
                 
                mapControleur.this.gameModele.setSpriteNeige(Ressources.neige.getSubimage(j * 256, i * 192, 256, 192));
                mapControleur.this.mapVue.update();
                 try {
                  Thread.sleep(80L);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                 } 
               } 
             } 
           } 
         }
       };
   }
 }