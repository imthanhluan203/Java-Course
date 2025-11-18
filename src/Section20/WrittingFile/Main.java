package Section20.WrittingFile;

import Section20.WrittingFile.student.Course;
import Section20.WrittingFile.student.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String header = """
                Student Id,Country Code,Enrolled Year,Age,Gender,\
                Experienced,Course Code,Engagement Month,Engagement Year,\
                Engagement Type""";
        Course jmc = new Course("JMC","Java Masterclass");
        Course pymc = new Course("PYC","Python Masterclass");
        List<Student> students = Stream
                .generate(()->Student.getRandomStudent(jmc,pymc))
                .limit(25)
                .toList();
//        System.out.println(header);
//        students.forEach(s->s.getEngagementRecords().forEach(System.out::println));
        Path path = Path.of("students.csv");
//        try {
//            Files.writeString(path,header);
//            for(var student : students){
//                Files.write(path,student.getEngagementRecords(), StandardOpenOption.APPEND);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try{
//            List<String> data = new ArrayList<>();
//            data.add(header);
//            for(Student student : students){
//                data.addAll(student.getEngagementRecords());
//            }
//            Files.write(path,data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of("take2.csv"))) {
            writer.write(header);
            writer.newLine();
            int count = 0;
            for(var student : students){
                for(var record : student.getEngagementRecords()){
                    writer.write(record);
                    writer.newLine();
                    count++;
                    if(count%5 == 0){
                        Thread.sleep(2000);
                        System.out.println(count);
                    }
                    if(count % 10 == 0){
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter writer = new FileWriter("take3.csv")) {
            writer.write(header);
            writer.write(System.lineSeparator());
            for(var student : students){
                for(var record : student.getEngagementRecords()){
                    writer.write(record);
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter("take4.txt")) {
            writer.println(header);
            for(var student : students){
                for(var record : student.getEngagementRecords()){
                    writer.println(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
