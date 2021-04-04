package ru.netology;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket clientSocket;
    private static BufferedWriter outBuf;
    private static BufferedReader inBuf;
    private static Scanner scan;

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 58001);
                inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outBuf = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                scan = new Scanner(System.in);
                System.out.println("Введите ваше сообщение:");
                String userData = scan.nextLine();

                outBuf.write(userData + "\n");
                outBuf.flush();

                String serverAnswer = inBuf.readLine();
                System.out.println(serverAnswer);
            } finally {
                clientSocket.close();
                inBuf.close();
                outBuf.close();
                scan.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
