package com.example.objects;

//this class is to be used to make multiple lights to move quickly 
//across the screen to create the appearence of motion

import java.util.Random;

import com.example.mfa.gamepanel.MainGamePanel;


import android.graphics.RectF;
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
// MainGamePanel.deviceHeight
 //MainGamePanel.deviceWidth
 public RectF  shape = new RectF(X,Y,X+100,Y+100);
 //Light initialization
  public Light()
 {
     Speed = 10;
     WIDTH = 5;
     HEIGHT = 5;
     
     X = generator.nextInt(MainGamePanel.deviceWidth);
     Y = generator.nextInt(MainGamePanel.deviceHeight);
  // RectF  shape = new RectF(X,Y,X+10,Y+10);
 }
 
 public Light(int s,int w)
 {
     Speed = s;
     WIDTH = w;
     HEIGHT = 2;
     randS = generator.nextInt(Speed);
     X = generator.nextInt(MainGamePanel.deviceWidth);
     Y = generator.nextInt(MainGamePanel.deviceHeight);
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
    	 X = MainGamePanel.deviceWidth;
         Y = generator.nextInt(MainGamePanel.deviceHeight);
         }
       
       if(Speed<=3){
    	   if(MainGamePanel.totalSpeed>2)
    	   {
    	   X-=MainGamePanel.totalSpeed;
    	   
    	   if(MainGamePanel.totalSpeed>0)
    		   shape.set(X,Y,X+(WIDTH)*(MainGamePanel.totalSpeed),Y+HEIGHT);
    	   else
    		   shape.set(X,Y,X+WIDTH,Y+HEIGHT);
    	   }
         rSize = generator.nextInt(3);
         WIDTH = rSize;
         HEIGHT = rSize;
         if(MainGamePanel.totalSpeed>0)
  		   shape.set(X,Y,X+(WIDTH)*(MainGamePanel.totalSpeed),Y+HEIGHT);
  	   else
  		   shape.set(X,Y,X+WIDTH,Y+HEIGHT);
       }
       else
       {
    	   if(MainGamePanel.totalSpeed>2)
    		  X-=(MainGamePanel.totalSpeed)/2;
    	   if(MainGamePanel.totalSpeed>0)
    		   shape.set(X,Y,X+(WIDTH)*(MainGamePanel.totalSpeed),Y+HEIGHT);
    	   else
    		   shape.set(X,Y,X+WIDTH,Y+HEIGHT);
       }
   }

 

}
