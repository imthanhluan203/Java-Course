package Section11.Interface;
interface Flyable{
    int length = 100;
    private static void log(){
        System.out.println("This is log");
    }
    private static void testlog(){
        System.out.println("This is test log");
        log();
    }
    default  void fly(){
        System.out.println("The className is:"+this.getClass().getSimpleName());
        log();
    }
}
public class InterfaceExample implements Flyable{
    @Override
    public void fly(){
        Flyable.super.fly();
        System.out.println(Flyable.length);
    }
}
