package Section17.BinGoGame;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> bingoPool = new ArrayList<>(75);
        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
            }
            start += 15;
        }
        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("-".repeat(30));
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0 || s.indexOf('O') == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println("\n" + "-".repeat(30));

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("-".repeat(30));

        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf('O') == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();
//                .forEach(x->System.out.print(x+" "));
        tempStream.forEach(x -> System.out.print(x + " "));

        System.out.println("\n" + "-".repeat(30));

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("-".repeat(30));

        String[] test = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(test)
                .sorted(Comparator.reverseOrder());
//                .forEach(x->System.out.println(x));
        var secondStream = Stream.of("Six", "Seven", "Eight")
                .map(String::toUpperCase);
//                .forEach(x->System.out.println(x));
        Stream.concat(firstStream, secondStream)
                .map(x -> x.charAt(0) + "-" + x)
                .forEach(System.out::println);

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (Character c : "BINGO".toCharArray()) {
            int[] temp = new int[15];
            int startIndex = bingoIndex;
            Arrays.setAll(temp, i -> i + startIndex);
            myMap.put(c, temp);
            bingoIndex += 15;
        }
        myMap.entrySet().stream()
                .map(e->"%s has range: %s - %s"
                        .formatted(e.getKey(),e.getValue()[0],e.getValue()[e.getValue().length-1]))
                .forEach(System.out::println);
        Stream.generate(()->new Random().nextInt(3))
                .limit(5)
                .forEach(System.out::println);
        IntStream.iterate(3 , x -> x + 1)
                .limit(10)
                .filter(x -> x%2 == 0)
                .forEach(x->System.out.print(x + " "));
    }

}
