package com.example.objects;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;
public class Asteroid 
{ 
    double   xVelocity, yVelocity, radius,mineRadius=200; 
    public int x,cx;
	public int y,cy;
	int scrnW;
	int scrnH;
    public boolean unlocked = false;
    Random generator =  new Random();
    int IMG;
    private Bitmap bitmap;	// the actual bitmap
    
    public Asteroid(Bitmap bitmap,double minVelocity, double maxVelocity,int scrnW,int scrnH)     
    { 
    	this.scrnW=scrnW;
    	this.scrnH=scrnH;
    	this.bitmap = bitmap;
    	this.radius=(bitmap.getWidth()/2);  
        this.y = generator.nextInt(scrnH); 
        this.x= generator.nextInt(scrnW)+scrnW+(int) MGP.dp[150]; 
        IMG= generator.nextInt(3)+1;   
        //calculates a random direction and a random 
        //velocity between minVelocity and maxVelocity
        double vel=minVelocity + Math.random()*(maxVelocity-minVelocity), dir=2*Math.PI*1; // random direction
        
        xVelocity=(int)vel*Math.cos(dir); 
        yVelocity=(int)vel*Math.sin(dir); 
    }
    
   
    public void move()
     { 
//       if(x==0)
//    	   x-=1;
    	
       if(unlocked) 
       {
    	   x-=xVelocity; //move the asteroid
           y-=yVelocity;
    	   x-=MGP.totalSpeed/2;
            if(x<=- MGP.dp[100])
             {
                 moveBack();
                 if(MGP.asteroidsPassed+1!=MGP.asteroidPassLimit)
                 MGP.asteroidsPassed+=1;
             }
           
          
       }
       else if(unlocked==false)
       {
           if(x<scrnW+MGP.dp[50])
         {
            //x-=AsteroidsGame.totalSpeed;
        	x-=MGP.totalSpeed;
            x-=xVelocity; //move the asteroid
            y-=yVelocity;
            if(x<-MGP.dp[100])
                 {
            	  moveBack();
                 }
          }
        }
       cx=x + (bitmap.getWidth() / 2);
       cy=y + (bitmap.getHeight() / 2);
    		   
     } 
    
    public boolean shipCollision(Player ship)
    { 
        //using the distance formula to check for collisions.
        if(Math.pow(radius+ship.radius,2) > Math.pow(ship.cx-cx,2) + Math.pow(ship.cy-cy,2))
          return true; 
        return false; 
    }
    
    public boolean shotCollision(Shot shot)
    { 
        if(Math.pow(radius,2) > Math.pow(shot.x-cx,2)+ Math.pow(shot.y-cy,2))
          return true; 
        return false; 
    }
    
    public boolean touchCollision(int tx,int ty)
    { 
        if(tx>x&&tx<x+bitmap.getWidth()&&ty>y&&ty<y+bitmap.getHeight())
          return true; 
        return false; 
    }
     
    public void draw(Canvas canvas) 
    {
		canvas.drawBitmap(bitmap, x , y , null);
		canvas.drawLine(cx,cy,(float) (cx-radius),cy,MGP.redPaint);
	}
    
    public boolean getLockedState()
    { 
        return unlocked; 
    } 

    public void setBitmap(Bitmap bitmap) 
    {
    	this.bitmap = bitmap;	
    }    

    
    public void moveBack()
    {
        this.y = generator.nextInt(scrnH);   
        this.x = generator.nextInt((scrnW)*2)+scrnW+(int)MGP.dp[150];
    }   
}