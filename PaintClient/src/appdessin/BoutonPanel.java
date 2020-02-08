/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

/**
 *
 * @author Mehdi Fll
 */
public class BoutonPanel extends javax.swing.JPanel {

    String att = "libre";
    public Color Couleur = Color.BLACK;
    Boolean fill = false;
    String Stroke = "dashed";
    ButtonGroup b;

    public Color getCouleur() {
        return Couleur;
    }

    public String getStroke() {
        return Stroke;
    }

    public void setStroke(String Stroke) {
        this.Stroke = Stroke;
    }

    public Boolean getFill() {
        return fill;
    }

    public void setFill(Boolean fill) {
        this.fill = fill;
    }

    public String getAtt() {
        return att;
    }

    /**
     * Creates new form NewJPanel
     */
    public BoutonPanel() {
        initComponents();
        type_ligne.setSelectedIndex(0);
        libre.setSelected(true);
        libre.setBorder(BorderFactory.createEmptyBorder());
        libre.setContentAreaFilled(false);

        ligne.setBorder(BorderFactory.createEmptyBorder());
        ligne.setContentAreaFilled(false);

        rectangle.setBorder(BorderFactory.createEmptyBorder());
        rectangle.setContentAreaFilled(false);

        cercle.setBorder(BorderFactory.createEmptyBorder());
        cercle.setContentAreaFilled(false);

        selection.setBorder(BorderFactory.createEmptyBorder());
        selection.setContentAreaFilled(false);

        remplissage.setBorder(BorderFactory.createEmptyBorder());
        remplissage.setContentAreaFilled(false);

        couleur.setBorder(BorderFactory.createEmptyBorder());
        couleur.setContentAreaFilled(false);

        b = new ButtonGroup();
        b.add(libre);
        b.add(ligne);
        b.add(rectangle);
        b.add(cercle);
        // b.add(jToggleButton5);
        if (libre.isSelected()) {
            libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libres.png")));
        } else {
            libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libre.png")));
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser5 = new javax.swing.JColorChooser();
        couleur = new javax.swing.JButton();
        type_ligne = new javax.swing.JComboBox<>();
        libre = new javax.swing.JToggleButton();
        rectangle = new javax.swing.JToggleButton();
        cercle = new javax.swing.JToggleButton();
        ligne = new javax.swing.JToggleButton();
        selection = new javax.swing.JToggleButton();
        remplissage = new javax.swing.JToggleButton();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(40, 600));

        couleur.setBackground(new java.awt.Color(24, 44, 97));
        couleur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/couleur.png"))); // NOI18N
        couleur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                couleurMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                couleurMouseExited(evt);
            }
        });
        couleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                couleurActionPerformed(evt);
            }
        });

        type_ligne.setBackground(new java.awt.Color(0, 0, 0));
        type_ligne.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        type_ligne.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "basic", "dashed", "lvl3" }));
        type_ligne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_ligneActionPerformed(evt);
            }
        });

        libre.setBackground(new java.awt.Color(24, 44, 97));
        libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libre.png"))); // NOI18N
        libre.setActionCommand("libre");
        libre.setOpaque(true);
        libre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                libreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                libreMouseExited(evt);
            }
        });
        libre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libreActionPerformed(evt);
            }
        });

        rectangle.setBackground(new java.awt.Color(24, 44, 97));
        rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/rectangle.png"))); // NOI18N
        rectangle.setActionCommand("rectangle");
        rectangle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rectangleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rectangleMouseExited(evt);
            }
        });
        rectangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rectangleActionPerformed(evt);
            }
        });

        cercle.setBackground(new java.awt.Color(24, 44, 97));
        cercle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/cercle.png"))); // NOI18N
        cercle.setActionCommand("cercle");
        cercle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cercleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cercleMouseExited(evt);
            }
        });
        cercle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cercleActionPerformed(evt);
            }
        });

        ligne.setBackground(new java.awt.Color(24, 44, 97));
        ligne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/ligne.png"))); // NOI18N
        ligne.setActionCommand("ligne");
        ligne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ligneMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ligneMouseExited(evt);
            }
        });
        ligne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ligneActionPerformed(evt);
            }
        });

        selection.setBackground(new java.awt.Color(24, 44, 97));
        selection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/selection.png"))); // NOI18N
        selection.setActionCommand("selection");
        selection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectionMouseExited(evt);
            }
        });
        selection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionActionPerformed(evt);
            }
        });

        remplissage.setBackground(new java.awt.Color(24, 44, 97));
        remplissage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/fill.png"))); // NOI18N
        remplissage.setActionCommand("selection");
        remplissage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                remplissageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                remplissageMouseExited(evt);
            }
        });
        remplissage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remplissageActionPerformed(evt);
            }
        });

        jSeparator12.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator11.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(type_ligne, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator9)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(libre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(ligne, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cercle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rectangle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selection, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(remplissage, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(couleur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator11))
                .addGap(114, 114, 114))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(libre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ligne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cercle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rectangle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(couleur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selection, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(remplissage, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(type_ligne, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void couleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_couleurActionPerformed
        Couleur = JColorChooser.showDialog(null, "Choose a color", Color.RED);

        if (couleur.isSelected()) {
            couleur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/couleurs.png")));
        } else {
            couleur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/couleur.png")));
        }
                         }//GEN-LAST:event_couleurActionPerformed

    private void type_ligneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_ligneActionPerformed
        setStroke(type_ligne.getSelectedItem().toString());
    }//GEN-LAST:event_type_ligneActionPerformed

    private void selectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionActionPerformed
        if (selection.isSelected()) {
            att = selection.getActionCommand();;

            libre.setEnabled(false);
            libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libre.png")));
            ligne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/ligne.png")));
            rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/rectangle.png")));
            cercle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/cercle.png")));
            selection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/selections.png")));
            ligne.setEnabled(false);

            rectangle.setEnabled(false);

            cercle.setEnabled(false);
        } else {
            libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libres.png")));
            ligne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/ligne.png")));
            rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/rectangle.png")));
            cercle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/cercle.png")));
            selection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/selection.png")));
            //b.getSelection();
            libre.setEnabled(true);
            libre.setSelected(true);
            ligne.setEnabled(true);
            //jToggleButton5.setEnabled(true);
            rectangle.setEnabled(true);

            cercle.setEnabled(true);
            att = "libre";

        }// TODO add your handling code here:
    }//GEN-LAST:event_selectionActionPerformed

    private void ligneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ligneActionPerformed
        if (ligne.isSelected()) {
            libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libre.png")));
            ligne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/lignes.png")));
            rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/rectangle.png")));
            cercle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/cercle.png")));
            selection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/selection.png")));
            libre.setSelected(false);
            att = ligne.getActionCommand();

            rectangle.setSelected(false);
            cercle.setSelected(false);
        } else {
            att = "";
        }// TODO add your handling code here:
    }//GEN-LAST:event_ligneActionPerformed

    private void cercleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cercleActionPerformed
        if (cercle.isSelected()) {
            libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libre.png")));
            ligne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/ligne.png")));
            rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/rectangle.png")));
            cercle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/cercles.png")));
            selection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/selection.png")));
            libre.setSelected(false);
            att = cercle.getActionCommand();

            rectangle.setSelected(false);
            ligne.setSelected(false);
        } else {
            att = "";
        }// TODO add your handling code here:
    }//GEN-LAST:event_cercleActionPerformed

    private void rectangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rectangleActionPerformed
        if (rectangle.isSelected()) {
            libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libre.png")));
            ligne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/ligne.png")));
            rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/rectangles.png")));
            cercle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/cercle.png")));
            selection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/selection.png")));
            libre.setSelected(false);
            att = rectangle.getActionCommand();

            ligne.setSelected(false);
            cercle.setSelected(false);
        } else {
            att = "";
        }// TODO add your handling code here:
    }//GEN-LAST:event_rectangleActionPerformed

    private void libreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libreActionPerformed
        if (libre.isSelected()) {

            libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libres.png")));
            ligne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/ligne.png")));
            rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/rectangle.png")));
            cercle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/cercle.png")));
            selection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/selection.png")));

            ligne.setSelected(false);
            att = libre.getActionCommand();

            rectangle.setSelected(false);
            cercle.setSelected(false);
        } else {
            att = "";
        }// TODO add your handling code here:
    }//GEN-LAST:event_libreActionPerformed

    private void libreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libreMouseExited
        if (libre.isSelected() == false)
libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libre.png")));    }//GEN-LAST:event_libreMouseExited

    private void libreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libreMouseEntered

libre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/libres.png")));    }//GEN-LAST:event_libreMouseEntered

    private void remplissageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remplissageActionPerformed
        if (this.remplissage.isSelected()) {
            remplissage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/fills.png")));
            setFill(true);
        } else {
            remplissage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/fill.png")));
            setFill(false);
        }     // TODO add your handling code here:
    }//GEN-LAST:event_remplissageActionPerformed

    private void ligneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ligneMouseEntered
        ligne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/lignes.png")));
    }//GEN-LAST:event_ligneMouseEntered

    private void ligneMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ligneMouseExited
        if (ligne.isSelected() == false) {
            ligne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/ligne.png")));
        }
    }//GEN-LAST:event_ligneMouseExited

    private void cercleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cercleMouseEntered
        cercle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/cercles.png")));
    }//GEN-LAST:event_cercleMouseEntered

    private void cercleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cercleMouseExited
        if (cercle.isSelected() == false) {
            cercle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/cercle.png")));
        }
    }//GEN-LAST:event_cercleMouseExited

    private void rectangleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rectangleMouseEntered
        rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/rectangles.png")));
    }//GEN-LAST:event_rectangleMouseEntered

    private void rectangleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rectangleMouseExited
        if (rectangle.isSelected() == false) {
            rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/rectangle.png")));
        }
    }//GEN-LAST:event_rectangleMouseExited

    private void selectionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectionMouseEntered
        selection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/selections.png")));
    }//GEN-LAST:event_selectionMouseEntered

    private void selectionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectionMouseExited
        if (selection.isSelected() == false) {
            selection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/selection.png")));
        }
    }//GEN-LAST:event_selectionMouseExited

    private void remplissageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remplissageMouseEntered
        remplissage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/fills.png")));
    }//GEN-LAST:event_remplissageMouseEntered

    private void remplissageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remplissageMouseExited
        if (remplissage.isSelected() == false) {
            remplissage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/fill.png")));
        }
    }//GEN-LAST:event_remplissageMouseExited

    private void couleurMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_couleurMouseEntered
        couleur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/couleurs.png")));
    }//GEN-LAST:event_couleurMouseEntered

    private void couleurMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_couleurMouseExited
        if (couleur.isSelected() == false) {
            couleur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/appdessin/images/couleur.png")));
        }
    }//GEN-LAST:event_couleurMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton cercle;
    private javax.swing.JButton couleur;
    private javax.swing.JColorChooser jColorChooser5;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JToggleButton libre;
    private javax.swing.JToggleButton ligne;
    private javax.swing.JToggleButton rectangle;
    private javax.swing.JToggleButton remplissage;
    private javax.swing.JToggleButton selection;
    private javax.swing.JComboBox<String> type_ligne;
    // End of variables declaration//GEN-END:variables
}
