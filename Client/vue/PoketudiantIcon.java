 package vue;
 
 import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.event.MouseListener;
 import javax.swing.BorderFactory;
 import javax.swing.ImageIcon;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.SpringLayout;
 import principal.Poketudiant;
 import principal.Ressources;
 
 
 
 
 public class PoketudiantIcon
   extends JPanel
 {
   private BarreDeVie barreDeVie;
   private JLabel pokeIcon;
   private JLabel downArrow;
   private JLabel upArrow;
   private JLabel releaseIcon;
   private JLabel levelLabel;
   private int indice;
   
   public PoketudiantIcon(int indice) {
     super(new SpringLayout());
     this.indice = indice;
     init();
   }
   
   private void init() {
     this.barreDeVie = new BarreDeVie();
     this.pokeIcon = new JLabel()
       {
         protected void paintComponent(Graphics g) {
           g.setColor(getBackground());
           g.fillRect(0, 0, getWidth(), getHeight());
           super.paintComponent(g);
         }
       };
     this.downArrow = new JLabel(new ImageIcon(Ressources.boutonMoveDown));
     this.upArrow = new JLabel(new ImageIcon(Ressources.boutonMoveUp));
     this.releaseIcon = new JLabel(new ImageIcon(Ressources.boutonFree));
     this.levelLabel = new JLabel();
     this.barreDeVie.setPreferredSize(new Dimension(88, 10));
     this.pokeIcon.setPreferredSize(new Dimension(88, 80));
     this.downArrow.setPreferredSize(new Dimension(20, 30));
     this.upArrow.setPreferredSize(new Dimension(20, 30));
     this.releaseIcon.setPreferredSize(new Dimension(20, 20));
     this.levelLabel.setPreferredSize(new Dimension(30, 30));
     this.levelLabel.setFont(new Font("Pixel Operator 8", 1, 15));
     this.levelLabel.setHorizontalTextPosition(0);
     this.levelLabel.setHorizontalAlignment(0);
     this.levelLabel.setVerticalAlignment(0);
     this.levelLabel.setVerticalTextPosition(0);
     this.levelLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
     this.levelLabel.setForeground(new Color(0, 0, 0));
     this.levelLabel.setOpaque(true);
     this.levelLabel.setBackground(Color.white);
     this.pokeIcon.setBackground(Color.WHITE);
     this.pokeIcon.setOpaque(false);
     this.pokeIcon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     add(this.barreDeVie);
     add(this.downArrow);
     add(this.upArrow);
     add(this.levelLabel);
     add(this.pokeIcon);
     add(this.releaseIcon);
     setPreferredSize(new Dimension(128, 120));
     SpringLayout layout = (SpringLayout)getLayout();
     layout.putConstraint("West", this.pokeIcon, 10, "West", this);
     layout.putConstraint("North", this.pokeIcon, 10, "North", this);
     layout.putConstraint("North", this.barreDeVie, 0, "South", this.pokeIcon);
     layout.putConstraint("West", this.barreDeVie, 0, "West", this.pokeIcon);
     layout.putConstraint("West", this.upArrow, 0, "East", this.pokeIcon);
     layout.putConstraint("North", this.upArrow, 0, "North", this.pokeIcon);
     layout.putConstraint("North", this.downArrow, 0, "South", this.upArrow);
     layout.putConstraint("West", this.downArrow, 0, "East", this.pokeIcon);
     layout.putConstraint("North", this.releaseIcon, 0, "South", this.downArrow);
     layout.putConstraint("West", this.releaseIcon, 0, "East", this.pokeIcon);
     layout.putConstraint("North", this.levelLabel, -5, "North", this.pokeIcon);
     layout.putConstraint("West", this.levelLabel, -5, "West", this.pokeIcon);
     setBackground(new Color(0, 0, 0, 0));
     setOpaque(false);
     hideOptions();
   }
   
   public int getIndice() {
     return this.indice;
   }
 
   
   public void update(Poketudiant p) {
     this.levelLabel.setText((new StringBuilder(String.valueOf(p.getLvl()))).toString());
     this.pokeIcon.setIcon(new ImageIcon(p.getVariety().getPortrait().getScaledInstance(88, 80, 1)));
     this.barreDeVie.setVie(p.getPvCurrent(), p.getPvMax());
     repaint();
   }
 
   
   public void showOptions() {
     this.upArrow.setVisible(true);
     this.downArrow.setVisible(true);
     this.releaseIcon.setVisible(true);
   }
 
   
   public void hideOptions() {
     this.upArrow.setVisible(false);
     this.downArrow.setVisible(false);
     this.releaseIcon.setVisible(false);
   }
 
   
   public void addOptionsMouseListener(MouseListener up, MouseListener down, MouseListener release) {
     this.upArrow.addMouseListener(up);
     this.downArrow.addMouseListener(down);
     this.releaseIcon.addMouseListener(release);
   }
 
   
   protected void paintComponent(Graphics g) {
     g.setColor(getBackground());
     g.fillRect(0, 0, getWidth(), getHeight());
     super.paintComponent(g);
   }
 }