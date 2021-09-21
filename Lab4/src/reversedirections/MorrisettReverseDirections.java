package reversedirections;

/****************************************** 
* @Author John Morrisett
* @since 2/9/2021
* Program: MorrisettReverseDirections.java
* COMSC111L.51
* Professor Patterson
* Purpose:  Read in a specifically formatted file of directions "e.g."
* 			North on Smith
* 			South on Foo
* 			East on Bar
* 			Print out the original directions using a stack, and then print out the
* 			Reverse directions e.g.
* 			West on Bar
* 			North on Foo
* 			South on Smith
* 			
* Specs:  Read in the specifically formatted file; assign it to a stack; 
* 		  Print out the original directions; print out the reverse directions
* 		  Of the original directions
* Notes:  #symbol indicates where a change would need to be made if assignment called for
* 		  Java API stack
*******************************************/

import java.util.Scanner;
import java.io.File;
import jsjf.*;
import java.util.Stack;

public class MorrisettReverseDirections {
	//Program that prints out a set of directions, and then its reverse
	public static void main(String args[]) throws java.io.IOException{
		//get the file information
		ArrayStack<String> data = readIn("src/goingthere.txt"); //#Change ArrayStack to Stack
		ArrayStack<String> reverse = new ArrayStack<String>(); //#Change ArrayStack to Stack
		

		System.out.println("Original Directions:\n");
		while (!data.isEmpty()) {
			//print out information in an ordered fashion
			String val = data.pop();
			System.out.println(val);
			reverse.push(val);  //save reverse order
		}
		System.out.println("\nReverse Directions\n");
		while (!reverse.isEmpty()) {
			String val = reverse.pop();
			String[] info = val.split(" ", 2); //split into changed and static parts of string
			
			//change the direction of the original to the opposite and print it
			if (info[0].toLowerCase().equals("north")) { 
				System.out.print("South ");
			} else if (info[0].toLowerCase().equals("south")) {
				System.out.print("North ");
			} else if (info[0].toLowerCase().equals("east")) {
				System.out.print("West ");
			} else if (info[0].toLowerCase().equals("west")) {
				System.out.print("East ");
			}
			System.out.println(info[1]);  //print out the rest of the information
		}
		System.out.println("\n\tBrought to you by John Morrisett 3/2/2021");
	}
	
	public static ArrayStack<String> readIn(String newFile) throws java.io.IOException{
		//assigns the contents of a file to an array stack
		File file = new File(newFile);
		Scanner readIn = new Scanner(file);
		ArrayStack<String> temp = new ArrayStack<String>(); //#Change ArrayStack to Stack
		ArrayStack<String> result = new ArrayStack<String>(); //#Change ArrayStack to Stack
		while (readIn.hasNextLine()) {
			//read in all of the information
			temp.push(readIn.nextLine());
		}
		readIn.close();
		while (!temp.isEmpty()) {
			//since the temp stack is reversed from the original file, reverse again
			result.push(temp.pop());
		}
		return result;
	}
}
