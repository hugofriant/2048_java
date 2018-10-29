package jeu_2048.game;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class EngineTest {

	@Test
	public void testEngine() {
		Engine engine = new Engine();
		assertEquals(engine.getLargeur() * engine.getHauteur() - 2, engine.getNumberOfFreeCells());
	}
	
	@Test
	public void testGetEmptyCells() {
	    int[][] vals = {{0,2,2,2},{2,4,2,8},{4,8,16,2},{16,4,0,16}};
	    Engine engine = new Engine(vals);
	    
	    ArrayList<Cell> empty = new ArrayList<Cell>();
	    empty.add(engine.getGrille()[0][0]); empty.add(engine.getGrille()[3][2]);
	    assertTrue(empty.equals(engine.getEmptyCells()));
	}
	
	@Test
	public void testGetNumberOfFreeCells() {
	    int[][] vals = {{0,2,0,0},{2,4,0,8},{0,0,0,2},{16,4,0,16}};
	    Engine engine = new Engine(vals);
	    
	    assertEquals(8, engine.getNumberOfFreeCells());
	}
	
	@Test
	public void testAddNewCell() {
	    int[][] vals = {{0,2,0,0},{2,4,0,8},{0,0,0,2},{16,4,0,16}};
	    Engine engine = new Engine(vals);
	    
	    engine.addNewCell();
	    assertEquals(7, engine.getNumberOfFreeCells());
	}
	
	@Test
	public void testGetVals() {
	    int[][] vals = {{0,2,0,0},{2,4,0,8},{0,0,0,2},{16,4,0,16}};
	    Engine engine = new Engine(vals);
	    
	    int[] expected = {0,2,0,0,2,4,0,8,0,0,0,2,16,4,0,16};
	    assertArrayEquals(engine.getVals(), expected);
	}
	
	@Test
	public void testRotate() {
	    int[][] vals = {{0,2,0,0},{2,4,0,8},{0,0,0,2},{16,4,0,16}};
	    Engine engine = new Engine(vals);
	    
	    int[] expected = {16,0,2,0,4,0,4,2,0,0,0,0,16,2,8,0};
	    engine.rotate();
	    assertArrayEquals(engine.getVals(), expected);
	}
	
	@Test
	public void testMoveRight() {
		int[][] vals = {{0,2,0,0},
						{2,4,0,8},
						{0,0,0,2},
						{16,4,0,16}};
	    Engine engine = new Engine(vals);
	    
	    int[] expected = {0,0,0,2,0,2,4,8,0,0,0,2,0,16,4,16};
	    engine.moveRight();
	    assertArrayEquals(engine.getVals(), expected);
	}
	
	@Test
	public void testFuseRight() {
		int[][] vals = {{0,0,2,2},
						{2,2,2,2},
						{0,0,0,2},
						{0,0,16,16}};
	    Engine engine = new Engine(vals);
	    
	    int[] expected = {0,0,0,4,0,4,0,4,0,0,0,2,0,0,0,32};
	    engine.fuseRight();
	    assertArrayEquals(engine.getVals(), expected);
	}
	
	@Test
	public void testRight() {
		int[][] vals = {{0,0,2,2},
						{2,2,2,2},
						{0,0,0,2},
						{0,0,16,16}};
	    Engine engine = new Engine(vals);
	    
	    int[] expected = {0,0,0,4,0,0,4,4,0,0,0,2,0,0,0,32};
	    engine.right();
	    assertArrayEquals(engine.getVals(), expected);
	}
	
	@Test
	public void testIsOver() {
		int[][] vals = {{2,4,8,16},
						{32,64,128,256},
						{512,1024,2,4},
						{8,16,32,64}};
	    Engine engine = new Engine(vals);
	    assertTrue(engine.isOver());
	}
	
	@Test
	public void testScore() {
		int[][] vals = {{2,4,8,16},
						{32,64,128,256},
						{512,1024,2,4},
						{8,16,32,64}};
	    Engine engine = new Engine(vals);
	    assertEquals(engine.score(), 1024);
	}

	@Test
	public void testEchange() {
		int[][] vals = {{0,0,2,2},
						{2,2,2,2},
						{0,0,0,2},
						{0,0,16,16}};
	    Engine engine = new Engine(vals);

	    int[] expected = {16,0,2,2,2,2,2,2,0,0,0,2,0,0,16,0};
	    engine.echange(0, 0, 3, 3);
	    assertArrayEquals(engine.getVals(), expected);
	}
	
	@Test
	public void testGetNumberOfSameAdjacentValues() {
	    //  2  2  0  8
	    //  2  4  0  8
	    //  0  4  0 16
	    //  4  4  0 16
	    int[][] vals = {{2,2,0,8},{2,4,0,8},{0,4,0,16},{4,4,0,16}};
	    Engine engine = new Engine(vals);
	    assertEquals(7, engine.getNumberOfSameAdjacentValues());
	}
	
	@Test
	public void testGetNumberOfSameAdjacentValuesBis() {
	    //  2  2  2  2
	    //  2  2  2  2
	    //  2  2  2  2
	    //  2  2  2  2
	    int[][] vals = {{2,2,2,2},{2,2,2,2},{2,2,2,2},{2,2,2,2}};
	    Engine engine = new Engine(vals);
	    assertEquals(24, engine.getNumberOfSameAdjacentValues());
	}
	
	@Test
	public void testGetMonotonicity() {
	    //  2  2  0  8
	    //  2  4  0  8
	    //  0  4  0 16
	    //  4  4  0 16
	    int[][] vals = {{2,2,0,8},{2,4,0,8},{0,4,0,16},{4,4,0,16}};
	    Engine engine = new Engine(vals);
	    assertEquals(3, engine.getMonotonicity());
	}
	
	@Test
	public void testGetMonotonicityBis() {
	    //  2  4  8  16
	    //  32  64  128  256
	    //  512  1024  2048 4096
	    //  8192  16384  32768 65536
	    int[][] vals = {{2,4,8,16},{32,64,128,256},{512,1024,2048,4096},{8192,16384,32768,65536}};
	    Engine engine = new Engine(vals);
	    assertEquals(24, engine.getMonotonicity());
	}
	
	@Test
	public void testEvaluateOne() {
		//  0  0  0  0
	    //  0  0  0  0
	    //  0  0  0  0
	    //  0  0  0  0
	    int[][] vals = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
	    Engine engine = new Engine(vals);
	    assertTrue(engine.evaluate(1.0, 0.0, 0.0)==1.0);
	}
	
	@Test
	public void testEvaluateTwo() {
		//  2  2  2  2
	    //  2  2  2  2
	    //  2  2  2  2
	    //  2  2  2  2
	    int[][] vals = {{2,2,2,2},{2,2,2,2},{2,2,2,2},{2,2,2,2}};
	    Engine engine = new Engine(vals);
	    assertTrue(engine.evaluate(1.0, 0.0, 0.0)==0.0);
	}
	
	@Test
	public void testEvaluateThree() {
		//  2  2  2  2
	    //  2  2  2  2
	    //  2  2  2  2
	    //  2  2  2  2
	    int[][] vals = {{2,2,2,2},{2,2,2,2},{2,2,2,2},{2,2,2,2}};
	    Engine engine = new Engine(vals);
	    assertTrue(engine.evaluate(0.0, 1.0, 0.0)==1.0);
	}

	@Test
	public void testEvaluateFour() {
		//  2  2  0  8
	    //  2  4  0  8
	    //  0  4  0 16
	    //  4  4  0 16
	    int[][] vals = {{2,2,0,8},{2,4,0,8},{0,4,0,16},{4,4,0,16}};
	    Engine engine = new Engine(vals);
	    assertTrue(engine.evaluate(0.0, 0.0, 1.0)==0.125);
	}
}
