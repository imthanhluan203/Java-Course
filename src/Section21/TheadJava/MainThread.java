package Section21.TheadJava;
class MyThread implements Runnable{
    private boolean do_stop = false;
    public synchronized void stop_thread(){
        this.do_stop = true;
    }
    public synchronized boolean keep_running(){
        return do_stop == false;
    }
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        while (keep_running()){
            System.out.println(threadName + " Running ...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class MainThread {

    public static void main(String[] args) {
        MyThread myRunable = new MyThread();
        new Thread(myRunable,"My Thread 1").start();
        new Thread(myRunable,"My Thread 2").start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        myRunable.stop_thread();
    }
}
