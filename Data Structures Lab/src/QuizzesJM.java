/****************************************** 
* @Author John Morrisett
* @since 2/9/2021
* Program: QuizzesJM.java
* COMSC111L.51
* Professor Patterson
* Purpose:  Is a game in which the player must input the names
* 	of state capitals when prompted with the state from the
* 	file states.txt.  States are chosen randomly, and the number
* 	of questions that the player needs to answer can be changed
* 	before the start of the game
* Specs:  Read in the specifically formatted file; ask the player for how many questions;
* 		  Input answers until complete; ask the player if they want to play again;
* 		  If so repeat the above steps;
* top-down-design - divide and conquer!
* agile development: always have working/deliverable code
*******************************************/

import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class QuizzesJM {
	public static void main(String args[])throws java.io.IOException {

		String[][] data = readIn("src/states.txt");
		char answer = 'g';
		System.out.println("Welcome to Capital Quiz!");
		
		do {
			gameState(data);
			do {
				Scanner inp = new Scanner(System.in);
				System.out.print("Would you like to play again? y/n");
				String inputted = inp.nextLine();
				if (!inputted.equals("")) {
					answer = inputted.toLowerCase().charAt(0);
				} else {
					answer = 'g';
				}

			} while(answer != 'y' && answer != 'n');
		} while(answer == 'y');	
		System.out.println("\n\t\tBrought to you by John Morrisett on 2/9/2021");
	}
	

	public static String[][] readIn(String newFile) throws java.io.IOException{
		
		//readIn takes a file name and returns the data in order for the game
		//to run properly
		
		File file = new File(newFile);
		Scanner readIn = new Scanner(file);
		int stateAmount = readIn.nextInt();
		readIn.nextLine();
		String[][] data = new String[stateAmount][2];
		for (int i = 0; i < stateAmount; i++) {  //for each state/country slot, copy it into the data
			data[i][0] = readIn.nextLine();
			data[i][1] = readIn.nextLine();
		}
		readIn.close();
		return data;
	}
	
	public static void gameState(String[][] data) {
		
		//gameState contains all the functionality for the game itself
		int correctAnswers = 0;
		final int MAXQUESTIONS = data.length;
		int questionNumber = 0;
		Scanner input = new Scanner(System.in);
		
		//unusedList is an array list that contains every index in the data set that
		//hasn't been used
		ArrayList<Integer> unusedList = new ArrayList<Integer>(); 
		for (int i = 0; i < MAXQUESTIONS; i++) {
			unusedList.add(i);
		}

		do {
			//loop for inputting the number of questions the player wants to answer
			if (questionNumber < 0 || questionNumber > MAXQUESTIONS)
				System.out.println("Invalid. Enter a valid number of questions, from 1 - " 
				+ MAXQUESTIONS);
			else if (questionNumber == 0)
				System.out.println("Please enter the number of questions you would like to answer.");
			System.out.printf("Maximum question amount is %d:  ", MAXQUESTIONS);
			questionNumber = input.nextInt();
			input.nextLine();
		} while (questionNumber <= 0 || questionNumber > MAXQUESTIONS);
		
		for (int i = 0; i < questionNumber; i++) {
			//main game loop, asks the player to input their answer for the question
			int index = getRandom(unusedList);
			System.out.printf("%d. What is the Capitol of %s? ", (i+1), data[index][0]);
			String answer = input.nextLine().toLowerCase();
			if (answer.equals(data[index][1].toLowerCase())) {
				correctAnswers++;
				System.out.println("Correct!");
			} else {
				System.out.println("The correct answer is " + data[index][1]);
			}
			
		}
		
		//when the game is ended, print out the score and percentage
		System.out.printf("You got %s out of %s questions, or %.1f%%", 
				correctAnswers, questionNumber, 
				((double)correctAnswers/(double)questionNumber)*100);
		System.out.println();

	}
	
	public static int getRandom(ArrayList<Integer> unusedList) {
		
		//returns a random index of the data set that hasn't been used before
		//note that the data set is not the same as the unusedList.  unusedList
		//keeps track of all of the states/countries that have NOT been used
		
		int result;
		Random r = new Random();
		int index = 0;
		
		//if the unused list has multiple values left, choose one of those randomly.  
		if (unusedList.size() > 1)  
			index = r.nextInt(unusedList.size()-1);
		else {  //if the list has one value left, use that value
			index = 0;
		}
		result = unusedList.get(index);  //set the result as the index that was randomly picked
		unusedList.remove(index);  //remove this dataset index from unusedList, so it isn't pointed to again
		return result;
	}
	

}
