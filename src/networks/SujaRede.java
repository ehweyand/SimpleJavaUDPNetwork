/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SujaRede {

    public static void main(String[] args) {
        try {
            int porta = 8000;

            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("192.168.0.9");
            socket.setBroadcast(true);

            while (true) {
                String mensagem = "A vaca morreu";
                byte[] buffer = mensagem.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, porta);
                socket.send(packet);

                Thread.sleep(1000);
            }
            
            //socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}