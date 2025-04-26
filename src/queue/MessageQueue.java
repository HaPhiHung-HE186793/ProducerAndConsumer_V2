package queue;

import model.Message;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue<T> {

        private static final int CAPACITY = 1000000 ;
        private final LinkedList<T> queue = new LinkedList<>();

        public synchronized void put(T item) throws InterruptedException {
            while (queue.size() == CAPACITY) {
                System.out.println("Queue is full");
                wait();
            }
            queue.addLast(item);
            notifyAll();
        }

        public synchronized T take() throws InterruptedException {
            while (queue.isEmpty()) {
                System.out.println("Queue is empty");
                wait();
            }
            T item = queue.removeFirst();
            notifyAll();
            return item;
        }

        public synchronized int size() {
            return queue.size();
        }
}
