package JavaArray.MinimumChallange;

import java.util.Arrays;
import java.util.Scanner;

public class Minimum {
    private int[] arr;
    private int length;
    public Minimum(int length){
        this.length = length;
        arr = new int[length];
    }
    public void readInteger(){
        System.out.println("Start loading number...");
        Scanner sc = new Scanner(System.in);
        for(int i = 0;i<length;i++){
            System.out.println("Enter number " + (i+1) + ":");
            arr[i] = sc.nextInt();
        }
    }
    public void reversedArray(){
        for(int i=0;i<length/2;i++){
            int temp = arr[i];
            arr[i] = arr[length - 1 - i];
            arr[length - 1 - i] = temp;
        }
    }
    public int findMin(){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<length;i++){
            min = arr[i] < min ? arr[i] : min;
        }
        return min;
    }

    @Override
    public String toString() {
        return "{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
}
