 package modele;
 
 import java.util.Observable;
 import principal.Main;
 
 
 
public class CombatModele extends Observable{
   private PersonnageModele joueur0;
   private PersonnageModele rival;
   private GameModele gameModele;
   
   public CombatModele(GameModele gameModele) {
    this.gameModele = gameModele;
    this.joueur0 = new PersonnageModele(0);
    this.rival = new PersonnageModele(1);
    this.rival.setNbPoketudiant(Integer.parseInt(Main.socket.getLastReadLine().split(" ")[3]));
   }
 
 
   
   public PersonnageModele getJoueur0() {
    return this.joueur0;
   }
   
   public PersonnageModele getRival() {
    return this.rival;
   }
 
   
   public void setChanged(String s) {
    setChanged();
    notifyObservers(s);
   }
   
   public GameModele getGameModele() {
    return this.gameModele;
   }
}