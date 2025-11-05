package Section12.GenericPractice.GenericChallenge;

import java.util.ArrayList;

interface Mappable{
    void render();
}
class Point implements Mappable{
    private String name;
    private double x;
    private double y;

    public Point(String name, String location) {
        this.name = name;
        String[] local = location.split(",");
        this.x = Double.parseDouble(local[0]);
        this.y = Double.parseDouble(local[1]);
    }
    @Override
    public void render() {
        System.out.printf("Render %s as %s ([%.4f,%.4f])%n",name,getClass().getSimpleName(),x,y);
    }
}
class Line implements Mappable{
    private String name;
    ArrayList<ArrayList<Double>> setPoint = new ArrayList<>();

    public Line(String name, String location) {
        this.name = name;
        String[] locationArr = location.split(",");
        int i=0;
        while (i< locationArr.length){
            ArrayList<Double> temp = new ArrayList<>();
            temp.add(Double.parseDouble(locationArr[i]));
            temp.add(Double.parseDouble(locationArr[i+1]));
            setPoint.add(temp);
            i+=2;
        }
    }
    @Override
    public void render() {
        System.out.printf("Render %s as %s",name,getClass().getSimpleName());
        System.out.print(" ([");
        for(var ob : setPoint){
            System.out.printf("[%.4f, %.4f]",ob.get(0),ob.get(1));
        }
        System.out.print("])");
        System.out.println();
    }
}
public class Main {
    public static void main(String[] args) {
        Line sgr1 = new Line("Sai gon river 1","47.215,-95.2348,29.1566,-89.2495,35.1556,-90.0659");
        Line sgr2 = new Line("Sai gon river 2","47.215,-95.2348,29.1566,-89.2495,35.1556,-90.0659");
        Line sgr3 = new Line("Sai gon river 3","47.215,-95.2348,29.1566,-89.2495,35.1556,-90.0659");
        Line sgr4 = new Line("Sai gon river 4","47.215,-95.2348,29.1566,-89.2495,35.1556,-90.0659");
        Layer<Line> allRiver = new Layer<>();
        allRiver.add(sgr1);
        allRiver.add(sgr2);
        allRiver.add(sgr3);
        allRiver.add(sgr4);
        allRiver.printAllLayer();
    }
    public static void printInfo(Mappable t){
        t.render();
    }
}
