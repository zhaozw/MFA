package com.example.activities;

import java.util.ArrayList;

import com.example.listViewComponents.ImageListViewAdapter;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ShipTypeActivity extends Activity {

	ArrayList<Integer> image_details;
	ImageView shipImage;
	TextView shipTypeTitle;
	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences settings, unlocks;
	AlertDialog.Builder cannotUseItemDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_ship_type);

		// Restore preferences
		settings = getSharedPreferences(PREFS_NAME, 0);
		unlocks = getSharedPreferences("UnlocksFile", 0);

		shipImage = (ImageView) findViewById(R.id.ShipImage);
		shipTypeTitle = (TextView) findViewById(R.id.CurrentShipName);

		setInitialShipChoice(shipImage, shipTypeTitle);

		// SharedPreferences.Editor editor = settings.edit();

		image_details = GetSearchResults();

		Log.d("Ship Type Activity", " Initializing adapter ");

		final ListView lv1 = (ListView) findViewById(R.id.ShipTypeListView);
		lv1.setAdapter(new ImageListViewAdapter(this, image_details));

		lv1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {

				if (isItemAvailable(position)) {
					shipImage.setImageResource(((ImageListViewAdapter) lv1
							.getAdapter()).getImageId(position));
					if (position != 0)
						shipTypeTitle.setText(getString(getResources()
								.getIdentifier(
										"Market_Item_" + (position + 6)
												+ "_Name", "string",
										getPackageName())));
					else
						shipTypeTitle
								.setText(getString(R.string.DefaultShipName));
					SharedPreferences.Editor editor = settings.edit();
					editor.putInt("ship_choice", position);
					editor.commit();
				} else {
					cannotUseItem(position + 6);
				}

			}
		});

		Log.d("Ship Type Activity", " Initialized adapter ");

	}

	private ArrayList<Integer> GetSearchResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();

		Integer item_details;

		item_details = R.drawable.f162;
		results.add(item_details);

		for (int k = 0; k <= 10; k++) {
			item_details = getResources().getIdentifier(
					"market_item_" + (k + 7) + "_icon", "drawable",
					getPackageName());
			results.add(item_details);
		}

		return results;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ship_type, menu);
		return true;
	}

	public void setInitialShipChoice(ImageView shipImage, TextView shipTypeTitle) {

		if (settings.getInt("ship_choice", 0) == 0) {
			shipTypeTitle.setText(getString(R.string.DefaultShipName));
			shipImage.setImageResource(R.drawable.f162);
		} else {
			shipTypeTitle.setText(getString(getResources().getIdentifier(
					"Market_Item_" + (settings.getInt("ship_choice", 0) + 6)
							+ "_Name", "string", getPackageName())));
			shipImage.setImageResource((getResources().getIdentifier(
					"market_item_" + (settings.getInt("ship_choice", 0) + 6)
							+ "_icon", "drawable", getPackageName())));
		}

	}

	public boolean isItemAvailable(int ship) {

		// UNLOCKS IS THE FILE THAT HOLDS THE ITEM'S LOCKED OR UNLOCKED STATE
		// THE FIRST UNLOCKABLE SHIP STARTS AT MARKET ITEM 7 SO WE ADD 6 TO
		// POSITION BECAUSE THE FIRST SHIP IS NOT ON THE UNLOCK PAGE
		// SO THE SHIP AT POSITION 1 WILL ADD TO CHECK FOR MARKET ITEM 7

		if (ship == 0)
			return true;
		else if (unlocks.getBoolean("Market_Item_" + (ship + 6) + "_Purchased",
				false))
			return true;
		else
			return false;

	}

	public void cannotUseItem(final int item) {

		DialogInterface.OnClickListener cannotUseDialog = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					Intent j = new Intent(ShipTypeActivity.this, Market.class);
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
