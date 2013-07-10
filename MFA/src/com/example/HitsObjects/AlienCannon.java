package com.example.HitsObjects;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;
import com.example.objects.GameCharacter;
import com.example.objects.LifeBar;

public class AlienCannon extends GameCharacter {

	// movement variables
	public boolean moveDown = true, entered = false;
	public int initialSpeed = 25, currentSpeed = 25;

	public Bitmap firedAlienImg, firedAlienShotImg;

	// the array of alies that are to be fired at the player
	public FiredAliens[] firedAliens;

	// shot delay, this correlates to the rate of fire
	// the lower the number the faster it fires
	// to be used in the firing of aliens
	public int shotDelay;
	public int shotDelayLeft;
	public int numShots = 0;
	Random generator;
	public boolean failed = false;

	public LifeBar lifebar;

	public int firedAliensStartX, firedAliensStartY;

	public AlienCannon(Context context) {
		super();
		x = 2000;
		y = 0;
		img = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.splitship2);
		firedAlienImg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.projectile135);
		firedAlienShotImg = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.laser);
		firedAliens = new FiredAliens[10];
		shotDelay = 58;
		shotDelayLeft = 10;
		generator = new Random();
		lifebar = new LifeBar((MGP.deviceWidth / 2) + 50, 10, 3);
	}

	public void move() {

		if (x > MGP.deviceWidth - width && entered == false) {
			x -= 1;
		} else
			entered = true;

		if (entered)
			if (moveDown) {
				y += currentSpeed;
				currentSpeed -= 1;
				if (currentSpeed <= 0) {
					moveDown = false;
					currentSpeed = initialSpeed;
				}
			} else {
				y -= currentSpeed;
				currentSpeed -= 1;
				if (currentSpeed <= 0) {
					moveDown = true;
					currentSpeed = initialSpeed;
				}
			}

		moveAliens();

		cx = x + (img.getWidth() / 2);
		cy = y + (img.getHeight() / 2);

		if (shotDelayLeft > 0 && entered)
			shotDelayLeft--;

	}

	@Override
	public void draw(Canvas canvas) {
		drawAliens(canvas);
		super.draw(canvas);
		lifebar.draw(canvas);
	}

	public void drawAliens(Canvas canvas) {
		// draw all the shots on the screen
		for (int i = 0; i < numShots; i++) // numUserShots
		{
			firedAliens[i].draw(canvas);
		}
	}

	public void moveAliens() {
		// move shots and remove dead shots
		for (int i = 0; i < numShots; i++) {
			// if(powerUp.sineBullets)
			firedAliens[i].move();
			// removes shot if it has gone for too long
			// without hitting anything
			if (firedAliens[i].x <= -100) {
				// shifts all the next shots up one
				// space in the array

				deleteShot(i);
				i--; // move the outer loop back one so
						// the shot shifted up is not skipped
			}
		}

	}

	public boolean checkForNewShots() {
		if (entered && canShoot()) {
			// set the trigger to play the laser to 0

			// add a shot on to the array
			firedAliens[numShots] = shoot();
			numShots++;
			return true;
		}
		return false;
	}

	public void deleteShot(int index) {
		// delete shot and move all shots after it up in the array
		numShots--;
		for (int i = index; i < numShots; i++)
			firedAliens[i] = firedAliens[i + 1];
		firedAliens[numShots] = null;
	}

	public boolean canShoot() {
		if (shotDelayLeft > 0)
			return false;
		else
			return true;
	}

	public FiredAliens shoot() {

		shotDelayLeft = shotDelay + generator.nextInt(5);
		return new FiredAliens(firedAlienImg, firedAlienShotImg, cx, cy);
	}

	public void lowerHealth() {
		lifebar.lowerHealth();

		if (lifebar.life <= 0)
			fail();

	}

	public void fail() {
		failed = true;
		firedAliens = null;
		firedAlienImg = null;
		firedAlienShotImg = null;
		firedAliens = null;
		generator = null;
	}

}
