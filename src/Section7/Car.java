package Section7;

public class Car {
    private String make = "Tesla";
    private String model = "VF9";
    private String color = "LightWhite";
    private int doors = 4;
    private boolean convertible = true;
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getDoors() {
        return doors;
    }

    public String getColor() {
        return color;
    }

    public boolean isConvertible() {
        return convertible;
    }

    public void describeCar(){
        System.out.println(doors+ "-door-"  + color + " " + make + " " + model + " " +
                (convertible ? "Convertible" : "hehe"));
    }
}
