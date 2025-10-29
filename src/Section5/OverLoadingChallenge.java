package Section5;

public class OverLoadingChallenge {
    public static void main(String[] args) {
        System.out.print(convertToCentimeter(125) +"\n");
        System.out.print(convertToCentimeter(4,125));
    }
    public static double convertToCentimeter(int inches){
        return inches * 2.54;
    }
    public static double convertToCentimeter(int feet,int inches){
        return convertToCentimeter(feet*12 + inches);
    }
}
