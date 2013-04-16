package com.example.objects;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;

public class Shot {

	private final double shotSpeed;
	private double angle;
	public double x;
	public double y;
	private double xVelocity;
	private double yVelocity;
	private int lifeLeft;
	private Bitmap bitmap; // the actual bitmap
	int ps, sine = 15;
	int dir = 0;
	private Random generator = new Random();
	public boolean angleChanged;

	public Shot(double x, double y, double angle, int lifeLeft, int shotSpeed,
			Bitmap bitmap, int dir) {
		this.dir = dir;
		this.x = x;
		this.y = y;
		ps = (int) y;
		this.angle = angle;
		xVelocity = shotSpeed * Math.cos(angle);
		yVelocity = shotSpeed * Math.sin(angle);
		this.bitmap = bitmap;
		// the number of frames the shot will last for before disappearing if it
		// doesn't hit anything
		this.lifeLeft = lifeLeft;
		this.shotSpeed = shotSpeed;
		angleChanged = false;
	}

	public Shot(double x, double y, double angle, int lifeLeft, int shotSpeed,
			Bitmap bitmap) {
		this.dir = dir;
		this.x = x;
		this.y = y;
		ps = (int) y;
		this.angle = angle;
		xVelocity = shotSpeed * Math.cos(angle);
		yVelocity = shotSpeed * Math.sin(angle);
		this.bitmap = bitmap;
		// the number of frames the shot will last for before disappearing if it
		// doesn't hit anything
		this.lifeLeft = lifeLeft;
		this.shotSpeed = shotSpeed;
	}

	public void move() {
		lifeLeft--; // make shot disappear if it goes for too long
		if (dir == 0) {
			x += xVelocity;
			y += yVelocity;
		} else if (dir == 1) {
			x -= xVelocity;
			y -= yVelocity;
		}

		if (x > MGP.deviceWidth || x < 0)
			lifeLeft = 0;
	}

	public void changeAngle() {
		this.angle = generator.nextInt(360);
		xVelocity = shotSpeed * Math.cos(angle);
		yVelocity = shotSpeed * Math.sin(angle);
		x += xVelocity;
		y += yVelocity;
		x += xVelocity;
		y += yVelocity;
		x += xVelocity;
		y += yVelocity;
	}

	public void moveSin() {
		lifeLeft -= 0.5;
		sine += 10;
		x += 2;
		y += sine * Math.sin(x);
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, (float) x - (bitmap.getWidth() / 2),
				(float) y - (bitmap.getHeight() / 2), null);
	}

	public int getLifeLeft() {
		return lifeLeft;
	}

}
