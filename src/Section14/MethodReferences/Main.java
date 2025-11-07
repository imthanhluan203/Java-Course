package Section14.MethodReferences;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("Anna","Bob",
                "Chuck","Dave"));
        list.forEach(System.out::println);
        calculator(Integer::sum,10,14);
    }
    private static <T> void calculator(BinaryOperator<T> function,T value1,T value2){
        T result = function.apply(value1,value2);
        System.out.printf("Result of operation:%s%n",result);
    }
}
