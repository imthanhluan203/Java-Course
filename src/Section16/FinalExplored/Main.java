package Section16.FinalExplored;

import Section16.Consumer.specific.ChildClass;
import Section16.External.util.Logger;
import Section16.FinalExplored.generic.BaseClass;

public class Main {
    public static void main(String[] args) {
        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass child_2 = new ChildClass();
        parent.recommendedMethod();
        System.out.println("-".repeat(30));
        child.recommendedMethod();
        System.out.println("-".repeat(30));
        child_2.recommendedMethod();
        System.out.println("-".repeat(30));
        parent.recommendedMethodStatic();
        System.out.println("-".repeat(30));
        child.recommendedMethodStatic();
        System.out.println("-".repeat(30));
        child_2.recommendedMethodStatic();

        System.out.println("-".repeat(30));
        BaseClass.recommendedMethodStatic();
        System.out.println("-".repeat(30));
        ChildClass.recommendedMethodStatic();
        System.out.println("-".repeat(30));

        String xArgument = "This is all I've got to say about Section ";
        StringBuilder xArgument_withStr = new StringBuilder("This is all I've got to say about Section");
        doXYZ(xArgument,16,xArgument_withStr);
        System.out.println(xArgument);
        System.out.println(xArgument_withStr);
        System.out.println("-".repeat(30));
        StringBuilder tracker = new StringBuilder("Step 1 is abc");
        Logger.logToConsole(tracker);
        tracker.append(", Step 2 is xyz");
        Logger.logToConsole(tracker);
        System.out.println("After logging, tracker = " + tracker);
    }
    private static void doXYZ(String x, int y,StringBuilder z){
        final String c = x+y;
        System.out.println("c = " + c);
        x = c;
        z.append(y);
    }
}
