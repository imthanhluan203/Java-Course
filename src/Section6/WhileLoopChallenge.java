package Section6;

public class WhileLoopChallenge {
    public static void main(String[] args) {
        int i=5;
        int count = 0;
        while (i<=20){
            if(isEvenNumber(i)){
                System.out.println("The even number is " + i);
                count++;
            }
            if(count == 5){
                break;
            }
            i++;
        }
    }
    public static boolean isEvenNumber(int number){
        return number%2 == 0;
    }
}
