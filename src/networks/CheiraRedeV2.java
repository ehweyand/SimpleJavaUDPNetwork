/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author wolfi
 */
public class CheiraRedeV2 {

    public static void main(String[] args) {

        try {
            int porta = 8000;

            byte[] buffer = new byte[1024];

            String enderecoBroadcast = "255.255.255.255";
            InetAddress address = InetAddress.getByName(enderecoBroadcast);

            DatagramSocket socket = new DatagramSocket(porta);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                // aqui espera o pacote
                socket.receive(packet);

                InetAddress client = packet.getAddress();
                int clientPort = packet.getPort();
                
                String efetiva = new String(buffer, 0, packet.getLength());
                
                System.out.println(efetiva);
                
                for (int i = 0; i < efetiva.length(); i++) {
                    System.out.println(((int) efetiva.charAt(i)) + " - " + efetiva.charAt(i));
                }
                
                if(efetiva.equals("A vaca desmaiou")){
                    System.out.println("VIVA A VACA TA VIVA ajustada corretamente");
                }

                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
