package Section14.ChainingLambdas;

import java.util.Arrays;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        String name = "Nguyen Thanh";
        Function<String,String> uCase = t->t.concat(" Luan");
//        Function<String,String> lastName = s -> s.concat(" Luan");
//        Function<String,String> uCaseLastName = uCase.andThen(lastName);
        Function<String,String[]> f0 = uCase
                .andThen(s->s.toUpperCase())
                .andThen(s->s.split(" "));
        System.out.println(Arrays.toString(f0.apply(name)));
    }
}
