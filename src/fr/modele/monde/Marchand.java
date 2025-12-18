package fr.modele.monde;

import fr.modele.inventaire.*;
import fr.modele.personnage.Heros;
import java.util.ArrayList;
import java.util.List;

public class Marchand {
    private final List<Objet> objetsAVendre;
    
    public Marchand() {
        objetsAVendre = new ArrayList<>();
        initialiserObjets();
    }
    
    private void initialiserObjets() {
        // Armes
        objetsAVendre.add(new Arme("Épée Rouillée", "Une vieille épée usée", 5, 20));
        objetsAVendre.add(new Arme("Hache de Bûcheron", "Hache robuste pour le bois", 8, 35));
        objetsAVendre.add(new Arme("Arc Simple", "Arc de chasse basique", 6, 30));
        objetsAVendre.add(new Arme("Épée Longue", "Épée bien équilibrée", 12, 50));
        objetsAVendre.add(new Arme("Marteau de Guerre", "Lourd et puissant", 15, 70));
        
        // Armures
        objetsAVendre.add(new Armure("Tunique de Cuir", "Armure légère en cuir", 3, 25));
        objetsAVendre.add(new Armure("Cotte de Mailles", "Armure moyenne flexible", 6, 45));
        objetsAVendre.add(new Armure("Armure de Plates", "Armure lourde complète", 10, 70));
        objetsAVendre.add(new Armure("Bouclier en Bois", "Protection basique", 4, 30));
        
        // Potions
        objetsAVendre.add(new Potion("Potion de Soin", "Soigne 20 points de vie", 20, 15));
        objetsAVendre.add(new Potion("Potion Majeure", "Soigne 50 points de vie", 50, 35));
        objetsAVendre.add(new Potion("Élixir de Vie", "Soigne 100 points de vie", 100, 80));
        
        // Matéria
        objetsAVendre.add(new Materia("Matéria d'Attaque", "Améliore les armes de +5", 5, 40));
        objetsAVendre.add(new Materia("Matéria de Défense", "Améliore les armures de +5", 5, 40));
        objetsAVendre.add(new Materia("Matéria d'Âme", "Améliore de +10", 10, 80));
    }
    
    public void afficherObjets() {
        System.out.println("\n🏪 === MARCHAND DE POLYMORPHIA ===");
        System.out.println("Que souhaitez-vous acheter ?\n");
        System.out.printf("%-3s %-20s %-30s %-10s\n", "No", "Nom", "Description", "Prix");
        System.out.println("----------------------------------------------------------------");
        
        for (int i = 0; i < objetsAVendre.size(); i++) {
            Objet objet = objetsAVendre.get(i);
            System.out.printf("%-3d %-20s %-30s %-10d\n", 
                (i+1), 
                objet.getNom(), 
                objet.getDescription().length() > 30 ? 
                    objet.getDescription().substring(0, 27) + "..." : objet.getDescription(),
                objet.getValeur());
        }
        System.out.println("\n" + (objetsAVendre.size() + 1) + ". Quitter la boutique");
    }
    
    public boolean vendreObjet(Heros heros, int choix) {
        if (choix < 1 || choix > objetsAVendre.size()) {
            return false;
        }
        
        Objet objet = objetsAVendre.get(choix - 1);
        
        if (heros.depenserIntcoins(objet.getValeur())) {
            heros.getInventaire().ajouterObjet(objet);
            System.out.println(" Achat réussi ! Vous avez acheté : " + objet.getNom());
            return true;
        }
        return false;
    }
    
    public List<Objet> getObjetsAVendre() {
        return new ArrayList<>(objetsAVendre);
    }
}