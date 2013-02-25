package com.example.HitsObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;
import com.example.objects.Player;
import com.example.objects.Shot;

public class HitGiantBoss {

    public Shot[] shots;
    public boolean shooting=true;
	public int shotDelay, shotDelayLeft;
    public int numUserShots;
    public int life= 176;
	public HitGiantBossIndividualParts eye,topJaw,bottomJaw;
	public Bitmap shotImg;
	public boolean failed;
	
	public HitGiantBoss()
	{
		eye = new HitGiantBossIndividualParts(2000);
		topJaw = new HitGiantBossIndividualParts(2000);
		bottomJaw = new HitGiantBossIndividualParts(2000);
		shots=new Shot[20];
	    shotDelay = 120;
	    shotDelayLeft = 0;
	}
	
	public void setImages(Bitmap eyeImg,Bitmap lowerJawImg, Bitmap UpperJawImg,Bitmap shotImg)
	{
		eye.setImage(eyeImg);
		topJaw.setImage(UpperJawImg);
		bottomJaw.setImage(lowerJawImg);
		this.shotImg=shotImg;
	}
	
	public void Move(Player ship){
		
		 eye.MoveEye(ship.cx, ship.cy);
		 topJaw.MoveUpperJaw(ship.cx, ship.cy);
		 bottomJaw.MoveLowerJaw(ship.cx, ship.cy);
		 
		 moveShotsSin();
		 
		 updateShotCollision(ship);
		 
		 if(shotDelayLeft>0) 
	          shotDelayLeft--;  
	}
	
	public void Draw(Canvas canvas)
	{
		 eye.draw(canvas);
		 topJaw.draw(canvas);
		 bottomJaw.draw(canvas);

		 //draw all the shots on the screen
		 for(int i=0;i<numUserShots;i++) //numUserShots
		 {
		  shots[i].draw(canvas);
		 }

	}
	
	 public void moveShots()
	 {
			//move shots and remove dead shots
	      for(int i=0;i<numUserShots;i++){  
	        shots[i].move(); 
	        //removes old shots and shifts shot array
	        if(shots[i].getLifeLeft()<=0){   
                deleteShot(i);  // move the outer loop back one so 
                 i--;// the shot shifted up is not skipped
	        } 
	      }
	    
	 }
	 
	 public void moveShotsSin()
	 {
			//move shots and remove dead shots
	      for(int i=0;i<numUserShots;i++)
	      {  
	    	 // if(powerUp.sineBullets)
	        shots[i].move(); 
	        //removes shot if it has gone for too long
	        //without hitting anything
	        if(shots[i].getLifeLeft()<=0)
	        {   
	            //shifts all the next shots up one 
	            //space in the array
	           
	            deleteShot(i); 
	            i--; // move the outer loop back one so 
	                 // the shot shifted up is not skipped
	        } 
	      }
	    
	 }
	 
	 public boolean checkForNewShots()
	 {
        if(shooting && canShoot())  
	      {   
	        //set the trigger to play the laser to 0
	     	
	        //add a shot on to the array
	        shots[numUserShots]=shoot();  
	        numUserShots++; 
	        return true;
	      } 
        else
        return false;
	 }
	 
	 public boolean updateShotCollision(Player ship)
	 {
			
	      for(int i=0;i<numUserShots;i++)
	      {
		    if(ship.shotCollision(shots[i])){
		    	 deleteShot(i);
		    	 i=100;
         	    return true;         	
		    }
	      }
	     return false;    
	 }
	 

	 
//	 public boolean updateShipShotCollision(Player ship)
//	 {
//			//move shots and remove dead shots
//	      for(int i=0;i<ship.numUserShots;i++)
//		    if(shotCollision(ship.shots[i]))
//		    {
//		     ship.deleteShot(i);
//		     //i=100;
//         	return true;
//		    }
//	     return false;    
//	 }
	 
	 
//		public boolean shotCollision(Shot shot)
//	    { 
//	        // Uses the distance formula to check for collisions with other shots.
//	        if(Math.pow(radius,2) > Math.pow(shot.x-x,2)+ Math.pow(shot.y-y,2))
//	          return true; 
//	        return false; 
//	    }
	 
		public void deleteShot(int index)
	    { 
	        //delete shot and move all shots after it up in the array
	        numUserShots--; 
	        for(int i=index;i<numUserShots;i++) 
	          shots[i]=shots[i+1]; 
	        shots[numUserShots]=null; 
	    }
	 
	 
//	 public void setLifeBarSize()
//	   {
//	       lifeBarSize-=6;
//	   }
//	 
	
	
	
	  public boolean canShoot()
	   { 
	       if(shotDelayLeft>0) 
	         return false; 
	       else
	         return true; 
	   }
//	   
	   public Shot shoot()
	   { 
	       shotDelayLeft=shotDelay; 
	       return new Shot(eye.x,eye.cy,0,(int)MGP.dp[60],(int)MGP.dp[10],shotImg,1);
	   }
}
