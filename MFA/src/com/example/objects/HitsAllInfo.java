package com.example.objects;

import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.HitsObjects.AsteroidHitSmall;
import com.example.HitsObjects.FollowAI;
import com.example.HitsObjects.HitGiantBoss;
import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;

public class HitsAllInfo {

    
    public HitsInfo[] hitsInfo;
    
    public  HitGiantBoss hit0;
    public  AsteroidHitSmall hit1;
    public  FollowAI hit2;
    public FlyingMessage flyingMessage;
    public int currentlyActivatedHit=-1;
    Random randomGenerator;
    
    public HitsAllInfo(){
        hitsInfo = new HitsInfo[2];
        randomGenerator = new Random();
        flyingMessage = new FlyingMessage();

        for(int k=0;k<hitsInfo.length;k++)
        {
        	hitsInfo[k]= new HitsInfo();
        }
        
    }

    
    public void initialize(HashMap map){
        

        	  
    	for(int k=0;k<2;k++){    
        	
        	Log.d("Hits All Info ", "starting loop");
                if(map.get("Hit"+k+"Active").toString().equals("1")){
                	
                	Log.d("Hits All Info ", "setting active");
                    hitsInfo[k].active = true;
                    
                	Log.d("Hits All Info ", "setting name");
                    hitsInfo[k].name = (String) map.get("Hit"+k+"From");
                    
                	Log.d("Hits All Info ", "setting message");
                    hitsInfo[k].message = (String) map.get("Hit"+k+"Msg");
                    
                	Log.d("Hits All Info ", "setting activation wave");
                    
                	hitsInfo[k].activationWave = k+1;
                    	
                	Log.d("Hits All Info ", "setting type");
                    hitsInfo[k].type = k;
                }
           
                else if(map.get("Hit"+k+"Active").toString().equals("0"))
                    hitsInfo[k].active=false;    
        }    
    }
    
    

    public void checkStartHit(Context context)
    {
    	for(int k=0;k<hitsInfo.length;k++){
    	 if(hitsInfo[k].activationWave==MGP.wave)
    	 createHit(k,context);
    	}   	 
    }
     
    public void createHit(int hit,Context context){
    	
    	switch(hit){

    	case(0):
    	      Log.d("Hits All Info ", "creating hit 0");
    	      hit0= new  HitGiantBoss();
    	      flyingMessage.activate(1,(int) MGP.dp[3],hitsInfo[0].name+":  "+ hitsInfo[0].message);
    	      hit0.setImages(BitmapFactory.decodeResource(context.getResources(), R.drawable.sheephead), BitmapFactory.decodeResource(context.getResources(), R.drawable.sheepleg), BitmapFactory.decodeResource(context.getResources(), R.drawable.sheepleg),BitmapFactory.decodeResource(context.getResources(), R.drawable.fireball));
    	 break; 
    	 
    	case(1):

    		  Log.d("Hits All Info ", "creating hit 1");
    	      flyingMessage.activate(1,(int) MGP.dp[3],hitsInfo[1].name+":  "+ hitsInfo[1].message);
    		  hit1 = new AsteroidHitSmall(BitmapFactory.decodeResource(context.getResources(), R.drawable.alien), MGP.dp[15],MGP.dp[30],MGP.deviceWidth,MGP.deviceHeight);
    	 break;
    	 
    	case(2):

  		  Log.d("Hits All Info ", "creating hit 2");
  	      flyingMessage.activate(1,(int) MGP.dp[3],hitsInfo[2].name+":  "+ hitsInfo[2].message);
  		
  	      hit2 = new FollowAI(BitmapFactory.decodeResource(context.getResources(), R.drawable.ast));
  	 break;
      }
        
 	
    	
     currentlyActivatedHit = hit;
    }
    
    
    
    public void MoveHits(Player ship){
    	
    	
    	
    	flyingMessage.move();
    	
    	switch(currentlyActivatedHit){

	    	case(0):
	    		hit0.Move(ship);
	    		break;
	    	case(1):
	    		hit1.move();
	    		break;
	    	case(2):
	    		hit2.move(ship.cx,ship.cy);
	    		break;
	    	default:
	    		//Log.d("Hits All Info ", "no hit currently in game");
	    	break;	
	    	
	    	
    	}
	
    }
    
   public void DrawHits(Canvas canvas){
    	
	   flyingMessage.draw(canvas);
		switch(currentlyActivatedHit){

    	case(0):
    		hit0.Draw(canvas);
    		break;
    	case(1):
    		hit1.draw(canvas);
    		break;
//    	case(2):
//    		hit2.draw(canvas);
//    		break;
    		
    	default:
    	
    	break;		
	  }
    	
    }
    
    
    public void drawHitInfo(Canvas canvas){
//    	canvas.drawText("hit 0 from "+hitsInfo[0].name,0,50,MGP.orangePaint);
//    	canvas.drawText("hit 0 message"+hitsInfo[0].message,0,60,MGP.orangePaint);
//    	canvas.drawText("hit 0 activation wave"+hitsInfo[0].activationWave,0,70,MGP.orangePaint);
//    	
//    	canvas.drawText("hit 1 from "+hitsInfo[1].name,0,80,MGP.orangePaint);
//    	canvas.drawText("hit 1 message"+hitsInfo[1].message,0,90,MGP.orangePaint);
//    	canvas.drawText("hit 1 activation wave"+hitsInfo[1].activationWave,0,100,MGP.orangePaint);
//    	canvas.drawText("currently actvivated hit "+currentlyActivatedHit,0,115,MGP.orangePaint);
    }


	public void checkHitFailure() {
		
		switch(currentlyActivatedHit){

    	case(0):
    		if(hit0.failed)
    			currentlyActivatedHit=-1;
    		break;
    	case(1):
    		if(hit1.failed)
    			currentlyActivatedHit=-1;
    		break;
    	case(2):
    		if(hit2.failed)
    			currentlyActivatedHit=-1;
    		break;
    		
    	default:
    		Log.d("Hits All Info ", "no hit currently in game");
    	break;	
    	
    	
	}

		
	}
    
    
}