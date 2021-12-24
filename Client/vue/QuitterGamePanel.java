package vue;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
 
 
public class QuitterGamePanel extends JPanel {
  private static final Color COULEURBACKGROUND = new Color(51, 51, 51, 170);
  private static final Color BTNCOLOROUT = new Color(255, 255, 255);
  private static final Color BTNCOLOROVER = new Color(204, 0, 0);
  private static final Color TRANSPARENT = new Color(0, 0, 0, 0);
  private static final Font POLICEPIXEL = new Font("Pixel Operator 8", 0, 17); 
  private JLabel lblCancel;
  private JLabel lblQuit;
  private JLabel lblQuitMessage;
   
  public QuitterGamePanel() {
    init();
  }
   
  private void init() {
    this.lblCancel = new JLabel("Cancel"){
      protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
      }
    };
    this.lblQuit = new JLabel("Quitter"){
      protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
      }
    };
    this.lblQuitMessage = new JLabel("Etes-vous sûr de vouloir quitter ?"){
      protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
      }
    };
     
    this.lblCancel.setForeground(BTNCOLOROUT);
    this.lblCancel.setOpaque(false);
    this.lblCancel.setBorder((Border)null);
    this.lblCancel.setBackground(TRANSPARENT);
    this.lblCancel.setFont(POLICEPIXEL);
    this.lblCancel.setHorizontalTextPosition(0);
    this.lblCancel.setHorizontalAlignment(0);
    this.lblQuit.setBackground(TRANSPARENT);
    this.lblQuit.setForeground(BTNCOLOROUT);
    this.lblQuit.setOpaque(false);
    this.lblQuit.setBorder((Border)null);
    this.lblQuit.setFont(POLICEPIXEL);
    this.lblQuit.setHorizontalTextPosition(0);
    this.lblQuit.setHorizontalAlignment(0); 
    this.lblQuitMessage.setOpaque(false);
    this.lblQuitMessage.setForeground(Color.WHITE);
    this.lblQuitMessage.setBackground(TRANSPARENT);
    this.lblQuitMessage.setFont(POLICEPIXEL);
    this.lblQuitMessage.setHorizontalTextPosition(0);
    this.lblQuitMessage.setHorizontalAlignment(0); 
    JPanel panelBoutons = new JPanel(new FlowLayout(1, 10, 5));
    panelBoutons.add(this.lblQuit);
    panelBoutons.add(this.lblCancel);
    panelBoutons.setBackground(TRANSPARENT);
    panelBoutons.setBorder((Border)null);
    panelBoutons.setOpaque(true); 
    setLayout(new BorderLayout());
    add(this.lblQuitMessage, "Center");
    add(panelBoutons, "South");
    setBackground(COULEURBACKGROUND);
    setBorder((Border)null);
    setOpaque(false);
    setFocusable(true);
    setVisible(false);
  }
 
   
  public void selectBtn(int ind) {
    if (ind == 0){
      this.lblQuit.setForeground(BTNCOLOROVER);
      this.lblCancel.setForeground(BTNCOLOROUT);
    }else {
      this.lblQuit.setForeground(BTNCOLOROUT);
      this.lblCancel.setForeground(BTNCOLOROVER);
    } 
    repaint();
  }
 
  public JLabel getLblQuit() {
    return this.lblQuit;
  }  
   
  public JLabel getLblCancel() {
    return this.lblCancel;
  }
 
  protected void paintComponent(Graphics g) {
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(g);
  }
}