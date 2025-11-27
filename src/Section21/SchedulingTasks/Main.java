package Section21.SchedulingTasks;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM,
                FormatStyle.LONG
        );
        Callable<ZonedDateTime> waitThenDoIt = () -> {
            ZonedDateTime zdt = null;
            try{
                TimeUnit.SECONDS.sleep(2);
                zdt = ZonedDateTime.now();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return zdt;
        };
        var threadPool = Executors.newFixedThreadPool(2);
        List<Callable<ZonedDateTime>> list = Collections.nCopies(4,waitThenDoIt);
        try{
            System.out.println("---> " + ZonedDateTime.now().format(dtf));
            var results = threadPool.invokeAll(list);
            for(var result : results){
                System.out.println(result.get(1,TimeUnit.SECONDS).format(dtf));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

        System.out.println("---> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        var scheduleTask = executor.scheduleAtFixedRate(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(ZonedDateTime.now().format(dtf));
        },1,1,TimeUnit.SECONDS);
        long time = System.currentTimeMillis();
        while(!scheduleTask.isDone()){
            try{
                TimeUnit.SECONDS.sleep(1);
                if((System.currentTimeMillis() - time) / 1000 > 10){
                    scheduleTask.cancel(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
    }
}
