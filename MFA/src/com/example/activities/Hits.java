package com.example.activities;

import com.example.mfa.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

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
    
    public void startChooseRivalsActivity(View v) {
        Intent intent = new Intent(Hits.this, ChooseRival.class);
        startActivity(intent);
    }

    public void finishActivityA(View v) {
    	Hits.this.finish();
    } 
}
