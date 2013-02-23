package com.example.objects;

import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;

public class FlyingMessage {


	 //X coordinate
	 int X=0;

	 // Y coordinate
	 int Y=0;
	 
	 // direction to travel
	 int dir=0;
	
	 int speed=0;
	 
	 boolean active=false;
	 
	 String message="";
	 
	 public FlyingMessage(){

	 }
	 
	 public void activate(int dir,int speed,String message){
		 
          this.speed=speed;
		 
		 this.dir=dir;
		 active=true;
		 if(dir==1){
			 X = MGP.deviceWidth*2;
	         Y = MGP.deviceHeight/2; 
		 }
		 else if(dir==0){
			 X = -MGP.deviceWidth*2;
	         Y = MGP.deviceHeight/2; 
		 }
		 
		this.message=message;
		 
	 }
	 
	 public void move(){
		 
      if(active)
		 if(dir==1){
			    X-=speed;
			 if(X<-1000){
				active=false;
				X = MGP.deviceWidth*2;
			 } 
		 }
		 else if(dir==0){
	         X+=speed;
	         if(X<MGP.deviceWidth+200){
					active=false;
					X = MGP.deviceWidth*2;
				 } 

		 }
	 }
	 
	 public void draw(Canvas canvas){
		 MGP.greenPaint.setTextSize((float) MGP.dp[80]); 
		 canvas.drawText(message, X,Y,MGP.greenPaint);	 
	 }
}
