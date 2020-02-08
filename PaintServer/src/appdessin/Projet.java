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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Mehdi Fll
 */
public class Projet {
       ArrayList<ObjectInputStream> listIn = new ArrayList<ObjectInputStream>();
    ArrayList<ObjectOutputStream> listOut = new ArrayList<ObjectOutputStream>();
    ArrayList<DataInputStream> listInd = new ArrayList<DataInputStream>();
    ArrayList<DataOutputStream> listOutd = new ArrayList<DataOutputStream>();
      ArrayList<Socket> listClients = new ArrayList<Socket>();
      boolean visible=false;
            private String key;
            private Vector vPoints = new Vector();
            private ArrayList<ColoredRectangle> ColoredRectangles = new ArrayList<ColoredRectangle>();
            private ArrayList<ColoredCercle> ColoredCercle = new ArrayList<ColoredCercle>();
            private ArrayList<ColoredLigne> ColoredLignes = new ArrayList<ColoredLigne>();



    public Projet(String key) {
        this.key = key;
    }

    public ArrayList<ObjectInputStream> getListIn() {
        return listIn;
    }

    public void setListIn(ArrayList<ObjectInputStream> listIn) {
        this.listIn = listIn;
    }

    public ArrayList<ObjectOutputStream> getListOut() {
        return listOut;
    }

    public void setListOut(ArrayList<ObjectOutputStream> listOut) {
        this.listOut = listOut;
    }

    public ArrayList<DataInputStream> getListInd() {
        return listInd;
    }

    public void setListInd(ArrayList<DataInputStream> listInd) {
        this.listInd = listInd;
    }

    public ArrayList<DataOutputStream> getListOutd() {
        return listOutd;
    }

    public void setListOutd(ArrayList<DataOutputStream> listOutd) {
        this.listOutd = listOutd;
    }
    
    

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public Vector getvPoints() {
        return vPoints;
    }

    public void setvPoints(Vector vPoints) {
        this.vPoints = vPoints;
    }

    public ArrayList<ColoredRectangle> getColoredRectangles() {
        return ColoredRectangles;
    }

    public void setColoredRectangles(ArrayList<ColoredRectangle> ColoredRectangles) {
        this.ColoredRectangles = ColoredRectangles;
    }

    public ArrayList<ColoredCercle> getColoredCercle() {
        return ColoredCercle;
    }

    public void setColoredCercle(ArrayList<ColoredCercle> ColoredCercles) {
        this.ColoredCercle = ColoredCercles;
    }

    public ArrayList<ColoredLigne> getColoredLignes() {
        return ColoredLignes;
    }

    public void setColoredLignes(ArrayList<ColoredLigne> ColoredLignes) {
        this.ColoredLignes = ColoredLignes;
    }




}

