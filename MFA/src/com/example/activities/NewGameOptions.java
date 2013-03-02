package com.example.activities;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;
import com.example.mfa.networking.JSONPaser;
import com.example.objects.HitsAllInfo;

import android.os.Bundle;
import android.os.PowerManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class NewGameOptions extends Activity {
	public MGP gamePanel;
	public  Display display;
	public float density; 
    public static float dpHeight; 
    public static float dpWidth;
    public static DisplayMetrics dm;
    public static HitsAllInfo hitsAllInfo;
    public static int lightColor;
    private PowerManager.WakeLock wl;
    public static final String PREFS_NAME = "MyPrefsFile";

	String url = "http://cofactor1-unbrandedtech.dotcloud.com/Get_Hits.php?hits=";
	public HashMap<String, String> map = new HashMap<String, String>();
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	    lightColor = settings.getInt("lightColor", 0);
	    Log.d("NewGameOptions: ", "Light Color ="+lightColor);
		
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");

		
		display = getWindowManager().getDefaultDisplay();
	    DisplayMetrics outMetrics = new DisplayMetrics ();
	    display.getMetrics(outMetrics);

	    float density  = getResources().getDisplayMetrics().density;
	    float dpHeight = outMetrics.heightPixels / density;
	    float dpWidth  = outMetrics.widthPixels / density;
		
	    dm = getResources().getDisplayMetrics();
		
		
	    
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_new_game_options);
	    Intent intent = getIntent();
	    String hit = intent.getStringExtra("hits");
	    JSONArray Hits = null;

		// Creating JSON Parser instance
		JSONPaser jParser = new JSONPaser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url + hit);

		Log.d("NewGameOptions: ", "Starting JSON Try Loop");
		try {
			// Getting Array of Contacts
			Hits = json.getJSONArray("Hits");

			// looping through All Contacts
			for (int i = 0; i < Hits.length(); i++) {
				JSONObject s = Hits.getJSONObject(i);
				
				Log.d("New Game Options", "Starting Parse");
				
		   		String hitid = s.getString("HitsID");

//				// Storing each json item in variable
				
				map.put("HitsID", hitid);
				
				Log.d("New Game Options", "initializing map");
				for(int k =0;k<=15;k++){
					
					
					map.put("Hit"+k+"From",   s.getString("Hit"+k+"From"));
					map.put("Hit"+k+"Active", s.getString("Hit"+k+"Active"));
					map.put("Hit"+k+"Msg", s.getString("Hit"+k+"Msg"));
					
					
					Log.d("NewGameOptions ", "jsonArray  "+ k+ s.getString("Hit"+k+"From"));
					Log.d("NewGameOptions ", "jsonArray  "+ k+ s.getString("Hit"+k+"Msg"));
					Log.d("NewGameOptions ", "jsonArray "+ k+ s.getString("Hit"+k+"Active"));
					
					Log.d("NewGameOptions ", "Map "+ k+ map.get("Hit"+k+"From"));
					Log.d("NewGameOptions ", "Map "+ k+ map.get("Hit"+k+"Active"));
					Log.d("NewGameOptions ", "Map "+ k+ map.get("Hit"+k+"Msg"));
				}
				
				Log.d("New Game Options", "Done");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		hitsAllInfo= new HitsAllInfo();
		Log.d("NewGameOptions: ", "Initailizing Hits Info");
		 hitsAllInfo.initialize(map);
		
		
	}

	public void BeginCustomGame(View v) {

	}

	public void BeginStandardGame(View v) {
		// set our MainGamePanel as the View
		setContentView(gamePanel = new MGP(this));
		Log.d("Standard Game Started", "Panel Created");
		
	}
	

	    @Override
	protected void onPause() {
	    super.onPause();
	    wl.release();
	}//End of onPause
	
	@Override
	protected void onResume() {
	    super.onResume();
	    wl.acquire();
	}//End of onResume

	
	
	@Override
	public void onBackPressed() {
		
	}
//	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_new_game_options, menu);
		return true;
	}

}