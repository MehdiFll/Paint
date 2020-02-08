/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin;

import appdessin.classes.ColoredCercle;
import appdessin.classes.ColoredLigne;
import appdessin.classes.ColoredRectangle;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mehdi Fll
 */
public class Refresh implements Runnable {

    ObjectInputStream input = null;
    ObjectOutputStream out = null;
    DataOutputStream outputd = null;
    DataInputStream inputd = null;
    Fenetre F;

    public Refresh(ObjectInputStream input, ObjectOutputStream out, Fenetre F, DataInputStream inputd, DataOutputStream outputd) {
        this.input = input;
        this.out = out;
        this.outputd = outputd;
        this.inputd = inputd;
        this.F = F;

    }

    public void startRefresh() {
        Thread t = new Thread(this);
        t.start();
    }

    public void refreshOnce() {
        try {
            F.getDessinPanel1().getColoredRectangles().clear();
            synchronized (input) {

                int size = inputd.readInt();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        F.getDessinPanel1().getColoredRectangles().add((ColoredRectangle) input.readObject());
                    }
                    System.out.println("refreshed rectangles");
                }

                size = inputd.readInt();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        F.getDessinPanel1().getColoredCercle().add((ColoredCercle) input.readObject());
                    }
                    System.out.println("refreshed cercle");
                }

                size = inputd.readInt();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        F.getDessinPanel1().getColoredLignes().add((ColoredLigne) input.readObject());
                    }
                    System.out.println("refreshed ligne");
                }

                size = inputd.readInt();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        F.getDessinPanel1().getvPoints().add(F.getDessinPanel1().Nbre, (Vector) input.readObject());
                    }
                    System.out.println("refreshed libre");
                }
//possibilité d'ajouter une boucle

                //Thread.sleep(7000);
            }

        } catch (Exception ex) {
            System.out.println(ex.toString() + " cccccccccccccccccccccccc");
            ex.printStackTrace();

        }

    }

    public void run() {

        try {
            // Thread.sleep(2000);
            if (F.getDessinPanel1().getDrawing() != 0) {
                wait();
            }
            while (true) {
                //
                //Thread.sleep(2000);
                synchronized (input) {
                    int size = inputd.readInt();
                    if (size > 0) {
                        F.getDessinPanel1().getColoredRectangles().clear();
                        for (int i = 0; i < size; i++) {
                            F.getDessinPanel1().getColoredRectangles().add((ColoredRectangle) input.readObject());
                            System.out.println("refresh par thread reçu " + i);
                        }
                    }

                    size = inputd.readInt();
                    if (size > 0) {
                                                F.getDessinPanel1().getColoredCercle().clear();

                        for (int i = 0; i < size; i++) {
                            F.getDessinPanel1().getColoredCercle().add((ColoredCercle) input.readObject());
                        }
                        System.out.println("refreshed cercle");
                    }

                    size = inputd.readInt();
                    if (size > 0) {
                                                F.getDessinPanel1().getColoredLignes().clear();

                        for (int i = 0; i < size; i++) {
                            
                            F.getDessinPanel1().getColoredLignes().add((ColoredLigne) input.readObject());
                        }
                        System.out.println("refreshed ligne");
                    }

                    size = inputd.readInt();
                    if (size > 0) {
                                                F.getDessinPanel1().getvPoints().clear();

                        for (int i = 0; i < size; i++) {
                            F.getDessinPanel1().getvPoints().add(F.getDessinPanel1().Nbre, (Vector) input.readObject());
                        }
                        System.out.println("refreshed libre");
                    }
                }

                F.getDessinPanel1().repaint();
//possibilité d'ajouter une boucle

                //Thread.sleep(7000);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString() + " cccccccccccccccccccccccc");
            ex.printStackTrace();
        }

    }

}
