package com.example.objects;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class GameCharacter {

	public int x;
	public int cx, cy;
	public int y;
	public int width, height;
	public int radius;
	protected Random r;
	public boolean unlocked = true;
	protected Bitmap img;
	public boolean dead = false;
	public Point lastCollision;

	public GameCharacter(Bitmap IMG, double x, double y) {

		if (IMG != null) {
			this.img = IMG;
			width = img.getWidth();
			height = img.getHeight();
		}

		this.x = (int) x;
		this.y = (int) y;

		lastCollision = new Point(-1, -1);
	}

	public GameCharacter(Bitmap IMG) {

		if (IMG != null) {
			this.img = IMG;
			width = img.getWidth();
			height = img.getHeight();
		}

		lastCollision = new Point(-1, -1);
	}

	public GameCharacter() {

		lastCollision = new Point(-1, -1);
	}

	public int getCX() {
		return cx = x + (img.getWidth() / 2);
	}

	public int getCY() {
		return cy = y + (img.getHeight() / 2);
	}

	public void draw(Canvas canvas) {

		if (img != null) {
			canvas.drawBitmap(img, x, y, null);
		} else if (dead == false)
			canvas.drawCircle(cx, cy, radius, Paints.lightGreen);
		else if (dead == true)
			canvas.drawCircle(cx, cy, radius, Paints.grey);

		canvas.drawCircle(lastCollision.x, lastCollision.y, 2,
				Paints.lightGreen);

	}

	public void initializeBitmap(Bitmap img) {
		this.img = img;
		width = img.getWidth();
		height = img.getHeight();
	}

	public boolean RadiusCollision(int oRadius, int ox, int oy) {
		// using the distance formula to check for collisions.
		if (Math.pow(radius + oRadius, 2) > Math.pow(ox - cx, 2)
				+ Math.pow(oy - cy, 2))
			return true;
		return false;
	}

	public boolean RadiusCollision(int oRadius, double ox, double oy) {
		// using the distance formula to check for collisions.
		if (Math.pow(radius + oRadius, 2) > Math.pow(ox - cx, 2)
				+ Math.pow(oy - cy, 2))
			return true;
		return false;
	}

	public boolean touchCollision(int tx, int ty) {
		if (tx > x && tx < x + img.getWidth() && ty > y
				&& ty < y + img.getHeight())
			return true;
		return false;
	}

	public boolean checkForCollision(GameCharacter other) {

		try {

			if (x < 0 && other.x < 0 && y < 0 && other.y < 0)
				return false;
			Rect r1 = new Rect(x, y, x + width, y + height);
			Rect r2 = new Rect(other.x, other.y, other.x + other.width, other.y
					+ other.height);
			Rect r3 = new Rect(r1);
			if (r1.intersect(r2)) {
				for (int i = r1.left; i < r1.right; i++) {
					for (int j = r1.top; j < r1.bottom; j++) {
						if (img.getPixel(i - r3.left, j - r3.top) != Color.TRANSPARENT) {
							if (other.img.getPixel(i - r2.left, j - r2.top) != Color.TRANSPARENT) {
								lastCollision = new Point(
										other.x + i - r2.left, other.y + j
												- r2.top);
								return true;
							}
						}
					}
				}
			}

			return false;

		} catch (Exception e) {
			Log.d("Game Character", "collision error" + e);
			return false;
		}

	}

}