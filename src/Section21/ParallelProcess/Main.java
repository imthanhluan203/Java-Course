package Section21.ParallelProcess;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int numbersLenght = 100_000;
        long[] numbers = new Random(42).longs(numbersLenght,1,numbersLenght).toArray();
        long sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        ExecutorService threadPool = Executors.newWorkStealingPool(4);




    }
}
