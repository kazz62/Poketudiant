 package principal;
 
 import java.awt.image.BufferedImage;
 
 public enum Variety
 {
  PARLFOR("Parlfor", Type.NOISY, Ressources.parlfor.getSubimage(0, 0, 22, 35), Ressources.parlfor.getSubimage(22, 0, 22, 35), Ressources.parlfor.getSubimage(44, 0, 22, 20)),
  RIGOLAMOR("Rigolamor", Type.NOISY, Ressources.rigolamor.getSubimage(0, 0, 22, 35), Ressources.rigolamor.getSubimage(22, 0, 22, 35), Ressources.rigolamor.getSubimage(44, 0, 22, 20)),
  ISMAR("Ismar", Type.NOISY, Ressources.ismar.getSubimage(0, 0, 22, 35), Ressources.ismar.getSubimage(22, 0, 22, 35), Ressources.ismar.getSubimage(44, 0, 22, 20)),
  PROCRASTINO("Procrastino", Type.LAZY, Ressources.procrastino.getSubimage(0, 0, 22, 35), Ressources.procrastino.getSubimage(22, 0, 22, 35), Ressources.procrastino.getSubimage(44, 0, 22, 20)),
  ALABOURRE("Alabourre", Type.LAZY, Ressources.alabourre.getSubimage(0, 0, 22, 35), Ressources.alabourre.getSubimage(22, 0, 22, 35), Ressources.alabourre.getSubimage(44, 0, 22, 20)),
  NUIDEBOU("Nuidebou", Type.LAZY, Ressources.nuidebou.getSubimage(0, 0, 22, 35), Ressources.nuidebou.getSubimage(22, 0, 22, 35), Ressources.nuidebou.getSubimage(44, 0, 22, 20)),
  COUCHTAR("Couchtar", Type.LAZY, Ressources.couchtar.getSubimage(0, 0, 22, 35), Ressources.couchtar.getSubimage(22, 0, 22, 35), Ressources.couchtar.getSubimage(44, 0, 22, 20)),
  BUCHAFON("Buchafon", Type.MOTIVATED, Ressources.buchafon.getSubimage(0, 0, 22, 35), Ressources.buchafon.getSubimage(22, 0, 22, 35), Ressources.buchafon.getSubimage(44, 0, 22, 20)),
  PROMOMAJOR("Promomajor", Type.MOTIVATED, Ressources.promomajor.getSubimage(0, 0, 22, 35), Ressources.promomajor.getSubimage(22, 0, 22, 35), Ressources.promomajor.getSubimage(44, 0, 22, 20)),
  BELMENTION("Belmention", Type.MOTIVATED, Ressources.belmention.getSubimage(0, 0, 22, 35), Ressources.belmention.getSubimage(22, 0, 22, 35), Ressources.belmention.getSubimage(44, 0, 22, 20)),
  ENSEIGNANT_DRESSEUR("Enseignant-dresseur", Type.TEACHER, Ressources.enseignantDresseur.getSubimage(0, 0, 22, 35), Ressources.enseignantDresseur.getSubimage(22, 0, 22, 35), Ressources.enseignantDresseur.getSubimage(44, 0, 22, 20));
   
   private String name;
   
   private Type type;
   private BufferedImage face;
   private BufferedImage dos;
   private BufferedImage portrait;
   
   Variety(String name, Type type, BufferedImage face, BufferedImage dos, BufferedImage portrait) {
    this.name = name;
    this.type = type;
    this.face = face;
    this.dos = dos;
    this.portrait = portrait;
   }
 
   
   public String getName() {
    return this.name;
   }
   
   public Type getType() {
    return this.type;
   }
   
   public BufferedImage getDos() {
    return this.dos;
   }
   
   public BufferedImage getFace() {
     return this.face;
   }
   
   public BufferedImage getPortrait() {
    return this.portrait;
   }
   public static Variety getVariety(String name) {
     byte b;
     int i;
     Variety[] arrayOfVariety;
    for (i = (arrayOfVariety = values()).length, b = 0; b < i; ) { Variety v = arrayOfVariety[b];
      if (v.getName().equalsIgnoreCase(name)) return v;  b++; }
    return null;
   }
 }