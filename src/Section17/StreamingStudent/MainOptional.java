package Section17.StreamingStudent;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        List<Student> students = Stream.generate(()->Student.getRandomStudent(pymc,jmc))
                .limit(1000)
                .collect(Collectors.toList());
//        students.add(0,null);
        Optional<Student> o1 = getStudent(students, "first");
        System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
        o1.ifPresent(System.out::println);
    }
    private static Optional<Student> getStudent(List<Student> list,String type){
        if(list == null || list.isEmpty()){
            return Optional.empty();
        } else if (type.equalsIgnoreCase("first")) {
            return Optional.ofNullable(list.get(0));
        } else if (type.equalsIgnoreCase("last")) {
            return Optional.ofNullable(list.get(list.size() - 1));
        }
        return  Optional.of(list.get(new Random().nextInt(0,list.size())));
    }
}
