package Section20.ReadingFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class MainScanner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(Path.of("src/Section20/ReadingFile/fixedWidth.txt"))) {
            var results =
                    scanner.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
                            .skip(1)
                            .map(m -> m.group(5).trim())
                            .distinct()
                            .sorted()
                            .toArray(String[]::new);
            System.out.println(Arrays.toString(results));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
