package katsita;

import katsita.sockets.ClientSocket;
import katsita.sockets.ServerSocket;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {


        // client socket
        ClientSocket client = new ClientSocket();
        try {
            client.starrConnection("192.168.21.127", 2000);
            System.out.println(client.sendMessage("Hello Server"));
        }catch (IOException e1){
            System.out.println(e1.getMessage());
        }finally {
            try {
                client.close();
            }catch (IOException e2){
                System.out.println(e2.getMessage());
            }
        }

        // server socket
        ServerSocket server = new ServerSocket();
        try {
            server.start(2000);
        }catch (IOException e1){
            System.out.println(e1.getMessage());
        }finally {
            try {
                server.close();
            }catch (IOException e2){
                System.out.println(e2.getMessage());
            }
        }
    }
}