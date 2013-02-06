package com.example.activities;

import java.util.List;
import java.util.Map;

import com.example.mfa.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Hits extends Activity {






	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // make it landscape mode
        requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        
        setContentView(R.layout.activity_hits); 
        
        
        Button purchaseHit = (Button) findViewById(R.id.purchase);
        purchaseHit.setOnClickListener(PurchaseHitListener); 
            
    }


   public void purchase(View v)
   {
	   
	   //later we will do an if else statement where if the hit is purchased 
	   //this will purchase it, and if it is then it will send them to the next menu
	   startChooseRivalsActivity(v);
   }
	
    OnClickListener PurchaseHitListener = new OnClickListener() {
        public void onClick(View v){
             startChooseRivalsActivity(v);
         }
    };
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_options, menu);
        return true;
    }
    
    
    public void startChooseRivalsActivity(View v) {
        Intent intent = new Intent(Hits.this, ChooseRival.class);
        startActivity(intent);
    }

    public void finishActivityA(View v) {
    	Hits.this.finish();
    }
    
}
