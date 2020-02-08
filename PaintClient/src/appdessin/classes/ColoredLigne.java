package appdessin.classes;

import java.awt.Color;
import java.io.Serializable;

public class ColoredLigne implements Serializable {

    private Color foreground;
    private Ligne ligne;
    private String stroke;
    private String name;
    public ColoredLigne(Color foreground, Ligne ligne, String stroke,String name) {
        this.foreground = foreground;
        this.ligne = ligne;
        this.stroke = stroke;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }
    
    public ColoredLigne() {
               
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Ligne getLigne() {
        return ligne;
    }
    
    public boolean isSelected(double x, double y){
        if(this.ligne.intersectsLine(x, y, 5, 5)) return true;
        else return false;
    }

      
    
}
