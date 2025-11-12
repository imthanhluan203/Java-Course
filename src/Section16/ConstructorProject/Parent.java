package Section16.ConstructorProject;

public class Parent {

    private final String name;
    private final String dob;
    protected final int sibling;
    public Parent(String name, String dob,int sibling) {
        this.name = name;
        this.dob = dob;
        this.sibling = sibling;
        System.out.println("In Parent Constructor");
    }

//    public Parent() {
//        System.out.println("No Args in Parent Constructor");
//    }

    {
        System.out.println("In Parent Initilizer");
//        name = "Luan";
//        dob = "13/01/2003";
    }


    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }


    @Override
    public String toString() {
        return "name:%s dob:%s".formatted(name,dob);
    }
    static  {
        System.out.println("This is static method initiazer from Parent");
    }
}
