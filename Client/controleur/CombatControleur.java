package controleur;
 
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import modele.CombatModele;
import principal.Attaque;
import principal.Main;
import principal.Poketudiant;
import principal.Ressources;
import principal.Variety;
import vue.BoutonMenu;
import vue.CombatVue;
 
 
public class CombatControleur{

  private int var;
  private boolean attaque;
  private boolean switchPoke;
  private CombatModele combatModele;
  private CombatVue combatVue;
  private MouseListener mouseListener;
  private Runnable runnableBarreHpJoueur0;
  private Runnable runnableBarreHpRival;
  private Thread threadBarreHpJoueur0;
  private Thread threadBarreHpRival;
  private Runnable runnableCombat;
  private Thread threadCombat;
  private TchatControleur tchatControleur;
   
  public CombatControleur(CombatModele combatModele, CombatVue combatVue, TchatControleur tchatControleur) {
    this.var = Ressources.var;
    this.attaque = false;
    this.switchPoke = false;
    this.combatModele = combatModele;
    this.combatVue = combatVue;
    this.tchatControleur = tchatControleur;
    init();
    this.threadCombat = new Thread(this.runnableCombat);
    this.threadCombat.start();
  }
   
  private void init() {
    initRunnableCombat();
    initMouseListener();
    initKeyListeners();
  }
   
  private void initRunnableCombat() {
    this.runnableCombat = new Runnable() {
      private boolean run = true;
         
      public void run() {
        try { while (this.run) {
          String reponse = Main.socket.readLine();
          if (reponse == null) {
            CombatControleur.this.combatModele.setChanged("menu broadcast");
            this.run = false;
            Main.socket.close();
            continue;
          } 
          if (reponse.equals("encounter enter action")) {
            CombatControleur.this.combatVue.setDescription("Que va faire " + CombatControleur.this.combatModele.getJoueur0().getPokeCurrent().getVariety().getName() + " ?");
            CombatControleur.this.combatVue.setUnlock();
            continue;
          } 
          if (reponse.equals("encounter enter poketudiant index")) {
            CombatControleur.this.combatVue.setDescription("Choisissez un Pokétudiant");
            CombatControleur.this.combatVue.setEquipe();
            continue;
          } 
          if (!reponse.equals("encounter forbidden action")){
            if (!reponse.equals("encounter invalid poketudiant index")) {
              if (reponse.equals("encounter catch ok")) {
                CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getRival().getPokeCurrent().getVariety().getName()) + " capturé !");
                Thread.sleep(1500L);
                CombatControleur.this.combatModele.setChanged("join game");
                this.run = false;
                continue;
              }
              if (reponse.equals("encounter catch fail")) {
                CombatControleur.this.combatVue.setDescription("Catch fail ...");
                Thread.sleep(1500L);
                continue;
              } 
              if (reponse.equals("encounter escape ok")) {
                CombatControleur.this.combatModele.setChanged("join game");
                this.run = false;
                continue;
              } 
              if (reponse.equals("encounter escape fail")) {
                CombatControleur.this.combatVue.setDescription("Escape fail ...");
                Thread.sleep(1500L);
                continue;
              } 
              if (reponse.equals("encounter KO opponent")) {
                CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getRival().getPokeCurrent().getVariety().getName()) + " KO !");
                Thread.sleep(1500L);
                continue;
              } 
              if (reponse.equals("encounter KO player")) {
                CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getPokeCurrent().getVariety().getName()) + " KO !");
                Thread.sleep(1500L);
                continue;
              } 
              if (reponse.equals("encounter win")) {
                CombatControleur.this.combatVue.setDescription("Vous gagnez !");
                Thread.sleep(1500L);  
                do {
                  reponse = Main.socket.readLine();
                  if (reponse.length() > 24 && reponse.substring(0, 24).equals("encounter poketudiant xp")){
                    int indice = Integer.parseInt(reponse.substring(25, 26));
                    int xp = Integer.parseInt(reponse.substring(27));
                    if (xp >= 0) { CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " gagne " + xp + " xp."); }
                    else { CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " perd " + (xp * -1) + " xp."); }
                    Thread.sleep(1500L);               
                  }else if (reponse.length() > 27 && reponse.substring(0, 27).equals("encounter poketudiant level")){
                    int indice = Integer.parseInt(reponse.substring(28, 29));
                    int level = Integer.parseInt(reponse.substring(30));
                    if (level >= 0) { CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " gagne " + level + " level."); }
                    else { CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " perd " + (level * -1) + " level."); }
                    Thread.sleep(1500L);
                       
                  }else if (reponse.length() > 31 && reponse.substring(0, 31).equals("encounter poketudiant evolution")){
                    int indice = Integer.parseInt(reponse.substring(32, 33));
                    String variety = reponse.substring(34);
                    CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " évolue en " + variety + " !");
                    Thread.sleep(1500L);
                  }
                } while (!reponse.substring(0, 3).equals("map"));
                    CombatControleur.this.combatModele.setChanged("join game");
                    this.run = false;
                    continue;
              } 
              if (reponse.equals("encounter lose")) {
                CombatControleur.this.combatVue.setDescription("Vous avez perdu !");
                Thread.sleep(1500L);       
                do {
                  reponse = Main.socket.readLine();
                  if (reponse.length() > 24 && reponse.substring(0, 24).equals("encounter poketudiant xp")){
                    int indice = Integer.parseInt(reponse.substring(25, 26));
                    int xp = Integer.parseInt(reponse.substring(27));
                    if (xp >= 0) { CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " gagne " + xp + " xp."); }
                    else { CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " perd " + (xp * -1) + " xp."); }
                    Thread.sleep(1500L);
                       
                  }else if (reponse.length() > 27 && reponse.substring(0, 27).equals("encounter poketudiant level")){
                    int indice = Integer.parseInt(reponse.substring(28, 29));
                    int level = Integer.parseInt(reponse.substring(30));
                    if (level >= 0) { CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " gagne " + level + " level."); }
                    else { CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " perd " + (level * -1) + " level."); }
                    Thread.sleep(1500L);
                       
                  }else if (reponse.length() > 31 && reponse.substring(0, 31).equals("encounter poketudiant evolution")){
                    int indice = Integer.parseInt(reponse.substring(32, 33));
                    String variety = reponse.substring(34);
                    CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getEquipe()[indice].getVariety().getName()) + " évolue en " + variety + " !");
                    Thread.sleep(1500L);
                  }
                     
                } while (!reponse.substring(0, 3).equals("map"));      
                CombatControleur.this.combatModele.setChanged("join game");
                this.run = false;
                continue;
              } 
              if (reponse.length() > 29 && reponse.substring(0, 28).equals("encounter poketudiant player")) {
                int oldHp;
                String[] tmp = reponse.split(" ");
                if (CombatControleur.this.combatModele.getJoueur0().getPokeCurrent() != null && !CombatControleur.this.switchPoke) {
                int oldHpCurrent = CombatControleur.this.combatModele.getJoueur0().getEquipe()[CombatControleur.this.combatModele.getJoueur0().getIndexCurrentPoke()].getPvCurrent();
                int hpMax = CombatControleur.this.combatModele.getJoueur0().getEquipe()[CombatControleur.this.combatModele.getJoueur0().getIndexCurrentPoke()].getPvMax();
                oldHp = oldHpCurrent * 100 / hpMax;
                CombatControleur.this.combatVue.getHpPerCentJoueur0().setBounds(200 * CombatControleur.this.var, 119 * CombatControleur.this.var, oldHp * 48 * CombatControleur.this.var / 100, 2 * CombatControleur.this.var);
                int k;
                for (k = 0; k < 125; k++) {
                  int posX = CombatControleur.this.combatVue.getPoketudiantRival().getX();
                  int posY = CombatControleur.this.combatVue.getPoketudiantRival().getY();
                  CombatControleur.this.combatVue.getPoketudiantRival().setBounds(posX - 2, posY + 1, CombatControleur.this.combatVue.getPoketudiantRival().getWidth(), CombatControleur.this.combatVue.getPoketudiantRival().getHeight());
                  CombatControleur.this.combatVue.getPoketudiantRival().update();
                  Thread.sleep(1L);
                } 
                for (k = 0; k < 125; k++) {
                  int posX = CombatControleur.this.combatVue.getPoketudiantRival().getX();
                  int posY = CombatControleur.this.combatVue.getPoketudiantRival().getY();
                  CombatControleur.this.combatVue.getPoketudiantRival().setBounds(posX + 2, posY - 1, CombatControleur.this.combatVue.getPoketudiantRival().getWidth(), CombatControleur.this.combatVue.getPoketudiantRival().getHeight());
                  CombatControleur.this.combatVue.getPoketudiantRival().update();
                  Thread.sleep(1L);
                } 
                CombatControleur.this.combatVue.getPoketudiantJoueur0().setVisible(false);
                Thread.sleep(100L);
                CombatControleur.this.combatVue.getPoketudiantJoueur0().setVisible(true);
                for (k = 0; k < 3; k++) {
                  Thread.sleep(100L);
                  CombatControleur.this.combatVue.getPoketudiantJoueur0().setVisible(false);
                  Thread.sleep(100L);
                  CombatControleur.this.combatVue.getPoketudiantJoueur0().setVisible(true);
                } 
              } else {
                oldHp = -1;
              } 
              if (CombatControleur.this.switchPoke){
                for (int k = 0; k < 250; k++) {
                  int posX = CombatControleur.this.combatVue.getPoketudiantJoueur0().getX();
                  int posY = CombatControleur.this.combatVue.getPoketudiantJoueur0().getY();
                  CombatControleur.this.combatVue.getPoketudiantJoueur0().setBounds(posX - 2, posY, CombatControleur.this.combatVue.getPoketudiantJoueur0().getWidth(), CombatControleur.this.combatVue.getPoketudiantJoueur0().getHeight());
                  CombatControleur.this.combatVue.getPoketudiantJoueur0().update();
                  Thread.sleep(1L);
                 } 
              }      
              Poketudiant poke = new Poketudiant();
              poke.setVariety(Variety.getVariety(tmp[3]));
              poke.setLvl(Integer.parseInt(tmp[4]));
              poke.setPvPerCent(Float.parseFloat(tmp[5]));
              poke.setAttaque1(Attaque.getAttaque(tmp[6]));
              poke.setAttaque2(Attaque.getAttaque(tmp[8]));
              CombatControleur.this.combatModele.getJoueur0().setPokeCurrent(poke);
              CombatControleur.this.combatVue.getPoketudiantJoueur0().setBackground(poke.getVariety().getDos());
              if (CombatControleur.this.switchPoke){
                for (int k = 0; k < 250; k++) {
                  int posX = CombatControleur.this.combatVue.getPoketudiantJoueur0().getX();
                  int posY = CombatControleur.this.combatVue.getPoketudiantJoueur0().getY();
                  CombatControleur.this.combatVue.getPoketudiantJoueur0().setBounds(posX + 2, posY, CombatControleur.this.combatVue.getPoketudiantJoueur0().getWidth(), CombatControleur.this.combatVue.getPoketudiantJoueur0().getHeight());
                  CombatControleur.this.combatVue.getPoketudiantJoueur0().update();
                  Thread.sleep(1L);
                } 
              }
              CombatControleur.this.combatVue.getBoutonAttaque1().setBackground(poke.getAttaque1().getImageBouton());
              CombatControleur.this.combatVue.getBoutonAttaque2().setBackground(poke.getAttaque2().getImageBouton());
              CombatControleur.this.combatVue.getTypeCurrentJoueur0().setBounds(153 * CombatControleur.this.var, 116 * CombatControleur.this.var, poke.getVariety().getType().getIcon().getWidth() * CombatControleur.this.var, poke.getVariety().getType().getIcon().getHeight() * CombatControleur.this.var);
              CombatControleur.this.combatVue.getTypeCurrentJoueur0().setBackground(poke.getVariety().getType().getIcon());
              CombatControleur.this.combatVue.setVarietyJoueur0(poke.getVariety().getName());
              CombatControleur.this.combatVue.setlvlJoueur0("Lv. " + poke.getLvl());
              int newHp = CombatControleur.this.combatModele.getJoueur0().getPokeCurrent().getPvPerCent();
              if (oldHp != -1) {
                int difHp = oldHp - newHp;
                if (difHp > 0) CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getRival().getPokeCurrent().getVariety().getName()) + " vous attaque !");
                  CombatControleur.this.initRunnableBarreHpJoueur0(difHp, CombatControleur.this.combatVue.getHpPerCentJoueur0());
                  CombatControleur.this.threadBarreHpJoueur0 = new Thread(CombatControleur.this.runnableBarreHpJoueur0);
                  CombatControleur.this.threadBarreHpJoueur0.start();
                  CombatControleur.this.threadBarreHpJoueur0.join();
                }
                else {
                  if (newHp >= 0 && newHp <= 25) { CombatControleur.this.combatVue.getHpPerCentJoueur0().setBackground(new Color(229, 29, 22)); }
                  else if (newHp > 25 && newHp <= 50) { CombatControleur.this.combatVue.getHpPerCentJoueur0().setBackground(new Color(229, 119, 29)); }
                  else if (newHp > 50 && newHp <= 75) { CombatControleur.this.combatVue.getHpPerCentJoueur0().setBackground(new Color(229, 226, 45)); }
                  else if (newHp > 75 && newHp <= 100) { CombatControleur.this.combatVue.getHpPerCentJoueur0().setBackground(new Color(24, 192, 32)); }
                  CombatControleur.this.combatVue.getHpPerCentJoueur0().setBounds(200 * CombatControleur.this.var, 119 * CombatControleur.this.var, newHp * 48 * CombatControleur.this.var / 100, 2 * CombatControleur.this.var);
                } 
                     
                   CombatControleur.this.combatVue.setAction();
                     
                   CombatControleur.this.switchPoke = false;
                    continue;
                   } 
                 if (reponse.length() > 31 && reponse.subSequence(0, 30).equals("encounter poketudiant opponent")) {
                     int oldHp;
                     
                   String[] tmp = reponse.split(" ");
                     
                   if (CombatControleur.this.combatModele.getRival().getPokeCurrent() != null && CombatControleur.this.attaque) {
                       
                    oldHp = CombatControleur.this.combatModele.getRival().getPokeCurrent().getPvPerCent();
                    CombatControleur.this.attaque = false;

                    int k;
                    for (k = 0; k < 125; k++) {  
                      int posX = CombatControleur.this.combatVue.getPoketudiantJoueur0().getX();
                      int posY = CombatControleur.this.combatVue.getPoketudiantJoueur0().getY();
                      CombatControleur.this.combatVue.getPoketudiantJoueur0().setBounds(posX + 2, posY - 1, CombatControleur.this.combatVue.getPoketudiantJoueur0().getWidth(), CombatControleur.this.combatVue.getPoketudiantJoueur0().getHeight());
                      CombatControleur.this.combatVue.getPoketudiantJoueur0().update();
                      Thread.sleep(1L);
                    } 
                    for (k = 0; k < 125; k++) {
                      int posX = CombatControleur.this.combatVue.getPoketudiantJoueur0().getX();
                      int posY = CombatControleur.this.combatVue.getPoketudiantJoueur0().getY();
                      CombatControleur.this.combatVue.getPoketudiantJoueur0().setBounds(posX - 2, posY + 1, CombatControleur.this.combatVue.getPoketudiantJoueur0().getWidth(), CombatControleur.this.combatVue.getPoketudiantJoueur0().getHeight());
                      CombatControleur.this.combatVue.getPoketudiantJoueur0().update();
                      Thread.sleep(1L);
                    } 
                    CombatControleur.this.combatVue.getPoketudiantRival().setVisible(false);
                    Thread.sleep(100L);
                    CombatControleur.this.combatVue.getPoketudiantRival().setVisible(true);
                    for (k = 0; k < 3; k++) {
                      Thread.sleep(100L);
                      CombatControleur.this.combatVue.getPoketudiantRival().setVisible(false);
                      Thread.sleep(100L);
                      CombatControleur.this.combatVue.getPoketudiantRival().setVisible(true);
                    } 
                  } else {
                     oldHp = -1;
                  } 
                  Poketudiant poke = new Poketudiant();
                  poke.setVariety(Variety.getVariety(tmp[3]));
                  poke.setLvl(Integer.parseInt(tmp[4]));
                  poke.setPvPerCent(Float.parseFloat(tmp[5]));
                  CombatControleur.this.combatModele.getRival().setPokeCurrent(poke);
                  CombatControleur.this.combatVue.getPoketudiantRival().setBackground(poke.getVariety().getFace());
                  CombatControleur.this.combatVue.getTypeCurrentRival().setBounds(2 * CombatControleur.this.var, 40 * CombatControleur.this.var, poke.getVariety().getType().getIcon().getWidth() * CombatControleur.this.var, poke.getVariety().getType().getIcon().getHeight() * CombatControleur.this.var);
                  CombatControleur.this.combatVue.getTypeCurrentRival().setBackground(poke.getVariety().getType().getIcon());
                  CombatControleur.this.combatVue.setVarietyRival(poke.getVariety().getName());
                  CombatControleur.this.combatVue.setlvlRival("Lv. " + poke.getLvl());
                  int newHp = CombatControleur.this.combatModele.getRival().getPokeCurrent().getPvPerCent();
                  if (oldHp != -1) {
                    int difHp = oldHp - newHp;
                    CombatControleur.this.initRunnableBarreHpRival(difHp, CombatControleur.this.combatVue.getHpPerCentRival());
                    CombatControleur.this.threadBarreHpRival = new Thread(CombatControleur.this.runnableBarreHpRival);
                    CombatControleur.this.threadBarreHpRival.start();
                    CombatControleur.this.threadBarreHpRival.join();
                  }
                  else {
                    if (newHp >= 0 && newHp <= 25) { CombatControleur.this.combatVue.getHpPerCentRival().setBackground(new Color(229, 29, 22)); }
                    else if (newHp > 25 && newHp <= 50) { CombatControleur.this.combatVue.getHpPerCentRival().setBackground(new Color(229, 119, 29)); }
                    else if (newHp > 50 && newHp <= 75) { CombatControleur.this.combatVue.getHpPerCentRival().setBackground(new Color(229, 226, 45)); }
                    else if (newHp > 75 && newHp <= 100) { CombatControleur.this.combatVue.getHpPerCentRival().setBackground(new Color(24, 192, 32)); }
                    CombatControleur.this.combatVue.getHpPerCentRival().setBounds(53 * CombatControleur.this.var, 43 * CombatControleur.this.var, newHp * 46 * CombatControleur.this.var / 100, 2 * CombatControleur.this.var);
                  } 
                  if (newHp == 0) CombatControleur.this.combatVue.setIconsPokeRivalKO(); 
                    continue;
                  } 
                  if (reponse.length() > 14 && reponse.substring(0, 13).equals("team contains")) {
                    int nb = Integer.parseInt(reponse.substring(14));
                    int j = 0;
                    CombatControleur.this.combatModele.getJoueur0().removePoketudiantAll();
                    for (int i = 0; i < nb; i++) {
                      String[] arrayOfString = Main.socket.readLine().split(" ");
                      Poketudiant poke = new Poketudiant();
                      poke.setVariety(Variety.getVariety(arrayOfString[0]));
                      poke.setLvl(Integer.parseInt(arrayOfString[2]));
                      poke.setXpCurrent(Integer.parseInt(arrayOfString[3]));
                      poke.setXpNextLvl(Integer.parseInt(arrayOfString[4]));
                      poke.setPvCurrent(Integer.parseInt(arrayOfString[5]));
                      poke.setPvMax(Integer.parseInt(arrayOfString[6]));
                      poke.setAttaque(Integer.parseInt(arrayOfString[7]));
                      poke.setDefense(Integer.parseInt(arrayOfString[8]));
                      poke.setAttaque1(Attaque.getAttaque(arrayOfString[9]));
                      poke.setAttaque2(Attaque.getAttaque(arrayOfString[11]));
                      poke.setNum(i);
                      CombatControleur.this.combatModele.getJoueur0().addPoketudiant(poke);
                      if (i != CombatControleur.this.combatModele.getJoueur0().getIndexCurrentPoke()) {
                        if (poke.getPvCurrent() == 0) {
                          CombatControleur.this.combatVue.getBoutonsPoketudiant()[j].setBackground(poke.getVariety().getType().getBackgroundKO());
                          CombatControleur.this.combatVue.getBoutonsPoketudiant()[j].setName("ko");
                        }
                        else {
                          CombatControleur.this.combatVue.getBoutonsPoketudiant()[j].setBackground(poke.getVariety().getType().getBackground());
                          CombatControleur.this.combatVue.getBoutonsPoketudiant()[j].setName("poketudiant " + i);
                        } 
                        CombatControleur.this.combatVue.getNomsPoketudiant()[j].setText(poke.getVariety().getName());
                        CombatControleur.this.combatVue.getHpsPoketudiant()[j].setText(String.valueOf(poke.getPvCurrent()) + "/" + poke.getPvMax());
                        j++;
                      } 
                    } 
                    Poketudiant tmp = CombatControleur.this.combatModele.getJoueur0().getEquipe()[CombatControleur.this.combatModele.getJoueur0().getIndexCurrentPoke()];
                    CombatControleur.this.combatVue.setHpJoueur0(String.valueOf(tmp.getPvCurrent()) + "/" + tmp.getPvMax());
                    CombatControleur.this.combatVue.setIconsPoke();
                    continue;
                  } 
                  if (reponse.length() > 3 && reponse.substring(0, 14).equals("rival message ")){ 
                    String[] s = reponse.substring(14).split(" ", 2);
                    String nomJoueur = s[0];
                    String message = s[1];
                    CombatControleur.this.tchatControleur.getTchatModele().ajouterMessage(message, nomJoueur); 
                  } 
                }
              } 
            }  
        }catch (InterruptedException e) { e.printStackTrace(); }
      }
    };
  }

  private void initKeyListeners() {
    this.combatVue.addKeyListener(this.tchatControleur.getKeyAdapterOutEntry());
  }
   
  private void initMouseListener() {
    this.mouseListener = new MouseListener(){        
        public void mouseReleased(MouseEvent e){
          BoutonMenu tmp = (BoutonMenu)e.getSource();
          if (tmp.getName().equals("boutonRun")) {
            CombatControleur.this.combatVue.setLock();
            Main.socket.sendTo("encounter action leave\n");  
          }else if (tmp.getName().equals("boutonCapture")) {
            CombatControleur.this.combatVue.setLock();
            Main.socket.sendTo("encounter action catch\n");           
          }else if (tmp.getName().equals("boutonFight")) {
            CombatControleur.this.combatVue.setFight();    
          }else if (tmp.getName().equals("boutonEquipe")) {
            CombatControleur.this.combatVue.setEquipe();          
          }else if (tmp.getName().length() > 12 && tmp.getName().substring(0, 11).equals("poketudiant")) {
            CombatControleur.this.switchPoke = true;
            CombatControleur.this.combatVue.setLock();
            Main.socket.sendTo("encounter action switch\n");
            Main.socket.sendTo("encounter poketudiant index " + tmp.getName().substring(12) + "\n");
            CombatControleur.this.combatModele.getJoueur0().setIndexCurrentPoke(Integer.parseInt(tmp.getName().substring(12)));          
          }else if (tmp.getName().equals("cancel")) {
            CombatControleur.this.combatVue.setAction();       
          }else if (tmp.getName().equals("attaque1")) {
            CombatControleur.this.attaque = true;
            CombatControleur.this.combatVue.setLock();
            Main.socket.sendTo("encounter action attack1\n");
            CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getPokeCurrent().getVariety().getName()) + " lance attaque " + CombatControleur.this.combatModele.getJoueur0().getPokeCurrent().getAttaque1().getName());
            CombatControleur.this.combatVue.setAction();
          }else if (tmp.getName().equals("attaque2")) {
            CombatControleur.this.attaque = true;
            CombatControleur.this.combatVue.setLock();
            Main.socket.sendTo("encounter action attack2\n");
            CombatControleur.this.combatVue.setDescription(String.valueOf(CombatControleur.this.combatModele.getJoueur0().getPokeCurrent().getVariety().getName()) + " lance attaque " + CombatControleur.this.combatModele.getJoueur0().getPokeCurrent().getAttaque2().getName());
            CombatControleur.this.combatVue.setAction();
          } 
        }

         public void mousePressed(MouseEvent e) {}

         public void mouseExited(MouseEvent e) {}

         public void mouseEntered(MouseEvent e) {}

         public void mouseClicked(MouseEvent e) {}
    };
    this.combatVue.getBoutonCapture().addMouseListener(this.mouseListener);
    this.combatVue.getBoutonFight().addMouseListener(this.mouseListener);
    this.combatVue.getBoutonRun().addMouseListener(this.mouseListener);
    this.combatVue.getBoutonEquipe().addMouseListener(this.mouseListener);
    this.combatVue.getBoutonAttaque1().addMouseListener(this.mouseListener);
    this.combatVue.getBoutonAttaque2().addMouseListener(this.mouseListener);
    this.combatVue.getBoutonCancel().addMouseListener(this.mouseListener);
    this.combatVue.getBoutonPoketudiant1().addMouseListener(this.mouseListener);
    this.combatVue.getBoutonPoketudiant2().addMouseListener(this.mouseListener);
  }
   
  private void initRunnableBarreHpJoueur0(int a, JPanel barre) {
    this.runnableBarreHpJoueur0 = new Runnable(){
      private int dif;
      private JPanel barreHp;

      public void run() {
        for (int i = 0; i < this.dif * 48 * CombatControleur.this.var / 100; i++) {
          this.barreHp.setBounds(this.barreHp.getX(), this.barreHp.getY(), this.barreHp.getWidth() - 1, 2 * CombatControleur.this.var);
          int tmpHp = this.barreHp.getWidth() * 100 / 48 * CombatControleur.this.var;
          if (tmpHp > 0 && tmpHp <= 25) { this.barreHp.setBackground(new Color(229, 29, 22)); }
          else if (tmpHp > 25 && tmpHp <= 50) { this.barreHp.setBackground(new Color(229, 119, 29)); }
          else if (tmpHp > 50 && tmpHp <= 75) { this.barreHp.setBackground(new Color(229, 226, 45)); }
          else if (tmpHp > 75 && tmpHp <= 100) { this.barreHp.setBackground(new Color(24, 192, 32)); }
          this.barreHp.validate();
          this.barreHp.repaint();         
          try {
            Thread.sleep((5 * CombatControleur.this.var));
          } catch (InterruptedException e) {
            e.printStackTrace();
          } 
        } 
      }
    };
 }

   private void initRunnableBarreHpRival(int a, JPanel barre) {
    this.runnableBarreHpRival = new Runnable(){
      private int dif;
      private JPanel barreHp;
         
      public void run() {
        for (int i = 0; i <= this.dif * 46 * CombatControleur.this.var / 100; i++) {
          this.barreHp.setBounds(this.barreHp.getX(), this.barreHp.getY(), this.barreHp.getWidth() - 1, 2 * CombatControleur.this.var);
          int tmpHp = this.barreHp.getWidth() * 100 / 46 * CombatControleur.this.var;
          if (tmpHp >= 0 && tmpHp <= 25) { this.barreHp.setBackground(new Color(229, 29, 22)); }
          else if (tmpHp > 25 && tmpHp <= 50) { this.barreHp.setBackground(new Color(229, 119, 29)); }
          else if (tmpHp > 50 && tmpHp <= 75) { this.barreHp.setBackground(new Color(229, 226, 45)); }
          else if (tmpHp > 75 && tmpHp <= 100) { this.barreHp.setBackground(new Color(24, 192, 32)); }
          this.barreHp.validate();
          this.barreHp.repaint(); 
          try {
            Thread.sleep((5 * CombatControleur.this.var / (this.dif / 25 + 1)));
          } catch (InterruptedException e) {
            e.printStackTrace();
          } 
        } 
      }
    };
  }
}