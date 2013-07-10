package com.example.activities;

import java.util.ArrayList;

import com.example.listViewComponents.HitListViewAdapter;
import com.example.listViewComponents.HitListViewItem;
import com.example.listViewComponents.ItemDetails;
import com.example.listViewComponents.ItemListBaseAdapter;
import com.example.mfa.R;
import com.example.mfa.R.layout;
import com.example.mfa.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HitsNew extends Activity {
	
	ArrayList<HitListViewItem> hitsDetails;
	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_hits_new);
		
		

		hitsDetails = GetSearchResults();
		final ListView lv1 = (ListView) findViewById(R.id.HitsListView);
		lv1.setAdapter(new HitListViewAdapter(this, hitsDetails));

		lv1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				createIndividualHitDialog(position);		
			}
		});
		
	}
	
	public void createIndividualHitDialog(int hit){
		
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
					
			final FrameLayout frameView = new FrameLayout(context);
			builder.setView(frameView);

			final AlertDialog alertDialog = builder.create();
			LayoutInflater inflater = alertDialog.getLayoutInflater();
			View dialoglayout = inflater.inflate(R.layout.individual_hit_details_dialog,
					frameView);

			TextView name = (TextView) dialoglayout
					.findViewById(R.id.name);
			name.setText(getString(getResources()
					.getIdentifier("hits_" + hit + "_name",
							"string", getPackageName())));

			TextView description = (TextView) dialoglayout
					.findViewById(R.id.description);
			description.setText(getString(getResources()
					.getIdentifier("hits_" + hit + "_description",
							"string", getPackageName())));

			TextView availability = (TextView) dialoglayout
					.findViewById(R.id.availability);
			availability.setText("3");

			TextView cost = (TextView) dialoglayout
					.findViewById(R.id.cost);
			cost.setText(getString(getResources()
					.getIdentifier("hits_" + hit + "_cost",
							"string", getPackageName())));

			ImageView image = (ImageView) dialoglayout
					.findViewById(R.id.image);
			image.setId(R.drawable.africanamericanrectangle);
		
			Button chooseOpponent = (Button) dialoglayout
					.findViewById(R.id.chooseOpponent);
			chooseOpponent.setOnClickListener(new View.OnClickListener() {
			
				
				public void onClick(View v) {
					Toast toast = Toast.makeText(context, "Display Hits Gallery",
							Toast.LENGTH_SHORT);
					toast.show();
				}
			});

			
			alertDialog.show();

	}
		
	
	
	private ArrayList<HitListViewItem> GetSearchResults() {
		ArrayList<HitListViewItem> results = new ArrayList<HitListViewItem>();
		HitListViewItem hitDetails;
		for (int k = 0; k <= 15; k++) {
			hitDetails = new HitListViewItem();
			hitDetails.setHitName(getString(getResources().getIdentifier("hits_" + k + "_name", "string", getPackageName())));
			hitDetails.setImageID(getResources().getIdentifier("africanamericanrectangle", "drawable",getPackageName()));
			results.add(hitDetails);
		}
		return results;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hits_new, menu);
		return true;
	}

}
