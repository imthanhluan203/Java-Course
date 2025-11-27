package Section21.Executor;

import java.util.List;
import java.util.concurrent.*;

class ColorThreadFactory implements ThreadFactory {
    private String threadName = null;
    private int colorValue = 1;
    public ColorThreadFactory(ThreadColor color){
        this.threadName = color.name();
    }
    public ColorThreadFactory(){}
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = threadName;
        if(name == null){
            name = ThreadColor.values()[colorValue].name();
        }
        if(++colorValue > ThreadColor.values().length-1){
            colorValue = 1;
        }
        thread.setName(name);
        return thread;
    }
}
public class Main {
    public static void main(String[] args) {
        var multiExecutor = Executors.newCachedThreadPool();
        List<Callable<Integer>> taskList = List.of(
                ()->Main.sum(1,10,1,"red"),
                ()->Main.sum(10,100,10,"blue"),
                ()->Main.sum(2,20,2,"green")
        );
        try {
            var results = multiExecutor.invokeAll(taskList);
            for(var result : results){
                System.out.println(result.get(500,TimeUnit.SECONDS));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            multiExecutor.shutdown();
        }
    }
    public static void cachedmain(String[] args) {
        var multiExecutor = Executors.newCachedThreadPool();
        try{
            var redValue = multiExecutor.submit(
                    ()->Main.sum(1,10,1,"red")
            );
            var blueValue = multiExecutor.submit(
                    ()->Main.sum(10,100,10,"blue")
            );
            var greenValue = multiExecutor.submit(
                    ()->Main.sum(2,20,2,"green")
            );
            try {
                System.out.println(redValue.get(500,TimeUnit.SECONDS));
                System.out.println(blueValue.get(500,TimeUnit.SECONDS));
                System.out.println(greenValue.get(500,TimeUnit.SECONDS));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }finally {
            multiExecutor.shutdown();
        }
    }
    public static void fixedmain(String[] args) {
        int count = 3;
        var multiExecutor = Executors.newFixedThreadPool(2,
                new ColorThreadFactory());
        for(int i=0;i<4;i++){
            multiExecutor.execute(Main::countDown);
        }
        multiExecutor.shutdown();
    }
    public static void singlemain(String[] args) {
        var blueExecutor = Executors.newSingleThreadExecutor(
                new ColorThreadFactory(ThreadColor.ANSI_BLUE)
        );
        blueExecutor.execute(Main::countDown);
        blueExecutor.shutdown();
        boolean isDone;
        try {
             isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(isDone){
            System.out.println("Blue is done");
            var yellowExecutor = Executors.newSingleThreadExecutor(
                    new ColorThreadFactory(ThreadColor.ANSI_YELLOW)
            );
            yellowExecutor.execute(Main::countDown);
            yellowExecutor.shutdown();
            try {
                isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(isDone){
                System.out.println("Yellow is done");
                var redExecutor = Executors.newSingleThreadExecutor(
                        new ColorThreadFactory(ThreadColor.ANSI_RED)
                );
                redExecutor.execute(Main::countDown);
                redExecutor.shutdown();
                try {
                    isDone = redExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(isDone){
                    System.out.println("All thread is done");
                }
            }
        }


    }
    public static void notmain(String[] args) {
        Thread blue = new Thread(Main::countDown, ThreadColor.ANSI_BLUE.name());
        Thread yellow = new Thread(Main::countDown, ThreadColor.ANSI_YELLOW.name());
        Thread red = new Thread(Main::countDown, ThreadColor.ANSI_RED.name());
        blue.start();
        yellow.start();
        red.start();

    }
    private static void countDown(){
        String threadName = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;
        try{
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        String color = threadColor.color();
        for(int i=2; i >= 0 ; i--){
            System.out.println(color + " " +
                    threadName.replace("ANSI_","")+ " " + i);
        }
    }
    private static int sum(int start,int end,int delta,String colorString){
        var threadColor = ThreadColor.ANSI_RESET;
        try{
             threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
        } catch (IllegalArgumentException e) {

        }
        String color = threadColor.color();
        int sum = 0;
        for(int i=start; i<=end;i+=delta){
            sum += i;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(color + Thread.currentThread().getName() +", "
        + colorString + " " + sum);
        return sum;
    }
}
