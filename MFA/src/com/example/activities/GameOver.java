package com.example.activities;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;

public class GameOver extends Activity 
{
	TextView score, killed, shots, accuracy, time;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game_over);
		
		score = (TextView) findViewById(R.id.textView7);
	    score.setText("" + MGP.score);
	    
	    killed = (TextView) findViewById(R.id.textView8);
	    killed.setText("" + MGP.totalAsteroidsKilled);
	    
	    shots = (TextView) findViewById(R.id.textView9);
	    shots.setText("" + MGP.countShots);
	    
	    accuracy = (TextView) findViewById(R.id.textView10);
	    DecimalFormat df1 = new DecimalFormat("#.#");
	    accuracy.setText("" + df1.format(((double)MGP.totalAsteroidsKilled / MGP.countShots) * 100) + "%");
		
	    time = (TextView) findViewById(R.id.textView11);
	    DecimalFormat df2 = new DecimalFormat("#.#");
	    time.setText("" +  df2.format(MGP.waited) + " seconds");
	}
	
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
		case R.id.button1: 
		{
			Intent i = new Intent(GameOver.this, MainMenu.class);
			startActivity(i);
			finish();
		}
			break;
		case R.id.button2: 
		{
			Intent i = new Intent(GameOver.this, Game.class);
			startActivity(i);
			finish();
		}
			break;		
		}
	}
	
	@Override
	public void onBackPressed()
	{
		Intent intent = new Intent(GameOver.this, MainMenu.class);
		startActivity(intent);
		finish();
	}
}
