package es.codeurjc.ais.tictactoe;

import static org.mockito.Mockito.*;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.mockito.ArgumentCaptor;

import org.junit.Test;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;;

public class TicTacToeGameTest {

	@Test
	public void given_firstPlayer_when_move_then_winsTheGame() {
		// Given			
		TicTacToeGame tttGame = new TicTacToeGame();
		
		Connection connection1 = mock(Connection.class);
		Connection connection2 = mock(Connection.class);
		
		tttGame.addConnection(connection1);
		tttGame.addConnection(connection2);
		
		Player player1 = new Player(0, "X", "player1");
		Player player2 = new Player(1, "O", "player2");

		
		// When
		tttGame.addPlayer(player1);
		
		// Then
		verify(connection1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItem(player1)));
		verify(connection2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItem(player1)));
		
		reset(connection1);
		reset(connection2);
		
		// When		
		tttGame.addPlayer(player2);

		// Then
		verify(connection1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		verify(connection2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));

		reset(connection1);
		reset(connection2);

		
		
		ArrayList<Integer>  markList = new ArrayList<Integer>(); 
		markList.add(0);
		markList.add(1);
		markList.add(4);
		markList.add(2);
		markList.add(8);
		
		for (int markItem : markList)
		{			
			// when
			tttGame.mark(markItem);

			if (markList.size() -1  ==  markList.indexOf(markItem)) {
				break;
			}
			
			//then
			if (markList.indexOf(markItem)%2 == 0){
				verify(connection1).sendEvent(EventType.SET_TURN, player2);		
				verify(connection2).sendEvent(EventType.SET_TURN, player2);		
				
			}else {
				verify(connection1).sendEvent(EventType.SET_TURN, player1);		
				verify(connection2).sendEvent(EventType.SET_TURN, player1);						
			}

			reset(connection1);
			reset(connection2);
		}
	
		// Then
		int[] expectedPos = {0,4,8};
				
		ArgumentCaptor<WinnerValue> argument =	ArgumentCaptor.forClass(WinnerValue.class);
		verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
		assertEquals(0, argument.getValue().player.getId());
		assertEquals("player1", argument.getValue().player.getName());
		assertEquals("X", argument.getValue().player.getLabel());

		assertArrayEquals(expectedPos, argument.getValue().pos);		
	}

	
	@Test
	public void given_firstPlayer_when_move_then_lossTheGame() {
		// Given			
		TicTacToeGame tttGame = new TicTacToeGame();
		
		Connection connection1 = mock(Connection.class);
		Connection connection2 = mock(Connection.class);
		
		tttGame.addConnection(connection1);
		tttGame.addConnection(connection2);
		
		Player player1 = new Player(0, "X", "player1");
		Player player2 = new Player(1, "O", "player2");

		
		// When
		tttGame.addPlayer(player1);
		
		// Then
		verify(connection1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItem(player1)));
		verify(connection2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItem(player1)));
		
		reset(connection1);
		reset(connection2);
		
		// When		
		tttGame.addPlayer(player2);

		// Then
		verify(connection1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		verify(connection2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));

		reset(connection1);
		reset(connection2);

		
		
		ArrayList<Integer>  markList = new ArrayList<Integer>(); 
		markList.add(0);
		markList.add(2);
		markList.add(1);
		markList.add(5);
		markList.add(3);
		markList.add(8);
		
		for (int markItem : markList)
		{		
			// when
			tttGame.mark(markItem);

			if (markList.size() -1  ==  markList.indexOf(markItem)) {
				break;
			}
			
			//then
			if (markList.indexOf(markItem)%2 == 0){
				verify(connection1).sendEvent(EventType.SET_TURN, player2);		
				verify(connection2).sendEvent(EventType.SET_TURN, player2);		
				
			}else {
				verify(connection1).sendEvent(EventType.SET_TURN, player1);		
				verify(connection2).sendEvent(EventType.SET_TURN, player1);						
			}

			reset(connection1);
			reset(connection2);
		}
	
		// Then
		int[] expectedPos = {2,5,8};
				
		ArgumentCaptor<WinnerValue> argument =	ArgumentCaptor.forClass(WinnerValue.class);
		verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
		assertEquals(1, argument.getValue().player.getId());
		assertEquals("player2", argument.getValue().player.getName());
		assertEquals("O", argument.getValue().player.getLabel());

		assertArrayEquals(expectedPos, argument.getValue().pos);		
	}
	

	@Test
	public void given_twoPlayer_when_bothArePlayingAndNoneWins_then_IsADraw() {
		// Given			
		TicTacToeGame tttGame = new TicTacToeGame();
		
		Connection connection1 = mock(Connection.class);
		Connection connection2 = mock(Connection.class);
		
		tttGame.addConnection(connection1);
		tttGame.addConnection(connection2);
		
		Player player1 = new Player(0, "X", "player1");
		Player player2 = new Player(1, "O", "player2");

		
		// When
		tttGame.addPlayer(player1);
		
		// Then
		verify(connection1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItem(player1)));
		verify(connection2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItem(player1)));
		
		reset(connection1);
		reset(connection2);
		
		// When		
		tttGame.addPlayer(player2);

		// Then
		verify(connection1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));
		verify(connection2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(player1, player2)));

		reset(connection1);
		reset(connection2);

		
		
		ArrayList<Integer>  markList = new ArrayList<Integer>(); 
		markList.add(0);
		markList.add(8);
		markList.add(2);
		markList.add(6);
		markList.add(7);
		markList.add(1);
		markList.add(3);
		markList.add(5);
		markList.add(4);
		
		for (int markItem : markList)
		{
			// when
			tttGame.mark(markItem);

			if (markList.size() -1  ==  markList.indexOf(markItem)) {
				break;
			}
			
			//then
			if (markList.indexOf(markItem)%2 == 0){
				verify(connection1).sendEvent(EventType.SET_TURN, player2);		
				verify(connection2).sendEvent(EventType.SET_TURN, player2);		
				
			}else {
				verify(connection1).sendEvent(EventType.SET_TURN, player1);		
				verify(connection2).sendEvent(EventType.SET_TURN, player1);						
			}

			reset(connection1);
			reset(connection2);
		}
	
		// Then
		ArgumentCaptor<WinnerValue> argument =	ArgumentCaptor.forClass(WinnerValue.class);
		verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
				
		assertNull(argument.getValue());
		
	}

}
