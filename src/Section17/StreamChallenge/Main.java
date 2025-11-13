package Section17.StreamChallenge;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        int bingoIndex = 1;
        var streamOriginal = Stream.empty();
        for(Character c : "BINGO".toCharArray()){
            int startIndex = bingoIndex;
            var streamTemp = Stream.iterate(startIndex,x->x+1)
                    .limit(15)
                    .map(x ->"" + c + x);
            streamOriginal = Stream.concat(streamOriginal,streamTemp);
            bingoIndex += 15;
        }
        streamOriginal.forEach(System.out::println);
    }
}
