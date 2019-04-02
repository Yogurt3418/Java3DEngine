import java.util.ArrayList;

public class Point {
  private double x;
  private double y;
  private double z;

  //TODO: Clean these up and structure this with getters/setters/etc
  int onScreenX = 0;
  int onScreenY = 0;
  
  //just put all the points into a big array that sounds like a good idea
  public static ArrayList<Point> globalPoints = new ArrayList<Point>();
  
  public Point(double x, double y, double z, boolean cam) {

    this.x = x;
    this.y = y;
    this.z = z;
    if(!cam)
      globalPoints.add(this);

  }

  /**
   * 
   * @return X Value for this point
   */
  public double getX() {
    return x;
  }

  /**
   * 
   * @return Y Value for this point
   */
  public double getY() {
    return y;
  }

  /**
   * 
   * @return Z Value for this point
   */
  public double getZ() {
    return z;
  }
  
/**
 * 
 * @param newX - New X coordinate
 * @param newY - New Y coordinate
 * @param newZ - New Z coordinate
 */
  public void updatePoint(double newX, double newY, double newZ) {

    this.x = newX;
    this.y = newY;
    this.z = newZ;

  }

  public void incrementPoint(double xInc, double yInc, double zInc) {

    this.x += xInc;
    this.y += yInc;
    this.z += zInc;

  }

  public double length() {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
  }
  
  public double length2d() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }
 

  public static double dotProduct(Point a, Point b) {

    return (a.x * b.x) + (a.y * b.y) + (a.z * b.z);
  }

  public static double angleBetweenPoints(Point a, Point b) {

    return dotProduct(a, b) / (Math.abs(a.length()) * Math.abs(b.length()));
  }

  public static Point crossProduct(Point a, Point b) {

    return new Point((a.y * b.z) - (a.z * b.y), (a.z * b.x) - (a.x * b.z),
        (a.x * b.y) - (a.y * b.x), false);

  }
  
  public static boolean comparePoints(Point A, Point B) {
    
    if((A.getX() == B.getX()) & (A.getY() == B.getY())){
      return true;
    }
    return false;
  }

}
