package fr.jeu;

import fr.modele.inventaire.Potion;
import fr.modele.personnage.Heros;
import fr.modele.personnage.Monstre;
import fr.ui.InterfaceConsole;
import java.util.Scanner;

public class SystemeCombat {
    private final Heros heros;
    private final Monstre monstre;
    private final Scanner scanner;
    
    public SystemeCombat(Heros heros, Monstre monstre) {
        this.heros = heros;
        this.monstre = monstre;
        this.scanner = new Scanner(System.in);
    }
    
    public boolean demarrerCombat() {
        System.out.println("\n" + "⚔️".repeat(25));
        System.out.println("           COMBAT COMMENCE !");
        System.out.println("       " + heros.getNom() + " vs " + monstre.getNom());
        System.out.println("⚔️".repeat(25));
        
        InterfaceConsole.delai(1000);
        
        while (heros.estEnVie() && monstre.estEnVie()) {
            afficherEtatCombat();
            tourJoueur();
            
            if (monstre.estEnVie()) {
                tourMonstre();
            }
            
            InterfaceConsole.delai(500);
        }
        
        return heros.estEnVie();
    }
    
    private void afficherEtatCombat() {
        System.out.println("\n" + "─".repeat(50));
        System.out.printf("👤 %-15s : %3d/%3d PV\n", 
            heros.getNom(), heros.getPointsDeVie(), heros.getPointsDeVieMax());
        System.out.printf("👹 %-15s : %3d PV\n", 
            monstre.getNom(), monstre.getPointsDeVie());
        System.out.println("─".repeat(50));
    }
    
    private void tourJoueur() {
        System.out.println("\n🎮 --- VOTRE TOUR ---");
        System.out.println("1. ⚔️  Attaquer");
        System.out.println("2. 🧪 Utiliser une potion");
        System.out.println("3. 🏃 Tenter de fuir (50% de chance)");
        System.out.print("Choix : ");
        
        try {
            int choix = scanner.nextInt();
            
            switch(choix) {
                case 1 -> heros.attaquer(monstre);
                case 2 -> utiliserPotion();
                case 3 -> {
                    if (tenterFuite()) {
                        System.out.println("\n🏃💨 Vous avez réussi à fuir le combat !");
                    } else {
                        System.out.println("\n  Fuite échouée ! Le monstre vous bloque le chemin.");
                    }
                }
                default -> System.out.println("  Action invalide ! Vous perdez votre tour.");
            }
        } catch (Exception e) {
            System.out.println("  Erreur de saisie. Vous perdez votre tour.");
            scanner.nextLine();
        }
    }
    
    private void utiliserPotion() {
        var inventaire = heros.getInventaire();
        int nombrePotions = inventaire.compterPotions();
        
        if (nombrePotions == 0) {
            System.out.println("  Vous n'avez pas de potions !");
            tourJoueur(); // Revenir au choix
            return;
        }
        
        System.out.println("\n🧪 --- POTIONS DISPONIBLES ---");
        var objets = inventaire.getObjets();
        int compteur = 1;
        
        for (var o : objets) {
            if (o instanceof Potion p) {
                System.out.printf("%d. %s (+%d PV)\n", compteur, p.getNom(), p.getMontantSoin());
                compteur++;
            }
        }
        
        System.out.print("Choix de potion (0 pour annuler) : ");
        
        try {
            int choix = scanner.nextInt();
            
            if (choix == 0) {
                tourJoueur();
                return;
            }
            
            // Trouver la potion correspondante
            compteur = 1;
            for (var o : objets) {
                if (o instanceof Potion p) {
                    if (compteur == choix) {
                        heros.utiliserPotion(p);
                        return;
                    }
                    compteur++;
                }
            }
            
            System.out.println("  Choix invalide ! Vous perdez votre tour.");
        } catch (Exception e) {
            System.out.println("  Erreur de saisie. Vous perdez votre tour.");
            scanner.nextLine();
        }
    }
    
    private boolean tenterFuite() {
        return Math.random() < 0.5;
    }
    
    private void tourMonstre() {
        System.out.println("\n👹 --- TOUR DU MONSTRE ---");
        InterfaceConsole.delai(1000);
        monstre.attaquer(heros);
    }
}