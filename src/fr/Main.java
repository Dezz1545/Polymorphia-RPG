package fr;

import fr.jeu.MoteurDeJeu;
import fr.serveur.ServeurJeu;
import fr.ui.InterfaceConsole;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        InterfaceConsole.afficherTitre();
        
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║    BIENVENUE DANS POLYMORPHIA!       ║");
        System.out.println("║      Monde Héroïque Fantastique      ║");
        System.out.println("╚══════════════════════════════════════╝");
        
        System.out.println("\nChoisissez un mode de jeu :");
        System.out.println("1. Mode Solo (Aventure)");
        System.out.println("2. Mode Multijoueur (PvP - Client)");
        System.out.println("3. Héberger un serveur PvP");
        System.out.println("4. Quitter");
        System.out.print("\nVotre choix : ");
        
        try {
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch(choix) {
                case 1 -> demarrerModeSolo();
                case 2 -> demarrerModeClient();
                case 3 -> demarrerServeur(scanner);
                case 4 -> System.out.println("\nMerci d'avoir joué à Polymorphia !");
                default -> System.out.println("Choix invalide !");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private static void demarrerModeSolo() {
        MoteurDeJeu moteur = new MoteurDeJeu();
        moteur.demarrer();
    }
    
    private static void demarrerModeClient() {
        System.out.println("\nMode Multijoueur Client");
        System.out.println("Cette fonctionnalité sera implémentée dans la version 2.0");
        System.out.println("Retour au menu principal...\n");
        
        // Pour le moment, on retourne au mode solo
        demarrerModeSolo();
    }
    
    private static void demarrerServeur(Scanner scanner) {
        System.out.println("\n=== DÉMARRAGE DU SERVEUR ===");
        System.out.print("Port d'écoute (défaut : 5555) : ");
        
        int port = 5555;
        
        try {
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                port = Integer.parseInt(input);
            }
            
            ServeurJeu serveur = new ServeurJeu(port);
            serveur.demarrer();
        } catch (NumberFormatException e) {
            System.out.println("Erreur lors du démarrage du serveur : port invalide.");
        }
    }
}