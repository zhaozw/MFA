package com.example.mfa.OldMenus_JustForReference;

import com.example.mfa.gamepanel.MainGamePanel;
import com.example.objects.TouchButton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class MainGameVisuals {

	public TouchButton pauseButton,optionsButton,shootButton;
	private Bitmap bar;
	 //images of life bar
    private Bitmap[] lifeBar = new Bitmap[4];
    private int life, barY;
	
	public MainGameVisuals(Bitmap pause,Bitmap shoot,Bitmap options)//,Bitmap bar,Bitmap l1,Bitmap l2,Bitmap l3,Bitmap l4)
	{
		this.bar=bar;
		optionsButton = new TouchButton(options,0,30,50);
		shootButton = new TouchButton(shoot,MainGamePanel.deviceWidth-50,MainGamePanel.deviceHeight-50,50);
	}
	
	public void draw(Canvas canvas)
	{
		optionsButton.draw(canvas);
		canvas.drawOval(shootButton.touchLocation,MainGamePanel.s1);
		//MainGamePanel.redPaint.setTextSize(50.0f);
		//canvas.drawText("S",shootButton.x+10,shootButton.y+40,MainGamePanel.redPaint);	
		
		   //get ready get set go
		   MainGamePanel.textPaint.setTextSize(70.0f);
		   if(MainGamePanel.state==0&&MainGamePanel.waveDelay<300&&MainGamePanel.waveDelay>200) { 
			 canvas.drawText("Get Ready", 150, 200,MainGamePanel.textPaint ); 
		   }  
		   else if(MainGamePanel.state==0&&MainGamePanel.waveDelay<200&&MainGamePanel.waveDelay>100){
			 canvas.drawText("Get Set", 150, 200,MainGamePanel.textPaint ); 
		   }
		   else if(MainGamePanel.state==0&&MainGamePanel.waveDelay<100&&MainGamePanel.waveDelay>0){
			 canvas.drawText("Destroy", 150, 200,MainGamePanel.textPaint ); 
		   }	
		   
		   MainGamePanel.bluePaint.setTextSize(30.0f);
		   if(MainGamePanel.state==2&&MainGamePanel.waveDelay<300&&MainGamePanel.waveDelay>200) { 
				 canvas.drawText("Wave "+MainGamePanel.wave+" Completed", 10, 200,MainGamePanel.bluePaint ); 
		 }  
		 else if(MainGamePanel.state==2&&MainGamePanel.waveDelay<200&&MainGamePanel.waveDelay>100){
		 canvas.drawText("You Killed "+MainGamePanel.astKilledThisWave +" Asteroids", 10, 200,MainGamePanel.bluePaint ); 
		 }
		 else if(MainGamePanel.state==2&&MainGamePanel.waveDelay<100&&MainGamePanel.waveDelay>0){
		 canvas.drawText("Wave "+(MainGamePanel.wave+1)+" will begin", 10, 200,MainGamePanel.bluePaint ); 
		 }
	     else if(MainGamePanel.state==2&&MainGamePanel.waveDelay<50&&MainGamePanel.waveDelay>0){
	     canvas.drawText("Go ", 50, 200,MainGamePanel.bluePaint ); 
	     }
			
	}
	
	public int updateMenuTouch(float x,float y)
	{
		if(optionsButton.touchLocation.contains(x,y))
		   return 1;
		else if(shootButton.touchLocation.contains(x,y))
		   return 3;
		else
			return 0;
	}
}
