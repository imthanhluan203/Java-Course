package Section21.TheadJava;

public class MainJoin {
    public static void main(String[] args) {
        Thread myThread = new Thread(()->{
            for(int i=0;i<5;i++){
                sleep(1000);
                System.out.println("Running...");
            }
        });
        myThread.setDaemon(true);
        myThread.start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    private static void sleep(int milisecond){
        try {
            Thread.sleep(milisecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
