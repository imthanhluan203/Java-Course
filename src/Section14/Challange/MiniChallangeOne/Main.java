package Section14.Challange.MiniChallangeOne;

import java.util.function.Consumer;
import java.util.function.Supplier;

interface printTheParts{
    String everySecondChar(String sentence);
}
public class Main {
    public static void main(String[] args) {
        String sentence = "Hom nay toi di hoc";
        Consumer<String> printWorld = (s)->{
            String[] parts = s.split(" ");
            for(var e:parts){
                System.out.println(e);
            }
        };
        printWorld.accept(sentence);
        System.out.println("===".repeat(30));
        String sentence2 = "1234567890";
        printTheParts printWorld2 = x -> {
            StringBuilder returnval = new StringBuilder();
            for(int i=0;i<x.length();i++){
                if(i%2==1){
                    returnval.append(x.charAt(i));
                }
            }
            return  returnval.toString();
        };
        everySecondCharactor(x -> {
            StringBuilder returnval = new StringBuilder();
            for(int i=0;i<x.length();i++){
                if(i%2==1){
                    returnval.append(x.charAt(i));
                }
            }
            return  returnval.toString();
        },sentence2);

        Supplier<String> t = ()->{
            return "I love java";
        };
        String s = t.get();
        System.out.println(s);
    }
    public static void everySecondCharactor(printTheParts function,String sentence){
        System.out.println(function.everySecondChar(sentence));
    }
}
