package test;
/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Aug 29, 2019
 */

import game.Die;

/**
 * This class tests the compareTo method in the Die class
 */
public class TestDie2 {

	public static void main(String[] args) {
		Die d1 = new Die();
		Die d2 = new Die();

		int val1 = d1.roll();
		int val2 = d2.roll();
		
		if(d1.compareTo(d2)>0 && val1<=val2) {
			System.out.println("CompareTo says D1 is greater, but D2 has greater value.");
		}
		if(d1.compareTo(d2)<0 && val1>=val2) {
			System.out.println("CompareTo says D2 is greater, but D1 has greater value.");
		}
		if(d1.compareTo(d2)==0 && val1!=val2) {
			System.out.println("CompareTo says D2 equals D1, but they do not have the same value.");
		}
		
		System.out.println("Tests complete");

	}

}