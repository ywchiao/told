package edu.fgu.dclab;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int PORT = 4004;

    public Server() {
        try (
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket clientSocket = serverSocket.accept();

            PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(),
                true
            );

            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    clientSocket.getInputStream()
                )
            );
        ) {
            String inputLine, outputLine;

            outputLine = "Hello, visitor";
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = inputLine;
                out.println(outputLine);

                if (outputLine.equals("Bye.")) {
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Server.java
