/**
 * 
 */
package jeu_2048.hmi;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import jeu_2048.control.GameController;

public class GamePanel extends JPanel {

	private GameController control;

	private Color valToColor(int v)
	{
		if(v==2)
		{
			return new Color(250, 250, 210);
		}
		else if(v==4)
		{
			return new Color(238, 221, 130);
		}
		else if(v==8)
		{
			return new Color(205, 133, 63);
		}
		else if(v==16)
		{
			return new Color(205, 92, 92);
		}
		else if(v==32)
		{
			return new Color(255, 99, 71);
		}
		else if(v==64)
		{
			return new Color(255, 105, 180);
		}
		else if(v==128)
		{
			return new Color(186, 85, 211);
		}
		else if(v==256)
		{
			return new Color(160, 32, 240);
		}
		else if(v==512)
		{
			return new Color(127, 255, 212);
		}
		else if(v==1024)
		{
			return new Color(127, 255, 0);
		}
		else if(v==2048)
		{
			return new Color(0, 0, 255);
		}
		return new Color(250, 250, 210);
	}
	
	public GamePanel(GameController ctrl) {
		this.control = ctrl;
	}
	
	public void paint(Graphics g)
	{
		int w = this.control.getMainEngine().getLargeur();
		int h = this.control.getMainEngine().getHauteur();
		g.setFont(g.getFont().deriveFont(Font.BOLD, 48.0f));
		
		for(int i=0; i<w; i++)
		{
			for(int j=0; j<h; j++)
			{
				// Dessin des cases
				g.setColor(new Color(139, 69, 19));
				g.fillRect(10+i*110, 10+j*110, 100, 100);
				
				// Dessin des valeurs
				int cellValue = this.control.getMainEngine().getGrille()[j][i].getValue();
				g.setColor(valToColor(cellValue));
				g.fillRect(10+i*110+5, 10+j*110+5, 90, 90);
				
				g.setColor(Color.BLACK);
				FontMetrics frc = g.getFontMetrics();
				Rectangle2D r = frc.getStringBounds(Integer.toString(cellValue), g);
				
				if(cellValue != 0)
				{
					g.drawString(Integer.toString(cellValue), 60+i*110-(int)(r.getWidth()/2), 10+j*110+(int)(r.getHeight()/2)+40);
				}
			}
		}
	}
}