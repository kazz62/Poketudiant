 package modele;
 
 import java.util.Observable;
 
 public class TchatModele
   extends Observable
 {
   public void ajouterMessage(String message, String nomJoueur) {
    String msg = String.valueOf(nomJoueur) + ": " + message;
    setChanged();
    notifyObservers(msg);
   }
 }