public class SoftRender {

  private static void init() {
    
    
    Rectangle r1 = new Rectangle(45, 10, 20, 30, 20, 0);
    Rectangle r2 = new Rectangle(45, -10, 20, 30, -20, 0);
    System.out.println("Init Complete!");
    
  }

  public static void main(String[] args) {


    /*
     * Known Issues: 
     *      1. Flickering
     *      2. Display should really extend JFrame, not canvas
     *      
     */

    init();

    System.out.println("Startup!");

    Camera mainCam = new Camera(0.0, 0.0, 10.0, 0.0, 0.0);
    Display canvas = new Display(1024, 576, "Grafix");
    

    for (int i = 0; i < 360; i++) {

      try {
        Thread.sleep(100);
        mainCam.setCamPos(0, 0, 10);
        mainCam.setXyAngle(i);
        canvas.repaint();
      } catch (Exception e) {
      }

    }
    
    canvas.close();

  }

}
