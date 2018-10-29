/**
 * 
 */
package jeu_2048;

import jeu_2048.control.GameController;
import jeu_2048.game.Engine;

public class Launcher {

	public static void main(String[] args) {
		// begin-user-code
		GameController ctrl = new GameController(new Engine());
		
		
		// Calcul du score moyen
		int res = 0;
		for(int i=0; i<30; i++)
		{
			Engine eng = new Engine();
			eng.automaticGame();
			res+=eng.score();
			eng = null;
		}
		System.out.println("Moyenne : " + (double)(res)/30.0);
		// end-user-code
	}
}