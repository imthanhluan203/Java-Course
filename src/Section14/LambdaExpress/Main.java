package Section14.LambdaExpress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("alpha","bravo","charlie","delta"));
        for(var s : list){
            System.out.println(s);
        }
        list.removeIf(s->s.equalsIgnoreCase("bravo"));
        System.out.println("===".repeat(30));
        list.forEach(x -> System.out.println(x));


        System.out.println("===".repeat(30));
        list.forEach(x -> System.out.println(x));
        System.out.println("===".repeat(30));
        String prefix = "Nato";
        list.forEach(x -> {
            char firstLetter = x.charAt(0);
            System.out.printf("%s %s mean %s%n",prefix,x,firstLetter);
        });
        var result1 = caculator((a,b)-> a+b,3,4);
        var result2 = caculator((a,b)-> a.toUpperCase()+b.toUpperCase(),"Hello","World");
        var coords = List.of(
                new double[]{47.2160,-95.2348},
                new double[]{29.1560,-89.2495},
                new double[]{35.1556,-90.0659}
        );
        coords.forEach(s->{
            processPoint((lat,lon)->{
                System.out.printf("lat:%.2f lon:%.2f %n",lat,lon);
            },s[0],s[1]);
        });

        list.addAll(List.of("echo","easy","earnest"));
        System.out.println("===".repeat(30));
        list.forEach(x -> System.out.println(x));
        list.replaceAll(s->s.charAt(0) + "-" + s.toUpperCase());
        System.out.println("===".repeat(30));
        list.forEach(x -> System.out.println(x));

        String[] arrays = new String[10];
        System.out.println(Arrays.toString(arrays));
        Arrays.fill(arrays,"");
        System.out.println(Arrays.toString(arrays));
        Arrays.setAll(arrays,(i)->"--%d--".formatted(i));
        System.out.println(Arrays.toString(arrays));
    }
    public static  <T> T caculator(BinaryOperator<T> function, T value1, T value2){
        T result = function.apply(value1,value2);
        System.out.printf("Result of operation: %s%n",result);
        return result;
    }
    public static <T> void processPoint(BiConsumer<T,T> function,T value1,T value2){
        function.accept(value1,value2);
    }
}
