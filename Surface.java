import java.awt.Color;
import java.util.ArrayList;

public class Surface {

  //try to phase out points being used in the Surface class
  public Point[] points = {null, null, null, null};
  public Line[] lines = {null, null, null, null};
  public Color color;
  
  public static ArrayList<Surface> globalSurfaces = new ArrayList<Surface>();
  
  public Surface(Point a, Point b, Point c, Point d, Color col) {
    
    this.points[0] = a;
    this.points[1] = b;
    this.points[2] = c;
    this.points[3] = d;
    this.color = col;
    
    globalSurfaces.add(this);
    
  }
  
  public Surface(Line a, Line b) {
    
    this.points[0] = a.getA();
    this.points[1] = a.getB();
    this.points[2] = b.getA();
    this.points[3] = b.getB();
    
    this.lines[0] = a;
    this.lines[1] = b;
    this.lines[2] = new Line(a.getA(), b.getA());
    this.lines[3] = new Line(a.getB(), b.getB());
    
    globalSurfaces.add(this);
        
  }
}
