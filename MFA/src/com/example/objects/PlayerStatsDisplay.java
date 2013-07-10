package com.example.objects;

import java.util.ArrayList;
import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.listViewComponents.ImageGridAdapter;
import com.example.listViewComponents.RivalsItem;
import com.example.listViewComponents.RivalsItemListBaseAdapter;
import com.example.mfa.R;
import com.example.mfa.networking.UserFunctions;

public class PlayerStatsDisplay {

	public static void IndividualGeneralStatsDialog(final Context context) {

		UserFunctions userFunctions;
		userFunctions = new UserFunctions();
		userFunctions.getName(context);

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(userFunctions.getName(context));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);

		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.stats_dialog_xml,
				frameView);

		SharedPreferences stats = context.getSharedPreferences("StatsFile", 0);

		TextView asteroidsKilled = (TextView) dialoglayout
				.findViewById(R.id.AsteroidsKilledActual);
		asteroidsKilled.setText("" + stats.getInt("TotalAsteroidsKilled", 0));

		TextView highestWave = (TextView) dialoglayout
				.findViewById(R.id.HighestWaveActual);
		highestWave.setText("" + stats.getInt("HighestWave", 0));

		TextView totalScore = (TextView) dialoglayout
				.findViewById(R.id.TotalScoreActual);
		totalScore.setText("" + stats.getInt("TotalScore", 0));

		TextView totalAccuracy = (TextView) dialoglayout
				.findViewById(R.id.AccuracyActual);
		totalAccuracy.setText("" + stats.getFloat("TotalAccuracy", 0));

		TextView totalShots = (TextView) dialoglayout
				.findViewById(R.id.shotsFiredActual);
		totalShots.setText("" + stats.getInt("TotalShotsFired", 0));

		Button achievementsButton = (Button) dialoglayout
				.findViewById(R.id.AchievementsButton);
		achievementsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				displayAllEarnedTrophys(context);
			}
		});

		Button hitStatsButton = (Button) dialoglayout
				.findViewById(R.id.HitsStatsButton);
		hitStatsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				displayHitsStats(context);
			}
		});

		Button friendsButton = (Button) dialoglayout
				.findViewById(R.id.statsDialogFriendsButton);
		friendsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				displayAllEarnedTrophys(context);
			}
		});

		Button rivalsButton = (Button) dialoglayout
				.findViewById(R.id.statsDialogRivalsButton);
		rivalsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				displayRivals(context);
			}
		});

		Button logoutButton = (Button) dialoglayout
				.findViewById(R.id.statsDialogLogoutButton);
		logoutButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				UserFunctions userFunctions;
				userFunctions = new UserFunctions();
				userFunctions.logoutUser(context);
				Toast toast = Toast
						.makeText(
								context,
								"The game will be more fun logged in. You should try it.",
								Toast.LENGTH_LONG);
				toast.show();
				alertDialog.dismiss();

			}
		});

		alertDialog.show();

	}

	public static void displayHitsStats(final Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(context.getString(R.string.HitsStats));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);

		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.hits_stats_dialog,
				frameView);

		TextView hitsSentActual = (TextView) dialoglayout
				.findViewById(R.id.HitsSentActual);
		hitsSentActual.setText("1");

		TextView HitsSentSuccessful = (TextView) dialoglayout
				.findViewById(R.id.HitsSentSuccessfulActual);
		HitsSentSuccessful.setText("2");

		TextView FailedHitsSent = (TextView) dialoglayout
				.findViewById(R.id.FailedHitsSentActual);
		FailedHitsSent.setText("3");

		TextView SentHitsSuccessRate = (TextView) dialoglayout
				.findViewById(R.id.SentHitsSuccessRateActual);
		SentHitsSuccessRate.setText("4");

		TextView HitsRecieved = (TextView) dialoglayout
				.findViewById(R.id.HitsRecievedActual);
		HitsRecieved.setText("5");

		TextView HitsFailedAgainst = (TextView) dialoglayout
				.findViewById(R.id.HitsFailedAgainstActual);
		HitsFailedAgainst.setText("6");

		TextView HitsAvoided = (TextView) dialoglayout
				.findViewById(R.id.HitsAvoidedActual);
		HitsAvoided.setText("7");

		TextView HitsFailureRate = (TextView) dialoglayout
				.findViewById(R.id.HitsFailureRateActual);
		HitsFailureRate.setText("8");

		Button galleryButton = (Button) dialoglayout
				.findViewById(R.id.SuccessfulHitsGalleryButton);
		galleryButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast toast = Toast.makeText(context, "Display Hits Gallery",
						Toast.LENGTH_SHORT);
				toast.show();
			}
		});

		Button hitsOutButton = (Button) dialoglayout
				.findViewById(R.id.hitsStatsHitsCurrentlyOutButton);
		hitsOutButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast toast = Toast.makeText(context,
						"Display Currently Out Hits", Toast.LENGTH_SHORT);
				toast.show();
			}
		});

		alertDialog.show();

	}

	public static void displayRivals(final Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(context.getString(R.string.Rivals));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);

		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.rivals_dialog, frameView);

		final ListView lv1 = (ListView) dialoglayout
				.findViewById(R.id.RivalsListView);
		lv1.setAdapter(new RivalsItemListBaseAdapter(context,
				GetRivalsForListView()));

		lv1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {

			}
		});

		alertDialog.show();

	}

	private static ArrayList<RivalsItem> GetRivalsForListView() {
		ArrayList<RivalsItem> results = new ArrayList<RivalsItem>();

		Random r = new Random();
		RivalsItem rivalsItem;
		for (int k = 0; k <= 30; k++) {

			rivalsItem = new RivalsItem();

			rivalsItem.setUserName(NamesAndMessages.randomName());

			rivalsItem.sethitId("" + r.nextInt(50000));

			rivalsItem.setHitSentDate((r.nextInt(30) + 1) + "/" + r.nextInt(12)
					+ "/" + r.nextInt(1000));

			results.add(rivalsItem);

		}

		return results;
	}

	public void displayAchievements() {

	}

	public static void displayAllEarnedTrophys(Context context) {

		// achievementDialog;
		final ArrayList<Integer> earnedTrophys = new ArrayList<Integer>();

		SharedPreferences trophys = context.getSharedPreferences("TrophysFile",
				0);

		for (int k = 0; k < 30; k++) {
			if (trophys.getBoolean("trophy_" + k + "_unlocked", false))
				earnedTrophys.add(k);
		}

		displayTrophyDialog(earnedTrophys, context);
	}

	public static void displayRecentlyEarnedTrophys(
			final ArrayList<Integer> earnedTrophys, Context context) {

		displayTrophyDialog(earnedTrophys, context);

	}

	public static void displayTrophyDialog(
			final ArrayList<Integer> earnedTrophys, final Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle("    "
						+ context.getString(R.string.AchievementsUnlocked));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);

		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(
				R.layout.achievement_unlocked_dialog, frameView);

		final GridView gridview = (GridView) dialoglayout
				.findViewById(R.id.GridViewAchievementsUnlocked);

		gridview.setAdapter(new ImageGridAdapter(context,
				GetTrophyIconsResults(earnedTrophys, context)));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				IndividualTrophyView(position, context, gridview, earnedTrophys);
			}
		});

		alertDialog.show();

	}

	private static ArrayList<Integer> GetTrophyIconsResults(
			ArrayList<Integer> earnedTrophys, Context context) {

		ArrayList<Integer> results = new ArrayList<Integer>();

		Integer item_details;
		for (int k = 0; k < earnedTrophys.size(); k++) {

			if (earnedTrophys.get(k) < 12 || earnedTrophys.get(k) > 19)
				item_details = context.getResources().getIdentifier(
						"trophy_icon_" + earnedTrophys.get(k), "drawable",
						context.getPackageName());
			else
				item_details = context.getResources().getIdentifier(
						"trophy_icon_generic", "drawable",
						context.getPackageName());

			results.add(item_details);
		}

		return results;
	}

	public static void IndividualTrophyView(final int item, Context context,
			GridView gridview, ArrayList<Integer> earnedTrophys) {

		AlertDialog.Builder IndividualTrophyView = new AlertDialog.Builder(
				context);
		IndividualTrophyView.setTitle(context.getString(context.getResources()
				.getIdentifier("Trophy_" + earnedTrophys.get(item) + "_Name",
						"string", context.getPackageName())));
		IndividualTrophyView.setMessage(context.getString(context
				.getResources().getIdentifier(
						"Trophy_" + earnedTrophys.get(item) + "_Description",
						"string", context.getPackageName())));
		IndividualTrophyView.setIcon((int) ((ImageGridAdapter) gridview
				.getAdapter()).getItemId(item));
		IndividualTrophyView.show();

	}

	public static void clearStats(Context context) {

		SharedPreferences trophys = context.getSharedPreferences("TrophysFile",
				0);
		SharedPreferences.Editor editor = trophys.edit();

		for (int k = 0; k < 30; k++) {
			editor.putBoolean("trophy_" + k + "_unlocked", false);

		}

		editor.commit();

		SharedPreferences market = context.getSharedPreferences("UnlocksFile",
				0);
		SharedPreferences.Editor editor2 = market.edit();

		for (int k = 0; k < 31; k++) {
			editor2.putBoolean("Market_Item_" + k + "_Purchased", false);
		}

		editor2.putInt("Cash", 0);
		editor2.commit();

		SharedPreferences stats = context.getSharedPreferences("StatsFile", 0);
		SharedPreferences.Editor editor3 = stats.edit();
		editor3.putInt("TotalAsteroidsKilled", 0);
		editor3.putInt("TotalShotsFired", 0);
		editor3.putFloat("TotalAccuracy", 0);
		editor3.putInt("TotalScore", 0);
		editor3.putInt("HighestWave", 0);
		editor3.commit();

		SharedPreferences prefs = context
				.getSharedPreferences("MyPrefsFile", 0);
		SharedPreferences.Editor editor4 = prefs.edit();
		editor4.putInt("ec1", 0);
		editor4.putInt("ec2", 0);
		editor4.putInt("ec3", 0);
		editor4.putInt("ec4", 0);
		editor4.putInt("ec5", 0);
		editor4.putInt("ec6", 0);
		editor4.putInt("thrusterColor", 0);
		editor4.putInt("txtc", 0);
		editor4.putInt("lightColor", 0);
		editor4.commit();

		SharedPreferences customPrefs = context.getSharedPreferences(
				"CustomGamePrefs", 0);
		SharedPreferences.Editor editor5 = customPrefs.edit();
		editor5.putBoolean("god", false);
		editor5.putBoolean("intense", false);
		editor5.putBoolean("shootFaster", false);
		editor5.putBoolean("sineBullets", false);
		editor5.putBoolean("slowMo", false);
		editor5.putBoolean("god", false);
		editor5.putInt("hit1", -1);
		editor5.putInt("hit1", -1);
		editor5.putInt("hit1", -1);
		editor5.commit();

	}

	public static void checkForUnlockedTrophies(Context context, int astKilled,
			int waveReached, int shotsFired, int accuracy, int score) {

		ArrayList<Integer> trophysEarnedThisGame = new ArrayList<Integer>();

		SharedPreferences stats = context.getSharedPreferences("StatsFile", 0);
		SharedPreferences trophys = context.getSharedPreferences("TrophysFile",
				0);
		SharedPreferences unlocks = context.getSharedPreferences("UnlocksFile",
				0);
		checkTotalAsteroidsKilledTrophys(trophysEarnedThisGame, trophys, stats,
				context);
		checkCurrentCashTrophys(trophysEarnedThisGame, trophys, unlocks,
				context);
		checkSingleGamePointsTrophys(trophysEarnedThisGame, score, trophys,
				context);
		checkFailureTrophy(trophysEarnedThisGame, shotsFired, astKilled,
				trophys, context);
		checkWaveReachedTrophys(trophysEarnedThisGame, waveReached, trophys,
				context);

		if (trophysEarnedThisGame.size() > 0)
			PlayerStatsDisplay.displayRecentlyEarnedTrophys(
					trophysEarnedThisGame, context);

	}

	public static void checkTotalAsteroidsKilledTrophys(
			ArrayList<Integer> trophysEarnedThisGame,
			SharedPreferences trophys, SharedPreferences stats, Context context) {

		for (int k = 0; k <= 11; k++) {
			if (trophys.getBoolean("trophy_" + k + "_unlocked", false) == false) {
				if (stats.getInt("TotalAsteroidsKilled", 0) >= Integer
						.parseInt(context.getString(context.getResources()
								.getIdentifier("Trophy_" + k + "_Requirement",
										"string", context.getPackageName())))) {
					trophysEarnedThisGame.add(k);
					SharedPreferences.Editor editor = trophys.edit();
					editor.putBoolean("trophy_" + k + "_unlocked", true);
					editor.commit();
				}
			}
		}
	}

	public static void checkCurrentCashTrophys(
			ArrayList<Integer> trophysEarnedThisGame,
			SharedPreferences trophys, SharedPreferences unlocks,
			Context context) {

		for (int k = 12; k <= 15; k++) {
			if (trophys.getBoolean("trophy_" + k + "_unlocked", false) == false) {
				if (unlocks.getInt("Cash", 0) >= Integer.parseInt(context
						.getString(context.getResources().getIdentifier(
								"Trophy_" + k + "_Requirement", "string",
								context.getPackageName())))) {
					trophysEarnedThisGame.add(k);
					SharedPreferences.Editor editor = trophys.edit();
					editor.putBoolean("trophy_" + k + "_unlocked", true);
					editor.commit();
				}
			}
		}
	}

	public static void checkSingleGamePointsTrophys(
			ArrayList<Integer> trophysEarnedThisGame, int score,
			SharedPreferences trophys, Context context) {

		for (int k = 16; k <= 18; k++) {
			if (trophys.getBoolean("trophy_" + k + "_unlocked", false) == false) {
				if (score >= Integer.parseInt(context.getString(context
						.getResources().getIdentifier(
								"Trophy_" + k + "_Requirement", "string",
								context.getPackageName())))) {
					trophysEarnedThisGame.add(k);
					SharedPreferences.Editor editor = trophys.edit();
					editor.putBoolean("trophy_" + k + "_unlocked", true);
					editor.commit();
				}
			}
		}
	}

	public static void checkFailureTrophy(
			ArrayList<Integer> trophysEarnedThisGame, int shotsFired,
			int astKilled, SharedPreferences trophys, Context context) {

		if (trophys.getBoolean("trophy_" + 19 + "_unlocked", false) == false) {
			if (shotsFired <= Integer.parseInt(context.getString(context
					.getResources().getIdentifier(
							"Trophy_" + 19 + "_Requirement", "string",
							context.getPackageName())))) {
				trophysEarnedThisGame.add(19);
				SharedPreferences.Editor editor = trophys.edit();
				editor.putBoolean("trophy_" + 19 + "_unlocked", true);
				editor.commit();
			}
		}
	}

	public static void checkWaveReachedTrophys(
			ArrayList<Integer> trophysEarnedThisGame, int waveReached,
			SharedPreferences trophys, Context context) {

		for (int k = 20; k <= 29; k++) {
			if (trophys.getBoolean("trophy_" + k + "_unlocked", false) == false) {
				if (waveReached > Integer.parseInt(context.getString(context
						.getResources().getIdentifier(
								"Trophy_" + k + "_Requirement", "string",
								context.getPackageName())))) {
					trophysEarnedThisGame.add(k);
					SharedPreferences.Editor editor = trophys.edit();
					editor.putBoolean("trophy_" + k + "_unlocked", true);
					editor.commit();
				}
			}
		}
	}

}
