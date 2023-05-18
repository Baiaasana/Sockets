package katsita.group_chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class ReadThread implements Runnable {
    private static final int MAX_LENGTH = 1000;
    private MulticastSocket multicastSocket;
    private InetAddress group;
    private int port;

    public ReadThread(MulticastSocket multicastSocket, int port, InetAddress group) {
        this.group = group;
        this.multicastSocket = multicastSocket;
        this.port = port;
    }

    @Override
    public void run() {
        while (GroupChat.finish) {
            byte[] buffer = new byte[MAX_LENGTH];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, group, port);
            try {
                multicastSocket.receive(datagramPacket);
                String message = new String(buffer, 0, datagramPacket.getLength(), StandardCharsets.UTF_8);
                System.out.println(message);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}