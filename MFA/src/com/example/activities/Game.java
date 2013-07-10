package com.example.activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.HitsObjects.HitsAllInfo;
import com.example.HitsObjects.HitsInfo;
import com.example.mfa.NewMenu;
import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;
import com.example.mfa.networking.ConnectionDetector;
import com.example.mfa.networking.DatabaseHandler;
import com.example.mfa.networking.JSONParser;
import com.example.mfa.networking.LoginFunctions;
import com.example.mfa.networking.UserFunctions;

public class Game extends Activity {
	public MGP gamePanel;
	public Display display;
	public float density;
	public static float dpHeight;
	public static float dpWidth;
	public static DisplayMetrics dm;
	public static HitsAllInfo hitsAllInfo;
	public static int ship, lightColor, ec1, ec2, ec3, ec4, ec5, ec6,
			thrusterColor, txtc;
	public static boolean god, intense, alwaysShootFast, alwaysSineBullets,
			alwaysSlowmo;
	public static boolean customGame = false;

	private PowerManager.WakeLock wl;
	public static final String PREFS_NAME = "MyPrefsFile";
	public String hits;
	public static Activity activity;
	public static Boolean online;
	public static Bitmap playerShip;
	ConnectionDetector cd;
	public final Context context = this;

	String url;
	public HashMap<String, String> map = new HashMap<String, String>();

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		cd = new ConnectionDetector(getApplicationContext());
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		lightColor = settings.getInt("lightColor", 0);
		ec1 = settings.getInt("ec1", 1);
		ec2 = settings.getInt("ec2", 2);
		ec3 = settings.getInt("ec3", 3);
		ec4 = settings.getInt("ec4", 4);
		ec5 = settings.getInt("ec5", 2);
		ec6 = settings.getInt("ec6", 0);
		thrusterColor = settings.getInt("thrusterColor", 3);
		txtc = settings.getInt("txtc", 3);
		ship = settings.getInt("ship_choice", 0);
		playerShip = BitmapFactory.decodeResource(
				getResources(),
				getResources().getIdentifier(
						"ship_" + settings.getInt("ship_choice", 0),
						"drawable", getPackageName()));

		settings = getSharedPreferences("CustomGamePrefs", 0);
		Intent intent = getIntent();
		customGame = intent.getBooleanExtra("customGame", false);
		Log.d("Game: ", "" + intent.getBooleanExtra("customGame", false));

		Log.d("Game: ", "Light Color =" + lightColor);

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");

		display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);

		float density = getResources().getDisplayMetrics().density;
		float dpHeight = outMetrics.heightPixels / density;
		float dpWidth = outMetrics.widthPixels / density;

		dm = getResources().getDisplayMetrics();

		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		UserFunctions userFunctions = new UserFunctions();

		url = "http://cofactorstudios.com/Get_Hits.php?hits="
				+ LoginFunctions.getPlayerHitsID(context,
						userFunctions.getEmail(context));
		Log.d("Game",
				LoginFunctions.getPlayerHitsID(context,
						userFunctions.getEmail(context)));

		activity = this;

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		hitsAllInfo = new HitsAllInfo(context);

		if (customGame) {

			ArrayList<HitsInfo> customHits = new ArrayList<HitsInfo>();
			if (settings.getInt("hit1", -1) > -1)
				customHits.add(new HitsInfo(settings.getInt("hit1", -1), true));
			if (settings.getInt("hit2", -1) > -1)
				customHits.add(new HitsInfo(settings.getInt("hit2", -1), true));
			if (settings.getInt("hit2", -1) > -1)
				customHits.add(new HitsInfo(settings.getInt("hit3", -1), true));
			hitsAllInfo.initialize2(customHits);

			god = settings.getBoolean("god", false);
			intense = settings.getBoolean("intense", false);
			alwaysShootFast = settings.getBoolean("shootFaster", false);
			alwaysSineBullets = settings.getBoolean("sineBullets", false);
			alwaysSlowmo = settings.getBoolean("slowMo", false);

		} else if (!cd.isConnectingToInternet()) {

			Toast toast = Toast.makeText(getApplicationContext(),
					"No internet Connection, starting Custom Game",
					Toast.LENGTH_LONG);
			toast.show();
			Log.d("New Game Options", "Starting Custom Game");
			Intent j = new Intent(Game.this, CustomGameOptions.class);
			j.putExtra("customGame", true);
			j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(j);

		} else {

			online = true;

			JSONArray Hits = null;

			// Creating JSON Parser instance
			JSONParser jParser = new JSONParser();

			// getting JSON string from URL
			JSONObject json = jParser.getJSONFromUrl(url);

			Log.d("Game,url + HitID", url);
			try {
				// Getting Array of Contacts
				Hits = json.getJSONArray("Hits");

				JSONObject s = Hits.getJSONObject(0);

				String hitid = s.getString("HitsID");

				map.put("HitsID", hitid);

				Log.d("NewGameOptions json array", s.toString());
				// looping through All Contacts
				for (int k = 0; k <= 15; k++) {
					map.put("Hit" + k + "From", s.getString("Hit" + k + "From"));
					map.put("Hit" + k + "Active",
							s.getString("Hit" + k + "Active"));
					map.put("Hit" + k + "Msg", s.getString("Hit" + k + "Msg"));
					;
				}
				Log.d("NewGameOptions map", map.toString());

				Log.d("New Game Options", "Done");
				// }
			} catch (JSONException e) {
				e.printStackTrace();
			}

			Log.d("Game: ", "Initailizing Hits Info");
			hitsAllInfo.initializeOnline(map);

		}

		setContentView(gamePanel = new MGP(this));
	}

	public void gameOver() {
		MGP.hitsInGame = hitsAllInfo.hitsThatArrived;
		Intent i = new Intent(Game.this, GameOver.class);
		startActivity(i);
	}

	@Override
	protected void onPause() {
		super.onPause();
		gamePanel.closeGame();
		wl.release();

	}// End of onPause

	@Override
	protected void onResume() {
		super.onResume();
		setContentView(gamePanel = new MGP(this));
		wl.acquire();

	}// End of onResume

	@Override
	public void onBackPressed() {
		gamePanel.setPause(true); // pause the game

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Quit Game?")
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener).show();
		return;
	}

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:

				gamePanel.closeGame();
				Intent intent = new Intent(Game.this, NewMenu.class);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();

				break;

			case DialogInterface.BUTTON_NEGATIVE:
				gamePanel.setPause(false);
				break;
			}
		}
	};

	// public void saveBitmap(Bitmap bitmap) {
	//
	// saveToInternalStorage(bitmap);
	// // savefile(bitmap);
	// // storeImage(bitmap);
	// }
	//
	// public boolean saveToInternalStorage(Bitmap bitmap){
	//
	// try {
	// // Use the compress method on the Bitmap object to write image to
	// // the OutputStream
	// FileOutputStream fos = openFileOutput("desiredFilename.png",
	// Context.MODE_PRIVATE);
	//
	// // Writing the bitmap to the output stream
	// bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
	// fos.close();
	//
	// Log.d("", "storage location" +
	// Environment.getExternalStorageDirectory());
	//
	// return true;
	// }
	// catch (Exception e) {
	// Log.e("saveToInternalStorage()", e.getMessage());
	// return false;
	// }
	// }
	//
	// public static void saveImages(Activity activity) throws IOException
	// {
	// for (int i=0; i<categories.getItems().length; i++) {
	// OutputStream os2 =
	// activity.openFileOutput(categories.getItems()[i].getName(),
	// Context.MODE_WORLD_READABLE);
	// OutputStreamWriter osw2 = new OutputStreamWriter(os2);
	// Bitmap bmp =
	// ((BitmapDrawable)categories.getItems()[i].getCategoryImage()).getBitmap();
	// bmp.compress(Bitmap.CompressFormat.PNG, 90, os2);
	// osw2.close();
	// }
	// }
	//
	// public void readStuff(){
	//
	// for (int i=0; i<categories.getItems().length; i++) {
	// InputStream is =
	// activity.openFileInput(categories.getItems()[i].getName());
	// Bitmap b = BitmapFactory.decodeStream(is);
	//
	// // do whatever you need with b
	// }
	//
	// }
	//
	//
	// public boolean savefile(Bitmap bitmap){
	//
	// try {
	// // Use the compress method on the Bitmap object to write image to
	// // the OutputStream
	// FileOutputStream fos = context.openFileOutput("desiredFilename.png",
	// Context.MODE_PRIVATE);
	//
	// // Writing the bitmap to the output stream
	// bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
	// fos.close();
	//
	// return true;
	// } catch (Exception e) {
	// Log.e("saveToInternalStorage()", e.getMessage());
	// return false;
	// }
	// }
	//
	// private void storeImage(Bitmap image) {
	// File pictureFile = getOutputMediaFile();
	// if (pictureFile == null) {
	// Log.d("pff",
	// "Error creating media file, check storage permissions: ");//
	// e.getMessage());
	// return;
	// }
	// try {
	// FileOutputStream fos = new FileOutputStream(pictureFile);
	// image.compress(Bitmap.CompressFormat.PNG, 90, fos);
	// fos.close();
	// } catch (FileNotFoundException e) {
	// Log.d("pff", "File not found: " + e.getMessage());
	// } catch (IOException e) {
	// Log.d("pff", "Error accessing file: " + e.getMessage());
	// }
	// }
	//
	// /** Create a File for saving an image or video */
	// private File getOutputMediaFile(){
	// // To be safe, you should check that the SDCard is mounted
	// // using Environment.getExternalStorageState() before doing this.
	// File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
	// + "/Android/data/"
	// + getApplicationContext().getPackageName()
	// + "/Files");
	//
	// // This location works best if you want the created images to be shared
	// // between applications and persist after your app has been uninstalled.
	//
	// // Create the storage directory if it does not exist
	// if (! mediaStorageDir.exists()){
	// if (! mediaStorageDir.mkdirs()){
	// return null;
	// }
	// }
	// // Create a media file name
	// String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new
	// Date());
	// File mediaFile;
	// String mImageName="MI_"+ timeStamp +".jpg";
	// mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	// mImageName);
	// return mediaFile;
	// }
	//
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// if(gamePanel.getPause())
		// gamePanel.setPause(false);
		// else
		// gamePanel.setPause(true);

		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}

}
