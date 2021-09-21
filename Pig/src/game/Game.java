package game;

/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Aug 29, 2019
 */

/**
 * Game class contains two Players and a PairOfDice. Method play contains the game loop.
 */
public class Game {
	private int currentPlayerIdx;
	private Player currentPlayer;
	private PairOfDice dice;
	private Player[] players = new Player[2];
	
	//Set the score to win the game
	public static final int WIN_SCORE = 100;
	
	/**
	 * Set up a new game.  Player p0 will move first.
	 */
	Game(Player p0, Player p1){
		dice = new PairOfDice();
		
		players[0] = p0;
		players[1] = p1;
		
		currentPlayerIdx = 0;
		currentPlayer = players[currentPlayerIdx];
	}
	
	/**
	 * Main game loop.  While their is still a possible move, get the move from the current player, make sure 
	 * it is valid, and update the board.  Display the winner after the board is full.
	 */
	public void play() {
		while(!haveWinner()) {
			displayGameState();		
			if(currentPlayer.isRolling()) {//if the current player does roll again
				dice.roll();
				System.out.println(currentPlayer.getName() + " rolls " + dice.toString());
				if (dice.hasSnakeEyes()) {//rolled two ones
					currentPlayer.resetRoundTotal();
					currentPlayer.resetGameTotal();
					takeTurn();
				}else if (dice.hasOne()) {//rolled one one
					currentPlayer.resetRoundTotal();
					takeTurn();
				}else {//rolled no ones
					currentPlayer.addToTotal(dice.getSum());
				}	
			}else { //if the currentPlayer does not roll again
				System.out.println(currentPlayer.getName() + " ends turn");
				currentPlayer.endTurn();
				takeTurn();
			}
		}
		if(currentPlayer.isWinner()) {
			System.out.println(currentPlayer.getName() + " wins");
		}else {
			takeTurn();
			System.out.println(currentPlayer.getName() + " wins");

		}

	}

	/**
	 * Return true if the game has been won, false otherwise
	 */
	private boolean haveWinner() {
		return players[0].isWinner() || players[1].isWinner();
	}

	/**
	 * Print the state of the game to the console
	 */
	private void displayGameState() {
		System.out.println("************************");
		for (int i = 0; i<2; i++) {
			System.out.println(players[i].getName() + " totals are:");
			if (players[i] instanceof ComputerPlayer) {
				System.out.println("\tBehavior:  " + ((ComputerPlayer) players[i]).getAI());
			}
			System.out.println("\tRound Total: "+ players[i].getRoundTotal());
			System.out.println("\tGame total: "+ players[i].getGameTotal());	
		}
		System.out.println("Current Player is "+ currentPlayer.getName());
		System.out.println("************************");
	}

	/**
	 * Switch the value of currentPlayer to the other player.
	 */
	private void takeTurn() {
		currentPlayerIdx = (currentPlayerIdx + 1) % 2;
		currentPlayer = players[currentPlayerIdx];
		
	}
	


}
