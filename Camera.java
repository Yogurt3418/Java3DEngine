import java.util.ArrayList;

public class Camera{

  public static ArrayList<Camera> globalCameras = new ArrayList<Camera>();
  
  Point camCenter;
  Point camLeft;
  Point camTop;
  Point camBack;
  
  //IDEA : make a point in each direction, x,y,z
  
  private static int fovXY = 90;
  private static int fovXZ = 90;

  //RADIANS
  private double xyAngle;
  private double xzAngle;
  
  /**
   * Create new camera object with given values
   * 
   * @param x X Position
   * @param y X Position
   * @param z X Position
   * @param angXY Angle in XY plane
   * @param angXZ Angle in XZ plane
   */
  public Camera(double x, double y, double z, double angXY, double angXZ) {
    
    
    this.xyAngle = angXY;
    this.xzAngle = angXZ;
    
    this.camCenter = new Point(x,y,z, false);
    
    this.camLeft = new Point(x+Math.sin(Math.toRadians(this.xyAngle)), y+Math.cos(Math.toRadians(this.xyAngle)),z, true);
    //this.camLeft = new Point(x+Math.cos(Math.toRadians(this.xyAngle)), y+Math.sin(Math.toRadians(this.xyAngle)),z, true);
    
    this.camTop = new Point(x+Math.sin(Math.toRadians(this.xzAngle)), y, z+Math.cos(Math.toRadians(this.xzAngle)), true);
    //this.camTop = new Point(x+Math.cos(Math.toRadians(this.xzAngle)), y, z+Math.sin(Math.toRadians(this.xzAngle)), true);
    
    this.camBack = new Point(x-Math.cos(Math.toRadians(this.xyAngle)), y-Math.sin(Math.toRadians(this.xyAngle)), z-Math.sin(Math.toRadians(this.xzAngle)), true);
    
    globalCameras.add(this);
  }
  
  
  /**
   * 
   * @return
   */
  public static int getFovXY() {
    return fovXY;
  }

  public static void setFovXY(int fovXY) {
    Camera.fovXY = fovXY;
  }
  
  public static int getFovXZ() {
    return fovXZ;
  }
  
  public static void setFovXZ(int fovXZ) {
    Camera.fovXZ = fovXZ;
  }
  
/**
 * returns camera angle in XY plane
 * 
 * @return xyAngle
 */
  public double getXyAngle() {
    return this.xyAngle;
  }

  /**
   * Change camera Angle in XY plane and update points based on this new value
   * 
   * @param xyAngle New angle value (in degrees) for camera in XY plane
   */
  public void setXyAngle(double xyAngle) {
    
    this.xyAngle = xyAngle;
    
    
    this.camLeft.updatePoint(this.camCenter.getX()+Math.sin(Math.toRadians(this.xyAngle)), this.camCenter.getY()+Math.cos(Math.toRadians(this.xyAngle)),this.camCenter.getZ());
    
    this.camBack.updatePoint(this.camCenter.getX()-Math.cos(Math.toRadians(this.xyAngle)), this.camCenter.getY()-Math.sin(Math.toRadians(this.xyAngle)),this.camCenter.getZ());
    //this.camTop.updatePoint(this.camCenter.getX()+Math.cos(Math.toRadians(this.xzAngle)), this.camCenter.getY(), this.camCenter.getZ()+Math.sin(Math.toRadians(this.xzAngle)));
    
  }

  /**
   * returns camera angle in XZ plane
   * 
   * @return xzAngle
   */
  public double getXzAngle() {
    return xzAngle;
  }

  /**
   * Change camera Angle in XZ plane and update points based on this new value
   * 
   * @param xyAngle New angle value (in degrees) for camera in XZ plane
   */
  public void setXzAngle(double xzAngle) {
    
    this.xzAngle = xzAngle;
    
    //this.camLeft.updatePoint(this.camCenter.getX()+Math.cos(Math.toRadians(this.xyAngle)), this.camCenter.getY()+Math.sin(Math.toRadians(this.xyAngle)),this.camCenter.getZ());
    
    this.camTop.updatePoint(this.camCenter.getX()+Math.sin(Math.toRadians(this.xzAngle)), this.camCenter.getY(), this.camCenter.getZ()+Math.cos(Math.toRadians(this.xzAngle)));
    
    this.camBack.updatePoint(this.camCenter.getX()-Math.cos(Math.toRadians(this.xzAngle)), this.camCenter.getY(), this.camCenter.getZ()-Math.sin(Math.toRadians(this.xzAngle)));
  }
  
  
  /**
   * Change camera position to new point
   * 
   * @param x New x position
   * @param y New y position
   * @param z New z position
   */
  public void setCamPos(double x, double y, double z) {
    
    this.camCenter.updatePoint(x,y,z);
    

    //CHECK THESE TO MAKE SURE THEY ARE RIGHT
    this.camLeft.updatePoint(x+Math.sin(Math.toRadians(this.xyAngle)), y+Math.cos(Math.toRadians(this.xyAngle)),z);
    //= new Point(x+Math.cos(Math.toRadians(this.xyAngle)), y+Math.sin(Math.toRadians(this.xyAngle)),z, true);
    
    this.camTop.updatePoint(x+Math.sin(Math.toRadians(this.xzAngle)), y, z+Math.cos(Math.toRadians(this.xzAngle)));// = new Point(x+Math.cos(Math.toRadians(this.xzAngle)), y, z+Math.sin(Math.toRadians(this.xzAngle)), true);

    
  }
}
