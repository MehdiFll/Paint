/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *
 * @author Mehdi Fll
 */ 
public class Cercle extends Ellipse2D implements Serializable{

    
        double x;
        double y;
        double width;
        double height;

    public Cercle(double x0,double y0,double width,double height) {
        this.x=x0;
        this.y=y0;
        this.width=width;
        this.height=height;
    }

    public Cercle() {
    }
    


    public double getX0() {
        return x;
    }

    public void setX0(int x0) {
        this.x = x0;
    }

    public double getY0() {
        return y;
    }

    public void setY0(int y0) {
        this.y = y0;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Cercle(int x0, int y0, int x1, int y1) {
        this.x = x0;
        this.y = y0;
        this.width =x1;
        this.height =y1;
    }
    public Cercle(int x0, int y0, int x1) {
        this.x = x0;
        this.y = y0;
        this.width =Math.abs(x1-x0);
        this.height = width;
    }

    

    @Override
    public double getX() {
            return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBounds(double x, double y, double w, double h) {
           this.x=x;
           this.y=y;
           width=w;
           height=h;
    }

    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
