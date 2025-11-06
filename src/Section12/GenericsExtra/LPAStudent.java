package Section12.GenericsExtra;

public class LPAStudent extends Student {
    private double percentComplete;
    private int ID;
    private static int studentId = 1;
    public LPAStudent() {
//        super();
        percentComplete = random.nextDouble(0,100.001);
        ID = studentId++;
    }

    @Override
    public String toString() {
        return "%-15d %-15s %-15s %d %8.1f%%".formatted(ID,name,course,yearStarted,percentComplete);
    }
    public double getPercentComplete(){
        return percentComplete;
    }

    @Override
    public int getID() {
        return super.getID();
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        double valuedou = Double.valueOf(value);
        return percentComplete < valuedou;
    }
}
