package com.example.objects;

import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.HitsObjects.AsteroidHitLarge;
import com.example.HitsObjects.AsteroidHitMedium;
import com.example.HitsObjects.AsteroidHitSmall;
import com.example.HitsObjects.FollowAI;
import com.example.HitsObjects.Hit10;
import com.example.HitsObjects.Hit11;
import com.example.HitsObjects.Hit12;
import com.example.HitsObjects.Hit13;
import com.example.HitsObjects.Hit14;
import com.example.HitsObjects.Hit15;
import com.example.HitsObjects.Hit7;
import com.example.HitsObjects.Hit8;
import com.example.HitsObjects.Hit9;
import com.example.HitsObjects.HitGiantBoss;
import com.example.HitsObjects.MineField;
import com.example.HitsObjects.SmallGroupAIs;
import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;

public class HitsAllInfo {

	public HitsInfo[] hitsInfo;

	public HitGiantBoss hit0;
	public AsteroidHitSmall hit1;
	public AsteroidHitMedium hit2;
	public AsteroidHitLarge hit3;
	public FollowAI hit4;
	public SmallGroupAIs hit5;
	public MineField hit6;
	public Hit7 hit7;
	public Hit8 hit8;
	public Hit9 hit9;
	public Hit10 hit10;
	public Hit11 hit11;
	public Hit12 hit12;
	public Hit13 hit13;
	public Hit14 hit14;
	public Hit15 hit15;

	public FlyingMessage flyingMessage;
	public int currentlyActivatedHit = -1;
	Random randomGenerator;
	int[] currentlySetWaves;
	int[] setHits = new int[3];
	int totalActiveHits;

	public HitsAllInfo() {
		hitsInfo = new HitsInfo[16];
		randomGenerator = new Random();
		flyingMessage = new FlyingMessage();

		for (int k = 0; k < hitsInfo.length; k++) {
			hitsInfo[k] = new HitsInfo();
		}

	}

	public void initialize(HashMap map) {

		for (int k = 0; k < 16; k++) {

			Log.d("Hits All Info ", "starting loop");
			if (map.get("Hit" + k + "Active").toString().equals("1")) {
				totalActiveHits++;
				Log.d("Hits All Info ", "setting active");
				hitsInfo[k].active = true;

				Log.d("Hits All Info ", "setting name");
				hitsInfo[k].name = (String) map.get("Hit" + k + "From");

				Log.d("Hits All Info ", "setting message");
				hitsInfo[k].message = (String) map.get("Hit" + k + "Msg");

			}

			else if (map.get("Hit" + k + "Active").toString().equals("0"))
				hitsInfo[k].active = false;
		}

		Log.d("Hits All Info ", "deactivating hits");
		if (totalActiveHits > 3) {
			while (totalActiveHits > 3) {
				int k = randomGenerator.nextInt(16);

				if (hitsInfo[k].active == true) {
					hitsInfo[k].active = false;
					totalActiveHits--;
				}
			}
		}

		Log.d("Hits All Info ", "retrieving active hits");
		int c = 0;
		for (int k = 0; k < hitsInfo.length; k++) {

			if (hitsInfo[k].active == true) {
				setHits[c] = k;
				c++;
			}
		}

		Log.d("Hits All Info ", "setting up activation waves");
		for (int k = 0; k < 3; k++) {
			if (k == 0)
				hitsInfo[setHits[k]].activationWave = randomGenerator
						.nextInt(1) + 1;
			else if (k == 1)
				hitsInfo[setHits[k]].activationWave = randomGenerator
						.nextInt(3) + 2;
			else if (k == 2)
				hitsInfo[setHits[k]].activationWave = randomGenerator
						.nextInt(3) + 5;
		}
	}

	public void checkStartHit(Context context) {
		for (int k = 0; k < hitsInfo.length; k++) {
			if (hitsInfo[k].activationWave == MGP.wave)
				createHit(k, context);
		}
	}

	public void createHit(int hit, Context context) {

		switch (hit) {

		case (0):
			Log.d("Hits All Info ", "creating hit 0");
			hit0 = new HitGiantBoss();
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[0].name + ":  "
					+ hitsInfo[0].message);
			hit0.setImages(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.eye100), BitmapFactory.decodeResource(
					context.getResources(), R.drawable.lj300), BitmapFactory
					.decodeResource(context.getResources(), R.drawable.uj300),
					BitmapFactory.decodeResource(context.getResources(),
							R.drawable.bullet));
			break;

		case (1):

			Log.d("Hits All Info ", "creating hit 1");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[1].name + ":  "
					+ hitsInfo[1].message);
			hit1 = new AsteroidHitSmall(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.ag100), MGP.dp[7],
					MGP.dp[20], MGP.deviceWidth, MGP.deviceHeight);
			break;

		case (2):

			Log.d("Hits All Info ", "creating hit 2");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[2].name + ":  "
					+ hitsInfo[2].message);
			hit2 = new AsteroidHitMedium(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.ab165), MGP.dp[7],
					MGP.dp[20], MGP.deviceWidth, MGP.deviceHeight);

			break;
		case (3):
			Log.d("Hits All Info ", "creating hit 3");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[3].name + ":  "
					+ hitsInfo[3].message);
			hit3 = new AsteroidHitLarge(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.ar250), MGP.dp[7],
					MGP.dp[20], MGP.deviceWidth, MGP.deviceHeight);

			break;
		case (4):

			Log.d("Hits All Info ", "creating hit 4");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[4].name + ":  "
					+ hitsInfo[4].message);
			hit4 = new FollowAI(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.tinyship1));

			break;
		case (5):

			Log.d("Hits All Info ", "creating hit 5");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[5].name + ":  "
					+ hitsInfo[5].message);

			hit5 = new SmallGroupAIs(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.vship));
			break;
		case (6):

			Log.d("Hits All Info ", "creating hit 6");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[6].name + ":  "
					+ hitsInfo[6].message);

			hit6 = new MineField(10, BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge));
			break;
		case (7):

			Log.d("Hits All Info ", "creating hit 7");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[7].name + ":  "
					+ hitsInfo[7].message);

			hit7 = new Hit7(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (8):

			Log.d("Hits All Info ", "creating hit 8");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[8].name + ":  "
					+ hitsInfo[8].message);

			hit8 = new Hit8(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (9):

			Log.d("Hits All Info ", "creating hit 9");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[9].name + ":  "
					+ hitsInfo[9].message);

			hit9 = new Hit9(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (10):

			Log.d("Hits All Info ", "creating hit 10");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[10].name
					+ ":  " + hitsInfo[10].message);

			hit10 = new Hit10(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (11):

			Log.d("Hits All Info ", "creating hit 11");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[11].name
					+ ":  " + hitsInfo[11].message);

			hit11 = new Hit11(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (12):

			Log.d("Hits All Info ", "creating hit 12");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[12].name
					+ ":  " + hitsInfo[12].message);

			hit12 = new Hit12(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (13):

			Log.d("Hits All Info ", "creating hit 13");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[13].name
					+ ":  " + hitsInfo[13].message);

			hit13 = new Hit13(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (14):

			Log.d("Hits All Info ", "creating hit 14");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[14].name
					+ ":  " + hitsInfo[14].message);

			hit14 = new Hit14(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (15):

			Log.d("Hits All Info ", "creating hit 15");
			flyingMessage.activate(1, (int) MGP.dp[3], hitsInfo[15].name
					+ ":  " + hitsInfo[15].message);

			hit15 = new Hit15(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		}

		currentlyActivatedHit = hit;
	}

	public void MoveHits(Player ship) {

		flyingMessage.move();

		switch (currentlyActivatedHit) {

		case (0):
			hit0.Move(ship);
			break;
		case (1):
			hit1.move();
			break;
		case (2):
			hit2.move();
			break;
		case (3):
			hit3.move();
			break;
		case (4):
			hit4.move(ship.cx, ship.cy);
			break;
		case (5):
			hit5.move(ship);
			break;
		case (6):
			hit6.move();
			break;
		case (7):
			hit7.move();
			break;
		case (8):
			hit8.move();
			break;
		case (9):
			hit9.move();
			break;
		case (10):
			hit10.move();
			break;
		case (11):
			hit11.move();
			break;
		case (12):
			hit12.move();
			break;
		case (13):
			hit13.move();
			break;
		case (14):
			hit14.move();
			break;
		case (15):
			hit15.move();
			break;
		default:
			// Log.d("Hits All Info ", "no hit currently in game");
			break;

		}

	}

	public void DrawMessage(Canvas canvas) {
		flyingMessage.draw(canvas);
	}

	public void DrawHits(Canvas canvas) {

		for (int k = 0; k < 3; k++) {
			canvas.drawText("hit " + setHits[k] + " "
					+ hitsInfo[setHits[k]].name + " activation Wave "
					+ hitsInfo[setHits[k]].activationWave, 0,
					(k + 1) * 10 + 10, MGP.orangePaint);
		}

		switch (currentlyActivatedHit) {

		case (0):
			hit0.Draw(canvas);
			break;
		case (1):
			hit1.draw(canvas);
			break;
		case (2):
			hit2.draw(canvas);
			break;
		case (3):
			hit3.draw(canvas);
			break;
		case (4):
			hit4.draw(canvas);
			break;
		case (5):
			hit5.draw(canvas);
			break;
		case (6):
			hit6.draw(canvas);
			break;
		case (7):
			hit7.draw(canvas);
			break;
		case (8):
			hit8.draw(canvas);
			break;
		case (9):
			hit9.draw(canvas);
			break;
		case (10):
			hit10.draw(canvas);
			break;
		case (11):
			hit11.draw(canvas);
			break;
		case (12):
			hit12.draw(canvas);
			break;
		case (13):
			hit13.draw(canvas);
			break;
		case (14):
			hit14.draw(canvas);
			break;
		case (15):
			hit15.draw(canvas);
			break;

		default:

			break;
		}

	}

	public void drawHitInfo(Canvas canvas) {
		// canvas.drawText("hit 0 from "+hitsInfo[0].name,0,50,MGP.orangePaint);
		// canvas.drawText("hit 0 message"+hitsInfo[0].message,0,60,MGP.orangePaint);
		// canvas.drawText("hit 0 activation wave"+hitsInfo[0].activationWave,0,70,MGP.orangePaint);
		//
		// canvas.drawText("hit 1 from "+hitsInfo[1].name,0,80,MGP.orangePaint);
		// canvas.drawText("hit 1 message"+hitsInfo[1].message,0,90,MGP.orangePaint);
		// canvas.drawText("hit 1 activation wave"+hitsInfo[1].activationWave,0,100,MGP.orangePaint);
		// canvas.drawText("currently actvivated hit "+currentlyActivatedHit,0,115,MGP.orangePaint);
	}

	public void checkHitFailure() {

		switch (currentlyActivatedHit) {

		case (0):
			if (hit0.failed)
				currentlyActivatedHit = -1;
			break;
		case (1):
			if (hit1.failed)
				currentlyActivatedHit = -1;
			break;
		case (2):
			if (hit2.failed)
				currentlyActivatedHit = -1;
			break;
		case (3):
			if (hit3.failed)
				currentlyActivatedHit = -1;
			break;
		case (4):
			if (hit4.failed)
				currentlyActivatedHit = -1;
			break;
		case (5):
			if (hit5.failed)
				currentlyActivatedHit = -1;
			break;
		case (6):
			if (hit6.failed)
				currentlyActivatedHit = -1;
			break;
		case (7):
			if (hit7.failed)
				currentlyActivatedHit = -1;
			break;
		case (8):
			if (hit8.failed)
				currentlyActivatedHit = -1;
			break;
		case (9):
			if (hit9.failed)
				currentlyActivatedHit = -1;
			break;
		case (10):
			if (hit10.failed)
				currentlyActivatedHit = -1;
			break;
		case (11):
			if (hit11.failed)
				currentlyActivatedHit = -1;
			break;
		case (12):
			if (hit12.failed)
				currentlyActivatedHit = -1;
			break;
		case (13):
			if (hit13.failed)
				currentlyActivatedHit = -1;
			break;
		case (14):
			if (hit14.failed)
				currentlyActivatedHit = -1;
			break;
		case (15):
			if (hit15.failed)
				currentlyActivatedHit = -1;
			break;

		default:
			Log.d("Hits All Info ", "no hit currently in game");
			break;

		}

	}

}