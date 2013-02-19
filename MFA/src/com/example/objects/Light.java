package com.example.objects;

//this class is to be used to make multiple lights to move quickly 
//across the screen to create the appearence of motion

import java.util.Random;

import android.graphics.RectF;

import com.example.mfa.gamepanel.MGP;

public class Light
{
Random generator = new Random();
 //width of light
int WIDTH;
 
 //height of light
 int HEIGHT;
 
 //X coordinate
 int X;

 // Y coordinate
 int Y;
 int randS;
 int randM;
 int randF;
 int rSize;
 int Speed;
 boolean active;
// RectF shape ;
// MGP.deviceHeight
 //MGP.deviceWidth
 public RectF  shape = new RectF(X,Y,X+100,Y+100);
 //Light initialization
  public Light()
 {
     Speed = 10;
     WIDTH = 5;
     HEIGHT = 5;
     
     X = generator.nextInt(MGP.deviceWidth);
     Y = generator.nextInt(MGP.deviceHeight);
  // RectF  shape = new RectF(X,Y,X+10,Y+10);
 }
 
 public Light(int s,int w)
 {
     Speed = s;
     WIDTH = w;
     HEIGHT = 2;
     randS = generator.nextInt(Speed);
     X = generator.nextInt(MGP.deviceWidth);
     Y = generator.nextInt(MGP.deviceHeight);
     shape.set(X,Y,X+HEIGHT,Y+WIDTH);
 }
  
   public RectF getShape()
   { 
      return shape;   
   }
   
   public void MoveStar()
   {   
       
       X-=Speed+randS;
         
       if(X<0){
    	 X = MGP.deviceWidth;
         Y = generator.nextInt(MGP.deviceHeight);
         }
       
       if(Speed<=3){
    	   if(MGP.totalSpeed>2)
    	   {
    	   X-=MGP.totalSpeed;
    	   
    	   if(MGP.totalSpeed>0)
    		   shape.set(X,Y,X+(WIDTH)*(MGP.totalSpeed),Y+HEIGHT);
    	   else
    		   shape.set(X,Y,X+WIDTH,Y+HEIGHT);
    	   }
         rSize = generator.nextInt(3);
         WIDTH = rSize;
         HEIGHT = rSize;
         if(MGP.totalSpeed>0)
  		   shape.set(X,Y,X+(WIDTH)*(MGP.totalSpeed),Y+HEIGHT);
  	   else
  		   shape.set(X,Y,X+WIDTH,Y+HEIGHT);
       }
       else
       {
    	   if(MGP.totalSpeed>2)
    		  X-=(MGP.totalSpeed)/2;
    	   if(MGP.totalSpeed>0)
    		   shape.set(X,Y,X+(WIDTH)*(MGP.totalSpeed),Y+HEIGHT);
    	   else
    		   shape.set(X,Y,X+WIDTH,Y+HEIGHT);
       }
   }

 

}
