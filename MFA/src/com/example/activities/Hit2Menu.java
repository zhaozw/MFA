package com.example.activities;

import com.example.mfa.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Hit2Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_hit2_menu);
	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Purchase: {
			Intent i = new Intent(Hit2Menu.this, ChooseRival.class);
			startActivity(i);
		}
			break;
		case R.id.NextButton: {
			Intent i = new Intent(Hit2Menu.this, Hit0Menu.class);
			finish();
			startActivity(i);
		}
			break;
		case R.id.PrevButton: {
			Intent i = new Intent(Hit2Menu.this, Hit1Menu.class);
			finish();
			startActivity(i);
		}
			break;
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hit2_menu, menu);
		return true;
	}

}