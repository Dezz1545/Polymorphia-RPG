package fr.modele.personnage;

import fr.modele.inventaire.*;

public class Heros extends Personnage {
    private final Inventaire inventaire;
    private Arme armeEquipee;
    private Armure armureEquipee;
    private int intcoins;
    
    public Heros(String nom) {
        super(nom, 100, 10, 5);
        this.inventaire = new Inventaire();
        this.intcoins = 50; // Argent de départ
    }
    
    @Override
    public void attaquer(Personnage cible) {
        int attaqueTotale = attaque;
        if (armeEquipee != null) {
            attaqueTotale += armeEquipee.getBonusAttaque();
        }
        
        int degats = Math.max(1, attaqueTotale - cible.getDefense());
        cible.subirDegats(degats);
        
        System.out.println("\n⚔️  " + nom + " attaque " + cible.getNom() + 
                         " et inflige " + degats + " points de dégâts !");
    }
    
    @Override
    public void subirDegats(int degats) {
        int degatsReels = Math.max(1, degats - defense);
        if (armureEquipee != null) {
            degatsReels = Math.max(1, degatsReels - armureEquipee.getBonusDefense());
        }
        
        pointsDeVie -= degatsReels;
        System.out.println("💥 " + nom + " subit " + degatsReels + " points de dégâts !");
        
        if (pointsDeVie <= 0) {
            pointsDeVie = 0;
            enVie = false;
            System.out.println("\n💀 " + nom + " a été vaincu !");
        }
    }
    
    public void equiperArme(Arme arme) {
        this.armeEquipee = arme;
        System.out.println("🔪 " + nom + " équipe " + arme.getNom());
    }
    
    public void equiperArmure(Armure armure) {
        this.armureEquipee = armure;
        System.out.println("🛡️  " + nom + " équipe " + armure.getNom());
    }
    
    public void utiliserPotion(Potion potion) {
        potion.utiliser(this);
        inventaire.retirerObjet(potion);
    }
    
    public void ajouterIntcoins(int montant) {
        intcoins += montant;
        System.out.println("💰 +" + montant + " intcoins ! Total : " + intcoins);
    }
    
    public boolean depenserIntcoins(int montant) {
        if (intcoins >= montant) {
            intcoins -= montant;
            return true;
        }
        System.out.println("  Vous n'avez pas assez d'intcoins !");
        return false;
    }
    
    // Getters
    public Inventaire getInventaire() { return inventaire; }
    public Arme getArmeEquipee() { return armeEquipee; }
    public Armure getArmureEquipee() { return armureEquipee; }
    public int getIntcoins() { return intcoins; }
    
    public void afficherStatistiquesCompletes() {
        afficherStatistiques();
        System.out.println("Intcoins : " + intcoins);
        System.out.println("Arme équipée : " + 
            (armeEquipee != null ? armeEquipee.getNom() + " (+" + armeEquipee.getBonusAttaque() + " att)" : "Aucune"));
        System.out.println("Armure équipée : " + 
            (armureEquipee != null ? armureEquipee.getNom() + " (+" + armureEquipee.getBonusDefense() + " def)" : "Aucune"));
    }
}