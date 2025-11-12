package Section16.ConstructorProject.External;

import Section16.ConstructorProject.Parent;

import java.util.Random;

public class Child extends Parent {
    private final int birthOrder = getBirthOrder();
    private final String birthOrderString;
    {
        if(sibling == 0){
            birthOrderString = "only";
        } else if (birthOrder == 1) {
            birthOrderString = "first";
        } else if (birthOrder == (sibling + 1)) {
            birthOrderString = "Last";
        } else{
            birthOrderString = "Middle";
        }
        System.out.println("Child: Initializer, birthOrder = " + birthOrder +", birthOrderString");
    }
    public Child() {
        super("Jane Doe","02/02/1920",5);
        System.out.println("Child: Constructor");
    }
    private final int getBirthOrder(){
        if (sibling == 0) return 1;
        return new Random().nextInt(1,sibling+2);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + birthOrderString + " child";
    }
}
