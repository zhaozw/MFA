package com.example.activities;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mfa.NewMenu;
import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;
import com.example.mfa.networking.LoginFunctions;
import com.example.mfa.networking.UserFunctions;
import com.example.objects.PlayerStatsDisplay;

public class GameOver extends Activity {
	TextView CG_Score, CG_AsteroidsKilled, CG_ShotsFired, CG_Time,
			CG_WaveReached, T_Score, T_AsteroidsKilled, T_ShotsFired,
			T_WaveReached, T_Time;
	SharedPreferences stats, unlocks, UnprocessedHits;
	SharedPreferences.Editor editor;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_game_over);

		if (MGP.hitsInGame.size() > 0)
			deactivateHits();

		// ADDING THE GAME SCORE TO CASH FOR USE
		unlocks = getSharedPreferences("UnlocksFile", 0);
		SharedPreferences.Editor unlocksEditor = unlocks.edit();
		unlocksEditor.putInt("Cash", unlocks.getInt("Cash", 0) + MGP.score);
		unlocksEditor.commit();

		stats = getSharedPreferences("StatsFile", 0);
		editor = stats.edit();
		editor.putInt(
				"TotalAsteroidsKilled",
				MGP.totalAsteroidsKilled
						+ stats.getInt("TotalAsteroidsKilled", 0));
		editor.putInt("TotalShotsFired",
				MGP.countShots + stats.getInt("TotalShotsFired", 0));
		if (MGP.countShots > 0)
			editor.putFloat(
					"TotalAccuracy",
					(MGP.totalAsteroidsKilled / MGP.countShots)
							+ stats.getFloat("TotalAccuracy", 0));

		editor.putLong("TotalTime",
				MGP.totalTime + stats.getLong("TotalTime", 0));

		editor.putInt("TotalScore", MGP.score + stats.getInt("TotalScore", 0));
		if (MGP.wave > stats.getInt("HighestWave", 0))
			editor.putInt("HighestWave", MGP.wave);

		int accuracy = 0;
		if (MGP.countShots > 0)
			accuracy = (MGP.totalAsteroidsKilled / MGP.countShots);

		if (MGP.countShots > 0)
			editor.putFloat("TotalAccuracy",
					accuracy + stats.getFloat("TotalAccuracy", 0));

		editor.commit();

		CG_Score = (TextView) findViewById(R.id.CurrentGameScore);
		CG_AsteroidsKilled = (TextView) findViewById(R.id.CurrentGameAsteroidsKilled);
		CG_ShotsFired = (TextView) findViewById(R.id.CurrentGameShotsFired);
		CG_Time = (TextView) findViewById(R.id.CurrentGameTime);
		CG_WaveReached = (TextView) findViewById(R.id.CurrentGameWaveReached);
		T_Score = (TextView) findViewById(R.id.GameOverTotalScore);
		T_AsteroidsKilled = (TextView) findViewById(R.id.GameOverTotalAsteroidsKilled);
		T_ShotsFired = (TextView) findViewById(R.id.GameOverTotalShotsFired);
		T_WaveReached = (TextView) findViewById(R.id.GameOverHighestWave);
		T_Time = (TextView) findViewById(R.id.GameOverTotalTime);

		CG_Score.setText("" + MGP.score);
		CG_AsteroidsKilled.setText("" + MGP.totalAsteroidsKilled);
		CG_ShotsFired.setText("" + MGP.countShots);
		CG_WaveReached.setText("" + MGP.wave);
		T_Score.setText("" + stats.getInt("TotalScore", MGP.score));
		T_AsteroidsKilled
				.setText(""
						+ stats.getInt("TotalAsteroidsKilled",
								MGP.totalAsteroidsKilled));
		T_ShotsFired.setText(""
				+ stats.getInt("TotalShotsFired", MGP.countShots));
		T_WaveReached.setText("" + stats.getInt("HighestWave", MGP.wave));
		NumberFormat numberFormat = new DecimalFormat("00000000");
		CG_Time.setText("" + numberFormat.format(MGP.totalTime));
		T_Time.setText(""
				+ numberFormat.format(stats.getLong("TotalTime", MGP.totalTime)));

		PlayerStatsDisplay.checkForUnlockedTrophies(context,
				MGP.totalAsteroidsKilled, MGP.wave, MGP.countShots, accuracy,
				MGP.score);

		if (MGP.hitsInGame.size() > 0)
			HitsSentDialog();

	}

	public void deactivateHits() {

		for (int k = 0; k < MGP.hitsInGame.size(); k++) {
			if (LoginFunctions.deactivateHit(context,
					MGP.hitsInGame.get(k).HitID, MGP.hitsInGame.get(k).hitType)) {
				Toast toast = Toast.makeText(context,
						"HitsSuccessfullyDeactivated", Toast.LENGTH_LONG);
				toast.show();

			} else {
				UnprocessedHits = getSharedPreferences("UnprocessedHits", 0);
				SharedPreferences.Editor UnprocessedHitsEditor = UnprocessedHits
						.edit();
				UnprocessedHitsEditor.putInt("Hit" + k + "Type",
						MGP.hitsInGame.get(k).hitType);
				UnprocessedHitsEditor.putString("Hit" + k + "HitID",
						MGP.hitsInGame.get(k).HitID);
				UnprocessedHitsEditor.commit();
			}
		}
	}

	public void initializeStats() {

	}

	public void HitsSentDialog() {

		UserFunctions userFunctions = new UserFunctions();
		userFunctions.getName(context);

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(getString(R.string.HitsSent));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);

		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.hits_sent_dialog,
				frameView);

		TextView hit1Sender = (TextView) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_1_sender);
		TextView hit2Sender = (TextView) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_2_sender);
		TextView hit3Sender = (TextView) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_3_sender);
		TextView hit1Name = (TextView) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_1_hit_sent);
		TextView hit2Name = (TextView) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_2_hit_sent);
		TextView hit3Name = (TextView) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_3_hit_sent);
		TextView hit1Message = (TextView) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_1_message);
		TextView hit2Message = (TextView) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_2_message);
		TextView hit3Message = (TextView) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_3_message);
		TextView hit1Success = (TextView) dialoglayout
				.findViewById(R.id.hits_sender_dialog_hit_1_success);
		TextView hit2Success = (TextView) dialoglayout
				.findViewById(R.id.hits_sender_dialog_hit_2_success);
		TextView hit3Success = (TextView) dialoglayout
				.findViewById(R.id.hits_sender_dialog_hit_3_success);

		Button hit1SenderStats = (Button) dialoglayout
				.findViewById(R.id.hits_sent_dialog_hit_1_sender_stats);
		Button hit2SenderStats = (Button) dialoglayout
				.findViewById(R.id.hits_sender_dialog_hit_2_sender_stats);
		Button hit3SenderStats = (Button) dialoglayout
				.findViewById(R.id.hits_sender_dialog_hit_3_sender_stats);

		if (MGP.hitsInGame.size() == 1) {

			hit1Sender.setText(MGP.hitsInGame.get(0).name);
			hit1Name.setText(getString(R.string.HitSent)
					+ " "
					+ getString(getResources().getIdentifier(
							"hits_" + MGP.hitsInGame.get(0).hitType + "_name",
							"string", getPackageName())));
			if (MGP.hitsInGame.get(0).succeeded)
				hit1Success.setText(R.string.HitSuccessful);
			else
				hit1Success.setText(R.string.HitNotSuccessful);
			hit1Message.setText(getString(R.string.Message) + " "
					+ MGP.hitsInGame.get(0).message);

			hit2Sender.setVisibility(View.GONE);
			hit3Sender.setVisibility(View.GONE);
			hit2Message.setVisibility(View.GONE);
			hit3Message.setVisibility(View.GONE);
			hit2Name.setVisibility(View.GONE);
			hit3Name.setVisibility(View.GONE);
			hit2Success.setVisibility(View.GONE);
			hit3Success.setVisibility(View.GONE);
			hit2SenderStats.setVisibility(View.GONE);
			hit3SenderStats.setVisibility(View.GONE);

		} else if (MGP.hitsInGame.size() == 2) {

			hit1Sender.setText(MGP.hitsInGame.get(0).name);
			hit1Name.setText(getString(R.string.HitSent)
					+ " "
					+ getString(getResources().getIdentifier(
							"hits_" + MGP.hitsInGame.get(0).hitType + "_name",
							"string", getPackageName())));
			if (MGP.hitsInGame.get(0).succeeded)
				hit1Success.setText(R.string.HitSuccessful);
			else
				hit1Success.setText(R.string.HitNotSuccessful);
			hit1Message.setText(getString(R.string.Message) + " "
					+ MGP.hitsInGame.get(0).message);

			hit2Sender.setText(MGP.hitsInGame.get(1).name);
			hit2Name.setText(getString(R.string.HitSent)
					+ " "
					+ getString(getResources().getIdentifier(
							"hits_" + MGP.hitsInGame.get(1).hitType + "_name",
							"string", getPackageName())));
			if (MGP.hitsInGame.get(1).succeeded)
				hit2Success.setText(R.string.HitSuccessful);
			else
				hit2Success.setText(R.string.HitNotSuccessful);
			hit2Message.setText(getString(R.string.Message) + " "
					+ MGP.hitsInGame.get(1).message);

			hit3Name.setVisibility(View.GONE);
			hit3Success.setVisibility(View.GONE);
			hit3SenderStats.setVisibility(View.GONE);
			hit3Message.setVisibility(View.GONE);
			hit3Sender.setVisibility(View.GONE);
		} else {
			hit1Sender.setText(MGP.hitsInGame.get(0).name);
			hit1Name.setText(getString(R.string.HitSent)
					+ " "
					+ getString(getResources().getIdentifier(
							"hits_" + MGP.hitsInGame.get(0).hitType + "_name",
							"string", getPackageName())));
			if (MGP.hitsInGame.get(0).succeeded)
				hit1Success.setText(R.string.HitSuccessful);
			else
				hit1Success.setText(R.string.HitNotSuccessful);
			hit1Message.setText(getString(R.string.Message) + " "
					+ MGP.hitsInGame.get(0).message);

			hit2Sender.setText(MGP.hitsInGame.get(1).name);
			hit2Name.setText(getString(R.string.HitSent)
					+ " "
					+ getString(getResources().getIdentifier(
							"hits_" + MGP.hitsInGame.get(1).hitType + "_name",
							"string", getPackageName())));
			if (MGP.hitsInGame.get(1).succeeded)
				hit2Success.setText(R.string.HitSuccessful);
			else
				hit2Success.setText(R.string.HitNotSuccessful);
			hit2Message.setText(getString(R.string.Message) + " "
					+ MGP.hitsInGame.get(1).message);

			hit3Sender.setText(MGP.hitsInGame.get(2).name);
			hit3Name.setText(getString(R.string.HitSent)
					+ " "
					+ getString(getResources().getIdentifier(
							"hits_" + MGP.hitsInGame.get(2).hitType + "_name",
							"string", getPackageName())));
			if (MGP.hitsInGame.get(2).succeeded)
				hit3Success.setText(R.string.HitSuccessful);
			else
				hit3Success.setText(R.string.HitNotSuccessful);
			hit3Message.setText(getString(R.string.Message) + " "
					+ MGP.hitsInGame.get(2).message);
		}

		// Button achievementsButton = (Button)
		// dialoglayout.findViewById(R.id.AchievementsButton);
		// achievementsButton.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View v) {
		// PlayerStatsDisplay.displayAllEarnedTrophys(context);
		// }
		// });

		alertDialog.show();

	}

	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.button1: {
	// Intent i = new Intent(GameOver.this, NewMenu.class);
	// startActivity(i);
	// finish();
	// }
	// break;
	// case R.id.button2: {
	// Intent i = new Intent(GameOver.this, Game.class);
	// startActivity(i);
	// finish();
	// }
	// break;
	// }
	// }

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(GameOver.this, NewMenu.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

}
