package com.example.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mfa.R;

public class Options extends Activity implements OnItemSelectedListener {

	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // make
																					// it
																					// landscape
																					// mode
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_options);

		// Restore preferences
		settings = getSharedPreferences(PREFS_NAME, 0);

		Spinner lightColorSpinner = (Spinner) findViewById(R.id.LightColors);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout

		Spinner ec1 = (Spinner) findViewById(R.id.ec1);
		Spinner ec2 = (Spinner) findViewById(R.id.ec2);
		Spinner ec3 = (Spinner) findViewById(R.id.ec3);
		Spinner ec4 = (Spinner) findViewById(R.id.ec4);
		Spinner ec5 = (Spinner) findViewById(R.id.ec5);
		Spinner ec6 = (Spinner) findViewById(R.id.ec6);
		Spinner thrusters = (Spinner) findViewById(R.id.thrustersColor);
		
		
		Spinner txt = (Spinner) findViewById(R.id.textC);
		
		ArrayAdapter<CharSequence> playerTypeAdapter = ArrayAdapter
				.createFromResource(this, R.array.colors,
						android.R.layout.simple_spinner_item);
	
		
		// Specify the layout to use when the list of choices appears

		playerTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner

		lightColorSpinner.setAdapter(playerTypeAdapter);
		ec1.setAdapter(playerTypeAdapter);
		ec2.setAdapter(playerTypeAdapter);
		ec3.setAdapter(playerTypeAdapter);
		ec4.setAdapter(playerTypeAdapter);
		ec5.setAdapter(playerTypeAdapter);
		ec6.setAdapter(playerTypeAdapter);
		txt.setAdapter(playerTypeAdapter);
		thrusters.setAdapter(playerTypeAdapter);
		
		ec1.setOnItemSelectedListener(this);
		ec2.setOnItemSelectedListener(this);
		ec3.setOnItemSelectedListener(this);
		ec4.setOnItemSelectedListener(this);
		ec5.setOnItemSelectedListener(this);
		ec6.setOnItemSelectedListener(this);
		txt.setOnItemSelectedListener(this);
		thrusters.setOnItemSelectedListener(this);
		lightColorSpinner.setOnItemSelectedListener(this);
	}

	// SELECTION CHOICE FOR LIGHT COLOR
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		// We need an Editor object to make preference changes.
		// All objects are from android.context.Context
		SharedPreferences.Editor editor = settings.edit();


		switch(parent.getId()){
        case R.id.ec1:
        	switch (pos) {
    		case (0):
    			editor.putInt("ec1", 0);
    			editor.commit();
    			break;
    		case (1):
    			editor.putInt("ec1", 1);
    			editor.commit();
    			break;
    		case (2):
    			editor.putInt("ec1", 2);
    			editor.commit();
    			break;
    		case (3):
    			editor.putInt("ec1", 3);
    			editor.commit();
    			break;
    		case (4):
    			editor.putInt("ec1", 4);
    			editor.commit();
    			break;
    		case (5):
    			editor.putInt("ec1", 5);
    			editor.commit();
    			break;
    		case (6):
    			editor.putInt("ec1", 6);
    			editor.commit();
    			break;
    		case (7):
    			editor.putInt("ec1", 7);
    			editor.commit();
    			break;
    		case (8):
    			editor.putInt("ec1", 8);
    			editor.commit();
    			break;
    		case (9):
    			editor.putInt("ec1", 9);
    			editor.commit();
    			break;
    		case (10):
    			editor.putInt("ec1", 10);
    			editor.commit();
    			break;
    		}
        	
        	
            break;
        case R.id.ec2:
         	switch (pos) {
    		case (0):
    			editor.putInt("ec2", 0);
    			editor.commit();
    			break;
    		case (1):
    			editor.putInt("ec2", 1);
    			editor.commit();
    			break;
    		case (2):
    			editor.putInt("ec2", 2);
    			editor.commit();
    			break;
    		case (3):
    			editor.putInt("ec2", 3);
    			editor.commit();
    			break;
    		case (4):
    			editor.putInt("ec2", 4);
    			editor.commit();
    			break;
    		case (5):
    			editor.putInt("ec2", 5);
    			editor.commit();
    			break;
    		case (6):
    			editor.putInt("ec2", 6);
    			editor.commit();
    			break;
    		case (7):
    			editor.putInt("ec2", 7);
    			editor.commit();
    			break;
    		case (8):
    			editor.putInt("ec2", 8);
    			editor.commit();
    			break;
    		case (9):
    			editor.putInt("ec2", 9);
    			editor.commit();
    			break;
    		case (10):
    			editor.putInt("ec2", 10);
    			editor.commit();
    			break;
    		}
        	
        	
            break;
        case R.id.ec3:
        	
         	switch (pos) {
    		case (0):
    			editor.putInt("ec3", 0);
    			editor.commit();
    			break;
    		case (1):
    			editor.putInt("ec3", 1);
    			editor.commit();
    			break;
    		case (2):
    			editor.putInt("ec3", 2);
    			editor.commit();
    			break;
    		case (3):
    			editor.putInt("ec3", 3);
    			editor.commit();
    			break;
    		case (4):
    			editor.putInt("ec3", 4);
    			editor.commit();
    			break;
    		case (5):
    			editor.putInt("ec3", 5);
    			editor.commit();
    			break;
    		case (6):
    			editor.putInt("ec3", 6);
    			editor.commit();
    			break;
    		case (7):
    			editor.putInt("ec3", 7);
    			editor.commit();
    			break;
    		case (8):
    			editor.putInt("ec3", 8);
    			editor.commit();
    			break;
    		case (9):
    			editor.putInt("ec3", 9);
    			editor.commit();
    			break;
    		case (10):
    			editor.putInt("ec3", 10);
    			editor.commit();
    			break;
    		}
        	
            break;
            
        case R.id.ec4:
         	switch (pos) {
    		case (0):
    			editor.putInt("ec4", 0);
    			editor.commit();
    			break;
    		case (1):
    			editor.putInt("ec4", 1);
    			editor.commit();
    			break;
    		case (2):
    			editor.putInt("ec4", 2);
    			editor.commit();
    			break;
    		case (3):
    			editor.putInt("ec4", 3);
    			editor.commit();
    			break;
    		case (4):
    			editor.putInt("ec4", 4);
    			editor.commit();
    			break;
    		case (5):
    			editor.putInt("ec4", 5);
    			editor.commit();
    			break;
    		case (6):
    			editor.putInt("ec4", 6);
    			editor.commit();
    			break;
    		case (7):
    			editor.putInt("ec4", 7);
    			editor.commit();
    			break;
    		case (8):
    			editor.putInt("ec4", 8);
    			editor.commit();
    			break;
    		case (9):
    			editor.putInt("ec4", 9);
    			editor.commit();
    			break;
    		case (10):
    			editor.putInt("ec4", 10);
    			editor.commit();
    			break;
    		}
        	
        	
        break;
        
        
        case R.id.ec5:
         	switch (pos) {
    		case (0):
    			editor.putInt("ec5", 0);
    			editor.commit();
    			break;
    		case (1):
    			editor.putInt("ec5", 1);
    			editor.commit();
    			break;
    		case (2):
    			editor.putInt("ec5", 2);
    			editor.commit();
    			break;
    		case (3):
    			editor.putInt("ec5", 3);
    			editor.commit();
    			break;
    		case (4):
    			editor.putInt("ec5", 4);
    			editor.commit();
    			break;
    		case (5):
    			editor.putInt("ec5", 5);
    			editor.commit();
    			break;
    		case (6):
    			editor.putInt("ec5", 6);
    			editor.commit();
    			break;
    		case (7):
    			editor.putInt("ec5", 7);
    			editor.commit();
    			break;
    		case (8):
    			editor.putInt("ec5", 8);
    			editor.commit();
    			break;
    		case (9):
    			editor.putInt("ec5", 9);
    			editor.commit();
    			break;
    		case (10):
    			editor.putInt("ec5", 10);
    			editor.commit();
    			break;
    		}
        	
        	
        break;
        
        case R.id.ec6:
         	switch (pos) {
    		case (0):
    			editor.putInt("ec6", 0);
    			editor.commit();
    			break;
    		case (1):
    			editor.putInt("ec6", 1);
    			editor.commit();
    			break;
    		case (2):
    			editor.putInt("ec6", 2);
    			editor.commit();
    			break;
    		case (3):
    			editor.putInt("ec6", 3);
    			editor.commit();
    			break;
    		case (4):
    			editor.putInt("ec6", 4);
    			editor.commit();
    			break;
    		case (5):
    			editor.putInt("ec6", 5);
    			editor.commit();
    			break;
    		case (6):
    			editor.putInt("ec6", 6);
    			editor.commit();
    			break;
    		case (7):
    			editor.putInt("ec6", 7);
    			editor.commit();
    			break;
    		case (8):
    			editor.putInt("ec6", 8);
    			editor.commit();
    			break;
    		case (9):
    			editor.putInt("ec6", 9);
    			editor.commit();
    			break;
    		case (10):
    			editor.putInt("ec6", 10);
    			editor.commit();
    			break;
    		}
        	
        	
        break;
        
        case R.id.thrustersColor:
         	switch (pos) {
    		case (0):
    			editor.putInt("thrusterColor", 0);
    			editor.commit();
    			break;
    		case (1):
    			editor.putInt("thrusterColor", 1);
    			editor.commit();
    			break;
    		case (2):
    			editor.putInt("thrusterColor", 2);
    			editor.commit();
    			break;
    		case (3):
    			editor.putInt("thrusterColor", 3);
    			editor.commit();
    			break;
    		case (4):
    			editor.putInt("thrusterColor", 4);
    			editor.commit();
    			break;
    		case (5):
    			editor.putInt("thrusterColor", 5);
    			editor.commit();
    			break;
    		case (6):
    			editor.putInt("thrusterColor", 6);
    			editor.commit();
    			break;
    		case (7):
    			editor.putInt("thrusterColor", 7);
    			editor.commit();
    			break;
    		case (8):
    			editor.putInt("thrusterColor", 8);
    			editor.commit();
    			break;
    		case (9):
    			editor.putInt("thrusterColor", 9);
    			editor.commit();
    			break;
    		case (10):
    			editor.putInt("thrusterColor", 10);
    			editor.commit();
    			break;
    		}
        	
        	
        break;
        
        case R.id.textC:
         	switch (pos) {
    		case (0):
    			editor.putInt("txtc", 0);
    			editor.commit();
    			break;
    		case (1):
    			editor.putInt("txtc", 1);
    			editor.commit();
    			break;
    		case (2):
    			editor.putInt("txtc", 2);
    			editor.commit();
    			break;
    		case (3):
    			editor.putInt("txtc", 3);
    			editor.commit();
    			break;
    		case (4):
    			editor.putInt("txtc", 4);
    			editor.commit();
    			break;
    		case (5):
    			editor.putInt("txtc", 5);
    			editor.commit();
    			break;
    		case (6):
    			editor.putInt("txtc", 6);
    			editor.commit();
    			break;
    		case (7):
    			editor.putInt("txtc", 7);
    			editor.commit();
    			break;
    		case (8):
    			editor.putInt("txtc", 8);
    			editor.commit();
    			break;
    		case (9):
    			editor.putInt("txtc", 9);
    			editor.commit();
    			break;
    		case (10):
    			editor.putInt("txtc", 10);
    			editor.commit();
    			break;
    		}
        	
        	
       break;
     case R.id.LightColors:
         	switch (pos) {
    		case (0):
    			editor.putInt("lightColor", 0);
    			editor.commit();
    			break;
    		case (1):
    			editor.putInt("lightColor", 1);
    			editor.commit();
    			break;
    		case (2):
    			editor.putInt("lightColor", 2);
    			editor.commit();
    			break;
    		case (3):
    			editor.putInt("lightColor", 3);
    			editor.commit();
    			break;
    		case (4):
    			editor.putInt("lightColor", 4);
    			editor.commit();
    			break;
    		case (5):
    			editor.putInt("lightColor", 5);
    			editor.commit();
    			break;
    		case (6):
    			editor.putInt("lightColor", 6);
    			editor.commit();
    			break;
    		case (7):
    			editor.putInt("lightColor", 7);
    			editor.commit();
    			break;
    		case (8):
    			editor.putInt("lightColor", 8);
    			editor.commit();
    			break;
    		case (9):
    			editor.putInt("lightColor", 9);
    			editor.commit();
    			break;
    		case (10):
    			editor.putInt("lightColor", 10);
    			editor.commit();
    			break;
    		}
        	
        	
            break;
		}
		

		
		
		
		
		

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
