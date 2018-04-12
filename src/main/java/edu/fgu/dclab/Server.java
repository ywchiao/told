package edu.fgu.dclab;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int PORT = 4004;

    private ChatRoom chatRoom;

    public Server() {
        chatRoom = new ChatRoom();

        new Thread(chatRoom).start();

        try (
            ServerSocket serverSocket = new ServerSocket(PORT);
        ) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                Servant servant = new Servant(clientSocket);

                chatRoom.enter(servant);

                new Thread(servant).start();
//                new Thread(new Servant(clientSocket)).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Server.java
