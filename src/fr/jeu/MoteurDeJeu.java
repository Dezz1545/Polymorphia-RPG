package fr.jeu;

import fr.modele.inventaire.*;
import fr.modele.monde.Bestiaire;
import fr.modele.monde.Marchand;
import fr.modele.personnage.Heros;
import fr.modele.personnage.Monstre;
import fr.ui.InterfaceConsole;
import java.util.Random;
import java.util.Scanner;

public class MoteurDeJeu {
    private Heros heros;
    private final Marchand marchand;
    private final Scanner scanner;
    private final Random random;
    private boolean jeuEnCours;
    
    public MoteurDeJeu() {
        scanner = new Scanner(System.in);
        random = new Random();
        marchand = new Marchand();
        jeuEnCours = true;
    }
    
    public void demarrer() {
        try (scanner) {
            InterfaceConsole.afficherTitre();
            System.out.print("\nEntrez le nom de votre héros : ");
            String nomHeros = scanner.nextLine();
            
            heros = new Heros(nomHeros);
            
            System.out.println("\n Bienvenue, " + nomHeros + " !");
            System.out.println("Vous débutez votre aventure avec " + heros.getIntcoins() + " intcoins.");
            System.out.println("Rendez-vous chez le marchand pour vous équiper avant de partir à l'aventure !");
            
            while (jeuEnCours && heros.estEnVie()) {
                afficherMenuPrincipal();
            }
            
            if (!heros.estEnVie()) {
                System.out.println("\n GAME OVER ");
                System.out.println("Votre héros " + heros.getNom() + " a été vaincu...");
            }
            
            System.out.println("\nMerci d'avoir joué à Polymorphia !");
        }
    }
    
    private void afficherMenuPrincipal() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("            MENU PRINCIPAL - " + heros.getNom());
        System.out.println("═".repeat(50));
        System.out.println("1. Voir l'inventaire");
        System.out.println("2.  S'équiper d'un objet");
        System.out.println("3. Utiliser de la Matéria");
        System.out.println("4. Visiter le marchand");
        System.out.println("5.  Partir à l'aventure");
        System.out.println("6. Voir les statistiques");
        System.out.println("7. Mode Multijoueur (PvP)");
        System.out.println("8. Quitter le jeu");
        System.out.print("\nVotre choix : ");
        
        try {
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch(choix) {
                case 1 -> heros.getInventaire().afficher();
                case 2 -> equiperObjet();
                case 3 -> utiliserMateria();
                case 4 -> visiterMarchand();
                case 5 -> partirAventure();
                case 6 -> afficherStatistiques();
                case 7 -> demarrerPvP();
                case 8 -> jeuEnCours = false;
                default -> System.out.println("  Choix invalide !");
            }
        } catch (Exception e) {
            System.out.println("  Erreur : Veuillez entrer un nombre valide.");
            scanner.nextLine(); // Nettoyer le buffer
        }
    }
    
    private void equiperObjet() {
        var inventaire = heros.getInventaire();
        var objets = inventaire.getObjets();
        
        if (objets.isEmpty()) {
            System.out.println("\n Votre inventaire est vide !");
            return;
        }
        
        System.out.println("\n  === ÉQUIPEMENT ===");
        for (int i = 0; i < objets.size(); i++) {
            System.out.println((i+1) + ". " + objets.get(i).getNom());
        }
        System.out.println("0. Annuler");
        System.out.print("Choisissez un objet à équiper : ");
        
        try {
            int choix = scanner.nextInt();
            scanner.nextLine();
            
            if (choix == 0) return;
            if (choix < 1 || choix > objets.size()) {
                System.out.println("  Choix invalide !");
                return;
            }
            
            Objet objetSelectionne = objets.get(choix - 1);
            
            switch (objetSelectionne) {
                case Arme a -> heros.equiperArme(a);
                case Armure ar -> heros.equiperArmure(ar);
                default -> System.out.println("  Cet objet ne peut pas être équipé !");
            }
        } catch (Exception e) {
            System.out.println("  Erreur de saisie.");
        }
    }
    
    private void utiliserMateria() {
        var inventaire = heros.getInventaire();
        var objets = inventaire.getObjets();
        
        // Chercher de la matéria
        Materia materia = null;
        
        for (var o : objets) {
            if (o instanceof Materia m) {
                materia = m;
                break;
            }
        }
        
        if (materia == null) {
            System.out.println("\n  Vous n'avez pas de Matéria !");
            return;
        }
        
        System.out.println("\n === UTILISATION DE MATÉRIA ===");
        System.out.println("Matéria disponible : " + materia.getNom());
        System.out.println("Valeur d'amélioration : +" + materia.getValeurAmelioration());
        System.out.println("\nQue voulez-vous améliorer ?");
        System.out.println("1. Arme équipée");
        System.out.println("2. Armure équipée");
        System.out.println("0. Annuler");
        System.out.print("Choix : ");
        
        try {
            int choix = scanner.nextInt();
            scanner.nextLine();
            
            if (choix == 0) return;
            
            switch(choix) {
                case 1 -> {
                    if (heros.getArmeEquipee() != null) {
                        heros.getArmeEquipee().ameliorer(materia.getValeurAmelioration());
                        inventaire.retirerObjet(materia);
                    } else {
                        System.out.println("  Vous n'avez pas d'arme équipée !");
                    }
                }
                case 2 -> {
                    if (heros.getArmureEquipee() != null) {
                        heros.getArmureEquipee().ameliorer(materia.getValeurAmelioration());
                        inventaire.retirerObjet(materia);
                    } else {
                        System.out.println("  Vous n'avez pas d'armure équipée !");
                    }
                }
                default -> System.out.println("  Choix invalide !");
            }
        } catch (Exception e) {
            System.out.println("  Erreur de saisie.");
        }
    }
    
    private void visiterMarchand() {
        boolean shopping = true;
        
        while (shopping) {
            System.out.println("\n Intcoins disponibles : " + heros.getIntcoins());
            marchand.afficherObjets();
            
            System.out.print("\nVotre choix : ");
            
            try {
                int choix = scanner.nextInt();
                scanner.nextLine();
                
                if (choix == marchand.getObjetsAVendre().size() + 1) {
                    shopping = false;
                    System.out.println("\n Au revoir ! Revenez nous voir !");
                } else {
                    marchand.vendreObjet(heros, choix);
                }
            } catch (Exception e) {
                System.out.println("  Erreur : Veuillez entrer un nombre valide.");
                scanner.nextLine();
            }
        }
    }
    
    private void partirAventure() {
        System.out.println("\n Vous partez à l'aventure dans les forêts de Polymorphia...");
        InterfaceConsole.delai(1500);
        
        if (Bestiaire.rencontreAleatoire()) {
            Monstre monstre = Bestiaire.rencontrerMonstreParNiveau(heros.getNiveau());
            System.out.println("\n  Un " + monstre.getNom() + " apparaît !");
            
            SystemeCombat combat = new SystemeCombat(heros, monstre);
            boolean victoire = combat.demarrerCombat();
            
            if (victoire) {
                System.out.println("\n" + "".repeat(10));
                System.out.println("          VICTOIRE !");
                System.out.println("*".repeat(10));
                
                // Récompenses
                int recompense = monstre.getRecompenseIntcoins();
                heros.ajouterIntcoins(recompense);
                
                // Chance de trouver un objet rare
                if (random.nextDouble() < 0.3) {
                    System.out.println("\n Le monstre a laissé tomber un objet rare !");
                    Objet[] objetsRares = {
                        new Potion("Potion Magique", "Soigne 75 PV", 75, 50),
                        new Materia("Matéria Rare", "Améliore de +15", 15, 100),
                        new Arme("Dague Enchantée", "Lame magique", 18, 120)
                    };
                    Objet objetTrouve = objetsRares[random.nextInt(objetsRares.length)];
                    heros.getInventaire().ajouterObjet(objetTrouve);
                }
                
                // Monter de niveau
                if (random.nextDouble() < 0.5) {
                    heros.monterNiveau();
                }
                
            }
        } else {
            System.out.println("\n🌿 La route est paisible pour le moment...");
            System.out.println("Vous continuez votre chemin.");
            
            // Petite chance de trouver quelque chose
            if (random.nextDouble() < 0.2) {
                System.out.println("\n💰 Vous trouvez un petit sac d'intcoins par terre !");
                heros.ajouterIntcoins(random.nextInt(20) + 5);
            }
        }
    }
    
    private void afficherStatistiques() {
        heros.afficherStatistiquesCompletes();
    }
    
    private void demarrerPvP() {
        System.out.println("\n🌐 === MODE MULTIJOUEUR PvP ===");
        System.out.println("Cette fonctionnalité sera disponible dans la prochaine mise à jour !");
        System.out.println("En attendant, continuez votre aventure en solo.");
        
        // Pour l'instant, retour à l'aventure
        partirAventure();
    }
}