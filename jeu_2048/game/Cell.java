/**
 * 
 */
package jeu_2048.game;

public class Cell {
	private int value;
		
		public Cell(int v)
		{
			this.value = v;
		}
	
		public int getValue() {
			return value;
		}
	
		public void setValue(int value) {
			this.value = value;
		}
}