package com.imaniac.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.rey.material.widget.Button;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.Spinner.OnItemSelectedListener;

public class MenuActivity extends Activity {
	Spinner game_type,difficulty_type;
	String game[]={"1 Player","2 Players"};
	String difficulties[]={"Easy","Medium","Difficult"};
	int arg1,arg2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		game_type=(Spinner)findViewById(R.id.spinner_players);
		difficulty_type=(Spinner)findViewById(R.id.spinner_difficulty);
		ArrayAdapter<String> game_adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spn,game);
		game_type.setAdapter(game_adapter);
		game_adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
		ArrayAdapter<String> difficulty_adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spn,difficulties);
		difficulty_type.setAdapter(difficulty_adapter);
		difficulty_adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
		game_type.setOnItemSelectedListener(new OnItemSelectedListener() {

			

			@Override
			public void onItemSelected(Spinner parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				arg1=position;
			}
		});
		difficulty_type.setOnItemSelectedListener(new OnItemSelectedListener() {

		
			@Override
			public void onItemSelected(Spinner parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				arg2=position;
			}
		});
		
		Button play=(Button)findViewById(R.id.button_play);
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = null;
				if(arg1==0)
					{
						i = new Intent(MenuActivity.this,AndroidTicTacToe.class);
					}
				else
					i = new Intent(MenuActivity.this,MultiPlayerActivity.class);
					
				i.putExtra("difficulty", arg2);
				startActivity(i);
				finish();
			}
		});
	}

	
}
