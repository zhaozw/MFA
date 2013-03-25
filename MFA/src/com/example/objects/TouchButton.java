package com.example.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.mfa.gamepanel.MGP;

public class TouchButton {
	int size;
	public int x, y, h, w;
	public RectF touchLocation, shadow;
	private Bitmap bitmap; // the actual bitmap
	public boolean active = false, shade;

	public TouchButton(Bitmap b, int x, int y, int h, int w) {
		bitmap = b;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		touchLocation = new RectF(x, y, h, w);
	}

	public TouchButton(Bitmap b, int x, int y, int size) {
		bitmap = b;
		this.x = x;
		this.y = y;
		this.w = x + size;
		this.h = y + size;
		this.size = size;
		touchLocation = new RectF(x, y, x + size, y + size);
	}

	public TouchButton(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.w = x + size;
		this.h = y + size;
		this.size = size;
		touchLocation = new RectF(x, y, w, h);
	}

	public TouchButton(double x, double y, double size, boolean shade) {
		this.x = (int) x;
		this.y = (int) y;
		this.w = (int) (x + size);
		this.h = (int) (y + size);
		this.size = (int) size;
		touchLocation = new RectF(this.x, this.y, w, h);
		this.shade = shade;

		if (shade) {
			shadow = new RectF((int) x, MGP.deviceHeight - 20,
					(int) (x + size), MGP.deviceHeight - 5);
		}
	}

	public boolean getActiveState() {
		return active;
	}

	public void setActiveState(boolean a) {
		active = a;
	}

	public RectF getShape() {
		return touchLocation;
	}

	public void draw(Canvas canvas) {
		if (bitmap != null)
			canvas.drawBitmap(bitmap, x, y, null);
		else {
			if (shade)
				canvas.drawOval(shadow, MGP.blackPaint);
			canvas.drawOval(touchLocation, MGP.redPaint);
		}
	}
}