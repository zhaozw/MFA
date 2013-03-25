package com.example.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mfa.R;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main_menu);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButton1: {

			Intent i = new Intent(MainMenu.this, NewGameOptions.class);
			startActivity(i);
		}
			break;
		case R.id.imageButton2: {
			Intent i = new Intent(MainMenu.this, Options.class);
			startActivity(i);
		}
			break;
		case R.id.imageButton3: {
			Intent i = new Intent(MainMenu.this, HighScores.class);
			startActivity(i);
		}
			break;
		case R.id.imageButton4: {
			Intent i = new Intent(MainMenu.this, Hit1Menu.class);
			startActivity(i);
		}
			break;
		case R.id.imageButton5: {
			Intent i = new Intent(MainMenu.this, Credits.class);
			startActivity(i);
		}
			break;
		case R.id.imageButton6: {
			Intent i = new Intent(MainMenu.this, Market.class);
			startActivity(i);
		}
			break;
		case R.id.button1: {
			Intent i = new Intent(MainMenu.this, GameLogo.class);
			startActivity(i);
		}
			break;
		}
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Quit Application?")
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener).show();
		return;
	}

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:

				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				finish();
				break;

			case DialogInterface.BUTTON_NEGATIVE:
				break;
			}
		}
	};
}