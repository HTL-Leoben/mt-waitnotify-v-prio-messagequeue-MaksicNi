package at.htlle.pos4.prio_messagequeue;

public class Producer extends Thread {
    private final PriorityMessageQueue queue;
    private final String name;

    public Producer(String name, PriorityMessageQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int count = 1;
            while (true) {
                boolean isPriority = Math.random() < 0.3;
                String content = name + " Nachricht " + count++;
                Message msg = new Message(isPriority, content);
                queue.sendMessage(msg);
                Thread.sleep((int)(Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

