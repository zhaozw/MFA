package com.example.objects;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;

public class Player {
	public int x, cx, y, cy;
	private Bitmap img1, img2, img3, img4, img5, shotImg; // the actual bitmap
	private int speed = 2;

	public int radius = 17;
	public int imgChoice = 1;
	Random generator = new Random();
	public boolean exit = false, enter = false;
	// shot array
	public Shot[] shots;
	public boolean shooting = false;
	public int shotDelay, shotDelayLeft;
	public int numUserShots;
	public boolean unlocked = true;
	private LifeBar bar;
	public static int life;
	private double initialLife;

	public Player(Bitmap i1, Bitmap shotImg, int x, int y) {
		bar = new LifeBar();
		this.img1 = i1;
		this.shotImg = shotImg;
		this.y = y;
		this.x = x;
		shotDelay = 11;
		shotDelayLeft = 0;
		shots = new Shot[40];
		life = 3;
		initialLife = life;

	}

	public void drawShots(Canvas canvas) {
		// draw all the shots on the screen
		for (int i = 0; i < numUserShots; i++) // numUserShots
		{
			shots[i].draw(canvas);
		}
	}

	public void moveShots() {
		// move shots and remove dead shots
		for (int i = 0; i < numUserShots; i++) {
			// if(powerUp.sineBullets)
			shots[i].move();
			// removes shot if it has gone for too long
			// without hitting anything
			if (shots[i].getLifeLeft() <= 0) {
				// shifts all the next shots up one
				// space in the array

				deleteShot(i);
				i--; // move the outer loop back one so
						// the shot shifted up is not skipped
			}
		}

	}

	public void moveShotsSin() {
		// move shots and remove dead shots
		for (int i = 0; i < numUserShots; i++) {
			// if(powerUp.sineBullets)
			shots[i].moveSin();
			// removes shot if it has gone for too long
			// without hitting anything
			if (shots[i].getLifeLeft() <= 0) {
				// shifts all the next shots up one
				// space in the array

				deleteShot(i);
				i--; // move the outer loop back one so
						// the shot shifted up is not skipped
			}
		}
	}

	public boolean checkForNewShots() {
		if (shooting && canShoot()) {
			// set the trigger to play the laser to 0

			// add a shot on to the array
			shots[numUserShots] = shoot();
			numUserShots++;
			return true;
		}
		return false;
	}

	public void checkForNewShots2() {
		if (shooting && canShoot()) {
			// set the trigger to play the laser to 0

			// add a shot on to the array
			shots[numUserShots] = shoot();
			numUserShots++;
		}
	}

	public void deleteShot(int index) {
		// delete shot and move all shots after it up in the array
		numUserShots--;
		for (int i = index; i < numUserShots; i++)
			shots[i] = shots[i + 1];
		shots[numUserShots] = null;
	}

	public void draw(Canvas canvas) {

		bar.draw(canvas);
		canvas.drawBitmap(img1, x, y, null);
	}

	public void move(int xDiff, int yDiff) {
		if (unlocked) {
			// ship will move forward
			if (xDiff / 6 > 0 && x < MGP.deviceWidth - 70)
				x += (xDiff / 7);

			// ship will move backwards
			if (xDiff / 6 < 0 && x > 0)
				x += (xDiff / 7);

			// ship will move down
			if (yDiff / 6 > 0 && y < MGP.deviceHeight - 90)
				y += (yDiff / 7);

			// ship will move up
			if (yDiff / 6 < 0 && y > 0)
				y += (yDiff / 7);
		} else if (enter) {
			if (x > (MGP.deviceHeight / 8))
				x -= 2;
			else {
				unlocked = true;
				enter = false;
			}
		} else if (exit) {
			if (x >= (MGP.deviceWidth / 2) - 5
					&& x <= (MGP.deviceWidth / 2) + 5
					&& y >= (MGP.deviceHeight / 2) - 5
					&& y <= (MGP.deviceHeight / 2) + 5) {
				exit = false;
				unlocked = false;
			} else {
				unlocked = false;
				if (x < MGP.deviceWidth / 2) {
					x += 2;
				} else {
					x -= 2;
				}
				if (y < MGP.deviceHeight / 2) {
					y += 2;
				} else {
					y -= 2;
				}
			}
		}

		if (shotDelayLeft > 0)
			shotDelayLeft--;
		cx = x + (img1.getWidth() / 2);
		cy = y + (img1.getHeight() / 2);
	}

	public boolean shotCollision(Shot shot) {
		// Uses the distance formula to check for collisions with other shots.
		return (Math.pow(radius, 2) > Math.pow(shot.x - cx, 2)
				+ Math.pow(shot.y - cy, 2));
	}

public void setLifeBarSize(){
	
	bar.setBar((life / initialLife) * 200);
	if ((life / initialLife) <= 0.75 && (life / initialLife) > 0.25)
		LifeBar.paint = Paints.yellow;
	else if (life / initialLife <= 0.25)
		LifeBar.paint = Paints.red;
}
	
	public boolean canShoot() {
		if (shotDelayLeft > 0)
			return false;
		else
			return true;
	}

	public Shot shoot() {
		shotDelayLeft = shotDelay + generator.nextInt(2);
		return new Shot(cx + img1.getWidth() / 2, cy, 0, 60, 12, shotImg, 0);
	}
}