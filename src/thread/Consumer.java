package thread;

import model.Message;
import monitor.Monitor;
import queue.MessageQueue;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private final MessageQueue<Message> queue;
    private final int consumerId;
    private static final AtomicInteger counter = new AtomicInteger(1);

    public Consumer(MessageQueue<Message> queue, int consumerId) {
        this.queue = queue;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int customerId = counter.getAndIncrement();
                if (customerId > 1000000) break;
                Message message = queue.take();
                System.out.println("Consumer-" + consumerId + " consumed: " + message);
                Thread.sleep(ThreadLocalRandom.current().nextInt(0, 5));
                Monitor.processed(); // tăng biến đếm và đo thời gian
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
