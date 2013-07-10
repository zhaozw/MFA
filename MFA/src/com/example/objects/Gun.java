package com.example.objects;

import com.example.mfa.gamepanel.MGP;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Gun {

	// the array holding the bullets
	public Shot[] shots;

	// if the gun should fire if shot delay has run out
	public boolean shooting = true;

	// this is the speed the bullets travel
	public double speed;

	// angle or direction in which the bullets will fire
	public double angle;

	// shot delay, this correlates to the rate of fire
	// the lower the number the faster it fires
	public int shotDelay,

	// delay until next shot can be fired
			shotDelayLeft;

	// image of the bullet
	public Bitmap img;

	// current total shots in the array
	public int numShots;

	public int startX, startY;

	public Gun(int shotDelay, double speed, Bitmap shotImg) {

		shots = new Shot[20];
		this.img = shotImg;
		this.shotDelay = shotDelay;
		this.speed = speed;

	}

	public void draw(Canvas canvas) {
		for (int i = 0; i < numShots; i++) {
			shots[i].draw(canvas);
		}
	}

	public void moveShots() {
		// move shots and remove dead shots
		for (int i = 0; i < numShots; i++) {
			shots[i].move();
			// removes old shots and shifts shot array
			if (shots[i].getLifeLeft() <= 0) {
				deleteShot(i); // move the outer loop back one so
				i--;// the shot shifted up is not skipped
			}
		}
		shotDelayLeft--;

	}

	// public boolean checkForNewShots() {
	// if (shooting && canShoot()) {
	// // set the trigger to play the laser to 0
	//
	// // add a shot on to the array
	// shots[numShots] = shoot(startX,startY);
	// numShots++;
	// return true;
	// } else
	// return false;
	// }

	public void deleteShot(int index) {
		// delete shot and move all shots after it up in the array
		numShots--;
		for (int i = index; i < numShots; i++)
			shots[i] = shots[i + 1];
		shots[numShots] = null;
	}

	public boolean canShoot() {
		if (shotDelayLeft > 0)
			return false;
		else
			return true;
	}

	public void setAngle(int startX, int startY, int otherX, int otherY) {
		angle = Math.atan2(otherY - startY, otherX - startX);
	}

	public Shot shoot(int x, int y) {
		shotDelayLeft = shotDelay;
		return new Shot(x, y, angle, (int) MGP.dp[60], speed, img);
	}

	public boolean checkForNewShots(int x, int y) {
		if (shooting && canShoot()) {
			// set the trigger to play the laser to 0

			// add a shot on to the array
			shots[numShots] = shoot(x, y);
			numShots++;
			return true;
		} else
			return false;
	}

}
