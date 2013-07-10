package com.example.objects;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.mfa.gamepanel.MGP;

public class AnalogStick {
	// size of the big circle
	int size;
	int analogSize;
	int x, y, cx, cy;
	int acx, acy;
	public RectF fullPad;
	public RectF activeLocation, stick;
	public int xDiff;
	public int yDiff;

	// accepts size and size as parameters to create the object
	public AnalogStick(int s, int as) {
		this.size = s;
		analogSize = as;
		this.x = 10;
		this.y = MGP.deviceHeight - size - 10;
		fullPad = new RectF(x, y, x + size, y + size);
		cx = (x + (size / 2));
		cy = (y + (size / 2));
		activeLocation = new RectF(cx - (analogSize / 2),
				cy - (analogSize / 2), cx + (analogSize / 2), cy
						+ (analogSize / 2));
		acx = ((int) activeLocation.left + (analogSize / 2));
		acy = ((int) activeLocation.top + (analogSize / 2));

	}

	public void draw(Canvas canvas) {
		canvas.drawOval(fullPad, Paints.silver1);
		canvas.drawLine(cx, cy, acx, acy, Paints.black);
		canvas.drawOval(activeLocation, Paints.silver2);
	}

	public RectF getFullPad() {
		return fullPad;
	}

	// public void GetInputAndDisplay(Canvas canvas) {
	// canvas.drawText("ACTIVE lOCATION X " + activeLocation.left, 15, 200,
	// MGP.WPaint);
	// canvas.drawText("ACTIVE lOCATION y " + activeLocation.top, 15, 210,
	// MGP.WPaint);
	// canvas.drawText("ACTIVE lOCATION BOTTOM  " + activeLocation.bottom, 15,
	// 220, MGP.WPaint);
	// canvas.drawText("ACTIVE lOCATION RIGHT SIDE " + activeLocation.right,
	// 15, 230, MGP.WPaint);
	// canvas.drawText("ACTIVE lOCATION CENTER X " + acx, 15, 240, MGP.WPaint);
	// canvas.drawText("ACTIVE lOCATION CENTER y " + acy, 15, 250, MGP.WPaint);
	// canvas.drawText("xDiff " + xDiff, 15, 260, MGP.WPaint);
	// canvas.drawText("yDiff " + yDiff, 15, 270, MGP.WPaint);
	// canvas.drawText("STANDARD CENTER X " + cx, 15, 280, MGP.WPaint);
	// canvas.drawText("STANDARD CENTER y " + cy, 15, 290, MGP.WPaint);
	// }

	public void SetActiveLocation(float x, float y) {
		activeLocation.set(x - (analogSize / 2), y - (analogSize / 2), x
				+ (analogSize / 2), y + (analogSize / 2));
		acx = ((int) activeLocation.left + (analogSize / 2));
		acy = ((int) activeLocation.top + (analogSize / 2));

		xDiff = acx - cx;
		yDiff = acy - cy;
	}

	public void SetActiveLocation() {
		activeLocation.set(cx - (analogSize / 2), cy - (analogSize / 2), cx
				+ (analogSize / 2), cy + (analogSize / 2));
		acx = ((int) activeLocation.left + (analogSize / 2));
		acy = ((int) activeLocation.top + (analogSize / 2));
		xDiff = acx - cx;
		yDiff = acy - cy;
	}

}
