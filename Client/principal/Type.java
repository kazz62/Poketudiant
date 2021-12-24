 package principal;
 
 import java.awt.image.BufferedImage;
 
 public enum Type
 {
  NOISY("Noisy", Ressources.iconNoisy, Ressources.backgroundNoisy, Ressources.backgroundNoisyKO),
  LAZY("Lazy", Ressources.iconLazy, Ressources.backgroundLazy, Ressources.backgroundLazyKO),
  MOTIVATED("Motivated", Ressources.iconMotivated, Ressources.backgroundMotivated, Ressources.backgroundMotivatedKO),
  TEACHER("Teacher", Ressources.iconTeacher, Ressources.backgroundTeacher, Ressources.backgroundTeacherKO);
   
   private String name;
   
   private BufferedImage icon;
   private BufferedImage background;
   private BufferedImage backgroundKO;
   
   Type(String name, BufferedImage icon, BufferedImage background, BufferedImage backgroundKO) {
    this.name = name;
    this.icon = icon;
    this.background = background;
    this.backgroundKO = backgroundKO;
   }
 
   
   public String getName() {
    return this.name;
   }
   
   public BufferedImage getIcon() {
    return this.icon;
   }
   
   public BufferedImage getBackground() {
    return this.background;
   }
   
   public BufferedImage getBackgroundKO() {
    return this.backgroundKO;
   }
 }