package com.imaniac.tictactoe;

/* TicTacToeConsole.java
 * Origionally developed Frank McCown (Harding University)
 * Modified by William Labanowski to provide the logic for Tic Tac Toe on Android
 * Logic for a tic tac toe game.
 */

import java.util.Random;

import android.util.Log;

public class MultiTicTacToeGame {

	private static final String TAG = "TicTacToeGame";

	private final char mBoard[];
	public static final int BOARD_SIZE = 9;

	public static final char HUMAN_PLAYER_1 = 'X';
	public static final char HUMAN_PLAYER_2 = 'O';
	public static final char OPEN_SPOT = ' ';

	//private Random mRand;

	// comps difficulty levels
	public enum DifficultyLevel {
		Easy, Harder, Expert
	};

	// current difficulty level
	private DifficultyLevel mDifficultyLevel = DifficultyLevel.Expert;
	
	public MultiTicTacToeGame() {
		// Seed the random number generator
	

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
		if (!(player == HUMAN_PLAYER_1 || player == HUMAN_PLAYER_2))
			throw new IllegalArgumentException("player must be " + HUMAN_PLAYER_1
					+ " or " + HUMAN_PLAYER_2 + ". " + player);
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
			if (mBoard[i] == HUMAN_PLAYER_1 && mBoard[i + 1] == HUMAN_PLAYER_1
					&& mBoard[i + 2] == HUMAN_PLAYER_1)
				return 2;
			if (mBoard[i] == HUMAN_PLAYER_2
					&& mBoard[i + 1] == HUMAN_PLAYER_2
					&& mBoard[i + 2] == HUMAN_PLAYER_2)
				return 3;
		}

		// Check vertical wins
		for (int i = 0; i <= 2; i++) {
			if (mBoard[i] == HUMAN_PLAYER_1 && mBoard[i + 3] == HUMAN_PLAYER_1
					&& mBoard[i + 6] == HUMAN_PLAYER_1)
				return 2;
			if (mBoard[i] == HUMAN_PLAYER_2
					&& mBoard[i + 3] == HUMAN_PLAYER_2
					&& mBoard[i + 6] == HUMAN_PLAYER_2)
				return 3;
		}

		// Check for diagonal wins
		if ((mBoard[0] == HUMAN_PLAYER_1 && mBoard[4] == HUMAN_PLAYER_1 && mBoard[8] == HUMAN_PLAYER_1)
				|| (mBoard[2] == HUMAN_PLAYER_1 && mBoard[4] == HUMAN_PLAYER_1 && mBoard[6] == HUMAN_PLAYER_1))
			return 2;
		if ((mBoard[0] == HUMAN_PLAYER_2 && mBoard[4] == HUMAN_PLAYER_2 && mBoard[8] == HUMAN_PLAYER_2)
				|| (mBoard[2] == HUMAN_PLAYER_2
						&& mBoard[4] == HUMAN_PLAYER_2 && mBoard[6] == HUMAN_PLAYER_2))
			return 3;

		// Check for tie
		for (int i = 0; i < BOARD_SIZE; i++) {
			// If we find a number, then no one has won yet
			if (mBoard[i] != HUMAN_PLAYER_1 && mBoard[i] != HUMAN_PLAYER_2)
				return 0;
		}

		// If we make it through the previous loop, all places are taken, so
		// it's a tie
		return 1;
	}



	
	//called by the main activity
	public DifficultyLevel getDifficultyLevel() {
		return mDifficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		mDifficultyLevel = difficultyLevel;
	}

}
