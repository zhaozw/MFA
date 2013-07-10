package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mfa.R;
import com.example.mfa.networking.ConnectionDetector;
import com.example.mfa.networking.DatabaseHandler;
import com.example.mfa.networking.UserFunctions;

public class NewGameOptions extends Activity {

	ConnectionDetector cd;
	DatabaseHandler db;
	UserFunctions userFunctions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_new_game_options);
		cd = new ConnectionDetector(getApplicationContext());
		db = new DatabaseHandler(getApplicationContext());
		userFunctions = new UserFunctions();
	}

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.imageButton1:

			if (!cd.isConnectingToInternet()) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"No internet Connection, starting Custom Game",
						Toast.LENGTH_LONG);
				toast.show();
				startCustomGame();
			} else if (!userFunctions.isUserLoggedIn(getApplicationContext())) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"User not logged in, starting Custom Game",
						Toast.LENGTH_LONG);
				toast.show();
				startCustomGame();
			} else {
				Log.d("New Game Options", "Starting Intent");
				Intent i = new Intent(NewGameOptions.this, Game.class);
				Log.d("New Game Options", "starting activity");
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}

			break;
		case R.id.imageButton2:
			Log.d("New Game Options", "Starting Intent");
			Intent j = new Intent(NewGameOptions.this, CustomGameOptions.class);
			Log.d("New Game Options", "starting activity");
			j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(j);

			break;
		}
	}

	public void startCustomGame() {

		Log.d("New Game Options", "Starting Custom Game");
		Intent j = new Intent(NewGameOptions.this, CustomGameOptions.class);
		j.putExtra("customGame", true);
		j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(j);

	}

}
