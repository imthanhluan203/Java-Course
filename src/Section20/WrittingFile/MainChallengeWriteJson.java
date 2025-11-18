package Section20.WrittingFile;

import Section20.WrittingFile.student.Course;
import Section20.WrittingFile.student.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class MainChallengeWriteJson {
    public static void main(String[] args) {
        String header = """
                Student Id,Country Code,Enrolled Year,Age,Gender,\
                Experienced,Course Code,Engagement Month,Engagement Year,\
                Engagement Type""";
        Course jmc = new Course("JMC","Java Masterclass");
        Course pymc = new Course("PYC","Python Masterclass");
        String students = Stream
                .generate(()->Student.getRandomStudent(jmc,pymc))
                .limit(1000)
                .map(Student::toJson)
                .collect(Collectors.joining(",","[","]"));
        System.out.println(students);
        try(BufferedWriter writer = Files.newBufferedWriter(Path.of("students.json"))){
            writer.write(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
