package JavaArray;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] arr = createRandomArray(10);
        System.out.println(Arrays.toString(arr));
        int[] arr_copy = Arrays.copyOf(arr,10);
        arr_copy[2] = 1000;
        System.out.println(Arrays.toString(arr));
//        Arrays.sort(arr);
//        for(int i = arr.length - 1 ; i >= 0;i--){
//            System.out.print(arr[i]+" ");
//        }
    }
    public static int[] createRandomArray(int length){
        Random value = new Random(42);
        int[] result = new int[length];
        for(int i=0;i<length;i++){
            result[i] = value.nextInt(100);
        }
        return  result;
    }
}
