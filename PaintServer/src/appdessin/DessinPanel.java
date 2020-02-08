/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin;

import appdessin.classes.Cercle;
import appdessin.classes.ColoredCercle;
import appdessin.classes.ColoredLigne;
import appdessin.classes.ColoredPoint;
import appdessin.classes.ColoredRectangle;
import appdessin.classes.Ligne;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mehdi Fll
 */
public class DessinPanel extends javax.swing.JPanel {

    JTable Table;
    BoutonPanel BP;
    int x0;
    int y0;
    int x1;
    int y1;
    private Vector vPoints = new Vector();
    private ArrayList<ColoredRectangle> ColoredRectangles = new ArrayList<ColoredRectangle>();
    private ArrayList<ColoredCercle> ColoredCercles = new ArrayList<ColoredCercle>();
    private ArrayList<ColoredLigne> ColoredLignes = new ArrayList<ColoredLigne>();
   
    private Ligne shapel;
    private Rectangle shape;

    private Cercle shapec;
    private Point startPoint;
    private String selection = "";
    int position;
    static int nbrR = 0;
    static int nbrC = 0;
    static int nbrL = 0;
    String nameO = "";
    final static float dash1[] = {10.0f};
    final static BasicStroke dashed = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    public JTable getTable() {
        return Table;
    }

    public void setTable(JTable Table) {
        this.Table = Table;
    }

    public ArrayList<ColoredCercle> getColoredCercle() {
        return ColoredCercles;
    }

    public void setColoredCercle(ArrayList<ColoredCercle> ColoredCercle) {
        this.ColoredCercles = ColoredCercle;
    }

    public void setvPoints(Vector vPoints) {
        this.vPoints = vPoints;
    }

    public void setColoredRectangles(ArrayList<ColoredRectangle> ColoredRectangles) {
        this.ColoredRectangles = ColoredRectangles;
    }

    public ArrayList<ColoredLigne> getColoredLignes() {
        return ColoredLignes;
    }

    public void setColoredLignes(ArrayList<ColoredLigne> ColoredLignes) {
        this.ColoredLignes = ColoredLignes;
    }

    public Vector getvPoints() {
        return vPoints;
    }

    public ArrayList<ColoredRectangle> getColoredRectangles() {
        return ColoredRectangles;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    int Nbre = 0;

    /**
     * Creates new form DessinPanel
     */
    public DessinPanel() {
        initComponents();
        //awla settable(fenetre.jtable2)
    }

    public void setBP(BoutonPanel bp) {
        this.BP = bp;

    }
    public void affichage(ArrayList<Projet> list , Fenetre F) {
        for (int i=0; i<list.size();i++) {
            if(list.get(i).getKey().equals(F.key)) {
                F.getDessinPanel1().setColoredRectangles(list.get(i).getColoredRectangles());
                F.getDessinPanel1().setColoredCercle(list.get(i).getColoredCercle());
                F.getDessinPanel1().setColoredLignes(list.get(i).getColoredLignes());
                F.getDessinPanel1().setvPoints(list.get(i).getvPoints());
                break;
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
   

        for (int i = 0; i < vPoints.size(); i++) {
            Vector polyLine = ((Vector) vPoints.elementAt(i));
            for (int j = 0; j < polyLine.size() - 1; j++) {
                Color a = ((ColoredPoint) polyLine.elementAt(j)).couleur;
                g.setColor(a);
                g.drawLine(((ColoredPoint) polyLine.elementAt(j)).x0, ((ColoredPoint) polyLine.elementAt(j)).y0, ((ColoredPoint) polyLine.elementAt(j + 1)).x0, ((ColoredPoint) polyLine.elementAt(j + 1)).y0);
            }
        }

        //Color foreground = BP.Couleur;
        g.setColor(Color.BLACK);

        for (ColoredRectangle cr : ColoredRectangles) {
            Graphics2D g2d = (Graphics2D) g;
            switch (cr.getStroke()) {
                case "basic": {
                    g2d.setStroke(new BasicStroke(0.0f));
                    break;
                }
                case "dashed": {
                    g2d.setStroke(dashed);
                    break;
                }
                case "lvl3": {
                    g2d.setStroke(new BasicStroke(3.0f));
                    break;
                }
            }

            if (cr.isFilled()) {
                g2d.setColor(cr.getForeground());
                Rectangle r = cr.getRectangle();
                g2d.fillRect(r.x, r.y, r.width, r.height);
            } else {
                g2d.setColor(cr.getForeground());
                Rectangle r = cr.getRectangle();
                g2d.drawRect(r.x, r.y, r.width, r.height);
            }
        }

        for (ColoredCercle cs : ColoredCercles) {
            Graphics2D g2d = (Graphics2D) g;

            switch (cs.getStroke()) {
                case "basic": {
                    g2d.setStroke(new BasicStroke(0.0f));
                    break;
                }
                case "dashed": {
                    g2d.setStroke(dashed);
                    break;
                }
                case "lvl3": {
                    g2d.setStroke(new BasicStroke(3.0f));
                    break;
                }
            }

            if (cs.isFilled()) {
                g2d.setColor(cs.getForeground());
                Cercle c = cs.getCercle();
                g2d.fillOval((int) c.getX(), (int) c.getY(), (int) c.getWidth(), (int) c.getHeight());
            } else {
                g2d.setColor(cs.getForeground());
                Cercle c = cs.getCercle();
                g2d.drawOval((int) c.getX(), (int) c.getY(), (int) c.getWidth(), (int) c.getHeight());
            }
        }
        //  Paint the Rectangle as the mouse is being dragged
        if (shape != null) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(BP.Couleur);
            switch (BP.getStroke()) {
                case "basic": {
                    g2d.setStroke(new BasicStroke(0.0f));
                    break;
                }
                case "dashed": {
                    g2d.setStroke(dashed);
                    break;
                }
                case "lvl3": {
                    g2d.setStroke(new BasicStroke(3.0f));
                    break;
                }
            }
            if (BP.getFill()) {
                g2d.fill(shape);
            } else {
                g2d.draw(shape);
            }

        }
        if (shapec != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(BP.Couleur);
            switch (BP.getStroke()) {
                case "basic": {
                    g2d.setStroke(new BasicStroke(0.0f));
                    break;
                }
                case "dashed": {
                    g2d.setStroke(dashed);
                    break;
                }
                case "lvl3": {
                    g2d.setStroke(new BasicStroke(3.0f));
                    break;
                }
            }
            if (BP.getFill()) {
                g2d.fill(shapec);
            } else {
                g2d.draw(shapec);
            }
        }
        for (ColoredLigne cl : ColoredLignes) {
            Graphics2D g2d = (Graphics2D) g;
            switch (cl.getStroke()) {
                case "basic": {
                    g2d.setStroke(new BasicStroke(0.0f));
                    break;
                }
                case "dashed": {
                    g2d.setStroke(dashed);
                    break;
                }
                case "lvl3": {
                    g2d.setStroke(new BasicStroke(3.0f));
                    break;
                }
            }
            g.setColor(cl.getForeground());
            Ligne l = cl.getLigne();
            g.drawLine((int) l.getX1(), (int) l.getY1(), (int) l.getX2(), (int) l.getY2());

        }

        //  Paint the Rectangle as the mouse is being dragged
        if (shapel != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(BP.Couleur);
            switch (BP.getStroke()) {
                case "basic": {
                    g2d.setStroke(new BasicStroke(0.0f));
                    break;
                }
                case "dashed": {
                    g2d.setStroke(dashed);
                    break;
                }
                case "lvl3": {
                    g2d.setStroke(new BasicStroke(3.0f));
                    break;
                }
            }
            g2d.draw(shapel);
        }
    }

    public void addRectangle(Rectangle rectangle, Color color, Boolean filled, Color fill, String stroke, String name) {
        //  Add the Rectangle to the List so it can be repainted

        ColoredRectangle cr = new ColoredRectangle(color, rectangle, filled, fill, stroke, name);
        ColoredRectangles.add(cr);

    }

    public void addCercle(Cercle cercle, Color color, Boolean filled, Color fill, String stroke, String name) {
        //  Add the Rectangle to the List so it can be repainted

        ColoredCercle cs = new ColoredCercle(color, cercle, filled, fill, stroke, name);
        ColoredCercles.add(cs);

    }

    public void addLigne(Ligne ligne, Color color, String stroke, String name) {
        //  Add the Rectangle to the List so it can be repainted

        ColoredLigne cl = new ColoredLigne(color, ligne, stroke, name);
        ColoredLignes.add(cl);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
       

    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        

    }//GEN-LAST:event_formMouseDragged

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

