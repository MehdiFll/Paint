/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin;

import java.net.Socket;

/**
 *
 * @author Mehdi Fll
 */
public class ThreadF implements Runnable {

    Lab7cser ser;
    Fenetre F;

    public Fenetre getF() {
        return F;
    }
    
    
    public ThreadF(Fenetre F, Lab7cser ser) {
        this.ser = ser;
        this.F = F;
        
        Thread t = new Thread(this);
        t.start();
    }
    
    public void run() {
        
       
        this.ser.gererConnection(F);
        
        
      
        
    }
    
}
