/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin;

import appdessin.classes.ColoredCercle;
import appdessin.classes.ColoredLigne;
import appdessin.classes.ColoredPoint;
import appdessin.classes.ColoredRectangle;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author WIAM
 */
class Connexion implements Runnable {

    private Socket socketDialogue;
    Lab7cser ser;
    Fenetre F;
    ObjectInputStream input = null;
    ObjectOutputStream out = null;
    DataOutputStream outputd = null;
    DataInputStream inputd = null;
    String key;
    Projet p;
    int indice;

    Connexion(Socket socket, Lab7cser ser, ObjectInputStream in, ObjectOutputStream out, DataInputStream inputd, DataOutputStream outputd) {
        this.socketDialogue = socket;
        this.ser = ser;
        this.input = in;
        this.out = out;
        this.outputd = outputd;
        this.inputd = inputd;
        Thread t = new Thread(this);
        t.start();
    }

    public void setF(Fenetre F) {
        this.F = F;
    }

    static final String READ_OBJECT_SQL = "SELECT rectangles, cercles, lignes, libres FROM projet WHERE cle = ?";
    static final String INSERT_OBJECT_SQL = "INSERT INTO projet(cle, rectangles, cercles, lignes, libres) VALUES (?, ?, ? , ? ,?)";
    static final String UPDATE_OBJECT_SQL = "UPDATE projet SET rectangles = ?, cercles = ?, lignes = ?, libres = ? WHERE CLE = ?";

    public void readJavaObject(Connection conn, Projet p) throws Exception {

        PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
        pstmt.setString(1, key);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        ByteArrayInputStream bais = (ByteArrayInputStream) rs.getBinaryStream(1);
        ObjectInputStream in = new ObjectInputStream(bais);
        Object Obj = in.readObject();
        p.setColoredRectangles((ArrayList<ColoredRectangle>) Obj);

        bais = (ByteArrayInputStream) rs.getBinaryStream(2);
        in = new ObjectInputStream(bais);
        Obj = in.readObject();
        p.setColoredCercle((ArrayList<ColoredCercle>) Obj);

        bais = (ByteArrayInputStream) rs.getBinaryStream(3);
        in = new ObjectInputStream(bais);
        Obj = in.readObject();
        p.setColoredLignes((ArrayList<ColoredLigne>) Obj);

        bais = (ByteArrayInputStream) rs.getBinaryStream(4);
        in = new ObjectInputStream(bais);
        Obj = in.readObject();
        p.setvPoints((Vector) Obj);

        rs.close();
        pstmt.close();
    }

    public void updateJavaObject(Connection conn, Object rec, Object cer, Object lin, Object lib) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(UPDATE_OBJECT_SQL);
        pstmt.setString(5, key);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(rec);
        oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        pstmt.setBinaryStream(1, bais);

        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(cer);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        pstmt.setBinaryStream(2, bais);

        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(lin);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        pstmt.setBinaryStream(3, bais);

        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(lib);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        pstmt.setBinaryStream(4, bais);

        int rs = pstmt.executeUpdate();
        pstmt.close();
        System.out.println("writeJavaObject: done serializing: ");

    }

    public void insertJavaObject(Connection conn, Object rec, Object cer, Object lin, Object lib) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(INSERT_OBJECT_SQL);
        pstmt.setString(1, key);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(rec);
        oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        pstmt.setBinaryStream(2, bais);

        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(cer);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        pstmt.setBinaryStream(3, bais);

        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(lin);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        pstmt.setBinaryStream(4, bais);

        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(lib);
        oos.close();
        bais = new ByteArrayInputStream(baos.toByteArray());
        pstmt.setBinaryStream(5, bais);

        int rs = pstmt.executeUpdate();
        pstmt.close();
        System.out.println("writeJavaObject: done serializing: ");

    }

    @Override
    public void run() {

        boolean etat = false;
        try {

            String S = inputd.readUTF();
            if (S.equals("auth")) {
                String login = inputd.readUTF();
                String mdp = inputd.readUTF();
                key = inputd.readUTF();
                System.out.println(login + " " + mdp + " " + key);
                DB db = new DB();
                try {
                    Connection con = db.getCon();
                    Statement stmt = con.createStatement();
                    Statement stmt2 = con.createStatement();

                    ResultSet res = stmt.executeQuery("SELECT * FROM CLIENT WHERE login ='" + login + "' AND mdp ='" + mdp + "'");
                    ResultSet res1 = stmt2.executeQuery("SELECT * FROM PROJET WHERE  cle='" + key + "'");
                    if (res.next()) {
                        if (res1.next()) {
                            outputd.writeUTF("OK");
                            p = new Projet(key);
                            this.readJavaObject(con, p);
                            F.getDessinPanel1().affichage(ser.projets, F);
                            F.getDessinPanel1().repaint();

                            if (ser.projets.size() == 0) {
                                p.listIn.add(input);
                                p.listInd.add(inputd);
                                p.listOut.add(out);
                                p.listOutd.add(outputd);
                                p.listClients.add(socketDialogue);

                                ser.projets.add(p);
                                indice = ser.projets.size() - 1;

                            } else {
                                for (int i = 0; i < ser.projets.size(); i++) {
                                    if (ser.projets.get(i).getKey().equals(key)) {
                                        System.out.println(ser.projets.get(i).getKey());
                                        ser.projets.get(i).listIn.add(input);
                                        ser.projets.get(i).listInd.add(inputd);
                                        ser.projets.get(i).listOut.add(out);
                                        ser.projets.get(i).listOutd.add(outputd);
                                        ser.projets.get(i).listClients.add(socketDialogue);
                                        indice = i;
                                        p = ser.projets.get(i);

                                        break;
                                    }
                                }
                            }
                        } else {
                            //insert
                            outputd.writeUTF("OK");
                            System.out.println("nouveau projet.");
                            p = new Projet(key);
                            p.listIn.add(input);
                            p.listInd.add(inputd);
                            p.listOut.add(out);
                            p.listOutd.add(outputd);
                            p.listClients.add(socketDialogue);

                            ser.projets.add(p);
                            indice = ser.projets.size() - 1;
                            this.insertJavaObject(con, p.getColoredRectangles(), p.getColoredCercle(), p.getColoredLignes(), p.getvPoints());
                        }

                        etat = true;
                        ser.R.refreshnouveau(p, null, out, null, outputd);

                    } else {
                        outputd.writeUTF("NOT OK");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            ////////////////////////////////////////
            while (etat) {

                Object O;
                int max = inputd.readInt();
//                for(int i=0; i<ser.projets.size();i++) {
//                    if(ser.projets.get(i).getKey().equals(key))
//                        ser.projets.get(i).visible=true;
//                }

                O = input.readObject(); //switch selon la forme
                System.out.println(O.getClass().getSimpleName());
                switch (O.getClass().getSimpleName()) {
                    case "ColoredRectangle": {
                        //System.out.println(O);
                        p.getColoredRectangles().add(max - 1, (ColoredRectangle) O);
                        ser.projets.add(indice, p);
                        ser.R.refreshdessin(p, p.listClients, p.listIn, p.listOut, null, p.listOutd);
                        
                        F.getDessinPanel1().affichage(ser.projets, F);
                        F.getDessinPanel1().repaint();
                        DB db = new DB();
                        Connection con = db.getCon();
                        this.updateJavaObject(con, ser.projets.get(indice).getColoredRectangles(), ser.projets.get(indice).getColoredCercle(), ser.projets.get(indice).getColoredLignes(), ser.projets.get(indice).getvPoints());
                        break;
                    }//envoyer un indice au clients pour qu'ils refraichissent leur dessin
                    case "ColoredCercle": {
                        p.getColoredCercle().add(max - 1, (ColoredCercle) O);
                        ser.projets.add(indice, p);
                        ser.R.refreshdessin(p, p.listClients, p.listIn, p.listOut, null, p.listOutd);

                        F.getDessinPanel1().affichage(ser.projets, F);
                        F.getDessinPanel1().repaint();
                        DB db = new DB();
                        Connection con = db.getCon();
                        this.updateJavaObject(con, p.getColoredRectangles(), p.getColoredCercle(), p.getColoredLignes(), p.getvPoints());
                        break;
                    }
                    case "ColoredLigne": {
                        p.getColoredLignes().add(max - 1, (ColoredLigne) O);
                        ser.projets.add(indice, p);
                        ser.R.refreshdessin(p, p.listClients, p.listIn, p.listOut, null, p.listOutd);

                        F.getDessinPanel1().affichage(ser.projets, F);
                        F.getDessinPanel1().repaint();
                        DB db = new DB();
                        Connection con = db.getCon();
                        this.updateJavaObject(con, p.getColoredRectangles(), p.getColoredCercle(), p.getColoredLignes(), p.getvPoints());
                        break;
                    }
                    case "Vector": {
                        //System.out.println(max);

                        p.getvPoints().add(F.getDessinPanel1().Nbre, (Vector) O);
                        ser.projets.add(indice, p);
                        ser.R.refreshdessin(p, p.listClients, p.listIn, p.listOut, null, p.listOutd);

                        //listes des projets li kayna f dessin panel 
                        // wahd indice ida kan admin ki chouf p f server 
                        //repaint 
                        F.getDessinPanel1().affichage(ser.projets, F);
                        F.getDessinPanel1().repaint();

                        DB db = new DB();
                        Connection con = db.getCon();
                        this.updateJavaObject(con, p.getColoredRectangles(), p.getColoredCercle(), p.getColoredLignes(), p.getvPoints());
                        break;
                    }

                }

            }

            input.close();
            out.close();
            System.out.println("FIN..");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
