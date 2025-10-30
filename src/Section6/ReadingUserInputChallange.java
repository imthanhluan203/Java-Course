package Section6;

import java.util.Scanner;

public class ReadingUserInputChallange {
    public static void main(String[] args) {
        readInput(5);
    }
    public static void readInput(int number){
        int count = 1;
        int sum=0;
        Scanner scan = new Scanner(System.in);
        while (count <= 5){
            System.out.println("Enter number #"+count+":");
            try {
                int userInput = Integer.parseInt(scan.nextLine());
                sum += userInput;
                count+=1;
            }catch (Exception e){
                System.out.println("Invalid Number");
            }

        }
        System.out.println("The total is "+sum);
    }
}
