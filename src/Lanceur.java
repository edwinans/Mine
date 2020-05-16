/**
 * TP2:Lanceur.java
 * 
 * Arwin/Edwin Ansari Tabrizi
 */

import java.util.Scanner;
public class Lanceur {
    public static void main (String[] args){
        Joueur j1=new Joueur();
        j1.demandeNom();
        Scanner input=new Scanner(System.in);
        System.out.println("jouer? (0=NON , 1=OUI) ");
        int vj=input.nextInt();
        if(vj==0) return; //si le jouer ne veux pas jouer alors fin du programme!
        
        Jeu jeu=new Jeu(j1); //sinon on commence une partie
        jeu.jouer();
    }
}
