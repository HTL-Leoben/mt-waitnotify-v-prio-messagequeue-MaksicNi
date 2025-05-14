package at.htlle.pos4.prio_messagequeue;

public class Consumer extends Thread {
    private final PriorityMessageQueue queue;
    private final String name;

    public Consumer(String name, PriorityMessageQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message msg = queue.receiveMessage();
                System.out.println(name + " hat empfangen: " + msg);
                Thread.sleep((int)(Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

