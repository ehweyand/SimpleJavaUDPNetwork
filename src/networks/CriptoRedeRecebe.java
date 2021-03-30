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
public class CriptoRedeRecebe {

    static final String REGEXP_WORDS = "[a-zA-Z ]+";

    public static void main(String[] args) {

        int[] encrypted = {55, 65, 21, 80, 21, 0, 67, 92, 25, 19, 17, 84, 3, 
            65, 7, 84, 86, 5, 12, 67, 86, 5, 6, 17, 20, 0, 17, 67, 31, 6, 2};
        int expectedCheckSum = 2789;
        int encryptedLength = encrypted.length;

        try {
            int porta = 8000;

            byte[] buffer = new byte[1024];

            String enderecoBroadcast = "255.255.255.255";
            InetAddress address = InetAddress.getByName(enderecoBroadcast);

            DatagramSocket socket = new DatagramSocket(porta);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            //Para ficar recebendo pacotes sem previsão de parada
            // Quebra o laço apenas quando 
            while (true) {
                // aqui espera o pacote
                socket.receive(packet);

                InetAddress client = packet.getAddress();
                int clientPort = packet.getPort();

                // Chave
                String efetiva = new String(buffer, 0, packet.getLength());

                //Tentar decryptar
                tryToDecryptMsg(efetiva, encrypted, expectedCheckSum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void tryToDecryptMsg(String key, int[] msg, int checkSum) {
        int x = 0;
        int originalSum = 0;

        String temp = "";
        for (int i = 0; i < msg.length; i++) {
            temp += (char) (msg[i] ^ key.charAt(x));
            x++;

            originalSum += (char) temp.charAt(i);
            // Maior que 3, pois a chave tem 4 caracteres
            if (x > 3) {
                x = 0;
            }
        }

        if (originalSum == checkSum && checkStringChars(temp)) {
            System.out.println(temp);
            System.out.println(originalSum);
            System.out.println("Chave utilizada: " + key);
        }

    }

    private static boolean checkStringChars(String s) {
        return s.matches(REGEXP_WORDS);
    }
}
