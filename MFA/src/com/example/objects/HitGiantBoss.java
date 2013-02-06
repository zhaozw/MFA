package com.example.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class HitGiantBoss {

    public Shot[] shots;
    public boolean shooting=false;
	public int shotDelay, shotDelayLeft;
    public int numUserShots;
    public int life= 176;
	public HitGiantBossIndividualParts eye,topJaw,bottomJaw;
	
	
	public HitGiantBoss()
	{
		eye = new HitGiantBossIndividualParts(2000);
		topJaw = new HitGiantBossIndividualParts(2000);
		bottomJaw = new HitGiantBossIndividualParts(2000);
	}
	
	public void setImages(Bitmap eyeImg,Bitmap lowerJawImg, Bitmap UpperJawImg)
	{
		eye.setImage(eyeImg);
		topJaw.setImage(UpperJawImg);
		bottomJaw.setImage(lowerJawImg);	
	}
	
	public void Move(int x, int y){
		
		 eye.MoveEye(x, y);
		 topJaw.MoveUpperJaw(x, y);
		 bottomJaw.MoveLowerJaw(x, y);
	}
	
	public void Draw(Canvas canvas)
	{
		 eye.draw(canvas);
		 topJaw.draw(canvas);
		 bottomJaw.draw(canvas);
	}
	
//	 public void setLifeBarSize()
//	   {
//	       lifeBarSize-=6;
//	   }
//	 
	
	
	
//	  public boolean canShoot()
//	   { 
//	       if(shotDelayLeft>0) 
//	         return false; 
//	       else
//	         return true; 
//	   }
//	   
//	   public Shot shoot()
//	   { 
//
//	       shotDelayLeft=shotDelay; 
//	       return new Shot(cx+img.getWidth() / 2,cy,0,60,12,shotImg);
//	   }
}
