package com.imaniac.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MenuActivity extends Activity {
	Spinner game_type,difficulty_type;
	String game[]={"1 Player","2 Players"};
	String difficulties[]={"Easy","Medium","Difficult"};
	int arg1,arg2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		game_type=(Spinner)findViewById(R.id.spinner1);
		difficulty_type=(Spinner)findViewById(R.id.spinner2);
		game_type.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,game));
		difficulty_type.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,difficulties));
		game_type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				arg1=position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		difficulty_type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				arg2=position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button play=(Button)findViewById(R.id.button1);
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
