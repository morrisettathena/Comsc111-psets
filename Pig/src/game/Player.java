package game;

/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Aug 29, 2019
 */


public abstract class Player {
	protected String name;
	protected int gameTotal;
	protected int roundTotal;
	
	Player(){
		gameTotal = 0;
		roundTotal = 0;
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getRoundTotal() {
		return roundTotal;
	}


	public int getGameTotal() {
		return gameTotal;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * Abstract method to be implemented by each Player class.  Returns true if the player wishes to roll
	 * again, false otherwise.
	 */
	public abstract boolean isRolling();


	
	public boolean isWinner() {
		return gameTotal >= Game.WIN_SCORE;
	}

	public void resetGameTotal() {
		gameTotal = 0;
		
	}

	public void resetRoundTotal() {
		roundTotal = 0;
		
	}

	public void addToTotal(int sum) {
		roundTotal +=sum;
		
	}

	public void endTurn() {
		gameTotal += roundTotal;
		roundTotal = 0;
		
	}



	
}
