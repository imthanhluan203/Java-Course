package Section21.ParallelStreamAndMore;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Person(String firstName,String lastName, int age){
    private final static String[] firsts =
            {"Able","Bob","Charlie","Donna","Eve","Fred"};
    private final static String[] lasts =
            {"Norton","OHara","Petersen","Quincy","Richardson","Smith"};
    private final static Random random = new Random();
    public Person(){
        this(firsts[random.nextInt(firsts.length)],
                lasts[random.nextInt(lasts.length)],
                random.nextInt(18,100));
    }

    @Override
    public String toString() {
        return "%s, %s (%d)".formatted(lastName,firstName,age);
    }
}
public class Main {
    public static void main(String[] args) {
        Stream.generate(Person::new)
                .limit(10)
                .parallel()
                .sorted(Comparator.comparing(Person::age))
                .forEachOrdered(System.out::println);
        System.out.println("-".repeat(50));
        int sum = IntStream.range(1,101)
                .parallel()
                .reduce(0 ,Integer :: sum);
        System.out.println("The sum of the numbers is: " + sum);
        System.out.println("-".repeat(50));
        String humpltyDumpty = """
                Humpty Dumpty sat on a wall
                Humpty Dumpty had a great fall.
                All the king's hoes and all the king's men
                Couldn't put Humpty together again.
                """;
        var words = new Scanner(humpltyDumpty).tokens().toList();
        words.forEach(System.out::println);
        var backTogether2 = words.stream()
                .parallel()
                .collect(()->new StringBuilder(" "),
                        StringBuilder::append,
                        StringBuilder::append);
        System.out.println(backTogether2);

        Map<String, Long> lastNameCounts = Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .collect(()->new HashMap<String,Long>(),
                        (map,person)->{map.merge(person.lastName(),1L, Long::sum);},
                        (a,b)->{
                            b.forEach((k,v)->{
                                a.merge(k,v, Long::sum);
                            });
                        });
        lastNameCounts.forEach((a,b)->{
            System.out.println(a + ":" + b);
        });
    }
}
