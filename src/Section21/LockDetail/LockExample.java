package Section21.LockDetail;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    public static void main(String[] args) {
        //lockBasic();
        lockInterruptibly();
    }
    private static void lockInterruptibly(){
        Lock lock = new ReentrantLock();
        Thread.currentThread().interrupt();
        try{
            lock.lockInterruptibly();
            lock.unlock();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
    private static void lockBasic(){
        Lock lock = new ReentrantLock(true);
        Runnable runnable = () -> {lockSleepUnlock(lock,1000);};
        Thread thread1 = new Thread(runnable,"Thread 1");
        Thread thread2 = new Thread(runnable,"Thread 2");
        Thread thread3 = new Thread(runnable,"Thread 3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
    private static void lockSleepUnlock(Lock lock,long timeMillis){
        try{
            lock.lock();
            printThreadMsg(" holds the lock");
            sleep(timeMillis);
        }finally {
            lock.unlock();
        }
    }
    private static void printThreadMsg(String text){
        System.out.println(Thread.currentThread().getName() + text);
    }
    private static void sleep(long timeMillis){
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
