package edu.fgu.dclab;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ChatRoom implements Runnable {
    private BlockingQueue<String> messageQueue = new LinkedBlockingDeque<>();;
    private Vector<Servant> servants = new Vector<>();

    public void enter(Servant servant) {
        servant.setMessageQueue(this.messageQueue);
        servants.add(servant);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String line = this.messageQueue.take();

                for (Servant servant : servants) {
                    servant.write(line);
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// ChatRoom.java