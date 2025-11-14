package Section17.Exercise.AverageNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       String open  = "Input numbers, type \"end\" to stop.\n";
       Scanner sc = new Scanner(System.in);
       System.out.println(open);
       List<Double> store = new ArrayList<>();
       while (true){
           String row = sc.nextLine();
           if(row.equalsIgnoreCase("end")){
               break;
           }
           store.add(Double.parseDouble(row));
       }
       double averageNegative = store.stream().mapToDouble(x->Double.valueOf(x))
               .filter(x->x<0)
               .average().getAsDouble();
       double averagePositive = store.stream().mapToDouble(x->Double.valueOf(x))
                .filter(x->x>0)
                .average().getAsDouble();
       System.out.println("Print the average of the negative numbers or the positive numbers? (n/p)");
       String row2 = sc.nextLine();
       if(row2.equalsIgnoreCase("n")){
           System.out.println(averageNegative);
       } else if (row2.equalsIgnoreCase("p")) {
            System.out.println(averagePositive);
       }
    }
}
