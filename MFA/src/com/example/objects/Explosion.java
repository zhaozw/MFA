package com.example.objects;

import android.graphics.Canvas;

public class Explosion {

	public FireBall[] FireBalls = new FireBall[17];
	boolean exp;
	public int startX = 0, startY = 0;

	public Explosion() {

		for (int k = 0; k < FireBalls.length; k++) {
			if (k < 7)
				FireBalls[k] = new FireBall(1, 0);
			else if (k < 13)
				FireBalls[k] = new FireBall(2, 0);
			else if (k < 18)
				FireBalls[k] = new FireBall(3, k - 12);
		}

	}

	// public Explosion()
	// {
	//
	// for(int k=0; k<FireBalls.length;k++)
	// {
	// if(k<6)
	// FireBalls[k] = new FireBall(1,0);
	// else if(k>5)
	// FireBalls[k] = new FireBall(2,0);
	// else if(k<29)
	// FireBalls[k] = new FireBall(3,k-19);
	// }
	//
	// }

	public void triggerExplosions2(int x, int y) {
		for (int k = 0; k < FireBalls.length; k++) {
			FireBalls[k].explode = true;
			FireBalls[k].X = x;
			FireBalls[k].Y = y;
			startX = x;
			startY = y;
		}
	}

	public void triggerExplosions(int x, int y) {
		{
			for (int k = 0; k < FireBalls.length; k++)
				FireBalls[k].ExplodeTrue(x, y);
			startX = x;
			startY = y;
		}
	}

	public void triggerExplosionsNuke(int x, int y) {
		for (int k = 0; k < FireBalls.length; k++) {
			FireBalls[k].nuke = true;
			FireBalls[k].explode = true;
			FireBalls[k].X = x;
			FireBalls[k].Y = y;
			startX = x;
			startY = y;
		}

	}

	public void updateExplosions() {

		for (int k = 0; k < FireBalls.length; k++) {
			if (FireBalls[k].explode)
				FireBalls[k].Explode();
		}

	}

	public void draw(Canvas canvas) {

		// switch (MGP.explodeColor) {
		// case (0):

		for (int k = 0; k < FireBalls.length; k++) {
			FireBalls[k].drawExplosionsCustom(canvas);
		}

		// break;
		// case (1):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions1(canvas);
		// }
		// break;
		// case (2):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions2(canvas);
		// }
		// break;
		// case (3):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions3(canvas);
		// }
		// break;
		// case (4):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions4(canvas);
		// }
		// break;
		// case (5):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions5(canvas);
		// }
		// break;
		// case (6):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions6(canvas);
		// }
		// break;
		// case (7):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions7(canvas);
		// }
		// break;
		// case (8):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions8(canvas);
		// }
		// break;
		// case (9):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions9(canvas);
		// }
		// break;
		// case (10):
		// for (int k = 0; k < FireBalls.length; k++) {
		// FireBalls[k].drawExplosions10(canvas);
		// }
		// break;

		// }

	}
	// canvas.drawOval(lights2[r][c].getShape(),MGP.WPaint);

}
