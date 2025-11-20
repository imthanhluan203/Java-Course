package Section21.MutipleThreads;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch(TimeUnit.SECONDS);
        Thread green = new Thread(()->new StopWatch(TimeUnit.SECONDS).countDown(),ThreadColor.ANSI_GREEN.name());
        Thread purple = new Thread(()->new StopWatch(TimeUnit.SECONDS).countDown(7),ThreadColor.ANSI_PURPLE.name());
        Thread red = new Thread(()->new StopWatch(TimeUnit.SECONDS).countDown(),ThreadColor.ANSI_RED.name());

        green.start();
        red.start();
        purple.start();
    }
}
class StopWatch{
    private TimeUnit timeUnit;
    private static int i;
    public StopWatch(TimeUnit timeUnit){
        this.timeUnit = timeUnit;
    }
    public void countDown(){
        countDown(5);
    }
    public void countDown(int unitCount){
        String threadName = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;
        threadColor = ThreadColor.valueOf(threadName);
        String color =  threadColor.color();
        for(i = unitCount; i > 0; i--){
            try {
                timeUnit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s%s Thread : i = %d%n", color, threadName, i);
        }
    }
}