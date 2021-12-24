 package modele;
 
 import java.util.Observable;
 import principal.TypeCase;
 
 
 
 public class CaseModele
   extends Observable
 {
   private int x;
   private int y;
   private TypeCase type;
   
   public CaseModele(int x, int y, TypeCase type) {
    this.x = x;
    this.y = y;
    this.type = type;
   }

   public int getX() {
    return this.x;
   }
   
   public int getY() {
    return this.y;
   }

   public TypeCase getType() {
    return this.type;
   }
 
   public void setType(TypeCase t) {
    this.type = t;
    setChanged();
    notifyObservers("type");
   }
 }