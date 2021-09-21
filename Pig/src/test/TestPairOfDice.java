package test;
/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Aug 29, 2019
 */

import game.PairOfDice;

/**
 * This class tests the PairOfDice class to make sure that the values are in range and 
 * follow the expected distribution.
 */
public class TestPairOfDice {

	public static void main(String[] args) {
		PairOfDice dice1 = new PairOfDice();

		//verify that roll, getSum, and toString are consistent
		int val1 = dice1.roll();
		int val2 = dice1.getSum();
		String s1 = dice1.toString();
		String s2 = dice1.getDie1Value() + " " + dice1.getDie2Value();
		if(!s1.equals(s2)) {
			System.out.println("Next two lines should match");
			System.out.println(s1);
			System.out.println(s2);
		}
		if(val1 != val2) {
			System.out.println("Next two lines should match");
			System.out.println(val1);
			System.out.println(val2);
		}
		

		int numOnes = 0;
		int numSnakeEyes = 0;
		double sumTotal = 0.0;
		int numRolls = 1000;
		//roll the dice numRolls times and make sure the values are in the expected range and distribution
		for(int i = 0; i < numRolls; i++) {
			int sum = dice1.roll();
			sumTotal += sum;
			
			//the sum of two dice should be between 2 and 12
			if(sum > 12 || sum < 2) {
				System.out.println("Value out of range");
			}
			
			if(dice1.hasOne()) {
				numOnes++;
			}
			
			if(dice1.hasSnakeEyes()) {
				numSnakeEyes++;
			}
		}
		double avgSum = (double)sumTotal/numRolls;
		double onesRate = (double)numOnes/numRolls;
		double snakeEyeRate = (double)numSnakeEyes/numRolls;

		
		//The expected outcome of rolling two dice is 7.  If it is very different on average there may
		//be a problem.
		if(avgSum < 6.5 || avgSum > 7.5) {
			System.out.println("Sum may be incorrect");
		}
		
		//The expected rate of getting at least one 1 is 11/36.  If it is very different on average 
		//there may be a problem.
		if(onesRate < .25 || onesRate > .36){
			System.out.println("hasOne may be incorrect");
		}
		
		//The expected rate of getting two 1s is 1/36.  If it is very different on average 
		//there may be a problem.
		if(snakeEyeRate < .02 || snakeEyeRate > .037){
			System.out.println("hasSnakeEyes may be incorrect");
		}
		
		System.out.println("Tests complete");

	}

}
