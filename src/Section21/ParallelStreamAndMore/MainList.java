package Section21.ParallelStreamAndMore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class MainList {
    public static void main(String[] args) {
        Map<String,Long> threadCount = new ConcurrentHashMap<>();
        var perList = Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .peek(x->{
                    String name = Thread.currentThread().getName();
                    threadCount.merge(name,1L,Long::sum);
                }).toArray(Person[]::new);
        System.out.println(threadCount);
        System.out.println(threadCount.values().stream().reduce(0L,Long::sum));
    }
}
