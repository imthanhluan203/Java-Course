package Section10;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListChallenge {
    public static void main(String[] args) {
        ArrayList<String> grocery = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true){
            String menu = String.format("Available actions:%n" +
                                        "0 - to shutdown%n" +
                                        "1 - to add item(s) to list (comma delimited list)%n" +
                                        "2 - to remove any items (comma delimited list)%n" +
                                        "Enter a number for which action you want to do:");
            System.out.print(menu);
            int your_choice = sc.nextInt();
            sc.nextLine();
            boolean flag = false;
            switch (your_choice){
                case 0:
                    flag = true;
                    break;
                case 1:
                    System.out.print("Enter your name Item:");
                    String yourItem = sc.nextLine();
                    if(!grocery.contains(yourItem)){
                        grocery.add(yourItem);
                    }
                    break;
                case 2:
                    System.out.print("Enter your name Item:");
                    String delete_item = sc.nextLine();
                    grocery.remove(delete_item);
                    break;
            }
            System.out.println(grocery);
            if(flag == true){
                break;
            }
        }
    }
}
