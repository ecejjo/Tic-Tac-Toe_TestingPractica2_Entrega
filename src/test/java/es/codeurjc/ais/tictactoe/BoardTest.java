package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;

public class BoardTest {
	Board board;
	Cell cell;

	@Before
	public void setUpTest() {
		board = new Board();
		cell = new Cell();	
	}
	
	@Test
	public void given_firstPlayer_when_move_then_winsTheGame() {
		// Given			
		cell = board.getCell(0);
		cell.active = true;
		cell.value = "X";

	    cell = board.getCell(1);
		cell.active = true;
		cell.value = "X";
		
		cell = board.getCell(2);
		cell.active = true;
		cell.value = "X";
		
		// When
		boolean drawResult = board.checkDraw();
		int[] winPos = board.getCellsIfWinner("X");
		int[] expectedPos = {0,1,2};
		
		
		// Then
		assertArrayEquals(expectedPos, winPos);
		assertFalse(drawResult);
	}
	
	@Test	
	public void given_firstPlayer_when_move_then_lossTheGame() {
		// Given	
		cell = board.getCell(0);
		cell.active = true;
		cell.value = "X";

		cell = board.getCell(4);
		cell.active = true;
		cell.value = "X";
		
		cell = board.getCell(2);
		cell.active = true;
		cell.value = "X";

		cell = board.getCell(3);
		cell.active = true;
		cell.value = "X";
		
		// When
		int[] winPos = board.getCellsIfWinner("X");
		boolean drawResult = board.checkDraw();
		
		// Then
		assertNull(winPos);
		assertFalse(drawResult);
		
	}	
		
	@Test	
	public void given_twoPlayer_when_bothArePlayingAndNoneWins_then_IsADraw() {
		// Given	
		cell = board.getCell(0);
		cell.active = true;
		cell.value = "X";
		
		cell = board.getCell(2);
		cell.active = true;
		cell.value = "X";

		cell = board.getCell(3);
		cell.active = true;
		cell.value = "X";
		
		cell = board.getCell(5);
		cell.active = true;
		cell.value = "X";
	
		cell = board.getCell(7);
		cell.active = true;
		cell.value = "X";
		
		
		cell = board.getCell(1);
		cell.active = true;
		cell.value = "O";

		cell = board.getCell(4);
		cell.active = true;
		cell.value = "O";
		
		cell = board.getCell(6);
		cell.active = true;
		cell.value = "O";
	
		cell = board.getCell(8);
		cell.active = true;
		cell.value = "O";
		
		// When
		boolean drawResult = board.checkDraw();
		int[] winPos = board.getCellsIfWinner("X");

		// Then
		assertNull(winPos);		
		assertTrue(drawResult);
	}	
		
}
