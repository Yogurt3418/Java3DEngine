import java.util.ArrayList;

public class Line {

  private Point a;
  private Point b;
  
  Point[] points = {null, null};
  
  public Point getA() {
    return a;
  }

  public void setA(Point a) {
    this.a = a;
  }

  public Point getB() {
    return b;
  }

  public void setB(Point b) {
    this.b = b;
  }

  
  public static ArrayList<Line> globalLines = new ArrayList<Line>();
  
  public Line(Point a, Point b) {
    
    this.a = a;
    this.b = b;
    
    this.points[0] = a;
    this.points[1] = b;
    globalLines.add(this);
    
  }
  
  public double length() {
    
    return new Point(this.a.getX()-this.b.getX(), this.a.getY()-this.b.getY(), this.a.getZ()-this.b.getZ(), false).length();
    
  }
  
}
