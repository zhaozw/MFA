package com.example.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mfa.NewMenu;
import com.example.mfa.R;
import com.example.mfa.networking.*;

public class GameLogo extends Activity {

	boolean isInternetPresent;
	public TextView FBID;
	public ImageButton FBLogin;
	public Button btnLogout, btnLogin, Offline;
	UserFunctions userFunctions;
	DatabaseHandler db;
	ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		cd = new ConnectionDetector(getApplicationContext());

		isInternetPresent = cd.isConnectingToInternet();
		Log.d("GameLogo", "Starting onCreate");

		setContentView(R.layout.activity_game_logo);
		Log.d("GameLogo: ", "Setting content view");

		FBID = (TextView) findViewById(R.id.FBID);

		btnLogout = (Button) findViewById(R.id.btnLogout);
		btnLogin = (Button) findViewById(R.id.btnLogin);

		FBLogin = (ImageButton) findViewById(R.id.FBLogin);

		// check for Internet status
		if (!isInternetPresent) {
			showAlertDialog(GameLogo.this, "No Internet Connection",
					"You don't have internet connection.", false);
		} else {

			userFunctions = new UserFunctions();
			db = new DatabaseHandler(getApplicationContext());
			Log.d("GameLogo",
					"Database Handler and userfunctions have been created.");
			if (intent.hasExtra("name")) {
				intent.getExtras();
				FBID.setText(intent.getStringExtra("uid"));
			}

			if (!userFunctions.isUserLoggedIn(getApplicationContext())) {
				btnLogout = (Button) findViewById(R.id.btnLogout);
				btnLogout.setVisibility(View.GONE);
			} else {
				Log.d("GameLogo", "User is logged in");
				FBID.setText(db.getUserDetails().get("name").toString());
			}
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.FBLogin: {
			FBID.setVisibility(View.VISIBLE);
			Intent intent = new Intent(this, MainMenu.class);
			startActivity(intent);
			break;
		}
		case R.id.btnLogout: {
			Log.d("Button Pressed", "Log Out Button");
			if (userFunctions.isUserLoggedIn(getApplicationContext())) {
				userFunctions.logoutUser(getApplicationContext());
			}
			Intent login = new Intent(getApplicationContext(),
					LoginActivity.class);
			login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(login);
			finish();
			break;
		}
		case R.id.btnLogin: {
			Log.d("Button Pressed", "Log In");
			if (userFunctions.isUserLoggedIn(getApplicationContext())) {
				Log.d("BTNLogin Button Pressed", "User is logged in");
				Intent intent = new Intent(this, NewMenu.class);
				startActivity(intent);
			} else {
				Log.d("BTNLogin Button Pressed", "User is not logged in");
				Intent login = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(login);
				finish();
			}
			break;
		}

		}

	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
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