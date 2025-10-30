package Section6;

public class SwitchChallange {
    public static void main(String[] args) {
        char letter = 'B';
        switch (letter){
            case 'A':
                System.out.print("Able");
                break;
            case 'B':
                System.out.print("Baker");
                break;
            case 'C':
                System.out.print("Charlie");
                break;
            case 'D':
                System.out.print("Dog");
                break;
            case 'E':
                System.out.print("Easy");
                break;
            default:
                System.out.print("Not found");
                break;
        }
        for(int i = 0;i <8;i++){
            printDayOfWeek(i);
        }
    }
    public static void printDayOfWeek(int day){
        String dayName = switch (day){
            case 0->"\nSunday";
            case 1->"\nMonday";
            case 2->"\nTuesday";
            case 3->"\nMonday";
            case 4->"\nWednesday";
            case 5->"\nThursday";
            case 6->"\nSaturday";
            default -> "\nInvalid day";
        };
        System.out.print(dayName);
    }

}
