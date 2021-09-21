package game;

/**
 * @author John Morrisett <jmorrisett725@g.rwu.edu>
 * @date 2/15/2021
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class which allows a computer AI to play the game
 */

public class ComputerPlayer extends Player {
	
	//several ai types are listed below.  Standard is the one required for class completion
	//Smart uses expected values to determine the most optimal plays
	//Random uses the same ai as standard, but then randomly decides whether or not to continue
	public final List<String> AITYPES = Arrays.asList("Standard", "Random");
	private static int id = 1;
	private String AI = "Standard";
	Scanner input = new Scanner(System.in);

	public ComputerPlayer() {
		//constructor that asks the player to choose which kind of ai they want
		super();
		this.AI = "Standard";
		this.setName("ComputerPlayer " + id);
		id++;
		inputAI();
	}
	
	public ComputerPlayer(String AI) throws IllegalArgumentException {
		//constructor that automatically sets the ai based on the input
		super();
		setAI(AI);
		this.setName("ComputerPlayer " + id);
		id++;
	}

	public void inputAI() {
		//user input method of setting the ai
		boolean repeat = false;
		
		System.out.println("Choose an AI type");
		do {
			//nice string menu for the options
			for (int i = 0; i < AITYPES.size()-1; i++) {
				System.out.printf("%d for %s, ", i+1, AITYPES.get(i));
			}
			System.out.printf("or %d for %s.  ", AITYPES.size(), AITYPES.get(AITYPES.size()-1));
			int answer = input.nextInt()-1;
			System.out.println();
			if (answer < AITYPES.size() && answer >= 0) {
				//error checking if the input was a wrong number
				repeat = false;
				setAI(AITYPES.get(answer));
				System.out.println("Chosen " + AITYPES.get(answer));
			} else {
				repeat = true;
				System.out.println("Please choose a valid input");
			}
		} while (repeat);
	}
	
	public void setAI(String AI) {
		//setter for the ai method, does a little error checking
		if (AITYPES.contains(AI)) {
			this.AI = AI;
		} else
			throw new IllegalArgumentException("Not a valid AI type");
	}
	
	public String getAI() {
		return this.AI;
	}
	
	@Override
	public boolean isRolling() {
		//method which determines if the compute wants to roll again.  Changes based on ai
		if (AI.equals("Standard")) {
			//ai which operates based on how we were instructed to create the ai in class
			return (roundTotal < 20);
		}
		else if (AI.equals("Random")) {
			//ai which randomly decides when to keep rolling after going past 20.
			Random rand = new Random();
			return (rand.nextInt(3) == 1 || roundTotal < 20);
		}
				
		return false;
	}

}
