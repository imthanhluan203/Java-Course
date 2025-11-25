package Section21.JavaSync;
class Mycounter{
    private int count = 0;
    Object moniter_one= null;
    Object moniter_two= null;
    Mycounter(Object a,Object b){
        this.moniter_one = a;
        this.moniter_two = b;
    }
    public void add_one(){
        synchronized (this.moniter_one){
            for(int i=0;i<100000;i++){
                count++;
            }
        }
    }
    public void add_two(){
        synchronized (this.moniter_two){
            for(int i=0;i<100000;i++){
                count++;
            }
        }
    }
    public int getCount(){
        return count;
    }
}
public class MainPracticeTwo {
    public static void main(String[] args) {
        Object first = new Object();
        Object second = new Object();
        Mycounter one = new Mycounter(first,second);
        Thread threadOne = new Thread(one::add_one);
        Thread threadtwo = new Thread(one::add_one);
        threadOne.start();
        threadtwo.start();
        try {
            threadOne.join();
            threadtwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(one.getCount());
    }
}
