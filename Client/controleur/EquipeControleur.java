 package controleur;
 
 import java.awt.Component;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import javax.swing.JLabel;
 import javax.swing.SwingUtilities;
 import principal.Main;
 import vue.PoketudiantIcon;
 import vue.EquipeVue;
 
 
 public class EquipeControleur
 {
   private EquipeVue equipeVue;
   private MouseAdapter mouseAdapter;
   private MouseAdapter releaseAdapter;
   private MouseAdapter moveUpAdapter;
   private MouseAdapter moveDownAdapter;
   
   public EquipeControleur(EquipeVue equipeVue) {
    this.equipeVue = equipeVue;
    initMouseListener();
   }
 
   
   public void initMouseListener() {
    this.mouseAdapter = new MouseAdapter()
       {
         public void mouseEntered(MouseEvent e) {
          super.mouseEntered(e);
          PoketudiantIcon pokeIcn = (PoketudiantIcon)e.getSource();
          pokeIcn.showOptions();
         }
 
         
         public void mouseExited(MouseEvent e) {
          super.mouseExited(e);
          PoketudiantIcon pokeIcn = (PoketudiantIcon)e.getSource();
          pokeIcn.hideOptions();
         }
       };
     
    this.releaseAdapter = new MouseAdapter()
       {
         public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
          JLabel lbl = (JLabel)e.getSource();
          int indice = ((PoketudiantIcon)lbl.getParent()).getIndice();
          Main.socket.sendTo("poketudiant " + indice + " free\n");
         }
 
         
         public void mouseEntered(MouseEvent e) {
          super.mouseEntered(e);
          PoketudiantIcon pokeIcn = (PoketudiantIcon)((JLabel)e.getSource()).getParent();
          pokeIcn.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(), e, (Component)pokeIcn));
         }
 
         
         public void mouseExited(MouseEvent e) {
          super.mouseExited(e);
          PoketudiantIcon pokeIcn = (PoketudiantIcon)((JLabel)e.getSource()).getParent();
          pokeIcn.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(), e, (Component)pokeIcn));
         }
       };
     
    this.moveUpAdapter = new MouseAdapter()
       {
         public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          JLabel lbl = (JLabel)e.getSource();
          int indice = ((PoketudiantIcon)lbl.getParent()).getIndice();
          Main.socket.sendTo("poketudiant " + indice + " move up\n");
         }
 
         
         public void mouseEntered(MouseEvent e) {
          super.mouseEntered(e);
          PoketudiantIcon pokeIcn = (PoketudiantIcon)((JLabel)e.getSource()).getParent();
          pokeIcn.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(), e, (Component)pokeIcn));
         }
         
         public void mouseExited(MouseEvent e) {
          super.mouseExited(e);
          PoketudiantIcon pokeIcn = (PoketudiantIcon)((JLabel)e.getSource()).getParent();
          pokeIcn.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(), e, (Component)pokeIcn));
         }
       };
    this.moveDownAdapter = new MouseAdapter()
       {
         public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          JLabel lbl = (JLabel)e.getSource();
          int indice = ((PoketudiantIcon)lbl.getParent()).getIndice();
          Main.socket.sendTo("poketudiant " + indice + " move down\n");
         }
 
         
         public void mouseEntered(MouseEvent e) {
          super.mouseEntered(e);
          PoketudiantIcon pokeIcn = (PoketudiantIcon)((JLabel)e.getSource()).getParent();
          pokeIcn.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(), e, (Component)pokeIcn));
         }
 
         
         public void mouseExited(MouseEvent e) {
          super.mouseExited(e);
          PoketudiantIcon pokeIcn = (PoketudiantIcon)((JLabel)e.getSource()).getParent();
          pokeIcn.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(), e, (Component)pokeIcn));
         }
       };
     
    for (PoketudiantIcon pokeIcn : this.equipeVue.getEquipeIcons()) {
       
      pokeIcn.addMouseListener(this.mouseAdapter);
      pokeIcn.addOptionsMouseListener(this.moveUpAdapter, this.moveDownAdapter, this.releaseAdapter);
     } 
   }
 }