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
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
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

    private PowerManager.WakeLock wl;

	String url = "http://cofactor1-unbrandedtech.dotcloud.com/Get_Hits.php?hits=";
	public HashMap<String, String> map = new HashMap<String, String>();
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
				
				
		
				
				
				// Storing each json item in variable

				String hit0bool = s.getString("Hit0Bool");
				String hit0from = s.getString("Hit0From");
				String hit0msg = s.getString("Hit0Msg");
				
				String hit1bool = s.getString("Hit1Bool");
				String hit1from =s.getString("Hit1From");
				String hit1msg =s.getString("Hit1Msg");

				String hit2bool = s.getString("Hit2Bool");
				String hit2from =s.getString("Hit2From");
				String hit2msg =s.getString("Hit2Msg");
				
				String hit3bool = s.getString("Hit3Bool");
				String hit3from =s.getString("Hit3From");
				String hit3msg =s.getString("Hit3Msg");
				
				String hit4bool = s.getString("Hit4Bool");
				String hit4from =s.getString("Hit4From");
				String hit4msg =s.getString("Hit4Msg");
				
				String hit5bool = s.getString("Hit5Bool");
				String hit5from =s.getString("Hit5From");
				String hit5msg =s.getString("Hit5Msg");
				
				String hit6bool = s.getString("Hit6Bool");
				String hit6from =s.getString("Hit6From");
				String hit6msg =s.getString("Hit6Msg");
				
				String hit7bool = s.getString("Hit7Bool");
				String hit7from =s.getString("Hit7From");
				String hit7msg =s.getString("Hit7Msg");
				
				String hit8bool = s.getString("Hit8Bool");
				String hit8from =s.getString("Hit8From");
				String hit8msg =s.getString("Hit8Msg");
				
				String hit9bool = s.getString("Hit9Bool");
				String hit9from =s.getString("Hit9From");
				String hit9msg =s.getString("Hit9Msg");
				
				String hit10bool = s.getString("Hit10Bool");
				String hit10from =s.getString("Hit10From");
				String hit10msg =s.getString("Hit10Msg");
				
				String hit11bool = s.getString("Hit11Bool");
				String hit11from =s.getString("Hit11From");
				String hit11msg =s.getString("Hit11Msg");
				
				String hit12bool = s.getString("Hit12Bool");
				String hit12from =s.getString("Hit12From");
				String hit12msg =s.getString("Hit12Msg");
				
				String hit13bool = s.getString("Hit13Bool");
				String hit13from =s.getString("Hit13From");
				String hit13msg =s.getString("Hit13Msg");
				
				String hit14bool = s.getString("Hit14Bool");
				String hit14from =s.getString("Hit14From");
				String hit14msg =s.getString("Hit14Msg");
				
				String hit15bool = s.getString("Hit15Bool");
				String hit15from =s.getString("Hit15From");
				String hit15msg =s.getString("Hit15Msg");

	

				Log.d("New Game Options", "Adding to map");
				// adding each child node to HashMap key => value
				map.put("HitsID", hitid);
				map.put("Hit0Bool", hit0bool);
				map.put("Hit0From", hit0from);
				map.put("Hit0Msg", hit0msg);
				
				map.put("Hit1Bool", hit1bool);
				map.put("Hit1From", hit1from);
				map.put("Hit1Msg", hit1msg);
				
				

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