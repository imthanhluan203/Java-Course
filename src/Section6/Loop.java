package Section6;

public class Loop {
    public static void main(String[] args) {
        for(double i = 7.5;i<=10;i+=0.25){
            if(calculateInterest(100,i) > 8.5){
                break;
            }
            System.out.println(calculateInterest(100,i));
        }
        for(int number = 2; number >0; number++){
            System.out.println(number);
        }
    }
    public static double calculateInterest(double value,double interest){
        return value * interest / 100;
    }
}
