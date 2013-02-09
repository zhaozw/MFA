package com.example.activities;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mfa.R;
import com.example.mfa.gamepanel.MainGamePanel;
import com.example.mfa.networking.JSONPaser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class NewGameOptions extends Activity {
	public MainGamePanel gamePanel;
	
	String url = "http://cofactor1-unbrandedtech.dotcloud.com/Get_Hits.php?hits=";
	public HashMap<String, String> map = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
				
				// Storing each json item in variable
				String hitid = s.getString("HitsID");
				String hit0bool = s.getString("Hit0Bool");
				String hit0from = s.getString("Hit0From");
				String hit0msg = s.getString("Hit0Msg");
				String hit1bool = s.getString("Hit1Bool");
				String hit1from =s.getString("Hit1From");
				String hit1msg =s.getString("Hit1Msg");

				// adding each child node to HashMap key => value
				map.put("HitsID", hitid);
				map.put("Hit0Bool", hit0bool);
				map.put("Hit0From", hit0from);
				map.put("Hit0Msg", hit0msg);
				map.put("Hit1Bool", hit1bool);
				map.put("Hit1From", hit1from);
				map.put("Hit1Msg", hit1msg);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void BeginCustomGame(View v) {

	}

	public void BeginStandardGame(View v) {
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