import java.awt.Canvas;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class SoftRender {

  public static void main(String[] args) {
    
    
    /*
     * Known Issues:
     * 1.Geometry directly behind the camera is rendered as though it is in front
     *  (Add another line directly behind?)
     * 
     */
    
    
    //Point a = new Point(0,-5,0, false);
    //Point b = new Point(30,15,15);
    //Point c = new Point(15,30,15);
    
    //Line AB = new Line(a,b);
    
    /* TODO
     * 
     * Point -> Line -> Surface -> Geometry
     * Rewrite surface class to make this work
     * 
     */
    
    //Surface ABC = new Surface(new Point(10,10,5,false), new Point(10,10,-5,false), new Point(10,-10,5,false), new Point(10,-10,-5,false), Color.RED);
    //Surface DEF = new Surface(new Point(20,10,5,false), new Point(20,10,-5,false), new Point(20,-10,5,false), new Point(20,-10,-5,false), Color.RED);
    
    //Front
    Point A = new Point(20,10,10, false);
    Point B = new Point(20,10,-10,false);
    Point C = new Point(20,-10,10,false);
    Point D = new Point(20,-10,-10,false);
    //Back
    Point E = new Point(30,-10,10,false);
    Point F = new Point(30,-10,-10,false);
    Point G = new Point(30,10,10,false);
    Point H = new Point(30,10,-10,false);
    
    Line AB = new Line(A,B);
    Line CD = new Line(C,D);
    Line EF = new Line(E,F);
    Line GH = new Line(G,H);
    
    Line AC = new Line(A,C);
    Line GE = new Line(G,E);
    
    Line BD = new Line(B,D);
    Line HF = new Line(H,F);
    
    //Front
    Surface ABCD = new Surface(AB, CD);
    //Left
    Surface CDEF = new Surface(CD, EF);
    //Right
    Surface ABGH = new Surface(AB, GH);
    //Back
    Surface EFGH = new Surface(EF, GH);
    //Top
    Surface ACEG = new Surface(AC, GE);
    //Bottom
    Surface BDFH = new Surface(BD, HF);
    

    
    
    System.out.println("STARTUP!");
    
    Camera mainCam = new Camera(0.0, 0.0, 0.0, 0.0, 0.0);
    
    //do camera stuff here
    
    Canvas canvas = new Display(1024,576, "Grafix");
    
     /*
     for(int i = 0; i<360;i++) {
       
       try {
         Thread.sleep(100);
         mainCam.setCamPos(0, 0, 0);
         mainCam.setXyAngle(i);
         canvas.repaint();
         }catch(Exception e) {}
       
     }
     */
    
}
  
}
