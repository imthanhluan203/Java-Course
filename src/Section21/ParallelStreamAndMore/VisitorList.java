package Section21.ParallelStreamAndMore;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VisitorList {
    private static final ArrayBlockingQueue<Person> newVisitors =
            new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        Runnable producer = () -> {
            Person visitor = new Person();
            boolean flagAdd = false;
            flagAdd = newVisitors.add(visitor);
            if (flagAdd) {
                System.out.println("Add person successfully");
                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is full, add data failure");
            }
        };
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(producer, 0L, 1L, TimeUnit.SECONDS);
        while (true) {
            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
    }
}
