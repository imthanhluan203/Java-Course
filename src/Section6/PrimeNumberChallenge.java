package Section6;

public class PrimeNumberChallenge {
    public static void main(String[] args) {
        int count = 0;
        for(int i=0;i<1000;i++){
            if(isPrime(i)){
                count++;
                System.out.println(i);
            }
        }
    }
    public static boolean isPrime(int number){
        if(number == 2){
            return true;
        }
        if(number < 2){
            return false;
        }
        for(int i=2;i<number/2;i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
}
