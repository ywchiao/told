package edu.fgu.dclab;

import java.io.*;
import java.net.Socket;

public class Servant implements Runnable {
    Socket socket = null;

    public Servant(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String line;

        try (
            BufferedReader in = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
            );

            PrintWriter out = new PrintWriter(
                this.socket.getOutputStream(),
                true
            );
        ) {
            out.println("Welcome visitor!" + "\n");

            while ((line = in.readLine()) != null) {
                out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println("NetReader I/O Exc eption");
        }
    }
}

// Servant.java
