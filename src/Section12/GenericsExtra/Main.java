package Section12.GenericsExtra;

import Section12.GenericsExtra.util.QueryList;

import java.util.ArrayList;
import java.util.List;

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
        var queryList = new QueryList<>(lpaStudent);
        var matches = queryList.getMatches("Course","Python");
        printmoreList(matches);

        var student2021 = QueryList.getMatches(students,"YearStarted","2021");
        printmoreList(student2021);
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
