package com.example.activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mfa.R;
import com.example.mfa.networking.JSONParser;
import com.example.mfa.networking.PlayerStatsObject;

public class ChooseRival extends Activity implements OnItemSelectedListener {

	
	
	// url to make request
	private static String url = "http://www.acsii-inc.net/php/hhemployeescan.php?EMPID=1048&DATES=09/17/2012&DATEE=09/17/2012";
	
	// JSON Node names
	private static final String TAG_SEARCH = "SEARCH";
	private static final String TAG_CREW = "Crew";
	private static final String TAG_LUNCH = "Lunch";
	
	// contacts JSONArray
	JSONArray search = null;
	 private View messageCheckBoxView;
	 private View enterMessageView;
	 private View purchaseView;
	 private View sendHitView;
	 public PlayerStatsObject[] playerStatsArray; 
	 
	 
	 ArrayList<HashMap<String, String>> Players;
	
	 Button messageBox,sendHitCheckBox,purchaseButton;
	 TextView Title;
	 
	 Context context;
//	
	 int duration = Toast.LENGTH_LONG;
//	
  	String currentlySelectedPlayerName;
	int currentlySelectedPlayerHitActive;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        
      	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // make it landscape mode
        requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	playerStatsArray = new PlayerStatsObject[1000];

        
    setContentView(R.layout.activity_choose_rival);
    context = getApplicationContext();
    
   Players = new ArrayList<HashMap<String, String>>();

	// Creating JSON Parser instance
	JSONParser jParser = new JSONParser();

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
			String lunch = s.getString(TAG_LUNCH);
			Log.d("crew" , crew );
			Log.d("lunch" , lunch );
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			
			// adding each child node to HashMap key => value
			map.put(TAG_LUNCH, lunch);
			map.put(TAG_CREW, crew);
			
			playerStatsArray[i]= new PlayerStatsObject(crew,Integer.parseInt(lunch));
			
			Players.add(map);
		}
	} catch (JSONException e) {
		e.printStackTrace();
	}
	/**
	 * Updating parsed JSON data into ListView
	 * */
	ListAdapter adapter = new SimpleAdapter(this, Players,
			R.layout.list_item,
			new String[] {TAG_CREW,TAG_LUNCH}, new int[] {
					R.id.name, R.id.IHA });
	
	
	
	 ListView playersListView = (ListView) findViewById(R.id.PlayersList);
	// Assign adapter to ListView
	 playersListView.setAdapter(adapter);
	 
	     //register with a click listener
    	 playersListView.setOnItemClickListener(playerListClickListener);
    	 
    	
    	 
    	 
    	 
		 Spinner spinner = (Spinner) findViewById(R.id.PlayerType);
		  // Create an ArrayAdapter using the string array and a default spinner layout
		 
		 ArrayAdapter<CharSequence> playerTypeAdapter = ArrayAdapter.createFromResource(this,
	        R.array.player_choice_array, android.R.layout.simple_spinner_item);
		 // Specify the layout to use when the list of choices appears
		 
		 playerTypeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		 // Apply the adapter to the spinner
		 
		 spinner.setAdapter(playerTypeAdapter); 
		 spinner.setOnItemSelectedListener(this);
		 
		 
        messageCheckBoxView = findViewById(R.id.SendMessageCheckBox);
        enterMessageView = findViewById(R.id.playerMessage);
        purchaseView = findViewById(R.id.Purchase);
        sendHitView = findViewById(R.id.checkBox1);
        
        messageBox = (Button) findViewById(R.id.SendMessageCheckBox);
        sendHitCheckBox = (Button) findViewById(R.id.checkBox1);
        purchaseButton = (Button) findViewById(R.id.Purchase);
         
        messageBox.setOnClickListener(messageCheckBoxListener); 
        sendHitCheckBox.setOnClickListener(sendHitCheckBoxListener); 
        purchaseButton.setOnClickListener(purchaseButtonListener);
        Title = (TextView) findViewById(R.id.hit_type);

        
        
        
    }  
    
  
 // Create a message handling object as an anonymous class.
    private OnItemClickListener playerListClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
        	
        	
        	 Log.d("Player Selected","" + parent.getItemAtPosition(position));

        	
        	 
        	 currentlySelectedPlayerName=playerStatsArray[position].name;
        	 currentlySelectedPlayerHitActive=playerStatsArray[position].hitActive;
        	 

        	 if( currentlySelectedPlayerHitActive==0)
        	 {
        			messageCheckBoxView.setVisibility(View.GONE);  
        		    enterMessageView.setVisibility(View.GONE);  
        		    purchaseView.setVisibility(View.GONE); 	 
        		    sendHitView.setVisibility(View.INVISIBLE); 
                    Title.setText("Hit Not Available");
        	 }
        	 else if(currentlySelectedPlayerHitActive==1)
        	 {
        		 Title.setText("Hit Available");
        		 if(((CompoundButton) messageBox).isChecked()){
             		enterMessageView.setVisibility(View.VISIBLE);  
             	}
             	else if(((CompoundButton) messageBox).isChecked()==false){
             		enterMessageView.setVisibility(View.GONE);  	
             	}
     		    sendHitView.setVisibility(View.VISIBLE); 
        		 
        	 }
        	 
        }
    };
    
    
    // SELECTION CHOICE FOR PLAYER TYPE
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
    	
         switch(pos)
         {
         case(0):
        	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));

        	 break;
         case(1):
        	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));

        	 break;
         case(2):
        	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));
         
        	 break;
         case(3):
        	 Log.d("spinner selected","" + parent.getItemAtPosition(pos));

        	 break;  
         }
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		messageCheckBoxView.setVisibility(View.GONE);  
	    enterMessageView.setVisibility(View.GONE);  
	    purchaseView.setVisibility(View.GONE); 	
	}

    
	//HIT AVAILABLE CHECK BOX
    OnClickListener sendHitCheckBoxListener = new OnClickListener() {
        public void onClick(View v) {
        	Log.d("send hit box","currently set to " + ((CompoundButton) sendHitCheckBox).isChecked());

        	
        	if(((CompoundButton) sendHitCheckBox).isChecked()){
        		
        		if(((CompoundButton) messageBox).isChecked()){
            		enterMessageView.setVisibility(View.VISIBLE);  
            	}
            	else if(((CompoundButton) messageBox).isChecked()==false){
            		enterMessageView.setVisibility(View.GONE);  	
            	}
        		
        		messageCheckBoxView.setVisibility(View.VISIBLE);  
        		purchaseView.setVisibility(View.VISIBLE);  
        	}
        	else if(((CompoundButton) sendHitCheckBox).isChecked()==false){
        		enterMessageView.setVisibility(View.GONE); 
        		messageCheckBoxView.setVisibility(View.GONE);  
        		purchaseView.setVisibility(View.GONE);  
        	}
        	
        }
    };
    
    //SEND MESSAGE CHECK BOX
	OnClickListener messageCheckBoxListener = new OnClickListener() {
        public void onClick(View v) {
        	
        	//Log.d("MessageBox","currently set to " + messageBox.isPressed());
        	Log.d("MessageBox","currently set to " + ((CompoundButton) messageBox).isChecked());
	
        	if(((CompoundButton) messageBox).isChecked()){
        		enterMessageView.setVisibility(View.VISIBLE);  
        	}
        	else if(((CompoundButton) messageBox).isChecked()==false){
        		enterMessageView.setVisibility(View.GONE);  	
        	}   		 
        }
    };
    
    OnClickListener purchaseButtonListener = new OnClickListener() 
    {
        public void onClick(View v) 
        {
	       	CharSequence text = "You just sent a hit out on "+ currentlySelectedPlayerName + currentlySelectedPlayerHitActive +"";
	    	Toast hitSentToast = Toast.makeText( context, text, duration);
	        hitSentToast.show();
        	
        }
    };	
}


