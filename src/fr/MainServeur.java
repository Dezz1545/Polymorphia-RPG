package fr;

import fr.serveur.ServeurJeu;

public class MainServeur {
    public static void main(String[] args) {
        System.out.println("=== SERVEUR POLYMORPHIA ===");
        System.out.println("Démarrage du serveur sur le port 5555...");
        
        ServeurJeu serveur = new ServeurJeu(5555);
        serveur.demarrer();
    }
}