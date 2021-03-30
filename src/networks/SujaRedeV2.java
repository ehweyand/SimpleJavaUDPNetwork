/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SujaRedeV2 {

    public static DatagramSocket socket = null;
    public static int porta = 8000;
    public static InetAddress address = null;

    public static void enviaMensagem(String s) {
        try {
            byte[] buffer = s.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, porta);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {

            socket = new DatagramSocket();
            address = InetAddress.getByName("192.168.0.9");
            //socket.setBroadcast(true);

            int i = 0;

            while (true) {
                String mensagem = "o boi " + (i++);
                enviaMensagem(mensagem);

                Thread.sleep(1000);
            }

            //socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}