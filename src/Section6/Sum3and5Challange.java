package Section6;

public class Sum3and5Challange {
    public static void main(String[] args) {
        int sum = 0;
        int count =0;
        for(int i=1;i<=1000;i++){
            if(i%3 == 0 && i%5 == 0){
                System.out.println(i);
                sum += i;
                count++;
            }
            if(count==5){
                System.out.println(sum);
                break;
            }

        }
    }
}
