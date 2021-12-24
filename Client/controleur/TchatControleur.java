 package controleur;
 
 import java.awt.event.FocusEvent;
 import java.awt.event.FocusListener;
 import java.awt.event.KeyAdapter;
 import java.awt.event.KeyEvent;
 import java.util.Observer;
 import modele.TchatModele;
 import principal.Main;
 import vue.TchatVue;
 
 public class TchatControleur
 {
   private TchatModele tchatModele;
   private TchatVue tchatVue;
   private FocusListener focusListener;
   private KeyAdapter keyAdapterInEntry;
   private KeyAdapter keyAdapterOutEntry;
   
   public TchatControleur(TchatModele tchatModele, TchatVue tchatVue) {
    this.tchatModele = tchatModele;
    this.tchatVue = tchatVue;
    this.tchatModele.addObserver((Observer)tchatVue);
    initListeners();
   }
 
   
   private void initListeners() {
    this.focusListener = new FocusListener()
       {
         public void focusLost(FocusEvent e)
         {
          TchatControleur.this.tchatVue.lostFocus();
         }
 
         
         public void focusGained(FocusEvent e) {
          TchatControleur.this.tchatVue.gainFocus();
         }
       };
     
    this.keyAdapterInEntry = new KeyAdapter()
       {
         public void keyPressed(KeyEvent e)
         {
           super.keyPressed(e);
          if (e.getKeyCode() == 10) {
             
            String text = TchatControleur.this.tchatVue.getEnteredText();
            if (!text.isEmpty())
             {
              Main.socket.sendTo("send message " + text + "\n");
             }
            TchatControleur.this.tchatVue.getEntryMessage().transferFocus();
           }
            else if (e.getKeyCode() == 27) {
             
            TchatControleur.this.tchatVue.getEntryMessage().transferFocus();
           } 
         }
       };
     
    this.keyAdapterOutEntry = new KeyAdapter()
       {
         public void keyPressed(KeyEvent e)
         {
          super.keyPressed(e);
          if (e.getKeyCode() == 84)
           {
            TchatControleur.this.tchatVue.getEntryMessage().requestFocusInWindow();
           }
         }
       };
     
    this.tchatVue.getEntryMessage().addKeyListener(this.keyAdapterInEntry);
    this.tchatVue.getEntryMessage().addFocusListener(this.focusListener);
   }
   
   public KeyAdapter getKeyAdapterOutEntry() {
    return this.keyAdapterOutEntry;
   }
   
   public TchatModele getTchatModele() {
     return this.tchatModele;
   }
 }