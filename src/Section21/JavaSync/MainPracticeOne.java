package Section21.JavaSync;
class SynchronizedExchanger{
    private Object myobject = null;

    public synchronized Object getMyobject() {
        return myobject;
    }

    public synchronized void setMyobject(Object myobject) {
        this.myobject = myobject;
    }

    public Object getObj() {
        synchronized (this){
            return myobject;
        }
    }

    public void setObj(Object myobject) {
        synchronized (this){
            this.myobject = myobject;
        }
    }
}
public class MainPracticeOne {
    public static void main(String[] args) {
        SynchronizedExchanger mySync = new SynchronizedExchanger();
        Thread thread1 = new Thread(()->{
            for(int i=0;i<1000;i++){
                mySync.setMyobject("" + i);
            }
        },"Thread one");
        Thread thread2 = new Thread(()->{
            for(int i=0;i<1000;i++){
                System.out.println(mySync.getMyobject());
            }
        },"Thread two");
        thread1.start();
        thread2.start();
    }
}
