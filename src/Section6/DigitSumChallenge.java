package Section6;

public class DigitSumChallenge {
    public static void main(String[] args) {
        System.out.println(sumDigits(1234));
    }
    public static int sumDigits(int number){
        if(number < 0){
            return -1;
        }
        if(number < 10){
            return number;
        }
        int temp = number;
        int sum = 0;
        while (temp > 0){
            sum += temp%10;
            temp = temp/10;
        }
        return  sum;
    }
}
