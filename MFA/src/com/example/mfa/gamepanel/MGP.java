
package com.example.mfa.gamepanel;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.HitsObjects.FollowAI;
import com.example.HitsObjects.HitGiantBoss;
import com.example.activities.Game;
import com.example.mfa.R;
import com.example.objects.AnalogStick;
import com.example.objects.Asteroid;
import com.example.objects.Explosion;
import com.example.objects.FlyingMessage;
import com.example.objects.InteractiveSong;
import com.example.objects.LightBackground;
import com.example.objects.Paints;
import com.example.objects.Player;
import com.example.objects.PowerUpsAll;
import com.example.objects.SoundEffectsManager;
import com.example.objects.Thrusters;
import com.example.objects.TouchButton;

public class MGP extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG = MGP.class.getSimpleName();

	private MainThread thread;
	//private LifeBar bar;
	private TouchButton shootButton;
	private FlyingMessage message;
	private Explosion explo1, explo2, explo3;
	private Thrusters shipThrusters;
	private InteractiveSong gameSoundtrack;
	private SoundEffectsManager soundEffects;
	private Player ship;
	private LightBackground ltBackground;
	private AnalogStick analog;
	private PowerUpsAll powerUps;
	HitGiantBoss bossHit;
   
	public static double[] dp; // DP units

	DisplayMetrics metrics = this.getResources().getDisplayMetrics();
	int dh = metrics.heightPixels;
	int dw = metrics.widthPixels;
	public static int deviceWidth;
	public static int deviceHeight;
	public RectF screenShape;
	public int currentHit = -1;
    public int gameOverCounter = 500;
	public boolean gameOver;
    
	boolean pause;
	private boolean debugInfo = false;
	boolean createBitmap = false;
	private int pointerIndex;
	boolean pointerAnalog1 = false, pointerAnalog2 = false;
	boolean pointerShoot1 = false, pointerShoot2 = false;
	Bitmap dock, myBitmap = null;

	public int optionsChoice = 0;
	//public static int life;
	//private double initialLife;
	private Thread splashThread;

	// what wave the game is currently on
	public static int wave = 0;

	public float playBackRate = 1;
    public FollowAI ai;
	
	// for random numbers
	Random Generator = new Random();
	int r;

	// 0 = game just begun , 1 = wave ongoing , 2 = between waves, 3 = paused, 4
	// = gameOver, 5= game has not yet started,6= close game
	public static int state = 0;

	// the time that counts down when the waves finish before starting a new
	// wave
	public static int waveDelay = 400;

	public static int lightColor = 1,ec1,ec2,ec3,ec4;

	// the amount of asteroids that must pass before the wave finishes
	// using this as opposed to asteroids killed allows for variable amounts of
	// points
	// each wave
	public static float waited;
	public static int asteroidPassLimit, countShots;
	public static int asteroidsPassed = 0, totalSpeed = -1, astKilledThisWave,
			totalAsteroidsKilled;
	public static int score; // the points the player has received;
	int backBonusCount = 0;
	int astDiff;

    public static Paint textPaint;

	// the array of asteroids
	Asteroid[] asteroids = new Asteroid[18];

	// Alien[] enemies = new Alien[10];

	// the number that is solely responsible for
	int asteroidsUnlocked = 3;

	public static Paints paints;
	
	// the fps to be displayed
	private String avgFps;

	// exp cycle for switching through the different 3 explosions objects
	int expCycle = 1;
	boolean startMusic = false, stopMusic = false, stopKick = false,
			musicPlaying;
	public int laserST = -1, playerHitST = -1, bonusWavePassLimit = 10;
	boolean bonusWaveStart = false, bonusWaveOngoing = false;

	public MGP(Context context) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		lightColor = Game.lightColor;
		
		pause = false;

		gameTimer();

		
		dp = new double[1000];

		for (int k = 0; k < dp.length; k++) {
			dp[k] = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, k,
					Game.dm);
		}

		deviceWidth = dw;
		deviceHeight = dh;

		 ai = new FollowAI(BitmapFactory.decodeResource(getResources(),
					R.drawable.littleship),BitmapFactory.decodeResource(getResources(),
							R.drawable.laser));
		 
		shootButton = new TouchButton(MGP.deviceWidth - MGP.dp[70],
				MGP.deviceHeight - MGP.dp[70], MGP.dp[60], true);

		ship = new Player(BitmapFactory.decodeResource(getResources(),
				R.drawable.f162), BitmapFactory.decodeResource(getResources(),
				R.drawable.laser), 500, 200);
		analog = new AnalogStick((int) dp[115], (int) dp[75]);
		powerUps = new PowerUpsAll(BitmapFactory.decodeResource(getResources(),
				R.drawable.bomb), BitmapFactory.decodeResource(getResources(),
				R.drawable.slowmotion), BitmapFactory.decodeResource(
				getResources(), R.drawable.sinewave),
				BitmapFactory.decodeResource(getResources(),
						R.drawable.shootfaster));
		message = new FlyingMessage();
		screenShape = new RectF(0, 0, deviceWidth, deviceHeight);
		gameSoundtrack = new InteractiveSong(this.getContext());
		soundEffects = new SoundEffectsManager(this.getContext());
		explo1 = new Explosion();
		explo2 = new Explosion();
		explo3 = new Explosion();
		shipThrusters = new Thrusters();
		ltBackground = new LightBackground();
	//	bar = new LifeBar();
		// initialize asteroids
		for (int i = 0; i < asteroids.length; i++) {
			if (i < 3)
				asteroids[i] = new Asteroid(BitmapFactory.decodeResource(
						getResources(), R.drawable.a1), MGP.dp[1] / 2, dp[4],
						deviceWidth, deviceHeight);
			else if (i < 6)
				asteroids[i] = new Asteroid(BitmapFactory.decodeResource(
						getResources(), R.drawable.a5), MGP.dp[1] / 2, dp[4],
						deviceWidth, deviceHeight);
			else if (i < 9)
				asteroids[i] = new Asteroid(BitmapFactory.decodeResource(
						getResources(), R.drawable.a3), MGP.dp[1] / 2, dp[4],
						deviceWidth, deviceHeight);
			else if (i < 12)
				asteroids[i] = new Asteroid(BitmapFactory.decodeResource(
						getResources(), R.drawable.a6), MGP.dp[1] / 2, dp[4],
						deviceWidth, deviceHeight);
			else if (i < 15)
				asteroids[i] = new Asteroid(BitmapFactory.decodeResource(
						getResources(), R.drawable.a4), MGP.dp[1] / 2, dp[4],
						deviceWidth, deviceHeight);
			else if (i < 18)
				asteroids[i] = new Asteroid(BitmapFactory.decodeResource(
						getResources(), R.drawable.a2), MGP.dp[1] / 2, dp[4],
						deviceWidth, deviceHeight);
		}

		// the bar image
		dock = BitmapFactory.decodeResource(getResources(), R.drawable.bar);

		// create Paints
		textPaint = new Paint(); 
		textPaint.setARGB(150, 255, 0, 0);
		textPaint.setTextSize(11); 
		textPaint.setAntiAlias(true); // red paint
		
		paints = new Paints();
		
		//paints.initalizeCustomColors(Game.lightColor, Game.ec1, Game.ec2, Game.ec3, Game.ec4);
		
		paints.initalizeCustomColors(Game.lightColor, Game.ec1, Game.ec2, Game.ec3, Game.ec4, Game.ec5, Game.ec6,Game.thrusterColor,Game.txtc);

		// 0 white
		// 1 black
		// 2 red
		// 3 orange
		// 4 yellow
		// 5 lightGreen
		// 6 darkGreen
		// 7 lightBlue
		// 8 darkBlue
		// 9 purple 
		// 10 pink
		
		
		//life = 0;
		//initialLife = life;

		ResetGame();

		// create the game loop thread
		thread = new MainThread(getHolder(), this);

		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		Log.d(TAG, "set Focusable...");
		// buildDrawingCache (true);
	}

	// Implement the OnClickListener callback
	public void onClick(View v) {
		// do something when the button is clicked
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		int action = event.getAction() & MotionEvent.ACTION_MASK;

		switch (action) {
		case (MotionEvent.ACTION_DOWN):

			if (analog.fullPad.contains(event.getX(), event.getY()) && Player.life > 0) {
				analog.SetActiveLocation(event.getX(), event.getY());
				pointerAnalog1 = true;
			} else if (shootButton.touchLocation.contains(event.getX(),
					event.getY())) {
				if (Player.life > 0 && bonusWaveOngoing == false) {
					countShots++;
					if (ship.shooting == true)
						ship.shooting = false;
					else
						ship.shooting = true;

					pointerShoot1 = true;
				}
			} else if (powerUps.nuke.shape.contains(event.getX(), event.getY())) {
				collision(4, (int) event.getX(), (int) event.getY());
			}

			if (bonusWaveOngoing) {

				for (int k = 0; k < asteroids.length; k++) {

					if (asteroids[k].touchCollision((int) event.getX(),
							(int) event.getY())) {
						backBonusCount++;
						asteroids[k].moveBackBonus();
						if (backBonusCount > 25)
							asteroids[k].unlocked = false;

						collision(2, (int) event.getX(), (int) event.getY());
					} else {
						collision(0, (int) event.getX(), (int) event.getY());
					}
				}
			}

			break;

		case (MotionEvent.ACTION_POINTER_DOWN):

			pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;

			if (powerUps.nuke.shape.contains(event.getX(pointerIndex), event.getY(pointerIndex))) 
				collision(4, (int) event.getX(), (int) event.getY());
			
			if (!pointerShoot1) {
				if (shootButton.touchLocation.contains(
						event.getX(pointerIndex), event.getY(pointerIndex))) {
					pointerShoot2 = true;
					countShots++;
					if (Player.life > 0 && bonusWaveOngoing == false) {
						if (ship.shooting == true) {
							ship.shooting = false;
						} else {
							ship.shooting = true;
						}
					}
				}
			}
			if (!pointerAnalog1) {
				if (analog.fullPad.contains(event.getX(pointerIndex),
						event.getY(pointerIndex))
						&& Player.life > 0) {
					analog.SetActiveLocation(event.getX(pointerIndex),
							event.getY(pointerIndex));
					pointerAnalog2 = true;
				}
			}
			break;

		case MotionEvent.ACTION_MOVE: {
			if (pointerAnalog1) {
				if (analog.fullPad.contains(event.getX(), event.getY())
						&& Player.life > 0) {
					analog.SetActiveLocation(event.getX(), event.getY());
				}
			}

			if (pointerAnalog2) {
				if (analog.fullPad.contains(event.getX(pointerIndex),
						event.getY(pointerIndex))
						&& Player.life > 0) {
					analog.SetActiveLocation(event.getX(pointerIndex),
							event.getY(pointerIndex));
				}
			}
			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {
			if (pointerShoot2) {
				ship.shooting = false;
				pointerShoot2 = false;
			}

			if (pointerAnalog2) {
				analog.SetActiveLocation();
				pointerAnalog2 = false;
			}
			break;
		}
		case MotionEvent.ACTION_UP: {
			if (pointerAnalog1) {
				// defaults it back to the center
				analog.SetActiveLocation();
				pointerAnalog1 = false;
			}
			if (pointerShoot1) {
				ship.shooting = false;
				pointerShoot1 = false;
			}
			break;
		}
		}
		return true;
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}

	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		// Log.d(TAG, "rendering");

		ltBackground.draw(canvas);

		 Game.hitsAllInfo.DrawMessage(canvas);

		// draw the asteroids
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].draw(canvas);
		}
		ship.drawShots(canvas);

		canvas.drawBitmap(dock, 0, deviceHeight - 35, null);

		textPaint.setTextSize((float) MGP.dp[20]);
		canvas.drawText("score: " + score, (float) MGP.dp[140],
				(float) (deviceHeight - MGP.dp[5]), textPaint);

		textPaint.setTextSize(10.0f);
		powerUps.draw(canvas);

		if (state != 4) {
			if (!bonusWaveOngoing) {
				shootButton.draw(canvas);
				analog.draw(canvas);
			}
			shipThrusters.Draw(canvas);
			ship.draw(canvas);
		}

	   // Game.hitsAllInfo.DrawHits(canvas);
       // ai.draw(canvas);
        
		explo1.draw(canvas);
		// canvas.drawText("1" , explo1.startX, explo1.startY, redPaint);
		explo2.draw(canvas);
		// canvas.drawText("2", explo2.startX, explo2.startY, redPaint);
		explo3.draw(canvas);
		// canvas.drawText("3", explo3.startX, explo3.startY, redPaint);

		message.draw(canvas);

	    Game.hitsAllInfo.drawHitInfo(canvas);

	//	bar.draw(canvas);

		// display fps
		displayFps(canvas, avgFps);
	}

	/**
	 * This is the game update method. It iterates through all the objects and
	 * calls their update method if they have one or calls specific engine's
	 * update method.
	 */
	public void update() {
		if (!pause) {
			// Log.d(TAG, "updating");
			updateGameState();
			updateObjects();
			updateAudio();
			//updateHits();
			//updateAI();
		}

	}

     public void updateAI(){
	     
    	 ai.move(ship.x,ship.y);
	     
	    for (int i = 0; i < ai.numShots; i++) {
			if (ship.shotCollision(ai.shots[i])) {
				collision(1, ship.cx, ship.cy);
				;
			}
		}
	    
	    for (int j = 0; j < ship.numUserShots; j++) {
			if (ai.failed == false
					&&ai.shotCollision(ship.shots[j])) {
				ship.deleteShot(j);
				j = ship.numUserShots;// break out of inner loop - it has
				ai.failed = true;
				collision(0,ai.cx, ai.cy);
			}
		}
	    
     if(ai.checkCollisionFront(ship))
    	 if(ai.checkForNewShots())
    		 soundEffects.laserST = 0;
     }
     
     
	public void updateAudio() {
		// Log.d(TAG, "starting effects ");
		soundEffects.playSounds(Player.life);

		if (startMusic) {
			Log.d(TAG, "starting track " + wave);
			gameSoundtrack.startMusic();
			startMusic = false;
			musicPlaying = true;
		}

		if (stopMusic) {
			Log.d(TAG, "stopping music");
			gameSoundtrack.endWave();
			stopMusic = false;
			musicPlaying = false;
		}

		if (stopKick) {
			stopKick = false;
			Log.d(TAG, "stopping Kick");
			gameSoundtrack.startWave();
		}
	}

	public void stopAudio() {

		gameSoundtrack.stopAudio();

	}

	public void updateObjects() {
		if (!bonusWaveOngoing)
			ltBackground.updateLights();

		// moves the user ship
		ship.move(analog.xDiff, analog.yDiff);

		// make the explosions explode
		explo1.updateExplosions();
		explo2.updateExplosions();
		explo3.updateExplosions();
		shipThrusters.Update(ship.cx, ship.cy);

		// if game is ongoing then run the asteroids
		updateAsteroids();
		updateShots();
		powerUps.Update(ship);

		message.move();

		if (powerUps.shootFaster.shipCollision(ship)) {
			powerUps.shootFaster.activate();
			ship.shotDelay = 6;
		}

		if (powerUps.spreadShot.shipCollision(ship))
			powerUps.spreadShot.activate();

		if (powerUps.slowMo.shipCollision(ship)) {
			powerUps.slowMo.activate();
			MainThread.MAX_FPS = 24;
			MainThread.FRAME_PERIOD = 1000 / MainThread.MAX_FPS;
			slowMoAudio();
		}

		if (powerUps.shootFaster.active == false)
			ship.shotDelay = 12;

		if (powerUps.slowMo.active == false) {
			MainThread.MAX_FPS = 50;
			MainThread.FRAME_PERIOD = 1000 / MainThread.MAX_FPS;
			normalAudio();
		}

		// if(powerUps.shootMassive.shipCollision(ship))
		// if(powerUps.spreadShot.shipCollision(ship))
	}

	private void updateShots() {
		// set the trigger to play the laser to 0
		if (ship.checkForNewShots())
			soundEffects.laserST = 0;

		if (powerUps.spreadShot.active)
			ship.moveShotsSin();
		else
			ship.moveShots();

	}

	private void collision(int type, int x, int y) {
		triggerExplosions(x, y);

		switch (type) {
		case (1):
			soundEffects.playerHitST = 0;

			if (Player.life > 0) 
				Player.life--;
				
			ship.setLifeBarSize();
			
			break;
		case (2):
			totalAsteroidsKilled++;
			astKilledThisWave++;
			asteroidsPassed++;
			score += 10;
			break;
		case (3):

			break;
		case (4):
			explo1.triggerExplosionsNuke(x, y);
			explo2.triggerExplosionsNuke(x, y);
			explo3.triggerExplosionsNuke(x, y);
			soundEffects.nukeST = 0;
			for (int i = 0; i < asteroids.length; i++) {
				if (screenShape.contains(asteroids[i].x, asteroids[i].y)) {
					asteroids[i].moveBack();
					score += 10;
					astKilledThisWave += 1;
					totalAsteroidsKilled += 1;
				} else
					asteroids[i].moveBack();
			}
			powerUps.nuke.unlocked = false;
			powerUps.nuke.x = MGP.deviceWidth * 5 + MGP.deviceWidth;

			break;

		default:

			break;

		}
		if (1 != type && type != 4)
			soundEffects.expST = 0;
	}

	public void triggerExplosions(int x, int y) {
		switch (expCycle) {
		case (1):
			explo1.triggerExplosions(x, y);
			break;
		case (2):
			explo2.triggerExplosions(x, y);
			break;
		case (3):
			explo3.triggerExplosions(x, y);
			break;
		}
		expCycle += 1;
		if (expCycle == 4)
			expCycle = 1;
	}

	private void updateAsteroids() {
		
		for (int i = 0; i < asteroids.length; i++) {
			// move each asteroid
			if (bonusWaveOngoing)
				asteroids[i].moveBonus();
			else if (!bonusWaveOngoing)
				asteroids[i].move();

			// check for collisions with the ship
			if (asteroids[i].shipCollision(ship) && Player.life > 0) {
				if (bonusWaveOngoing) {
					backBonusCount++;
					asteroids[i].moveBackBonus();
					collision(1, ship.cx, ship.cy);
				} else {
					asteroids[i].moveBack();
					collision(1, ship.cx, ship.cy);
				}
			}
			// check for collision with the user shots.
			for (int j = 0; j < ship.numUserShots; j++) {
				if (asteroids[i].shotCollision(ship.shots[j])) {
					collision(2, asteroids[i].cx, asteroids[i].cy);
					ship.deleteShot(j);
					// delete the original asteroid
					asteroids[i].moveBack();
					j = ship.numUserShots;// break out of inner loop - it has
					// already been hit, don't need to check
					// for collision with other shots
				}
			}
		}
	}

	public void updateGameState() {
		// this will be in charge of handling the games current state
		// waves mainly and whether to end or start a wave

		// state is to be used for the state the game is in
		// 0 is paused, 1 is ongoing , 2 between, 3 is game over

		// if the game is in between waves, and there is still wave delay left
		if ((state == 2 && waveDelay >= 0) || state == 0 && waveDelay >= 0)
			waveDelay -= 1;

		if (MGP.state == 0 && MGP.waveDelay == 395) {
			message.activate(1, 10, "Ready     Set     Go");
		}

		if (MGP.state == 2 && MGP.waveDelay == 395) {
			if (bonusWaveStart)
				message.activate(1, 10, "Bonus wave will begin");
			else
				message.activate(1, 10, "Wave " + MGP.wave + " Completed");
		}

		// game over if the ship is dead
		if (Player.life == 0) {
			gameOver=true;
			state = 4;
			MainThread.MAX_FPS = 20;
			MainThread.FRAME_PERIOD = 1000 / MainThread.MAX_FPS;
			slowMoAudio();
			
			if(Game.hitsAllInfo.currentlyActivatedHit!=-1){
				Game.hitsAllInfo.hitsInfo[Game.hitsAllInfo.currentlyActivatedHit].succeeded=true;				
			}
			
		}
		
		if(gameOver)
		gameOverCounter--;
		
		
		
		if(gameOver&&gameOverCounter<0){
		   ((Game) Game.activity).gameOver();
		   ((Activity) getContext()).finish();
		   closeGame();
		}
		   
		
		

		// if the player died and the game is over then stop the music
		if (state == 4 && stopMusic == false && musicPlaying) {
			stopMusic = true;
		}

		// this starts a new wave after a set amount of time by unlocking
		// asteroids
		// and changing the state to 1, and adding one to wave
		if (waveDelay == 0) {
			if (bonusWaveStart)
				startBonusWave();
			else
				startWave();
		}

		if (astKilledThisWave == 1 && musicPlaying == false) {
			Log.d(TAG, "triggering music");
			startMusic = true;
		}

		// this checks to see if the limit of asteroids passing has been reached
		// if it has it will call end wave to lock all asteroids after they exit
		// the screen and change the game state to in between waves which is
		// state 2
		if (asteroidsPassed >= asteroidPassLimit && state == 1
				&& !bonusWaveStart && bonusWaveOngoing == false) {
			endWave();
		}

		if (backBonusCount == 30 && bonusWaveOngoing == true) {
			endWave();
			bonusWaveOngoing = false;
			ship.enter = true;
		}
	}

	public void startBonusWave() {
		Log.d(TAG, "starting bonus wave");

		bonusWaveOngoing = true;
		asteroidsUnlocked = 5;
		backBonusCount = 0;

		for (int k = 0; k < asteroidsUnlocked; k++) {
			asteroids[k].moveBackBonus();
			asteroids[k].unlocked = true;
		}

		ship.exit = true;
		ship.unlocked = false;
		powerUps.nuke.unlocked = false;
		powerUps.shootFaster.unlocked = false;
		powerUps.spreadShot.unlocked = false;
		powerUps.slowMo.unlocked = false;
		bonusWaveStart = false;
		ship.shooting = false;
	}

	public void startWave() {
		Log.d(TAG, "starting wave");

		for (int k = 0; k < asteroidsUnlocked; k++) {
			asteroids[k].unlocked = true;
		}

		  if(wave>0&&wave<4)
		     {
			   totalSpeed += 2; 
		     }
		     else if(wave>4)
		     {
			   totalSpeed += 1; 
		     }
		     
		// change game state to wave ongoing
		state = 1;

		// add one to wave
		wave++;

	    Game.hitsAllInfo.checkStartHit(this.getContext());

		if (wave < 10) {
			gameSoundtrack.setIntensity(wave, this.getContext());
		} else if (wave > 9) {
			gameSoundtrack.setIntensity(9, this.getContext());
		}

		waveDelay -= 1;

		stopKick = true;
		powerUps.nuke.unlock();
		powerUps.shootFaster.unlock();
		powerUps.spreadShot.unlock();
		powerUps.slowMo.unlock();
		astKilledThisWave = 0;
	}

	public void endWave() {
		Log.d(TAG, "ending wave");

//		// check if the user killed all asteroids in the current wave
//		if (astKilledThisWave == asteroidPassLimit)
//			bonusWaveStart = true;

		for (int k = 0; k < asteroids.length; k++) {
			asteroids[k].unlocked = false;
		}

		// increase number of asteroids to pass next wave
	if(wave<5)
		asteroidPassLimit += 10;
	else
		asteroidPassLimit += 7;
		
		// reset the amount of asteroids that have passed
		asteroidsPassed = 0;

	     if(wave<4)
	         asteroidsUnlocked+=2;
	      else
	    	 asteroidsUnlocked+=1;
	     
	      if(asteroidsUnlocked>=asteroids.length)
	    	  asteroidsUnlocked=asteroids.length-1;
	      
		stopMusic = true;

		// change state to in between
		state = 2;

		// increase the wave delay to allow time between the waves
		waveDelay = 400;

		powerUps.nuke.unlocked = false;
		powerUps.shootFaster.unlocked = false;
		powerUps.spreadShot.unlocked = false;
		powerUps.slowMo.unlocked = false;
	}

	public void setPause(boolean p) {
		pause = p;
	}

	public boolean getPause() {
		return pause;
	}

	public void ResetGame() {
		waited = 0;
		countShots = 0;
		stopMusic = true;
		stopKick = true;
		startMusic = false;
		totalAsteroidsKilled = 0;
		asteroidsUnlocked = 3;
		astKilledThisWave = 0;
		totalSpeed = -1;
		asteroidPassLimit = 10;
		wave = 0;
		state = 0;
		waveDelay = 400;
		asteroidsPassed = 0;
		score = 0;
		Player.life = 3;
		ship.x = 100;
		ship.y = deviceHeight / 2;
		for (int k = 0; k < asteroids.length; k++) {
			asteroids[k].moveBack();
			asteroids[k].unlocked = false;
		}
		powerUps.nuke.unlocked = false;
		powerUps.shootFaster.unlocked = false;
		powerUps.spreadShot.unlocked = false;
		powerUps.slowMo.unlocked = false;
	}

	public void gameTimer() {
		// the timer of the game(runs on a separate thread)
		splashThread = new Thread() {
			@Override
			public void run() {
				try {
					waited = 0;
					while (Player.life > 0) {
						sleep(100);
						if (!pause)
							waited += .1;
					}
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		};
		splashThread.start();
	}

	public void normalAudio() {
		soundEffects.playBackRate = 1;
	}

	public void slowMoAudio() {
		soundEffects.playBackRate = (float) 0.7;
	}

	public void setAvgFps(String avgFps) {
		this.avgFps = avgFps;
	}

	public void closeGame() {
		stopAudio();
		thread.setRunning(false);
	}

	public void updateHits() {
		Game.hitsAllInfo.MoveHits(ship);
		Game.hitsAllInfo.checkHitFailure();

		switch (Game.hitsAllInfo.currentlyActivatedHit) {

		case (0):

			if (Game.hitsAllInfo.hit0.checkForNewShots()) {
				soundEffects.laserST = 1;
			}

			for (int i = 0; i < Game.hitsAllInfo.hit0.numUserShots; i++) {
				if (ship.shotCollision(Game.hitsAllInfo.hit0.shots[i])) {
					collision(1, ship.cx, ship.cy);
					Log.d("MGP", "Got to collision");
				}
			}

			break;
		case (1):
			if (Game.hitsAllInfo.hit1.shipCollision(ship) && Player.life > 0)
				collision(1, ship.cx, ship.cy);

			for (int j = 0; j < ship.numUserShots; j++) {
				if (Game.hitsAllInfo.hit1.shotCollision(ship.shots[j])) {
					Game.hitsAllInfo.hit1.wasHit();
					collision(0, (int) ship.shots[j].x, (int) ship.shots[j].y);
				}
			}
			break;
		case(4):
			
			Game.hitsAllInfo.hit4.checkForFailure();
			Game.hitsAllInfo.hit4.move(ship);

			for (int k = 0; k < Game.hitsAllInfo.hit4.aiPack.length; k++) {
				if (Game.hitsAllInfo.hit4.aiPack[k].checkForNewShots()) {
					soundEffects.laserST = 0;
				}

				for (int i = 0; i < Game.hitsAllInfo.hit4.aiPack[k].numShots; i++) {
					if (ship.shotCollision(Game.hitsAllInfo.hit4.aiPack[k].shots[i])) {
						collision(1, ship.cx, ship.cy);

						Log.d("MGP", "Got to collision");
					}
				}

				for (int j = 0; j < ship.numUserShots; j++) {
					if (Game.hitsAllInfo.hit4.aiPack[k].dead == false
							&&Game.hitsAllInfo.hit4.aiPack[k].shotCollision(ship.shots[j])) {
						ship.deleteShot(j);
						j = ship.numUserShots;// break out of inner loop - it has
						Game.hitsAllInfo.hit4.aiPack[k].dead = true;
						collision(0,Game.hitsAllInfo.hit4.aiPack[k].cx, Game.hitsAllInfo.hit4.aiPack[k].cy);
						Game.hitsAllInfo.hit4.updateFollowers();
					}
				}
			}
			
			
			
		break;
		case(5):

			Game.hitsAllInfo.hit5.checkForFailure();
			Game.hitsAllInfo.hit5.move(ship);

			for (int k = 0; k < Game.hitsAllInfo.hit5.aiPack.length; k++) {
				if (Game.hitsAllInfo.hit5.aiPack[k].checkForNewShots()) {
					soundEffects.laserST = 0;
				}

				for (int i = 0; i < Game.hitsAllInfo.hit5.aiPack[k].numShots; i++) {
					if (ship.shotCollision(Game.hitsAllInfo.hit5.aiPack[k].shots[i])) {
						collision(1, ship.cx, ship.cy);

						Log.d("MGP", "Got to collision");
					}
				}

				for (int j = 0; j < ship.numUserShots; j++) {
					if (Game.hitsAllInfo.hit5.aiPack[k].dead == false
							&&Game.hitsAllInfo.hit5.aiPack[k].shotCollision(ship.shots[j])) {
						ship.deleteShot(j);
						j = ship.numUserShots;// break out of inner loop - it has
						Game.hitsAllInfo.hit5.aiPack[k].dead = true;
						collision(0,Game.hitsAllInfo.hit5.aiPack[k].cx, Game.hitsAllInfo.hit5.aiPack[k].cy);
						Game.hitsAllInfo.hit5.updateFollowers();
					}
				}
			}
			break;
		case(6):
			
			Game.hitsAllInfo.hit6.move();
		for (int k = 0; k < Game.hitsAllInfo.hit6.mines.length; k++) {
			if (Game.hitsAllInfo.hit6.mines[k].shipInnerCollision(ship)) {
				collision(1, ship.cx, ship.cy);
				Game.hitsAllInfo.hit6.mines[k].exploded();
			}

			if (Game.hitsAllInfo.hit6.mines[k].shipOuterCollision(ship))
				Game.hitsAllInfo.hit6.mines[k].activated = true;

			for (int j = 0; j < ship.numUserShots; j++) {
				if (Game.hitsAllInfo.hit6.mines[k].shotCollision(ship.shots[j])) {
					ship.shots[j].changeAngle();
				}
			}

			if (Game.hitsAllInfo.hit6.mines[k].activationTime < 0) {
				collision(3, Game.hitsAllInfo.hit6.mines[k].cx,Game.hitsAllInfo.hit6.mines[k].cy);

				for (int i = 0; i < asteroids.length; i++) {
					if (asteroids[i].mineCollision(Game.hitsAllInfo.hit6.mines[k]))
						collision(0, asteroids[i].cx, asteroids[i].cy);
					asteroids[i].moveBack();
				}

				if (Game.hitsAllInfo.hit6.mines[k].shipOuterCollision(ship)) {
					collision(1, ship.cx, ship.cy);
				}

				for (int j = 0; j < Game.hitsAllInfo.hit6.mines.length; j++) {
					if (j != k)
						if (Game.hitsAllInfo.hit6.mines[k]
								.mineOuterCollision(Game.hitsAllInfo.hit6.mines[j])
								&& Game.hitsAllInfo.hit6.mines[j].cx < deviceWidth)
							Game.hitsAllInfo.hit6.mines[j].activated = true;
				}
				Game.hitsAllInfo.hit6.mines[k].exploded();
			}
		}
			break;
		}
	}

	private void displayFps(Canvas canvas, String fps) {
		if (canvas != null && fps != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(fps, this.getWidth() - 50, 20, paint);
		}
	}

	public void createBitmap(Canvas canvas) {
		// myBitmap = Bitmap.createBitmap((int)deviceWidth, (int)deviceHeight,
		// Config.RGB_565);

		myBitmap = Bitmap.createBitmap(getDrawingCache());
		destroyDrawingCache();
		// canvas.setBitmap(myBitmap);

		createBitmap = false;
		Log.d("creating bitmap ", " create bitmap");
	}
}
