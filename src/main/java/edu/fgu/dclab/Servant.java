package edu.fgu.dclab;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Servant implements Runnable {
    Socket socket = null;
    BlockingQueue<String> messageQueue = null;
    PrintWriter out = null;

    public Servant(Socket socket) {
        this.socket = socket;
    }

    public void setMessageQueue(BlockingQueue queue) {
        this.messageQueue = queue;
    }

    public void write(String msg) {
        this.out.println(msg);
    }

    @Override
    public void run() {
        String line;

        try (
            BufferedReader in = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
            );

            //PrintWriter out = new PrintWriter(
        ) {
            this.out = new PrintWriter(
                this.socket.getOutputStream(),
                true
            );

            this.out.println("Welcome visitor!" + "\n");

            while ((line = in.readLine()) != null) {
                try {
                    messageQueue.put(line);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println("NetReader I/O Exc eption");
        }
    }
}

// Servant.java