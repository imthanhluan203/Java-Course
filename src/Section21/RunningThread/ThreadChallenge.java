package Section21.RunningThread;

public class ThreadChallenge {
    public static void main(String[] args) {
        Thread firstThread = new Thread(()->{
            for(int i = 0;i<=10;i+=2){
                System.out.print(" " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread secondThread = new Thread(()->{
            for(int i = 1;i<=11;i+=2){
                System.out.print(" " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        firstThread.start();
        secondThread.start();
    }
}
