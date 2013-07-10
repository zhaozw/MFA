package com.example.HitsObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;
import com.example.mfa.networking.LoginFunctions;
import com.example.objects.FlyingMessage;
import com.example.objects.Paints;
import com.example.objects.Player;

public class HitsAllInfo {

	// public HitsInfo[] hitsInfo;

	public ArrayList<HitsInfo> hitsInGame;
	public ArrayList<HitsInfo> hitsThatArrived;
	public HitGiantBoss hit0;
	public AsteroidHitSmall hit1;
	public AsteroidHitLarge hit2;
	public FollowAI hit3;
	public AIPack hit4;
	public AIPack hit5;
	public MineField hit6;
	public Hit7 hit7;
	public AlienCannon hit8;
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
	public int[] setHits = new int[3];
	public int totalActiveHits;

	Context context;

	public HitsAllInfo(Context context) {
		this.context = context;
		hitsInGame = new ArrayList<HitsInfo>();
		hitsThatArrived = new ArrayList<HitsInfo>();
		randomGenerator = new Random();
		flyingMessage = new FlyingMessage();

	}

	public void initializeOnline(HashMap<String, String> onlineHits) {

		for (int k = 0; k < onlineHits.size(); k++) {
			if (onlineHits.get("Hit" + k + "Active") != null)
				if (onlineHits.get("Hit" + k + "Active").toString().equals("1")) {
					totalActiveHits++;
					hitsInGame.add(new HitsInfo());
					hitsInGame.get(hitsInGame.size()-1).active = true;
					hitsInGame.get(hitsInGame.size()-1).HitID = onlineHits
							.get("Hit" + k + "From");
					hitsInGame.get(hitsInGame.size()-1).name = LoginFunctions
							.getPlayerNameFromID(context,
									onlineHits.get("Hit" + k + "From"));
					hitsInGame.get(hitsInGame.size()-1).message = onlineHits.get("Hit" + k
							+ "Msg");
					hitsInGame.get(hitsInGame.size()-1).hitType = k;
				}
		}

		for (int k = 0; k < hitsInGame.size(); k++) {

			do {
				hitsInGame.get(k).activationWave = randomGenerator.nextInt(4) + 1;
			} while (!ActivationWaveAvailabile(k));
		}
	}

	public void initialize2(ArrayList<HitsInfo> customHits) {

		for (int k = 0; k < customHits.size(); k++) {
			totalActiveHits++;
			hitsInGame.add(new HitsInfo());
			hitsInGame.get(k).active = true;
			hitsInGame.get(k).name = customHits.get(k).name;
			hitsInGame.get(k).message = customHits.get(k).message;
			hitsInGame.get(k).hitType = customHits.get(k).hitType;
		}

		for (int k = 0; k < hitsInGame.size(); k++) {

			do {
				hitsInGame.get(k).activationWave = randomGenerator.nextInt(4) + 1;
			} while (!ActivationWaveAvailabile(k));
		}
	}

	public boolean ActivationWaveAvailabile(int hit) {
		for (int k = 0; k < hitsInGame.size(); k++) {
			if (k != hit)
				if (hitsInGame.get(k).activationWave == hitsInGame.get(hit).activationWave)
					return false;

		}
		return true;

	}

	public void checkStartHit(Context context) {
		for (int k = 0; k < hitsInGame.size(); k++) {
			if (hitsInGame.get(k).activationWave == MGP.wave) {
				hitsThatArrived.add(hitsInGame.get(k));
				createHit(k);
			}

		}
	}

	public void createHit(int hit) {

		switch (hitsInGame.get(hit).hitType) {

		case (0):
			Log.d("Hits All Info ", "creating hit 0");
			hit0 = new HitGiantBoss(context);
			break;
		case (1):
			Log.d("Hits All Info ", "creating hit 1");
			hit1 = new AsteroidHitSmall(context);
			break;
		case (2):
			Log.d("Hits All Info ", "creating hit 2");
			hit2 = new AsteroidHitLarge(context);
			break;
		case (3):
			Log.d("Hits All Info ", "creating hit 3");
			hit3 = new FollowAI(context);
			break;
		case (4):
			Log.d("Hits All Info ", "creating hit 4");
			hit4 = new AIPack(context, 15);
			break;
		case (5):
			Log.d("Hits All Info ", "creating hit 5");
			hit5 = new AIPack(context, 31);
			break;
		case (6):
			Log.d("Hits All Info ", "creating hit 6");

			hit6 = new MineField(context);
			break;
		case (7):
			Log.d("Hits All Info ", "creating hit 7");
			hit7 = new Hit7(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (8):
			Log.d("Hits All Info ", "creating hit 8");
			hit8 = new AlienCannon(context);
		case (9):
			Log.d("Hits All Info ", "creating hit 9");
			hit9 = new Hit9(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (10):
			Log.d("Hits All Info ", "creating hit 10");
			hit10 = new Hit10(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (11):
			Log.d("Hits All Info ", "creating hit 11");
			hit11 = new Hit11(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (12):
			Log.d("Hits All Info ", "creating hit 12");
			hit12 = new Hit12(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (13):
			Log.d("Hits All Info ", "creating hit 13");
			hit13 = new Hit13(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (14):

			Log.d("Hits All Info ", "creating hit 14");

			hit14 = new Hit14(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		case (15):

			Log.d("Hits All Info ", "creating hit 15");

			hit15 = new Hit15(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.icelarge), MGP.dp[15],
					MGP.dp[30], MGP.deviceWidth, MGP.deviceHeight);
			break;
		}
		flyingMessage.activate(1, (int) MGP.dp[3], hitsInGame.get(hit).name
				+ " " + hitsInGame.get(hit).message);

		currentlyActivatedHit = hit;
	}

	public void MoveHits(Player ship) {

		flyingMessage.move();

		if (currentlyActivatedHit != -1)
			switch (hitsInGame.get(currentlyActivatedHit).hitType) {

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
				hit3.move(ship);
				break;
			case (4):
				hit4.move(ship);
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
		for (int k = 0; k < hitsInGame.size(); k++) {
			canvas.drawText(
					"hit " + hitsInGame.get(k).hitType + " "
							+ hitsInGame.get(k).name + " activation Wave "
							+ hitsInGame.get(k).activationWave + "failed? "
							+ hitsInGame.get(k).failed + "succeeded? "
							+ hitsInGame.get(k).succeeded, 0,
					(k + 1) * 10 + 10, Paints.orange);
		}

		canvas.drawText(hitsInGame.size() + "", 0, 10, Paints.textPaint);

		if (currentlyActivatedHit != -1)
			switch (hitsInGame.get(currentlyActivatedHit).hitType) {

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

	// public void checkHitFailure() {
	//
	// switch (hitsInGame.get(currentlyActivatedHit).hitType) {
	//
	// case (0):
	// if (hit0.failed) {
	// hitsInfo[0].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (1):
	// if (hit1.failed) {
	// hitsInfo[1].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (2):
	// if (hit2.failed) {
	// hitsInfo[2].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (3):
	// if (hit3.failed) {
	// hitsInfo[3].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (4):
	// if (hit4.failed) {
	// hitsInfo[4].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (5):
	// if (hit5.failed) {
	// hitsInfo[5].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (6):
	// if (hit6.failed) {
	// hitsInfo[6].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (7):
	// if (hit7.failed) {
	// hitsInfo[7].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (8):
	// if (hit8.failed) {
	// hitsInfo[8].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (9):
	// if (hit9.failed) {
	// hitsInfo[9].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (10):
	// if (hit10.failed) {
	// hitsInfo[10].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (11):
	// if (hit11.failed) {
	// hitsInfo[11].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (12):
	// if (hit12.failed) {
	// hitsInfo[12].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (13):
	// if (hit13.failed) {
	// hitsInfo[13].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (14):
	// if (hit14.failed) {
	// hitsInfo[14].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	// case (15):
	// if (hit15.failed) {
	// hitsInfo[15].failed = true;
	// currentlyActivatedHit = -1;
	// }
	// break;
	//
	// default:
	// Log.d("Hits All Info ", "no hit currently in game");
	// break;
	//
	// }
	//
	// }

}