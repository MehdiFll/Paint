/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin.classes;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author WIAMGH
 */
public class ColoredPoint implements Serializable {
    public int x0, y0;
    public Color couleur;

    public ColoredPoint(int x0, int y0, Color couleur) {
        this.x0 = x0;
        this.y0 = y0;
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    
}
