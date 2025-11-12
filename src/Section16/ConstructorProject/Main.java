package Section16.ConstructorProject;

import Section16.ConstructorProject.External.Child;

public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent("Dat","14/02/2021", 4);
        Child child = new Child();
        System.out.println("Parent: "+parent);
        System.out.println("Child: " + child);

        Person joe = new Person(null,"01/01/1950");
        System.out.println(joe);
        Person joeCopy = new Person(joe);
        System.out.println(joeCopy);
        Generation g = Generation.BABY_BOOMER;
    }
}
