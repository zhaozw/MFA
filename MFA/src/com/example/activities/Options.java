package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.example.mfa.R;

public class Options extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // make
																					// it
																					// landscape
																					// mode
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_options);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.StartColorsActivity: {
			Intent i = new Intent(Options.this, ColorOptionsActivity.class);
			startActivity(i);
			break;
		}
		case R.id.StartSettingsActivity: {
			Intent i = new Intent(Options.this, SettingsActivity.class);
			startActivity(i);
			break;
		}
		case R.id.StartShipsActivity: {
			Intent i = new Intent(Options.this, ShipTypeActivity.class);
			startActivity(i);
			break;
		}

		}
	}

}
