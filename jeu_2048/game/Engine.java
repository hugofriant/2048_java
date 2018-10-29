/**
 * 
 */
package jeu_2048.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Engine {
	
	private int largeur;
	private int hauteur;
	private Cell[][] grille;
	
	// Constructeur sans arguments
	public Engine()
	{
		this.largeur = 4;
		this.hauteur = 4;
		Cell[][] res = new Cell[this.largeur][this.hauteur];
		for(int i=0; i<this.largeur; i++)
		{
			for(int j=0; j<this.hauteur; j++)
			{
				res[i][j]=new Cell(0);
			}
		}
		
		this.grille = res;
		addNewCell();
		addNewCell();
	}

	// Constructeur avec initialisation
	public Engine(int[][] tab)
	{
		this.largeur = tab[0].length;
		this.hauteur = tab.length;
		
		Cell[][] res = new Cell[this.largeur][this.hauteur];
		for(int i=0; i<this.largeur; i++)
		{
			for(int j=0; j<this.hauteur; j++)
			{
				res[i][j]=new Cell(tab[i][j]);
			}
		}
		
		this.grille = res;
	}
	
	public ArrayList<Cell> getEmptyCells()
	{
		ArrayList<Cell> res = new ArrayList<Cell>();
		for(int i=0; i<this.largeur; i++)
		{
			for(int j=0; j<this.hauteur; j++)
			{
				if(this.grille[i][j].getValue()==0)
				{
					res.add(this.grille[i][j]);
				}
			}
		}
		return res;
	}
	
	public void addNewCell()
	{
		ArrayList<Cell> emptyCells = this.getEmptyCells();
		if(emptyCells.size()!=0)
		{
			// On a 80% de chance d'avoir un nombre compris entre 1 et 80 et 20% de chance pour les nombres restants
			int index = genRandInt(0, emptyCells.size()-1);
			int val = genRandInt(1,100);
			if(val<=80)
			{
				emptyCells.get(index).setValue(2);
			}
			else
			{
				emptyCells.get(index).setValue(4);
			}
		}
	}
	
	public void rotate()
	{
		// On échange la ième ligne et la (3-i)ème colonne
		Cell[][] res = new Cell[this.largeur][this.hauteur];
		for(int i=this.largeur-1; i>-1; i--)
		{
			for(int j=0; j<this.hauteur; j++)
			{
				res[j][this.largeur-1-i]=this.grille[i][j];
			}
		}
		this.grille=res;
	}
	
	public void moveRight()
	{
		for(int i=0; i<this.hauteur; i++)
		{
			// lastZero désigne la position du zéro le plus à droite sur la ligne en cours de traitement
			int lastZero = this.largeur-1;
			for(int j=this.largeur-1; j>-1; j--)
			{
				if(this.grille[i][j].getValue()!=0)
				{
					echange(i,j,i,lastZero);
					lastZero-=1;
				}
				else
				{
					if(j>lastZero)
					{
						lastZero=j;
					}
				}
			}
		}
	}
	
	public void fuseRight()
	{
		for(int i=0; i<this.hauteur; i++)
		{
			for(int j=this.largeur-1; j>0; j--)
			{
				if(this.grille[i][j].getValue()==this.grille[i][j-1].getValue())
				{
					this.grille[i][j].setValue(2*this.grille[i][j].getValue());
					this.grille[i][j-1].setValue(0);
				}
			}
		}
	}
	
	public void right()
	{
		moveRight();
		fuseRight();
		moveRight();
	}
	
	public void down()
	{
		rotate();
		rotate();
		rotate();
		right();
		rotate();
	}
	
	public void left()
	{
		rotate();
		rotate();
		right();
		rotate();
		rotate();
	}
	
	public void up()
	{
		rotate();
		right();
		rotate();
		rotate();
		rotate();
	}
	
	public boolean isOver()
	{
		ArrayList<Cell> emptyCells = this.getEmptyCells();
		// Test cellules vides
		if(emptyCells.size()!=0)
		{
			return false;
		}
		
		// Test cellules contigues identiques sur les lignes
		for(int i=0; i<this.hauteur; i++)
		{
			for(int j=this.largeur-1; j>0; j--)
			{
				if(this.grille[i][j].getValue()==this.grille[i][j-1].getValue())
				{
					return false;
				}
			}
		}

		// Test cellules contigues identiques sur les colonnes
		for(int j=0; j<this.largeur; j++)
		{
			for(int i=this.hauteur-1; i>0; i--)
			{
				if(this.grille[i][j].getValue()==this.grille[i-1][j].getValue())
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public int score()
	{
		int max = 0;
		for(int i=0; i<this.largeur; i++)
		{
			for(int j=0; j<this.hauteur; j++)
			{
				if(this.grille[i][j].getValue()>max)
				{
					max = this.grille[i][j].getValue();
				}
			}
		}
		return max;
	}
	
	public int getNumberOfFreeCells()
	{
		return getEmptyCells().size();
	}
	
	public int getNumberOfSameAdjacentValues()
	{
		int res = 0;
		for(int i=0; i<this.hauteur; i++)
		{
			for(int j=this.largeur-1; j>0; j--)
			{
				if((this.grille[i][j].getValue()==this.grille[i][j-1].getValue()) && ((this.grille[i][j].getValue()!=0)))
				{
					res+=1;
				}
			}
		}
		for(int j=0; j<this.largeur; j++)
		{
			for(int i=this.hauteur-1; i>0; i--)
			{
				if((this.grille[i][j].getValue()==this.grille[i-1][j].getValue()) && (this.grille[i][j].getValue()!=0))
				{
					res+=1;
				}
			}
		}
		return res;
	}
	
	public int getMonotonicity()
	{
		int res = 0;
		for(int i=0; i<this.hauteur; i++)
		{
			for(int j=0; j<this.largeur; j++)
			{
				if((i<this.hauteur-1) && (this.grille[i][j].getValue()!=0))
				{
					if((this.grille[i+1][j].getValue()>this.grille[i][j].getValue()) && (this.grille[i+1][j].getValue()!=0))
					{
						res+=1;
					}
					else if((this.grille[i+1][j].getValue()<this.grille[i][j].getValue()) && (this.grille[i+1][j].getValue()!=0))
					{
						res-=1;
					}
				}
				
				if((j<this.largeur-1) && (this.grille[i][j].getValue()!=0))
				{
					if((this.grille[i][j+1].getValue()>this.grille[i][j].getValue()) && (this.grille[i][j+1].getValue()!=0))
					{
						res+=1;
					}
					else if((this.grille[i][j+1].getValue()<this.grille[i][j].getValue()) && (this.grille[i][j+1].getValue()!=0))
					{
						res-=1;
					}
				}
			}
		}
		return res;
	}
	
	public double evaluate(double p1, double p2, double p3)
	{
		double freeCells = (double)(getNumberOfFreeCells())/(double)(this.hauteur*this.largeur);
		double adjacentValues = (double)getNumberOfSameAdjacentValues()/24d;
		double monotonicity = (double)getMonotonicity()/24d;
		return (p1*freeCells+p2*adjacentValues+p3*monotonicity);
	}
	
	public void echange(int x1, int y1, int x2, int y2)
	{
		Cell temp = new Cell(this.grille[x1][y1].getValue());
		this.grille[x1][y1]=new Cell(this.grille[x2][y2].getValue());
		this.grille[x2][y2]=temp;
	}
	
	public void nextAutomaticStep()
	{
		double p1 = 0.1;
		double p2 = 0.8;
		double p3 = 0.1;
		
		Engine engRight = new Engine(getVals2D());
		Engine engDown = new Engine(getVals2D());
		Engine engLeft = new Engine(getVals2D());
		Engine engUp = new Engine(getVals2D());
		
		int[][]beforeRight = engRight.getVals2D();
		int[][]beforeUp = engUp.getVals2D();
		int[][]beforeLeft = engLeft.getVals2D();
		int[][]beforeDown = engDown.getVals2D();
		
		engRight.right();
		engDown.down();
		engLeft.left();
		engUp.up();
		
		double evalRight = engRight.evaluate(p1, p2, p3);
		double evalDown = engDown.evaluate(p1, p2, p3);
		double evalLeft = engDown.evaluate(p1, p2, p3);
		double evalUp = engDown.evaluate(p1, p2, p3);
		
		if(Arrays.deepEquals(beforeRight, engRight.getVals2D())==true)
		{
			evalRight = 0.0;
		}
		
		if(Arrays.deepEquals(beforeLeft, engLeft.getVals2D())==true)
		{
			evalLeft = 0.0;
		}
		
		if(Arrays.deepEquals(beforeDown, engDown.getVals2D())==true)
		{
			evalDown = 0.0;
		}
		
		if(Arrays.deepEquals(beforeUp, engUp.getVals2D())==true)
		{
			evalUp = 0.0;
		}
		
		if((evalRight>=evalDown) && (evalRight>=evalLeft) && (evalRight>=evalUp))
		{
			this.right();
			this.addNewCell();
		}
		else if((evalDown>=evalUp) && (evalDown>=evalRight) && (evalDown>=evalLeft))
		{
			this.down();
			this.addNewCell();
		}
		else if((evalLeft>=evalDown) && (evalLeft>=evalRight) && (evalLeft>=evalUp))
		{
			this.left();
			this.addNewCell();
		}
		else if((evalUp>=evalDown) && (evalUp>=evalRight) && (evalUp>=evalLeft))
		{
			this.up();
			this.addNewCell();
		}
		else 
		{
			this.right();
			this.addNewCell();
		}
	}
	
	public void automaticGame()
	{
		while (isOver()==false)
		{
			nextAutomaticStep();
		}
	}
	
	public int[] getVals()
	{
		int[] res = new int[16];
		int c= 0;
		
		for(int i=0; i<this.largeur; i++)
		{
			for(int j=0; j<this.hauteur; j++)
			{
				res[c]=this.grille[i][j].getValue();
				c+=1;
			}
		}
		return res;
	}
	
	public int[][] getVals2D()
	{
		int[][] res = new int[this.largeur][this.hauteur];
		int c=0;
		
		for(int i=0; i<this.largeur; i++)
		{
			int[] tempTab = new int[this.largeur];
			int c2=0;
			for(int j=0; j<this.hauteur; j++)
			{
				tempTab[c2]=this.grille[i][j].getValue();
				c2+=1;
			}
			res[c]=tempTab;
			c+=1;
		}
		return res;
	}
	
	static int genRandInt(int min, int max)
	{
		double rnd = Math.random();
		return (int)(rnd*(max-min+1)+min);
	}
	
	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public Cell[][] getGrille() {
		return grille;
	}

	public void setGrille(Cell[][] grille) {
		this.grille = grille;
	}
}