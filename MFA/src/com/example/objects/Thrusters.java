package com.example.objects;

import android.graphics.Canvas;

public class Thrusters {
	FireBall[] FireBalls = new FireBall[20];
	public boolean active;
	public int explodeColor = 3;

	public Thrusters() {
		for (int k = 0; k < FireBalls.length; k++)
			FireBalls[k] = new FireBall();

	}

	public void Update(int x, int y) {
		for (int k = 0; k < FireBalls.length; k++)
			FireBalls[k].ThrustersGo(x, y);
	}

	public void Draw(Canvas canvas) {
		for (int k = 0; k < FireBalls.length; k++)
			FireBalls[k].drawThrusters(canvas);
	}

}
