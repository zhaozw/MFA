package com.example.mfa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.activities.AchievementsActivity;
import com.example.activities.HitsNew;
import com.example.activities.Market;
import com.example.activities.NewGameOptions;
import com.example.activities.Options;
import com.example.listViewComponents.RivalsItem;
import com.example.listViewComponents.RivalsItemListBaseAdapter;
import com.example.mfa.networking.ConnectionDetector;
import com.example.mfa.networking.DatabaseHandler;
import com.example.mfa.networking.JSONParser;
import com.example.mfa.networking.LoginFunctions;
import com.example.mfa.networking.UserFunctions;
import com.example.objects.PlayerStatsDisplay;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class NewMenu extends Activity {

	Animation phaseLights, floating;
	ImageView towerLights, rock;
	Button startGame, loginLogout;
	ConnectionDetector cd;
	DatabaseHandler db;
	UserFunctions userFunctions;
	boolean online = false;
	AlertDialog.Builder loginDialog;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_new_menu);

		phaseLights = AnimationUtils.loadAnimation(this, R.anim.fade);
		floating = AnimationUtils.loadAnimation(this, R.anim.floating);

		startGame = (Button) findViewById(R.id.start_game);
		loginLogout = (Button) findViewById(R.id.Login_Logout);

		rock = (ImageView) findViewById(R.id.rock);
		towerLights = (ImageView) findViewById(R.id.towerLights);

		cd = new ConnectionDetector(getApplicationContext());
		db = new DatabaseHandler(getApplicationContext());

		userFunctions = new UserFunctions();

		Log.d("getIndividualDetails",
				LoginFunctions.getPlayerStats(context, "k").toString());
		Log.d("getRivalsDetails", LoginFunctions.getRivals(context, "k")
				.toString());

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Login_Logout: {
			if (!cd.isConnectingToInternet()) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"No internet Connection", Toast.LENGTH_LONG);
				toast.show();
			} else if (!userFunctions.isUserLoggedIn(getApplicationContext())) {
				loginChoice();
			} else {
				IndividualGeneralStatsDialog();
			}
			break;
		}
		case R.id.start_game: {
			Intent i = new Intent(NewMenu.this, NewGameOptions.class);
			startActivity(i);
			break;
		}
		case R.id.market: {
			Intent i = new Intent(NewMenu.this, Market.class);
			startActivity(i);
			break;
		}
		case R.id.Options: {
			Intent i = new Intent(NewMenu.this, Options.class);
			startActivity(i);
			break;
		}
		case R.id.Achievements: {
			Intent i = new Intent(NewMenu.this, AchievementsActivity.class);
			startActivity(i);
			break;
		}
		case R.id.newMenuHits: {
			Intent i = new Intent(NewMenu.this, HitsNew.class);
			startActivity(i);
			break;
		}
		case R.id.delete_stats_and_unlocks: {
			PlayerStatsDisplay.clearStats(context);
			break;
		}
		}
	}

	public void loginChoice() {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(context.getString(R.string.LoginOrRegister));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);
		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.login_choice_dialog,
				frameView);

		Button login = (Button) dialoglayout
				.findViewById(R.id.LoginDialog_Login);
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				login();
				alertDialog.dismiss();
			}
		});

		Button register = (Button) dialoglayout
				.findViewById(R.id.LoginDialog_Register);
		register.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				register();
				alertDialog.dismiss();
			}
		});

		Button cancel = (Button) dialoglayout
				.findViewById(R.id.Login_dialog_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});

		alertDialog.show();

	}

	public void login() {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(context.getString(R.string.Login));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);
		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.login_dialog, frameView);

		final EditText emailEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.EmailEnterTextBox);
		final EditText passwordEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.PasswordEnterTextBox);

		Button login = (Button) dialoglayout.findViewById(R.id.LoginButton);
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast toast;
				Log.d("LoginActivity", emailEditTextBox.getText().toString()
						+ "  " + passwordEditTextBox.getText().toString());
				if (emailEditTextBox.getText().toString().length() > 0
						&& passwordEditTextBox.getText().toString().length() > 0) {
					switch (LoginFunctions.attemptLogin(context,
							emailEditTextBox.getText().toString(),
							passwordEditTextBox.getText().toString())) {
					case (1):
						// SUCCESSFULL LOGIN
						toast = Toast.makeText(context,
								context.getString(R.string.LoginSuccessfull),
								Toast.LENGTH_LONG);
						toast.show();

						towerLights.setImageResource(R.drawable.greenlights);
						towerLights.startAnimation(phaseLights);
						rock.startAnimation(floating);
						loginLogout.startAnimation(floating);
						startGame.startAnimation(floating);

						alertDialog.dismiss();
						break;

					case (2):
						toast = Toast.makeText(context, context
								.getString(R.string.IncorrectUsernamePassword),
								Toast.LENGTH_SHORT);
						toast.show();
						break;

					case (3):
						toast = Toast.makeText(context,
								context.getString(R.string.ConnectionError),
								Toast.LENGTH_SHORT);
						toast.show();
						break;

					case (4):
						toast = Toast.makeText(context,
								context.getString(R.string.LoginFailed),
								Toast.LENGTH_SHORT);
						toast.show();
						break;

					}
				} else {
					toast = Toast.makeText(context,
							context.getString(R.string.EnterTextNextTime),
							Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		Button cancel = (Button) dialoglayout
				.findViewById(R.id.CancelLoginButton);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});

		alertDialog.show();

	}

	public void register() {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(context.getString(R.string.Register));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);
		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.register_dialog,
				frameView);

		final EditText emailEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.emailEditTextRegister);
		final EditText passwordEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.passwordEditTextRegister);
		final EditText confirmPasswordEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.confirmPasswordEditTextRegister);
		final EditText usernameEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.userNameEditTextRegister);

		Button login = (Button) dialoglayout
				.findViewById(R.id.RegisterDialogRegister);
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast toast;
				Log.d("LoginActivity", usernameEditTextBox.getText().toString()
						+ "  " + emailEditTextBox.getText().toString() + "  "
						+ "" + passwordEditTextBox.getText().toString() + " "
						+ confirmPasswordEditTextBox.getText().toString());

				if (passwordEditTextBox
						.getText()
						.toString()
						.compareTo(
								confirmPasswordEditTextBox.getText().toString()) == 0) {
					if (emailEditTextBox.getText().toString().length() > 0
							&& passwordEditTextBox.getText().toString()
									.length() > 0
							&& usernameEditTextBox.getText().toString()
									.length() > 0) {
						switch (LoginFunctions.registerAccount(context,
								usernameEditTextBox.getText().toString(),
								emailEditTextBox.getText().toString(),
								passwordEditTextBox.getText().toString())) {
						case (1): // SUCCESSFULL REGISTER
							toast = Toast.makeText(
									context,
									context.getString(R.string.RegistrationSuccesfull),
									Toast.LENGTH_LONG);
							toast.show();
							towerLights
									.setImageResource(R.drawable.greenlights);
							towerLights.startAnimation(phaseLights);
							rock.startAnimation(floating);
							loginLogout.startAnimation(floating);
							startGame.startAnimation(floating);

							alertDialog.dismiss();
							alertDialog.dismiss();
							break;
						case (2):

							break;
						case (3):

							break;

						default:

						}
					} else {
						toast = Toast.makeText(context,
								context.getString(R.string.EnterTextNextTime),
								Toast.LENGTH_LONG);
						toast.show();
					}

				} else {
					toast = Toast.makeText(context,
							context.getString(R.string.PasswordsDontMatch),
							Toast.LENGTH_LONG);
					toast.show();
					passwordEditTextBox.setText("");
					confirmPasswordEditTextBox.setText("");
				}
			}
		});

		Button cancel = (Button) dialoglayout
				.findViewById(R.id.RegisterDialogCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});

		alertDialog.show();

	}

	public void IndividualGeneralStatsDialog() {

		UserFunctions userFunctions = new UserFunctions();
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
				PlayerStatsDisplay.displayAllEarnedTrophys(context);
			}
		});

		Button hitStatsButton = (Button) dialoglayout
				.findViewById(R.id.HitsStatsButton);
		hitStatsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				PlayerStatsDisplay.displayHitsStats(context);
			}
		});

		Button friendsButton = (Button) dialoglayout
				.findViewById(R.id.statsDialogFriendsButton);
		friendsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				PlayerStatsDisplay.displayAllEarnedTrophys(context);
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

				towerLights.setImageResource(R.drawable.redlights);
				towerLights.startAnimation(floating);
				towerLights.setAlpha((float) 1.0);

				// towerLights.setImageAlpha(255);
				rock.startAnimation(floating);
				loginLogout.startAnimation(floating);
				startGame.startAnimation(floating);
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

	public void displayHitsStats(final Context context) {

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

	public void displayRivals(final Context context) {

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

	public HashMap<String, String> getPlayerStats(Context context, String email) {

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser
				.getJSONFromUrl("http://cofactorstudios.com/Player_Info.php?email="
						+ email);

		HashMap<String, String> map = new HashMap<String, String>();

		try {

			// Getting Array of Contacts
			JSONArray GameDetails = json.getJSONArray("Game");

			JSONObject s = GameDetails.getJSONObject(0);

			map.put("Email", s.getString("Email"));
			map.put("Username", s.getString("Username"));
			map.put("Highscore", s.getString("Highscore"));
			map.put("Money", s.getString("Money"));
			map.put("HitsID", s.getString("HitsID"));

			Log.d("New Game Options", "Done");

		} catch (JSONException e) {
			e.printStackTrace();
			Toast toast = Toast.makeText(context, "GetDetailsFailed",
					Toast.LENGTH_LONG);
			toast.show();
			return map;
		}

		return map;
	}

	@Override
	public void onResume() {
		super.onResume(); // Always call the superclass method first

		if (!cd.isConnectingToInternet()) {
			Toast toast = Toast
					.makeText(
							getApplicationContext(),
							"No internet Connection, click on cell tower to test connection",
							Toast.LENGTH_LONG);
			toast.show();
			towerLights.setImageResource(R.drawable.redlights);
			towerLights.startAnimation(floating);
		} else if (!userFunctions.isUserLoggedIn(getApplicationContext())) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"No User Logged in, click on cell tower Log in",
					Toast.LENGTH_LONG);
			toast.show();
			towerLights.setImageResource(R.drawable.redlights);
			towerLights.startAnimation(floating);

		} else {
			towerLights.startAnimation(phaseLights);
			// lights defalut to green

		}

		rock.startAnimation(floating);
		loginLogout.startAnimation(floating);
		startGame.startAnimation(floating);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_new_menu, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Quit Application?")
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener).show();
		return;
	}

	DialogInterface.OnClickListener logOutConfirm = new DialogInterface.OnClickListener() {
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