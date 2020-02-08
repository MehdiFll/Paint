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
    private int Drawing = 0;
    private Cercle shapec;
    private Point startPoint;
    private String selection = "";
    int position;
    static int nbrR = 0;
    static int nbrC = 0;
    static int nbrL = 0;
    String nameO = "";
    public int Nbre = 0;
    final static float dash1[] = {10.0f};
    final static BasicStroke dashed = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    public int getNbre() {
        return Nbre;
    }

    public void setNbre(int Nbre) {
        this.Nbre = Nbre;
    }

    public int getDrawing() {
        return Drawing;
    }

    public void setDrawing(int Drawing) {
        this.Drawing = Drawing;
    }

    
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
        Graphics g = getGraphics();

        switch (BP.getAtt()) {
            case "libre": {
                x0 = evt.getX();
                y0 = evt.getY();

                vPoints.add(Nbre, new Vector());
                ((Vector) vPoints.elementAt(Nbre)).add(new ColoredPoint(x0, y0, BP.getCouleur()));

                break;
            }
            case "rectangle": {

                startPoint = evt.getPoint();
                shape = new Rectangle();

                break;
            }

            case "cercle": {
                startPoint = evt.getPoint();
                shapec = new Cercle();
                break;
            }
            case "ligne": {
                startPoint = evt.getPoint();
                shapel = new Ligne();
                break;
            }
            case "selection": {
                for (int i = 0; i < ColoredRectangles.size(); i++) {

                    if (ColoredRectangles.get(i).isSelected(evt.getX(), evt.getY())) {
                        this.selection = "Rectangle";
                        this.setPosition(i);
                        //System.out.println(ColoredRectangles.get(i).getName());
                        //System.out.println("x: " + ColoredRectangles.get(i).getRectangle().getX() + "y: " + ColoredRectangles.get(i).getRectangle().getY() + "Width: " + ColoredRectangles.get(i).getRectangle().getWidth() + "Height: " + ColoredRectangles.get(i).getRectangle().getHeight());
                        DefaultTableModel m = (DefaultTableModel) this.getTable().getModel();

                        m.setRowCount(0);

                        m.addRow(new Object[]{"name", ColoredRectangles.get(i).getName()});
                        m.addRow(new Object[]{"width", ColoredRectangles.get(i).getRectangle().getWidth()});
                        m.addRow(new Object[]{"height", ColoredRectangles.get(i).getRectangle().getHeight()});
                        m.addRow(new Object[]{"stroke", ColoredRectangles.get(i).getStroke()});
                        m.addRow(new Object[]{"fill", ColoredRectangles.get(i).isFilled()});
                        String color3 = "<html><font color=rgb(" + ColoredRectangles.get(i).getForeground().getRed() + "," + ColoredRectangles.get(i).getForeground().getGreen() + "," + ColoredRectangles.get(i).getForeground().getBlue() + ")>color </font></html>";
                        m.addRow(new Object[]{"color", color3});
                        //System.out.println(m.getColumnName(0));;
                        break;
                    }
//????????????
                }
                for (int j = 0; j < ColoredCercles.size(); j++) {

                    if (ColoredCercles.get(j).isSelected(evt.getX(), evt.getY())) {
                        this.selection = "Cercle";
                        this.setPosition(j);
                        //System.out.println(ColoredCercles.get(j).getName());
                        //System.out.println("x: " + ColoredCercles.get(j).getCercle().getX() + "y: " + ColoredCercles.get(j).getCercle().getY() + "Width: " + ColoredCercles.get(j).getCercle().getWidth() + "Height: " + ColoredCercles.get(j).getCercle().getHeight());
                        DefaultTableModel m = (DefaultTableModel) this.getTable().getModel();
                        m.setRowCount(0);

                        m.addRow(new Object[]{"name", ColoredCercles.get(j).getName()});
                        m.addRow(new Object[]{"width", ColoredCercles.get(j).getCercle().getWidth()});
                        m.addRow(new Object[]{"height", ColoredCercles.get(j).getCercle().getHeight()});
                        m.addRow(new Object[]{"stroke", ColoredCercles.get(j).getStroke()});
                        m.addRow(new Object[]{"fill", ColoredCercles.get(j).isFilled()});
                        String color3 = "<html><font color=rgb(" + ColoredCercles.get(j).getForeground().getRed() + "," + ColoredCercles.get(j).getForeground().getGreen() + "," + ColoredCercles.get(j).getForeground().getBlue() + ")>color </font></html>";
                        m.addRow(new Object[]{"color", color3});
                        break;

                    }

                }
                ColoredLigne cl;
                for (int k = 0; k < ColoredLignes.size(); k++) {
                    cl = ColoredLignes.get(k);
                    if (cl.isSelected(evt.getX(), evt.getY())) {
                        this.selection = "ligne";
                        startPoint = evt.getPoint();
                        setPosition(k);
                        DefaultTableModel m = (DefaultTableModel) this.getTable().getModel();
                        m.setRowCount(0);

                        m.addRow(new Object[]{"name", ColoredLignes.get(k).getName()});
                        m.addRow(new Object[]{"start x", cl.getLigne().getX1()});
                        m.addRow(new Object[]{"start y", cl.getLigne().getY1()});
                        m.addRow(new Object[]{"end x", cl.getLigne().getX2()});
                        m.addRow(new Object[]{"end y", cl.getLigne().getY2()});
                        m.addRow(new Object[]{"stroke", cl.getStroke()});

                        String color3 = "<html><font color=rgb(" + ColoredLignes.get(k).getForeground().getRed() + "," + ColoredLignes.get(k).getForeground().getGreen() + "," + ColoredLignes.get(k).getForeground().getBlue() + ")>color </font></html>";
                        m.addRow(new Object[]{"color", color3});

                        break;
                    }
                }

                for (int l = 0; l < vPoints.size(); l++) {
                    Vector polyLine = ((Vector) vPoints.elementAt(l));
                    for (int j = 0; j < polyLine.size() - 1; j++) {
                        Point tmp = new Point(((ColoredPoint) polyLine.elementAt(j)).x0, ((ColoredPoint) polyLine.elementAt(j)).y0);
                        if (tmp.equals(evt.getPoint())) {
                            startPoint = evt.getPoint();
                            setPosition(l);
                            this.selection = "libre";
                            DefaultTableModel m = (DefaultTableModel) this.getTable().getModel();
                            m.setRowCount(0);
                            m.addRow(new Object[]{"free" + l});
                            break;  //System.out.println("position : " + position + "true - x : " + tmp.getX() + "- Y : " + tmp.getY());
                        }
                    }
                }

            }

        }

    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        Graphics g = getGraphics();

        switch (BP.getAtt()) {
            case "libre": {
                this.setDrawing(4);
                break;
                
            }
            case "rectangle": {

                if (shape.width != 0 || shape.height != 0) {
                    nbrR++;
                    nameO = "Rect" + nbrR;
                    addRectangle(shape, BP.getCouleur(), BP.getFill(), BP.getCouleur(), BP.getStroke(), nameO);
                    this.setDrawing(1);
                }

                shape = null;
                break;
            }

            case "cercle": {

                if (shapec.getWidth() != 0 || shapec.getHeight() != 0) {
                    nbrC++;
                    nameO = "Cercle" + nbrC;
                    addCercle(shapec, BP.getCouleur(), BP.getFill(), BP.getCouleur(), BP.getStroke(), nameO);
                    this.setDrawing(2);
                }

                shapec = null;
                break;

            }
            case "ligne": {
                //shapel.setLine(shapel.getX1(), shapel.getY1(), evt.getX(), evt.getY());
                nbrL++;
                nameO = "Ligne" + nbrL;
                addLigne(shapel, BP.getCouleur(), BP.getStroke(), nameO);
                this.setDrawing(3);
                shapel = null;
                break;

            }

            case "selection": {
                selection = "";

            }

        }

    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Graphics2D g = (Graphics2D) getGraphics();

        switch (BP.getAtt()) {
            case "libre": {

                g.setColor(BP.getCouleur());
                g.drawLine(x0, y0, evt.getX(), evt.getY());
                x0 = evt.getX();
                y0 = evt.getY();
                ((Vector) vPoints.elementAt(Nbre)).add(new ColoredPoint(x0, y0, BP.getCouleur()));

                break;
            }
            case "rectangle": {
                int x = Math.min(startPoint.x, evt.getX());
                int y = Math.min(startPoint.y, evt.getY());
                int width = Math.abs(startPoint.x - evt.getX());
                int height = Math.abs(startPoint.y - evt.getY());

                shape.setBounds(x, y, width, height);
                repaint();

                break;
            }
            case "cercle": {
                int x = Math.min(startPoint.x, evt.getX());
                int y = Math.min(startPoint.y, evt.getY());
                int width = Math.abs(startPoint.x - evt.getX());
                int height = Math.abs(startPoint.y - evt.getY());

                shapec.setBounds(x, y, width, height);
                repaint();
                break;

            }
            case "ligne": {

                //g.drawLine(startPoint.x, startPoint.y, evt.getX(), evt.getY());
//                shapel.setX2(evt.getX());
//                shapel.setY2(evt.getY());
                shapel.setLine(startPoint.x, startPoint.y, evt.getX(), evt.getY());
                repaint();
                break;
            }
            case "selection": {
                switch (selection) {
                    case "Rectangle": {
                        ColoredRectangle cr;
                        x1 = evt.getX();
                        y1 = evt.getY();

                        cr = ColoredRectangles.get(getPosition());
                        cr.getRectangle().setBounds(x1, y1, (int) cr.getRectangle().getWidth(), (int) cr.getRectangle().getHeight());
                        if (BP.getFill()) {
                            cr.setFilled(true);
                            cr.setFill(BP.getCouleur());
                        }

                        ColoredRectangles.add(getPosition(), (ColoredRectangle) cr);

                        repaint();
                        break;

                    }
                    case "Cercle": {
                        ColoredCercle cs;
                        x1 = evt.getX();
                        y1 = evt.getY();

                        cs = ColoredCercles.get(getPosition());
                        cs.getCercle().setBounds(evt.getX(), evt.getY(), cs.getCercle().getWidth(), cs.getCercle().getHeight());
                        if (BP.getFill()) {
                            cs.setFilled(true);
                            cs.setFill(BP.getCouleur());
                        }

                        ColoredCercles.add(getPosition(), (ColoredCercle) cs);
                        repaint();
                        break;

                    }
                    case "ligne": {
                        ColoredLigne cl;
                        double dx, dy;
                        dx = evt.getX() - startPoint.x;
                        dy = evt.getY() - startPoint.y;
                        startPoint.x = evt.getX();
                        startPoint.y = evt.getY();

                        cl = ColoredLignes.get(getPosition());
                        cl.getLigne().setLine(cl.getLigne().getX1() + dx, cl.getLigne().getY1() + dy, cl.getLigne().getX2() + dx, cl.getLigne().getY2() + dy);
                        ColoredLignes.add(getPosition(), (ColoredLigne) cl);
                        repaint();

                        break;
                    }

                    case "libre": {
                        ColoredPoint cp;
                        Point tmp;
                        int dx, dy;
                        dx = evt.getX() - startPoint.x;
                        dy = evt.getY() - startPoint.y;
                        startPoint.x = evt.getX();
                        startPoint.y = evt.getY();
                        Vector polyLine = ((Vector) vPoints.elementAt(getPosition()));
                        for (int j = 0; j < polyLine.size(); j++) {
                            tmp = new Point(((ColoredPoint) polyLine.elementAt(j)).x0, ((ColoredPoint) polyLine.elementAt(j)).y0);
                            tmp.translate(dx, dy);
                            cp = new ColoredPoint(((ColoredPoint) polyLine.elementAt(j)).x0, ((ColoredPoint) polyLine.elementAt(j)).y0, ((ColoredPoint) polyLine.elementAt(j)).couleur);
                            ((Vector) vPoints.elementAt(getPosition())).set(j, new ColoredPoint(tmp.x, tmp.y, cp.couleur));
                        }
                        repaint();
                        break;
                    }
                }
                break;
            }

    }//GEN-LAST:event_formMouseDragged

    }

}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

