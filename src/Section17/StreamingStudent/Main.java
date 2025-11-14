package Section17.StreamingStudent;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        Student[] students = new Student[500];
        Arrays.setAll(students,i->Student.getRandomStudent(pymc,jmc));
        var maleStudents = Arrays.stream(students).filter(x->x.getGender().equalsIgnoreCase("M"))
                .limit(10)
                .collect(Collectors.toList());
        Collections.shuffle(maleStudents);

    }

}
