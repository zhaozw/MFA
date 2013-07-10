package com.example.HitsObjects;

import java.util.Random;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.mfa.R;
import com.example.objects.GameCharacter;
import com.example.objects.Gun;
import com.example.objects.Paints;
import com.example.objects.Player;

public class FollowAI extends GameCharacter {

	public Gun gun;
	Random generator = new Random();

	public int followXdistance = 0, followYdistance = 0, xdeadZone = 0,
			ydeadZone = 0;
	public int avoidXdistance = 0, avoidYdistance = 0, xAdeadZone = 0,
			yAdeadZone = 0;;
	public boolean failed = false;

	public FollowAI(Context context) {
		img = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.followship);
		width = img.getWidth();
		height = img.getHeight();
		radius = width / 2;
		gun = new Gun(30, 15, null);
		x = (-200);
		y = (150);

		setFollowVariables(130, 10, 40, 20);
		setAvoidVariables(80, 100, 30, 20);

		// ready to shoot
	}

	@Override
	public void draw(Canvas canvas) {

		gun.draw(canvas);

		if (img != null) {
			canvas.drawBitmap(img, x, y, null);
		} else
			canvas.drawText("bitmap aint workin", cx, cy, Paints.red);

	}

	public void followVariables(int sx, int sy) {

		if ((sy - y) > followYdistance)
			y += 1;
		else if ((sy - y) < -1 * followYdistance)
			y -= 1;

		if ((sx - x) > followXdistance + xdeadZone)
			x += 2;
		else if ((sx - x) < followXdistance)
			x -= 2;

		Log.d("FollowAI ", "following behind");
	}

	public void setFollowVariables(int fxd, int fyd, int xdz, int ydz) {

		followXdistance = fxd;
		followYdistance = fyd;
		xdeadZone = xdz;
		ydeadZone = ydz;

	}

	public void setAvoidVariables(int axd, int ayd, int xAdz, int yAdz) {

		avoidXdistance = axd;
		avoidYdistance = ayd;
		xAdeadZone = xAdz;
		yAdeadZone = yAdz;

	}

	public void move(Player ship) {
		if ((ship.x - x) < 0) {
			avoidVariables(ship.x, ship.y);
		} else {
			followVariables(ship.x, ship.y);
		}

		gun.moveShots();

		cx = x + (img.getWidth() / 2);
		cy = y + (img.getHeight() / 2);

	}

	public void avoidVariables(int sx, int sy) {

		if ((sy - y) >= 0) {
			// alien ship is above

			if ((sy - y) < avoidYdistance
					&& (sy - y) < avoidYdistance + yAdeadZone)
				y -= 1;
			else if ((sy - y) > avoidYdistance + yAdeadZone)
				y += 1;

		} else if ((sy - y) < 0) {
			// alien ship is below
			if ((sy - y) * (-1) < avoidYdistance
					&& (sy - y) * (-1) < avoidYdistance + yAdeadZone)
				y += 1;
			else if ((sy - y) * (-1) > avoidYdistance + yAdeadZone)
				y -= 1;

		}

		if ((sx - x) > -100)
			x += 2;

		else if ((sx - x) < -150)

			x -= 2;

		Log.d("FollowAI ", "following behind");
	}

	public boolean checkCollisionFront(Player ship) {
		// using the distance formula to check for collisions.
		if (Math.pow(radius + ship.radius + 30, 2) > Math.pow(ship.cx
				- followXdistance - cx, 2)
				+ Math.pow(ship.cy - cy, 2))
			return true;
		return false;
	}

}