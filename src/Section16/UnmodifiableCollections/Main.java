package Section16.UnmodifiableCollections;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringBuilder bobNotes = new StringBuilder();
        StringBuilder billNotes = new StringBuilder("Bill struggles with generics");
        Student bob = new Student("Bob",bobNotes);
        Student bill = new Student("Bill",billNotes);
        List<Student> students = new ArrayList<>(List.of(bob,bill));
        List<Student> studentsCopy = new ArrayList<>(students);
        List<Student> studentsCopy2 = List.copyOf(students);
        studentsCopy.add(new Student("Bonnie",new StringBuilder()));
        //studentsCopy2.add(new Student("Bonnie",new StringBuilder()));

        StringBuilder bonnieNote = studentsCopy.get(2).getStudentNotes();
        bonnieNote.append("Bonnie is taking 3 of my courses");
        bobNotes.append("Bob was one of my first students");
        students.forEach(x->System.out.println(x));
        System.out.println("-".repeat(30));


        studentsCopy.forEach(x->System.out.println(x));
        System.out.println("-".repeat(30));

        studentsCopy2.forEach(x->System.out.println(x));
        System.out.println("-".repeat(30));
    }
}
