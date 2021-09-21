package game;

/**
 * @author John Morrisett <jmorrisett725@g.rwu.edu>
 * @date 2/15/2021
 */

public class PairOfDice {
	
/**
 * Class which represents two of the die class being tossed
 */
	
	private Die dieA;
	private Die dieB;
	
	public PairOfDice() {
		dieA = new Die();
		dieB = new Die();
	}
	
	public int roll() {
		//returns the value of the two dies rolled together
		return dieA.roll() + dieB.roll();
	}
	
	public int getSum() {
		return dieA.getValue() + dieB.getValue();
	}
	
	public int getDie1Value() {
		return dieA.getValue();
	}
	
	public int getDie2Value() {
		return dieB.getValue();
	}
	
	public boolean hasSnakeEyes() {
		//returns true if both values on the dies are one
		return dieA.getValue() == 1 && dieB.getValue() == 1;
	}
	
	public boolean hasOne() {
		//returns true if one value on the dies are one
		return dieA.getValue() == 1 ^ dieB.getValue() == 1;
	}
	
	public String toString() {
		return dieA.getValue() + " " + dieB.getValue();
	}
}
