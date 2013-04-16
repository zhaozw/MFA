package com.example.activities;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;

public class GameOver extends Activity {
	TextView score, killed, shots, accuracy, time, finalWaveNumber,firstHitName,firstHitSentBy,firstHitWaveSent,firstHitSuccessOrFailure;
    TextView hit1line1,hit1line2,hit1line3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_game_over);

		score = (TextView) findViewById(R.id.textView7);
		score.setText("" + MGP.score);

		killed = (TextView) findViewById(R.id.textView8);
		killed.setText("" + MGP.totalAsteroidsKilled);

		shots = (TextView) findViewById(R.id.textView9);
		shots.setText("" + MGP.countShots);

		accuracy = (TextView) findViewById(R.id.textView10);
		DecimalFormat df1 = new DecimalFormat("#.#");
		accuracy.setText(""
				+ df1.format(((double) MGP.totalAsteroidsKilled / MGP.countShots) * 100)
				+ "%");

		time = (TextView) findViewById(R.id.textView11);
		DecimalFormat df2 = new DecimalFormat("#.#");
		time.setText("" + df2.format(MGP.waited) + " seconds");
		
		
		finalWaveNumber = (TextView) findViewById(R.id.finalwaveNumber);
		finalWaveNumber.setText("" + MGP.wave);
		
		
		hit1line1= (TextView) findViewById(R.id.hitinfo1line1);
		hit1line2= (TextView) findViewById(R.id.hitinfo1line2);
		hit1line3= (TextView) findViewById(R.id.hitinfo1line3);
		setHitMessageInfo(+Game.hitsAllInfo.setHits[0],hit1line1,hit1line1,hit1line1);
		
		
		//if(Game.hitsAllInfo.totalActiveHits==3){
		
//		if(Game.hitsAllInfo.totalActiveHits==3){
//		
//		firstHitName = (TextView) findViewById(R.id.FirstHitName);
//		firstHitName.setText(" "+Game.hitsAllInfo.hitsInfo[Game.hitsAllInfo.setHits[0]]);
//	
//		firstHitSentBy = (TextView) findViewById(R.id.firstHitSenderName);
//		firstHitSentBy.setText("" + MGP.wave);
//		
//		firstHitWaveSent = (TextView) findViewById(R.id.firstHitWaveSent);
//		firstHitWaveSent.setText("" + MGP.wave);
//		
//		firstHitSuccessOrFailure = (TextView) findViewById(R.id.firstHitsuccessorFailure);
//		firstHitSuccessOrFailure.setText("" + MGP.wave);
//		
//	    }else if(Game.hitsAllInfo.totalActiveHits==2){
//			
//	    }else if(Game.hitsAllInfo.totalActiveHits==1){
//	    	
//	    }else if(Game.hitsAllInfo.totalActiveHits==0){
//	    	firstHitName.setVisibility(1);
//	    	//firstHitSentBy.setVisibility(INVISIBLE);
//	    	//firstHitWaveSent.setVisibility(INVISIBLE);
//	    	//firstHitSuccessOrFailure.setVisibility(INVISIBLE);
//	    }
		
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1: {
			Intent i = new Intent(GameOver.this, MainMenu.class);
			startActivity(i);
			finish();
		}
			break;
		case R.id.button2: {
			Intent i = new Intent(GameOver.this, Game.class);
			startActivity(i);
			finish();
		}
			break;
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(GameOver.this, MainMenu.class);
		startActivity(intent);
		finish();
	}
	
	
	
	
	
	public void setHitMessageInfo(int hit, TextView view1,TextView view2,TextView view3){

	   switch (hit) {

			case (0):
				view1.setText(" "+R.string.hits_0_name+" name"+R.string.sentBy + "sendername" + Game.hitsAllInfo.hitsInfo[hit].name);	
				break;
			case (1):
				view1.setText(" "+R.string.hits_1_name+" name"+R.string.sentBy + "sendername" + Game.hitsAllInfo.hitsInfo[hit].name);
				break;
			case (2):
				view1.setText(" "+R.string.hits_2_name+" name"+R.string.sentBy + "sendername" + Game.hitsAllInfo.hitsInfo[hit].name);
				break;
			case (4):
				view1.setText(" "+R.string.hits_4_name+" name"+R.string.sentBy + "sendername" + Game.hitsAllInfo.hitsInfo[hit].name);
				break;
			case (5):
				view1.setText(" "+R.string.hits_5_name+" name"+R.string.sentBy + "sendername" + Game.hitsAllInfo.hitsInfo[hit].name);
				break;
			case (6):
				view1.setText(" "+R.string.hits_6_name+" name"+R.string.sentBy + "sendername" + Game.hitsAllInfo.hitsInfo[hit].name);
				break;
			

			default:
				
				break;

			}

		
		
		
	}
	
	
	
}
