package fr.modele.inventaire;

public class Arme implements Objet {
    private final String nom;
    private final String description;
    private int bonusAttaque;
    private final int valeur;
    
    public Arme(String nom, String description, int bonusAttaque, int valeur) {
        this.nom = nom;
        this.description = description;
        this.bonusAttaque = bonusAttaque;
        this.valeur = valeur;
    }
    
    @Override
    public String getNom() { return nom; }
    
    @Override
    public String getDescription() { return description; }
    
    @Override
    public int getValeur() { return valeur; }
    
    public int getBonusAttaque() { return bonusAttaque; }
    
    public void ameliorer(int bonus) {
        bonusAttaque += bonus;
        System.out.println("✨ " + nom + " a été améliorée ! Bonus d'attaque : +" + bonusAttaque);
    }
}