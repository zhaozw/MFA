package com.example.HitsObjects;

import com.example.mfa.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class MineField {
	public boolean failed;
	public Mine[] mines;

	public MineField(Context context) {
		mines = new Mine[50];
		for (int k = 0; k < mines.length; k++)
			mines[k] = new Mine(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.mine1));
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
