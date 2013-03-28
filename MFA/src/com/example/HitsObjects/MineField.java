package com.example.HitsObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MineField {
	public boolean failed;
	public Mine[] mines;

	public MineField(int amount, Bitmap bitmap) {
		mines = new Mine[amount];
		for (int k = 0; k < mines.length; k++)
			mines[k] = new Mine(bitmap);
	}

	public MineField(Bitmap bitmap) {
		mines = new Mine[10];

		for (int k = 0; k < mines.length; k++)
			mines[k] = new Mine(bitmap);
	}

	public void move() {
		for (int k = 0; k < mines.length; k++)
			mines[k].move();
	}

	public void draw(Canvas canvas) {
		for (int k = 0; k < mines.length; k++)
			mines[k].draw(canvas);
	}

}
