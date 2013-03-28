package com.example.HitsObjects;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.mfa.gamepanel.MGP;
import com.example.objects.Player;
import com.example.objects.Shot;

public class Mine {

	int innerRadius;
	public int outerRadius;
	int width, height;
	int x, y;
	public int cx;
	public int cy;
	public int activationTime = 25;
	public boolean activated;
	public boolean exploded;
	public boolean dead;
	Bitmap img;
	RectF outerRing;
	Random generator = new Random();

	public Mine(Bitmap img) {
		this.img = img;
		width = img.getWidth();
		height = img.getHeight();
		innerRadius = width / 2;
		outerRadius = width;
		this.y = generator.nextInt(MGP.deviceHeight);
		this.x = generator.nextInt(MGP.deviceWidth * 3) + MGP.deviceWidth
				+ (int) MGP.dp[150];
		outerRing = new RectF(x, y, x + outerRadius, x + outerRadius);
	}

	public void draw(Canvas canvas) {
		if(!dead){
			if (activated == false) {
				MGP.greenPaint.setStyle(Paint.Style.STROKE);
				canvas.drawCircle(cx, cy, outerRadius, MGP.greenPaint);
			} else if (activated == true) {
				MGP.redPaint.setStyle(Paint.Style.STROKE);
				canvas.drawCircle(cx, cy, outerRadius, MGP.redPaint);
			}
	
			if (img != null)
				canvas.drawBitmap(img, x, y, null);
			else {
				canvas.drawText("shit Aint working", x, y, MGP.redPaint);
	
			}
		}
	}

	public void move() {
		if(!dead){
				x -= 1;
	
			if (activated)
				activationTime--;
	
			cx = x + (width / 2);
			cy = y + (height / 2);
		}
	}

	public void exploded() {
		exploded = true;
		activated = false;
		activationTime = 100;
		this.x = generator.nextInt(MGP.deviceWidth) + MGP.deviceWidth
				+ (int) MGP.dp[150];
		cx = x + (width / 2);
		cy = y + (height / 2);
		dead=true;
	}

	public boolean shipInnerCollision(Player ship) {
		// using the distance formula to check for collisions.
		if (Math.pow(innerRadius + ship.radius, 2) > Math.pow(ship.cx - cx, 2)
				+ Math.pow(ship.cy - cy, 2))
			return true;
		return false;
	}

	public boolean shipOuterCollision(Player ship) {
		// using the distance formula to check for collisions.
		if (Math.pow(outerRadius + ship.radius, 2) > Math.pow(ship.cx - cx, 2)
				+ Math.pow(ship.cy - cy, 2))
			return true;
		return false;
	}

	public boolean shotCollision(Shot shot) {
		// Uses the distance formula to check for collisions with other shots.
		return (Math.pow(innerRadius, 2) > Math.pow(shot.x - cx, 2)
				+ Math.pow(shot.y - cy, 2));
	}

	public boolean mineOuterCollision(Mine mine) {
		// using the distance formula to check for collisions.
		if (Math.pow(outerRadius + mine.outerRadius, 2) > Math.pow(
				mine.cx - cx, 2) + Math.pow(mine.cy - cy, 2))
			return true;
		return false;
	}

}
