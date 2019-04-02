import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public class Display extends Canvas {


   //I have no idea what this is or why it is here but it seems important
  private static final long serialVersionUID = 1L;

  private int consoleY = 10;
  
  private JFrame frame;

  private void consolePrint(Graphics g, String str) {

    g.drawString(str, 5, consoleY);
    consoleY += 10;

  }

  public JFrame getFrame() {
    return this.frame;
  }

  public Display(int x, int y, String title) {

    frame = new JFrame(title);
    this.setSize(x, y);
    frame.add(this);
    frame.pack();
    frame.setVisible(true);
    //Read more about this 
    this.createBufferStrategy(2);


  }

  //Close this window
  public void close() {
    this.frame.setVisible(false);
    this.frame.dispose();
    System.out.println("Closing!");
  }

  
  //These don't really belong here, they should be in the point class or polygon class
  // point, center, left
  public static double angleLawOfCosinesXY(Point p1, Point p2, Point p3) {

    double b = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));// cam
                                                                                                  // center


    double c = Math.sqrt(Math.pow(p1.getX() - p3.getX(), 2) + Math.pow(p1.getY() - p3.getY(), 2));;// cam
                                                                                                   // arm


    double calcXYT = (1 + (b * b) - (c * c));
    double calcXYB = (2 * b);

    double calcXY = calcXYT / calcXYB;

    // The problem appears to be with how it figures out what is on the left and what is on the
    // right
    return /* Math.signum((p1.getY() - p3.getY())) * */Math.acos(calcXY);

  }

  // point, center, top
  public static double angleLawOfCosinesXZ(Point p1, Point p2, Point p3) {

    double b = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getZ() - p2.getZ(), 2));// cam
                                                                                                  // center


    double c = Math.sqrt(Math.pow(p1.getX() - p3.getX(), 2) + Math.pow(p1.getZ() - p3.getZ(), 2));;// cam
                                                                                                   // arm


    double calcXZT = (1 + (b * b) - (c * c));
    double calcXZB = (2 * b);

    double calcXZ = calcXZT / calcXZB;

    // The problem appears to be with how it figures out what is on the left and what is on the
    // right
    return /* Math.signum((p1.getZ() - p3.getZ())) * */ Math.acos(calcXZ);

  }

  //Show all coordinates of camera points on the left side of the screen
  private void printCameraInfo(Graphics g) {
    consoleY = 10;// reset console
    consolePrint(g, "cam X:  " + (int)Camera.globalCameras.get(0).camCenter.getX());
    consolePrint(g, "cam Y:  " + (int)Camera.globalCameras.get(0).camCenter.getY());
    consolePrint(g, "cam Z:  " + (int)Camera.globalCameras.get(0).camCenter.getZ());

    consolePrint(g, "top X:  " + (int)Camera.globalCameras.get(0).camTop.getX());
    consolePrint(g, "top Y:  " + (int)Camera.globalCameras.get(0).camTop.getY());
    consolePrint(g, "top Z:  " + (int)Camera.globalCameras.get(0).camTop.getZ());

    consolePrint(g, "lft X:  " + (int)Camera.globalCameras.get(0).camLeft.getX());
    consolePrint(g, "lft Y:  " + (int)Camera.globalCameras.get(0).camLeft.getY());
    consolePrint(g, "lft Z:  " + (int)Camera.globalCameras.get(0).camLeft.getZ());
    
    consolePrint(g, "bck X:  " + (int)Camera.globalCameras.get(0).camBack.getX());
    consolePrint(g, "bck Y:  " + (int)Camera.globalCameras.get(0).camBack.getY());
    consolePrint(g, "bck Z:  " + (int)Camera.globalCameras.get(0).camBack.getZ());

    consolePrint(g, "XY: " + Camera.globalCameras.get(0).getXyAngle());
    consolePrint(g, "XZ: " + Camera.globalCameras.get(0).getXzAngle());
  }

  public void paint(Graphics g) {

    
    //Show all camera params
    printCameraInfo(g);


    double b;
    double c;
    double calcXYT;
    double calcXYB;
    double calcXY;

    //O(6x^2)?
    
    // For all geometry
    for (Geometry G : Geometry.globalGeometry)
      
      //For each polygon in this geometry
      for (Polygon P : G.polygons) {

        // for each line in this polygon
        for (Line L : P.lines) {

          // for each point in this line
          for (Point p : L.points) {

            // Calculate where it needs to be drawn on screen

            //Calculate the angle between this point and the xy camera
            double angleLeft = angleLawOfCosinesXY(p, Camera.globalCameras.get(0).camCenter,
                Camera.globalCameras.get(0).camLeft);
            
            //Calculate the angle between this point and the back point in the XY plane
            double angleBackXY = angleLawOfCosinesXY(p, Camera.globalCameras.get(0).camCenter,
                Camera.globalCameras.get(0).camBack); 


            //Calculate the angle between this point and the xz camera
            double angleTop = angleLawOfCosinesXZ(p, Camera.globalCameras.get(0).camCenter,
                Camera.globalCameras.get(0).camTop);
            
            //Calculate the angle between this point and the back point in the XZ plane
            double angleBackXZ = angleLawOfCosinesXZ(p, Camera.globalCameras.get(0).camCenter,
                Camera.globalCameras.get(0).camBack); 

            
            
            //if this point is not behind us
            if((angleBackXY >= Math.PI/2) | (angleBackXZ >= Math.PI/2)) {
              
              //Set on screen coordinates for where this point is located, can still make
              //mistakes however, should do something about that
              p.onScreenX = (int) (this.getWidth()
                  * (((Math.toDegrees(angleLeft) - (90 - (Camera.getFovXY() / 2)))
                      / (Camera.getFovXY()))));
              p.onScreenY = (int) (this.getHeight()
                  * (((Math.toDegrees(angleTop) - (90 - (Camera.getFovXZ() / 2)))
                      / (Camera.getFovXZ()))));
              
            }else {
              //Point is behind us and therefore, off screen
              p.onScreenX = -1;
              p.onScreenY = -1;
              
            }


          }
          
          
        }
        
        boolean polyOnScreen = true;
        int xPts[] = {P.points[0].onScreenX, P.points[1].onScreenX, P.points[2].onScreenX};
        int yPts[] = {P.points[0].onScreenY, P.points[1].onScreenY, P.points[2].onScreenY};

        
        //Check if all points are on screen
        for(int i=0; i < 3; i++) {
          if(xPts[i] < 0)
            polyOnScreen=false;
        }
        
        for(int j=0; j < 3; j++) {
          if(yPts[j] < 0)
            polyOnScreen=false;
        }
        
        //only draw a polygon if all points are on screen
        if(polyOnScreen)
          g.setColor(Color.BLACK);
          g.drawPolygon(xPts, yPts, 3);
          //g.setColor(P.color);
          //g.fillPolygon(xPts, yPts, 3);
        
      }
  }
}
