package fr.modele.inventaire;

public class Materia implements Objet {
    private final String nom;
    private final String description;
    private final int valeurAmelioration;
    private final int valeur;
    
    public Materia(String nom, String description, int valeurAmelioration, int valeur) {
        this.nom = nom;
        this.description = description;
        this.valeurAmelioration = valeurAmelioration;
        this.valeur = valeur;
    }
    
    @Override
    public String getNom() { return nom; }
    
    @Override
    public String getDescription() { return description; }
    
    @Override
    public int getValeur() { return valeur; }
    
    public int getValeurAmelioration() { return valeurAmelioration; }
}