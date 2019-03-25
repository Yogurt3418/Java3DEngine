import java.awt.Color;
import java.util.ArrayList;

public class Polygon {

    public Point[] points = {null, null, null};
    public Line[] lines = {null, null};
    public Color color;
    
    public static ArrayList<Polygon> globalPolygons = new ArrayList<Polygon>();
    
    public Polygon(Point a, Point b, Point c) {
      
      this.points[0] = a;
      this.points[1] = b;
      this.points[2] = c;
      
      globalPolygons.add(this);
      
    }
    
    
  
}
