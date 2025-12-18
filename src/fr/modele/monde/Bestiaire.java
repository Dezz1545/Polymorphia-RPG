package fr.modele.monde;

import fr.modele.personnage.Monstre;
import java.util.Random;

public class Bestiaire {
    private static final Random rand = new Random();
    
    public static Monstre rencontrerMonstre() {
        return Monstre.genererMonstreAleatoire();
    }
    
    public static Monstre rencontrerBoss() {
        return new Monstre("DRAGON ANCIEN", 150, 25, 15, 100);
    }
    
    public static boolean rencontreAleatoire() {
        // 40% de chance de rencontrer un monstre
        return rand.nextDouble() < 0.4;
    }
    
    public static Monstre rencontrerMonstreParNiveau(int niveauJoueur) {
        Monstre monstre = rencontrerMonstre();
        
        // Ajuster le monstre selon le niveau du joueur
        int multiplicateur = 1 + (niveauJoueur / 3);
        Monstre base = monstre; // garder la référence initiale
        monstre = new Monstre(
            base.getNom() + " (Niv " + niveauJoueur + ")",
            base.getPointsDeVie() * multiplicateur,
            base.getAttaque() + (niveauJoueur * 2),
            base.getDefense() + (niveauJoueur),
            base.getRecompenseIntcoins() * multiplicateur
        );
        
        return monstre;
    }
}