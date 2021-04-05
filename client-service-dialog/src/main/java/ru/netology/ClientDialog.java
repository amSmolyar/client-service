package ru.netology;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientDialog {
    private static BufferedReader inBuf;
    private static Scanner scan;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("netology.homework", 58003);
             BufferedWriter outBuf = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            scan = new Scanner(System.in);
            try {
                inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while (!readServerMessage()) {
                    readAll();
                    String userData = scan.nextLine();
                    outBuf.write(userData + "\n");
                    outBuf.flush();
                }
                readAll();
            } finally {
                inBuf.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            scan.close();
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
