package Section12.GenericsExtra;

import Section12.GenericsExtra.util.QueryItem;
import Section12.GenericsExtra.util.QueryList;

import java.util.*;

class CompareStudent <T extends QueryItem> implements Comparator<T>{

    @Override
    public int compare(T o1, T o2) {
        if(o1 instanceof LPAStudent s1 && o2 instanceof LPAStudent s2){
            return Double.valueOf(s1.getPercentComplete()).compareTo(Double.valueOf(s2.getPercentComplete()));
        }
        if(o1 instanceof Student s1 && o2 instanceof Student s2){
            return Integer.valueOf(s1.getID()).compareTo(Integer.valueOf(s2.getID()));
        }
        return 0;
    }
}
public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        for(int i = 0;i<10;i++){
            students.add(new Student());
        }
        printmoreList(students);

        List<LPAStudent> lpaStudent = new ArrayList<>();
        for(int i = 0;i<10;i++){
            lpaStudent.add(new LPAStudent());
        }
        printmoreList(lpaStudent);
        QueryList<Student> querylist_1 = new QueryList<>(students);
        System.out.println("---".repeat(30));
        for(var ob:querylist_1){
            System.out.println(ob);
        }
        System.out.println("---".repeat(30));
        Collections.sort(querylist_1,new CompareStudent<Student>().reversed());
        for(var ob:querylist_1){
            System.out.println(ob);
        }
        System.out.println("---".repeat(30));
        QueryList<LPAStudent> querylist_2 = new QueryList<>(lpaStudent);
        for(var ob:querylist_2){
            System.out.println(ob);
        }
        System.out.println("---".repeat(30));
        Collections.sort(querylist_2,new CompareStudent<LPAStudent>());
        for(var ob:querylist_2){
            if(ob.matchFieldValue("","60"))
                System.out.println(ob);
        }

    }
    public static void printmoreList(List<? extends Student> students){
//        Student last = students.get(students.size()-1);
//        students.set(0,last);
        for(var student : students){
            System.out.println(student.getYearStarted()+":"+student);
        }
        System.out.println();
    }
}
