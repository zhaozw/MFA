package com.example.activities;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;
import com.example.mfa.networking.DatabaseHandler;
import com.example.mfa.networking.UserFunctions;
import com.example.objects.HitsAllInfo;

public class Game extends Activity {
	public MGP gamePanel;
	public Display display;
	public float density;
	public static float dpHeight;
	public static float dpWidth;
	public static DisplayMetrics dm;
	public static HitsAllInfo hitsAllInfo;
	public static int lightColor;
	private PowerManager.WakeLock wl;
	public static final String PREFS_NAME = "MyPrefsFile";
	public String hits;
	public static Activity activity;

	String url = "http://cofactor1-unbrandedtech.dotcloud.com/Get_Hits.php?hits=";
	public HashMap<String, String> map = new HashMap<String, String>();

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		lightColor = settings.getInt("lightColor", 0);
		Log.d("Game: ", "Light Color =" + lightColor);

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");

		display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);

		float density = getResources().getDisplayMetrics().density;
		float dpHeight = outMetrics.heightPixels / density;
		float dpWidth = outMetrics.widthPixels / density;

		dm = getResources().getDisplayMetrics();

		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		UserFunctions userFunctions = new UserFunctions();

		activity = this;

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(gamePanel = new MGP(this));

		// if(!userFunctions.isUserLoggedIn(getApplicationContext()))
		// {
		// Toast toast = Toast.makeText(getApplicationContext(),
		// "Please Log in", Toast.LENGTH_LONG);
		// toast.show();
		// }
		// else
		// {
		//
		// String hit = db.getUserDetails().get("uid").toString().trim();
		// JSONArray Hits = null;
		//
		// // Creating JSON Parser instance
		// JSONParser jParser = new JSONParser();
		//
		// // getting JSON string from URL
		// JSONObject json = jParser.getJSONFromUrl(url + hit);
		//
		// Log.d("Game",hit);
		//
		// Log.d("Game: ", "Starting JSON Try Loop");
		// try
		// {
		// // Getting Array of Contacts
		// Hits = json.getJSONArray("Hits");
		// Log.d("Game","Getting Json Array");
		//
		// // looping through All Contacts
		// for (int i = 0; i < Hits.length(); i++)
		// {
		// JSONObject s = Hits.getJSONObject(i);
		//
		// Log.d("New Game Options", "Starting Parse");
		//
		// String hitid = s.getString("HitsID");
		//
		// // Storing each json item in variable
		//
		// map.put("HitsID", hitid);
		//
		// Log.d("New Game Options", "initializing map");
		// for(int k =0;k<=15;k++)
		// {
		// map.put("Hit"+k+"From", s.getString("Hit"+k+"From"));
		// map.put("Hit"+k+"Active", s.getString("Hit"+k+"Active"));
		// map.put("Hit"+k+"Msg", s.getString("Hit"+k+"Msg"));
		//
		// Log.d("NewGameOptions ", "jsonArray  "+ k+
		// s.getString("Hit"+k+"From"));
		// Log.d("NewGameOptions ", "jsonArray  "+ k+
		// s.getString("Hit"+k+"Msg"));
		// Log.d("NewGameOptions ", "jsonArray "+ k+
		// s.getString("Hit"+k+"Active"));
		//
		// Log.d("NewGameOptions ", "Map "+ k+ map.get("Hit"+k+"From"));
		// Log.d("NewGameOptions ", "Map "+ k+ map.get("Hit"+k+"Active"));
		// Log.d("NewGameOptions ", "Map "+ k+ map.get("Hit"+k+"Msg"));
		// }
		//
		// Log.d("New Game Options", "Done");
		// }
		// }
		// catch (JSONException e)
		// {
		// e.printStackTrace();
		// }
		// hitsAllInfo= new HitsAllInfo();
		// Log.d("Game: ", "Initailizing Hits Info");
		// hitsAllInfo.initialize(map);
		// }
	}

	public void gameOver() {
		Intent i = new Intent(Game.this, GameOver.class);
		startActivity(i);
	}

	@Override
	protected void onPause() {
		super.onPause();
		gamePanel.closeGame();
		wl.release();

	}// End of onPause

	@Override
	protected void onResume() {
		super.onResume();
		setContentView(gamePanel = new MGP(this));
		wl.acquire();

	}// End of onResume

	@Override
	public void onBackPressed() {
		gamePanel.setPause(true); // pause the game

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Quit Game?")
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener).show();
		return;
	}

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:

				gamePanel.closeGame();
				Intent intent = new Intent(Game.this, MainMenu.class);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();

				break;

			case DialogInterface.BUTTON_NEGATIVE:
				gamePanel.setPause(false);
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// if(gamePanel.getPause())
		// gamePanel.setPause(false);
		// else
		// gamePanel.setPause(true);

		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}

}
