package com.example.HitsObjects;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;
import com.example.objects.Shot;

public class AI {

	public Shot[] shots;
	public boolean shooting = true;
	public int shotDelay, shotDelayLeft;
	public Bitmap shotImg;
	public int numShots;

	public int radius;
	Random generator = new Random();
	public int width, height;
	public int x, y, cy, cx;
	public Bitmap img;
	public AI following;
	public int followXdistance = 0, followYdistance = 0, xdeadZone = 0,
			ydeadZone = 0;
	public boolean dead = false;

	public AI(Bitmap bitmap, Bitmap shotImg) {
		img = bitmap;
		width = img.getWidth();
		height = img.getHeight();
		radius = width / 2;
		this.shotImg = shotImg;
		x = (int) (MGP.deviceWidth+MGP.dp[50]);
		y = (MGP.deviceHeight/2);
		shots = new Shot[20];
		shotDelay = 400;
		shotDelayLeft = 0;

		// ready to shoot
	}

	public void setFollowing(AI ai) {
		following = ai;
	}

	public void draw(Canvas canvas) {

		if (img != null) {
			canvas.drawBitmap(img, x, y, null);
			//canvas.drawLine(cx, cy, following.cx, following.cy, MGP.orangePaint);
		}
//		else
//			canvas.drawText("bitmap aint workin", cx, cy, MGP.redPaint);

		// draw all the shots on the screen
		for (int i = 0; i < numShots; i++) // numUserShots
		{
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

	}

	public boolean checkForNewShots() {
		if (shooting && canShoot()) {
			// set the trigger to play the laser to 0

			// add a shot on to the array
			shots[numShots] = shoot();
			numShots++;
			return true;
		} else
			return false;
	}

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

	//
	public Shot shoot() {
		shotDelayLeft = shotDelay;
		return new Shot(cx, cy, 0, (int) MGP.dp[60], (int) MGP.dp[10], shotImg,
				1);
	}

	public void moveVariables() {

		if ((following.y - y) > followYdistance)
			y += 1;
		else if ((following.y - y) < followYdistance + ydeadZone)
			y -= 1;

		if ((following.x - x) > followXdistance)
			x += 2;
		else if ((following.x - x) < followXdistance + xdeadZone)
			x -= 2;

		cx = x + (img.getWidth() / 2);
		cy = y + (img.getHeight() / 2);

		if (shotDelayLeft > 0)
			shotDelayLeft--;

	}

	public void setFollowVariables(int fxd, int fyd, int xdz, int ydz) {

		followXdistance = fxd;
		followYdistance = fyd;
		xdeadZone = xdz;
		ydeadZone = ydz;

	}

	public void move(int shipX, int shipY) {
		// if ((shipX-x)<0)
		// MoveAI2(shipY,shipX);
		// else
		// {
		// if(enter&&x<40)
		// x+=1;

		if ((shipY - y) > 10)
			y += 1;
		else if ((shipY - y) < -10)
			y -= 1;

		// if(shipX<800)//shipX>150)
		// {
		if ((shipX - x) > -200)
			x += 2;
		else if ((shipX - x) < -50)
			x -= 2;
		// }
		// }

		cx = x + (img.getWidth() / 2);
		cy = y + (img.getHeight() / 2);
	}

	public void MoveAI2(int shipY, int shipX) {
		if (shipY > 200 && y > 100)
			y -= 4;
		else if (shipY < 200 && y < 300)
			y += 4;
		if (shipX > 300 && x > 100)
			x -= 4;
		else if (shipX < 300 && x < 400)
			x += 4;
	}

	public boolean shotCollision(Shot shot) {
		if (Math.pow(radius, 2) > Math.pow(shot.x - cx, 2)
				+ Math.pow(shot.y - cy, 2))
			return true;
		return false;
	}

}
