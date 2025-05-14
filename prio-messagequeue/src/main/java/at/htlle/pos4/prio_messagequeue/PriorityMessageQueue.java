package at.htlle.pos4.prio_messagequeue;

import java.util.LinkedList;
import java.util.Queue;

public class PriorityMessageQueue {
    private final Queue<Message> priorityQueue = new LinkedList<>();
    private final Queue<Message> normalQueue = new LinkedList<>();
    private final int capacity;

    public PriorityMessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void sendMessage(Message msg) throws InterruptedException {
        while (size() >= capacity) {
            wait();
        }
        if (msg.isPriority()) {
            priorityQueue.offer(msg);
        } else {
            normalQueue.offer(msg);
        }
        System.out.println("Produziert: " + msg);
        notifyAll();
    }

    public synchronized Message receiveMessage() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        Message msg = !priorityQueue.isEmpty() ? priorityQueue.poll() : normalQueue.poll();
        System.out.println("Konsumiert: " + msg);
        notifyAll();
        return msg;
    }

    private int size() {
        return priorityQueue.size() + normalQueue.size();
    }

    private boolean isEmpty() {
        return priorityQueue.isEmpty() && normalQueue.isEmpty();
    }
}

