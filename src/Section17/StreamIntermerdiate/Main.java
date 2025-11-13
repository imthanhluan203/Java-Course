package Section17.StreamIntermerdiate;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        IntStream.iterate((int)'A', i->i<=(int)'z',i->i+1)
                .filter(x->Character.isAlphabetic(x))
                .skip(5)
                .forEach(x->System.out.print((char)x+" "));
        System.out.println("-".repeat(30));
        Random rd= new Random();
        Stream.generate(()->rd.nextInt((int)'A',(int)'Z'+1))
                .limit(50)
                .distinct()
                .sorted()
                .forEach(x->System.out.printf("%c ",x));
        System.out.println("-".repeat(30));
        int maxSeats = 100;
        int seatsInRow = 10;
        var stream = Stream.iterate(0,i->i<maxSeats,i->i+1)
                .map(i->new Seat((char)('A' + i/seatsInRow),i%seatsInRow + 1))
                        .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString));
//                .mapToDouble(Seat::price)
//                        .mapToObj("%.2f"::formatted);

        stream.forEach(System.out::println);
    }
}
