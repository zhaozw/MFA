package com.example.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class PowerUpsAll {

	public PowerUp spreadShot, shootFaster, slowMo;
	public PowerUp nuke;

	public PowerUpsAll() {
		spreadShot = new PowerUp(1);
		shootFaster = new PowerUp(2);
		slowMo = new PowerUp(3);
		spreadShot.unlocked = false;
		shootFaster.unlocked = false;
		slowMo.unlocked = false;

		nuke = new PowerUp(4);
	}

	public PowerUpsAll(Bitmap bomb, Bitmap sine, Bitmap slow, Bitmap fast) {
		spreadShot = new PowerUp(1, sine);
		shootFaster = new PowerUp(2, fast);
		slowMo = new PowerUp(3, slow);
		nuke = new PowerUp(4, bomb);
	}

	public void draw(Canvas canvas) {
		spreadShot.draw(canvas);
		shootFaster.draw(canvas);
		slowMo.draw(canvas);
		nuke.draw(canvas);
	}

	public void Update(Player ship) {

		spreadShot.move();
		shootFaster.move();
		slowMo.move();
		nuke.move();
	}

}
