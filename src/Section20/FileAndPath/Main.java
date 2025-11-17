package Section20.FileAndPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        useFile("testfile.txt");
        usePath("pathFile.txt");
    }
    private static void useFile(String fileName){
        File file = new File(fileName);
        boolean fileExists = file.exists();
        System.out.printf("FIle '%s' %s%n",fileName,
                fileExists ?"exist." : "does not exist");
        if(fileExists){
            System.out.println("Deleting File: " + fileName);
            fileExists = !file.delete();
        }
        if(!fileExists){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
            System.out.println("Created File:" + fileName);
            if(file.canWrite()){
                System.out.println("Would write to file here");
            }
        }
    }
    private static void usePath(String fileName){
        Path path = Path.of(fileName);
        boolean fileExists = Files.exists(path);
        System.out.printf("FIle '%s' %s%n",fileName,
                fileExists ?"exist." : "does not exist");
        if(fileExists){
            System.out.println("Deleting File: " + fileName);
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!fileExists){
            try {
                Files.createFile(path);
                System.out.println("Created File:" + fileName);
                if (Files.isWritable(path)) {
                    System.out.println("Would write to file here");
                    try {
                        Files.writeString(path, """
                                Here is some data,
                                For my file,
                                just to prove,
                                Using the Files class and path are better
                                """);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("I can read too");
                    System.out.println("-".repeat(50));
                    Files.readAllLines(path).forEach(System.out::println);
                }
            }catch (IOException e) {
                System.out.println("Something went wrong");
            }
        }
    }
}
