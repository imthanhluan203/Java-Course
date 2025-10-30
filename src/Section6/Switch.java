package Section6;

public class Switch {
    public static void main(String[] args) {
        int value = 3;
        switch (value){
            case 1 -> {
                System.out.print("This is one");
            }
            case 2 -> {
                System.out.print("This is two");
            }
            case 3,4 ->{
                System.out.print("This is three or four");
            }
            default -> {
                System.out.print("This is not 1 2 3 4");
            }
        }
    }
}
