package com.example.HitsObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.mfa.R;
import com.example.objects.GameCharacter;
import com.example.objects.Gun;
import com.example.objects.Player;

public class HitGiantBoss extends GameCharacter {

	public Gun gun;
	public int life = 176;
	public HitGiantBossIndividualParts eye, topJaw, bottomJaw;
	public Bitmap shotImg;
	public boolean failed;

	public HitGiantBoss(Context context) {
		eye = new HitGiantBossIndividualParts(BitmapFactory.decodeResource(
				context.getResources(), R.drawable.eye));
		topJaw = new HitGiantBossIndividualParts(BitmapFactory.decodeResource(
				context.getResources(), R.drawable.uj));
		bottomJaw = new HitGiantBossIndividualParts(
				BitmapFactory.decodeResource(context.getResources(),
						R.drawable.lj));
		gun = new Gun(20, 120, BitmapFactory.decodeResource(
				context.getResources(), R.drawable.bullet));
	}

	public void Move(Player ship) {
		eye.MoveEye(ship.cx, ship.cy);
		topJaw.MoveUpperJaw(ship.cx, ship.cy);
		bottomJaw.MoveLowerJaw(ship.cx, ship.cy);
		gun.moveShots();
	}

	public void Draw(Canvas canvas) {
		eye.draw(canvas);
		topJaw.draw(canvas);
		bottomJaw.draw(canvas);
		gun.draw(canvas);

	}

	public void lowerHealth() {

	}

}
