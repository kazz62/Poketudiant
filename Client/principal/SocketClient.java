 package principal;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.net.Socket;
 import java.net.UnknownHostException;
 
 
 
 
 
 
 
 
 
 
 public class SocketClient
 {
   private boolean connected = false;
  private String lastReadLine = null;
   
   private BufferedWriter out;
   
   public void connectTo(String adresse, int port) {
    if (!this.connected)
     
     { 
       
      try { this.socket = new Socket(adresse, port);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        this.connected = true; }
       catch (UnknownHostException e)
      { e.printStackTrace(); }
      catch (IOException e) { e.printStackTrace(); }
        }
    else { System.out.println("erreur : socket déjà connectée"); }
   
   }
   private BufferedReader in; private Socket socket;
   public void sendTo(String message) {
    if (this.connected) {
 
       
       try { 
        System.out.println("sendTo : '" + message + "'");
        this.out.write(message);
        this.out.flush(); }
      catch (IOException e) { e.printStackTrace(); }
     
     }
   }
   
   public String readLine() {
    if (this.connected) {
       
       try {
         
        this.lastReadLine = this.in.readLine();
        System.out.println("readLine : '" + this.lastReadLine + "'");
       }
      catch (IOException e) {
        return null;
       } 
     }
     
    return this.lastReadLine;
   }
 
   
   public void close() {
    if (this.connected) {
       
       try {
         
        System.out.println("Fermeture socket");
        this.socket.close();
        this.connected = false;
      } catch (IOException e) {
        e.printStackTrace();
       } 
     }
   }
   
   public String getLastReadLine() {
    return this.lastReadLine;
   }
 }