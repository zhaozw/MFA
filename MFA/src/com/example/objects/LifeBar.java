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
	int initialWidth, currentWidth;
	double lifeX;
	public int initialLife, life;

	public LifeBar() {
		x = (MGP.deviceWidth / 2) + 50;
		y = MGP.deviceHeight - 30;
		lifeX = 200;
		paint.setColor(Color.GREEN);
	}

	public LifeBar(int x, int y, int life) {
		this.life = life;
		this.x = x;
		this.y = y;
		initialLife = life;
		initialWidth = 200;
		lifeX = 200;
		paint.setColor(Color.GREEN);
	}

	public void draw(Canvas canvas) {
		Paints.white.setStrokeWidth(3);
		Paints.white.setStyle(Paint.Style.STROKE);
		canvas.drawRect(x, y, x + 200, y + 20, Paints.white);
		Paints.white.setStyle(Paint.Style.FILL);
		canvas.drawRect(x + 3, y + 3, x + (int) lifeX, y + 17, paint);
	}

	public void setBar(double x) {
		lifeX = x;
	}

	public void lowerHealth() {
		life--;
		setLifeBarSize();
	}

	public void setLifeBarSize() {

		setBar((life / initialLife) * initialWidth);
		if ((life / initialLife) <= 0.75 && (life / initialLife) > 0.25)
			LifeBar.paint = Paints.yellow;
		else if (life / initialLife <= 0.25)
			LifeBar.paint = Paints.red;
	}
}