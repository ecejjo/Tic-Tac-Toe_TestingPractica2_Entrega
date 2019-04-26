package es.codeurjc.ais.tictactoe;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import es.codeurjc.ais.tictactoe.WebApp;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;

public class TicTacToeGameSystemTest {
	private WebDriver driver1;
	private WebDriver driver2;
    private static HashMap<Integer, String> cellId;

	@BeforeClass
	public static void setupClass() {
	    cellId = new HashMap<Integer, String>();
	    cellId.put(0, "cell-0");
	    cellId.put(1, "cell-1");
	    cellId.put(2, "cell-2");
	    cellId.put(3, "cell-3");
	    cellId.put(4, "cell-4");
	    cellId.put(5, "cell-5");
	    cellId.put(6, "cell-6");
	    cellId.put(7, "cell-7");
	    cellId.put(8, "cell-8");
	    
		ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();

		WebApp.start();
	}
	
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}	
	
	@Before
	public void setupTest() {
		driver1 = new ChromeDriver();
		driver2 = new ChromeDriver();
	}
	
	@After
	public void teardown() {
		if (driver1 != null) {
			driver1.quit();
		}
		
		if (driver2 != null) {
			driver2.quit();
		}		
	}
	
	@Test
	public void given_firstPlayer_when_move_then_winsTheGame() {
		//given
		driver1.get("http://localhost:8080");
		driver2.get("http://localhost:8080");
		
		driver1.findElement(By.id("nickname")).sendKeys("player1");
		driver1.findElement(By.id("startBtn")).click();

		driver2.findElement(By.id("nickname")).sendKeys("player2");
		driver2.findElement(By.id("startBtn")).click();
		
		ArrayList<Integer>  markList = new ArrayList<Integer>(); 
		markList.add(0);
		markList.add(1);
		markList.add(4);
		markList.add(2);
		markList.add(8);
		
		for (int markItem : markList)
		{
			if (markList.indexOf(markItem)%2 == 0){
				driver1.findElement(By.id(cellId.get(markItem))).click();
				
			}else {
				driver2.findElement(By.id(cellId.get(markItem))).click();		
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String messageExpected = "player1 wins! player2 looses.";

		String driver1Text = driver1.switchTo().alert().getText();
		String driver2Text = driver2.switchTo().alert().getText();
				
		assertEquals(messageExpected, driver1Text);
		assertEquals(messageExpected, driver2Text);
	}

	@Test
	public void given_firstPlayer_when_move_then_lossTheGame() {
		//given
		driver1.get("http://localhost:8080");
		driver2.get("http://localhost:8080");
		
		driver1.findElement(By.id("nickname")).sendKeys("player1");
		driver1.findElement(By.id("startBtn")).click();

		driver2.findElement(By.id("nickname")).sendKeys("player2");
		driver2.findElement(By.id("startBtn")).click();
		
		ArrayList<Integer>  markList = new ArrayList<Integer>(); 
		markList.add(0);
		markList.add(2);
		markList.add(1);
		markList.add(5);
		markList.add(3);
		markList.add(8);
		
		for (int markItem : markList)
		{
			if (markList.indexOf(markItem)%2 == 0){
				driver1.findElement(By.id(cellId.get(markItem))).click();
				
			}else {
				driver2.findElement(By.id(cellId.get(markItem))).click();		
			}
		}
	    		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String messageExpected = "player2 wins! player1 looses.";

		String driver1Text = driver1.switchTo().alert().getText();
		String driver2Text = driver2.switchTo().alert().getText();
				
		assertEquals(messageExpected, driver1Text);
		assertEquals(messageExpected, driver2Text);
	}

	@Test
	public void given_twoPlayer_when_bothArePlayingAndNoneWins_then_IsADraw() {
		//given
		driver1.get("http://localhost:8080");
		driver2.get("http://localhost:8080");
		
		driver1.findElement(By.id("nickname")).sendKeys("player1");
		driver1.findElement(By.id("startBtn")).click();

		driver2.findElement(By.id("nickname")).sendKeys("player2");
		driver2.findElement(By.id("startBtn")).click();
		
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
			if (markList.indexOf(markItem)%2 == 0){
				driver1.findElement(By.id(cellId.get(markItem))).click();
				
			}else {
				driver2.findElement(By.id(cellId.get(markItem))).click();		
			}
		}
	    		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String messageExpected = "Draw!";

		String driver1Text = driver1.switchTo().alert().getText();
		String driver2Text = driver2.switchTo().alert().getText();
				
		assertEquals(messageExpected, driver1Text);
		assertEquals(messageExpected, driver2Text);
	}

}
