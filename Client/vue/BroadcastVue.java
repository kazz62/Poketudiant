 package vue;
 
 import controleur.BroadcastControleur;
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
 import javax.swing.UIManager;
 import javax.swing.border.Border;
 import javax.swing.plaf.ScrollBarUI;
 import modele.BroadcastModele;
 import principal.MyScrollBarUI;
 import principal.Ressources;
 
 public class BroadcastVue
   extends JPanel
   implements Observer
 {
   private static final long serialVersionUID = 1L;
   private BroadcastModele broadcastModele;
   private ArrayList<BoutonMenu> listServer;
   private JPanel serversList;
   private BoutonMenu boutonActualiser;
   private JScrollPane scrollPane;
   private int var;
   private JLabel lblNoServer;
   
   public BroadcastVue(BroadcastModele broadcastModele) {
    this.broadcastModele = broadcastModele;
    this.listServer = new ArrayList<>();
    setOpaque(true);
    setLayout((LayoutManager)null);
    setBackground(new Color(0, 64, 128));
    this.var = Ressources.var;
    setBounds(0, 0, 256 * this.var, 192 * this.var);
    init();
   }
   
   private void init() {
    this.boutonActualiser = new BoutonMenu(Ressources.boutonActualiser, "Actualiser");
    this.boutonActualiser.setBounds(38 * this.var / 2, 149 * this.var, Ressources.boutonActualiser.getWidth() * this.var, Ressources.boutonActualiser.getHeight() * this.var);
    add(this.boutonActualiser);
    this.boutonActualiser.setOpaque(false);
    JLabel text = new JLabel("Actualiser");
    text.setFont(new Font("Arial", 0, 7 * this.var));
    text.setBounds(25 * this.var, 5 * this.var, 250 * this.var, 8 * this.var);
    text.setForeground(Color.DARK_GRAY);
    this.boutonActualiser.add(text);
    this.serversList = new JPanel();
    this.serversList.setOpaque(true);
    this.serversList.setLayout((LayoutManager)null);
    this.serversList.setMinimumSize(new Dimension(150 * this.var, 500 * this.var));
    this.serversList.setBackground(new Color(248, 248, 248));
    setOpaque(true);
    UIManager.put("ScrollBar.minimumThumbSize", new Dimension(22 * this.var, 12 * this.var));
    UIManager.put("ScrollBar.maximumThumbSize", new Dimension(22 * this.var, 22 * this.var));
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(38 * this.var / 2, 23 * this.var, 218 * this.var, 125 * this.var);
    this.scrollPane.setBackground(new Color(248, 248, 248));
    this.scrollPane.setBorder((Border)null);
    this.scrollPane.getVerticalScrollBar().setUnitIncrement(10);
    this.scrollPane.setOpaque(true);
    this.scrollPane.setViewportView(this.serversList);
    this.scrollPane.getVerticalScrollBar().setUI((ScrollBarUI)new MyScrollBarUI());
    this.scrollPane.getVerticalScrollBar().setBackground(new Color(0.0F, 0.0F, 0.0F, 0.0F));
    this.scrollPane.getVerticalScrollBar().setOpaque(false);
    this.scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(22 * this.var, this.scrollPane.getHeight()));
    this.lblNoServer = new JLabel("Aucun serveur");
    this.lblNoServer.setFont(text.getFont());
    this.lblNoServer.setForeground(Color.black);
    this.lblNoServer.setBackground(new Color(248, 248, 248));
    this.lblNoServer.setOpaque(true);
    this.lblNoServer.setVisible(false);
    this.lblNoServer.setHorizontalAlignment(0);
    this.lblNoServer.setHorizontalTextPosition(0);
    this.lblNoServer.setBounds(383, 294, 250, 100);
    add(this.lblNoServer);
    add(this.scrollPane);
   }
 
   
   public void actualiserUnlock() {
    if (this.listServer.isEmpty())
     {
      this.lblNoServer.setVisible(true);
     }
    this.boutonActualiser.setBackground(Ressources.boutonActualiser);
    this.boutonActualiser.getComponent(0).setForeground(Color.DARK_GRAY);
    this.boutonActualiser.update();
   }
 
   
   public void actualiserLock() {
    this.lblNoServer.setVisible(false);
    this.listServer.clear();
    this.serversList.removeAll();
    this.boutonActualiser.setBackground(Ressources.boutonActualiserLock);
    this.boutonActualiser.getComponent(0).setForeground(Color.GRAY);
    this.boutonActualiser.update();
    this.scrollPane.setViewportView(this.serversList);
   }
   
   private void addServer() {
    BoutonMenu boutonServer = new BoutonMenu(Ressources.boutonBleu, "Server " + this.broadcastModele.getLastPacket().getAddress().getHostAddress());
    this.listServer.add(boutonServer);
    boutonServer.setBounds(0, Ressources.boutonBleu.getHeight() * this.var * (this.listServer.size() - 1), Ressources.boutonBleu.getWidth() * this.var, Ressources.boutonBleu.getHeight() * this.var);
    this.serversList.add(boutonServer);
    this.serversList.setPreferredSize(new Dimension(150 * this.var, this.listServer.size() * Ressources.boutonBleu.getHeight() * this.var));
    JLabel text = new JLabel("Serveur");
    JLabel name = new JLabel(this.broadcastModele.getLastPacket().getAddress().getHostAddress());
    text.setFont(new Font("Arial", 0, 9 * this.var));
    name.setFont(new Font("Arial", 0, 9 * this.var));
    text.setBounds(15 * this.var, 16 * this.var, 250 * this.var, 8 * this.var);
    name.setBounds(100 * this.var, 16 * this.var, 250 * this.var, 8 * this.var);
    text.setForeground(Color.WHITE);
    name.setForeground(Color.BLACK);
    boutonServer.add(text);
    boutonServer.add(name);
    boutonServer.addMouseListener(BroadcastControleur.mouseListener);
   }
   
   public BroadcastModele getBroadcastModele() {
    return this.broadcastModele;
   }
 
 
   
   protected void paintComponent(Graphics g) {
    super.paintComponent(g);
   }
   
   public void update(Observable arg0, Object arg1) {
     if (arg1 != null) {
       
       if (arg1.equals("new server"))
       {
         addServer();
       }
       if (arg1.equals("actualisation")) {
         
        this.broadcastModele.setActualisation(true);
         actualiserUnlock();
       } 
     } 
     
     validate();
     repaint();
   }
   
   public BoutonMenu getBoutonActualiser() {
     return this.boutonActualiser;
   }
   
   public ArrayList<BoutonMenu> getListServer() {
    return this.listServer;
   }
 }
