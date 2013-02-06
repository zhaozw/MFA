package com.example.mfa.OldMenus_JustForReference;

import com.example.mfa.gamepanel.MainGamePanel;
import com.example.objects.TouchButton;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameOverVisuals {

	TouchButton exit,resetGame;
	private Bitmap bitmap;
	
	public GameOverVisuals(Bitmap bitmap)
	{
		this.bitmap = bitmap;
		exit  = new TouchButton(bitmap,(MainGamePanel.deviceWidth/5),MainGamePanel.deviceHeight/5,60);
		resetGame = new TouchButton(bitmap,(MainGamePanel.deviceWidth/5)*3,MainGamePanel.deviceHeight/5,60);
	}
	
	public void draw(Canvas canvas)
	{
	    MainGamePanel.textPaint.setTextSize(70.0f);
		canvas.drawText("GAME OVER",60, 50,MainGamePanel.textPaint ); 
		canvas.drawText("Final Score "+ MainGamePanel.score+" Points", 100, 170,MainGamePanel.bluePaint ); 
		canvas.drawText("Asteroids killed: "+ MainGamePanel.totalAsteroidsKilled, 100, 200,MainGamePanel.bluePaint ); 
		exit.draw(canvas);
		canvas.drawText("Exit?",exit.x,exit.y,MainGamePanel.yellowPaint);
		resetGame.draw(canvas);
		canvas.drawText("Restart The Game?",resetGame.x,resetGame.y,MainGamePanel.yellowPaint);
	}
	
		public int updateGameOverCollision(float x , float y)
	{
		int optionsChoice=0;
		//0 = nothing, 1 = explosion color, 2= light color, 3 =exit Options Menu

		if(exit.touchLocation.contains(x,y))
		    optionsChoice=1;
		else if(resetGame.touchLocation.contains(x,y))
		    optionsChoice=2;
		
		return optionsChoice;
	}
}
