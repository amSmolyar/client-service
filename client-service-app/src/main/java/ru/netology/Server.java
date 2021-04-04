package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;
    private static Socket clientSocket;
    private static BufferedReader inBuf;
    private static BufferedWriter outBuf;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(58001);
            clientSocket = server.accept();
            try {
                inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outBuf = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String clientMessage = inBuf.readLine();
                System.out.println("Message from client: " + clientMessage + "\n" +
                        "port number is: " + clientSocket.getPort());
                // Ответим клиенту, что его сообщеие получено:
                outBuf.write("From server:    confirm you wrote: '" + clientMessage + "' from port: " + clientSocket.getPort() + "\n");
                outBuf.flush();
            } finally {
                clientSocket.close();
                inBuf.close();
                outBuf.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
