import java.util.ArrayList;

public class Camera{

  public static ArrayList<Camera> globalCameras = new ArrayList<Camera>();
  
  Point camCenter;
  Point camLeft;
  //Point camRight;
  Point camTop;
  //Point camBottom;
  
  public static int fovXY = 90;
  public static int fovXZ = 90;

  //RADIANS
  double xyAngle;
  double xzAngle;
  
  public Camera(double x, double y, double z, double angXY, double angXZ) {
    
    
    this.xyAngle = angXY;
    this.xzAngle = angXZ;
    
    this.camCenter = new Point(x,y,z, false);
    
    
    //CHECK THESE TO MAKE SURE THEY ARE RIGHT
    this.camLeft = new Point(x+Math.cos(Math.toRadians(this.xyAngle)), y+Math.sin(Math.toRadians(this.xyAngle)),z, true);
    
    this.camTop = new Point(x+Math.cos(Math.toRadians(this.xzAngle)), y, z+Math.sin(Math.toRadians(this.xzAngle)), true);
    //this.camTop = new Point(Math.cos(Math.toRadians(this.fovXZ/2)), 0, Math.sin(Math.toRadians(this.fovXZ/2)), true);
    
    globalCameras.add(this);
  }
  

  public double getXyAngle() {
    return xyAngle;
  }

  public void setXyAngle(double xyAngle) {
    
    this.xyAngle = xyAngle;
    
    
    this.camLeft.updatePoint(this.camCenter.getX()+Math.cos(Math.toRadians(this.xyAngle)), this.camCenter.getY()+Math.sin(Math.toRadians(this.xyAngle)),this.camCenter.getZ());
    
    //this.camTop.updatePoint(this.camCenter.getX()+Math.cos(Math.toRadians(this.xzAngle)), this.camCenter.getY(), this.camCenter.getZ()+Math.sin(Math.toRadians(this.xzAngle)));
    
  }

  public double getXzAngle() {
    return xzAngle;
  }

  public void setXzAngle(double xzAngle) {
    
    this.xzAngle = xzAngle;
    
    //this.camLeft.updatePoint(this.camCenter.getX()+Math.cos(Math.toRadians(this.xyAngle)), this.camCenter.getY()+Math.sin(Math.toRadians(this.xyAngle)),this.camCenter.getZ());
    
    this.camTop.updatePoint(this.camCenter.getX()+Math.cos(Math.toRadians(this.xzAngle)), this.camCenter.getY(), this.camCenter.getZ()+Math.sin(Math.toRadians(this.xzAngle)));
    
    
  }
  
  public void setCamPos(double x, double y, double z) {
    
    this.camCenter.updatePoint(x,y,z);
    

    //CHECK THESE TO MAKE SURE THEY ARE RIGHT
    this.camLeft.updatePoint(x+Math.cos(Math.toRadians(this.xyAngle)), y+Math.sin(Math.toRadians(this.xyAngle)),z);
    //= new Point(x+Math.cos(Math.toRadians(this.xyAngle)), y+Math.sin(Math.toRadians(this.xyAngle)),z, true);
    
    this.camTop.updatePoint(x+Math.cos(Math.toRadians(this.xzAngle)), y, z+Math.sin(Math.toRadians(this.xzAngle)));// = new Point(x+Math.cos(Math.toRadians(this.xzAngle)), y, z+Math.sin(Math.toRadians(this.xzAngle)), true);

    
  }
}
