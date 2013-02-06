package com.example.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class PowerUpsAll {

	

	public PowerUp spreadShot,shootFaster,slowMo;
	public PowerUp nuke;
	
	public PowerUpsAll()
	{
		spreadShot = new PowerUp(1);
		shootFaster = new PowerUp(2);
		slowMo= new PowerUp(3);
		spreadShot.unlocked=false;
		shootFaster.unlocked=false;
		slowMo.unlocked=false;
		
		nuke = new PowerUp(4);
	}
	
	public PowerUpsAll(Bitmap bomb)
	{
		spreadShot = new PowerUp(1);
		shootFaster = new PowerUp(2);
		slowMo= new PowerUp(3);
		nuke = new PowerUp(4,bomb);
	}
	
	
	public void draw(Canvas canvas)
	{
		spreadShot.draw2(canvas);
		shootFaster.draw2(canvas);
		slowMo.draw2(canvas);
		nuke.draw(canvas);	
	}
	
	public void Update(Player ship)
	{
		
		
		spreadShot.move();
		shootFaster.move();
		slowMo.move();
		nuke.move();
	}
	
}
