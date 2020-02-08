/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mehdi Fll
 */
public class Refresh {

    Fenetre F;

    public Refresh(Fenetre F) {

        this.F = F;

    }

    public void refreshdessin(Projet p, ArrayList<Socket> listClients, ArrayList<ObjectInputStream> in, ArrayList<ObjectOutputStream> out, ArrayList<DataInputStream> ind, ArrayList<DataOutputStream> outd) {

        //possibilité d'ajouter une boucle
        for (int i = 0; i < listClients.size(); i++) {
            try {
                
                int size = p.getColoredRectangles().size();
                outd.get(i).writeInt(size);
                if (size > 0) {
                    for (int j = 0; j < p.getColoredRectangles().size(); j++) {
                        out.get(i).writeObject(p.getColoredRectangles().get(j));
                    }
                    System.out.println("refresh des rectangles pour thread est envoyé");
                }
                
                size = p.getColoredCercle().size();
                outd.get(i).writeInt(size);
                if (size > 0) {
                    for (int j = 0; j < p.getColoredCercle().size(); j++) {
                        out.get(i).writeObject(p.getColoredCercle().get(j));
                    }
                    System.out.println("refresh des cercles pour thread est envoyé");
                }
                
                size = p.getColoredLignes().size();
                outd.get(i).writeInt(size);
                if (size > 0) {
                    for (int j = 0; j < p.getColoredLignes().size(); j++) {
                        out.get(i).writeObject(p.getColoredLignes().get(j));
                    }
                    System.out.println("refresh pour thread est envoyé");
                }
                
                size = p.getvPoints().size();
                outd.get(i).writeInt(size);
                if (size > 0) {
                    for (int j = 0; j < p.getvPoints().size(); j++) {
                        out.get(i).writeObject(p.getvPoints().get(j));
                    }
                    System.out.println("refresh pour thread est envoyé");
                }
                
                

            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }

    }

    public void refreshnouveau(Projet p,ObjectInputStream in, ObjectOutputStream out, DataInputStream ind, DataOutputStream outd) {
        try {

            
                int size = p.getColoredRectangles().size();
                outd.writeInt(size);
                if (size > 0) {
                    for (int j = 0; j < p.getColoredRectangles().size(); j++) {
                        out.writeObject(p.getColoredRectangles().get(j));
                    }
                    System.out.println("refresh des rectangles pour thread est envoyé");
                }
                
                size = p.getColoredCercle().size();
                outd.writeInt(size);
                if (size > 0) {
                    for (int j = 0; j < p.getColoredCercle().size(); j++) {
                        out.writeObject(p.getColoredCercle().get(j));
                    }
                    System.out.println("refresh des cercles pour thread est envoyé");
                }
                
                size = p.getColoredLignes().size();
                outd.writeInt(size);
                if (size > 0) {
                    for (int j = 0; j < p.getColoredLignes().size(); j++) {
                        out.writeObject(p.getColoredLignes().get(j));
                    }
                    System.out.println("refresh des lignes pour thread est envoyé");
                }
                
                size = p.getvPoints().size();
                outd.writeInt(size);
                if (size > 0) {
                    for (int j = 0; j < p.getvPoints().size(); j++) {
                        out.writeObject(p.getvPoints().get(j));
                    }
                    System.out.println("refresh des libres pour thread est envoyé");
                }
                
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

}
