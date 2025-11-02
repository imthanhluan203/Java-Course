package Section8.Polymophysm;

public class Main {
    public static void main(String[] args) {
        Car c1 = new ElectricCar("Vinfast", 150,90);
        runRace(c1);
        Car c2 = new GasPoweredCar("Vinfast 2",200,15);
        runRace(c2);
        Car c3 = new HybridCar("Vinfast 3",200,15, 20);
        runRace(c3);
    }
    public static void runRace(Car car){
        car.drive();
    }
}
