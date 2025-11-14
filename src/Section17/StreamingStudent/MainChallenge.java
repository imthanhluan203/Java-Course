package Section17.StreamingStudent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallenge {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        Course gmc = new Course("GMC", "Creating Games in Java");
        Student[] student_pymc  = new Student[50];
        Student[] student_jmc = new Student[100];
        Arrays.setAll(student_pymc,i->Student.getRandomStudent(pymc));
        Arrays.setAll(student_jmc,i->Student.getRandomStudent(jmc));
        List<Student> student = new ArrayList<>(Arrays.asList(student_pymc));
        student.addAll(Arrays.asList(student_jmc));
        long pymc1 = student.stream().map(x -> x.getEngagementMap().keySet())
                .filter(x -> x.contains("JMC"))
                .count();
        System.out.println(pymc1);

        List<Student> studen12A = Stream.generate(() -> Student.getRandomStudent(pymc,jmc,gmc))
                .limit(5000)
                .collect(Collectors.toList());
        var percentCompleteJMC=studen12A.stream()
                .map(x->x.getPercentComplete("JMC"))
                        .reduce(new double[]{0,0},
                                (k,v)-> new double[]{k[0] + v, k[1] + 1},
                                (a, b) -> {
                                    return new double[]{a[0] + b[0], a[1] + b[1]};
                                });
        var averageScore = percentCompleteJMC[0]/percentCompleteJMC[1];
        var studentHaveCondition = studen12A.stream().filter(x -> x.getEngagementMap().keySet().contains("JMC"))
                .filter(x->x.getPercentComplete("JMC") > averageScore *1.25)
                .peek(x->System.out.println(x.getPercentComplete("JMC")))
                .collect(Collectors.toList());
        studentHaveCondition.forEach(System.out::println);
    }
}
