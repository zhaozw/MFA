package com.example.activities;

import com.example.mfa.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class GameLogo extends Activity 
{
	private ProgressDialog pd = null;
    private Object data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // make it landscape mode
        requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_game_logo);
		
//		// Show the ProgressDialog on this thread
//        this.pd = ProgressDialog.show(this, "Loading..", "Downloading Data...", true, false);
//
//        // Start a new thread that will download all the data
//        new DownloadTask().execute("Any parameters my download task needs here");
	}
	
	public void onClick(View v)
    {
      switch (v.getId())
      {
         case R.id.imageButton1:
         { 
        	 finish();
             Intent i = new Intent(GameLogo.this,MainMenu.class);
             startActivity(i); 
         }        
      }
    }

	private class DownloadTask extends AsyncTask<String, Void, Object> 
    {
       protected Object doInBackground(String... args) 
       {
                    Log.i("MyApp", "Background thread starting");

                    // This is where you would do all the work of downloading your data
                    

                    //return "replace this with your data object";
                    for(int i = 0; i < 1000000; i++)
                    {
                   	 data = " " + " yacov ";
                    }
                    
                    return data;
       }

       protected void onPostExecute(Object result) 
       {
                    // Pass the result data back to the main activity
                    GameLogo.this.data = result;

                    if (GameLogo.this.pd != null)
                    {
                   	 GameLogo.this.pd.dismiss();
                   	 Toast.makeText(getApplicationContext(), "Data Loading Completed", Toast.LENGTH_LONG).show();
                    }
       } 
   }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game_logo, menu);
		return true;
	}
}
