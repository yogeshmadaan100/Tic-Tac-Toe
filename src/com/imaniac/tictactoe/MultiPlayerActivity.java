package com.imaniac.tictactoe;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
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
	//fonts
	Typeface arial,times;
	// Various text displayed
	private TextView mInfoTextView;
	private TextView mHumanScoreTextView;
	private TextView mComputerScoreTextView;
	private TextView mTieScoreTextView;

	private boolean mGameOver;
	ValueAnimator vanim,vanim2,vanim3,vanim4;
	View line1,line2,line3,line4;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4169e1")));
		
		Bundle b =getIntent().getExtras();
		int dif=b.getInt("difficulty");
		
		mBoardButtons = new Button[TicTacToeGame.BOARD_SIZE];
		for (int i = 0; i < mBoardButtons.length; i++)
			mBoardButtons[i] = (Button) findViewById(BUTTON_IDS[i]);
		TextView computer_Name=(TextView)findViewById(R.id.computer_score_label);
		computer_Name.setText("Player 2");
		TextView computer_Name1=(TextView)findViewById(R.id.player_score_label);
		computer_Name1.setText("Player 1");
		line1=(View)findViewById(R.id.line1);
		line2=(View)findViewById(R.id.line2);
		line3=(View)findViewById(R.id.line3);
		line4=(View)findViewById(R.id.line4);
		vanim= ValueAnimator.ofInt(0,900);
		vanim.setDuration(700);
		vanim2= ValueAnimator.ofInt(0,900);
		vanim2.setDuration(700);
		vanim3= ValueAnimator.ofInt(0,900);
		vanim3.setDuration(700);
		vanim4= ValueAnimator.ofInt(0,900);
		vanim4.setDuration(700);
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
		arial=Typeface.createFromAsset(getAssets(), "fonts/arial.ttf");
		times=Typeface.createFromAsset(getAssets(), "fonts/times.ttf");
		mInfoTextView.setTypeface(arial);
		mHumanScoreTextView.setTypeface(times);
		mComputerScoreTextView.setTypeface(times);
		mTieScoreTextView.setTypeface(times);
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
		new MaterialDialog.Builder(this)
        .title("Game Over")
        .content(""+mInfoTextView.getText().toString())
        .positiveText("Play Again")
        .negativeText("Exit")
        .titleGravity(GravityEnum.CENTER)
        .contentColorRes(android.R.color.white)
        .dividerColor(R.color.material_blue)
        .backgroundColorRes(R.color.material_blue_grey_800)
         .titleColorRes(R.color.material_blue)
         .theme(com.afollestad.materialdialogs.Theme.LIGHT)
        .dividerColorRes(R.color.material_blue)
         .positiveColor(Color.WHITE)
        .negativeColorAttr(android.R.attr.textColorSecondaryInverse)
        .callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
            	
            	 startNewGame();
             	  
            }

            @Override
            public void onNeutral(MaterialDialog dialog) {
                Toast.makeText(getApplicationContext(), "Neutral", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegative(MaterialDialog dialog) {
            	onBackPressed();
            }
        })
        .show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		Log.d(TAG, "In onCreateOptionsMenu. What is up?");
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_restart:
			startNewGame();
			return true;
		case R.id.action_share:
					 Intent sendIntent = new Intent();
			      	 sendIntent.setAction(Intent.ACTION_SEND);
			       	 sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi I am using Tic Tac Toe - Fun Reloaded .You must also try https://play.google.com/store/apps/details?id=com.imaniac.tictactoe");
			       	 sendIntent.setType("text/plain");
			       	 startActivity(sendIntent);
			return true;
		
		}
		return false;
	}

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
