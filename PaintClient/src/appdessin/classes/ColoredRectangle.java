package appdessin.classes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;

public class ColoredRectangle extends Rectangle implements Serializable{

    private Color foreground;
    private Rectangle rectangle;
    private String stroke;
    private boolean filled;
    private Color fill;
    private String name;
    public ColoredRectangle(Color foreground, Rectangle rectangle,boolean filled,Color fill,String stroke,String name) {
        this.foreground = foreground;
        this.rectangle = rectangle;
        this.filled=filled;
        this.fill=fill;
        this.stroke=stroke;
        this.name=name;
    }

    public Color getFill() {
        return fill;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean Filled) {
        this.filled = Filled;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String Stroke) {
        this.stroke = Stroke;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public boolean isSelected(int x, int y){
        if(this.rectangle.contains(x, y)) return true;
        else return false;
    }

  
 

    
    
    
}
