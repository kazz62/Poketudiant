 package principal;
 
 import java.awt.Dimension;
 import java.awt.Font;
 import java.awt.FontFormatException;
 import java.awt.GraphicsEnvironment;
 import java.awt.Toolkit;
 import java.awt.image.BufferedImage;
 
 import java.io.IOException;
 import javax.imageio.ImageIO;
 
 
 public class Ressources
 {
   public static BufferedImage neutre;
   public static BufferedImage sol;
   public static BufferedImage herbe;
   public static BufferedImage vie;
   public static BufferedImage arbre1;
   public static BufferedImage arbre2;
   public static BufferedImage clotures;
   public static BufferedImage fleur;
   public static BufferedImage lac;
   public static BufferedImage lacBorderLeft;
   public static BufferedImage lacBorderTop;
   public static BufferedImage lacCornorLeftTop;
   public static BufferedImage montagne;
   public static BufferedImage plante1;
   public static BufferedImage plante2;
   public static BufferedImage plante3;
   public static BufferedImage neige;
   public static BufferedImage fightBackground;
   public static BufferedImage boutonEquipe;
   public static BufferedImage boutonFight;
   public static BufferedImage boutonCapture;
   public static BufferedImage boutonRun;
   public static BufferedImage boutonFightLock;
   public static BufferedImage boutonEquipeLock;
   public static BufferedImage boutonCaptureLock;
   public static BufferedImage boutonRunLock;
   public static BufferedImage iconMyPoke;
   public static BufferedImage iconPokeRival;
   public static BufferedImage iconMyPokeKO;
   public static BufferedImage iconPokeRivalKO;
   public static BufferedImage noIconMyPoke;
   public static BufferedImage noIconPokeRival;
   public static BufferedImage uiRival;
   public static BufferedImage uiJoueur0;
   public static BufferedImage uiCombat;
   public static BufferedImage backgroundMotivated;
   public static BufferedImage backgroundLazy;
   public static BufferedImage backgroundNoisy;
   public static BufferedImage backgroundTeacher;
   public static BufferedImage backgroundNoPoketudiant;
   public static BufferedImage backgroundMotivatedKO;
   public static BufferedImage backgroundLazyKO;
   public static BufferedImage backgroundNoisyKO;
   public static BufferedImage backgroundTeacherKO;
   public static BufferedImage buchafon;
   public static BufferedImage belmention;
   public static BufferedImage promomajor;
   public static BufferedImage procrastino;
   public static BufferedImage couchtar;
   public static BufferedImage nuidebou;
   public static BufferedImage parlfor;
   public static BufferedImage alabourre;
   public static BufferedImage ismar;
   public static BufferedImage rigolamor;
   public static BufferedImage enseignantDresseur;
   public static BufferedImage boutonCancel;
   public static BufferedImage sortBaillement;
   public static BufferedImage sortPtitSomme;
   public static BufferedImage sortSuperdodo;
   public static BufferedImage sortBavardage;
   public static BufferedImage sortGroboucan;
   public static BufferedImage sortMegaphone;
   public static BufferedImage sortTitQuestion;
   public static BufferedImage sortPosercolle;
   public static BufferedImage sortFatalinterro;
   public static BufferedImage sortOBoulo;
   public static BufferedImage sortExoMaison;
   public static BufferedImage sortMaxReviz;
   public static BufferedImage iconLazy;
   public static BufferedImage iconNoisy;
   public static BufferedImage iconMotivated;
   public static BufferedImage iconTeacher;
   public static BufferedImage boutonMoveUp;
   public static BufferedImage boutonMoveDown;
   public static BufferedImage boutonFree;
   public static BufferedImage boutonBleu;
   public static BufferedImage boutonJaune;
   public static BufferedImage boutonActualiser;
   public static BufferedImage boutonActualiserLock;
   public static BufferedImage boutonValider;
   public static BufferedImage boutonValiderLock;
   public static BufferedImage boutonAnnuler;
   public static BufferedImage boutonCreateGame;
   public static BufferedImage boutonCreateGameLock;
   public static BufferedImage scrollbar_flecheTop;
   public static BufferedImage scrollbar_flecheBot;
   public static BufferedImage[] joueurs;
   public static boolean fullscreen;
   public static int resolutionHeight;
   public static int resolutionWidth;
   public static int var;
   public static String map;
   
   public Ressources() {
    init(); 
    try { read(); }
    catch (IOException e) { e.printStackTrace(); }
   
   }
 
   
   private void init() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
     
    resolutionHeight = (int)dimension.getHeight();
    resolutionWidth = (int)dimension.getWidth();
     
    joueurs = new BufferedImage[5];
    var = 4;
    fullscreen = false;
    map = "";
   }
   
   private void read() throws IOException{
    arbre1 = ImageIO.read(getClass().getResource("/Ressources/arbre1.png"));
    arbre2 = ImageIO.read(getClass().getResource("/Ressources/arbre2.png"));
    lac = ImageIO.read(getClass().getResource("/Ressources/lac.png"));
    lacBorderLeft = ImageIO.read(getClass().getResource("/Ressources/lacBorderLeft.png"));
    lacBorderTop = ImageIO.read(getClass().getResource("/Ressources/lacBorderTop.png"));
    lacCornorLeftTop = ImageIO.read(getClass().getResource("/Ressources/lacCornorLeftTop.png"));
    montagne = ImageIO.read(getClass().getResource("/Ressources/montagne.png"));
    plante1 = ImageIO.read(getClass().getResource("/Ressources/plante1.png"));
    plante2 = ImageIO.read(getClass().getResource("/Ressources/plante2.png"));
    plante3 = ImageIO.read(getClass().getResource("/Ressources/plante3.png"));
    neutre = ImageIO.read(getClass().getResource("/Ressources/neutre.png"));
    sol = ImageIO.read(getClass().getResource("/Ressources/sol.png"));
    herbe = ImageIO.read(getClass().getResource("/Ressources/herbes.png"));
    vie = ImageIO.read(getClass().getResource("/Ressources/vie.png"));
    clotures = ImageIO.read(getClass().getResource("/Ressources/clotures.png"));
    fleur = ImageIO.read(getClass().getResource("/Ressources/fleur.png"));
    neige = ImageIO.read(getClass().getResource("/Ressources/neige.png"));
    fightBackground = ImageIO.read(getClass().getResource("/Ressources/FightBackground.png"));
    boutonEquipe = ImageIO.read(getClass().getResource("/Ressources/boutonEquipe.png"));
    boutonFight = ImageIO.read(getClass().getResource("/Ressources/boutonFight.png"));
    boutonCapture = ImageIO.read(getClass().getResource("/Ressources/boutonCapture.png"));
    boutonRun = ImageIO.read(getClass().getResource("/Ressources/boutonRun.png"));
    boutonFightLock = ImageIO.read(getClass().getResource("/Ressources/boutonFightLock.png"));
    boutonEquipeLock = ImageIO.read(getClass().getResource("/Ressources/boutonEquipeLock.png"));
    boutonCaptureLock = ImageIO.read(getClass().getResource("/Ressources/boutonCaptureLock.png"));
    boutonRunLock = ImageIO.read(getClass().getResource("/Ressources/boutonRunLock.png"));
    iconMyPoke = ImageIO.read(getClass().getResource("/Ressources/iconMyPoke.png"));
    iconPokeRival = ImageIO.read(getClass().getResource("/Ressources/iconPokeRival.png"));
    iconMyPokeKO = ImageIO.read(getClass().getResource("/Ressources/iconMyPokeKO.png"));
    iconPokeRivalKO = ImageIO.read(getClass().getResource("/Ressources/iconPokeRivalKO.png"));
    noIconMyPoke = ImageIO.read(getClass().getResource("/Ressources/noIconMyPoke.png"));
    noIconPokeRival = ImageIO.read(getClass().getResource("/Ressources/noIconPokeRival.png"));
    uiRival = ImageIO.read(getClass().getResource("/Ressources/uiRival.png"));
    uiJoueur0 = ImageIO.read(getClass().getResource("/Ressources/uiJoueur0.png"));
    uiCombat = ImageIO.read(getClass().getResource("/Ressources/uiCombat.png"));
    backgroundMotivated = ImageIO.read(getClass().getResource("/Ressources/backgroundMotivated.png"));
    backgroundLazy = ImageIO.read(getClass().getResource("/Ressources/backgroundLazy.png"));
    backgroundNoisy = ImageIO.read(getClass().getResource("/Ressources/backgroundNoisy.png"));
    backgroundTeacher = ImageIO.read(getClass().getResource("/Ressources/backgroundTeacher.png"));
    backgroundNoPoketudiant = ImageIO.read(getClass().getResource("/Ressources/backgroundNoPoketudiant.png"));
    backgroundMotivatedKO = ImageIO.read(getClass().getResource("/Ressources/backgroundMotivatedKO.png"));
    backgroundLazyKO = ImageIO.read(getClass().getResource("/Ressources/backgroundLazyKO.png"));
    backgroundNoisyKO = ImageIO.read(getClass().getResource("/Ressources/backgroundNoisyKO.png"));
    backgroundTeacherKO = ImageIO.read(getClass().getResource("/Ressources/backgroundTeacherKO.png"));
    boutonCancel = ImageIO.read(getClass().getResource("/Ressources/boutonCancel.png"));
    sortBaillement = ImageIO.read(getClass().getResource("/Ressources/sortBaillement.png"));
    sortPtitSomme = ImageIO.read(getClass().getResource("/Ressources/sortPtitSomme.png"));
    sortSuperdodo = ImageIO.read(getClass().getResource("/Ressources/sortSuperdodo.png"));
    sortBavardage = ImageIO.read(getClass().getResource("/Ressources/sortBavardage.png"));
    sortGroboucan = ImageIO.read(getClass().getResource("/Ressources/sortGroboucan.png"));
    sortMegaphone = ImageIO.read(getClass().getResource("/Ressources/sortMegaphone.png"));
    sortTitQuestion = ImageIO.read(getClass().getResource("/Ressources/sortTitQuestion.png"));
    sortPosercolle = ImageIO.read(getClass().getResource("/Ressources/sortPosercolle.png"));
    sortFatalinterro = ImageIO.read(getClass().getResource("/Ressources/sortFatalinterro.png"));
    sortOBoulo = ImageIO.read(getClass().getResource("/Ressources/sortOBoulo.png"));
    sortExoMaison = ImageIO.read(getClass().getResource("/Ressources/sortExoMaison.png"));
    sortMaxReviz = ImageIO.read(getClass().getResource("/Ressources/sortMaxReviz.png"));
    buchafon = ImageIO.read(getClass().getResource("/Ressources/buchafon.png"));
    belmention = ImageIO.read(getClass().getResource("/Ressources/belmention.png"));
    promomajor = ImageIO.read(getClass().getResource("/Ressources/promomajor.png"));
    alabourre = ImageIO.read(getClass().getResource("/Ressources/alabourre.png"));
    procrastino = ImageIO.read(getClass().getResource("/Ressources/procrastino.png"));
    couchtar = ImageIO.read(getClass().getResource("/Ressources/couchtar.png"));
    nuidebou = ImageIO.read(getClass().getResource("/Ressources/nuidebou.png"));
    parlfor = ImageIO.read(getClass().getResource("/Ressources/parlfor.png"));
    ismar = ImageIO.read(getClass().getResource("/Ressources/ismar.png"));
    rigolamor = ImageIO.read(getClass().getResource("/Ressources/rigolamor.png"));
    enseignantDresseur = ImageIO.read(getClass().getResource("/Ressources/enseignantDresseur.png"));
    iconLazy = ImageIO.read(getClass().getResource("/Ressources/iconLazy.png"));
    iconNoisy = ImageIO.read(getClass().getResource("/Ressources/iconNoisy.png"));
    iconMotivated = ImageIO.read(getClass().getResource("/Ressources/iconMotivated.png"));
    iconTeacher = ImageIO.read(getClass().getResource("/Ressources/iconTeacher.png"));
    boutonBleu = ImageIO.read(getClass().getResource("/Ressources/boutonBleu.png"));
    boutonJaune = ImageIO.read(getClass().getResource("/Ressources/boutonJaune.png"));
    boutonActualiser = ImageIO.read(getClass().getResource("/Ressources/boutonActualiser.png"));
    boutonActualiserLock = ImageIO.read(getClass().getResource("/Ressources/boutonActualiserLock.png"));
    boutonValider = ImageIO.read(getClass().getResource("/Ressources/boutonValider.png"));
    boutonValiderLock = ImageIO.read(getClass().getResource("/Ressources/boutonValiderLock.png"));
    boutonAnnuler = ImageIO.read(getClass().getResource("/Ressources/boutonAnnuler.png"));
    boutonCreateGame = ImageIO.read(getClass().getResource("/Ressources/boutonCreateGame.png"));
    boutonCreateGameLock = ImageIO.read(getClass().getResource("/Ressources/boutonCreateGameLock.png"));
    boutonMoveUp = ImageIO.read(getClass().getResource("/Ressources/boutonMoveUp.png"));
    boutonMoveDown = ImageIO.read(getClass().getResource("/Ressources/boutonMoveDown.png"));
    boutonFree = ImageIO.read(getClass().getResource("/Ressources/boutonFree.png"));
    scrollbar_flecheTop = ImageIO.read(getClass().getResource("/Ressources/scrollbar_flecheTop.png"));
    scrollbar_flecheBot = ImageIO.read(getClass().getResource("/Ressources/scrollbar_flecheBot.png"));
    joueurs[0] = ImageIO.read(getClass().getResource("/Ressources/joueur0.png"));
    joueurs[1] = ImageIO.read(getClass().getResource("/Ressources/joueur1.png"));
    joueurs[2] = ImageIO.read(getClass().getResource("/Ressources/joueur2.png"));
    joueurs[3] = ImageIO.read(getClass().getResource("/Ressources/joueur3.png"));
    joueurs[4] = ImageIO.read(getClass().getResource("/Ressources/joueur4.png"));
     
     try {
      GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(0, getClass().getResourceAsStream("/Ressources/Pokemon X and Y.ttf")));
      GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(0, getClass().getResourceAsStream("/Ressources/PixelOperator8.ttf")));
    } catch (FontFormatException e) {
      e.printStackTrace();
     } 
   }
 }