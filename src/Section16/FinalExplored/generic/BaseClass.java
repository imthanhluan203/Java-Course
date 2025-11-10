package Section16.FinalExplored.generic;

public class BaseClass {
    public final void recommendedMethod(){
        System.out.println("[BaseClass.recommendedMethod]: Best Way to Do it");
        optionalMethod();
        mandatoryMethod();
    }
    protected void optionalMethod(){
        System.out.println("[BaseClass.optionalMethod]: Customize Optional Method");
    }
    private void mandatoryMethod(){
        System.out.println("[BaseClass.mandatoryMethod]: NON-NEGOTIABLE");
    }

    public static void recommendedMethodStatic(){
        System.out.println("[BaseClass.recommendedStatic]: Best Way to Do it");
        optionalMethodStatic();
        mandatoryMethodStatic();
    }
    protected static void optionalMethodStatic(){
        System.out.println("[BaseClass.optionalStatic]: Customize Optional Method");
    }
    private static void mandatoryMethodStatic(){
        System.out.println("[BaseClass.mandatoryStatic]: Mandatory");
    }
}
