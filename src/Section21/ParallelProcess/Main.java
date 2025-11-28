package Section21.ParallelProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumRecusiveTask extends RecursiveTask<Long> {
    private final int threshHold = 1000;
    private long[] nums;
    private int start;
    private int end;

    public SumRecusiveTask(long[] nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if((end - start) < threshHold){
            long sum=0;
            for(int i=start;i<end;i++){
                sum += nums[i];
            }
            return sum;
        }else{
            int mid = start + (end - start) / 2;
            SumRecusiveTask leftTask = new SumRecusiveTask(nums,start,mid);
            SumRecusiveTask rightTask = new SumRecusiveTask(nums,mid,end);
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        int numbersLenght = 100_000;
        long[] numbers = new Random(42).longs(numbersLenght,1,numbersLenght).toArray();
        long sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        ForkJoinPool threadPool = ForkJoinPool.commonPool();
        List<Callable<Long>> tasks = new ArrayList<>();
        int taskNo = 10;
        int splitCount = numbersLenght / taskNo;
        for(int i=0;i<10;i++){
            int start = i*splitCount;
            int end = start + splitCount;
            tasks.add(()->{
                long tasksum = 0;
                for(int j=start;j<end;j++){
                    tasksum += numbers[j];
                }
                return  tasksum;
            });
        }
        long taskSum = 0;
        try{
            var futures = threadPool.invokeAll(tasks);
            System.out.println("CPUs: " + Runtime.getRuntime().availableProcessors());
            System.out.println("Parallelism = " + threadPool.getParallelism());
            System.out.println("Pool size = " + threadPool.getPoolSize());
            System.out.println("Steal count = " + threadPool.getStealCount());


            for(var future : futures){
                taskSum += future.get();
            }

            RecursiveTask<Long> task = new SumRecusiveTask(numbers,0,numbersLenght);
            long forkJoinSUm = threadPool.invoke(task);
            System.out.println("Recursive Task sum:" + forkJoinSUm);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
        System.out.println("Thread Pool Sum = " + taskSum);
    }
}
