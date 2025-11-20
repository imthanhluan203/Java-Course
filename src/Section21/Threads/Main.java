package Section21.Threads;

public class Main {
    public static void main(String[] args) {
        var currentThread = Thread.currentThread();
        System.out.println(currentThread.getClass().getName());
        System.out.println(currentThread);
        printThreadState(currentThread);
        currentThread.setName("MainGuy");
        currentThread.setPriority(Thread.MAX_PRIORITY);
        printThreadState(currentThread);
        CustomThread customThread = new CustomThread();
        customThread.start();
        Runnable myRunable = () ->{
            for(int i=1;i<=8;i++){
                System.out.print(" 2 ");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread myThread = new Thread(myRunable);
        myThread.start();
        for(int i=0;i<3;i++){
            System.out.print(" 0 ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void printThreadState(Thread thread){
        System.out.println("-".repeat(30));
        System.out.println("Thread ID: " + thread.getId());
        System.out.println("Thread Name: " + thread.getName());
        System.out.println("Thread Priority: " + thread.getPriority());
        System.out.println("Thread State: " + thread.getThreadGroup());
        System.out.println("Thread is Alive: " + thread.isAlive());
        System.out.println("-".repeat(30));
    }
}
