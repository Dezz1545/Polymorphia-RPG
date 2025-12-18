package fr.ui;

public class InterfaceConsole {
    
    public static void afficherTitre() {
        System.out.println("\n" + "█".repeat(60));
        System.out.println("██                                                            ██");
        System.out.println("██   ██████╗  ██████╗ ██╗     ██╗   ██╗███╗   ███╗           ██");
        System.out.println("██   ██╔══██╗██╔═══██╗██║     ██║   ██║████╗ ████║           ██");
        System.out.println("██   ██████╔╝██║   ██║██║     ██║   ██║██╔████╔██║  POLY-    ██");
        System.out.println("██   ██╔═══╝ ██║   ██║██║     ██║   ██║██║╚██╔╝██║  MORPHIA  ██");
        System.out.println("██   ██║     ╚██████╔╝███████╗╚██████╔╝██║ ╚═╝ ██║           ██");
        System.out.println("██   ╚═╝      ╚═════╝ ╚══════╝ ╚═════╝ ╚═╝     ╚═╝           ██");
        System.out.println("██   RPG Héroïque Fantastique - Chasseur de Monstres         ██");
        System.out.println("██                                                            ██");
        System.out.println("█".repeat(60));
    }
    
    public static void effacerEcran() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void afficherAnimationCombat() {
        String[] frames = {
            "⚔️  >>>>------>  🐉",
            "⚔️   >>>>------> 🐉",
            "⚔️    >>>>------>🐉",
            "⚔️     >>>>------🐉>",
            "⚔️      >>>>-----🐉->",
            "⚔️       >>>>----🐉-->",
            "⚔️        >>>>---🐉--->",
            "⚔️         >>>>--🐉---->",
            "⚔️          >>>>-🐉----->",
            "⚔️           >>>>🐉------>"
        };
        
        for (String frame : frames) {
            System.out.print("\r" + frame);
            delai(150);
        }
        System.out.println();
    }
    
    public static void delai(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}