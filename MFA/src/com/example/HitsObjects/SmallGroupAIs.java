package com.example.HitsObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.objects.Player;

public class SmallGroupAIs {
	public AIPack groupAI;
	public boolean failed = true;

	public SmallGroupAIs(Bitmap img) {
		groupAI = new AIPack(10, img, img);
	}

	public void move(Player ship) {
		groupAI.move(ship);
	}

	public void draw(Canvas canvas) {
		groupAI.draw(canvas);
	}

}
