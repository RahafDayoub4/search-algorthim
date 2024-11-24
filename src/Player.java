import java.lang.annotation.Target;

import javax.swing.text.Position;

public class Player {
    private int currentX ,currentY;
    private char value; 
    private boolean filled; 
    private int xTar ;
    private int yTar;
    public Player(int x, int y, int xTar, int yTar) {
        this.currentX =x;
        this.currentY =y ;
        this.xTar = xTar;
        this.yTar = yTar;
        this.filled = true; // Default is not filled
    }
    public void setPosition(int x ,int y){
        this.currentX = x;
        this.currentY = y;
    }
    public void setX(int x){
         currentX = x;
    }
    public void setY(int y){
        currentY = y;
   }
   public void setXTar(int x){
    xTar = x;
}
public void setTarY(int y){
   yTar = y;
}
    public int getX(){
        return currentX;
    }
    public int getY(){
        return currentY;
    }
    public int getxTarget() {
        return xTar;
    }
    public int getyTarget() {
        return yTar;
    }
    public char getValue() {
        return value;
    }

    public boolean isFilled() {
        return filled;
    }

    public void toggle() {
        filled = !filled;
    }
}
