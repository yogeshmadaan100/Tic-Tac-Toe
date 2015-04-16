package com.imaniac.tictactoe;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.imaniac.tictactoe.TicTacToeGame.DifficultyLevel;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;

public class AndroidTicTacToe extends ActionBarActivity {

	private static final String TAG = "AndroidTicTacToe";

	static final int DIALOG_DIFFICULTY_ID = 0;
	static final int DIALOG_QUIT_ID = 1;

	private static final int[] BUTTON_IDS = { R.id.one, R.id.two, R.id.three,
		R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine };

	// Whose turn to go first
	private char mTurn = TicTacToeGame.COMPUTER_PLAYER;

	// Keep track of wins
	private int mHumanWins = 0;
	private int mComputerWins = 0;
	private int mTies = 0;

	// game logic
	private TicTacToeGame mGame;

	// Buttons making up the board
	private Button mBoardButtons[];

	// Various text displayed
	private TextView mInfoTextView;
	private TextView mHumanScoreTextView;
	private TextView mComputerScoreTextView;
	private TextView mTieScoreTextView;

	private boolean mGameOver;

	/** Animation Variables */
	ValueAnimator vanim,vanim2,vanim3,vanim4;
	View line1,line2,line3,line4;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//getSupportActionBar().setDisplayShowTitleEnabled(false);
		Bundle b =getIntent().getExtras();
		int dif=b.getInt("difficulty");
		
		mBoardButtons = new Button[TicTacToeGame.BOARD_SIZE];
		for (int i = 0; i < mBoardButtons.length; i++)
			mBoardButtons[i] = (Button) findViewById(BUTTON_IDS[i]);
		//getlines
		line1=(View)findViewById(R.id.line1);
		line2=(View)findViewById(R.id.line2);
		line3=(View)findViewById(R.id.line3);
		line4=(View)findViewById(R.id.line4);
		vanim= ValueAnimator.ofInt(0,900);
		vanim.setDuration(1000);
		vanim2= ValueAnimator.ofInt(0,900);
		vanim2.setDuration(1000);
		vanim3= ValueAnimator.ofInt(0,900);
		vanim3.setDuration(1000);
		vanim4= ValueAnimator.ofInt(0,900);
		vanim4.setDuration(1000);
		vanim.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				Log.e("animation", "end");
				vanim2.start();
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vanim.addUpdateListener(new AnimatorUpdateListener() {

		        @Override
		        public void onAnimationUpdate(ValueAnimator animation) {
		            int animProgress = (Integer) animation.getAnimatedValue();
		           // Log.e("update", ""+animProgress);
		            android.widget.LinearLayout.LayoutParams  layoutParams = (android.widget.LinearLayout.LayoutParams) line1.getLayoutParams();
			             layoutParams.height = (int) (animProgress);
			             Log.e("height", ""+layoutParams.height);
		            line1.setLayoutParams(layoutParams);
		        }
		    });
		vanim.start();
		vanim2.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				Log.e("animation", "end");
			vanim3.start();
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vanim2.addUpdateListener(new AnimatorUpdateListener() {

		        @Override
		        public void onAnimationUpdate(ValueAnimator animation) {
		            int animProgress = (Integer) animation.getAnimatedValue();
		           // Log.e("update", ""+animProgress);
		            android.widget.LinearLayout.LayoutParams  layoutParams = (android.widget.LinearLayout.LayoutParams) line2.getLayoutParams();
			             layoutParams.height = (int) (animProgress);
			             Log.e("height", ""+layoutParams.height);
		            line2.setLayoutParams(layoutParams);
		        }
		    });
		
	vanim3.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				Log.e("animation", "end");
				vanim4.start();
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vanim3.addUpdateListener(new AnimatorUpdateListener() {

		        @Override
		        public void onAnimationUpdate(ValueAnimator animation) {
		            int animProgress = (Integer) animation.getAnimatedValue();
		           // Log.e("update", ""+animProgress);
		            android.widget.LinearLayout.LayoutParams  layoutParams = (android.widget.LinearLayout.LayoutParams) line3.getLayoutParams();
			             layoutParams.width = (int) (animProgress);
			             Log.e("height", ""+layoutParams.width);
		            line3.setLayoutParams(layoutParams);
		        }
		    });
		
		vanim4.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				Log.e("animation", "end");
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
		
		vanim4.addUpdateListener(new AnimatorUpdateListener() {

		        @Override
		        public void onAnimationUpdate(ValueAnimator animation) {
		            int animProgress = (Integer) animation.getAnimatedValue();
		           // Log.e("update", ""+animProgress);
		            android.widget.LinearLayout.LayoutParams  layoutParams = (android.widget.LinearLayout.LayoutParams) line4.getLayoutParams();
			             layoutParams.width = (int) (animProgress);
			             Log.e("height", ""+layoutParams.width);
		            line4.setLayoutParams(layoutParams);
		        }
		    });
		// get the TextViews
		mInfoTextView = (TextView) findViewById(R.id.information);
		mHumanScoreTextView = (TextView) findViewById(R.id.player_score);
		mComputerScoreTextView = (TextView) findViewById(R.id.computer_score);
		mTieScoreTextView = (TextView) findViewById(R.id.tie_score);
		mGame = new TicTacToeGame();
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
		if (mTurn == TicTacToeGame.HUMAN_PLAYER) {
			mTurn = TicTacToeGame.COMPUTER_PLAYER;
			mInfoTextView.setText(R.string.first_computer);
			int move = mGame.getComputerMove();
			Log.e("position", ""+move);
			setMove(TicTacToeGame.COMPUTER_PLAYER, move);
			mInfoTextView.setText(R.string.turn_human);
		} else {
			mTurn = TicTacToeGame.HUMAN_PLAYER;
			mInfoTextView.setText(R.string.first_human);
		}
	}

	private void setMove(char player, int location) {
		mGame.setMove(player, location);
		mBoardButtons[location].setEnabled(false);
		mBoardButtons[location].setText(String.valueOf(player));
		if (player == TicTacToeGame.HUMAN_PLAYER)
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
				setMove(TicTacToeGame.HUMAN_PLAYER, location);

				// If no winner yet, let the <Mike's version> computer make a
				// move
				int winner = mGame.checkForWinner();
				if (winner == 0) {
					mInfoTextView.setText(R.string.turn_computer);
					int move = mGame.getComputerMove();
					Log.e("position", ""+move);
					setMove(TicTacToeGame.COMPUTER_PLAYER, move);
					winner = mGame.checkForWinner();
				}

				if (winner == 0)
					mInfoTextView.setText(R.string.turn_human);
				else {
					if (winner == 1) {
						mInfoTextView.setText(R.string.result_tie);
						mTies++;
						mTieScoreTextView.setText(Integer.toString(mTies));
					} else if (winner == 2) {
						mHumanWins++;
						mHumanScoreTextView.setText(Integer
								.toString(mHumanWins));
						mInfoTextView.setText(R.string.result_human_wins);
					} else {
						mComputerWins++;
						mComputerScoreTextView.setText(Integer
								.toString(mComputerWins));
						mInfoTextView.setText(R.string.result_computer_wins);
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
									AndroidTicTacToe.this.finish();
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
		Intent i=new Intent(AndroidTicTacToe.this,MenuActivity.class);
		startActivity(i);
		finish();
	}
		
	}
