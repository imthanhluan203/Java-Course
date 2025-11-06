package Section12.GenericsExtra;

import Section12.GenericsExtra.util.QueryItem;

import java.util.Random;

public class Student implements QueryItem {
    protected String name;
    protected String course;
    protected int yearStarted;
    private int ID;
    private static int studentId = 1;
    protected static Random random = new Random();
    private static String[] firstNames = {"Ann", "Bill", "Cathy", "John", "Tim"};
    private static String[] courses = {"C++","Java","Python"};
    public Student(){
        int lastNameIndex = random.nextInt(65,91);
        name = firstNames[random.nextInt(5)] + " " + (char)lastNameIndex;
        course = courses[random.nextInt(3)];
        yearStarted = random.nextInt(2018,2023);
        ID = studentId++;

    }
    public int getID(){
        return ID;
    }
    @Override
    public String toString(){
        return "%-15d %-15s %-15s %d".formatted(ID,name,course,yearStarted);
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        String fName = fieldName.toUpperCase();
        return switch (fName){
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted == Integer.parseInt(value);
            default -> false;
        };
    }
}
