import model.Message;
import queue.MessageQueue;
import thread.Consumer;
import thread.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MessageQueue<Message> queue = new MessageQueue<>();

        ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i <50; i++) {
            executor.submit(new Producer(queue));
        }

        for (int i = 0; i < 50; i++) {
            executor.submit(new Consumer(queue, i + 1));
        }

        executor.shutdown();
    }
}