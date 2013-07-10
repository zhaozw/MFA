package com.example.HitsObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;
import com.example.objects.GameCharacter;
import com.example.objects.Gun;

public class FiredAliens extends GameCharacter {

	double xVelocity, yVelocity;
	public boolean entered;
	public Gun lazerGun;

	public FiredAliens(Bitmap img, Bitmap shotImg, int x, int y) {
		super(img, x, y);
		lazerGun = new Gun(13, 15, null);
		radius = img.getWidth() / 2;
		double minVelocity = MGP.dp[6];
		double maxVelocity = MGP.dp[15];
		double vel = minVelocity + Math.random() * (maxVelocity - minVelocity); // velocity
		// between
		// minVelocity
		// and
		// maxVelocity
		double dir = 2 * Math.PI; // direction of asteroids

		xVelocity = (int) vel * Math.cos(dir);
		yVelocity = (int) vel * Math.sin(dir);

	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		lazerGun.draw(canvas);
	}

	public void move() {

		x -= xVelocity;
		y -= yVelocity;

		cx = x + (img.getWidth() / 2);
		cy = y + (img.getHeight() / 2);

		lazerGun.moveShots();

	}

}
