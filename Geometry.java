import java.util.ArrayList;

public class Geometry {

  public ArrayList<Polygon> polygons;
  
  public static ArrayList<Geometry> globalGeometry = new ArrayList<Geometry>();
  
public Geometry() {
    
    globalGeometry.add(this);
    this.polygons = new ArrayList<Polygon>();
  }
  
  public Geometry(ArrayList<Polygon> Polygons) {
    
    this.polygons = Polygons;
    globalGeometry.add(this);
    
  }
  
  public void addPolygon(Polygon p) {
    polygons.add(p);
  }
  
}
