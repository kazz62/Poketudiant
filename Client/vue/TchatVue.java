 package vue;
 
 import java.awt.BorderLayout;
 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Dimension;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.util.Observable;
 import java.util.Observer;
 import javax.swing.BorderFactory;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.JTextArea;
 import javax.swing.JTextField;
 import javax.swing.Timer;
 import javax.swing.border.Border;
 
 public class TchatVue
   extends JPanel
   implements Observer {
   private static final Color NOTFOCUSED = new Color(51, 51, 51, 60);
   private static final Color TRANSPARENT = new Color(0, 0, 0, 0);
   private static final Font POLICE = new Font("Arial", 1, 15);
   private static final Color MSGCOLOR = new Color(0, 204, 255);
   
   private JTextArea tchatPanel;
   private JTextField entryMessage;
   private JScrollPane scrollPane;
   private Timer fadeTimer;
   private Timer appearTimer;
   
   public TchatVue() {
     super(new BorderLayout(0, 5));
     init();
     initTimer();
   }
 
 
   
   private void init() {
     this.tchatPanel = new JTextArea(1, 1)
       {
         protected void paintComponent(Graphics g) {
           g.setColor(getBackground());
           g.fillRect(0, 0, getWidth(), getHeight());
           super.paintComponent(g);
         }
       };
     this.tchatPanel.setEditable(false);
     this.tchatPanel.setBorder((Border)null);
     this.tchatPanel.setFocusable(false);
     this.tchatPanel.setOpaque(false);
     this.tchatPanel.setFont(POLICE);
     this.tchatPanel.setForeground(MSGCOLOR);
     this.tchatPanel.setBackground(TRANSPARENT);
     this.tchatPanel.setWrapStyleWord(true);
     this.tchatPanel.setLineWrap(true);
     
     this.entryMessage = new JTextField()
       {
         protected void paintComponent(Graphics g) {
           g.setColor(getBackground());
           g.fillRect(0, 0, getWidth(), getHeight());
           super.paintComponent(g);
         }
       };
     this.entryMessage.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
     this.entryMessage.setCaretColor(MSGCOLOR);
     this.entryMessage.setOpaque(false);
     this.entryMessage.setPreferredSize(new Dimension(300, 35));
     this.entryMessage.setFont(POLICE);
     this.entryMessage.setForeground(Color.WHITE);
     this.entryMessage.setText("Appuyez sur T pour écrire");
     this.entryMessage.setBackground(NOTFOCUSED);
     
     this.scrollPane = new JScrollPane(this.tchatPanel)
       {
         protected void paintComponent(Graphics g) {
           g.setColor(getBackground());
           g.fillRect(0, 0, getWidth(), getHeight());
           super.paintComponent(g);
         }
       };
     this.scrollPane.getViewport().setBackground(TRANSPARENT);
     this.scrollPane.setBackground(TRANSPARENT);
     this.scrollPane.setBorder((Border)null);
     this.scrollPane.setViewportBorder((Border)null);
     this.scrollPane.setOpaque(false);
     this.scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
     add(this.scrollPane, "Center");
     add(this.entryMessage, "South");
     setBackground(TRANSPARENT);
     setFocusable(false);
     setOpaque(false);
   }
 
   
   private void initTimer() {
     this.fadeTimer = new Timer(10, new ActionListener()
         {
           int i = 0;       
           public void actionPerformed(ActionEvent e) {
            Color lastTchat = TchatVue.this.tchatPanel.getBackground();
             Color nextTchat = new Color(lastTchat.getRed(), lastTchat.getGreen(), lastTchat.getBlue(), lastTchat.getAlpha() - 10);
             Color lastEntry = TchatVue.this.entryMessage.getBackground();
             Color nextEntry = new Color(lastEntry.getRed(), lastEntry.getGreen(), lastEntry.getBlue(), lastEntry.getAlpha() - 4);
             TchatVue.this.entryMessage.setBackground(nextEntry);
             TchatVue.this.tchatPanel.setBackground(nextTchat);
             if (++this.i == 10) {
               ((Timer)e.getSource()).stop();
               this.i = 0;
             } 
             TchatVue.this.repaint();
           }
         });
    this.appearTimer = new Timer(10, new ActionListener()
         {
           int i = 0;
 
           
           public void actionPerformed(ActionEvent e) {
             Color lastTchat = TchatVue.this.tchatPanel.getBackground();
             Color nextTchat = new Color(lastTchat.getRed(), lastTchat.getGreen(), lastTchat.getBlue(), lastTchat.getAlpha() + 10);
             Color lastEntry = TchatVue.this.entryMessage.getBackground();
             Color nextEntry = new Color(lastEntry.getRed(), lastEntry.getGreen(), lastEntry.getBlue(), lastEntry.getAlpha() + 4);
             TchatVue.this.entryMessage.setBackground(nextEntry);
             TchatVue.this.tchatPanel.setBackground(nextTchat);
             if (++this.i == 10) {
               ((Timer)e.getSource()).stop();
               this.i = 0;
             } 
             TchatVue.this.repaint();
           }
         });
   }
   
   public JTextField getEntryMessage() {
     return this.entryMessage;
   }
 
   
   public String getEnteredText() {
     return this.entryMessage.getText();
   }
 
   
   public void gainFocus() {
     this.entryMessage.setForeground(MSGCOLOR);
     this.entryMessage.setText("");
     this.appearTimer.start();
     repaint();
   }
 
   
   public void lostFocus() {
     this.entryMessage.setForeground(Color.WHITE);
     this.entryMessage.setText("Appuyez sur T pour écrire");
     this.fadeTimer.start();
     repaint();
   }
 
 
   
   public void update(Observable o, Object arg) {
     if (o instanceof modele.TchatModele) {
       String msg = (String)arg;
       this.tchatPanel.append(String.valueOf(msg) + "\n");
       this.tchatPanel.setCaretPosition(this.tchatPanel.getDocument().getLength());
       repaint();
     } 
   }
 
   
   protected void paintComponent(Graphics g) {
     g.setColor(getBackground());
     g.fillRect(0, 0, getWidth(), getHeight());
     super.paintComponent(g);
   }
 }