package Section7.Composition;

public class Point {
    private int start;
    protected int end;
    public Point(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + start +
                ", y=" + end +
                '}';
    }
    public int getX() {
        return start;
    }
    public int getEnd() {
        return end;
    }
    public void setX(int x) {
        this.start = x;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
