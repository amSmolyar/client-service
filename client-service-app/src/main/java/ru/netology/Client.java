package ru.netology;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Scanner scan;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 58001);
             BufferedReader inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter outBuf = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            scan = new Scanner(System.in);
            System.out.println("Введите ваше сообщение:");
            String userData = scan.nextLine();

            outBuf.write(userData + "\n");
            outBuf.flush();

            String serverAnswer = inBuf.readLine();
            System.out.println(serverAnswer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            scan.close();
        }
    }
}
