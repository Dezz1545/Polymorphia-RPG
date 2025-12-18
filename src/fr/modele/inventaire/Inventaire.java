package fr.modele.inventaire;

import java.util.ArrayList;
import java.util.List;

public class Inventaire {
    private final List<Objet> objets;
    
    public Inventaire() {
        objets = new ArrayList<>();
    }
    
    public void ajouterObjet(Objet objet) {
        objets.add(objet);
        System.out.println(objet.getNom() + " ajouté à l'inventaire.");
    }
    
    public void retirerObjet(Objet objet) {
        objets.remove(objet);
    }
    
    public void afficher() {
        if (objets.isEmpty()) {
            System.out.println("\n📭 Inventaire vide.");
            return;
        }
        
        System.out.println("\n === INVENTAIRE ===");
        for (int i = 0; i < objets.size(); i++) {
            Objet objet = objets.get(i);
            System.out.printf("%d. %-20s | %-30s | Valeur: %d intcoins\n", 
                (i+1), objet.getNom(), objet.getDescription(), objet.getValeur());
        }
        System.out.println("=====================");
    }
    
    public List<Objet> getObjets() {
        return new ArrayList<>(objets);
    }
    
    public Arme getArme(int index) {
        if (index >= 0 && index < objets.size()) {
            Objet objet = objets.get(index);
            if (objet instanceof Arme a) {
                return a;
            }
        }
        return null;
    }
    
    public Armure getArmure(int index) {
        if (index >= 0 && index < objets.size()) {
            Objet objet = objets.get(index);
            if (objet instanceof Armure a) {
                return a;
            }
        }
        return null;
    }
    
    public Potion getPotion(int index) {
        if (index >= 0 && index < objets.size()) {
            Objet objet = objets.get(index);
            if (objet instanceof Potion p) {
                return p;
            }
        }
        return null;
    }
    
    public Materia getMateria(int index) {
        if (index >= 0 && index < objets.size()) {
            Objet objet = objets.get(index);
            if (objet instanceof Materia m) {
                return m;
            }
        }
        return null;
    }
    
    public int compterPotions() {
        int compte = 0;
        for (Objet objet : objets) {
            if (objet instanceof Potion) {
                compte++;
            }
        }
        return compte;
    }
}