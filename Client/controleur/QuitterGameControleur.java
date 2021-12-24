package controleur;
 
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import principal.Main;
import vue.QuitterGamePanel;
 
 
 
public class QuitterGameControleur{
  private QuitterGamePanel quitterVue;
  private KeyAdapter keyAdapterIn;
  private KeyAdapter keyAdapterOut;
  private MouseAdapter mouseAdapterIn;
  private int btnSelected;
   
  public QuitterGameControleur(QuitterGamePanel quitterVue) {
    this.quitterVue = quitterVue;
    this.btnSelected = 0;
    this.quitterVue.selectBtn(this.btnSelected);
    initMouseListener();
    initKeyListener();
  }
 
   
  private void initMouseListener() {
    this.mouseAdapterIn = new MouseAdapter()
       {
         public void mouseEntered(MouseEvent e) {
          super.mouseEntered(e);
          int i = ((JLabel)e.getSource()).equals(QuitterGameControleur.this.quitterVue.getLblQuit()) ? 0 : 1;
          QuitterGameControleur.this.quitterVue.selectBtn(i);
         }
   
         public void mouseExited(MouseEvent e) {
          super.mouseExited(e);
          QuitterGameControleur.this.quitterVue.selectBtn(QuitterGameControleur.this.btnSelected);
         }
 
         
         public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          if (((JLabel)e.getSource()).equals(QuitterGameControleur.this.quitterVue.getLblQuit())) {
            Main.socket.close();
          }
          else {
            QuitterGameControleur.this.quitterVue.setVisible(false);
            QuitterGameControleur.this.quitterVue.transferFocus();
          } 
         }
        };
    this.quitterVue.getLblCancel().addMouseListener(this.mouseAdapterIn);
    this.quitterVue.getLblQuit().addMouseListener(this.mouseAdapterIn);
  }
 
   
   private void initKeyListener() {
    this.keyAdapterIn = new KeyAdapter()
       {
         public void keyPressed(KeyEvent e) {
          super.keyPressed(e);
          if (e.getKeyCode() == 37 || e.getKeyCode() == 39 || 
            e.getKeyCode() == 81 || e.getKeyCode() == 68) {
            QuitterGameControleur.this.btnSelected = (QuitterGameControleur.this.btnSelected + 1) % 2;
            QuitterGameControleur.this.quitterVue.selectBtn(QuitterGameControleur.this.btnSelected);
           }
          else if (e.getKeyCode() == 27) {
             
            QuitterGameControleur.this.quitterVue.setVisible(false);
            QuitterGameControleur.this.quitterVue.transferFocus();
           }
          else if (e.getKeyCode() == 10) {
            if (QuitterGameControleur.this.btnSelected == 1) {
               
              QuitterGameControleur.this.quitterVue.setVisible(false);
              QuitterGameControleur.this.quitterVue.transferFocus();
             }
             else {
              Main.socket.close();
             } 
           } 
         }
       };
    this.keyAdapterOut = new KeyAdapter()
       {
         public void keyPressed(KeyEvent e) {
          super.keyPressed(e);
          if (e.getKeyCode() == 27) {  
            QuitterGameControleur.this.btnSelected = 0;
            QuitterGameControleur.this.quitterVue.selectBtn(QuitterGameControleur.this.btnSelected);
            QuitterGameControleur.this.quitterVue.setVisible(true);
            QuitterGameControleur.this.quitterVue.requestFocusInWindow();
           } 
         }
       };
    this.quitterVue.addKeyListener(this.keyAdapterIn);
   }
   
   public KeyAdapter getKeyAdapterOut() {
    return this.keyAdapterOut;
   }
}