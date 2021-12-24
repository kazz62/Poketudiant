 package modele;
 
 import java.io.IOException;
 import java.net.DatagramPacket;
 import java.net.DatagramSocket;
 import java.net.InetAddress;
 import java.net.SocketException;
 import java.util.ArrayList;
 import java.util.Observable;
 
 public class BroadcastModele
   extends Observable
 {
  private ArrayList<DatagramPacket> listServer = new ArrayList<>();
   
   private boolean actualisation = true;
 
   
   public void actualiser() {
    Thread t = new Thread(new Runnable()
         {
           public void run(){
            boolean boucle = true;   
             try {
             BroadcastModele.this.listServer.clear();
             DatagramSocket socket = new DatagramSocket();
             String data = "looking for poketudiant servers\n";
             socket.setBroadcast(true);
             socket.setSoTimeout(1000);
             InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
             DatagramPacket packet = new DatagramPacket(data.getBytes(), (data.getBytes()).length, broadcastAddress, 9000);
             socket.send(packet);
             while (boucle) {
              byte[] buf = new byte[1500];
              packet = new DatagramPacket(buf, buf.length);
              socket.receive(packet);
              String received = new String(packet.getData(), 0, packet.getLength());
              if (received.equalsIgnoreCase("i'm a poketudiant server\n")) {        
                 BroadcastModele.this.listServer.add(packet);
                 BroadcastModele.this.setChanged("new server");
                 BroadcastModele.this.notifyObservers();
                 } 
                System.out.println(String.valueOf(received) + " - " + packet.getAddress().getHostAddress());
               }     
             socket.close();
             }
             catch (SocketException e) {
             boucle = false;
             } catch (IOException e) {
              boucle = false;
             } 
             BroadcastModele.this.setChanged("actualisation");
             BroadcastModele.this.notifyObservers();
           }
         });
     t.start();
   }
 
   
   public DatagramPacket getLastPacket() {
    if (this.listServer.size() > 0) return this.listServer.get(this.listServer.size() - 1); 
    return null;
   }
 
   
   public void setChanged(String s) {
    setChanged();
    notifyObservers(s);
   }
  
   public boolean getActualisation() {
    return this.actualisation;
   }
 
   public void setActualisation(boolean b) {
     this.actualisation = b;
   }
   
   public ArrayList<DatagramPacket> getListServer() {
    return this.listServer;
   }
 }