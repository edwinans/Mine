/**
 * TP2:Joueur.java
 * 
 * Arwin/Edwin Ansari Tabrizi
 */

import java.util.*;

public class Joueur {
    private String nom;
    private Scanner in; // Pensez à faire tous vos scans depuis cette classe. [ok]
    
    public Joueur(){
        //Par défaut, le nom du joueur est "Anonyme". 
        nom = "Anonyme";
	}
	
	public void setNom(String s) {
    	this.nom=s;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	//pour demander le nom du joueur
    public void demandeNom() {
    	in=new Scanner(System.in);
    	System.out.println("veuilez saisir votre nom ?");
    	this.setNom(in.nextLine());
    }
	
    public int demandeHauteur() {
    	in=new Scanner(System.in);
    	System.out.println("quelle est l'hauteur souhaite?");
    	int res=in.nextInt();
    	if(res<=0) return demandeHauteur();
    	return res;
    }
    
    public int demandeLargeur() {
    	in=new Scanner(System.in);
    	System.out.println("quelle est la largeur souhaite?");
    	int res=in.nextInt();
    	if(res<=0) return demandeLargeur();
    	return res;
    }
	
	public int demandeMines(){
		in=new Scanner(System.in);
		System.out.println("quelle est le nombre de mines souhaite?");
		int res=in.nextInt();
		if(res<=0) return demandeMines();
		return res;
		
	}
    public int demandeAction() {
    	in=new Scanner(System.in);
		System.out.println("votre demande d'action? (r=revele et d=drapeau)");
		String s=in.next();
		if(s.equals("etoile")){
			//c'est pour tricher!!
			return -1;
		}
    	if(s.equals("r"))
    		return 0;
    	else if(s.charAt(0)=='d') return 1;
    	else
    		return demandeAction();
    	
	}
	
	public int demandeX(){
		in=new Scanner(System.in);
		System.out.println("veuillez entrer le numero de ligne?");
		int l=in.nextInt();
		if(l<=0) return demandeX();
		return l;
	}

	public int demandeY(){
		in=new Scanner(System.in);
		System.out.println("veuillez entrer le numero de colonne?");
		int c=in.nextInt();
		if(c<=0) return demandeY();
		return c;
	}

	public boolean demandeRestart(){
		in=new Scanner(System.in);
		System.out.println("est ce que vous voulez rejouer? (Y/N)");
		String s=in.next();
		if(s.equals("Y")) return true;
		else if(s.equals("N")) return false;
		else return demandeRestart(); 
	} 


}
