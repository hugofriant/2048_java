/**
 * 
 */
package jeu_2048.control;

import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;

import jeu_2048.game.Cell;
import jeu_2048.game.Engine;
import jeu_2048.hmi.GameWindow;

public class GameController {

	private Engine mainEngine;
	private GameWindow gameWindow;

	public GameController(Engine eng) {
		this.mainEngine = eng;
		this.gameWindow = new GameWindow(this);
	}
	
	
	
	public void doAction(KeyEvent e)
	{
		if(this.mainEngine.isOver()==true)
		{
			JOptionPane.showMessageDialog(gameWindow, "GAME OVER \nSCORE : "+this.mainEngine.score());
		}
		
		if(this.mainEngine.score()==2048)
		{
			JOptionPane.showMessageDialog(gameWindow, "BRAVO! \nSCORE : 2048");
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			int[][]cGrid = getCurrentGrid();
			this.mainEngine.up();
			if(Arrays.deepEquals(cGrid, getCurrentGrid())==false)
			{
				this.mainEngine.addNewCell();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			int[][]cGrid = getCurrentGrid();
			this.mainEngine.right();
			if(Arrays.deepEquals(cGrid, getCurrentGrid())==false)
			{
				this.mainEngine.addNewCell();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			int[][]cGrid = getCurrentGrid();
			this.mainEngine.down();
			if(Arrays.deepEquals(cGrid, getCurrentGrid())==false)
			{
				this.mainEngine.addNewCell();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			int[][]cGrid = getCurrentGrid();
			this.mainEngine.left();
			if(Arrays.deepEquals(cGrid, getCurrentGrid())==false)
			{
				this.mainEngine.addNewCell();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_N)
		{
			mainEngine.nextAutomaticStep();
		}
		else if(e.getKeyCode()==KeyEvent.VK_A)
		{
			mainEngine.automaticGame();
		}
	}
	
	public void printBoard(int[][] grille)
	{
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				System.out.print(grille[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	private int[][] getCurrentGrid()
	{
		int[][] res = new int[this.mainEngine.getLargeur()][this.mainEngine.getHauteur()];
		for(int i=0; i<this.mainEngine.getLargeur(); i++)
		{
			for(int j=0; j<this.mainEngine.getHauteur(); j++)
			{
				res[i][j]=this.mainEngine.getGrille()[i][j].getValue();
			}
		}
		return res;
	}

	public Engine getMainEngine() {
		return mainEngine;
	}
}