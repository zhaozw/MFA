package com.example.activities;

import com.example.mfa.R;
import com.example.mfa.gamepanel.MainGamePanel;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class NewGameOptions extends Activity {
	 public MainGamePanel gamePanel;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // make it landscape mode
        requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_new_game_options);
		
		
	}

	
    public void BeginCustomGame(View v)
	{
		
    	
    	
    }
    
    public void BeginStandardGame(View v)
    {
        // set our MainGamePanel as the View
        setContentView(gamePanel = new MainGamePanel(this));   
        Log.d("Standard Game Started", "Panel Created");	 	
    }
	  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_new_game_options, menu);
		return true;
	}

}
