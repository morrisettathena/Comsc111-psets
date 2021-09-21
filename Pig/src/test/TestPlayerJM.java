package test;

/**
 * @author John Morrisett <jmorrisett725@g.rwu.edu>
 * @date 2/15/2021
 */

import game.Player;
import game.HumanPlayer;
import game.ComputerPlayer;

public class TestPlayerJM {

	public static void main(String[] args) {
		HumanPlayer p1 = new HumanPlayer();
		ComputerPlayer p2 = new ComputerPlayer("Standard");
		
		/***********************TEST ROUND AND GAME TOTAL******************/
		p1.addToTotal(9);		                                       //addToTotal
		System.out.println("This should be 9: " + p1.getRoundTotal());  //RoundTotal
		p1.endTurn();                                                  //endTurn
		System.out.println("This should be 0: " + p1.getRoundTotal());
		System.out.println("This should be 9: " + p1.getGameTotal());  //getGameTotal
		p1.addToTotal(17); 
		System.out.println("This should be 17: " + p1.getRoundTotal());
		System.out.println("This should be 9: " + p1.getGameTotal());
		p1.endTurn();  
		System.out.println("This should be 0: " + p1.getRoundTotal());
		System.out.println("This should be 26: " + p1.getGameTotal());
		p1.resetGameTotal();                                           //resetGameTotal
		p1.addToTotal(1);
		System.out.println("This should be 1: " + p1.getRoundTotal());
		p1.resetRoundTotal();                                          //resetRoundTotal
		System.out.println("This should be 0: " + p1.getRoundTotal());
		
		/*************************TEST ISROLLING***************************/
		System.out.println("Try for y: " + p1.isRolling());            //isRolling
		System.out.println("Try for n: " + p1.isRolling());
		
		p2.addToTotal(19);
		System.out.println("This should print true: " + p2.isRolling());
		p2.addToTotal(1);
		System.out.println("This should print false: " + p2.isRolling());
		p2.resetRoundTotal();
		
		/****************************TEST INHERITANCE**********************/
		Player p3 = new HumanPlayer();                                  
		Player p4 = new ComputerPlayer("Standard");
		
		/****************************TEST OTHER METHODS********************/
		p1.setName("John");
		System.out.println(p1.getName());
		p1.addToTotal(99);
		p1.endTurn();
		System.out.println("This should be false: " + p1.isWinner());
		p1.addToTotal(1);
		p1.endTurn();
		System.out.println("This should be true: " + p1.isWinner());
		p2.setAI("Smart");
		System.out.println(p2.getAI());
		p2.inputAI();
		
		System.out.println("\n\tBrought to you by John Morrisett 2/16/2021");
		
	}

}
