package katsita.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void starrConnection(String inn, int port) throws IOException {
        clientSocket = new Socket(inn, port);

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String message) throws IOException {
        out.println(message);
        String serverMessage = in.readLine();
        return serverMessage;
    }

    public void close() throws IOException {
        clientSocket.close();
        in.close();
        out.close();
    }
}
