package persion.lijuntao.image;
import java.awt.*;  
import java.awt.geom.*;  
import javax.swing.*;  
  
public class DrawTest {  
  
    public static void main(String[] args) {  
        EventQueue.invokeLater(new Runnable(){  
            public void run() {  
                DrawImg f=new DrawImg();                  
            }             
        });  
    }  
}  
  
class DrawImg extends JFrame{     
    DrawImg(){  
        setLocationByPlatform(true);  
        setTitle("draw test");  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        getContentPane().add(new DrawComponent());  
        pack();       
        setVisible(true);  
    }     
}  
  
class DrawComponent extends JComponent{  
    private static final int DEFAULT_W=600;  
    private static final int DEFAULT_H=600;  
    DrawComponent(){  
        setPreferredSize(new Dimension(DEFAULT_W,DEFAULT_H));  
    }  
    public void paintComponent(Graphics g){  
        Graphics2D g2d=(Graphics2D)g;  
          
        double centerX=DEFAULT_W/2;  
        double centerY=DEFAULT_H/2;  
          
        double conerX=centerX+100;  
        double conerY=centerY+100;  
          
        Rectangle2D rect =new Rectangle2D.Double();  
        rect.setFrameFromCenter(centerX,centerY,conerX,conerY);  
          
        Ellipse2D ellipse=new Ellipse2D.Double();  
        ellipse.setFrame(rect);  
          
        Ellipse2D circle=new Ellipse2D.Double();  
        double radius=Point2D.distance(centerX, centerY, conerX, conerY);  
        circle.setFrameFromCenter(centerX,centerY,centerX+radius,centerY+radius);  
          
        Line2D line= new Line2D.Double(conerX,conerY,conerX-200,conerY-200);  
        Line2D line2= new Line2D.Double(conerX-200,conerY,conerX,conerY-200);  
          
          
        g2d.draw(rect);  
          
        g2d.draw(ellipse);  
        g2d.draw(circle);  
          
        g2d.draw(line);  
        g2d.draw(line2);          
    }     
}  