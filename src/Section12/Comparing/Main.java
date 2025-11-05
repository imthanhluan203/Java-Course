package Section12.Comparing;

public class Main {
    public static void main(String[] args) {
        Integer five = 5;
        Integer arr[] = {0,5,10,-50,50};
        for(Integer i : arr){
            int val = five.compareTo(i);
            System.out.printf("%d %s %d: compareTo:%d%n",five,
                    val == 0 ? "=" : (val < 0 ? "<":">"),i,val);
        }
    }
}
