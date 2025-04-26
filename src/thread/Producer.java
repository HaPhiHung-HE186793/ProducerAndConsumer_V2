    package thread;

    import model.Message;
    import monitor.Monitor;
    import queue.MessageQueue;

    import java.util.concurrent.atomic.AtomicInteger;

    public class Producer implements Runnable {
        private final MessageQueue<Message> queue;
        private static final AtomicInteger counter = new AtomicInteger(1); // Thread-safe counter

        public Producer(MessageQueue<Message> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int customerId = counter.getAndIncrement();
                    if (customerId > 1000000) break;

                    if (customerId == 1) {
                        Monitor.start();
                    }

                    Message message = new Message("Customer " + customerId, System.currentTimeMillis());
                    queue.put(message);
                    System.out.println("Produced: " + message + " | Queue size = " + queue.size());

                    Thread.sleep(0);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
