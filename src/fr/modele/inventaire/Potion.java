package fr.modele.inventaire;

import fr.modele.personnage.Heros;

public class Potion implements Objet {
    private final String nom;
    private final String description;
    private final int montantSoin;
    private final int valeur;
    
    public Potion(String nom, String description, int montantSoin, int valeur) {
        this.nom = nom;
        this.description = description;
        this.montantSoin = montantSoin;
        this.valeur = valeur;
    }
    
    @Override
    public String getNom() { return nom; }
    
    @Override
    public String getDescription() { return description; }
    
    @Override
    public int getValeur() { return valeur; }
    
    public int getMontantSoin() { return montantSoin; }
    
    public void utiliser(Heros heros) {
        heros.soigner(montantSoin);
    }
}