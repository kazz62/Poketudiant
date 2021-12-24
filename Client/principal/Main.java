 package principal;
 
 import java.io.IOException;

import vue.FenetreVue;
 
 public class Main
 {
  public static SocketClient socket = new SocketClient();
 
   
   public static void main(String[] args) {
    checkParams(args);
    new Ressources();
    FenetreVue fenetreVue = new FenetreVue();
    fenetreVue.init();
   } public static void checkParams(String[] args) {
     byte b;
     int i;
     String[] arrayOfString;
    for (i = (arrayOfString = args).length, b = 0; b < i; ) { String arg = arrayOfString[b];
       b++; }
   }
 }