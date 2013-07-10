package com.example.mfa.networking;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mfa.R;

public class LoginFunctions {

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "Username";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	public static int attemptLogin(Context context, String email,
			String password) {

		UserFunctions userFunction = new UserFunctions();
		JSONObject json = userFunction.loginUser(email, password);

		Log.d("LoginActivity", "Onclick Activated");

		// check for login response
		try {
			if (json.getString(KEY_SUCCESS) != null) {
				String res = json.getString(KEY_SUCCESS);
				if (Integer.parseInt(res) == 1) {
					// user successfully logged in
					// Store user details in SQLite Database
					DatabaseHandler db = new DatabaseHandler(context);
					JSONObject json_user = json.getJSONObject("user");

					// Clear all previous data in database
					userFunction.logoutUser(context);
					db.addUser(json_user.getString(KEY_NAME),
							json_user.getString(KEY_EMAIL),
							json.getString(KEY_UID),
							json_user.getString(KEY_CREATED_AT));

					return 1;
				} else {
					Toast toast = Toast.makeText(context, context
							.getString(R.string.IncorrectUsernamePassword),
							Toast.LENGTH_SHORT);
					toast.show();
					return 2;
				}
			}
		} catch (JSONException e) {
			Toast toast = Toast.makeText(context,
					context.getString(R.string.ConnectionError),
					Toast.LENGTH_SHORT);
			toast.show();
			e.printStackTrace();
			return 3;
		} catch (Exception e) {
			Toast toast = Toast.makeText(context,
					context.getString(R.string.ConnectionError),
					Toast.LENGTH_SHORT);
			toast.show();
			e.printStackTrace();
			return 3;
		}
		Toast toast = Toast.makeText(context,
				context.getString(R.string.LoginFailed), Toast.LENGTH_SHORT);
		toast.show();
		return 4;
	}

	public static int registerAccount(Context context, String userName,
			String email, String password) {

		Log.d("LoginFunctions", userName + "  " + email + "  " + "" + password);

		UserFunctions userFunction = new UserFunctions();
		JSONObject json = userFunction.registerUser(userName, email, password);

		// check for login response
		try {
			if (json.getString(KEY_SUCCESS) != null) {
				String res = json.getString(KEY_SUCCESS);
				if (Integer.parseInt(res) == 1) {
					// user successfully registered
					// Store user details in SQLite Database
					DatabaseHandler db = new DatabaseHandler(context);
					JSONObject json_user = json.getJSONObject("user");

					// Clear all previous data in database
					userFunction.logoutUser(context);
					db.addUser(json_user.getString(KEY_NAME),
							json_user.getString(KEY_EMAIL),
							json.getString(KEY_UID),
							json_user.getString(KEY_CREATED_AT));
					return 1;
				} else {
					// Error in registration
					Toast toast = Toast.makeText(context,
							context.getString(R.string.RegistrationError),
							Toast.LENGTH_SHORT);
					toast.show();
					return 2;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			// Error in registration
			Toast toast = Toast.makeText(context,
					context.getString(R.string.RegistrationError),
					Toast.LENGTH_LONG);
			toast.show();
			return 2;
		} catch (Exception e) {
			// Error in registration
			Toast toast = Toast.makeText(context,
					context.getString(R.string.RegistrationError),
					Toast.LENGTH_LONG);
			toast.show();
			e.printStackTrace();
			return 2;
		}
		return 2;
	}

	
	
	public static HashMap<String, String> getRivals(Context context,
			String email) {

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser
				.getJSONFromUrl("http://cofactorstudios.com/Rivals.php?HitsID="
						+ "51dba10cdd0046.15123727");

		HashMap<String, String> map = new HashMap<String, String>();

		try {

			// Getting Array of Contacts
			JSONArray GameDetails = json.getJSONArray("Game");

			JSONObject s = GameDetails.getJSONObject(0);

			for (int k = 0; k < 3; k++) {
				map.put("Rival" + k + "HitsID",
						s.getString("Rival" + k + "HitsID"));
				map.put("Rival" + k + "Name", s.getString("Rival" + k + "Name"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
			Toast toast = Toast.makeText(context, "GetDetailsFailed",
					Toast.LENGTH_LONG);
			toast.show();
			return map;
		}

		return map;
	}

	public static HashMap<String, String> getPlayerStats(Context context,
			String email) {

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

	public static String getPlayerHitsID(Context context, String email) {

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser
				.getJSONFromUrl("http://cofactorstudios.com/Player_Info.php?email="
						+ email);

		try {

			// Getting Array of Contacts
			JSONArray GameDetails = json.getJSONArray("Game");

			JSONObject s = GameDetails.getJSONObject(0);

			return s.getString("HitsID");

		} catch (JSONException e) {
			e.printStackTrace();
			Toast toast = Toast.makeText(context, "GetDetailsFailed",
					Toast.LENGTH_LONG);
			toast.show();
			return "null";
		}

	}

	public static String getPlayerNameFromID(Context context, String HitID) {

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser
				.getJSONFromUrl("http://cofactorstudios.com/Player_Info.php?hits="
						+ HitID);

		try {

			// Getting Array of Contacts
			JSONArray GameDetails = json.getJSONArray("Game");

			JSONObject s = GameDetails.getJSONObject(0);

			return s.getString("Username");

		} catch (JSONException e) {
			e.printStackTrace();
			Toast toast = Toast.makeText(context, "GetDetailsFailed",
					Toast.LENGTH_LONG);
			toast.show();
			return "null";
		}
	}

	public static boolean activateHit(Context context, String hitID, int hit, String message) {
		try {
			// defaultHttpClient
			UserFunctions uF = new UserFunctions();
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost
					("http://cofactorstudios.com/Set_Hit.php?From="+""+"&To="
					+ LoginFunctions.getPlayerHitsID(context,uF.getEmail(context))
					+ "&Type=" + hit + "&Msg="+message);
			httpClient.execute(httpPost);

			//	HttpResponse httpResponse = httpClient.execute(httpPost);
			//HttpEntity httpEntity = httpResponse.getEntity();
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
