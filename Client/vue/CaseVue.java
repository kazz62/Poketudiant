 package vue;
 
 import java.awt.Color;
 import java.awt.Graphics;
 import java.util.Observable;
 import java.util.Observer;
 import javax.swing.JPanel;
 import modele.CaseModele;
 import principal.Ressources;
 import principal.TypeCase;
 
 
 
 public class CaseVue extends JPanel implements Observer
 {
   private static final long serialVersionUID = 1L;
   private int var;
   private CaseModele caseModele;
   private int width;
   private int height;
   
   public CaseVue(CaseModele caseModele) {
    setOpaque(false);
    this.caseModele = caseModele;
    setBackground(Color.BLUE);
    this.var = Ressources.var;
    this.width = caseModele.getType().getWidth();
    this.height = caseModele.getType().getHeight();
     
    setBounds(caseModele.getX() * this.width * this.var, caseModele.getY() * this.height * this.var, this.width * this.var, this.height * this.var);
   }
 
 
   
   public CaseVue(CaseModele caseModele, int x, int y) {
    setOpaque(false);
    this.caseModele = caseModele;
    setBackground(Color.BLUE);
    this.var = Ressources.var;
    this.width = caseModele.getType().getWidth();
    this.height = caseModele.getType().getHeight();
    setBounds(x * this.var, y * this.var, this.width * this.var, this.height * this.var);
   }
 
 
 
   
   public void paintComponent(Graphics g) {
    if (this.caseModele.getType() != TypeCase.ARBRE1 && this.caseModele.getType() != TypeCase.CLOTURES && this.caseModele.getType() != TypeCase.FLEUR && this.caseModele.getType() != TypeCase.ARBRE2 && 
      this.caseModele.getType() != TypeCase.LAC_CORNOR && this.caseModele.getType() != TypeCase.LAC_BORDERLEFT && this.caseModele.getType() != TypeCase.LAC_BORDERTOP) {
      if (this.caseModele.getType() != TypeCase.LAC) {
         g.drawImage(Ressources.sol, 0, 0, 16 * this.var, 16 * this.var, this.caseModele.getX() % 4 * 16, this.caseModele.getY() % 4 * 16, this.caseModele.getX() % 4 * 16 + 16, this.caseModele.getY() % 4 * 16 + 16, this);
        if (this.caseModele.getType() != TypeCase.NEUTRE) g.drawImage(this.caseModele.getType().getBackground(), 0, 0, this.width * this.var, this.height * this.var, this);
       
       } else {
        g.drawImage(Ressources.lac, 0, 0, 16 * this.var, 16 * this.var, this.caseModele.getX() % 4 * 16, this.caseModele.getY() % 4 * 16, this.caseModele.getX() % 4 * 16 + 16, this.caseModele.getY() % 4 * 16 + 16, this);
       }
     
     } else {
      g.drawImage(this.caseModele.getType().getBackground(), 0, 0, this.width * this.var, this.height * this.var, this);
     } 
   }
   
   public void update(Observable o, Object arg) {}
 }