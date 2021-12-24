 package principal;
 
 import java.awt.image.BufferedImage;
 
 public enum Attaque
 {
  BAVARDAGE("Bavardage", Type.NOISY, 10, Ressources.sortBavardage),
  GROBOUCAN("Groboucan", Type.NOISY, 15, Ressources.sortGroboucan),
  MEGAPHONE_VOCAL("Megaphone-vocal", Type.NOISY, 20, Ressources.sortMegaphone),
  BAILLEMENT("Baillement", Type.LAZY, 10, Ressources.sortBaillement),
  PTI_SOMME("Pti'somme", Type.LAZY, 15, Ressources.sortPtitSomme),
  SUPERDODO("Superdodo", Type.LAZY, 20, Ressources.sortSuperdodo),
  OBOULO("Oboulo", Type.MOTIVATED, 10, Ressources.sortOBoulo),
  EXO_MAISON("Exo-maison", Type.MOTIVATED, 15, Ressources.sortExoMaison),
  MAX_REVIZ("Max-reviz", Type.MOTIVATED, 20, Ressources.sortMaxReviz),
  TIT_QUESTION("Tit'question", Type.TEACHER, 10, Ressources.sortTitQuestion),
  POSER_COLLE("Poser-colle", Type.TEACHER, 15, Ressources.sortPosercolle),
  FATAL_INTERRO("Fatal-Interro", Type.TEACHER, 20, Ressources.sortFatalinterro);
   
   private String name;
   
   private Type type;
   private int puissance;
   private BufferedImage imageBouton;
   
   Attaque(String name, Type type, int puissance, BufferedImage b) {
    this.name = name;
    this.type = type;
    this.puissance = puissance;
    this.imageBouton = b;
   }
 
   
   public String getName() {
    return this.name;
   }
   
   public int getPuissance() {
    return this.puissance;
   }
   
   public Type getType() {
    return this.type;
   }
   
   public BufferedImage getImageBouton() {
    return this.imageBouton;
   } public static Attaque getAttaque(String name) {
     byte b;
     int i;
     Attaque[] arrayOfAttaque;
      for (i = (arrayOfAttaque = values()).length, b = 0; b < i; ) { Attaque a = arrayOfAttaque[b];
       
        if (a.getName().equalsIgnoreCase(name)) return a;  b++; }
     
      return null;
   }
 }