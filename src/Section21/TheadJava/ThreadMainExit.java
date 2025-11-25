package Section21.TheadJava;

public class ThreadMainExit {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            while (true){
                System.out.println("Running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
