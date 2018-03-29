package edu.fgu.dclab;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int PORT = 4004;

    public Server() {
        try (
            ServerSocket serverSocket = new ServerSocket(PORT);
        ) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                new Thread(new Servant(clientSocket)).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Server.java
