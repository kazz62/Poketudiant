 package vue;
 
 import controleur.GamesServerControleur;
 import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.LayoutManager;
 import java.util.ArrayList;
 import java.util.Observable;
 import java.util.Observer;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.border.Border;
 import javax.swing.plaf.ScrollBarUI;
 import modele.GamesServerModele;
 import principal.MyScrollBarUI;
 import principal.Ressources;
 
 public class GamesServerVue
   extends JPanel
   implements Observer
 {
   private static final long serialVersionUID = 1L;
   private GamesServerModele gamesServerModele;
   private ArrayList<BoutonMenu> listGame;
   private JPanel gamesList;
   private BoutonMenu boutonActualiser;
   private JScrollPane scrollPane;
   private BoutonMenu boutonCreateGame;
   private CreateGameVue createGameVue;
   private JLabel lblNoGame;
   private int var;
   
   public GamesServerVue(GamesServerModele gamesServerModele) {
    this.gamesServerModele = gamesServerModele;
    this.listGame = new ArrayList<>();
    setOpaque(true);
    setLayout((LayoutManager)null);
    setBackground(new Color(0, 64, 128));
    this.var = Ressources.var;
    setBounds(0, 0, 256 * this.var, 192 * this.var);
    init();
   }
 
 
   
   private void init() {
    this.boutonCreateGame = new BoutonMenu(Ressources.boutonCreateGame, "Create game");
    this.boutonCreateGame.setBounds(238 * this.var / 2, 149 * this.var, Ressources.boutonActualiser.getWidth() * this.var, Ressources.boutonActualiser.getHeight() * this.var);
    JLabel name = new JLabel("Creer partie");
    name.setFont(new Font("Arial", 0, 7 * this.var));
    name.setBounds(25 * this.var, 5 * this.var, 50 * this.var, 9 * this.var);
    name.setForeground(Color.DARK_GRAY);
    this.boutonCreateGame.add(name);
    add(this.boutonCreateGame);
    this.boutonActualiser = new BoutonMenu(Ressources.boutonActualiser, "Actualiser");
    this.boutonActualiser.setBounds(38 * this.var / 2, 149 * this.var, Ressources.boutonActualiser.getWidth() * this.var, Ressources.boutonActualiser.getHeight() * this.var);
    add(this.boutonActualiser);
    this.boutonActualiser.setOpaque(false);
    JLabel text = new JLabel("Actualiser");
    text.setFont(new Font("Arial", 0, 7 * this.var));
    text.setBounds(25 * this.var, 5 * this.var, 250 * this.var, 9 * this.var);
    text.setForeground(Color.DARK_GRAY);
    this.boutonActualiser.add(text);
    this.gamesList = new JPanel();
    this.gamesList.setOpaque(true);
    this.gamesList.setLayout((LayoutManager)null);
    this.gamesList.setMinimumSize(new Dimension(150 * this.var, 500 * this.var));
    this.gamesList.setBackground(Color.white);
    setOpaque(true);
    this.createGameVue = new CreateGameVue();
    this.createGameVue.setBounds(0, 0, 218 * this.var, 125 * this.var);
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(38 * this.var / 2, 23 * this.var, 218 * this.var, 125 * this.var);
    this.scrollPane.setBackground(Color.WHITE);
    this.scrollPane.setBorder((Border)null);
    this.scrollPane.getVerticalScrollBar().setUnitIncrement(10);
    this.scrollPane.setOpaque(true);
    this.scrollPane.setViewportView(this.gamesList);
    this.scrollPane.getVerticalScrollBar().setUI((ScrollBarUI)new MyScrollBarUI());
    this.scrollPane.getVerticalScrollBar().setBackground(new Color(0.0F, 0.0F, 0.0F, 0.0F));
    this.scrollPane.getVerticalScrollBar().setOpaque(false);
    this.scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(22 * this.var, this.scrollPane.getHeight()));
    this.lblNoGame = new JLabel("Aucune partie");
    this.lblNoGame.setFont(text.getFont());
    this.lblNoGame.setForeground(Color.DARK_GRAY);
    this.lblNoGame.setBackground(Color.white);
    this.lblNoGame.setOpaque(true);
    this.lblNoGame.setVisible(false);
    this.lblNoGame.setHorizontalAlignment(0);
    this.lblNoGame.setHorizontalTextPosition(0);
    this.lblNoGame.setBounds(383, 294, 250, 100);
    add(this.lblNoGame);
    add(this.scrollPane);
   }
 
   
   public void actualiserUnlock() {
    if (this.listGame.isEmpty())
     {
      this.lblNoGame.setVisible(true);
     }
    this.boutonActualiser.setBackground(Ressources.boutonActualiser);
    this.boutonActualiser.getComponent(0).setForeground(Color.DARK_GRAY);
    this.boutonActualiser.update();
   }
 
   
   public void actualiserLock() {
    this.lblNoGame.setVisible(false);
    this.listGame.clear();
    this.gamesList.removeAll();
    this.boutonActualiser.setBackground(Ressources.boutonActualiserLock);
    this.boutonActualiser.getComponent(0).setForeground(Color.GRAY);
    this.boutonActualiser.update();
   }
 
   
   public void createGameLock() {
    this.lblNoGame.setVisible(false);
    this.boutonActualiser.setBackground(Ressources.boutonActualiserLock);
    this.boutonActualiser.getComponent(0).setForeground(Color.GRAY);
    this.boutonActualiser.update();
    this.boutonCreateGame.setBackground(Ressources.boutonCreateGameLock);
    this.boutonCreateGame.getComponent(0).setForeground(Color.GRAY);
    this.boutonCreateGame.update();
   }
 
   
   public void createGameUnlock() {
    if (this.listGame.isEmpty())
     {
      this.lblNoGame.setVisible(true);
     }
    this.boutonActualiser.setBackground(Ressources.boutonActualiser);
    this.boutonActualiser.getComponent(0).setForeground(Color.DARK_GRAY);
    this.boutonActualiser.update();
    this.boutonCreateGame.setBackground(Ressources.boutonCreateGame);
    this.boutonCreateGame.getComponent(0).setForeground(Color.DARK_GRAY);
    this.boutonCreateGame.update();
    validate();
    repaint();
   }
 
   
   public void addGame() {
    BoutonMenu boutonGame = new BoutonMenu(Ressources.boutonBleu, "Game " + this.gamesServerModele.getLastGame().substring(2));
    this.listGame.add(boutonGame);
    boutonGame.setBounds(0, Ressources.boutonBleu.getHeight() * this.var * (this.listGame.size() - 1), Ressources.boutonBleu.getWidth() * this.var, Ressources.boutonBleu.getHeight() * this.var);
    this.gamesList.add(boutonGame);
    this.gamesList.setPreferredSize(new Dimension(150 * this.var, this.listGame.size() * Ressources.boutonBleu.getHeight() * this.var));
    JLabel text = new JLabel("Serveur");
    JLabel name = new JLabel(String.valueOf(this.gamesServerModele.getLastGame().substring(2)) + "   " + this.gamesServerModele.getLastGame().split(" ")[0] + "/4");
    text.setFont(new Font("Arial", 0, 9 * this.var));
    name.setFont(new Font("Arial", 0, 9 * this.var));
    text.setBounds(15 * this.var, 16 * this.var, 250 * this.var, 8 * this.var);
    name.setBounds(100 * this.var, 16 * this.var, 250 * this.var, 8 * this.var);
    text.setForeground(Color.WHITE);
    name.setForeground(Color.BLACK);
    boutonGame.add(text);
    boutonGame.add(name);
    boutonGame.addMouseListener(GamesServerControleur.mouseListener);
   }
 
 
   
   public void createGame() {
    this.createGameVue.resetSaisie();
    this.scrollPane.setViewportView(this.createGameVue);
    validate();
    repaint();
   }
 
   
   public void quitCreateGame() {
    this.scrollPane.setViewportView(this.gamesList);
    validate();
    repaint();
   }
 
 
   
   public void update(Observable arg0, Object arg1) {
    System.out.println(arg1.toString());
    if (arg1 != null)
     {
      if (arg1.equals("new game")) {
         
       addGame();
       }
     else if (arg1.equals("actualisation")) {
         
        this.gamesServerModele.setActualisation(true);
        actualiserUnlock();
       } 
     }
     
    validate();
    repaint();
   }
 
 
   
   protected void paintComponent(Graphics g) {
     super.paintComponent(g);
   }
 
   
   public BoutonMenu getBoutonActualiser() {
    return this.boutonActualiser;
   }
 
   
   public BoutonMenu getBoutonNewGame() {
    return this.boutonCreateGame;
   }
 
   
   public CreateGameVue getCreateGameVue() {
    return this.createGameVue;
   }
 }