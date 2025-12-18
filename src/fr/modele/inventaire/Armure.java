package fr.modele.inventaire;

public class Armure implements Objet {
    private final String nom;
    private final String description;
    private int bonusDefense;
    private final int valeur;
    
    public Armure(String nom, String description, int bonusDefense, int valeur) {
        this.nom = nom;
        this.description = description;
        this.bonusDefense = bonusDefense;
        this.valeur = valeur;
    }
    
    @Override
    public String getNom() { return nom; }
    
    @Override
    public String getDescription() { return description; }
    
    @Override
    public int getValeur() { return valeur; }
    
    public int getBonusDefense() { return bonusDefense; }
    
    public void ameliorer(int bonus) {
        bonusDefense += bonus;
        System.out.println("✨ " + nom + " a été améliorée ! Bonus de défense : +" + bonusDefense);
    }
}