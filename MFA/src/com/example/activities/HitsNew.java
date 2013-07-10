package com.example.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.example.listViewComponents.HitListViewAdapter;
import com.example.listViewComponents.HitListViewItem;
<<<<<<< HEAD
import com.example.mfa.R;
=======
import com.example.listViewComponents.ItemDetails;
import com.example.listViewComponents.ItemListBaseAdapter;
import com.example.listViewComponents.RivalsItem;
import com.example.listViewComponents.RivalsItemListBaseAdapter;
import com.example.mfa.R;
import com.example.mfa.R.layout;
import com.example.mfa.R.menu;
import com.example.mfa.networking.LoginFunctions;
import com.example.mfa.networking.UserFunctions;

>>>>>>> origin/NewHitsPage
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
	
	public void createIndividualHitDialog(final int hit){
		
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
			availability.setText("Available");

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
					createChooseOpponentDialog(hit);
					alertDialog.dismiss();
				}
			});

			
			alertDialog.show();

	}
		
	public void createChooseOpponentDialog(final int hit){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
				
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);

		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.choose_opponent_dialog,
				frameView);

		Button Rivals = (Button) dialoglayout
				.findViewById(R.id.ChooseOpponentRivals);
		Rivals.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				createRivalsDialog(hit);
				alertDialog.dismiss();
			}
		});
		
		Button Random = (Button) dialoglayout
				.findViewById(R.id.ChooseOpponentRandom);
		Random.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast toast = Toast.makeText(context, "Display Hits Gallery",
						Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		
		Button Enemies = (Button) dialoglayout
				.findViewById(R.id.ChooseOpponentEnemies);
		Enemies.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast toast = Toast.makeText(context, "Display Hits Gallery",
						Toast.LENGTH_SHORT);
				toast.show();
			}
		});

		
		alertDialog.show();

}
	
	public void createRivalsDialog(final int hit){
		
		final ArrayList<RivalsItem> rivalsDetails = GetRivalsForListView();
		ArrayList<String> names = new ArrayList<String>();
		for(int k=0;k<rivalsDetails.size();k++){
			names.add(rivalsDetails.get(k).getUserName());
		}
		
			AlertDialog.Builder builder = new AlertDialog.Builder(context)
					.setTitle(context.getString(R.string.Rivals));
			final FrameLayout frameView = new FrameLayout(context);
			builder.setView(frameView);

			final AlertDialog alertDialog = builder.create();
			LayoutInflater inflater = alertDialog.getLayoutInflater();
			View dialoglayout = inflater.inflate(R.layout.rivals_listview_2, frameView);

			final ListView lv1 = (ListView) dialoglayout
					.findViewById(R.id.rivalsListView);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
			        android.R.layout.simple_list_item_1, names);
			lv1.setAdapter(adapter);

			lv1.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> a, View v, int position,
						long id) {
					   
					createAreYouSureDialog(hit,rivalsDetails.get(position));

				}
			});

			alertDialog.show();
			
	}

	public void createAreYouSureDialog(final int hit,final RivalsItem rival){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);

		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.confirm_send_hit_dialog,
				frameView);

				  final EditText messageTextBox = (EditText) dialoglayout.findViewById(R.id.confirmHitText);
	
				    TextView userName = (TextView) dialoglayout.findViewById(R.id.ConfirmHitDialogUserName);
				    userName.setText(getString(R.string.SendAfter)+" " +rival.getUserName());

					TextView hitName = (TextView) dialoglayout.findViewById(R.id.ConfirmHitDialogHitName);
					hitName.setText(getString(getResources().getIdentifier("hits_" + hit + "_name",
									"string", getPackageName())));


		Button Yes = (Button) dialoglayout
				.findViewById(R.id.ConfirmHitDialogYes);
		Yes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (messageTextBox.getText().toString().length() < 0) {
				    Toast toast = Toast.makeText(context,context.getString(R.string.MustIncludeMessage),Toast.LENGTH_SHORT);
					toast.show();		
				}else{
					LoginFunctions.activateHit(context, rival.gethitId(), hit,convertUnnacceptableCharacters(messageTextBox.getText().toString()));
					Toast toast = Toast.makeText(context,context.getString(R.string.HitSentConfirmed)+" "
					+rival.getUserName()+", "+ messageTextBox.getText().toString(),Toast.LENGTH_SHORT);
				    toast.show();	
					alertDialog.dismiss();
				}
			}
		});
//		
		Button No = (Button) dialoglayout
				.findViewById(R.id.ConfirmHitDialogNo);
		No.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			alertDialog.dismiss();
			}
		});
//		
		alertDialog.show();	
		
	}
	
	
	public String convertUnnacceptableCharacters(String message){
		
		StringBuilder myName = new StringBuilder("");
		
		for(int k=0;k<message.length();k++){
			if     (message.charAt(k)==' ')
			     myName.append("%20");
			else if(message.charAt(k)=='$')
				 myName.append("%24");
			else if(message.charAt(k)=='&')
				 myName.append("%26");
			else if(message.charAt(k)=='+')
				 myName.append("%2B");
			else if(message.charAt(k)==',')
				 myName.append("%2C");	
			else if(message.charAt(k)=='/')
				 myName.append("%2F");
			else if(message.charAt(k)==':')
				 myName.append("%3A");
			else if(message.charAt(k)==';')
				 myName.append("%3B");	
			else if(message.charAt(k)=='=')	
				 myName.append("%3D");	
			else if(message.charAt(k)=='?')	
				 myName.append("%3F");
			else if(message.charAt(k)=='@')	
				 myName.append("%40");
			else if(message.charAt(k)=='?')	
				 myName.append("%22");
			else if(message.charAt(k)=='<')	
				 myName.append("%3C");
			else if(message.charAt(k)=='>')	
				 myName.append("%3E");
			else if(message.charAt(k)=='#')	
				 myName.append("%23");
			else if(message.charAt(k)=='%')	
				 myName.append("%25");
			else if(message.charAt(k)=='{')	
				 myName.append("%7B");
			else if(message.charAt(k)=='}')	
				 myName.append("%7D");
			else if(message.charAt(k)=='|')	
				 myName.append("%7C");
			else if(message.charAt(k)=='^')	
				 myName.append("%5E");
			else if(message.charAt(k)=='~')	
				 myName.append("%7E");
			else if(message.charAt(k)=='[')	
				 myName.append("%5B");
			else if(message.charAt(k)==']')	
				 myName.append("%5D");
			else if(message.charAt(k)=='`')	
				 myName.append("%60");
			else
				 myName.append(message.charAt(k));
		}
		Log.d("ConvertedString"," "+ myName);
		return myName.toString();
	}
	
    public void createAreYouSureDialog2(final int hit,final RivalsItem rival){
		
	
	final Dialog d = new Dialog(this,R.style.CustomDialogTheme);
	d.setContentView(R.layout.confirm_send_hit_dialog);
	
	
	final EditText textBox = (EditText) d.findViewById(R.id.confirmHitText);
	
	Button close_btn = (Button) d.findViewById(R.id.ConfirmHitDialogYes);
	close_btn.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {
	    	

			if (textBox.getText().toString().length() > 0) {
			    Toast toast = Toast.makeText(context,context.getString(R.string.MustIncludeMessage),Toast.LENGTH_SHORT);
				toast.show();		
			}else{
				LoginFunctions.activateHit(context, rival.gethitId(), hit,textBox.getText().toString());
				Toast toast = Toast.makeText(context,context.getString(R.string.HitSentConfirmed)+" "
				+rival.getUserName()+", "+ textBox.getText().toString(),Toast.LENGTH_SHORT);
			    toast.show();	
				d.dismiss();
			}
	     
	    }
	});
	
	Button No = (Button) d.findViewById(R.id.ConfirmHitDialogNo);
	No.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Toast toast = Toast.makeText(context, "Display Hits Gallery",
					Toast.LENGTH_SHORT);
			toast.show();
		}
	});
	
	d.show();
	
	}
	
	
	private ArrayList<RivalsItem> GetRivalsForListView() {
		ArrayList<RivalsItem> results = new ArrayList<RivalsItem>();

		Random r = new Random();
		RivalsItem rivalsItem;

		UserFunctions u = new UserFunctions();
		HashMap<String, String> rivals = LoginFunctions.getRivals(context,
				u.getEmail(context));
		rivalsItem = new RivalsItem();
		// Log.d("getRivalsDetails", rivals.toString());

		for (int k = 0; k < 25; k++) {
			if (rivals.get("Rival" + k + "HitsID") != null)
				if (rivals.get("Rival" + k + "HitsID").length() > 0) {
					rivalsItem = new RivalsItem();
					Log.d("getRivalsDetails", rivals.get("Rival" + k + "Name"));
					Log.d("getRivalsDetails",
							rivals.get("Rival" + k + "HitsID"));

					rivalsItem.setUserName(rivals.get("Rival" + k + "Name"));
					rivalsItem.setHitSentDate((r.nextInt(30) + 1) + "/"
							+ r.nextInt(12) + "/" + r.nextInt(1000));
					rivalsItem.sethitId(rivals.get("Rival" + k + "HitsID"));
					results.add(rivalsItem);
				}
		}

		return results;
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
