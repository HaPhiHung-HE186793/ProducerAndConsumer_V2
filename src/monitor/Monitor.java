package monitor;

public class Monitor {
    private static long startTime = 0;
    private static long endTime = 0;
    private static int processedCount = 0;

    public static synchronized void start() {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }
    }

    public static synchronized void processed() {
        processedCount++;
        if (processedCount == 1000000) {
            endTime = System.currentTimeMillis();
            System.out.println("✅ All customers processed.");
            System.out.println("🕒 Total time: " + (endTime - startTime) + " ms");;
        }
    }
}
