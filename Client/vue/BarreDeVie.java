 package vue;
 
 import java.awt.Color;
 import java.awt.Graphics;
 import javax.swing.BorderFactory;
 import javax.swing.JPanel;
 
 public class BarreDeVie extends JPanel
 {
   private static final Color VERYLOWCOLOR = new Color(229, 29, 22);
   private static final Color LOWCOLOR = new Color(229, 119, 29);
   private static final Color HALFCOLOR = new Color(229, 226, 45);
   private static final Color HIGHCOLOR = new Color(24, 192, 32);

   private Color actualColor;
   private int pct;
   
   public BarreDeVie() {
     init();
   }
 
   
   public void init() {
    setBorder(BorderFactory.createLineBorder(Color.black, 1));
    setBackground(Color.white);
   }
 
   
   public void setVie(int currentVie, int maxVie) {
     this.pct = (int)(currentVie / maxVie * 100.0F);
     if (this.pct <= 25) {
       
       this.actualColor = VERYLOWCOLOR;
     }
     else if (this.pct <= 50) {
       
       this.actualColor = LOWCOLOR;
     }
     else if (this.pct <= 75) {
       
       this.actualColor = HALFCOLOR;
     }
    else if (this.pct <= 100) {
       
       this.actualColor = HIGHCOLOR;
     } 
   }
 
   
   protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     g.setColor(this.actualColor);
     g.fillRect(0, 0, getWidth() * this.pct / 100, getHeight());
   }
 }