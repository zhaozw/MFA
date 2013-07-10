package com.example.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mfa.R;

public class ColorOptionsActivity extends Activity implements
		OnItemSelectedListener {

	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences settings, unlocks;
	AlertDialog.Builder cannotUseItemDialog;

	Spinner ec1;
	Spinner ec2;
	Spinner ec3;
	Spinner ec4;
	Spinner ec5;
	Spinner ec6;
	Spinner thrusters;
	Spinner txt;
	Spinner lightColorSpinner;

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

		setContentView(R.layout.activity_color_options);

		// Restore preferences
		settings = getSharedPreferences(PREFS_NAME, 0);
		unlocks = getSharedPreferences("UnlocksFile", 0);

		lightColorSpinner = (Spinner) findViewById(R.id.LightColors);
		ec1 = (Spinner) findViewById(R.id.ec1);
		ec2 = (Spinner) findViewById(R.id.ec2);
		ec3 = (Spinner) findViewById(R.id.ec3);
		ec4 = (Spinner) findViewById(R.id.ec4);
		ec5 = (Spinner) findViewById(R.id.ec5);
		ec6 = (Spinner) findViewById(R.id.ec6);
		thrusters = (Spinner) findViewById(R.id.thrustersColor);
		txt = (Spinner) findViewById(R.id.textC);

		ArrayAdapter<CharSequence> playerTypeAdapter = ArrayAdapter
				.createFromResource(this, R.array.colors,
						android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears

		playerTypeAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner

		lightColorSpinner.setAdapter(playerTypeAdapter);
		ec1.setAdapter(playerTypeAdapter);
		ec2.setAdapter(playerTypeAdapter);
		ec3.setAdapter(playerTypeAdapter);
		ec4.setAdapter(playerTypeAdapter);
		ec5.setAdapter(playerTypeAdapter);
		ec6.setAdapter(playerTypeAdapter);
		txt.setAdapter(playerTypeAdapter);
		thrusters.setAdapter(playerTypeAdapter);

		ec1.setOnItemSelectedListener(this);
		ec2.setOnItemSelectedListener(this);
		ec3.setOnItemSelectedListener(this);
		ec4.setOnItemSelectedListener(this);
		ec5.setOnItemSelectedListener(this);
		ec6.setOnItemSelectedListener(this);
		txt.setOnItemSelectedListener(this);
		thrusters.setOnItemSelectedListener(this);
		lightColorSpinner.setOnItemSelectedListener(this);

		settings = getSharedPreferences(PREFS_NAME, 0);

		ec1.setSelection(settings.getInt("ec1", 1));
		ec2.setSelection(settings.getInt("ec2", 2));
		ec3.setSelection(settings.getInt("ec3", 3));
		ec4.setSelection(settings.getInt("ec4", 4));
		ec5.setSelection(settings.getInt("ec5", 2));
		ec6.setSelection(settings.getInt("ec6", 0));
		txt.setSelection(settings.getInt("txtc", 3));
		thrusters.setSelection(settings.getInt("thrusterColor", 3));
		lightColorSpinner.setSelection(settings.getInt("lightColor", 0));

		// 0 white
		// 1 black
		// 2 red
		// 3 orange
		// 4 yellow
		// 5 lightGreen
		// 6 darkGreen
		// 7 lightBlue
		// 8 darkBlue
		// 9 purple
		// 10 pink
	}

	// SELECTION CHOICE FOR LIGHT COLOR
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		// We need an Editor object to make preference changes.
		// All objects are from android.context.Context
		SharedPreferences.Editor editor = settings.edit();

		if (pos > 4)
			if (unlocks.getBoolean("Market_Item_" + (pos - 4) + "_Purchased",
					false))
				switch (parent.getId()) {
				case R.id.ec1:
					editor.putInt("ec1", pos);
					editor.commit();
					break;
				case R.id.ec2:
					editor.putInt("ec2", pos);
					editor.commit();
					break;
				case R.id.ec3:
					editor.putInt("ec3", pos);
					editor.commit();
					break;
				case R.id.ec4:
					editor.putInt("ec4", pos);
					editor.commit();
					break;
				case R.id.ec5:
					editor.putInt("ec5", pos);
					editor.commit();
					break;
				case R.id.ec6:
					editor.putInt("ec6", pos);
					editor.commit();
					break;
				case R.id.thrustersColor:
					editor.putInt("thrusterColor", pos);
					editor.commit();
					break;
				case R.id.textC:
					editor.putInt("txtc", pos);
					editor.commit();
					break;
				case R.id.LightColors:
					editor.putInt("lightColor", pos);
					editor.commit();
					break;
				}
			else {
				cannotUseItem(pos - 4);
				switch (parent.getId()) {
				case R.id.ec1:
					ec1.setSelection(settings.getInt("ec1", 1));
					break;
				case R.id.ec2:
					ec2.setSelection(settings.getInt("ec2", 2));
					break;
				case R.id.ec3:
					ec3.setSelection(settings.getInt("ec3", 3));
					break;
				case R.id.ec4:
					ec4.setSelection(settings.getInt("ec4", 4));
					break;
				case R.id.ec5:
					ec5.setSelection(settings.getInt("ec5", 2));
					break;
				case R.id.ec6:
					ec6.setSelection(settings.getInt("ec6", 0));
					break;
				case R.id.thrustersColor:
					thrusters.setSelection(settings.getInt("thrusterColor", 3));
					break;
				case R.id.textC:
					txt.setSelection(settings.getInt("txtc", 3));
					break;
				case R.id.LightColors:
					lightColorSpinner.setSelection(settings.getInt(
							"lightColor", 0));
					break;
				}
			}
		editor.commit();
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public void cannotUseItem(final int item) {

		DialogInterface.OnClickListener cannotUseDialog = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					Intent j = new Intent(ColorOptionsActivity.this,
							Market.class);
					j.putExtra("DesiredPurchase", item);
					startActivity(j);
					break;
				case DialogInterface.BUTTON_NEGATIVE:
					Toast toast2 = Toast.makeText(getApplicationContext(),
							getString(R.string.ItemNotToBePurchased),
							Toast.LENGTH_SHORT);
					toast2.show();
					break;
				}
			}
		};

		cannotUseItemDialog = new AlertDialog.Builder(this);
		cannotUseItemDialog.setMessage(getString(R.string.ItemNotPurchased));
		cannotUseItemDialog.setPositiveButton(getString(R.string.Yes),
				cannotUseDialog);
		cannotUseItemDialog.setNegativeButton(getString(R.string.No),
				cannotUseDialog);
		cannotUseItemDialog.show();

	}

}
