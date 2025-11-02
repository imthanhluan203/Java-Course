package Section7.Composition;

public class Main {
    public static void main(String[] args) {
        Line l1 = new Line(0, 0, 3, 4);
        System.out.println(l1);
        System.out.println(l1.getLength());
        Point p1 = new Point(2,3);
        Point p2 = new Point(10,8);
        Line l2 = new Line(p1, p2);
        System.out.println(l2);
        System.out.println(l2.getLength());
    }
}
