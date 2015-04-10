package com.imaniac.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.imaniac.tictactoe.MultiTicTacToeGame.DifficultyLevel;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;

public class MultiPlayerActivity extends ActionBarActivity {

	private static final String TAG = "AndroidTicTacToe";

	static final int DIALOG_DIFFICULTY_ID = 0;
	static final int DIALOG_QUIT_ID = 1;

	private static final int[] BUTTON_IDS = { R.id.one, R.id.two, R.id.three,
		R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine };

	// Whose turn to go first
	private char mTurn = MultiTicTacToeGame.HUMAN_PLAYER_2;

	// Keep track of wins
	private int mHumanWins = 0;
	private int mComputerWins = 0;
	private int mTies = 0;

	// game logic
	private MultiTicTacToeGame mGame;

	// Buttons making up the board
	private Button mBoardButtons[];

	// Various text displayed
	private TextView mInfoTextView;
	private TextView mHumanScoreTextView;
	private TextView mComputerScoreTextView;
	private TextView mTieScoreTextView;

	private boolean mGameOver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		Bundle b =getIntent().getExtras();
		int dif=b.getInt("difficulty");
		
		mBoardButtons = new Button[TicTacToeGame.BOARD_SIZE];
		for (int i = 0; i < mBoardButtons.length; i++)
			mBoardButtons[i] = (Button) findViewById(BUTTON_IDS[i]);
		TextView computer_Name=(TextView)findViewById(R.id.computer_score_label);
		computer_Name.setText("Player 2");
		TextView computer_Name1=(TextView)findViewById(R.id.player_score_label);
		computer_Name1.setText("Player 1");
		// get the TextViews
		mInfoTextView = (TextView) findViewById(R.id.information);
		mHumanScoreTextView = (TextView) findViewById(R.id.player_score);
		mComputerScoreTextView = (TextView) findViewById(R.id.computer_score);
		mTieScoreTextView = (TextView) findViewById(R.id.tie_score);
		mGame = new MultiTicTacToeGame();
		switch (dif) {
		case 0:
			mGame.setDifficultyLevel(DifficultyLevel.Easy);
			break;
		case 1:
			mGame.setDifficultyLevel(DifficultyLevel.Harder);
			break;
			
		case 2:
			mGame.setDifficultyLevel(DifficultyLevel.Expert);
			break;
	

		default:
			break;
		}
		startNewGame();
	}

	public void startNewGamePressed(View v) {
		startNewGame();
	}

	// Set up the game baord.
	private void startNewGame() {
		mGameOver = false;

		mGame.clearBoard();

		// Reset all buttons
		for (int i = 0; i < mBoardButtons.length; i++) {
			mBoardButtons[i].setText("");
			mBoardButtons[i].setEnabled(true);
			mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
		}

		// Alternate who goes first
		if (mTurn == MultiTicTacToeGame.HUMAN_PLAYER_1) {
			mTurn = MultiTicTacToeGame.HUMAN_PLAYER_2;
			mInfoTextView.setText(R.string.turn_human2);
			//int move = mGame.getComputerMove();
			//setMove(MultiTicTacToeGame.HUMAN_PLAYER_2, move);
			
		} else {
			mTurn = MultiTicTacToeGame.HUMAN_PLAYER_1;
			mInfoTextView.setText(R.string.turn_human1);
		}
	}

	private void setMove(char player, int location) {
		mGame.setMove(player, location);
		mBoardButtons[location].setEnabled(false);
		mBoardButtons[location].setText(String.valueOf(player));
		if (player == MultiTicTacToeGame.HUMAN_PLAYER_1)
			mBoardButtons[location].setTextColor(Color.rgb(0, 200, 0));
		else
			mBoardButtons[location].setTextColor(Color.rgb(200, 0, 0));
	}

	// when game is over, disable all buttons and set flag
	private void gameOver() {
		mGameOver = true;
		for (int i = 0; i < mBoardButtons.length; i++)
			mBoardButtons[i].setEnabled(false);
		
		com.rey.material.app.Dialog.Builder builder=null;
		  builder = new SimpleDialog.Builder(R.style.SimpleDialog){
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {
                startNewGame();
          	  fragment.dismiss();
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
                
          	  fragment.dismiss();
          	  onBackPressed();
            }
        };

        ((SimpleDialog.Builder)builder).message(""+mInfoTextView.getText().toString())
                .positiveAction("Play Again")
                .negativeAction("Exit");
        
        DialogFragment fragment=DialogFragment.newInstance(builder);
        fragment.show(getSupportFragmentManager(), null);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		Log.d(TAG, "In onCreateOptionsMenu. What is up?");
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_game:
			startNewGame();
			return true;
		case R.id.ai_difficulty:
			showDialog(DIALOG_DIFFICULTY_ID);
			return true;
		case R.id.quit:
			showDialog(DIALOG_QUIT_ID);
			return true;
		}
		return false;
	}*/

	// Handles clicks on the game baord buttons
	private class ButtonClickListener implements View.OnClickListener {
		int location;

		public ButtonClickListener(int location) {
			this.location = location;
		}

		@Override
		public void onClick(View view) {
			if (!mGameOver && mBoardButtons[location].isEnabled()) {
				setMove(mTurn, location);
				if (mTurn == MultiTicTacToeGame.HUMAN_PLAYER_1) {
					mTurn = MultiTicTacToeGame.HUMAN_PLAYER_2;
					mInfoTextView.setText("Player 1 goes first");
					//int move = mGame.getComputerMove();
					//setMove(MultiTicTacToeGame.HUMAN_PLAYER_2, move);
					mInfoTextView.setText(R.string.turn_human2);
				} else {
					mTurn = MultiTicTacToeGame.HUMAN_PLAYER_1;
					mInfoTextView.setText(R.string.turn_human1);
				}
				// If no winner yet, let the <Mike's version> computer make a
				// move
				int winner = mGame.checkForWinner();
				if (winner == 0) {
					//mInfoTextView.setText(R.string.turn_human2);
				//	int move = mGame.getComputerMove();
					//setMove(MultiTicTacToeGame.HUMAN_PLAYER_2, move);
					//winner = mGame.checkForWinner();
				}

				if (winner == 0)
					{
					//mInfoTextView.setText(R.string.turn_human1);
					}
				else {
					if (winner == 1) {
						mInfoTextView.setText(R.string.result_tie);
						mTies++;
						mTieScoreTextView.setText(Integer.toString(mTies));
					} else if (winner == 2) {
						mHumanWins++;
						mHumanScoreTextView.setText(Integer
								.toString(mHumanWins));
						mInfoTextView.setText("Player 1 Wins");
					} else {
						mComputerWins++;
						mComputerScoreTextView.setText(Integer
								.toString(mComputerWins));
						mInfoTextView.setText("Player 2 Wins");
					}
					gameOver();
				}
			}
		}
	}

	//Everything below this is used to create / implement my menu bar options
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id) {
		case DIALOG_DIFFICULTY_ID:
			builder.setTitle(R.string.difficulty_choose);
			final CharSequence[] levels = {
					getResources().getString(R.string.difficulty_easy),
					getResources().getString(R.string.difficulty_harder),
					getResources().getString(R.string.difficulty_expert) };
			// Set selected, an integer (0 to n-1), for the Difficulty
			//have to convert to int (.ordinal)
			int selected=mGame.getDifficultyLevel().ordinal();
			// dialog selected is the radio button that should be selected.
			builder.setSingleChoiceItems(levels, selected,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {
							dialog.dismiss(); // Close dialog

							// Set the diff level of mGame based on which
							// item was selected.
							DifficultyLevel dl=DifficultyLevel.values()[item];
							mGame.setDifficultyLevel(dl);

							// Display the selected difficulty level
							Toast.makeText(getApplicationContext(),
									levels[item], Toast.LENGTH_SHORT).show();
						}
					});
			dialog = builder.create();

			break;
		case DIALOG_QUIT_ID:
			// Create the quit confirmation dialog

			builder.setMessage(R.string.quit_question)
					.setCancelable(false)
					.setPositiveButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									MultiPlayerActivity.this.finish();
								}
							}).setNegativeButton(R.string.no, null);
			dialog = builder.create();

			break;
		}
		return dialog;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i=new Intent(MultiPlayerActivity.this,MenuActivity.class);
		startActivity(i);
		finish();
	}
		
	}
