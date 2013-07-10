package com.example.activities;

import java.util.ArrayList;

import com.example.listViewComponents.ImageGridAdapter;
import com.example.mfa.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class AchievementsActivity extends Activity {
	AlertDialog.Builder IndividualTrophyView;
	GridView gridview;
	SharedPreferences trophys;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_achievements);

		trophys = getSharedPreferences("TrophysFile", 0);

		gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageGridAdapter(this, GetSearchResults()));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				IndividualTrophyView(position);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_achievements, menu);
		return true;
	}

	private ArrayList<Integer> GetSearchResults() {
		ArrayList<Integer> results = new ArrayList<Integer>();

		Integer item_details;

		for (int k = 0; k <= 29; k++) {
			if (trophys.getBoolean("trophy_" + k + "_unlocked", false)) {

				if (k < 12 || k > 19)
					item_details = getResources().getIdentifier(
							"trophy_icon_" + k, "drawable", getPackageName());
				else
					item_details = getResources()
							.getIdentifier("trophy_icon_generic", "drawable",
									getPackageName());

			} else {
				item_details = R.drawable.trophy_item_locked;
			}

			results.add(item_details);
		}

		return results;
	}

	public void IndividualTrophyView(final int item) {

		DialogInterface.OnClickListener trophyViewDialog = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				// }
			}
		};

		IndividualTrophyView = new AlertDialog.Builder(this);
		IndividualTrophyView.setTitle(getString(getResources().getIdentifier(
				"Trophy_" + item + "_Name", "string", getPackageName())));
		IndividualTrophyView
				.setMessage(getString(getResources().getIdentifier(
						"Trophy_" + item + "_Description", "string",
						getPackageName())));
		IndividualTrophyView.setIcon((int) ((ImageGridAdapter) gridview
				.getAdapter()).getItemId(item));
		IndividualTrophyView.show();

	}

}
