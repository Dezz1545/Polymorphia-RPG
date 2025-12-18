package fr.modele.personnage;

public abstract class Personnage {
    protected String nom;
    protected int pointsDeVie;
    protected int pointsDeVieMax;
    protected int attaque;
    protected int defense;
    protected int niveau;
    protected boolean enVie;
    
    public Personnage(String nom, int pointsDeVie, int attaque, int defense) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.pointsDeVieMax = pointsDeVie;
        this.attaque = attaque;
        this.defense = defense;
        this.niveau = 1;
        this.enVie = true;
    }
    
    public abstract void attaquer(Personnage cible);
    public abstract void subirDegats(int degats);
    
    public void soigner(int montant) {
        pointsDeVie = Math.min(pointsDeVieMax, pointsDeVie + montant);
        System.out.println(nom + " récupère " + montant + " points de vie.");
    }
    
    public void monterNiveau() {
        niveau++;
        pointsDeVieMax += 20;
        pointsDeVie = pointsDeVieMax;
        attaque += 5;
        defense += 3;
        System.out.println(nom + " monte au niveau " + niveau + " !");
        afficherStatistiques();
    }
    
    public void afficherStatistiques() {
        System.out.println("\n=== STATISTIQUES DE " + nom.toUpperCase() + " ===");
        System.out.println("Niveau : " + niveau);
        System.out.println("PV : " + pointsDeVie + "/" + pointsDeVieMax);
        System.out.println("Attaque : " + attaque);
        System.out.println("Défense : " + defense);
    }
    
    // Getters
    public String getNom() { return nom; }
    public int getPointsDeVie() { return pointsDeVie; }
    public int getPointsDeVieMax() { return pointsDeVieMax; }
    public int getAttaque() { return attaque; }
    public int getDefense() { return defense; }
    public int getNiveau() { return niveau; }
    public boolean estEnVie() { return enVie; }
}