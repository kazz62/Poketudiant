 package vue;
 
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.LayoutManager;
 import java.util.Observable;
 import java.util.Observer;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import modele.CombatModele;
 import principal.Ressources;
 
 
 
 
 public class CombatVue
   extends JPanel
   implements Observer
 {
   private static final long serialVersionUID = 1L;
   private CombatModele combatModele;
   private TchatVue tchatVue;
   private BoutonMenu boutonEquipe;
   private BoutonMenu boutonFight;
   private BoutonMenu boutonCapture;
   private BoutonMenu boutonRun;
   private BoutonMenu boutonAttaque1;
   private BoutonMenu boutonAttaque2;
   private BoutonMenu boutonPoketudiant1;
   private JLabel nomPoketudiant1;
   private JLabel hpPoketudiant1;
   private BoutonMenu boutonPoketudiant2;
   private JLabel nomPoketudiant2;
   private JLabel hpPoketudiant2;
   private BoutonMenu boutonCancel;
   private BoutonMenu[] iconsMyEquipe;
   private BoutonMenu[] iconsEquipeAdversaire;
   private int indexIconsEquipeAdversaire;
   private JLabel desciption;
   private int var;
   private BoutonMenu uiRival;
   private BoutonMenu uiJoueur0;
   private BoutonMenu uiCombat;
   private Texture poketudiantRival;
   private Texture poketudiantJoueur0;
   private BoutonMenu typeCurrentJoueur0;
   private BoutonMenu typeCurrentRival;
   private JLabel varietyCurrentJoueur0;
   private JLabel varietyCurrentRival;
   private JLabel lvlCurrentJoueur0;
   private JLabel lvlCurrentRival;
   private JPanel hpPerCentJoueur0;
   private JPanel hpPerCentRival;
   private JLabel hpJoueur0;
   
   public CombatVue(CombatModele combatModele, TchatVue tchatVue, String type) {
    this.combatModele = combatModele;
    this.indexIconsEquipeAdversaire = -3;
    setLayout((LayoutManager)null);
    this.var = Ressources.var;
    setBounds(0, 0, 256 * this.var, 192 * this.var);
    this.tchatVue = tchatVue;
    init(type);
   }
 
 
   
   private void init(String type) {
    this.iconsMyEquipe = new BoutonMenu[3];
    this.iconsEquipeAdversaire = new BoutonMenu[3];
     
    this.tchatVue.setBounds(1 * this.var, 92 * this.var, 75 * this.var, 50 * this.var);
     int i;
    for (i = 0; i < 3; i++) {
       
      this.iconsMyEquipe[i] = new BoutonMenu(Ressources.noIconMyPoke, (new StringBuilder(String.valueOf(i))).toString());
      this.iconsMyEquipe[i].setBounds((3 + 10 * i) * this.var, 146 * this.var, 9 * this.var, 11 * this.var);
      add(this.iconsMyEquipe[i]);
     } 
    for (i = 0; i < 3; i++) {
       
      this.iconsEquipeAdversaire[i] = new BoutonMenu(Ressources.noIconPokeRival, (new StringBuilder(String.valueOf(i))).toString());
      this.iconsEquipeAdversaire[i].setBounds((246 - 8 * i) * this.var, 146 * this.var, 7 * this.var, 8 * this.var);
      add(this.iconsEquipeAdversaire[i]);
     } 
    this.boutonEquipe = new BoutonMenu(Ressources.boutonEquipe, "boutonEquipe");
    this.boutonFight = new BoutonMenu(Ressources.boutonFight, "boutonFight");
    if (type.equals("wild")) {
       
      this.boutonEquipe.setBounds(3 * this.var, 161 * this.var, 44 * this.var, 26 * this.var);
      this.boutonFight.setBounds(50 * this.var, 161 * this.var, 105 * this.var, 26 * this.var);
     }
     else {
       
      this.boutonEquipe.setBounds(52 * this.var, 161 * this.var, 44 * this.var, 26 * this.var);
      this.boutonFight.setBounds(99 * this.var, 161 * this.var, 105 * this.var, 26 * this.var);
     } 
    this.boutonCapture = new BoutonMenu(Ressources.boutonCapture, "boutonCapture");
    this.boutonCapture.setBounds(158 * this.var, 161 * this.var, 48 * this.var, 26 * this.var);
    this.boutonRun = new BoutonMenu(Ressources.boutonRun, "boutonRun");
    this.boutonRun.setBounds(209 * this.var, 161 * this.var, 44 * this.var, 26 * this.var);
    this.boutonAttaque1 = new BoutonMenu(Ressources.neutre, "attaque1");
    this.boutonAttaque1.setBounds(3 * this.var, 161 * this.var, 94 * this.var, 26 * this.var);
    this.boutonAttaque2 = new BoutonMenu(Ressources.neutre, "attaque2");
    this.boutonAttaque2.setBounds(107 * this.var, 161 * this.var, 94 * this.var, 26 * this.var);
    this.boutonCancel = new BoutonMenu(Ressources.boutonCancel, "cancel");
    this.boutonCancel.setBounds(212 * this.var, 169 * this.var, 41 * this.var, 18 * this.var);
    this.desciption = new JLabel();
    this.desciption.setFont(new Font("Arial", 0, 6 * this.var));
    this.desciption.setBounds(50 * this.var, 148 * this.var, 156 * this.var, 10 * this.var);
    this.desciption.setHorizontalAlignment(0);
    this.typeCurrentJoueur0 = new BoutonMenu(Ressources.neutre, "typeCurrentJoueur0");
    this.typeCurrentJoueur0.setBounds(153 * this.var, 116 * this.var, 21 * this.var, 6 * this.var);
    this.typeCurrentRival = new BoutonMenu(Ressources.neutre, "typeCurrentRival");
    this.typeCurrentRival.setBounds(2 * this.var, 40 * this.var, 21 * this.var, 6 * this.var);
    this.varietyCurrentJoueur0 = new JLabel();
    this.varietyCurrentJoueur0.setBounds(153 * this.var, 101 * this.var, 73 * this.var, 11 * this.var);
    this.varietyCurrentJoueur0.setFont(new Font("Pokemon X and Y", 0, 8 * this.var));
    this.varietyCurrentRival = new JLabel();
    this.varietyCurrentRival.setBounds(2 * this.var, 25 * this.var, 73 * this.var, 11 * this.var);
    this.varietyCurrentRival.setFont(new Font("Pokemon X and Y", 0, 8 * this.var));
    this.lvlCurrentJoueur0 = new JLabel();
    this.lvlCurrentJoueur0.setBounds(234 * this.var, 102 * this.var, 25 * this.var, 11 * this.var);
    this.lvlCurrentJoueur0.setFont(new Font("Pokemon X and Y", 1, 6 * this.var));
    this.lvlCurrentRival = new JLabel();
    this.lvlCurrentRival.setBounds(84 * this.var, 26 * this.var, 25 * this.var, 11 * this.var);
    this.lvlCurrentRival.setFont(new Font("Pokemon X and Y", 1, 6 * this.var));
    this.hpPerCentJoueur0 = new JPanel();
    this.hpPerCentJoueur0.setBounds(200 * this.var, 119 * this.var, 48 * this.var, 2 * this.var);
    this.hpPerCentRival = new JPanel();
    this.hpPerCentRival.setBounds(53 * this.var, 43 * this.var, 46 * this.var, 2 * this.var);
    this.hpJoueur0 = new JLabel();
    this.hpJoueur0.setBounds(199 * this.var, 122 * this.var, 50 * this.var, 11 * this.var);
    this.hpJoueur0.setFont(new Font("Pokemon X and Y", 1, 8 * this.var));
    this.boutonPoketudiant1 = new BoutonMenu(Ressources.backgroundNoPoketudiant, "No Poketudiant");
    this.nomPoketudiant1 = new JLabel();
    this.nomPoketudiant1.setBounds(6 * this.var, 6 * this.var, 82 * this.var, 8 * this.var);
    this.nomPoketudiant1.setHorizontalAlignment(0);
    this.nomPoketudiant1.setFont(new Font("Pokemon X and Y", 1, 6 * this.var));
    this.hpPoketudiant1 = new JLabel();
    this.hpPoketudiant1.setBounds(56 * this.var, 15 * this.var, 33 * this.var, 5 * this.var);
    this.hpPoketudiant1.setHorizontalAlignment(0);
    this.hpPoketudiant1.setFont(new Font("Pokemon X and Y", 1, 5 * this.var));
    this.boutonPoketudiant1.add(this.nomPoketudiant1);
    this.boutonPoketudiant1.add(this.hpPoketudiant1);
    this.boutonPoketudiant1.setBounds(3 * this.var, 161 * this.var, 94 * this.var, 26 * this.var);
    this.boutonPoketudiant2 = new BoutonMenu(Ressources.backgroundNoPoketudiant, "No Poketudiant");
    this.nomPoketudiant2 = new JLabel();
    this.nomPoketudiant2.setBounds(6 * this.var, 6 * this.var, 82 * this.var, 8 * this.var);
    this.nomPoketudiant2.setHorizontalAlignment(0);
    this.nomPoketudiant2.setFont(new Font("Pokemon X and Y", 1, 6 * this.var));
    this.hpPoketudiant2 = new JLabel();
    this.hpPoketudiant2.setBounds(56 * this.var, 15 * this.var, 33 * this.var, 5 * this.var);
    this.hpPoketudiant2.setHorizontalAlignment(0);
    this.hpPoketudiant2.setFont(new Font("Pokemon X and Y", 1, 5 * this.var));
    this.boutonPoketudiant2.add(this.nomPoketudiant2);
    this.boutonPoketudiant2.add(this.hpPoketudiant2);
    this.boutonPoketudiant2.setBounds(107 * this.var, 161 * this.var, 94 * this.var, 26 * this.var);    
    this.poketudiantRival = new Texture(Ressources.neutre);
    this.poketudiantRival.setBounds(170 * this.var, 22 * this.var, 44 * this.var, 70 * this.var);
    this.poketudiantJoueur0 = new Texture(Ressources.neutre);
    this.poketudiantJoueur0.setBounds(45 * this.var, 56 * this.var, 66 * this.var, 105 * this.var);
    this.uiRival = new BoutonMenu(Ressources.uiRival, "uiRival");
    this.uiRival.setBounds(0 * this.var, 21 * this.var, 110 * this.var, 31 * this.var);
    this.uiJoueur0 = new BoutonMenu(Ressources.uiJoueur0, "uiJoueur0");
    this.uiJoueur0.setBounds(146 * this.var, 96 * this.var, 110 * this.var, 40 * this.var);
    this.uiCombat = new BoutonMenu(Ressources.uiCombat, "uiCombat");
    this.uiCombat.setBounds(0 * this.var, 143 * this.var, 256 * this.var, 49 * this.var);
    add(this.tchatVue);
    add(this.boutonEquipe);
    add(this.boutonFight);
    if (type.equals("wild")) {
      add(this.boutonCapture);
      add(this.boutonRun);
     } 
    add(this.boutonAttaque1);
    add(this.boutonAttaque2);
    add(this.boutonPoketudiant1);
    add(this.boutonPoketudiant2);
    add(this.boutonCancel);
    add(this.desciption);
    add(this.typeCurrentJoueur0);
    add(this.typeCurrentRival);
    add(this.varietyCurrentJoueur0);
    add(this.varietyCurrentRival);
    add(this.lvlCurrentJoueur0);
    add(this.lvlCurrentRival);
    add(this.hpPerCentJoueur0);
    add(this.hpPerCentRival);
    add(this.hpJoueur0);
    add(this.uiRival);
    add(this.uiJoueur0);
    add(this.uiCombat);
    add(this.poketudiantRival);
    add(this.poketudiantJoueur0);
    setFocusable(true);
   }
 
   
   public void setFight() {
    this.boutonEquipe.setVisible(false);
    this.boutonFight.setVisible(false);
    this.boutonCapture.setVisible(false);
    this.boutonRun.setVisible(false);
    this.boutonPoketudiant1.setVisible(false);
    this.boutonPoketudiant2.setVisible(false);
    this.boutonAttaque1.setVisible(true);
    this.boutonAttaque2.setVisible(true);
    this.boutonCancel.setVisible(true);
   }
 
   
   public void setAction() {
    this.boutonAttaque1.setVisible(false);
    this.boutonAttaque2.setVisible(false);
    this.boutonPoketudiant1.setVisible(false);
    this.boutonPoketudiant2.setVisible(false);
    this.boutonCancel.setVisible(false);
     
    this.boutonEquipe.setVisible(true);
    this.boutonFight.setVisible(true);
    this.boutonCapture.setVisible(true);
    this.boutonRun.setVisible(true);
   }
 
   
   public void setEquipe() {
    this.boutonAttaque1.setVisible(false);
    this.boutonAttaque2.setVisible(false);
    this.boutonEquipe.setVisible(false);
    this.boutonFight.setVisible(false);
    this.boutonCapture.setVisible(false);
    this.boutonRun.setVisible(false);
    this.boutonPoketudiant1.setVisible(true);
    this.boutonPoketudiant2.setVisible(true);
    this.boutonCancel.setVisible(true);
   }
 
   
   public void setLock() {
    this.boutonEquipe.setName("Lock");
    this.boutonFight.setName("Lock");
    this.boutonCapture.setName("Lock");
    this.boutonRun.setName("Lock");
    this.boutonEquipe.setBackground(Ressources.boutonEquipeLock);
    this.boutonFight.setBackground(Ressources.boutonFightLock);
    this.boutonCapture.setBackground(Ressources.boutonCaptureLock);
    this.boutonRun.setBackground(Ressources.boutonRunLock);
    this.boutonEquipe.update();
    this.boutonFight.update();
    this.boutonCapture.update();
    this.boutonRun.update();
   }
 
   
   public void setUnlock() {
    this.boutonEquipe.setName("boutonEquipe");
    this.boutonFight.setName("boutonFight");
    this.boutonCapture.setName("boutonCapture");
    this.boutonRun.setName("boutonRun");
    this.boutonEquipe.setBackground(Ressources.boutonEquipe);
    this.boutonFight.setBackground(Ressources.boutonFight);
    this.boutonCapture.setBackground(Ressources.boutonCapture);
    this.boutonRun.setBackground(Ressources.boutonRun);
    this.boutonEquipe.update();
    this.boutonFight.update();
    this.boutonCapture.update();
    this.boutonRun.update();
   }
 
   
   public void setIconsPoke() {
    int nbNoKo = 0;
    int nbKo = 0;
     int i;
    for (i = 0; i < this.combatModele.getJoueur0().getNbPoketudiant(); i++) {
       if (this.combatModele.getJoueur0().getEquipe()[i].getPvCurrent() == 0) { nbKo++; }
      else { nbNoKo++; }
     }  System.out.println("nbnoko = " + nbNoKo);
    System.out.println("nbko = " + nbKo);
     for (i = 0; i < nbNoKo; ) { this.iconsMyEquipe[i].setBackground(Ressources.iconMyPoke); i++; }
     for (i = nbNoKo; i < nbNoKo + nbKo; ) { this.iconsMyEquipe[i].setBackground(Ressources.iconMyPokeKO); i++; }
    if (this.indexIconsEquipeAdversaire == -3) {
       
      for (i = 0; i < this.combatModele.getRival().getNbPoketudiant(); i++)
       {
        this.iconsEquipeAdversaire[i].setBackground(Ressources.iconPokeRival);
       }
      this.indexIconsEquipeAdversaire = this.combatModele.getRival().getNbPoketudiant() - 1;
     } 
     
    update();
   }
 
   
   public void setIconsPokeRivalKO() {
    this.iconsEquipeAdversaire[this.indexIconsEquipeAdversaire].setBackground(Ressources.iconPokeRivalKO);
    this.iconsEquipeAdversaire[this.indexIconsEquipeAdversaire].update();
    this.indexIconsEquipeAdversaire--;
   }
 
   
   public void setDescription(String s) {
    this.desciption.setText(s);
    update();
   }
 
   
   public void update() {
    validate();
    repaint();
   }
 
   
   protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(Ressources.fightBackground, 0, 0, 256 * this.var, 192 * this.var, this);
   }
 
   
   public BoutonMenu getBoutonCapture() {
    return this.boutonCapture;
   }
   
   public BoutonMenu getBoutonFight() {
    return this.boutonFight;
   }
   
   public BoutonMenu getBoutonRun() {
    return this.boutonRun;
   }
   
   public BoutonMenu getBoutonEquipe() {
    return this.boutonEquipe;
   }
   
   public BoutonMenu getBoutonAttaque1() {
    return this.boutonAttaque1;
   }
   
   public BoutonMenu getBoutonAttaque2() {
    return this.boutonAttaque2;
   }
   
   public BoutonMenu getBoutonCancel() {
    return this.boutonCancel;
   }
   
   public BoutonMenu getTypeCurrentJoueur0() {
     return this.typeCurrentJoueur0;
   }
   
   public BoutonMenu getTypeCurrentRival() {
     return this.typeCurrentRival;
   }
 
   
   public void setVarietyJoueur0(String s) {
    this.varietyCurrentJoueur0.setText(s);
   }
 
   
   public void setVarietyRival(String s) {
    this.varietyCurrentRival.setText(s);
   }
 
   
   public void setlvlJoueur0(String s) {
    this.lvlCurrentJoueur0.setText(s);
   }
 
   
   public void setlvlRival(String s) {
    this.lvlCurrentRival.setText(s);
   }
 
   
   public void setHpJoueur0(String s) {
     this.hpJoueur0.setText(s);
   }
   
   public JPanel getHpPerCentJoueur0() {
     return this.hpPerCentJoueur0;
   }
   
   public JPanel getHpPerCentRival() {
     return this.hpPerCentRival;
   }
   
   public BoutonMenu getBoutonPoketudiant1() {
     return this.boutonPoketudiant1;
   }
   
   public BoutonMenu getBoutonPoketudiant2() {
    return this.boutonPoketudiant2;
   }
   
   public Texture getPoketudiantRival() {
    return this.poketudiantRival;
   }
   
   public Texture getPoketudiantJoueur0() {
     return this.poketudiantJoueur0;
   }
 
   
   public BoutonMenu[] getBoutonsPoketudiant() {
     BoutonMenu[] res = new BoutonMenu[2];
     res[0] = this.boutonPoketudiant1;
     res[1] = this.boutonPoketudiant2;
     return res;
   }
 
   
   public JLabel[] getNomsPoketudiant() {
    JLabel[] res = new JLabel[2];
    res[0] = this.nomPoketudiant1;
    res[1] = this.nomPoketudiant2;
    return res;
   }
 
   
   public JLabel[] getHpsPoketudiant() {
    JLabel[] res = new JLabel[2];
    res[0] = this.hpPoketudiant1;
    res[1] = this.hpPoketudiant2;
   return res;
   }
   
   public void update(Observable o, Object arg) {}
 }
