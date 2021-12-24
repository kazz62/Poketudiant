package vue;
 
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import modele.PersonnageModele;
import principal.Poketudiant;
import principal.Ressources;
 
public class EquipeVue extends JPanel implements Observer{

  private static final Color BACKGROUND = new Color(51, 51, 51, 0); 
  private ArrayList<PoketudiantIcon> equipeIcons;
 
  public EquipeVue() {
    this.equipeIcons = new ArrayList<>();
    init();
  }
   
  private void init() {
    setLayout(new BoxLayout(this, 1));
    setBackground(BACKGROUND);
    for (int i = 0; i < 3; i++){
      this.equipeIcons.add(new PoketudiantIcon(i));
    }
    setOpaque(false);
  }
   
  public ArrayList<PoketudiantIcon> getEquipeIcons() {
    return this.equipeIcons;
  }
 
  public void update(Observable arg0, Object arg1) {
    if (arg1 instanceof PersonnageModele) {  
      PersonnageModele pm = (PersonnageModele)arg1;
      Poketudiant[] equipe = pm.getEquipe();
      removeAll();   
    for (int i = 0; i < pm.getNbPoketudiant(); i++) {
      ((PoketudiantIcon)this.equipeIcons.get(i)).update(equipe[i]);
      add(this.equipeIcons.get(i));
    } 
    setBounds(2 * Ressources.var, 2 * Ressources.var, 128, pm.getNbPoketudiant() * (((PoketudiantIcon)this.equipeIcons.get(0)).getPreferredSize()).height);
    validate();
    repaint();
    } 
   }
 
   
   protected void paintComponent(Graphics g) {
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(g);
   }
}