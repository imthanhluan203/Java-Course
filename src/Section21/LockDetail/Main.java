package Section21.LockDetail;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CounterSynchronized{
    private long count = 0;
    public synchronized void inc(){
        this.count++;
    }
    public synchronized long getCount(){
        return this.count;
    }
}
class CounterLock{
    private long count = 0;
    private Lock lock = new ReentrantLock();
    public void inc(){
        try{
            lock.lock();
            this.count++;
        }finally {
            lock.unlock();
        }
    }
    public long getCount(){
        try{
            lock.lock();

            return this.count;
        }finally {
            lock.unlock();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        var myCounter = new CounterLock();
        new Thread(()->{
            for(int i=0;i<10;i++){
                myCounter.inc();
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(myCounter.getCount());
            }
        }).start();
    }
}
