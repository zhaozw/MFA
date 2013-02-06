package com.example.mfa.OldMenus_JustForReference;

import com.example.mfa.gamepanel.MainGamePanel;
import com.example.objects.Player;
import com.example.objects.TouchButton;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class MainMenuVisuals {

	TouchButton gameStart,options,exitGame,hits;
	private Bitmap logoImg;
	public static double logoCount;
	
	public MainMenuVisuals(Bitmap logoImg, Bitmap startGame, Bitmap optionsBtn, Bitmap hitsBtn, Bitmap exitBtn)
	{
		gameStart = new TouchButton(startGame,(MainGamePanel.deviceWidth/5),MainGamePanel.deviceHeight/2,60);
		options = new TouchButton(optionsBtn,(MainGamePanel.deviceWidth/5)*2,MainGamePanel.deviceHeight/2,60);
		hits  = new TouchButton(hitsBtn,(MainGamePanel.deviceWidth/5)*3,MainGamePanel.deviceHeight/2,60);
		exitGame  = new TouchButton(exitBtn,(MainGamePanel.deviceWidth/5)*4,MainGamePanel.deviceHeight/2,60);
		
		this.logoImg=logoImg;
		logoCount = 0;
	}
	
	
	public void draw(Canvas canvas,Player ship)
	{
		if(logoCount < 15)
		{
			canvas.drawColor(Color.WHITE);
			canvas.drawBitmap(logoImg, (MainGamePanel.deviceWidth/2) - (logoImg.getWidth() / 2),(MainGamePanel.deviceHeight/2) - (logoImg.getHeight() / 2), null);
			updateLogo();
		}
		else
		{
			ship.enter=true;
			MainGamePanel.redPaint.setTextSize(37.0f);
			canvas.drawText("Mutha F$@%ing Asteroids",50,80,MainGamePanel.redPaint);	
			gameStart.draw(canvas);
			options.draw(canvas);
			hits.draw(canvas);
			exitGame.draw(canvas);
		}
	}
	
	public void updateLogo()
	{
		if (logoCount < 20)
			logoCount += 0.1;		
	}
	
	public int updateMenuCollision(float x, float y)
	{
		
		
		int menuState;
		//0 is main menu/1 is game start/2 is options/3 is hits/4 is exit
		
		if(gameStart.touchLocation.contains(x,y))
		    menuState=1;
		else if(options.touchLocation.contains(x,y))
		    menuState=2;
		else if(hits.touchLocation.contains(x,y))
		    menuState=3;
		else if(exitGame.touchLocation.contains(x,y))
		    menuState=4;
		else
			menuState=0;
		
		
		return menuState;
	}
	
//	public boolean updateGameStartCollision(Shot shot)
//	{
//		return gameStart.shotCollision(shot);
//	}
//	
//	public boolean updateOptionsCollision(Shot shot)
//	{
//		return(options.shotCollision(shot));
//	}
//	
//	public boolean updateHitsCollision(Shot shot)
//	{
//		return(hits.shotCollision(shot));
//	}
//	
//	public boolean updateExitGameCollision(Shot shot)
//	{
//		return(exitGame.shotCollision(shot));
//	}
	
}
