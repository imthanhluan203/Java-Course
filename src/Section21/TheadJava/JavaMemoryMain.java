package Section21.TheadJava;
class MyRunable implements Runnable{
    private int count = 0;
    @Override
    public synchronized void run() {
        for(int i=0;i<1000;i++){
            this.count++;
        }
        System.out.println(Thread.currentThread().getName() + " :" + this.count);
    }
}
public class JavaMemoryMain {
    public static void main(String[] args) {
        Runnable runable = new MyRunable();
        Thread a1 = new Thread(runable,"First Thread");
        Thread a2 = new Thread(runable,"Second Thread");
        a1.start();
        a2.start();
        try {
            a1.join();
            a2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
