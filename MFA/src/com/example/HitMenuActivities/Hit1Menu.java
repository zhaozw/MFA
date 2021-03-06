package com.example.HitMenuActivities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.activities.testchoose;
import com.example.mfa.R;

public class Hit1Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_hit1_menu);
		//
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Purchase: {
			Intent i = new Intent(Hit1Menu.this, testchoose.class);
			i.putExtra("HitType", "1");
			startActivity(i);
		}
			break;
		case R.id.NextButton: {
			Log.d("Hits1Menu", " Going to next Hit");
			Intent i = new Intent(Hit1Menu.this, Hit2Menu.class);
			startActivity(i);
			finish();
		}
			break;
		case R.id.PrevButton: {
			Log.d("Hits1Menu", " Going to prev Hit");
			Intent i = new Intent(Hit1Menu.this, Hit0Menu.class);
			startActivity(i);
			finish();
		}
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hit1_menu, menu);
		return true;
	}

}
