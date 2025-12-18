package fr.modele.personnage;

import java.util.Random;

public class Monstre extends Personnage {
    private final int recompenseIntcoins;
    private static final Random rand = new Random();
    
    public Monstre(String nom, int pointsDeVie, int attaque, int defense, int recompense) {
        super(nom, pointsDeVie, attaque, defense);
        this.recompenseIntcoins = recompense;
    }
    
    @Override
    public void attaquer(Personnage cible) {
        int degats = Math.max(1, attaque - cible.getDefense());
        cible.subirDegats(degats);
        
        System.out.println("👹 " + nom + " attaque " + cible.getNom() + 
                         " et inflige " + degats + " points de dégâts !");
    }
    
    @Override
    public void subirDegats(int degats) {
        pointsDeVie -= degats;
        
        if (pointsDeVie <= 0) {
            pointsDeVie = 0;
            enVie = false;
            System.out.println("🎉 " + nom + " a été vaincu !");
        }
    }
    
    public int getRecompenseIntcoins() { return recompenseIntcoins; }
    
    public static Monstre genererMonstreAleatoire() {
        String[] typesMonstres = {
            "Gobelin", "Loup Garou", "Zombie", "Squelette", 
            "Orc", "Araignée Géante", "Dragonnet", "Vampire"
        };
        
        String nom = typesMonstres[rand.nextInt(typesMonstres.length)];
        
        return switch (nom) {
            case "Gobelin" -> new Monstre(nom, 30, 8, 2, 10);
            case "Loup Garou" -> new Monstre(nom, 40, 12, 4, 15);
            case "Zombie" -> new Monstre(nom, 35, 6, 1, 8);
            case "Squelette" -> new Monstre(nom, 25, 9, 3, 12);
            case "Orc" -> new Monstre(nom, 50, 14, 6, 20);
            case "Araignée Géante" -> new Monstre(nom, 45, 10, 5, 18);
            case "Dragonnet" -> new Monstre(nom, 70, 16, 8, 30);
            case "Vampire" -> new Monstre(nom, 60, 18, 7, 25);
            default -> new Monstre(nom, 30, 8, 2, 10);
        };
    }
}