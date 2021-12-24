package vue;
 
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
 
public class Texture extends JPanel{

  private static final long serialVersionUID = 1L;
  private BufferedImage background;
   
  public Texture(BufferedImage background) {
    this.background = background;
    setOpaque(false);
  }
   
  public void setBackground(BufferedImage bufferImg) {
    this.background = bufferImg;
    validate();
    repaint();
  }
 
   
  public void update() {
    validate();
    repaint();
  }

  protected void paintComponent(Graphics g) {
    g.drawImage(this.background, 0, 0, getWidth(), getHeight(), this);
  }
}