package Section12.Comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Integer five = 5;
        Integer arr[] = {0,5,10,-50,50};
        for(Integer i : arr){
            int val = five.compareTo(i);
            System.out.printf("%d %s %d: compareTo:%d%n",five,
                    val == 0 ? "=" : (val < 0 ? "<":">"),i,val);
        }
        Student Tim = new Student("Tim");
        Student[] students = {new Student("Zach"),new Student("Tim"),
                                new Student("Ann")};
        Arrays.sort(students,new StudentCompare().reversed());
        System.out.println(Arrays.toString(students));
    }
}
class StudentCompare implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return o1.GPA.compareTo(o2.GPA);
    }
}
class Student implements Comparable<Student>{
    private String name;
    Double GPA = new Random().nextDouble(1,4);

    public Student(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "[name:%s | GPA:%.2f]".formatted(name,GPA);
    }

    @Override
    public int compareTo(Student o) {
        return GPA.compareTo(o.GPA);
    }
}