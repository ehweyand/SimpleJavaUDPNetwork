/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CheiraRede {

    public static void main(String[] args) {

        try {
            int porta = 8000;

            byte[] buffer = new byte[1024];

            String enderecoBroadcast = "255.255.255.255";
            InetAddress address = InetAddress.getByName(enderecoBroadcast);

            DatagramSocket socket = new DatagramSocket(porta);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            InetAddress client = packet.getAddress();
            int clientPort = packet.getPort();
            System.out.println("Recebido " + new String(buffer) + " do cliente " + client);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
