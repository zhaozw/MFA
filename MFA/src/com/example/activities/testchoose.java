package com.example.activities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.mfa.R;
import com.example.mfa.networking.*;
import com.example.objects.HitsAllInfo;

public class testchoose extends Activity {

	private static String setHit = "http://cofactor1-unbrandedtech.dotcloud.com/Set_Hit.php?";
	public Button Yacov, KAcie, Christian, James;
	DatabaseHandler db;
	JSONParser jParser = new JSONParser();

	int duration = Toast.LENGTH_LONG;
	
	String hitchoice, uid;
	boolean available;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Intent intent = getIntent();
		hitchoice = intent.getStringExtra("HitType").trim();
		
		db = new DatabaseHandler(getApplicationContext());
		setContentView(R.layout.activity_test_choose);
		
		Yacov = (Button) findViewById(R.id.SendYacov);
		James = (Button) findViewById(R.id.SendJames);
		Christian = (Button) findViewById(R.id.SendChristian);
		KAcie = (Button) findViewById(R.id.SendKAcie);
		
	}


		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.SendChristian: {
				// getting JSON string from URL
				Log.d("sending hit to christian", setHit +"?From="+ db.getUserDetails().get("uid").toString().trim() +"&To=5150e7b96b14f7.58739785&Type="+hitchoice+"&Msg=fuck you");
				uid = "5150e7b96b14f7.58739785";
				if(hitavailiable(uid)){
				try {
					// defaultHttpClient
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost((setHit +"?From="+ db.getUserDetails().get("uid").toString().trim() +"&To=5150e7b96b14f7.58739785&Type="+hitchoice+"&Msg=blahblah"));

					HttpResponse httpResponse = httpClient.execute(httpPost);
					HttpEntity httpEntity = httpResponse.getEntity();
					 Toast toast = Toast.makeText(getApplicationContext(), "Sent", duration);
					 toast.show();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}}
			break;
			}
			
			case R.id.SendYacov: {
				// getting JSON string from URL
				Log.d("sending hit to christian", setHit +"?From="+ db.getUserDetails().get("uid").toString().trim() +"&To=513557a6a1c802.11086283&Type="+hitchoice+"&Msg=fuck you");
				uid = "513557a6a1c802.11086283";
				if(hitavailiable(uid)){
				try {
					// defaultHttpClient
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost((setHit +"?From="+ db.getUserDetails().get("uid").toString().trim() +"&To=513557a6a1c802.11086283&Type="+hitchoice+"&Msg=blahblah"));

					HttpResponse httpResponse = httpClient.execute(httpPost);
					HttpEntity httpEntity = httpResponse.getEntity();
					 Toast toast = Toast.makeText(getApplicationContext(), "Sent", duration);
					 toast.show();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}}
			break;
			}
			
			
			case R.id.SendKAcie: {
				// getting JSON string from URL
				Log.d("sending hit to christian", setHit +"?From="+ db.getUserDetails().get("uid").toString().trim() +"&To=513be7b18d0511.81928076&Type="+hitchoice+"&Msg=fuck you");
				uid = "513be7b18d0511.81928076";
				if(hitavailiable(uid)){
				try {
				// defaultHttpClient
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost((setHit +"?From="+ db.getUserDetails().get("uid").toString().trim() +"&To=513be7b18d0511.81928076&Type="+hitchoice+"&Msg=blahblah"));

					HttpResponse httpResponse = httpClient.execute(httpPost);
					HttpEntity httpEntity = httpResponse.getEntity();
					 Toast toast = Toast.makeText(getApplicationContext(), "Sent", duration);
					 toast.show();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}}
			break;
			}
			
			
			case R.id.SendJames: {
				// getting JSON string from URL
				Log.d("sending hit to christian", setHit +"?From="+ db.getUserDetails().get("uid").toString().trim() +"&To=51354f4b171520.96868586&Type="+hitchoice+"&Msg=fuck you");
				
				uid = "51354f4b171520.96868586";
				if(hitavailiable(uid)){
				try {
					// defaultHttpClient
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost((setHit +"?From="+ db.getUserDetails().get("uid").toString().trim() +"&To=51354f4b171520.96868586&Type="+hitchoice+"&Msg=blahblah"));

					HttpResponse httpResponse = httpClient.execute(httpPost);
					HttpEntity httpEntity = httpResponse.getEntity();
					 Toast toast = Toast.makeText(getApplicationContext(), "Sent", duration);
					 toast.show();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}}
			break;
			}
		
		
	}}


		private boolean hitavailiable(String uid2) {
			
			JSONArray Hits = null;
			
			 // Creating JSON Parser instance
			 JSONParser jParser = new JSONParser();
			
			 // getting JSON string from URL
			 JSONObject json = jParser.getJSONFromUrl("http://cofactor1-unbrandedtech.dotcloud.com/Get_Hits.php?hits=" + uid2);
			
			 Log.d("Game: ", "Starting JSON Try Loop");
			 try
			 {
			 // Getting Array of Contacts
			 Hits = json.getJSONArray("Hits");
			 Log.d("Game","Getting Json Array");
			
			 // looping through All Contacts
			 for (int i = 0; i < Hits.length(); i++)
			 {
			 JSONObject s = Hits.getJSONObject(i);
			
			 Log.d("New Game Options", "Starting Parse");
			
			 String hitid = s.getString("HitsID");
			
			 // Storing each json item in variable
			

			 if(s.getString("Hit"+hitchoice+"Active").equals("0")){
				 return true;
			 }
			 else{
				 Toast toast = Toast.makeText(getApplicationContext(), "Failed", duration);
				 toast.show();
			 }
			 Log.d("New Game Options", "Done");
			 }
			 }
			 catch (JSONException e)
			 {
			 e.printStackTrace();
			 }
			return false;
		}}
			
