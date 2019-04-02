import java.awt.Color;

public class Rectangle {

  
  public Rectangle(float x1, float y1, float z1, float x2, float y2, float z2) {
    
    Point A = new Point(x1,y1,z1,false);
    Point B = new Point(x1,y1,z2,false);
    Point C = new Point(x1,y2,z1,false);
    Point D = new Point(x1,y2,z2,false);
    //Back
    Point E = new Point(x2,y1,z1,false);
    Point F = new Point(x2,y1,z2,false);
    Point G = new Point(x2,y2,z1,false);
    Point H = new Point(x2,y2,z2,false);
    
    Line AB = new Line(A,B);
    Line CD = new Line(C,D);
    Line EF = new Line(E,F);
    Line GH = new Line(G,H);
    
    Line AC = new Line(A,C);
    Line GE = new Line(G,E);
    
    Line BD = new Line(B,D);
    Line HF = new Line(H,F);
    
    Line AE = new Line(A,E);
    Line BF = new Line(B,F);
    Line CG = new Line(C,G);
    Line DH = new Line(D,H);
   
    try {
      
      Geometry Box = new Geometry();
      
      //Front
      Polygon ABC = new Polygon(AB, AC, Color.RED);
      Box.addPolygon(ABC);
      Polygon ACD = new Polygon(BD, CD, Color.RED);
      Box.addPolygon(ACD);
      
      //Back
      Polygon EFG = new Polygon(EF,GE, Color.BLUE);
      Box.addPolygon(EFG);
      Polygon FGH = new Polygon(GH, HF, Color.BLUE);
      Box.addPolygon(FGH);
      
      //Left
      Polygon ABF = new Polygon(AB, BF, Color.GREEN);
      Box.addPolygon(ABF);
      Polygon AEF = new Polygon(AE, EF, Color.GREEN);
      Box.addPolygon(AEF);
      
      //Right
      Polygon CGD = new Polygon(CG, DH, Color.BLACK);
      Box.addPolygon(CGD);
      Polygon CDH = new Polygon(CD, DH, Color.BLACK);
      Box.addPolygon(CDH);
      
      //Top
      Polygon ACE = new Polygon(AC, AE, Color.ORANGE);
      Box.addPolygon(ACE);
      Polygon CEG = new Polygon(CG, GE, Color.ORANGE);
      Box.addPolygon(CEG);
      
      //Bottom
      Polygon BDH = new Polygon(BD, BF, Color.gray);
      Box.addPolygon(BDH);
      Polygon DFH = new Polygon(DH, HF, Color.gray);
      Box.addPolygon(DFH);
      
      
      
      
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
}
