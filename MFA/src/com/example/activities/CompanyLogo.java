package com.example.activities;

import com.example.mfa.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class CompanyLogo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // make it landscape mode
        requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        
		setContentView(R.layout.activity_company_logo);
		
		// making the logo image run for a few seconds and then moving to the next activity
        Thread splashThread = new Thread() 
        {
           @Override
           public void run() 
           {
              try 
              {
                 int waited = 0;
                 while (waited < 3000)
                 {
                    sleep(100);
                    waited += 100;
                 }
              } 
              catch (InterruptedException e)
              {
                 // do nothing
              } 
              finally 
              {
                 finish();
                 Intent i = new Intent(CompanyLogo.this,GameLogo.class);
                 startActivity(i);
              }
           }
        };
        splashThread.start();
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_company_logo, menu);
		return true;
	}

}
