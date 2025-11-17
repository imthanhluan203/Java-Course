package Section20.ReadingFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try(FileReader reader = new FileReader("src/Section20/ReadingFile/file.txt")){
            char[] block = new char[1000];
            int data;
            while((data = reader.read(block)) != -1){
                String content = new String(block,0 ,data);
                System.out.printf("---> [%d chars] %s%n",data,content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("===".repeat(50));
        try(BufferedReader bReader = new BufferedReader(new FileReader("src/Section20/ReadingFile/fixedWidth.txt"))){
            bReader.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
