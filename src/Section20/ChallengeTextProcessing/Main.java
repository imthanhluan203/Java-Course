package Section20.ChallengeTextProcessing;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("src/Section20/ChallengeTextProcessing/article.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
          var result = reader.lines()
                    .map(s->s.toLowerCase())
                    .map(s->s.replaceAll("[\\p{Punct}]", " "))
                    .flatMap(x ->{
                              String[] wordsArray = x.split("\\s+");
                              return Arrays.stream(wordsArray);
                          })
                    .filter(x->x.length()>5)
                    .collect(Collectors.groupingBy(x->x,Collectors.counting()));
            result.entrySet().stream().sorted((o1,o2)->Long.valueOf(o1.getValue()).compareTo(Long.valueOf(o2.getValue())))
                    .forEach(x->System.out.println(x.getKey() +":" + x.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
