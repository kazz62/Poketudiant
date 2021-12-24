 package controleur;
 
 import java.awt.Component;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import modele.BroadcastModele;
 import principal.Main;
 import vue.BroadcastVue;
 import vue.BoutonMenu;
 
 public class BroadcastControleur
 {
   private BroadcastVue broadcastVue;
   private BroadcastModele broadcastModele;
   public static MouseListener mouseListener;
   
   public BroadcastControleur(BroadcastVue broadcastVue, BroadcastModele broadcastModele) {
    this.broadcastVue = broadcastVue;
    this.broadcastModele = broadcastModele;
    init();
    MouseEvent me = new MouseEvent((Component)broadcastVue.getBoutonActualiser(), 0, 0L, 0, 0, 0, 1, false);
    mouseListener.mouseReleased(me);
   }
 
 
   
   private void init() {
    initKeyListener();
    initMouseListener();
   }

   private void initKeyListener() {}
 
   private void initMouseListener() {
    mouseListener = new MouseListener()
       {
         
         public void mouseReleased(MouseEvent e)
         {
          BoutonMenu tmp = (BoutonMenu)e.getSource();
          if (tmp.getName().equals("Actualiser")) {
             if (BroadcastControleur.this.broadcastModele.getActualisation())
             {
              BroadcastControleur.this.broadcastModele.setActualisation(false);
              BroadcastControleur.this.broadcastVue.actualiserLock();
              BroadcastControleur.this.broadcastModele.actualiser();
             }
          } else if (tmp.getName().substring(0, 6).equals("Server")) {
             
            Main.socket.connectTo(tmp.getName().substring(7), 9001);
            BroadcastControleur.this.broadcastModele.setChanged("GamesServer " + tmp.getName().substring(7));
           } 
         }
       
         public void mousePressed(MouseEvent e) {}
     
         public void mouseExited(MouseEvent e) {}
   
         public void mouseEntered(MouseEvent e) {}
     
         public void mouseClicked(MouseEvent e) {}
       };
    this.broadcastVue.getBoutonActualiser().addMouseListener(mouseListener);
   }
 }