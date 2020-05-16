/**
 * TP2:Plateau.java
 * 
 * Arwin/Edwin Ansari Tabrizi
 */


public class Plateau {
    public final int hauteur, largeur, nbMines;
    
    private boolean[][] mines;
        /*indique où sont les mines sur le plateau*/
    private int[][] etats;
        /*indique dans quel état est chaque case
          (cachée, révélée, avec/sans drapeau)
          0=cachee , 1=revelee , 2=drapeau
          */
    private int[][] adja;
        /*indique le nombre de mines adjacentes 
          à chaque case*/

	//constructeur
    public Plateau(int h,int l, int n) {
    	hauteur=h; largeur=l ; nbMines=n;
    	mines=new boolean[h+2][l+2];
    	etats=new int[h+2][l+2];
    	adja=new int[h+2][l+2];
    	this.ajouteMinesAlea();
    	this.calculeAdjacence();
    }
   
    private void ajouteMinesAlea() {
    	if(nbMines> hauteur*largeur) return;
    	for(int i=0; i<nbMines ;i++) {
    		int r1=(int) (Math.random()*hauteur)+1;
    		int r2=(int) (Math.random()*largeur)+1;
    		if(mines[r1][r2]) {
    			i--;
    			continue;
    		}
    		mines[r1][r2]=true;
    	}
    }
    
    private void calculeAdjacence() {
    	for(int i=1;i<=hauteur;i++) {
    		for(int j=1;j<=largeur;j++) {
    			int n=0;
    			if(mines[i-1][j-1])
    				n++;
    			if(mines[i-1][j])
    				n++;
    			if(mines[i-1][j+1])
    				n++;
    			if(mines[i][j+1])
    				n++;
    			if(mines[i][j-1])
    				n++;
    			if(mines[i+1][j+1])
    				n++;
    			if(mines[i+1][j])
    				n++;
    			if(mines[i+1][j-1])
    				n++;
    			
    			adja[i][j]=n;
    	
    		}
    	}
    }
    
    public void revelerCase(int x,int y) {
		if(x<1 || x>hauteur || y<1 || y>largeur || etats[x][y]==1 ) return;
    	if(!(etats[x][y]==2)) //si ce n'est pas un drapeau
			etats[x][y]=1;
		if(adja[x][y]==0){
			revelerCase(x, y-1);
			revelerCase(x, y+1);
			revelerCase(x-1, y-1);
			revelerCase(x-1, y);
			revelerCase(x-1, y+1);
			revelerCase(x+1, y-1);
			revelerCase(x+1, y);
			revelerCase(x+1, y+1);
		}
    }
    
    public void drapeauCase(int x,int y) {
    	int et=etats[x][y];
    	if(et==0)
    		etats[x][y]=2;
    	else if(et==2)
    		etats[x][y]=0;
    }
    
    public void affichage() {
    	int nbDra=this.nbdrapeau();
    	for(int i=0;i<16;++i)
    		System.out.print("*");
    	System.out.println();

    	System.out.println("*Mines/drapeau*");
    	System.out.printf("*%3d  /  %d     *\n", nbMines, nbDra);
    	for(int i=0;i<16;++i)
    		System.out.print("*");
    	
    	System.out.println();
    	System.out.print("  ");
    	for(int i=0;i<largeur;i++)
    		System.out.print(i+1+" ");
    	
    	System.out.println();
    	
    	for(int i=1;i<=hauteur;i++) {
			System.out.print(i+ " ");
    		for(int j=1;j<=largeur;j++) {
    			int et=etats[i][j];
    			if(et==0)
    				System.out.print("."+" ");
    			else if(et==1)
        			System.out.print(this.adja[i][j] + " ");
    			else
    				System.out.print("?"+" ");
    		}
    		System.out.println();
    	}
	}
	
	//method aux: pour afficher l'endroi exacte des mines
	public void affichageHint() {
		
		System.out.println("\n");
		
		for(int i=0;i<16;++i)
    		System.out.print("*");
    	System.out.println();

    	System.out.println("**Hint / Plan***");
    	for(int i=0;i<16;++i)
    		System.out.print("*");
    	
    	System.out.println();
		System.out.print("  ");


    	for(int i=0;i<largeur;i++)
    		System.out.print(i+1+" ");

		System.out.println();

    	for(int i=1;i<=hauteur;i++) {
			System.out.print(i+ " ");
    		for(int j=1;j<=largeur;j++) {
				if(mines[i][j])
					System.out.print("@"+" ");
				else
					System.out.print(adja[i][j]+" ");
    		}
    		System.out.println();
    	}
    }

    public boolean jeuPerdu() {
    	
    	for(int i=1;i<=hauteur;i++) {
    		for(int j=1;j<=largeur;j++) {
    			if(etats[i][j]==1 && mines[i][j])
    				return true;
    		}
    	}
    	return false;
    }
    
    public boolean jeuGagne() {
    	for(int i=1;i<=hauteur;i++) {
    		for(int j=1;j<=largeur;j++) {
    			if(!mines[i][j] && etats[i][j]!=1)
    				return false;
    		}
    	}
    	return true;
    }
    //method aux : pour calculer le nombre de drapeau ! 
    public int nbdrapeau() {
    	int res=0;
    	for(int i=1;i<=hauteur;i++) 
    		for(int j=1;j<=largeur;j++) 
    			if(etats[i][j]==2) res++;
    	
    	return res;
    }
}
