/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin;

import org.xml.sax.Attributes;
import appdessin.classes.ColoredCercle;
import appdessin.classes.ColoredLigne;
import appdessin.classes.ColoredRectangle;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mehdi Fll
 */
public class Fenetre extends javax.swing.JFrame {

    File selectedFile = null;
    String titre;
    String login;
    String mdp;
    String key="zertyui";
    Socket monClient;
    Refresh R;

    ObjectOutputStream output = null;
    ObjectInputStream input = null;
    DataOutputStream outputd = null;
    DataInputStream inputd = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public DessinPanel getDessinPanel1() {
        return dessinPanel1;
    }

    public void setDessinPanel1(DessinPanel dessinPanel1) {
        this.dessinPanel1 = dessinPanel1;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public JTable getjTable2() {
        return jTable2;
    }

    public void setjTable2(JTable jTable2) {
        this.jTable2 = jTable2;
    }

    public void auth() {
        boolean etat;
        //System.out.println(login+"\n"+key);
        try {
            monClient = new Socket("localhost", 49990);
            output = new ObjectOutputStream(
                    monClient.getOutputStream());
            input = new ObjectInputStream(
                    monClient.getInputStream());

            outputd = new DataOutputStream(
                    monClient.getOutputStream());
            inputd = new DataInputStream(
                    monClient.getInputStream());

            if (login != null && mdp != null) {

                synchronized (input) {
                    outputd.writeUTF("auth");
                    outputd.writeUTF(login);
                    outputd.writeUTF(mdp);
                    System.out.println(getKey());
                    outputd.writeUTF(key);
                    String res = inputd.readUTF();
                    System.out.println(res);

//reponse de serveur
                    if (res.equals("OK")) {

                        R = new Refresh(input, null, this, inputd, null);
                        R.refreshOnce();
                        getDessinPanel1().repaint();
                        Dessin D = new Dessin(this);
                        R.startRefresh();

                    } else {
                        System.out.println(res);
                        input.close();
                        output.close();

                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString() + "aaaaaaaaaaaaaaaaa");
            e.printStackTrace();

        }
    }

    public void connecter() {

        String message = "A";
        try {

            while (true) {
                if (inputd.available() > 0) {
                }

                if (this.getDessinPanel1().getDrawing() != 0) {

                    System.out.println("le client est entrain de dessiner");

                    switch (this.getDessinPanel1().getDrawing()) {
                        case 1: {
                            outputd.writeInt(this.getDessinPanel1().getColoredRectangles().size());
                            output.writeObject(this.getDessinPanel1().getColoredRectangles().get(this.getDessinPanel1().getColoredRectangles().size() - 1));
                            this.getDessinPanel1().setDrawing(0);

                            break;
                        }
                        case 2: {
                            outputd.writeInt(this.getDessinPanel1().getColoredCercle().size());
                            output.writeObject(this.getDessinPanel1().getColoredCercle().get(this.getDessinPanel1().getColoredCercle().size() - 1));
                            this.getDessinPanel1().setDrawing(0);

                            break;
                        }
                        case 3: {
                            outputd.writeInt(this.getDessinPanel1().getColoredLignes().size());
                            output.writeObject(this.getDessinPanel1().getColoredLignes().get(this.getDessinPanel1().getColoredLignes().size() - 1));
                            this.getDessinPanel1().setDrawing(0);

                            break;
                        }
                        case 4: {
                            outputd.writeInt(this.getDessinPanel1().getvPoints().size());
                            output.writeObject( ((Vector) this.getDessinPanel1().getvPoints().elementAt(getDessinPanel1().Nbre)));
                            this.getDessinPanel1().setDrawing(0);

                            break;

                        }

                    }

                    if (message.equals("Quit")
                            || message.equals("quit")) {
                        break;
                    }

                }
                //R.refreshOnce();

            }
            input.close();
            output.close();
            monClient.close();

        } catch (Exception e) {
            System.out.println(e.toString() + "bbbbbbbbbbbbbbbbbbv");
            e.printStackTrace();

        }

    }
//    public void connect(){
//    Socket monClient;
// String str;
// ObjectOutputStream output = null;
// ObjectInputStream input = null;
// Fenetre F;
// String message = "A";
// try {
//    
// monClient = new Socket("localhost",49300); 
//output = new ObjectOutputStream(
// monClient.getOutputStream());
// input = new ObjectInputStream(
//monClient.getInputStream());
//
// while(true){
//
//  if (input.available() > 0) {
//                    this.getDessinPanel1().setColoredRectangles((ArrayList<ColoredRectangle>)input.readObject());
//                    this.getDessinPanel1().setvPoints((Vector)input.readObject());
//                    this.getDessinPanel1().setColoredCercle((ArrayList<ColoredCercle>)input.readObject());
//                    this.getDessinPanel1().setColoredLignes((ArrayList<ColoredLigne>)input.readObject());
//                    this.getDessinPanel1().repaint();
//                    if (message.equals("Quit")
//                           || message.equals("quit")) {
//                        break;
//                    }
//                }
//
//                if (this.getDessinPanel1().getDrawing()){
//
//                    output.writeObject(this.getDessinPanel1().getColoredRectangles());
//                    output.writeObject(this.getDessinPanel1().getvPoints());
//                    output.writeObject(this.getDessinPanel1().getColoredCercle());
//                    output.writeObject(this.getDessinPanel1().getColoredLignes());
//                    this.getDessinPanel1().repaint();
//                    if (message.equals("Quit")
//                            || message.equals("quit")) {
//                        break;
//                    }
//                    System.out.println(" --> : ");
//                }
// }
//
// output.close();
// monClient.close();
//
// }
// catch (Exception e) {
// System.out.println(e);
// }
//    
//    }

    public void open() {
        // Let's deserialize an Object
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        //int returnValue = jfc.showOpenDialog(null);
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());

            try {
                FileInputStream fileIn = new FileInputStream(selectedFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                dessinPanel1.setColoredRectangles((ArrayList<ColoredRectangle>) in.readObject());
                dessinPanel1.setvPoints((Vector) in.readObject());
                dessinPanel1.setColoredCercle((ArrayList<ColoredCercle>) in.readObject());
                dessinPanel1.setColoredLignes((ArrayList<ColoredLigne>) in.readObject());
                dessinPanel1.repaint();
                in.close();
                fileIn.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
            }
        }
        titre = "AppDessin - " + selectedFile.getName();
        this.setTitle(titre);
    }

    public void saveAs() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        //int returnValue = jfc.showOpenDialog(null);
        int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());

            try {

                FileOutputStream fileOut = new FileOutputStream(selectedFile);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(this.getDessinPanel1().getColoredRectangles());
                out.writeObject(this.getDessinPanel1().getvPoints());
                out.writeObject(this.getDessinPanel1().getColoredCercle());
                out.writeObject(this.getDessinPanel1().getColoredLignes());
                out.close();
                fileOut.close();
                System.out.println("\nSerialization Successful... Checkout your specified output file..\n");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        titre = "AppDessin - " + selectedFile.getName();
        this.setTitle(titre);
    }

    public void save() {

        try {

            FileOutputStream fileOut = new FileOutputStream(selectedFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dessinPanel1.getColoredRectangles());
            out.writeObject(dessinPanel1.getvPoints());
            out.writeObject(dessinPanel1.getColoredCercle());
            out.writeObject(dessinPanel1.getColoredLignes());

            out.close();
            fileOut.close();
            System.out.println("\nSerialization Successful... Checkout your specified output file..\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nouveauDessin() {
        dessinPanel1.getColoredRectangles().clear();
        dessinPanel1.getvPoints().clear();
        dessinPanel1.getColoredCercle().clear();
        dessinPanel1.getColoredLignes().clear();
        DefaultTableModel m = (DefaultTableModel) jTable2.getModel();
        m.setRowCount(0);
        dessinPanel1.repaint();
        titre = "AppDessin - Sans nom";
        this.setTitle(titre);

    }

    /**
     * Creates new form appdessin
     */
    public Fenetre() {
        initComponents();
        dessinPanel1.setBP(boutonPanel1);
        dessinPanel1.setTable(jTable2);
        titre = "AppDessin - Sans nom";
        this.setTitle(titre);

        //       *.* 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        boutonPanel2 = new appdessin.BoutonPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        boutonPanel1 = new appdessin.BoutonPanel();
        dessinPanel1 = new appdessin.DessinPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(30, 402));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "   ", "    "
            }
        ));
        jTable2.setMaximumSize(new java.awt.Dimension(30, 0));
        jTable2.setOpaque(false);
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 160, 600));
        getContentPane().add(boutonPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        javax.swing.GroupLayout dessinPanel1Layout = new javax.swing.GroupLayout(dessinPanel1);
        dessinPanel1.setLayout(dessinPanel1Layout);
        dessinPanel1Layout.setHorizontalGroup(
            dessinPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        dessinPanel1Layout.setVerticalGroup(
            dessinPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        getContentPane().add(dessinPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 800, 550));

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("Fichier");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Nouveau");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Ouvrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Enregistrer");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Enregistrer sous");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("A propos");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Services");

        jMenuItem5.setText("Se connecter");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int response = JOptionPane.showConfirmDialog(null, "Voulez vous enregistrer avant de continuer?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {

        } else if (response == JOptionPane.YES_OPTION) {
            if (getSelectedFile() == null) {
                saveAs();
            } else {
                save();
            }

        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Voulez vous enregistrer ?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            nouveauDessin();
        } else if (response == JOptionPane.YES_OPTION) {
            if (selectedFile == null) {
                saveAs();
            } else {
                save();
            }
            nouveauDessin();

        } else if (response == JOptionPane.CLOSED_OPTION) {
            nouveauDessin();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Voulez vous enregistrer avant de continuer?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            open();
        } else if (response == JOptionPane.YES_OPTION) {
            if (selectedFile == null) {
                saveAs();
            } else {
                save();
            }
            open();

        } else if (response == JOptionPane.CLOSED_OPTION) {
            open();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (selectedFile == null) {
            saveAs();
        } else {
            save();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        saveAs();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(this,
                "Cette application a été créée par \n"
                + "Mehdi Felloul et Wiam Gharbi ", "A Propos ",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Login L = new Login(this);
        L.setVisible(true);


    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                WebLookAndFeel.install ();
//                WebLookAndFeel.setAllowLinuxTransparency(true);

                //
                Fenetre F = new Fenetre();
                F.setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private appdessin.BoutonPanel boutonPanel1;
    private appdessin.BoutonPanel boutonPanel2;
    private appdessin.DessinPanel dessinPanel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

}
