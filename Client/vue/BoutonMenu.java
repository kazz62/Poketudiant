 package vue;
 
 import java.awt.Graphics;
 import java.awt.LayoutManager;
 import java.awt.image.BufferedImage;
 import javax.swing.JPanel;
 import principal.Ressources;
 
 
 public class BoutonMenu
   extends JPanel
 {
   private static final long serialVersionUID = 1L;
   private BufferedImage background;
   private boolean isSelected;
   private int var;
   
   public BoutonMenu(BufferedImage background, String name) {
      this.background = background;
      setLayout((LayoutManager)null);
      setName(name);
      setOpaque(false);
      this.var = Ressources.var;
   }
 
   public void setIsSelected(boolean b) {
      this.isSelected = b;
   }
   
   public boolean getSelected() {
     return this.isSelected;
   }
 
   public void setBackground(BufferedImage bufferImg) {
      this.background = bufferImg;
   }
 
   public void update() {
     this.var = Ressources.var;
     validate();
     repaint();
   }

   protected void paintComponent(Graphics g) {
      g.drawImage(this.background, 0, 0, this.background.getWidth() * this.var, this.background.getHeight() * this.var, this);
   }
 }
