package com.example.objects;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;
public class Alien 
{ 
	public int x,cx,cy;
	public int y;
    private double angle, radius;
    private int shotDelay, shotDelayLeft;
    private Bitmap alien, laser;
    private Random generator = new Random();
	public boolean unlocked = false;
    public Shot[] shots;
    public boolean shooting=true;
    public int numUserShots;
	int IMG;
    
    public Alien(Bitmap alien, Bitmap laser, double x,double y,double radius, int shotDelay)     
    { 
    	this.alien = alien;
    	this.laser = laser;
        this.x=(int)x; 
        this.y=(int)y; 
        this.radius=radius; 
        shots=new Shot[40];
        this.shotDelay=shotDelay; 
        shotDelayLeft=0;          
        angle=0;
    }
    
    public void move(int shipX, int shipY)
     { 
    	
    	if(unlocked)
    	{
    		if(shipX < 265 && x > 450)
    		{
    		x-=5;
    		}
    		else if(shipX > 265 && x < 80)
    		{
    			x+=5;
    		}
    		else
    		{
             if(shipY>160&&y>70)
              y-=4;
             else  if(shipY<160&&y<240)
              y+=4;
             if(shipX>265&&x>100)
              x-=4;
             else if(shipX<265&&x<360)
              x+=4; 
            }    
    	}
    	else  
    	{
    		if(x<MGP.deviceWidth+100)
    			x+=5;
    	}
    	 cx=x + (alien.getWidth() / 2);
	     cy=y + (alien.getHeight() / 2);
    	
     } 
     
    public void draw(Canvas canvas) 
    {
    	
		canvas.drawBitmap(alien, x , y , null);
	} 
     
    public void drawShots(Canvas canvas) 
	 {
		 //draw all the shots on the screen
	        for(int i=0;i<numUserShots;i++) //numUserShots
	            shots[i].draw(canvas);
	 }
	 
	 public void moveShots()
	 {
		 if(shotDelayLeft>0) 
	          shotDelayLeft--;
		 
			//move shots and remove dead shots
	      for(int i=0;i<numUserShots;i++)
	      {  
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

	 public boolean updateShipShotCollision(Player ship)
	 {
			//move shots and remove dead shots
	      for(int i=0;i<ship.numUserShots;i++)
		    if(shotCollision(ship.shots[i]))
		    {
		     ship.deleteShot(i);
		     //i=100;
         	return true;
		    }
	     return false;    
	 }
	 
	 public boolean updateShotCollision(Player ship)
	 {
			
	      for(int i=0;i<numUserShots;i++)
		    if(ship.shotCollision(shots[i]))
		    {
		     deleteShot(i);
         	return true;
		    }
	     return false;    
	 }
	 
	 public boolean checkForNewShots()
	 {

       if(shooting && canShoot()&&unlocked)  
	      {   
	        //set the trigger to play the laser to 0
	     	
	        //add a shot on to the array
	        shots[numUserShots]=shoot();  
	        numUserShots++; 
	        return true;
	      } 
       return false;
	 }
	 
		public void deleteShot(int index)
	    { 
	        //delete shot and move all shots after it up in the array
	        numUserShots--; 
	        for(int i=index;i<numUserShots;i++) 
	          shots[i]=shots[i+1]; 
	        shots[numUserShots]=null; 
	    }
	 
    public boolean shipCollision(Player ship)
    { 
        // Uses the distance formula to check for collisions with the user ship.
        if(Math.pow(radius+ship.radius,2) > Math.pow(ship.cx-cx,2) + Math.pow(ship.cy-cy,2))
        {
          moveBack();
          return true; 
        }
        return false; 
    }
    
    public boolean shotCollision(Shot shot)
    { 
        if(Math.pow(radius,2) > Math.pow(shot.x-cx,2)+ Math.pow(shot.y-cy,2))
          return true; 
        return false; 
    } 
    
    
    public void setAngle(int shipX, int shipY)
    {
        angle = Math.atan2(shipY-y,shipX-x);  
    }
    
    public boolean canShoot()
    { 
        if(shotDelayLeft>0) 
          return false; 
        else
          return true; 
    } 
    
    public Shot shoot()
    { 
        shotDelayLeft=shotDelay;
 
        return new Shot(x,y,angle,90,8,laser);
    }
    
    public void moveBack() 
	{
		this.y = generator.nextInt(MGP.deviceHeight);
		this.x = MGP.deviceWidth-generator.nextInt((MGP.deviceWidth)*8);
	}
}

