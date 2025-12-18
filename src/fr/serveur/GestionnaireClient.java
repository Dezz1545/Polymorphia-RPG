package fr.serveur;

import java.io.*;
import java.net.*;

public class GestionnaireClient implements Runnable {
    private final Socket socketClient;
    
    public GestionnaireClient(Socket socket) {
        this.socketClient = socket;
    }
    
    @Override
    public void run() {
        try (
            BufferedReader entree = new BufferedReader(
                new InputStreamReader(socketClient.getInputStream()));
            PrintWriter sortie = new PrintWriter(
                socketClient.getOutputStream(), true);
        ) {
            sortie.println("BIENVENUE SUR POLYMORPHIA PvP");
            sortie.println("En attente d'un adversaire...");
            
            String messageClient;
            while ((messageClient = entree.readLine()) != null) {
                System.out.println("Message du client : " + messageClient);
                sortie.println("Serveur : " + messageClient);
            }
        } catch (IOException e) {
            System.err.println("  Erreur avec le client : " + e.getMessage());
        } finally {
            try {
                socketClient.close();
            } catch (IOException e) {
                System.err.println("  Erreur fermeture socket : " + e.getMessage());
            }
        }
    }
}