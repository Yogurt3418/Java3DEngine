import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public class Display extends Canvas {

  /**
   * I have no idea what this is or why it is here
   */
  private static final long serialVersionUID = 1L;

  private static int dotSize = 10;
  
  private int consoleY = 10;
  
  private void consolePrint(Graphics g, String str) {
   
    g.drawString(str,5,consoleY);
    consoleY += 10;
    
  }


  public Display(int x, int y, String title) {

    JFrame frame = new JFrame(title);
    this.setSize(x, y);
    frame.add(this);
    frame.pack();
    frame.setVisible(true);

  }


  public void paint(Graphics g) {

    consoleY = 10;//reset
    
    
    this.consolePrint(g, "cam X:  " + Camera.globalCameras.get(0).camCenter.getX());
    this.consolePrint(g, "cam Y:  " + Camera.globalCameras.get(0).camCenter.getY());
    this.consolePrint(g, "cam Z:  " + Camera.globalCameras.get(0).camCenter.getZ());
    
    this.consolePrint(g, "top X:  " + Camera.globalCameras.get(0).camTop.getX());
    this.consolePrint(g, "top Y:  " + Camera.globalCameras.get(0).camTop.getY());
    this.consolePrint(g, "top Z:  " + Camera.globalCameras.get(0).camTop.getZ());
    
    this.consolePrint(g, "lft X:  " + Camera.globalCameras.get(0).camLeft.getX());
    this.consolePrint(g, "lft Y:  " + Camera.globalCameras.get(0).camLeft.getY());
    this.consolePrint(g, "lft Z:  " + Camera.globalCameras.get(0).camLeft.getZ());
    
    this.consolePrint(g, "XY: " + Camera.globalCameras.get(0).getXyAngle());
    this.consolePrint(g, "XZ: " + Camera.globalCameras.get(0).getXzAngle());
    
    

    double b;
    double c;
    double calcXYT;
    double calcXYB;
    double calcXY;
    
    System.out.println("Surface! " + Surface.globalSurfaces.size());
    //For all surfaces
    for (Surface S : Surface.globalSurfaces) {
      
      
      //for each line in this surface
      for (Line L : S.lines) {
        
        
        //for each point in this line
        for (Point p : L.points) {

          //Calculate where it needs to be drawn on screen


          ////////// XY

          // law of cosines c^2 = a^2+b^2-2ab*cos(c)

           b = Math.sqrt(Math.pow(p.getX() - Camera.globalCameras.get(0).camCenter.getX(), 2)
              + Math.pow(p.getY() - Camera.globalCameras.get(0).camCenter.getY(), 2));// cam center


           c = Math.sqrt(Math.pow(p.getX() - Camera.globalCameras.get(0).camLeft.getX(), 2)
              + Math.pow(p.getY() - Camera.globalCameras.get(0).camLeft.getY(), 2));;// cam arm


           calcXYT = (1 + (b * b) - (c * c));
           calcXYB = (2 * b);

           calcXY = calcXYT / calcXYB;

          double angleLeft = Math.signum((p.getY() - Camera.globalCameras.get(0).camLeft.getY()))
              * Math.acos(calcXY);

           //System.out.println(Math.toDegrees(angleLeft) + "| a:" + Math.round(a) + "| b:" +
           //Math.round(b)
           //+ "| c:" + Math.round(c) + "| calcXYT:" + calcXYT + "| calcB:" + calcXYB + "| calc:" +
           //calcXY);



          ////////// XZ

          // law of cosines c^2 = a^2+b^2-2ab*cos(c)

          double e = Math.sqrt(Math.pow(p.getX() - Camera.globalCameras.get(0).camCenter.getX(), 2)
              + Math.pow(p.getZ() - Camera.globalCameras.get(0).camCenter.getZ(), 2));// cam center


          double f = Math.sqrt(Math.pow(p.getX() - Camera.globalCameras.get(0).camTop.getX(), 2)
              + Math.pow(p.getZ() - Camera.globalCameras.get(0).camTop.getZ(), 2));;// cam arm 

          double calcXZT = ((1) + (e * e) - (f * f));
          double calcXZB = (2 * e);

          double calcXZ = calcXZT / calcXZB;

          // I have no clue if this is really the best way to do this
          double angleTop = Math.signum((p.getZ() - Camera.globalCameras.get(0).camTop.getZ()))
              * Math.acos(calcXZ);

          // System.out.println(Math.toDegrees(angleTop) + "| a:" + Math.round(d) + "| b:" +
          // Math.round(e)
          // + "| c:" + Math.round(f) + "| calcXYT:" + calcXZT + "| calcB:" + calcXZB + "| calc:" +
          // calcXZ);

          // System.out.println("");

          /////////// draw 

          /*
           * 
           * //2d top down g.setColor(S.color); g.fillOval(this.getWidth() / 2 + (int) p.getX() -
           * (dotSize / 2), this.getHeight() / 2 - (int) p.getY() - (dotSize / 2), dotSize,
           * dotSize);
           * 
           */


          p.onScreenX = (int) ((this.getWidth() / 2) + ((this.getWidth() / 2) * Math.toDegrees(angleLeft) / Camera.fovXY));
          p.onScreenY = (int) ((this.getHeight() / 2) - ((this.getHeight() / 2) * Math.toDegrees(angleTop) / Camera.fovXZ));
          
          // 3d
           g.setColor(Color.BLACK);
           g.fillOval(p.onScreenX, p.onScreenY,dotSize,dotSize);
           g.drawString("XY:" + Math.toDegrees(angleLeft), p.onScreenX,p.onScreenY);
               //(int) (this.getWidth()/2 + (this.getWidth()/2 * Math.toDegrees(angleLeft)/Camera.fovXY)), 
               //(int) (this.getHeight()/2 - (this.getHeight()/2 * Math.toDegrees(angleTop)/Camera.fovXZ))
               //,dotSize,dotSize);

          
          //at last
          

        }

        g.drawLine(L.points[0].onScreenX + dotSize/2, L.points[0].onScreenY + dotSize/2, L.points[1].onScreenX + dotSize/2, L.points[1].onScreenY + dotSize/2);
      }
    }
  }
}
