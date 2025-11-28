package Section21.ParallelStreams;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long numberLimit = 100_000_000;
        long[] numbers = new Random(42).longs(numberLimit,0,numberLimit).toArray();
        long current = System.nanoTime();
        double result_first = Arrays.stream(numbers).average().orElseThrow();
        long complete_time = System.nanoTime() - current;
        System.out.println(result_first +"-".repeat(10) + complete_time);

        current = System.nanoTime();
        double result_second = Arrays.stream(numbers).parallel().average().orElseThrow();
        complete_time = System.nanoTime() - current;
        System.out.println(result_second +"-".repeat(10) + complete_time);
    }
}
