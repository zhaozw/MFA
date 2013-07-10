package com.example.HitsObjects;

import java.util.Random;

import android.content.Context;
import android.graphics.BitmapFactory;
import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;
import com.example.objects.GameCharacter;

public class AsteroidHitSmall extends GameCharacter {
	double xVelocity, yVelocity;
	int life = 3;
	Random generator = new Random();

	public AsteroidHitSmall(Context context) {
		super();
		this.img = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.massiveasteroid);

		this.radius = (img.getWidth() / 2);
		this.y = generator.nextInt(2000);
		this.x = generator.nextInt(2000) + 10000;

		int minVelocity = 7;
		int maxVelocity = 20;
		// calculates a random direction and a random
		// velocity between minVelocity and maxVelocity
		double vel = minVelocity + Math.random() * (maxVelocity - minVelocity), dir = 2 * Math.PI * 1; // random
																										// direction
		xVelocity = (int) vel * Math.cos(dir);
		yVelocity = (int) vel * Math.sin(dir);
	}

	public void move() {

		x -= xVelocity; // move the asteroid
		y -= yVelocity;
		x -= MGP.totalSpeed / 2;
		cy = y + (img.getHeight() / 2);
		cx = x + (img.getWidth() / 2);
	}

	public void wasHit() {

		// life--;
		// if (life < 1) {
		// failed = true;
		// x = 2000;
		// }

	}

}