package game;

/**
 * @author John Morrisett <jmorrisett725@g.rwu.edu>
 * @date 2/15/2021
 */

import java.util.Scanner;
/**
 * Class which allows a human player to play Pigs.
 */

public class HumanPlayer extends Player {
	Scanner input = new Scanner(System.in);

	public HumanPlayer() {
		//standard constructor which asks for the player to input their name
		super();
		System.out.println("Please input name:  ");
		this.setName(input.nextLine());
	}
	
	@Override
	public boolean isRolling() {
		//input method which allows the player to decide if they want to keep rolling
		char val = 'a';
		do {
			System.out.println("Roll again?  y/n:  ");
			val = input.nextLine().toLowerCase().charAt(0);  //standardize the input
			if (val != 'y' && val != 'n') {
				System.out.println("Invalid input, try again.");
			}  //error checking, if someone put something wrong in
			if (val == 'y')
				return true;
			if (val == 'n')
				return false;
		} while (val != 'y' && val != 'n');
		return false;
	}

}
