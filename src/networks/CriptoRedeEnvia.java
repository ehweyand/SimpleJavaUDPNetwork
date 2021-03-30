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
 * @author evand
 */
public class CriptoRedeEnvia {

    public static final int START_CHAR_CODE = 42;
    public static final int END_CHAR_CODE = 122;

    // Informações de conexão
    public static int porta = 8000;
    public static DatagramSocket socket = null;
    public static InetAddress address = null;
    public static int pMaq = 0;
    public static String[] ips = {"192.168.0.9"};

    public static void main(String[] args) {

        //Tenta a conexão com a ou as máquinas
        try {
            socket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Poderia ter mais máquinas
        //Dentro de um laço
        //Quando mandava para última máquina
        //Mandava o próximo pra primeira
        for (int i = 118; i < END_CHAR_CODE; i++) {
            for (int j = START_CHAR_CODE; j < END_CHAR_CODE; j++) {
                for (int k = START_CHAR_CODE; k < END_CHAR_CODE; k++) {
                    for (int l = START_CHAR_CODE; l < END_CHAR_CODE; l++) {
                        enviaUDP("" + (char) i + (char) j + (char) k + (char) l);
                    }
                }
            }
        }

    }

    private static void enviaUDP(String msg) {
        try {
            byte[] buffer = msg.getBytes();

            address = InetAddress.getByName(ips[pMaq]);

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, porta);
            socket.send(packet);
           
            pMaq++;
           
            if (pMaq >= ips.length) {
                pMaq = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
