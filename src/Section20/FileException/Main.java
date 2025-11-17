package Section20.FileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args)  {
        System.out.println(new File("").getAbsoluteFile());
        String filename = "src/Section20/Files/testing.csv";
        File file =new File(filename);
        if(!file.exists()){
            System.out.println("1. I can't run unless this file exists");
            return;
        }
        System.out.println("I'm good to go");
        Path path = Paths.get("src/Section20/Files/testing.csv");
        if(!Files.exists(path)){
            System.out.println("2. I can't run unless this file exists");
            return;
        }
        System.out.println("I'm good to go");
    }
    private static void testFile(String fileName){
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Have some problem");
        }
        System.out.println("The file oke");
    }
    private static void testFile2(String filename){
        try(FileReader reader = new FileReader(filename)){

        } catch (FileNotFoundException e) {
            System.out.println("File '" + filename + "' does not exist");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("Maybe I'd log so");
        }
        System.out.println("File exists and able to use as a resource");
    }
}
