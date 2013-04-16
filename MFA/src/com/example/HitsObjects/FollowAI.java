package com.example.HitsObjects;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.example.mfa.gamepanel.MGP;
import com.example.objects.Paints;
import com.example.objects.Player;
import com.example.objects.Shot;

public class FollowAI {

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

	public int followXdistance = 0, followYdistance = 0, xdeadZone = 0,
			ydeadZone = 0;
	public int avoidXdistance = 0, avoidYdistance = 0,xAdeadZone = 0,
			yAdeadZone = 0;;
	public boolean failed=false;
	
	
	public FollowAI(Bitmap bitmap, Bitmap shotImg) {
		img = bitmap;
		width = img.getWidth();
		height = img.getHeight();
		radius = width / 2;
		this.shotImg = shotImg;
		x = (200);
		y = (150);
		shots = new Shot[20];
		shotDelay = 30;
		shotDelayLeft = 0;
		setFollowVariables((int)MGP.dp[130], (int)MGP.dp[10],(int) MGP.dp[40], (int)MGP.dp[20]);
		setAvoidVariables((int)MGP.dp[80], (int)MGP.dp[100],(int)MGP.dp[30], (int)MGP.dp[20]);
		
		// ready to shoot
	}



	public void draw(Canvas canvas) {

	

		// draw all the shots on the screen
		for (int i = 0; i < numShots; i++) // numUserShots
		{
			shots[i].draw(canvas);
		}

		if (img != null) {
			canvas.drawBitmap(img, x, y, null);
		} else
			canvas.drawText("bitmap aint workin", cx, cy, Paints.red);
		
	}

	public void followVariables(int sx, int sy) {

		if ((sy - y) > followYdistance)
			y += 1;
		else if ((sy - y) <-1* followYdistance)
			y -= 1;

		if ((sx - x) > followXdistance + xdeadZone)
			x += 2;
		else if ((sx - x) < followXdistance )
			x -= 2;

		Log.d("FollowAI ", "following behind");
	}

	public void setFollowVariables(int fxd, int fyd, int xdz, int ydz) {

		followXdistance = fxd;
		followYdistance = fyd;
		xdeadZone = xdz;
		ydeadZone = ydz;

	}
	
	public void setAvoidVariables(int axd, int ayd,int xAdz, int yAdz) {

		avoidXdistance = axd;
		avoidYdistance = ayd;
		xAdeadZone = xAdz;
		yAdeadZone = yAdz;
	}
	
	public void move(int shipX, int shipY) {
		if ((shipX - x) < 0){
			avoidVariables(shipX, shipY);
		}
		else {
		    followVariables(shipX, shipY);
		}
		
		moveShots();
		if (shotDelayLeft > 0)
			shotDelayLeft--;
		cx = x + (img.getWidth() / 2);
		cy = y + (img.getHeight() / 2);

	
	}

	public void avoidVariables(int sx, int sy) {

		if ((sy - y) >= 0){
			// alien ship is above
			
			
			if ((sy - y) < avoidYdistance&&(sy - y) < avoidYdistance+yAdeadZone)
				y -= 1;
			else if((sy - y) > avoidYdistance+yAdeadZone)
				y += 1;
	
		}
		else if ((sy - y) < 0){
			//alien ship is below
			 if ((sy - y)*(-1) < avoidYdistance&&(sy - y)*(-1) < avoidYdistance+yAdeadZone)
				y += 1;	
			 else if ((sy - y)*(-1) > avoidYdistance+yAdeadZone)
					y -= 1;	
			 
		}
		
		


		if ((sx - x) > -100)
			x += 2;

		else if ((sx - x) < -150)
						
			x -= 2;

		Log.d("FollowAI ", "following behind");
	}
	

	
	public boolean shotCollision(Shot shot) {
		if (Math.pow(radius, 2) > Math.pow(shot.x - cx, 2)
				+ Math.pow(shot.y - cy, 2))
			return true;
		return false;
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


	public boolean checkCollisionFront(Player ship){
			// using the distance formula to check for collisions.
			if (Math.pow(radius + ship.radius+30, 2) > Math.pow(ship.cx-followXdistance - cx, 2)
					+ Math.pow(ship.cy - cy, 2))
				return true;
			return false;	
	}
	
	//
	public Shot shoot() {
		shotDelayLeft = shotDelay;
		return new Shot(cx, cy, 0, (int) MGP.dp[60], (int) MGP.dp[10], shotImg,
				0);
	}
}