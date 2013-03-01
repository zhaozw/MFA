package com.example.activities;

import com.example.mfa.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class Options extends Activity implements OnItemSelectedListener {
	
	 public static final String PREFS_NAME = "MyPrefsFile";
     public int lightColor=0;
     SharedPreferences settings;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // make it landscape mode
        requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_options);
		
		  // Restore preferences
	      settings = getSharedPreferences(PREFS_NAME, 0);
	       
	       


		 Spinner lightColorSpinner = (Spinner) findViewById(R.id.LightColors);
		  // Create an ArrayAdapter using the string array and a default spinner layout
		 
		 ArrayAdapter<CharSequence> playerTypeAdapter = ArrayAdapter.createFromResource(this,
	        R.array.colors, android.R.layout.simple_spinner_item);
		 // Specify the layout to use when the list of choices appears
		 
		 playerTypeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		 // Apply the adapter to the spinner
		 
		 lightColorSpinner.setAdapter(playerTypeAdapter); 
		 lightColorSpinner.setOnItemSelectedListener(this);
//		 
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_options, menu);
		return true;
	}


    // SELECTION CHOICE FOR LIGHT COLOR 
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
    	
    	 // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences.Editor editor = settings.edit();
    	
    	
    	  switch(pos)
          {
          case(0):
         	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
             lightColor=0;
             editor.putInt("lightColor", lightColor);
             editor.commit();
         	 break;
          case(1):
         	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
          lightColor=1;
          editor.putInt("lightColor", lightColor);
          editor.commit();
         	 break;
          case(2):
         	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
          lightColor=2;
          editor.putInt("lightColor", lightColor);
          editor.commit();
         	 break;
          case(3):
         	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
          lightColor=3;
          editor.putInt("lightColor", lightColor);
          editor.commit();
         	 break;  
          case(4):
          	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
          lightColor=4;
          editor.putInt("lightColor", lightColor);
          editor.commit();
          	 break;
           case(5):
          	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
           lightColor=5;
           editor.putInt("lightColor", lightColor);
           editor.commit();
          	 break;
           case(6):
          	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
           lightColor=6;
           editor.putInt("lightColor", lightColor);
           editor.commit();
          	 break;
           case(7):
          	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
           lightColor=7;
           editor.putInt("lightColor", lightColor);
           editor.commit();
          	 break;  
           case(8):
            	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
             lightColor=8;
             editor.putInt("lightColor", lightColor);
             editor.commit();
            	 break;  
          }
    	  
    	 
         


	}


	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}



}
