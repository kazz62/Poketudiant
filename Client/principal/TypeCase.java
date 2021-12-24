 package principal;
 
 import java.awt.image.BufferedImage;
 
 public enum TypeCase
 {
  NEUTRE(' ', Ressources.neutre, 16, 16, false),
  PLANTE1(' ', Ressources.plante1, 16, 16, false),
  PLANTE2(' ', Ressources.plante2, 16, 16, false),
  PLANTE3(' ', Ressources.plante3, 16, 16, false),
  NOPE('?', Ressources.neutre, 16, 16, false),
  HERBE('*', Ressources.herbe, 16, 16, false),
  SOIN('+', Ressources.vie, 16, 16, false),
  ARBRE1('|', Ressources.arbre1, 44, 64, true),
  ARBRE2('|', Ressources.arbre2, 44, 64, true),
  CLOTURES('|', Ressources.clotures, 16, 24, true),
  MONTAGNE('|', Ressources.montagne, 16, 64, true),
  LAC_BORDERLEFT('|', Ressources.lacBorderLeft, 16, 16, true),
  LAC_BORDERTOP('|', Ressources.lacBorderTop, 16, 16, true),
  LAC_CORNOR('|', Ressources.lacCornorLeftTop, 16, 16, true),
  LAC('|', Ressources.lac, 16, 16, false),
  FLEUR('|', Ressources.fleur, 16, 16, true);
   
   private char protocole;
   
   private BufferedImage background;
   private int width;
   private int height;
   private boolean collision;
   
   TypeCase(char protocole, BufferedImage background, int width, int height, boolean collision) {
    this.background = background;
    this.collision = collision;
    this.protocole = protocole;
    this.width = width;
    this.height = height;
   }
 
 
 
   
   public static TypeCase getTypeCase(char c) {
    if (c != ' ') {
      byte b; int i; TypeCase[] arrayOfTypeCase;
      for (i = (arrayOfTypeCase = values()).length, b = 0; b < i; ){ 
        TypeCase t = arrayOfTypeCase[b];
        if (t.getProtocole() == c){
          return t;            
        }b++;
      }if (isPlayer(c)) return NOPE; 
        return NEUTRE;
    } 
    int rand = (int)(Math.random() * 6.0D);
    if (rand == 2) {
      rand = (int)(Math.random() * 3.0D);
      if (rand == 0){
      return PLANTE1; 
      }if (rand == 1){
        return PLANTE2; 
      } 
      return PLANTE3;
    } 
    return NEUTRE;
   }
 
 
   
   public BufferedImage getBackground() {
   return this.background;
   }
   public boolean getCollision() {
    return this.collision;
   }
   public char getProtocole() {
    return this.protocole;
   }
   public int getHeight() {
    return this.height;
   }
   public int getWidth() {
    return this.width;
   }
 
   
   public static boolean isPlayer(char c) {
    if (c >= '0' && c <= '9') return true; 
    return false;
   }
 }