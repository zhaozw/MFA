package com.example.activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mfa.R;
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
import android.widget.TextView;

public class GameLogo extends Activity {
	private Object data = null;
	String url = "http://cofactor1-unbrandedtech.dotcloud.com/Player_Info.php?FBID=james0123";

	// JSON Node names
	final String TAG_GAME = "Game";
	final String TAG_FBID = "FBID";
	final String TAG_FIRST = "First_Name";
	final String TAG_LAST = "Last_Name";
	final String TAG_HIGHSCORE = "Highscore";
	final String TAG_MONEY = "Money";
	final String TAG_HITS = "Hits";
	public HashMap<String, String> map = new HashMap<String, String>();
	public ArrayList<HashMap<String, String>> Players;
	public TextView FBID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game_logo);
		Log.d("GameLogo: ", "Setting content view");
		FBID = (TextView)findViewById(R.id.FBID);
		JSONArray search = null;

		// Creating JSON Parser instance
		JSONPaser jParser = new JSONPaser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);

		Log.d("GameLogo: ", "Starting JSON Try Loop");
		try {
			// Getting Array of Contacts
			search = json.getJSONArray(TAG_GAME);

			// looping through All Contacts
			for (int i = 0; i < search.length(); i++) {
				JSONObject s = search.getJSONObject(i);

				// Storing each json item in variable
				String fbid = s.getString(TAG_FBID);
				String first = s.getString(TAG_FIRST);
				String last = s.getString(TAG_LAST);
				String highscore = s.getString(TAG_HIGHSCORE);
				String money = s.getString(TAG_MONEY);
				String hits = s.getString(TAG_HITS);
				
				Log.d("GameLogo: ", "String" + fbid);
				Log.d("GameLogo: ", "String" + first);
				Log.d("GameLogo: ", "String" + last);
				Log.d("GameLogo: ", "String" + highscore);
				Log.d("GameLogo: ", "String" + money);
				Log.d("GameLogo: ", "String" + hits);
				// adding each child node to HashMap key => value
				map.put(TAG_FBID, fbid);
				map.put(TAG_FIRST, first);
				map.put(TAG_LAST, last);
				map.put(TAG_HIGHSCORE, highscore);
				map.put(TAG_MONEY, money);
				map.put(TAG_HITS, hits);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButton1: {
			Log.d("GameLogo: ", map.get(TAG_FBID).toString());
			FBID.setText(map.get(TAG_FBID).toString());
			Intent intent = new Intent(this, MainMenu.class);
			intent.putExtra("map", map);
			startActivity(intent);
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