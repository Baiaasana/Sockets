package katsita.group_chat;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class GroupChat {

    public static final String TERMINATE = "Leave Group";
    public static String name;
    public static volatile Boolean finish = false;

    public static void main(String[] args) {

        try {
            InetAddress group = InetAddress.getByName("224.0.0.0");
            int port = 120;
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();
            MulticastSocket multicastSocket = new MulticastSocket(port);
            multicastSocket.setTimeToLive(0);
            multicastSocket.joinGroup(group);

            ReadThread readThread = new ReadThread(multicastSocket, port, group);
            Thread thread = new Thread(readThread);
            thread.start();

            while (true) {
                String message = scanner.nextLine();
                if (message.equals(TERMINATE)) {
                    finish = true;
                    multicastSocket.leaveGroup(group);
                    multicastSocket.close();
                    break;
                }
                message = "BAIA".concat(message);
                byte[] buffer = message.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, group, port);
                multicastSocket.send(datagramPacket);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
