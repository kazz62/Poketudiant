 package principal;
 
 import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.Rectangle;
 import java.awt.geom.Rectangle2D;
 import javax.swing.JButton;
 import javax.swing.JComponent;
 import javax.swing.plaf.basic.BasicScrollBarUI;
 
 
 
 
 
 
 public class MyScrollBarUI
   extends BasicScrollBarUI
 {
  private int var = Ressources.var;
 
 
 
 
   
   protected void paintTrack(Graphics g, JComponent arg1, Rectangle arg2) {
    g.translate(arg2.x, arg2.y);
    Graphics2D g2d = (Graphics2D)g;
 
    g2d.setColor(new Color(0.0F, 0.0F, 0.0F, 0.0F));
    g2d.fill(new Rectangle2D.Double((0 * this.var), (0 * this.var), (8 * this.var), arg2.getHeight()));
    g2d.fill(new Rectangle2D.Double((14 * this.var), (0 * this.var), (8 * this.var), arg2.getHeight()));
    g2d.setColor(new Color(88, 112, 136));
    g2d.fill(new Rectangle2D.Double((8 * this.var), (0 * this.var), (2 * this.var), arg2.getHeight()));
    g2d.fill(new Rectangle2D.Double((12 * this.var), (0 * this.var), (2 * this.var), arg2.getHeight()));
    g2d.setColor(new Color(255, 255, 255));
    g2d.fill(new Rectangle2D.Double((10 * this.var), (0 * this.var), (2 * this.var), arg2.getHeight()));
    g.translate(-arg2.x, -arg2.y);
   }
 
   
   protected void paintThumb(Graphics g, JComponent arg1, Rectangle arg2) {
    g.translate(arg2.x, arg2.y);
    Graphics2D g2d = (Graphics2D)g;
    g2d.setColor(new Color(0.0F, 0.0F, 0.0F, 0.0F));
    g2d.fill(new Rectangle2D.Double((0 * this.var), (0 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((21 * this.var), (0 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), arg2.getHeight() - (1 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((21 * this.var), arg2.getHeight() - (1 * this.var), (1 * this.var), (1 * this.var)));
    g2d.setColor(new Color(152, 168, 164));
    g2d.fill(new Rectangle2D.Double((1 * this.var), (0 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((20 * this.var), (0 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), (1 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((21 * this.var), (1 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((1 * this.var), arg2.getHeight() - (1 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((20 * this.var), arg2.getHeight() - (1 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), arg2.getHeight() - (2 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((21 * this.var), arg2.getHeight() - (2 * this.var), (1 * this.var), (1 * this.var)));
    g2d.setColor(new Color(88, 112, 136));
    g2d.fill(new Rectangle2D.Double((2 * this.var), (0 * this.var), (18 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((1 * this.var), (1 * this.var), (20 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), (2 * this.var), (3 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((19 * this.var), (2 * this.var), (3 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), (3 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((20 * this.var), (3 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((2 * this.var), arg2.getHeight() - (1 * this.var), (18 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((1 * this.var), arg2.getHeight() - (2 * this.var), (20 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), arg2.getHeight() - (3 * this.var), (3 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((19 * this.var), arg2.getHeight() - (3 * this.var), (3 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), arg2.getHeight() - (4 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((20 * this.var), arg2.getHeight() - (4 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), arg2.getHeight() - (5 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((20 * this.var), arg2.getHeight() - (5 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), arg2.getHeight() - (6 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((20 * this.var), arg2.getHeight() - (6 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((0 * this.var), arg2.getHeight() - (7 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((20 * this.var), arg2.getHeight() - (7 * this.var), (2 * this.var), (1 * this.var)));
    g2d.setColor(new Color(255, 255, 255));
    g2d.fill(new Rectangle2D.Double((3 * this.var), (2 * this.var), (16 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((2 * this.var), (3 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((18 * this.var), (3 * this.var), (2 * this.var), (1 * this.var)));
    g2d.setColor(new Color(200, 208, 216));
    g2d.fill(new Rectangle2D.Double((2 * this.var), arg2.getHeight() - (6 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((19 * this.var), arg2.getHeight() - (6 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((2 * this.var), arg2.getHeight() - (7 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((19 * this.var), arg2.getHeight() - (7 * this.var), (1 * this.var), (1 * this.var)));
    g2d.setColor(new Color(152, 168, 184));
    g2d.fill(new Rectangle2D.Double((3 * this.var), arg2.getHeight() - (3 * this.var), (16 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((2 * this.var), arg2.getHeight() - (4 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((18 * this.var), arg2.getHeight() - (4 * this.var), (2 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((2 * this.var), arg2.getHeight() - (5 * this.var), (1 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((19 * this.var), arg2.getHeight() - (5 * this.var), (1 * this.var), (1 * this.var)));
    g2d.setColor(new Color(88, 112, 248));
    g2d.fill(new Rectangle2D.Double((4 * this.var), (3 * this.var), (14 * this.var), (1 * this.var)));
    g2d.setColor(new Color(56, 80, 200));
    g2d.fill(new Rectangle2D.Double((3 * this.var), arg2.getHeight() - (6 * this.var), (16 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((3 * this.var), arg2.getHeight() - (7 * this.var), (16 * this.var), (1 * this.var)));
    g2d.setColor(new Color(32, 48, 152));
    g2d.fill(new Rectangle2D.Double((4 * this.var), arg2.getHeight() - (4 * this.var), (14 * this.var), (1 * this.var)));
    g2d.fill(new Rectangle2D.Double((3 * this.var), arg2.getHeight() - (5 * this.var), (16 * this.var), (1 * this.var)));
    for (int i = 4 * this.var; i < arg2.getHeight() - (7 * this.var); i += this.var) {
      g2d.setColor(new Color(88, 112, 136));
      g2d.fill(new Rectangle2D.Double((0 * this.var), i, (2 * this.var), (1 * this.var)));
      g2d.fill(new Rectangle2D.Double((20 * this.var), i, (2 * this.var), (1 * this.var))); 
      g2d.setColor(new Color(255, 255, 255));
      g2d.fill(new Rectangle2D.Double((2 * this.var), i, (1 * this.var), (1 * this.var)));
      g2d.fill(new Rectangle2D.Double((19 * this.var), i, (1 * this.var), (1 * this.var)));
      g2d.setColor(new Color(88, 112, 248));
      g2d.fill(new Rectangle2D.Double((3 * this.var), i, (16 * this.var), (1 * this.var)));
      g2d.fill(new Rectangle2D.Double((3 * this.var), i, (16 * this.var), (1 * this.var)));
    } 
     
    g.translate(-arg2.x, -arg2.y);
 }
 
   
  protected JButton createDecreaseBouton(int arg0) {
   JButton bouton = new JButton()
      {
        private static final long serialVersionUID = 1L;
 
 
         
        protected void paintComponent(Graphics g) {
          g.drawImage(Ressources.scrollbar_flecheTop, 0, 0, 22 * MyScrollBarUI.this.var, 16 * MyScrollBarUI.this.var, this);
        }
      };
 
     
    bouton.setPreferredSize(new Dimension(22 * this.var, 16 * this.var));
    bouton.setOpaque(false);
    bouton.setBorder(null);
    return bouton;
  }
 
   
  protected JButton createIncreaseBouton(int orientation) {
    JButton bouton = new JButton()
      {
        private static final long serialVersionUID = 1L;
 
 
         
        protected void paintComponent(Graphics g) {
         g.drawImage(Ressources.scrollbar_flecheBot, 0, 0, 22 * MyScrollBarUI.this.var, 16 * MyScrollBarUI.this.var, this);
        }
      };
     
    bouton.setPreferredSize(new Dimension(22 * this.var, 16 * this.var));
    bouton.setOpaque(false);
    bouton.setBorder(null);
    return bouton;
  }
}
