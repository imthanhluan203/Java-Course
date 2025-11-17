package Section20.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("src/Section20/ReadingFile/fixedWidth.txt");
        try{
            System.out.println(new String(Files.readAllBytes(path)));
            System.out.println("-".repeat(30));
            System.out.println(Files.readString(path));

            Pattern p = Pattern.compile("(.{15})(.{3})(.{12})(.{8})(.{2}).*");
            Set<String> values = new TreeSet<>();
            Files.readAllLines(path).forEach(s->{
                if(!s.startsWith("Name")){
                    Matcher m = p.matcher(s);
                    if(m.find()){
                        values.add(m.group(3).trim());
                    }
                }
            });
            System.out.println(values);
            try(var streamResult = Files.lines(path)){
                var result = streamResult
                        .skip(1)
                        .map(p::matcher)
                        .filter(Matcher::find)
                        .map(x->x.group(3).trim())
                        .sorted()
                        .distinct()
                        .toArray(String[]::new);
                System.out.println(Arrays.toString(result));
            }
            try(var streamResult = Files.lines(path)){
                var result = streamResult
                        .skip(1)
                        .map(p::matcher)
                        .filter(Matcher::find)
                        .collect(Collectors.groupingBy(x->x.group(3).trim(),
                                Collectors.counting()));
                result.forEach((k,v)->System.out.println(k + ":" + v));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
