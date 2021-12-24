 package vue;
 
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.LayoutManager;
 import java.awt.geom.Rectangle2D;
 import javax.swing.BorderFactory;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JTextField;
 import principal.Ressources;
 
 
 
 
 
 
 
 
 public class CreateGameVue
   extends JPanel
 {
   private static final long serialVersionUID = 1L;
   private JTextField saisie;
   private int var;
   private BoutonMenu boutonValider;
   private BoutonMenu boutonAnnuler;
   
   public CreateGameVue() {
    this.var = Ressources.var;
    init();
   }
 
   
   private void init() {
    setOpaque(false);
    setLayout((LayoutManager)null);
 
    JLabel titre = new JLabel("Nouvelle partie");
    titre.setFont(new Font("Arial", 0, 10 * this.var));
    titre.setForeground(new Color(1.0F, 1.0F, 1.0F, 0.9F));
    titre.setBounds(118 * this.var / 2, 113 * this.var / 2 - 38 * this.var, 100 * this.var, 12 * this.var);
    titre.setHorizontalAlignment(0);
    titre.setHorizontalTextPosition(0);
 
    JLabel name = new JLabel("Nom  :");
    name.setFont(new Font("Arial", 0, 7 * this.var));
    name.setForeground(new Color(1.0F, 1.0F, 1.0F, 0.9F));
    name.setBounds(103 * this.var / 2, 113 * this.var / 2, 50 * this.var, 12 * this.var);
 
    this.saisie = new JTextField()
       {
         private static final long serialVersionUID = 1L;
         
         protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2d = (Graphics2D)g;
          g2d.setColor(new Color(1.0F, 1.0F, 1.0F, 0.1F));
          g2d.fill(new Rectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight()));
         }
       };
    this.saisie.setName("NameGame");
    this.saisie.setFont(new Font("Arial", 0, 7 * this.var));
    this.saisie.setOpaque(false);
    this.saisie.setForeground(new Color(1.0F, 1.0F, 1.0F, 0.75F));
    this.saisie.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
    this.saisie.setBounds(103 * this.var / 2 + 40 * this.var, 113 * this.var / 2, 75 * this.var, 12 * this.var);
    this.saisie.setCaretColor(Color.white);
    this.boutonValider = new BoutonMenu(Ressources.boutonValider, "Valider");
    this.boutonValider.setBounds(48 * this.var, 88 * this.var, Ressources.boutonValider.getWidth() * this.var, Ressources.boutonValider.getHeight() * this.var);
    add(this.boutonValider);
    this.boutonValider.setOpaque(false);
    JLabel textVal = new JLabel("Valider");
    textVal.setFont(new Font("Arial", 0, 6 * this.var));
    textVal.setBounds((this.boutonValider.getWidth() - 25 * this.var) / 2, (this.boutonValider.getHeight() - 9 * this.var) / 2, 25 * this.var, 8 * this.var);
    textVal.setForeground(Color.DARK_GRAY);
    this.boutonValider.add(textVal);
    this.boutonAnnuler = new BoutonMenu(Ressources.boutonAnnuler, "Annuler");
    this.boutonAnnuler.setBounds(115 * this.var, 88 * this.var, Ressources.boutonAnnuler.getWidth() * this.var, Ressources.boutonAnnuler.getHeight() * this.var);
    add(this.boutonAnnuler);
    this.boutonAnnuler.setOpaque(false);
    JLabel text = new JLabel("Annuler");
    text.setFont(new Font("Arial", 0, 6 * this.var));
    text.setBounds((this.boutonAnnuler.getWidth() - 25 * this.var) / 2, (this.boutonAnnuler.getHeight() - 9 * this.var) / 2, 25 * this.var, 8 * this.var);
    text.setForeground(Color.DARK_GRAY);
    this.boutonAnnuler.add(text);     
    add(this.boutonValider);
    add(titre);
    add(name);
    add(this.saisie);
   }
 
 
 
   
   protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     
    Graphics2D g2d = (Graphics2D)g;
     
    g2d.setColor(new Color(0.2F, 0.2F, 0.2F, 0.95F));
     
     g2d.fill(new Rectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight()));
   }
 
 
 
   
   public void resetSaisie() {
    this.saisie.setText("");
   }
 
   
   public String getTextSaisie() {
    return this.saisie.getText();
   }
   
   public BoutonMenu getBoutonAnnuler() {
    return this.boutonAnnuler;
   }
   
   public BoutonMenu getBoutonValider() {
    return this.boutonValider;
   }
}