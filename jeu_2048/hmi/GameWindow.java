/**
 * 
 */
package jeu_2048.hmi;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import jeu_2048.control.GameController;
import java.awt.event.KeyEvent;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author friant_hug
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class GameWindow extends JFrame implements KeyListener {

	private GamePanel panel;
	private GameController control;

	public GameWindow(GameController ctrl) {
		// begin-user-code
		this.control = ctrl;
		JFrame wdw = new JFrame();
		this.panel = new GamePanel(this.control);
		
		wdw.setTitle("2048");
		wdw.setPreferredSize(new Dimension(470,490));
		wdw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wdw.setBackground(Color.WHITE);
		wdw.add(this.panel);
		wdw.addKeyListener(this);
		
		wdw.pack();
		wdw.setLocationRelativeTo(null);
		wdw.setVisible(true);
		// end-user-code
	}

	public void keyPressed(KeyEvent e) {
		// begin-user-code
		this.control.doAction(e);
		this.panel.repaint();
		// end-user-code
	}

	public void keyReleased(KeyEvent e) {
		// begin-user-code
		// end-user-code
	}

	public void keyTyped(KeyEvent e) {
		// begin-user-code
		// end-user-code
	}
}