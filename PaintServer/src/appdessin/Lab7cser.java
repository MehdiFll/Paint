/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdessin;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class Lab7cser {

    Connexion C;

    private ServerSocket server;
    private int port = 49990;
    private boolean arretMarche = true;
    Refresh R;
    ArrayList<Socket> listClients = new ArrayList<Socket>();

    ArrayList<Projet> projets = new ArrayList<Projet>();
    public Connexion getC() {
        return C;
    }

    public void setC(Connexion C) {
        this.C = C;
    }

    public Lab7cser() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setArretMarche(boolean arretMarche) {
        this.arretMarche = arretMarche;
    }

    public void gererConnection(Fenetre F) {
        System.out.println("En Attente : " + server.getLocalSocketAddress());
        R = new Refresh(F);
// Acceptation des connexions
        while (true) {
            try {
                Socket socket = server.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                DataOutputStream outd = new DataOutputStream(socket.getOutputStream());
                DataInputStream ind = new DataInputStream(socket.getInputStream());
                listClients.add(socket);

                synchronized(this){
                this.setC(new Connexion(socket, this, in, out, ind, outd));
                               this.getC().setF(F);}
                
                System.out.println("Connexion de : " + socket.getRemoteSocketAddress());
                for (int i = 0; i<listClients.size(); i++) {
                    System.out.println(listClients.get(i));
                }
                if (!arretMarche) {
                    System.out.println("Serveur arrÃªtÃ©. ");
                    server.close();
                    break;
                }

               //System.out.println(listClients.get(listClients.size()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
