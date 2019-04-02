import java.awt.Color;
import java.util.ArrayList;

public class Polygon {

    public Line[] lines = {null, null};
    public Point[] points = {null, null, null};
    public Color color = null;
    
    public static ArrayList<Polygon> globalPolygons = new ArrayList<Polygon>();
    
    public Polygon(Point a, Point b, Point c, Color col) {
      
      lines[0] = new Line(a,b);
      lines[1] = new Line(b,c);
      color = col;
      
      globalPolygons.add(this);
      
    }
    
public Polygon(Line a, Line b, Color col) throws Exception {
      
  //This is a terrible way of doing this, think of a better one!
      if(Line.polygonCompatible(a,b)) {
        
        lines[0] = a;
        lines[1] = b;
        color = col;
        
        if(Point.comparePoints(a.getA(), b.getA()) | Point.comparePoints(a.getB(), b.getA())) {
          points[0] = a.getA();
          points[1] = a.getB();
          points[2] = b.getB();
        }else if(Point.comparePoints(a.getA(), b.getB()) | Point.comparePoints(a.getB(), b.getB())) {
          points[0] = a.getA();
          points[1] = a.getB();
          points[2] = b.getA();
        }
        
      }else {
        throw new Exception("Polygon Constructor : THESE TWO LINES DO NOT SHARE A COMMON POINT!");
      }
  
      
      
      globalPolygons.add(this);
      
    }
  
}
