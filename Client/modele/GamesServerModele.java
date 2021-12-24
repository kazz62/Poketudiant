 package modele;
 
 import java.util.ArrayList;
 import java.util.Observable;
 import principal.Main;
 
 
 
 
 
 
 
 
 public class GamesServerModele
   extends Observable
 {
  private ArrayList<String> listGame = new ArrayList<>();
   
   private boolean actualisation = true;
   
   private boolean createNewGame = false;
   
   public void createGame(String name) {
    Main.socket.sendTo("create game " + name + "\n");
     
    String reponse = Main.socket.readLine();
    System.out.println("reponse " + reponse);
     
    if (reponse.equals("game created")) {
       
      joinGame(name);
     }
    else if (reponse.equals("cannot create game")) {
       
      System.out.println("erreur lors de la cr�ation de la partie");
     } 
   }
 
   
   public void joinGame(String name) {
    Main.socket.sendTo("join game " + name + "\n");
     
    String reponse = Main.socket.readLine();
    System.out.println("reponse " + reponse);
     
    if (reponse.equals("game joined")) {
       
      setChanged("join game");
      notifyObservers();
     }
    else if (reponse.equals("cannot join game")) {
       
      System.out.println("erreur lors de la connection � la partie");
     } 
   }
 
   
   public void actualiser() {
    Thread t = new Thread(new Runnable()
         {
           public void run()
           {
            Main.socket.sendTo("require game list\n");
             
            String reponse = Main.socket.readLine();
            System.out.println("reponse " + reponse);
            if (reponse.length() >= 16 && reponse.substring(0, 15).equalsIgnoreCase("number of games")) {
               
              int nbGame = Integer.parseInt(reponse.substring(16));
              for (int i = 0; i < nbGame; i++) {
                if ((reponse = Main.socket.readLine()) == null) System.out.println("erreur protocole nb partie"); 
                GamesServerModele.this.listGame.add(reponse);
                GamesServerModele.this.setChanged("new game");
                GamesServerModele.this.notifyObservers();
               } 
              if (nbGame < 4) {
                 
                GamesServerModele.this.setChanged("create game");
                GamesServerModele.this.notifyObservers();
               } 
             } 
             
            GamesServerModele.this.setChanged("actualisation");
            GamesServerModele.this.notifyObservers();
           }
         });
     t.start();
   }
 
   
   public String getLastGame() {
    if (this.listGame.size() > 0) return this.listGame.get(this.listGame.size() - 1); 
    return null;
   }
 
   
   public void setChanged(String s) {
    setChanged();
    notifyObservers(s);
   }
   
   public ArrayList<String> getListGame() {
    return this.listGame;
   }
 
   
   public boolean getActualisation() {
    return this.actualisation;
   }
 
   
   public void setActualisation(boolean b) {
    this.actualisation = b;
   }
 
   
   public boolean getCreateNewGame() {
    return this.createNewGame;
   }
 
   
   public void setCreateNewGame(boolean b) {
    this.createNewGame = b;
   }
   
   public GamesServerModele(String adresse) {}
 }