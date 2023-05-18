package katsita.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocket {

    private java.net.ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public void start(int port) throws IOException {

        serverSocket = new java.net.ServerSocket(port);
        clientSocket = serverSocket.accept();

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String message = in.readLine();
        if(message.equals("Hello Server")){
            out.println("Hello Client");
        }else {
            System.out.println("Error!");
        }
    }

    public void close() throws IOException {
        serverSocket.close();
        clientSocket.close();
        in.close();
        out.close();
    }
}
