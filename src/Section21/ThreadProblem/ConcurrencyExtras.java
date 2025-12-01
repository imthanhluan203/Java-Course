package Section21.ThreadProblem;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

record Student(String name, int enrolledYear, int studentId) implements Comparable<Student>{

    @Override
    public int compareTo(Student o) {
        return this.studentId - o.studentId;
    }
}
class StudentId {
    private final AtomicInteger id = new AtomicInteger(0);
    public int getId(){
        return id.get();
    }
    public int getNextId(){
        return id.incrementAndGet();
    }
}
public class ConcurrencyExtras {
    private static Random random = new Random();
    private static ConcurrentSkipListSet<Student> studentSet = new ConcurrentSkipListSet<>();

    public static void main(String[] args) {
        StudentId idGenerator = new StudentId();
        Callable<Student> studentMaker = () -> {
            int studentId = idGenerator.getNextId();
            Student student = new Student("Tim " + studentId, random.nextInt(2018,2025),studentId );
            studentSet.add(student);
            return student;
        };
        var executor = Executors.newCachedThreadPool();
        for(int i = 0;i<10;i++){
            studentSet.clear();
            try {
                var futures = executor.invokeAll(
                        Collections.nCopies(10,studentMaker)
                );
                System.out.println("Print size:" + studentSet.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();

    }
}
