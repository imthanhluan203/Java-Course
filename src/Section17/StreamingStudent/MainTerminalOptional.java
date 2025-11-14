package Section17.StreamingStudent;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainTerminalOptional {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        List<Student> students = Stream.generate(()->Student.getRandomStudent(pymc,jmc))
                .limit(1000)
                .collect(Collectors.toList());
        students.stream()
                .filter(s -> s.getAge() <= 21)
                .findAny()
                .ifPresentOrElse(s->System.out.printf("Student %d from %s is %d%n",
                        s.getStudentId(),s.getCountryCode(),s.getAge()),
                        ()->System.out.println("Didn't find anyone under 21"));

        var a = students.stream().collect(Collectors.groupingBy(
                Student::getCountryCode,
                Collectors.filtering(x -> x.getYearEnrolled() > 2021, Collectors.toList())

        ));
        var b = students.stream().limit(10).collect(Collectors.partitioningBy(
                x -> x.getYearEnrolled() > 2021
        ));
        a.forEach((k,v) -> System.out.println(k + ":" + v));


    }
}
