package fr.serveur;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServeurJeu {
    private ServerSocket socketServeur;
    private final ExecutorService pool;
    private final int port;
    
    public ServeurJeu(int port) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(2);
    }
    
    public void demarrer() {
        try {
            socketServeur = new ServerSocket(port);
            System.out.println(" Serveur Polymorphia démarré sur le port " + port);
            System.out.println(" En attente de connexions de joueurs...");
            
            while (true) {
                Socket socketClient = socketServeur.accept();
                System.out.println(" Nouveau client connecté : " + 
                                 socketClient.getInetAddress());
                
                GestionnaireClient gestionnaire = new GestionnaireClient(socketClient);
                pool.execute(gestionnaire);
            }
        } catch (IOException e) {
            System.err.println(" Erreur serveur : " + e.getMessage());
        }
    }
}