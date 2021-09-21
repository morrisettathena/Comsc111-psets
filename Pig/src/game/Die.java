package game;

/**
 * @author John Morrisett <jmorrisett725@g.rwu.edu>
 * @date 2/15/2021
 */

import java.util.Random;

/**
 * this class simulates a six-sided die being rolled
 */

public class Die implements Comparable<Die>{
	private int value = 1;
	public final int SIDES = 6;
	
	public Die() {
		value = getValue();
	}
	
	public int roll() {
		//sets the value to a random integer from 1-6
		Random rand = new Random();
		value = rand.nextInt(6) + 1;
		return value;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "" + value;
	}
	
	@Override
	public int compareTo(Die d) {
		//compares two dies and returns an integer representation of which one is bigger
		if (this.getValue() > d.getValue())
			return 1;
		else if (this.getValue() < d.getValue())
			return -1;
		else
			return 0;
	}
}
