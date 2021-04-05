package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(58001);
             Socket clientSocket = server.accept();
             BufferedReader inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter outBuf = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            String clientMessage = inBuf.readLine();
            System.out.println("Message from client: " + clientMessage + "\n" +
                    "port number is: " + clientSocket.getPort());
            // Ответим клиенту, что его сообщеие получено:
            outBuf.write("From server:    confirm you wrote '" + clientMessage + "' from port: " + clientSocket.getPort() + "\n");
            outBuf.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
