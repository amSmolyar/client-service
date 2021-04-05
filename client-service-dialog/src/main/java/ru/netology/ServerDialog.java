package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

public class ServerDialog {
    private static BufferedReader inBuf;
    private static BufferedWriter outBuf;
    private static User newUser;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(58003);
             Socket clientSocket = server.accept()) {

            newUser = new User();
            try {
                inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outBuf = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                int cntQuestion = 5;
                while (cntQuestion > 0) {
                    cntQuestion = serverHeader(cntQuestion);
                }

                if (cntQuestion == -1) {
                    outBuf.write("Maybe next time" + "\n");
                    outBuf.flush();
                } else {
                    outBuf.write("You are registered successfully, \n" + newUser.toString());
                    outBuf.flush();
                }
            } finally {
                inBuf.close();
                outBuf.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int serverHeader(int cnt) throws Exception {
        String clientMessage;
        switch (cnt) {
            case 5:
                outBuf.write("Do you want to register? (yes/no)" + "\n");
                outBuf.flush();
                clientMessage = inBuf.readLine();
                if (clientMessage.equals("yes")) {
                    cnt--;
                } else if (clientMessage.equals("no")) {
                    cnt = -1;
                } else {
                    outBuf.write("Your answer is not correct" + "\n");
                    outBuf.flush();
                }
                break;
            case 4:
                outBuf.write("Write your name" + "\n");
                outBuf.flush();
                if ((clientMessage = inBuf.readLine()) != null) {
                    newUser.setName(clientMessage);
                    cnt--;
                }
                break;
            case 3:
                outBuf.write("Write your sex" + "\n");
                outBuf.flush();
                if ((clientMessage = inBuf.readLine()) != null) {
                    newUser.setSex(clientMessage);
                    cnt--;
                }
                break;
            case 2:
                outBuf.write("Write your age" + "\n");
                outBuf.flush();
                if ((clientMessage = inBuf.readLine()) != null) {
                    try {
                        newUser.setAge(Integer.parseInt(clientMessage));
                        cnt--;
                    } catch (Exception e) {
                        outBuf.write("Age format not correct" + "\n");
                        outBuf.flush();
                    }
                }
                break;
            case 1:
                outBuf.write("Write your parol" + "\n");
                outBuf.flush();
                if ((clientMessage = inBuf.readLine()) != null) {
                    newUser.setParol(clientMessage);
                    cnt--;
                }
                break;
            default:
                break;
        }
        return cnt;
    }
}
