package Section17.StreamingStudent;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        List<Student> students = Stream.generate(()->Student.getRandomStudent(pymc,jmc))
                .limit(1000)
                .toList();
        Set<Student> AustraliaStudents = students.stream().filter(x->x.getCountryCode().equalsIgnoreCase("AU"))
                .collect(Collectors.toSet());

        Set<Student> youngStudents = students.stream()
                .filter(x->x.getAgeEnrolled() < 30)
                .filter(x->x.getCountryCode().equalsIgnoreCase("AU"))
                .collect(()->new TreeSet<>(Comparator.comparing(Student::getStudentId)),TreeSet::add,TreeSet::addAll);

        youngStudents.forEach(x->System.out.println(x.getStudentId()));

        String countryList = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .reduce("hello",(k,v)->k + " " + v);
        System.out.println(countryList);

    }
}
