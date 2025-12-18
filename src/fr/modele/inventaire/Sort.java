package fr.modele.inventaire;

public class Sort implements Objet {
    private final String nom;
    private final String description;
    private final int degats;
    private final int coutMana;
    private final int valeur;
    
    public Sort(String nom, String description, int degats, int coutMana, int valeur) {
        this.nom = nom;
        this.description = description;
        this.degats = degats;
        this.coutMana = coutMana;
        this.valeur = valeur;
    }
    
    @Override
    public String getNom() { return nom; }
    
    @Override
    public String getDescription() { return description; }
    
    @Override
    public int getValeur() { return valeur; }
    
    public int getDegats() { return degats; }
    
    public int getCoutMana() { return coutMana; }
}