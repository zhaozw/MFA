package com.example.mfa.gamepanel;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class DroidzActivity extends Activity {
	/** Called when the activity is first created. */

	private static final String TAG = DroidzActivity.class.getSimpleName();
	public MGP gamePanel;
	public static AssetManager assets;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// requesting to turn the title OFF
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		// making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		gamePanel = new MGP(this);

		// set our MainGamePanel as the View
		setContentView(gamePanel);

		Log.d(TAG, "View added");

		assets = this.getAssets();

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {

		Log.d(TAG, "Restoring...");
		super.onRestoreInstanceState(savedInstanceState);

	}

	@Override
	protected void onStart() {
		Log.d(TAG, "Starting...");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "Restarting...");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "Resuming...");
		super.onResume();
		gamePanel.setFocusable(true);
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "Pausing...");
		super.onPause();

	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
		// gamePanel.closeGame();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}

}