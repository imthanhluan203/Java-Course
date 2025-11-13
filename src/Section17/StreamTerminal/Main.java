package Section17.StreamTerminal;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var result = IntStream.iterate(0,i->i<=1000,i->i+10)
                .summaryStatistics();
        System.out.println("Result = " + result);
        var leapYear = IntStream.iterate(2000,i->i<2025,i->i+1)
                .filter(i->i%4 == 0)
                .peek(x->System.out.println(x))
                .summaryStatistics();
        System.out.println("Leap year data = " + leapYear);
        System.out.println("-".repeat(30));
        Seat[] seats = new Seat[100];
        Arrays.setAll(seats,i->new Seat((char)('A' + i/10),i%10 + 1));
        Arrays.asList(seats).forEach(System.out::println);

        long reservationCount = Arrays.stream(seats).filter(x->x.isRevered() == true).count();
        System.out.println("Reservation count = " + reservationCount);
    }
}
