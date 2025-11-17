package Section20.FileListings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("");
        System.out.println("cwd = " + path.toAbsolutePath());
        try (Stream<Path> paths = Files.list(path)) {
            paths
                    .map(x->listDir(x))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-".repeat(30));
        try (Stream<Path> paths = Files.walk(path,2)) {
            paths
                    .filter(Files::isRegularFile)
                    .map(x->listDir(x))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-".repeat(30));
        try (Stream<Path> paths = Files.find(path,1,(p,attr)->{
            return attr.isRegularFile();
        })) {
            paths
                    .map(x->listDir(x))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===================Directory Stream==========");
        try(var dirs = Files.newDirectoryStream(path, "*.xml")){
            dirs.forEach(d->System.out.println(Main.listDir(d)));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static String listDir(Path path){
        try {
            boolean isDir = Files.isDirectory(path);
            FileTime dateField = Files.getLastModifiedTime(path);
            return "%s %-5s %12s %s".formatted(dateField,isDir ? "<DIR>" : "",isDir ? "" : Files.size(path), path);
        } catch (IOException e) {
            System.out.println("Whoops! Something went wrong with " + path);
            return path.toString();
        }
    }
}
