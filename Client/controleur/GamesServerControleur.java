package controleur;
 
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modele.GamesServerModele;
import vue.BoutonMenu;
import vue.GamesServerVue;
 
 
public class GamesServerControleur
 {
   private GamesServerVue gamesServerVue;
   private GamesServerModele gamesServerModele;
   public static MouseListener mouseListener;
   
  public GamesServerControleur(GamesServerVue gamesServerVue, GamesServerModele gamesServerModele) {
    this.gamesServerVue = gamesServerVue;
    this.gamesServerModele = gamesServerModele;
    init();
    MouseEvent me = new MouseEvent((Component)gamesServerVue.getBoutonActualiser(), 0, 0L, 0, 0, 0, 1, false);
    mouseListener.mouseReleased(me);
  }
 
   
   private void init() {
    initMouseListener();
  }
 
   
   private void initMouseListener() {
    mouseListener = new MouseListener()
       {
         
         public void mouseReleased(MouseEvent e)
         {
          BoutonMenu tmp = (BoutonMenu)e.getSource();
          if (tmp.getName().equals("Actualiser")) {
            if (GamesServerControleur.this.gamesServerModele.getActualisation() && !GamesServerControleur.this.gamesServerModele.getCreateNewGame())
             {
              GamesServerControleur.this.gamesServerModele.setActualisation(false);
              GamesServerControleur.this.gamesServerVue.actualiserLock();
              GamesServerControleur.this.gamesServerModele.actualiser();
             }
          } else if (tmp.getName().equals("Create game")) {
            if (GamesServerControleur.this.gamesServerModele.getActualisation() && !GamesServerControleur.this.gamesServerModele.getCreateNewGame())
             {
              GamesServerControleur.this.gamesServerModele.setCreateNewGame(true);
              GamesServerControleur.this.gamesServerVue.createGameLock();
              GamesServerControleur.this.gamesServerVue.createGame();
             }
          } else if (tmp.getName().equals("Annuler")) {
            GamesServerControleur.this.gamesServerVue.quitCreateGame();
            GamesServerControleur.this.gamesServerVue.createGameLock();
            GamesServerControleur.this.gamesServerModele.setCreateNewGame(false);
           }
          else if (tmp.getName().equals("Valider") && !GamesServerControleur.this.gamesServerVue.getCreateGameVue().getTextSaisie().isEmpty()) {
            GamesServerControleur.this.gamesServerVue.quitCreateGame();
            GamesServerControleur.this.gamesServerModele.createGame(GamesServerControleur.this.gamesServerVue.getCreateGameVue().getTextSaisie());
            GamesServerControleur.this.gamesServerVue.createGameUnlock();
            GamesServerControleur.this.gamesServerModele.setCreateNewGame(false);
           }
          else if (tmp.getName().length() > 4 && tmp.getName().substring(0, 4).equals("Game")) {
            GamesServerControleur.this.gamesServerModele.joinGame(tmp.getName().substring(5));
           } 
        }
 
 
 
         
         public void mousePressed(MouseEvent e) {}
 
 
 
         
         public void mouseExited(MouseEvent e) {}
 
 
 
         
         public void mouseEntered(MouseEvent e) {}
 
 
         
         public void mouseClicked(MouseEvent e) {}
       };
    this.gamesServerVue.getBoutonActualiser().addMouseListener(mouseListener);
    this.gamesServerVue.getBoutonNewGame().addMouseListener(mouseListener);
    this.gamesServerVue.getCreateGameVue().getBoutonAnnuler().addMouseListener(mouseListener);
    this.gamesServerVue.getCreateGameVue().getBoutonValider().addMouseListener(mouseListener);
  }
}