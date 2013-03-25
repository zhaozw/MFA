package com.example.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.mfa.gamepanel.MGP;

public class LifeBar {
	public static Paint paint = new Paint();
	public RectF shape;
	int x, y;
	double lifeX;

	public LifeBar() {
		x = (MGP.deviceWidth / 2) + 50;
		y = MGP.deviceHeight - 30;
		lifeX = 200;
		paint.setColor(Color.GREEN);
	}

	public void draw(Canvas canvas) {
		MGP.WPaint.setStrokeWidth(3);
		MGP.WPaint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(x, y, x + 200, y + 20, MGP.WPaint);
		MGP.WPaint.setStyle(Paint.Style.FILL);
		canvas.drawRect(x + 3, y + 3, x + (int) lifeX, y + 17, paint);
	}

	public void setBar(double x) {
		lifeX = x;
	}
}