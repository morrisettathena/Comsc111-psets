package game;
/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Aug 29, 2019
 */

import java.util.Scanner;

/**
 * Create a Game object and play a game of Pig with human and/or computer players
 */
public class GameLauncher {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Player p0;
		System.out.println("Is Player 1 human or computer?");
		if(input.next().charAt(0)=='h') {
			p0 = new HumanPlayer();
		}else {
			p0 = new ComputerPlayer();
		}
		
		Player p1;
		System.out.println("Is Player 2 human or computer?");
		if(input.next().charAt(0)=='h') {
			p1 = new HumanPlayer();
		}else {
			p1 = new ComputerPlayer();
		}
		Game g = new Game(p0, p1);
		g.play();
		input.close();
	}

}
