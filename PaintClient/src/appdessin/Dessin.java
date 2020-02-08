/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin;

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
public class Dessin implements Runnable {

    Fenetre F;

    public Dessin(Fenetre F) {
        this.F= F;
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {

        F.connecter();
    }

}