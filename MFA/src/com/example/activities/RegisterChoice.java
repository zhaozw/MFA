package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.mfa.R;
import com.example.mfa.networking.UserFunctions;

public class RegisterChoice extends Activity {

	public Button imageButton2, FormRegister;
	UserFunctions userFunctions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game_logo);
		Log.d("GameLogo: ", "Setting content view");
		userFunctions = new UserFunctions();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButton2: {
			Toast.makeText(getApplicationContext(),
					"I'm Still Working On The Facebook Thing!",
					Toast.LENGTH_LONG).show();
		}
		case R.id.FormRegister: {
			// user is not logged in show login screen
			Intent Register = new Intent(getApplicationContext(),
					RegisterActivity.class);
			Register.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(Register);
			// Closing dashboard screen
			finish();
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