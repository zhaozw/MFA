package com.example.mfa.OldMenus_JustForReference;

import com.example.mfa.gamepanel.MainGamePanel;
import com.example.objects.TouchButton;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class OptionsVisuals {

	TouchButton explosionColor,lightColor,exitOptions,resetGame,quitGame,shipImg;
	private Bitmap bitmap;
	
	public OptionsVisuals(Bitmap bitmap)
	{
		this.bitmap = bitmap;
		explosionColor = new TouchButton(bitmap,(MainGamePanel.deviceWidth/5),MainGamePanel.deviceHeight/2,60);
		lightColor = new TouchButton(bitmap,(MainGamePanel.deviceWidth/5)*2,MainGamePanel.deviceHeight/2,60);
		exitOptions  = new TouchButton(bitmap,(MainGamePanel.deviceWidth/5)*3,MainGamePanel.deviceHeight/2,60);
		resetGame = new TouchButton(bitmap,(MainGamePanel.deviceWidth/5)*4,MainGamePanel.deviceHeight/2,60);
		quitGame = new TouchButton(bitmap,(MainGamePanel.deviceWidth/2),MainGamePanel.deviceHeight/5,60);
		shipImg = new TouchButton(bitmap,(MainGamePanel.deviceWidth/2),(MainGamePanel.deviceHeight/4)*3,60);
	}
	
	public void draw(Canvas canvas)
	{
		canvas.drawText("The Options Menu",MainGamePanel.deviceWidth/2,0,MainGamePanel.yellowPaint);
		explosionColor.draw(canvas);
		canvas.drawText("Explosion color",explosionColor.x,explosionColor.y,MainGamePanel.yellowPaint);
		lightColor.draw(canvas);
		canvas.drawText("LightColor",lightColor.x,lightColor.y,MainGamePanel.yellowPaint);
		exitOptions.draw(canvas);
		canvas.drawText("Back To The Action!",exitOptions.x,exitOptions.y,MainGamePanel.yellowPaint);
		resetGame.draw(canvas);
		canvas.drawText("Restart The Game?",resetGame.x,resetGame.y,MainGamePanel.yellowPaint);
		quitGame.draw(canvas);
		MainGamePanel.redPaint.setTextSize(20.0f);
		canvas.drawText("Quit The Game?",quitGame.x,quitGame.y,MainGamePanel.redPaint);
//		shipImg.draw(canvas);
//		MainGamePanel.redPaint.setTextSize(20.0f);
//		canvas.drawText("Ship Img?",shipImg.x,shipImg.y,MainGamePanel.redPaint);
	}
	
		public int updateOptionsCollision(float x , float y)
	{
		int optionsChoice=0;
		//0 = nothing, 1 = explosion color, 2= light color, 3 =exit Options Menu
		if(explosionColor.touchLocation.contains(x,y))
		   optionsChoice=1;
		else if(lightColor.touchLocation.contains(x,y))
		    optionsChoice=2;
		else if(exitOptions.touchLocation.contains(x,y))
		    optionsChoice=3;
		else if(resetGame.touchLocation.contains(x,y))
		    optionsChoice=4;
		else if(quitGame.touchLocation.contains(x,y))
		    optionsChoice=5;
		else if(shipImg.touchLocation.contains(x,y))
		    optionsChoice=6;
		
		return optionsChoice;
	}
}
