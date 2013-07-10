package com.example.activities;

import com.example.mfa.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class CustomGameOptions extends Activity implements
		OnItemSelectedListener {

	SharedPreferences settings, unlocks;
	// Restore preferences
	Spinner hit1, hit2, hit3;
	CheckBox god, intense, shootFaster, sineBullets, slowMo;
	SharedPreferences.Editor editor;
	AlertDialog.Builder cannotUseItemDialog;
	Button startGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // make
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_custom_game_options);
		settings = getSharedPreferences("CustomGamePrefs", 0);
		unlocks = getSharedPreferences("UnlocksFile", 0);
		editor = settings.edit();

		hit1 = (Spinner) findViewById(R.id.Hit1CustomGameSpinner);
		hit2 = (Spinner) findViewById(R.id.Hit2CustomGameSpinner);
		hit3 = (Spinner) findViewById(R.id.Hit3CustomGameSpinner);
		god = (CheckBox) findViewById(R.id.CustomGameGodCheckBox);
		intense = (CheckBox) findViewById(R.id.CustomGameIntenseGameCheckBox);
		shootFaster = (CheckBox) findViewById(R.id.CustomGameShootFasterCheckBox);
		sineBullets = (CheckBox) findViewById(R.id.CustomGameSineBulletsCheckBox);
		slowMo = (CheckBox) findViewById(R.id.CustomGameSlowMotionCheckBox);
		startGame = (Button) findViewById(R.id.CustomGameStart);
		ArrayAdapter<CharSequence> hitChoice = ArrayAdapter.createFromResource(
				this, R.array.Hits, android.R.layout.simple_spinner_item);

		hitChoice
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		hit1.setAdapter(hitChoice);
		hit2.setAdapter(hitChoice);
		hit3.setAdapter(hitChoice);

		if (settings.getBoolean("god", false))
			god.setChecked(true);
		else
			god.setChecked(false);

		if (settings.getBoolean("intense", false))
			intense.setChecked(true);
		else
			intense.setChecked(false);

		if (settings.getBoolean("shootFaster", false))
			shootFaster.setChecked(true);
		else
			shootFaster.setChecked(false);

		if (settings.getBoolean("sineBullets", false))
			sineBullets.setChecked(true);
		else
			sineBullets.setChecked(false);

		if (settings.getBoolean("slowMo", false))
			slowMo.setChecked(true);
		else
			slowMo.setChecked(false);

		hit1.setOnItemSelectedListener(this);
		hit2.setOnItemSelectedListener(this);
		hit3.setOnItemSelectedListener(this);

		hit1.setSelection(1 + settings.getInt("hit1", -1));
		hit2.setSelection(1 + settings.getInt("hit2", -1));
		hit3.setSelection(1 + settings.getInt("hit3", -1));
	}

	// SELECTION CHOICE FOR LIGHT COLOR
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// We need an Editor object to make preference changes.
		// All objects are from android.context.Context

		switch (parent.getId()) {
		case R.id.Hit1CustomGameSpinner:
			if (pos == 0)
				editor.putInt("hit1", -1);
			else if (unlocks.getBoolean("Market_Item_" + (pos + 21)
					+ "_Purchased", false))
				editor.putInt("hit1", pos - 1);
			else {
				cannotUseItem(pos + 21);
				hit1.setSelection(0);
			}
			break;
		case R.id.Hit2CustomGameSpinner:
			if (pos == 0)
				editor.putInt("hit2", -1);
			else if (unlocks.getBoolean("Market_Item_" + (pos + 21)
					+ "_Purchased", false))
				editor.putInt("hit2", pos - 1);
			else {
				cannotUseItem(pos + 21);
				hit2.setSelection(0);
			}
			break;
		case R.id.Hit3CustomGameSpinner:
			if (pos == 0)
				editor.putInt("hit3", -1);
			else if (unlocks.getBoolean("Market_Item_" + (pos + 21)
					+ "_Purchased", false))
				editor.putInt("hit3", pos - 1);
			else {
				cannotUseItem(pos + 21);
				hit3.setSelection(0);
			}
			break;
		}
		editor.commit();
	}

	public void onClick(View v) {

		SharedPreferences.Editor editor = settings.edit();

		switch (v.getId()) {
		case R.id.CustomGameGodCheckBox:
			if (unlocks.getBoolean("Market_Item_21_Purchased", false)) {
				if (god.isChecked())
					editor.putBoolean("god", true);
				else
					editor.putBoolean("god", false);
			} else {
				Log.d("pff", "should not be running");
				cannotUseItem(21);
				god.setChecked(false);
			}
			break;
		case R.id.CustomGameIntenseGameCheckBox:
			if (unlocks.getBoolean("Market_Item_20_Purchased", false)) {
				if (intense.isChecked())
					editor.putBoolean("intense", true);
				else
					editor.putBoolean("intense", false);
			} else {
				cannotUseItem(20);
				intense.setChecked(false);
			}

			break;
		case R.id.CustomGameShootFasterCheckBox:
			if (unlocks.getBoolean("Market_Item_17_Purchased", false)) {
				if (shootFaster.isChecked())
					editor.putBoolean("shootFaster", true);
				else
					editor.putBoolean("shootFaster", false);
			} else {
				cannotUseItem(17);
				shootFaster.setChecked(false);
			}

			break;
		case R.id.CustomGameSineBulletsCheckBox:
			if (unlocks.getBoolean("Market_Item_19_Purchased", false)) {
				if (sineBullets.isChecked())
					editor.putBoolean("sineBullets", true);
				else
					editor.putBoolean("sineBullets", false);
			} else {
				cannotUseItem(19);
				sineBullets.setChecked(false);
			}

			break;
		case R.id.CustomGameSlowMotionCheckBox:
			if (unlocks.getBoolean("Market_Item_18_Purchased", false)) {
				if (slowMo.isChecked())
					editor.putBoolean("slowMo", true);
				else
					editor.putBoolean("slowMo", false);
			} else {
				cannotUseItem(18);
				slowMo.setChecked(false);
			}

			break;

		case R.id.CustomGameStart:
			Log.d("New Game Options", "Starting Intent");
			Intent i = new Intent(CustomGameOptions.this, Game.class);
			Log.d("New Game Options", "starting activity");
			i.putExtra("customGame", true);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			break;

		}
		editor.commit();
	}

	public void cannotUseItem(final int item) {

		DialogInterface.OnClickListener cannotUseDialog = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					Intent j = new Intent(CustomGameOptions.this, Market.class);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_custom_game_options, menu);
		return true;
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
