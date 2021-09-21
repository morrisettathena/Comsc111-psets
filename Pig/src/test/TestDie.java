package test;
/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Aug 29, 2019
 */

import game.Die;

/**
 * This class tests the Die class to make sure that the values are in range and random.
 */
public class TestDie {

	public static void main(String[] args) {
		Die d1 = new Die();
		Die d2 = new Die();

		//verify that roll, getValue, and toString return the same number
		d1.roll();
		int val1 = d1.getValue();
		int val2 = d2.roll();
		String s1 = d1.toString() + " "+ d2.toString();
		String s2 = val1  + " "+  val2;
		if(!s1.equals(s2)) {
			System.out.println("Next two lines should match");
			System.out.println(s1);
			System.out.println(s2);
		}
		
		
		int sum1 = 0;
		int sum2 = 0;
		int numRolls = 1000;
		//roll the dice numRolls times and make sure the values are in the expected range and distribution
		for(int i = 0; i < numRolls; i++) {
			sum1 += d1.roll();
			sum2 += d2.roll();
			
			//no die should have a value below 1 or over 6
			if(d1.getValue() > 6 || d1.getValue() < 1 || d2.getValue() > 6 || d2.getValue() < 1) {
				System.out.println("Value out of range");
			}
		}
		
		//if the sum of many rolls is the same, the rolls are likely not random
		if(sum1 == sum2) {
			System.out.println("Values may not be random");
		}
		
		//an average fair die roll is 3.5, if the average is very different, there may be a problem
		if(sum1 < numRolls * 3 || sum1 > numRolls * 4){
			System.out.println("Values may not be random");
		}
		
		System.out.println("Tests complete");

	}

}