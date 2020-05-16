/**
 * TP2:Jeu.java
 * 
 * Arwin/Edwin Ansari Tabrizi
 */

public class Jeu {
    private Joueur leJoueur;
    private Plateau lePlateau;

    private static int np=0;

    public Jeu(Joueur j){
        this.leJoueur=j;
    }

    public Jeu(Joueur j , Plateau p){
        this(j);
        this.lePlateau=p;
    }


    public void jouer(){
        System.out.println("********************************");
        System.out.println("Bonjour "+ leJoueur.getNom() + " vous venez de commencer votre parie numero : " + ++np);

        int h=leJoueur.demandeHauteur();
        int l=leJoueur.demandeLargeur();
        int n=leJoueur.demandeMines();

        lePlateau=new Plateau(h, l, n);
        
        while(!lePlateau.jeuPerdu()){
            lePlateau.affichage();

            System.out.println();        
            int act=leJoueur.demandeAction();
            if(act==-1){
                //on triche avec le mot cle "etoile" !
                lePlateau.affichageHint();
                //on redmande une action:
                act=leJoueur.demandeAction();
            }
            int x=leJoueur.demandeX();
            int y=leJoueur.demandeY();
            if(act==0)
                lePlateau.revelerCase(x, y);
            else if(act==1)
                lePlateau.drapeauCase(x, y);
   
            if(lePlateau.jeuGagne()){
                System.out.println();
                System.out.println("** Bravo!! "+leJoueur.getNom()+ " vous avez gagner");
                break;
            }
            else if(lePlateau.jeuPerdu()){
                System.out.println();
                System.out.println("** BOOOOM Perdu "+ leJoueur.getNom() + " :(");
                System.out.print("le Bon resultat: (@=mine) ");
                lePlateau.affichageHint();
                break;
            }

        }

        boolean rej=leJoueur.demandeRestart();
        if(rej) this.jouer();
        else{
            System.out.println("*****TERMINE*****");
            return;
        }
    }
}
