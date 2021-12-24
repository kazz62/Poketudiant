package vue;
 
import controleur.BroadcastControleur;
import controleur.CombatControleur;
import controleur.GamesServerControleur;
import controleur.TchatControleur;
import controleur.mapControleur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modele.BroadcastModele;
import modele.CombatModele;
import modele.GameModele;
import modele.GamesServerModele;
import modele.TchatModele;
import principal.Main;
import principal.Ressources;
  
public class FenetreVue extends JFrame implements Observer{
  private static final long serialVersionUID = 1L;
  private JPanel jpanel_ContentPane;
  private CombatModele combatModele;
  private CombatVue combatVue;
  private CombatControleur combatControleur;
  private BroadcastModele broadcastModele;
  private BroadcastVue broadcastVue;
  private BroadcastControleur broadcastControleur;
  private GamesServerModele gamesServerModele;
  private GamesServerVue gamesServerVue;
  private GamesServerControleur gamesServerControleur;
  private GameModele gameModele;
  private MapVue mapVue;
  private mapControleur mapControleur;
  private TchatModele tchatModele;
  private TchatVue tchatVue;
  private TchatControleur tchatControleur;
   
  public void init() {
    setTitle("Pokétudiant");
    setResizable(false);
    setDefaultCloseOperation(3);
    this.jpanel_ContentPane = new JPanel();
    setContentPane(this.jpanel_ContentPane);
    this.jpanel_ContentPane.setBackground(Color.BLACK);
    this.jpanel_ContentPane.setLayout((LayoutManager)null);
    addWindowListener(new WindowListener(){
      public void windowOpened(WindowEvent e) {}     
      public void windowIconified(WindowEvent e) {}
      public void windowDeiconified(WindowEvent e) {}
      public void windowDeactivated(WindowEvent e) {}
      public void windowClosing(WindowEvent e) {
      Main.socket.close();
      }
      public void windowClosed(WindowEvent e) {}
      public void windowActivated(WindowEvent e) {}
    });
    this.tchatModele = new TchatModele();
    this.tchatVue = new TchatVue();
    this.tchatControleur = new TchatControleur(this.tchatModele, this.tchatVue); 
    setScreen(); 
    setVisible(true);
    startBroadcast();
  }
 
  private void setScreen() {
    if (Ressources.fullscreen) {
      setPreferredSize(new Dimension(Ressources.resolutionWidth, Ressources.resolutionHeight));
      setSize(new Dimension(Ressources.resolutionWidth, Ressources.resolutionHeight));
      dispose();
      setUndecorated(true);
      setExtendedState(6);
      setVisible(true);
      setLocation(0, 0);
    }else{      
      dispose();
      setUndecorated(false);
      setExtendedState(0); 
      if (System.getProperty("os.name").contains("Linux")) {      
        System.out.println("SOUS LINUX");
        setPreferredSize(new Dimension(256 * Ressources.var, 192 * Ressources.var + 23));
        setSize(new Dimension(256 * Ressources.var, 192 * Ressources.var + 23));
      } 
      setVisible(true);
      setLocation((Ressources.resolutionWidth - 256 * Ressources.var) / 2, (Ressources.resolutionHeight - 192 * Ressources.var) / 2);
      if (System.getProperty("os.name").contains("Windows")) {
        System.out.println("SOUS WINDOWS");
        setPreferredSize(new Dimension(256 * Ressources.var + (getInsets()).left + (getInsets()).right, 192 * Ressources.var + (getInsets()).top + (getInsets()).bottom));
        setSize(new Dimension(256 * Ressources.var + (getInsets()).left + (getInsets()).right, 192 * Ressources.var + (getInsets()).top + (getInsets()).bottom));
      } 
    } 
  }
 
   
  private void startGame() {
    this.gameModele = new GameModele(this.tchatModele);
    this.gameModele.addObserver(this);
    this.mapVue = new MapVue(this.gameModele, this.tchatVue);
    this.mapControleur = new mapControleur(this.mapVue, this.gameModele, this.tchatControleur);
    this.jpanel_ContentPane.removeAll();
    this.jpanel_ContentPane.add(this.mapVue);
    this.mapVue.requestFocusInWindow();
    validate();
    repaint();
   }
 
   
  private void startBroadcast() {
    this.jpanel_ContentPane.removeAll();
    this.broadcastModele = new BroadcastModele();
    this.broadcastVue = new BroadcastVue(this.broadcastModele);
    this.broadcastModele.addObserver(this.broadcastVue);
    this.broadcastModele.addObserver(this);
    this.broadcastControleur = new BroadcastControleur(this.broadcastVue, this.broadcastModele);
    this.jpanel_ContentPane.add(this.broadcastVue);
    validate();
    repaint();
  }
 
   
  private void startGamesServer(String adresse) {
    this.jpanel_ContentPane.removeAll();
    this.gamesServerModele = new GamesServerModele(adresse);
    this.gamesServerVue = new GamesServerVue(this.gamesServerModele);
    this.gamesServerModele.addObserver(this.gamesServerVue);
    this.gamesServerModele.addObserver(this);
    this.gamesServerControleur = new GamesServerControleur(this.gamesServerVue, this.gamesServerModele);
    this.jpanel_ContentPane.add(this.gamesServerVue);
    validate();
    repaint();
  }
 
   
  private void startCombat(String typeCombat) {
    this.jpanel_ContentPane.removeAll();
    this.combatModele = new CombatModele(this.gameModele);
    this.combatVue = new CombatVue(this.combatModele, this.tchatVue, typeCombat);
    this.combatControleur = new CombatControleur(this.combatModele, this.combatVue, this.tchatControleur);
    this.jpanel_ContentPane.add(this.combatVue);
    this.combatModele.addObserver(this.combatVue);
    this.combatModele.addObserver(this);
    this.combatVue.requestFocusInWindow();
    validate();
    repaint();
  }
 
 
   
  public void update(Observable arg0, Object arg1) {
    if (arg1 != null && arg1 instanceof String) {
      String message = arg1.toString();
      if (message.length() >= 12 && message.substring(0, 11).equals("GamesServer")) {
        startGamesServer(message.substring(12));
      }else if (message.equals("join game")) {
        startGame();
      }else if (message.equals("new wild")) {
        startCombat("wild");
      }else if (message.equals("new rival")) {
        startCombat("rival");
      }else if (message.equals("menu broadcast")) {
        startBroadcast();
      } 
    } 
  }
}