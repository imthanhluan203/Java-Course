package Section16.Consumer.specific;

import Section16.FinalExplored.generic.BaseClass;

public class ChildClass extends BaseClass {
    @Override
    protected void optionalMethod() {
        System.out.println("[Child:optionalMethod] Extra Stuff Here");
        super.optionalMethod();
    }

//    @Override
////    public void recommendedMethod() {
////        System.out.println("[Child:recommendedMethod: I'll do things my way");
////        optionalMethod();
////    }
    private void mandatoryMethod(){
        System.out.println("[ChildClass.mandatoryMethod]: My own important stuff");
    }

    public static void recommendedMethodStatic(){
        System.out.println("[Child.recommendedStatic]: Best Way to Do it");
        optionalMethodStatic();
//        mandatoryMethodStatic();
    }
}
