package ru.netology;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientDialog {
    private static Socket clientSocket;
    private static BufferedWriter outBuf;
    private static BufferedReader inBuf;
    private static Scanner scan;

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 58003);
                inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outBuf = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                scan = new Scanner(System.in);

                while (!readServerMessage()) {
                    readAll();
                    String userData = scan.nextLine();
                    outBuf.write(userData + "\n");
                    outBuf.flush();
                }
                readAll();
            } finally {
                clientSocket.close();
                inBuf.close();
                outBuf.close();
                scan.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean readServerMessage() throws Exception {
        String serverMessage;
        boolean finish = false;
        serverMessage = inBuf.readLine();
        System.out.println(serverMessage);
        if (serverMessage.startsWith("You are registered successfully") || serverMessage.startsWith("Maybe next time")) {
            finish = true;
        }
        return finish;
    }

    private static void readAll() throws Exception {
        while (inBuf.ready()) {
            readServerMessage();
        }
    }
}
