package com.example.objects;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.mfa.gamepanel.MGP;

public class PowerUp {
	double radius = 10;
	public int x, cx;
	public int y, cy;
	int type = 0;
	int width = 30;
	int height = 30;
	public boolean active = false;
	public boolean unlocked = false;
	Random generator = new Random();
	public RectF shape;
	Bitmap img;
	int color;
	// for the powerUps time period
	int powerUpTime = 400;

	// 1 spreadShot, 2 shootFaster, 3 slowMo, 4 nuke;

	public PowerUp(int t) {
		type = t;
		x += generator.nextInt(MGP.deviceWidth) + (MGP.deviceWidth * 2);
		x += MGP.deviceWidth;
		y = generator.nextInt(MGP.deviceHeight);
		type = t;
		if (type == 4) {
			width += 20;
			height += 20;
		}
		shape = new RectF(x, y, x + width, y + height);
		shape = new RectF(x, y, x + width, y + height);
	}

	public PowerUp(int t, Bitmap img) {
		x += generator.nextInt(MGP.deviceWidth) + (MGP.deviceWidth * 2);
		x += MGP.deviceWidth;
		y = generator.nextInt(MGP.deviceHeight);
		this.img = img;
		type = t;
		if (type == 4)
			width += 20;
		height += 20;
		shape = new RectF(x, y, x + width, y + height);
	}

	public void move() {

		// whether it is in use or not is active
		// whether is is unlocked depending on wave is unlocked
		// IF UNLOCKED AND NOT ACTIVE THEN MOVE THE POWERUP
		if (unlocked && !active) {
			x -= 2;
			x -= MGP.totalSpeed;

			// if it is out of the screen move it back to the other side
			if (x <= -100) {
				moveBack();
			}
		}// IF IT IS UNLOCKED THEN GET IT OUT OF THE SCREEN AND KEEP IT THERE
		else if (unlocked == false) {
			// if its unlocked but its already on screen we want to move it out
			// of screen
			// before locking it
			if (x < MGP.deviceWidth + 100) {
				x -= MGP.totalSpeed;
				x -= 10; // move the powerUp
				if (x < -100) {
					moveBack();
				}
			}
		}
		// IF IT IS BEING USED THEN GET IT OUT OF SCREEN AND COUNT DOWN ITS
		// TIMER
		if (active == true) {
			x = 2000;
			powerUpTime--;
			if (powerUpTime == 0)
				active = false;

		}
		cx = x + (width / 2);
		cy = y + (height / 2);
		shape.set(x, y, x + width, y + height);
	}

	public boolean shipCollision(Player ship) {
		// using the distance formula to check for collisions.
		if (Math.pow(radius + ship.radius, 2) > Math.pow(ship.cx - cx, 2)
				+ Math.pow(ship.cy - cy, 2)) {
			return true;
		} else
			return false;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(img, cx - (img.getWidth() / 2), cy
				- (img.getWidth() / 2), null);
	}

	public void draw2(Canvas canvas) {
		switch (type) {
		case (1):
			canvas.drawOval(shape, MGP.bluePaint);
			// canvas.drawText("Spread Shot", x, y,MGP.textPaint );
			break;
		case (2):
			canvas.drawOval(shape, MGP.redPaint);
			// canvas.drawText("Shoot Faster", x, y,MGP.textPaint );
			break;
		case (3):
			canvas.drawOval(shape, MGP.yellowPaint);
			// canvas.drawText("SlowMo", x, y,MGP.textPaint );
			break;
		case (4):
			canvas.drawOval(shape, MGP.greenPaint);
			canvas.drawText("Nuke", x, y, MGP.textPaint);
			break;
		}
	}

	public void moveBack() {
		x += generator.nextInt(MGP.deviceWidth * 5) + MGP.deviceWidth;
		y = generator.nextInt(MGP.deviceHeight);
	}

	public void unlock() {
		x += generator.nextInt(MGP.deviceWidth * 5) + MGP.deviceWidth;
		unlocked = true;
	}

	public void activate() {
		powerUpTime = 500;
		active = true;
	}

	public void activate(int time) {
		powerUpTime = time;
		active = true;
	}

}
