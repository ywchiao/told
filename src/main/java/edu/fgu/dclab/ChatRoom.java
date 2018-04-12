package edu.fgu.dclab;

import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ChatRoom implements Runnable {
    private BlockingQueue<String> messageQueue = new LinkedBlockingDeque<>();;
    private Vector<Servant> servants = new Vector<>();

    public void enter(Socket client) {
        Servant servant = new Servant(client);

        servant.setMessageQueue(this.messageQueue);

        servant.write("Welcome visitor!" + "\n");

        servants.add(servant);

        new Thread(servant).start();
    } // enter()

    @Override
    public void run() {
        try {
            while (true) {
                String message = this.messageQueue.take();

                for (Servant servant : servants) {
                    servant.write(message);
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// ChatRoom.java