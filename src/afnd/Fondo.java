/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package afnd;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author netom
 */
public class Fondo extends JPanel{
   
    int x1,x2,y1,y2;

    public Fondo() {
           this.x1=0;
      this.x2=0;
      this.y1=0;
      this.y2=0;
    }
    
     @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawLine(x1, 0, x2, getHeight());
  }
  void pintar(int x1,int y1,int x2,int y2){
      this.x1=x1;
      this.x2=x2;
      this.y1=y1;
      this.y2=y2;
  }
}
