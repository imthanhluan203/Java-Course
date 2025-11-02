package Section8.Polymophysm;

public class Car {
    private String description;
    public Car(String description){
        this.description = description;
    }
    public void startEngine(){
        System.out.println("Car -> startEngine");
    }
    public void drive(){
        startEngine();
        System.out.println("Car -> driving, type is:" + this.getClass().getSimpleName());
        runEngine();
    }
    protected void runEngine(){
        System.out.println("Car -> runEngine");
    }
}
