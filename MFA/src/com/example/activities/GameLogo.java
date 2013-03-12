package com.example.activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mfa.R;
import com.example.mfa.networking.*;

public class GameLogo extends Activity {
	private Object data = null;
	String url = "http://cofactor1-unbrandedtech.dotcloud.com/Player_Info.php?FBID=";

	// JSON Node names
	final String TAG_GAME = "Game";
	final String TAG_FBID = "FBID";
	final String TAG_NAME = "Name";
	final String TAG_HIGHSCORE = "Highscore";
	final String TAG_MONEY = "Money";
	final String TAG_HITS = "Hits";
	public HashMap<String, String> map = new HashMap<String, String>();
	public ArrayList<HashMap<String, String>> Players;
	public TextView FBID;
	public ImageButton FBLogin;
	public Button btnLogout, btnLogin;
	UserFunctions userFunctions;
	DatabaseHandler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_game_logo);
		Log.d("GameLogo: ", "Setting content view");
		
		FBID = (TextView)findViewById(R.id.FBID);
		
		btnLogout = (Button)findViewById(R.id.btnLogout);
		btnLogin = (Button)findViewById(R.id.btnLogin);
		
		FBLogin = (ImageButton)findViewById(R.id.FBLogin);
		
        userFunctions = new UserFunctions();
        db = new DatabaseHandler(getApplicationContext());
        if(intent.hasExtra("name")){
        	intent.getExtras();
        	FBID.setText(intent.getStringExtra("uid"));
        }
        
        if(!userFunctions.isUserLoggedIn(getApplicationContext())){
        	btnLogout=(Button)findViewById(R.id.btnLogout);
        	btnLogout.setVisibility(View.GONE);
        }else{
        	FBID.setText(db.getUserDetails().get("name").toString());
         	
        	JSONArray search = null;

    		// Creating JSON Parser instance
    		JSONParser jParser = new JSONParser();
    		
    		// getting JSON string from URL
    		Log.d("GameLogo: ", "Sending Request " + url + db.getUserDetails().get("email").toString());
    		JSONObject json = jParser.getJSONFromUrl((url + db.getUserDetails().get("email").toString()).trim());
    		
    		Log.d("GameLogo: ", "Starting JSON Try Loop");
    		try {
    			// Getting Array of Contacts
    			search = json.getJSONArray(TAG_GAME);

    			// looping through All Contacts
    			for (int i = 0; i < search.length(); i++) {
    				JSONObject s = search.getJSONObject(i);

    				// Storing each json item in variable
    				String fbid = s.getString(TAG_FBID);
    				String name = s.getString(TAG_NAME);
    				String highscore = s.getString(TAG_HIGHSCORE);
    				String money = s.getString(TAG_MONEY);
    				String hits = s.getString(TAG_HITS);
    				
    				Log.d("GameLogo: ", "String" + fbid);
    				Log.d("GameLogo: ", "String" + name);
    				Log.d("GameLogo: ", "String" + highscore);
    				Log.d("GameLogo: ", "String" + money);
    				Log.d("GameLogo: ", "String" + hits);
    				// adding each child node to HashMap key => value
    				map.put(TAG_FBID, fbid);
    				map.put(TAG_NAME, name);
    				map.put(TAG_HIGHSCORE, highscore);
    				map.put(TAG_MONEY, money);
    				map.put(TAG_HITS, hits);

    			}
    		} catch (JSONException e) {
    			e.printStackTrace();
    		}
            }
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.FBLogin: {
			Log.d("GameLogo: ", map.get(TAG_FBID).toString());
        	FBID.setVisibility(View.VISIBLE);
			Intent intent = new Intent(this, MainMenu.class);
			intent.putExtra("map", map);
			startActivity(intent);
			break;
		}
		case R.id.btnLogout: {
			Log.d("Button Pressed","Log Out Button");
	        if(userFunctions.isUserLoggedIn(getApplicationContext())){
	            userFunctions.logoutUser(getApplicationContext());
	        }
	        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
	        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(login);
	        finish();
	        break;
		}
		case R.id.btnLogin: {
			Log.d("Button Pressed","Log In");
			if(userFunctions.isUserLoggedIn(getApplicationContext())){
				Log.d("GameLogo: ", map.get(TAG_FBID).toString());
	        	FBID.setVisibility(View.VISIBLE);
				FBID.setText(map.get(TAG_FBID).toString());
				Intent intent = new Intent(this, MainMenu.class);
				intent.putExtra("map", map);
				startActivity(intent);
				break;
	        }else{
			Intent login = new Intent(getApplicationContext(), LoginActivity.class);
	        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(login);
	        finish();
	        break;
			}
		}
		}
	}
}