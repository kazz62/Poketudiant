 package vue;
 
 import java.awt.Color;
 import java.awt.Graphics;
 import java.awt.LayoutManager;
 import javax.swing.JPanel;
 import modele.GameModele;
 import principal.Ressources;
 
 
 
 public class MapVue
   extends JPanel
 {
   private static final long serialVersionUID = 1L;
   private GrilleVue grilleVue;
   private GameModele gameModele;
   private TchatVue tchatVue;
   private QuitterGamePanel quitterVue;
   private EquipeVue equipeVue;
   private int var;
   
   public MapVue(GameModele gameModele, TchatVue tchatVue) {
    this.gameModele = gameModele;
    this.tchatVue = tchatVue;
    this.var = Ressources.var;
    setOpaque(true);
    setLayout((LayoutManager)null);
    setBackground(Color.BLACK);
    setBounds(0, 0, 256 * this.var, 192 * this.var);
    init();
   }
 
   
   public void init() {
    this.grilleVue = new GrilleVue(this.gameModele);
    this.gameModele.addObserver(this.grilleVue); 
    this.tchatVue.setBounds(2 * this.var, getHeight() - 52 * this.var, 75 * this.var, 50 * this.var);
    this.quitterVue = new QuitterGamePanel();
    this.quitterVue.setBounds(getWidth() / 2 - 250, getHeight() / 2 - 50, 500, 100); 
    this.equipeVue = new EquipeVue();
    this.equipeVue.setBounds(0, 0, 0, 0);
    this.gameModele.addObserver(this.equipeVue); 
    add(this.equipeVue);
    add(this.quitterVue);
    add(this.tchatVue);
    add(this.grilleVue);
    setFocusable(true);
   }
 
 
   
   public GrilleVue getGrilleVue() {
    return this.grilleVue;
   }
   
   public QuitterGamePanel getQuitterVue() {
    return this.quitterVue;
   }
   
   public EquipeVue getEquipeVue() {
    return this.equipeVue;
   }
 
   
   public void update() {
    validate();
    repaint();
   }
 
   
   public void paint(Graphics g) {
    super.paint(g);
    g.drawImage(this.gameModele.getSpriteNeige(), 0, 0, 256 * this.var, 192 * this.var, this);
   }
 
 
 
   
   protected void paintComponent(Graphics g) {
    super.paintComponent(g);
   }
 }
