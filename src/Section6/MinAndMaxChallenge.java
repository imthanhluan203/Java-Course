package Section6;

import java.util.Scanner;

public class MinAndMaxChallenge {
    public static void main(String[] args) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("Enter your integer");
            try{
                int userInput = Integer.parseInt(scan.nextLine());
                if(userInput < min){
                    min = userInput;
                }
                if(userInput > max){
                    max = userInput;
                }
            } catch (Exception e) {
                System.out.println("Exit program");
                break;
            }
        }
        System.out.println("Max value is " + max);
        System.out.println("Min value is " + min);
    }

}
