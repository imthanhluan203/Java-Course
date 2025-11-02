package Section7.Composition;

public class Line {
    private Point begin;
    private  Point end;
    public Line(Point begin,Point end){
        this.begin = begin;
        this.end = end;
    }
    public Line(int beginX, int beginY, int endX, int endY){
        begin = new Point(beginX,endX);
        end = new Point(beginY,endY);
    }

    public Point getBegin() {
        return begin;
    }

    public void setBegin(Point begin) {
        this.begin = begin;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
    public int getBeginX() { return begin.getX(); }
    public int getBeginY() { return end.getX(); }
    public int getEndX() { return begin.getEnd(); }
    public int getEndY() { return end.getEnd(); }

    public void setBeginX(int x) { begin.setX(x); }
    public void setBeginY(int x) { end.setX(x); }
    public void setBeginXY(int x) {
        begin.setX(x);
        end.setX(x);
    }
    public void setEndX(int y) { begin.setEnd(y); }
    public void setEndY(int y) { end.setEnd(y); }
    public void setEndXY(int y) {
        begin.setEnd(y);
        end.setEnd(y);
    }
    public int getLength() {
        int xdiff = begin.getX() - end.getX();
        int ydiff = begin.getEnd() - end.getEnd();
        return (int)Math.sqrt(xdiff*xdiff + ydiff*ydiff);
    }
    public double getGradient() {
        int xdiff = begin.getX() - end.getX();
        int ydiff = begin.getEnd() - end.getEnd();
        return Math.atan2(ydiff,xdiff);
    }
    @Override
    public String toString() {
        return "Line{" +
                "begin=" + begin +
                ", end=" + end +
                '}';
    }
}
