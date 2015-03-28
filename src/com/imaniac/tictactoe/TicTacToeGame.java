package com.imaniac.tictactoe;

/* TicTacToeConsole.java
 * Origionally developed Frank McCown (Harding University)
 * Modified by William Labanowski to provide the logic for Tic Tac Toe on Android
 * Logic for a tic tac toe game.
 */

import java.util.Random;

import android.util.Log;

public class TicTacToeGame {

	private static final String TAG = "TicTacToeGame";

	private final char mBoard[];
	public static final int BOARD_SIZE = 9;

	public static final char HUMAN_PLAYER = 'X';
	public static final char COMPUTER_PLAYER = 'O';
	public static final char OPEN_SPOT = ' ';

	
	
	private int iCoordinate; //final i coordinate for AI choice
	private int jCoordinate;
	private Random mRand;

	// comps difficulty levels
	public enum DifficultyLevel {
		Easy, Harder, Expert
	};

	// current difficulty level
	private DifficultyLevel mDifficultyLevel = DifficultyLevel.Expert;
	
	public TicTacToeGame() {
		// Seed the random number generator
		mRand = new Random();

		// fill the board
		mBoard = new char[BOARD_SIZE];
		clearBoard();
	}

	/** Clear the board of all X's and O's by setting all spots to OPEN_SPOT. */
	public void clearBoard() {
		for (int i = 0; i < mBoard.length; i++)
			mBoard[i] = OPEN_SPOT;
	}

	/**
	 * Set the given player at the given location on the game board. The
	 * location must be available, or the board will not be changed.
	 * 
	 * @param player
	 *            - The HUMAN_PLAYER or COMPUTER_PLAYER
	 * @param location
	 *            - The location (0-8) to place the move
	 */
	public void setMove(char player, int location) {
		if (location < 0 || location >= BOARD_SIZE)
			throw new IllegalArgumentException(
					"location must be between 0 and 8 inclusive: " + location);
		if (!(player == HUMAN_PLAYER || player == COMPUTER_PLAYER))
			throw new IllegalArgumentException("player must be " + HUMAN_PLAYER
					+ " or " + COMPUTER_PLAYER + ". " + player);
		mBoard[location] = player;
	}

	private void displayBoard() {
		System.out.println();
		System.out.println(mBoard[0] + " | " + mBoard[1] + " | " + mBoard[2]);
		System.out.println("-----------");
		System.out.println(mBoard[3] + " | " + mBoard[4] + " | " + mBoard[5]);
		System.out.println("-----------");
		System.out.println(mBoard[6] + " | " + mBoard[7] + " | " + mBoard[8]);
		System.out.println();
	}

	// Check for a winner. Return
	// 0 if no winner or tie yet
	// 1 if it's a tie
	// 2 if X won
	// 3 if O won
	public int checkForWinner() {

		// Check horizontal wins
		for (int i = 0; i <= 6; i += 3) {
			if (mBoard[i] == HUMAN_PLAYER && mBoard[i + 1] == HUMAN_PLAYER
					&& mBoard[i + 2] == HUMAN_PLAYER)
				return 2;
			if (mBoard[i] == COMPUTER_PLAYER
					&& mBoard[i + 1] == COMPUTER_PLAYER
					&& mBoard[i + 2] == COMPUTER_PLAYER)
				return 3;
		}

		// Check vertical wins
		for (int i = 0; i <= 2; i++) {
			if (mBoard[i] == HUMAN_PLAYER && mBoard[i + 3] == HUMAN_PLAYER
					&& mBoard[i + 6] == HUMAN_PLAYER)
				return 2;
			if (mBoard[i] == COMPUTER_PLAYER
					&& mBoard[i + 3] == COMPUTER_PLAYER
					&& mBoard[i + 6] == COMPUTER_PLAYER)
				return 3;
		}

		// Check for diagonal wins
		if ((mBoard[0] == HUMAN_PLAYER && mBoard[4] == HUMAN_PLAYER && mBoard[8] == HUMAN_PLAYER)
				|| (mBoard[2] == HUMAN_PLAYER && mBoard[4] == HUMAN_PLAYER && mBoard[6] == HUMAN_PLAYER))
			return 2;
		if ((mBoard[0] == COMPUTER_PLAYER && mBoard[4] == COMPUTER_PLAYER && mBoard[8] == COMPUTER_PLAYER)
				|| (mBoard[2] == COMPUTER_PLAYER
						&& mBoard[4] == COMPUTER_PLAYER && mBoard[6] == COMPUTER_PLAYER))
			return 3;

		// Check for tie
		for (int i = 0; i < BOARD_SIZE; i++) {
			// If we find a number, then no one has won yet
			if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER)
				return 0;
		}

		// If we make it through the previous loop, all places are taken, so
		// it's a tie
		return 1;
	}

	// get a random move. used within the get computer move method
	public int getRandomMove() {
		int move;
		do {
			move = mRand.nextInt(BOARD_SIZE);
		} while (mBoard[move] != OPEN_SPOT);

		Log.d(TAG, "Computer moving to " + move + " as a random move.");

		return move;
	}

	
	public int getWinningMove() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (mBoard[i] == OPEN_SPOT) {
				mBoard[i] = COMPUTER_PLAYER;
				if (checkForWinner() == 3) {
					Log.d(TAG, "Computer moving to " + i + " to win.");
					return i;
				} else
					mBoard[i] = OPEN_SPOT;
			}
		}
		return -1;
	}

	//block the user from winning
	public int getBlockingMove() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (mBoard[i] == OPEN_SPOT) {
				mBoard[i] = HUMAN_PLAYER;
				if (checkForWinner() == 2) {
					mBoard[i] = COMPUTER_PLAYER;
					Log.d(TAG, "Computer moving to " + i + " to block win.");
					return i;
				} else
					mBoard[i] = OPEN_SPOT;
			}
		}
		return -1;
	}
	
	//this uses the difficulty level to determine what algorithm the computer will use
	public int getComputerMove() {
		int move = -1;

		// EASY difficulty, just do a random move
		if (mDifficultyLevel == DifficultyLevel.Easy) {
			move = getRandomMove();
		}

		// harder difficulty, try to win then do random
		else if (mDifficultyLevel == DifficultyLevel.Harder) {
			move = getWinningMove();
			if (move == -1) {
				move = getRandomMove();
			}
		}

		// expert, try to win then block then random
		else if (mDifficultyLevel == DifficultyLevel.Expert) {
			
			move = getWinningMove();
			if (move == -1) {
				move = getBlockingMove();
			}
			if (move == -1) {
				move = getRandomMove();
			}
		}

		// return the move
		return move;

	}
	
	//called by the main activity
	public DifficultyLevel getDifficultyLevel() {
		return mDifficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		mDifficultyLevel = difficultyLevel;
		Log.e("difficulty set", ""+difficultyLevel);
	}
	
	
	
public int getMin(int[] board, int depth){
		
		//check game state to determine if at end of recursion
		int val = checkGameState(board);
		if (val != 2) { return val;}
		
		int min = Integer.MAX_VALUE; //set arbitrarily large min value
		int score;
		
		//iterate through all possible options
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				
				//determine if spot is open
				if(board[3*i+j] == 0){
					
					//if spot is open, temporarily choose it
					board[3*i+j] = -1;
					
					//recursively get score of that choice
					score = getMax(board, depth+1);
					
					/*if score is less than current min set
					  min to score*/
					if (score < min){
						min = score;
						
						/*if at top depth in recursion get i,j
						 coordinates */
						if (depth == 0){
							iCoordinate = i;
							jCoordinate = j;
						}
					}
					
					//remove temporary choice
					board[3*i+j] = 0;
				}
			} //end of inner loops
		} //end of outer for loop
		
		//return lowest available score
		return min;
	}
	
	public int getMax(int[] board, int depth){
		
		//check game state to determine if at end of recursion
		int val = checkGameState(board);
		if (val != 2) { return val; } 
		
		
		int max = Integer.MIN_VALUE; //create arbitrarily small max;
		int score;
		
		//iterate over all options
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				
				//determine if spot is open
				if(board[3*i+j] == 0){
					
					//if spot is open, temporarily choose it
					board[3*i+j] = 1;
					
					//recursively get score of that choice
					score = getMin(board, depth+1);
	
					/*if that score is greater than current max
						set max to the score */
					if (score > max){
						max = score;
						
						/*if the recursion level is at its highest
						 * depth, get the i, j coordinate for max choice */ 
						if (depth == 0){
							iCoordinate = i;
							jCoordinate = j;
						}	
					}
					
					//remove temporary choice
					board[3*i+j] = 0;
				}
			} // end of inner for loop
		} //end of outer for loop
		
		//return the maximum score of available choices
		return max;
	}
		
	public int checkGameState(int[] board){
		/*check status of game, namely if someone has won */
		
		//0 no winner, game is draw
		//1 is computer winner
		//-1 is human winner
		//2 no winner, continue game
		
		//check rows
		

		// Check horizontal wins
		for (int i = 0; i <= 6; i += 3) {
			if (mBoard[i] == HUMAN_PLAYER && mBoard[i + 1] == HUMAN_PLAYER
					&& mBoard[i + 2] == HUMAN_PLAYER)
				return -1;
			if (mBoard[i] == COMPUTER_PLAYER
					&& mBoard[i + 1] == COMPUTER_PLAYER
					&& mBoard[i + 2] == COMPUTER_PLAYER)
				return 1;
		}

		// Check vertical wins
		for (int i = 0; i <= 2; i++) {
			if (mBoard[i] == HUMAN_PLAYER && mBoard[i + 3] == HUMAN_PLAYER
					&& mBoard[i + 6] == HUMAN_PLAYER)
				return -1;
			if (mBoard[i] == COMPUTER_PLAYER
					&& mBoard[i + 3] == COMPUTER_PLAYER
					&& mBoard[i + 6] == COMPUTER_PLAYER)
				return 1;
		}

		// Check for diagonal wins
		if ((mBoard[0] == HUMAN_PLAYER && mBoard[4] == HUMAN_PLAYER && mBoard[8] == HUMAN_PLAYER)
				|| (mBoard[2] == HUMAN_PLAYER && mBoard[4] == HUMAN_PLAYER && mBoard[6] == HUMAN_PLAYER))
			return -1;
		if ((mBoard[0] == COMPUTER_PLAYER && mBoard[4] == COMPUTER_PLAYER && mBoard[8] == COMPUTER_PLAYER)
				|| (mBoard[2] == COMPUTER_PLAYER
						&& mBoard[4] == COMPUTER_PLAYER && mBoard[6] == COMPUTER_PLAYER))
			return 1;

		// Check for tie
		for (int i = 0; i < BOARD_SIZE; i++) {
			// If we find a number, then no one has won yet
			if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER)
				return 2;
		}

		// If we make it through the previous loop, all places are taken, so
		// it's a tie
		return 0;
		
		
	}
	
	

}
