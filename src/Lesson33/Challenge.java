package Lesson33;

public class Challenge {
    public static void main(String[] args) {
        double firstValue = 20;
        double secondValue = 80.000d;
        double thirdValue = (firstValue+secondValue)*100; //10000
        double fourValue = thirdValue%17; //0
        boolean fiveValue = (fourValue == 0);
        System.out.print(fiveValue + "\n");
        if(!fiveValue){
            System.out.print("got some remainder");
        }
    }
}
