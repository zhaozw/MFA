package com.example.mfa.networking;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mfa.R;


import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class one extends ListActivity {

	// url to make request
	private static String url = "http://www.acsii-inc.net/php/hhemployeescan.php?EMPID=1048&DATES=09/17/2012&DATEE=09/17/2012";
	
	// JSON Node names
	private static final String TAG_SEARCH = "SEARCH";
	private static final String TAG_CREW = "Crew";
	private static final String TAG_SCANDATE = "ScanDate";
	private static final String TAG_PHASE = "Phase";
	private static final String TAG_PACKCODE = "PackCode";
	private static final String TAG_SCANVALUE = "ScanValue";
	private static final String TAG_EMPLOYEEID = "EmployeeID";
	private static final String TAG_LUNCH = "Lunch";
	public ArrayList<HashMap<String, String>> Players;
	public ListAdapter adapter;
	// contacts JSONArray
	JSONArray search = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Activity State:" , "Started" );
		// Hashmap for ListView
		Players = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		JSONPaser jParser = new JSONPaser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);

		try {
			// Getting Array of Contacts
			search = json.getJSONArray(TAG_SEARCH);
			
			// looping through All Contacts
			for(int i = 0; i < search.length(); i++){
				JSONObject s = search.getJSONObject(i);
				
				// Storing each json item in variable
				String crew = s.getString(TAG_CREW);
				String scandate = s.getString(TAG_SCANDATE);
				String phase = s.getString(TAG_PHASE);
				String packcode = s.getString(TAG_PACKCODE);
				String scanvalue = s.getString(TAG_SCANVALUE);
				String employeeid = s.getString(TAG_EMPLOYEEID);
				String lunch = s.getString(TAG_LUNCH);
				Log.d("crew" , crew );
				Log.d("lunch" , lunch );
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
				map.put(TAG_CREW, crew);
				map.put(TAG_LUNCH, lunch);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		/**
		 * Updating parsed JSON data into ListView
		 * */
		adapter = new SimpleAdapter(this, Players,
				R.layout.list_item,
				new String[] {TAG_CREW,TAG_LUNCH}, new int[] {
						R.id.name, R.id.IHA });
   	 ListView playersListView = (ListView) findViewById(R.id.PlayersList);
		// Assign adapter to ListView
   	 playersListView.setAdapter(adapter);

	}

}
