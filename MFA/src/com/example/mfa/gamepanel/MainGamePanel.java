/**
 * 
 */
package com.example.mfa.gamepanel;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import com.example.mfa.R;
import com.example.mfa.OldMenus_JustForReference.GameOverVisuals;
import com.example.mfa.OldMenus_JustForReference.MainGameVisuals;
import com.example.mfa.OldMenus_JustForReference.MainMenuVisuals;
import com.example.mfa.OldMenus_JustForReference.OptionsVisuals;
import com.example.objects.Alien;
import com.example.objects.AnalogStick;
import com.example.objects.Asteroid;
import com.example.objects.Explosion;
import com.example.objects.HitGiantBoss;
import com.example.objects.InteractiveSong;
import com.example.objects.LightBackground;
import com.example.objects.Player;
import com.example.objects.PowerUpsAll;
import com.example.objects.SoundEffectsManager;
import com.example.objects.Thrusters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

/**
 * @author impaler
 * This is the main surface that handles the ontouch events and draws
 * the image to the screen.
 */
public class MainGamePanel extends SurfaceView implements
		SurfaceHolder.Callback{

	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	
	
	private MainThread thread;		
//
//    public MainMenuVisuals mainMenu;
//    public OptionsVisuals options;
    public MainGameVisuals mainGame;
//    public GameOverVisuals gameOverMenu;
    
	DisplayMetrics metrics = this.getResources().getDisplayMetrics();
    int dh = metrics.heightPixels;
    int dw = metrics.widthPixels;
    public static int deviceWidth;
    public static int deviceHeight;
	public RectF screenShape;
    public int currentHit=-1;

	private boolean debugInfo=false; 
	
	boolean createBitmap=false;
	
	//this will be for the options/start/whatever menu
	//0 is main menu/1 is game start/2 is options/3 is hits/4 is exit
    public int menuState=0,previousMenuState,previousGameState;
	
    Bitmap myBitmap =null;
    
    public int optionsChoice=0;

    //images of life bar
    private Bitmap[] lifeBar = new Bitmap[4];
    public static int life;



public int barY;
    
    private Bitmap bar;

    //what wave the game is currently on
    public static int wave = 0;
    
    public float playBackRate=1;
    
    //for random numbers
    Random Generator = new Random();
    int r;
    
    PowerUpsAll powerUps;
    
    private Explosion explo1,explo2,explo3;
    private Thrusters shipThrusters;
    
    //0 = game just begun ,  1 = wave ongoing , 2 = between waves, 3 = paused, 4 = gameOver, 5= game has not yet started,6= close game
    public static int state =5;
    
    //the time that counts down when the waves finish before starting a new wave
    public static int waveDelay = 600;
    
    public static int lightColor=1,explodeColor=1;
    
    //the amount of asteroids that must pass before the wave finishes
    //using this as opposed to asteroids killed allows for variable amounts of points
    //each wave
    public static int asteroidPassLimit=5; 
    
    public static int asteroidsPassed = 0,totalSpeed=-1,astKilledThisWave,totalAsteroidsKilled;
    public static int  totalAliensKilled=0,aliensKilledThisWave=0;
   
    HitGiantBoss bossHit;
    
    //the points the player has recieved;
    public static int score = 0; 
    
    public InteractiveSong gameSoundtrack;
    public SoundEffectsManager soundEffects;
    
    // to draw anything in android you need to create a color
    //this is a transparent red, and a white
    //I am going to use t so signify transparency in the color
    public static Paint textPaint,WPaint,redPaint,bluePaint,greenPaint,yellowPaint,greyPaint,blackPaint,orangePaint,purplePaint,pinkPaint,s1,s2,blueT,redT,orangeT,yellowT;

    //the player
    Player ship = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.ship),BitmapFactory.decodeResource(getResources(), R.drawable.laser),-200,-200);
    
    //the player
//    Player ship = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.bs65),
//    						BitmapFactory.decodeResource(getResources(), R.drawable.gps65),
//    						BitmapFactory.decodeResource(getResources(),R.drawable.grs65),
//    						BitmapFactory.decodeResource(getResources(),R.drawable.ship),
//    						BitmapFactory.decodeResource(getResources(), R.drawable.ps),
//    						BitmapFactory.decodeResource(getResources(), R.drawable.laser),100,100);
   
   
    //the array of asteroids
    Asteroid[] asteroids = new Asteroid[15];
    
    Alien[] enemies = new Alien[10];

    //the number that is solely responsible for
    int asteroidsUnlocked=2,enemiesUnlocked=0;

    //lights
    LightBackground ltBackground;
    
    // the fps to be displayed
	private String avgFps;
	

	//exp cycle for switching through the different 3 explosions objects
    int expCycle=1;
    boolean startMusic=false, stopMusic=false,stopKick=false,musicPlaying;
	public int laserST=-1,playerHitST=-1,bonusWavePassLimit;

	boolean bonusWaveStart=false,bonusWaveOngoing=false;
    
    AnalogStick analog;

	public MainGamePanel(Context context) 
	{
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		
		
          deviceWidth  = dw;
          deviceHeight = dh;
		
          screenShape= new RectF(0,0,deviceWidth,deviceHeight);
          
          analog = new AnalogStick(115,75);
//          mainMenu = new MainMenuVisuals(BitmapFactory.decodeResource(getResources(), R.drawable.logo), 
//        		  BitmapFactory.decodeResource(getResources(), R.drawable.startgamebtn),
//        		  BitmapFactory.decodeResource(getResources(), R.drawable.optionsbtn),
//        		  BitmapFactory.decodeResource(getResources(), R.drawable.hitsbtn),
//        		  BitmapFactory.decodeResource(getResources(), R.drawable.exitbtn));
//          
//
//          options = new OptionsVisuals(BitmapFactory.decodeResource(getResources(), R.drawable.ast));
          mainGame = new MainGameVisuals(BitmapFactory.decodeResource(getResources(), R.drawable.pause),BitmapFactory.decodeResource(getResources(), R.drawable.pause),BitmapFactory.decodeResource(getResources(), R.drawable.pause));
//          gameOverMenu = new GameOverVisuals(BitmapFactory.decodeResource(getResources(), R.drawable.ast));
          powerUps= new PowerUpsAll(BitmapFactory.decodeResource(getResources(), R.drawable.bomb));
          
          for(int j=0;j<enemies.length;j++)  
              enemies[j] = new Alien(BitmapFactory.decodeResource(getResources(), R.drawable.alien),BitmapFactory.decodeResource(getResources(), 
              		R.drawable.fireball),deviceWidth*2,deviceHeight/2,30,70);


	         gameSoundtrack = new InteractiveSong(this.getContext());
	        
	         soundEffects = new SoundEffectsManager(this.getContext());
	
	
	         //initialize asteroids
	          for(int i=0;i<asteroids.length;i++) 
	           asteroids[i] = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.ast), 40, .5, 4,deviceWidth,deviceHeight);
	         
	          explo1 = new Explosion();
	          explo2 = new Explosion();
	          explo3 = new Explosion();
	          shipThrusters = new Thrusters();
	          
	          ltBackground = new LightBackground(); 
	    
	          //create red paint  
			    textPaint = new Paint(); textPaint.setARGB (150, 255,0 , 0); textPaint.setTextSize(11);textPaint.setAntiAlias(true);

			    //create white paint
			    WPaint = new Paint(); WPaint.setARGB (255, 255,255 , 255); WPaint.setTextSize(11);
			    
			    redPaint = new Paint();redPaint.setARGB (255, 255,0,0); redPaint.setTextSize(11);redPaint.setAntiAlias(true);
			    bluePaint = new Paint(); bluePaint.setARGB (255, 0,0 , 255);bluePaint.setTextSize(11);bluePaint.setAntiAlias(true);
			    greenPaint = new Paint();greenPaint.setARGB (255, 34,139 , 34);greenPaint.setTextSize(11);greenPaint.setAntiAlias(true);
			    greyPaint = new Paint(); greyPaint.setARGB (255, 139,137 , 137);greyPaint.setTextSize(11);greyPaint.setAntiAlias(true);
			    blackPaint = new Paint();blackPaint.setARGB (255, 0,0 , 0); blackPaint.setTextSize(11);blackPaint.setAntiAlias(true);
			    yellowPaint = new Paint();yellowPaint.setARGB (255, 255,255 , 0); yellowPaint.setTextSize(11);yellowPaint.setAntiAlias(true);
			    orangePaint = new Paint(); orangePaint.setARGB (255, 255,140 , 0);orangePaint.setTextSize(11);orangePaint.setAntiAlias(true);
			    
			    yellowT = new Paint();yellowT.setARGB (100, 255,255 , 0); yellowT.setTextSize(11);yellowT.setAntiAlias(true);
			    blueT = new Paint(); blueT.setARGB (100, 0,0 , 255);blueT.setTextSize(11);blueT.setAntiAlias(true);
			    orangeT = new Paint(); orangeT.setARGB (100, 255,140 , 0);orangeT.setTextSize(11);orangeT.setAntiAlias(true);
			    redT = new Paint();redT.setARGB (100, 255,0,0); redT.setTextSize(11);redT.setAntiAlias(true);
			    purplePaint= new Paint();purplePaint.setARGB (255, 51,0 , 102); purplePaint.setTextSize(11);purplePaint.setAntiAlias(true);
			    pinkPaint = new Paint();pinkPaint.setARGB (255, 224,0 , 224); pinkPaint.setTextSize(11);pinkPaint.setAntiAlias(true);
		        s1 = new Paint();s1.setARGB (90, 224,224 , 224); s1.setTextSize(11);s1.setAntiAlias(true);
		        s2 = new Paint();s2.setARGB (255, 143,143 , 143); s2.setTextSize(11);s2.setAntiAlias(true);
		    //user shots
		    //userShots=new Shot[40];

		    barY= -30;
            
            bar = BitmapFactory.decodeResource(getResources(), R.drawable.bar2);
            
            lifeBar[0] = BitmapFactory.decodeResource(getResources(), R.drawable.life1);
            lifeBar[1] = BitmapFactory.decodeResource(getResources(), R.drawable.life2);
            lifeBar[2] = BitmapFactory.decodeResource(getResources(), R.drawable.life3);
            lifeBar[3] = BitmapFactory.decodeResource(getResources(), R.drawable.life4);

            life = 3;
  
		// create the game loop thread
		thread = new MainThread(getHolder(), this);
		
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		Log.d(TAG, "set Focusable...");
		//buildDrawingCache (true);
	}

	
	  // Implement the OnClickListener callback
    public void onClick(View v) {
      // do something when the button is clicked
    }
 

	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		super.onTouchEvent(event);
		
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		
		switch (action) 
        {
	      case (MotionEvent.ACTION_DOWN): 
				if(mainGame.shootButton.touchLocation.contains(event.getX(),event.getY()))
				{
					if(life>0&&bonusWaveOngoing==false){ 
			    		   if(ship.shooting==true) 
	                     	 ship.shooting=false;
	                      else 
	                     	 ship.shooting=true;
				      }
				}
	        	
				if(bonusWaveOngoing)
				{	
					for(int k = 0 ;k<asteroids.length;k++){
						
						if(asteroids[k].touchCollision((int)event.getX(),(int)event.getY())){
							asteroids[k].moveBack();
							collision(2,(int)event.getX(),(int)event.getY());
						}
						else
							collision(0,(int)event.getX(),(int)event.getY());
				    }
				}
				
				if(analog.fullPad.contains(event.getX(),event.getY()))
				{
					analog.SetActiveLocation(event.getX(),event.getY());
				}
				
		  break;
				
        case (MotionEvent.ACTION_POINTER_DOWN): 
        	
        	if(mainGame.shootButton.touchLocation.contains(event.getX(),event.getY()))
			{
				if(life>0&&bonusWaveOngoing==false){ 
		    		   if(ship.shooting==true) 
                     	 ship.shooting=false;
                      else 
                     	 ship.shooting=true;
			      }
			}   
        	
        	if(analog.fullPad.contains(event.getX(),event.getY())&&menuState==1)
			{
				analog.SetActiveLocation(event.getX(),event.getY());
			}
    		    
         break;

        case MotionEvent.ACTION_MOVE: 
        {
			if(analog.fullPad.contains(event.getX(),event.getY())&&menuState==1)
			{
				analog.SetActiveLocation(event.getX(),event.getY());
	        }
			break;		 
		}
        
        case MotionEvent.ACTION_POINTER_UP: 
        {   
			if(ship.shooting==true&&life>0&&bonusWaveOngoing==false)
                ship.shooting=false;
			break;
		}
        case MotionEvent.ACTION_UP: 
        {
                // defaults it back to the center
    			analog.SetActiveLocation();
    			if (ship.shooting == true && life > 0)
				{
				    ship.shooting = false;
				}
                break;
        }
      }
		return true;
	}
	
	
	public void surfaceCreated(SurfaceHolder holder) 
	{
		// at this point the surface is created and
		// we can safely start the game loop
		thread.setRunning(true);
		thread.start();
	}


	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) 
			{
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	

	

	public void render(Canvas canvas) 
	{
		canvas.drawColor(Color.BLACK);
		// Log.d(TAG, "rendering");
		 
		ltBackground.draw(canvas); 
		
		  //draw the asteroids
		   for(int i=0;i<asteroidsUnlocked;i++) 
	           asteroids[i].draw(canvas);   

		   ship.drawShots(canvas);
		   
		   for(int k=0;k<enemiesUnlocked;k++)
		      enemies[k].drawShots(canvas); 
     
        //draw the enemies
		for(int i=0;i<enemiesUnlocked;i++) 
	        enemies[i].draw(canvas);

		if(ship.unlocked)
			analog.draw(canvas);
			canvas.drawBitmap(bar, 0, barY, null);
			textPaint.setTextSize(25.0f);
			canvas.drawText("score is: " + score, 155, 25,textPaint );
	   
			
			drawHits(canvas);
			
			
		   if(life==3){
			   canvas.drawBitmap(lifeBar[0], 0, barY, null);
		   }else if(life==2){
			   canvas.drawBitmap(lifeBar[1], 0, barY, null);
		   } else if(life==1) {
			   canvas.drawBitmap(lifeBar[2], 0, barY, null); 
		   }
	        textPaint.setTextSize(10.0f);
            powerUps.draw(canvas);
            
            
            if(state!=4)
   		 {
   			 
   		        ship.draw(canvas);
   		        shipThrusters.Draw(canvas);
   		 }
       
            explo1.draw(canvas);
  		  // canvas.drawText("1" , explo1.startX, explo1.startY, redPaint);
  		   explo2.draw(canvas);
  		  // canvas.drawText("2", explo2.startX, explo2.startY, redPaint);
  		   explo3.draw(canvas);
  		  // canvas.drawText("3", explo3.startX, explo3.startY, redPaint);
            
  		   
  		 if(life==0)
		   {
		   canvas.drawBitmap(myBitmap,0,0,null);
		   }
			// display fps
			displayFps(canvas, avgFps);
			
//		//0 is main menu/1 is game start/2 is options/3 is hits/4 is exit
//		switch(menuState)
//		{
//		case(0):
//		mainMenu.draw(canvas,ship);
//		break;
//		case(1):
//			if(state!=4)
//			{
//              if(ship.unlocked)
//				analog.draw(canvas);
//				canvas.drawBitmap(bar, 0, barY, null);
//				textPaint.setTextSize(25.0f);
//				canvas.drawText("score is: " + score, 155, 25,textPaint );
//		   
//				
//				drawHits(canvas);
//				
//				
//			   if(life==3){
//				   canvas.drawBitmap(lifeBar[0], 0, barY, null);
//			   }else if(life==2){
//				   canvas.drawBitmap(lifeBar[1], 0, barY, null);
//			   } else if(life==1) {
//				   canvas.drawBitmap(lifeBar[2], 0, barY, null); 
//			   }
//		        mainGame.draw(canvas); 
//		        textPaint.setTextSize(10.0f);
//                powerUps.draw(canvas);
//			}
//		   else
//		   {
//			  gameOverMenu.draw(canvas); 
//		   }
//	    break;
//		case(2):
//	    options.draw(canvas);
//			break;
//		case(3):
//		  
//			break;
//		case(4):
//			
//			break;
//		}
            
		if(MainMenuVisuals.logoCount > 15)
		{	
		
		   
		 

	
		   
		  
		}
		   
	}

	/**
	 * This is the game update method. It iterates through all the objects
	 * and calls their update method if they have one or calls specific
	 * engine's update method.
	 */
	public void update() 
	{
		//Log.d(TAG, "updating");
	    updateGameState();
	    updateMenuState();
	    updateBar();
		updateObjects();
		updateAudio();
		updateHits();
    }

   public void updateAudio()
		{
	   
	         Log.d(TAG, "starting effects ");
	         soundEffects.playSounds();
	   
		     if(startMusic){
		    	 Log.d(TAG, "starting track "+wave);
		    	 gameSoundtrack.startMusic();	
		         startMusic=false; 
		         musicPlaying=true;
	       	 }
		     
		     if(stopMusic){
		    	 Log.d(TAG, "stopping music");
		        gameSoundtrack.endWave();	 
		        stopMusic=false;
		        musicPlaying=false;
		     }
		     
		     if(stopKick) {
		    	 stopKick=false;
		    	 Log.d(TAG, "stopping Kick");
		    	 gameSoundtrack.startWave(); 
		     }   
		}
	
	public void updateBar()
	{
		if(barY<0)
		   barY+=0.1;
	}
	
	public void updateObjects()
	{
		   ltBackground.updateLights();
		   
		   ship.move2(analog.xDiff,analog.yDiff);

		   //make the explosions explode
		   explo1.updateExplosions();
		   explo2.updateExplosions();
		   explo3.updateExplosions();
		   shipThrusters.Update(ship.cx, ship.cy);
		   updateEnemies();
		   
		   //if game is ongoing then run the asteroids
		   if(menuState==1){
		    updateAsteroids();
		    updateShots(); 
		   powerUps.Update(ship);
		   //updateEnemies();
		   }
		 		   
		   if(powerUps.shootFaster.shipCollision(ship)){
             powerUps.shootFaster.activate();
			   ship.shotDelay=6;}
		  
		   if(powerUps.spreadShot.shipCollision(ship))
			   powerUps.spreadShot.activate();
			   
//		   if(powerUps.slowMo.shipCollision(ship)){ 
//			   powerUps.slowMo.activate();
//		       MainThread.MAX_FPS=24;
//		       MainThread.FRAME_PERIOD = 1000 /  MainThread.MAX_FPS;
//		       slowMoAudio();
//		      }
		   
		   if(powerUps.shootFaster.active==false)   
			   ship.shotDelay=12;
		   
		   if(powerUps.slowMo.active==false){ 
              MainThread.MAX_FPS=50;
		      MainThread.FRAME_PERIOD = 1000 /  MainThread.MAX_FPS;
		      normalAudio();
		   }
		   
//		   if(powerUps.shootMassive.shipCollision(ship))
//		   if(powerUps.spreadShot.shipCollision(ship))
	 }    
	
	public void updateMenuState()
	{
		//0 is main menu/1 is game start/2 is options/3 is hits/4 is exit

          switch(menuState)
		   {
		   case(0):
			   //where the game currently is so nothing required to do
			   break;
		   case(1):
			   //start the game so we set the state to 
			   if(state==5||state==3)
				   state=previousGameState;
			   break;
		   case(2):
			   //boot up options 
			   break;
		   case(3):
			   //will be for hits menu
			   break;
		   case(4):
			   // exits the game
			   closeGame();
			   break;
		   }      
	}
	
	
//	public void updateOptionsMenu(float x, float y)
//	{
//		 //check for collision with the user shots.
//   
//          optionsChoice = options.updateOptionsCollision(x,y);     
// 
//    	//0 = nothing, 1 = explosion color, 2= light color, 3 =exit Options Menu
//        switch(optionsChoice)
//		   {
//		   case(0):
//			   
//			   break;
//		   case(1):
//			   MainGamePanel.explodeColor+=1;
//		     if(MainGamePanel.explodeColor==11)
//		    	 MainGamePanel.explodeColor=0;
//			   break;
//		   case(2):
//	            lightColor+=1;
//		     if(lightColor==8)
//		        lightColor=0;
//			   break;
//		   case(3):
//			   menuState=previousMenuState;
//			   break;
//		   case(4):
//			   ResetGame();
//			   break;
//		   case(5):
//			   closeGame();
//			   break;	
//		   case(6):
//			   ship.imgChoice+=1;
//		     if(ship.imgChoice==6)
//		    	 ship.imgChoice=1;
//			   break;
//		   }
//
//	}
//	
	
	private void updateShots()
    { 
		//move all the shots
		ship.moveShots();
		
		//set the trigger to play the laser to 0
				if(ship.checkForNewShots())
				    soundEffects.laserST=0;
		
				 if(powerUps.spreadShot.active)
		           ship.moveShotsSin();
				 else
				   ship.moveShots();		 
            
		 for(int k=0;k<enemies.length;k++){
			   enemies[k].moveShots();  
			   enemies[k].setAngle(ship.x, ship.y);
			 if(enemies[k].checkForNewShots())
				 soundEffects.laserST=0; 
		 }
     }
	

	
	private void collision(int type,int x,int y)
	{
		 triggerExplosions(x,y); 
		
		r=Generator.nextInt(28);
		  if(r==1){ 
			   powerUps.slowMo.activate(40);
		       MainThread.MAX_FPS=24;
		       MainThread.FRAME_PERIOD = 1000 /  MainThread.MAX_FPS;
		       slowMoAudio();
		      }
		  else if (r==7){
		     powerUps.slowMo.activate(130);
		       MainThread.MAX_FPS=24;
		       MainThread.FRAME_PERIOD = 1000 /  MainThread.MAX_FPS;
		       slowMoAudio();
	          }

	   switch(type){
		 case(1):
		       playerHitST=0;
			if(life==1)
          	{
          		createBitmap=true;
          	    Log.d("Collision","Starting bitmap");
          	}
	          	if(life>0)
	    	    {
		        life--;
		         }	
	          
	    	break;
		 case(2):
			 totalAsteroidsKilled+=1;
     	     astKilledThisWave+=1;   	    
             asteroidsPassed+=1;   
             score+=10;
		    break;
		 case(3):
			  totalAliensKilled+=1;
 	          aliensKilledThisWave+=1;
              score+=100;
		     break;
		
		 case(4):
				explo1.triggerExplosionsNuke(x,y);
		        explo2.triggerExplosionsNuke(x,y);
		        explo3.triggerExplosionsNuke(x,y);
		        soundEffects.nukeST = 0; 
		      for(int i=0;i<asteroids.length;i++){ 
	                if(screenShape.contains(asteroids[i].x,asteroids[i].y)) { 
	            	  asteroids[i].moveBack(); 
	                  score+=10;
	                  astKilledThisWave+=1;
	                  totalAsteroidsKilled+=1;
	                  }
	               else
	            	   asteroids[i].moveBack();	              
	           }
	            for(int i=0;i<enemies.length;i++)
	            { 
	            	if(screenShape.contains(enemies[i].x,enemies[i].y)){
	            		  totalAliensKilled+=1;
	        	          aliensKilledThisWave+=1;
	                      score+=100;
	                      enemies[i].moveBack();
	            	  }
	            	else
	            		  enemies[i].moveBack();
	            }
	            powerUps.nuke.unlocked=false;
	    		powerUps.nuke.x=MainGamePanel.deviceWidth*5+MainGamePanel.deviceWidth;
		    
			break;
	    	
		}
	   if(1!=type&&type!=4)
	     soundEffects.expST = 0;  
	   
	  	
	   
	}
	
	public void triggerExplosions(int x, int y)
	{
		switch(expCycle){
		    case(1):explo1.triggerExplosions(x,y);break;
			case(2):explo2.triggerExplosions(x,y);break;
	    	case(3):explo3.triggerExplosions(x,y);break;
		}
		expCycle+=1;
		if(expCycle==4)
			expCycle=1;
		
	}
	
	private void updateAsteroids()
    { 
        for(int i=0;i<asteroids.length;i++)
        { 
          // move each asteroid
          asteroids[i].move(); 
          
          //check for collisions with the ship
          if(asteroids[i].shipCollision(ship)&&life>0&&ship.unlocked){ 
              asteroids[i].moveBack();
              collision(1,(int)ship.x,(int)ship.y);
             } 
          //check for collision with the user shots.
          for(int j=0;j<ship.numUserShots;j++){
        	  if(asteroids[i].shotCollision(ship.shots[j])){ 
        		  collision(2,(int)asteroids[i].x,asteroids[i].y);
                  ship.deleteShot(j); 
                  //delete the original asteroid
                  asteroids[i].moveBack(); 
                 j=ship.numUserShots;//break out of inner loop - it has
                              //already been hit, don't need to check
                              //for collision with other shots
            }      
          } 
        } 
     }
	
	private void updateEnemies()
    { 
        for(int i=0;i<enemies.length;i++)
        { 
           //Moving enemies.  
           if(i==0)
             enemies[i].move(ship.x, ship.y); 
           else if(i!=0)
             enemies[i].move(enemies[i-1].x, enemies[i-1].y); 
             
              //check for physical collisions with the ship.
            if(enemies[i].shipCollision(ship)&& life > 0&&ship.unlocked){
                    collision(1,ship.x, ship.y);
              	    enemies[i].unlocked=false;
              	    enemies[i].moveBack();
            		}
  
       	 //and update collision against the ship with enemies shots 
           if(enemies[i].updateShotCollision(ship)&& life > 0){ 
                    explo1.triggerExplosions(ship.x, ship.y);
                    collision(1,ship.x, ship.y);
            		}
           
           //checking shot collision against the enemies with the players shots
           if(enemies[i].updateShipShotCollision(ship)&&ship.unlocked) {
        	    collision(3,enemies[i].x, enemies[i].y);
        	    enemies[i].unlocked=false;
        	    enemies[i].moveBack();
               }  
          }
     }
	
	
	
	public void updateGameState() 
	{
		        //this will be in charge of handling the games current state
				// waves mainly and whether to end or start a wave
				
				 //state is to be used for the state the game is in
			    // 0 is paused, 1 is ongoing , 2 between, 3 is game over
				
				
				//if the game is in between waves, and there is still wave delay left
				if((state==2&&waveDelay>=0)||state==0&&waveDelay>=0)
			       waveDelay-=1;
				
				//game over if the ship is dead
				if(life==0)
				{
					state=4;
				ship.shooting=false;
				}

			     
			     //if the player died and the game is over then stop the music
			     if(state==4&&stopMusic==false&&musicPlaying)
			     {
			    	 stopMusic=true; 
			     }
				
			     if(totalAsteroidsKilled%15==0&&bonusWaveStart==false&&wave>1)
			    	 bonusWaveStart=true;
			     
				//this starts a new wave after a set amount of time by unlocking asteroids
				//and changing the state to 1, and adding one to wave
				if(waveDelay==0)
		        {startWave();}
			
				if(astKilledThisWave==1&&musicPlaying==false)
				{
					 Log.d(TAG, "triggering music");
					startMusic=true;
				}
				//this checks to see if the limit of asteroids passing has been reached
				//if it has it will call end wave to lock all asteroids after they exit 
				//the screen and change the game state to in between waves which is state 2
				if(asteroidsPassed>=asteroidPassLimit&&state==1&&!bonusWaveStart&&bonusWaveOngoing==false)
			     {
			       endWave(); 
			     }	
				else if(asteroidsPassed>=asteroidPassLimit&&state==1&&bonusWaveStart)
				{
					startBonusWave();
				}
				
				if(asteroidsPassed>=bonusWavePassLimit&&state==1&&bonusWaveOngoing==true)
				{
					 endWave(); 
					 bonusWaveOngoing=false;
					 ship.enter=true;
				}
	  }
	
	public void startBonusWave()
	{
		Log.d(TAG, "starting bonus wave");
		bonusWaveOngoing=true;
		bonusWavePassLimit=asteroidPassLimit*2;
		ship.exit=true;
		ship.unlocked=false;
		 for(int k=0;k<enemiesUnlocked;k++) {
			  enemies[k].unlocked=false;
		     }
		powerUps.nuke.unlocked=false;
	    powerUps.shootFaster.unlocked=false;
	    powerUps.spreadShot.unlocked=false;
	    powerUps.slowMo.unlocked=false;
	    bonusWaveStart=false;
	    ship.shooting=false;
	}
		
	 public void endWave()
	 { 
		 Log.d(TAG, "ending wave");
		 
		      for(int k=0;k<asteroids.length;k++){
              asteroids[k].unlocked=false;     
             }
		      
		     for(int k=0;k<enemiesUnlocked;k++) {
			  enemies[k].unlocked=false;
		     }
		     
		      // increase number of asteroids to pass next wave
		      asteroidPassLimit+=(asteroidPassLimit)/2;
		      
		      //reset the amount of asteroids that have passed
		      asteroidsPassed=0;
		      
		      //increase the number of asteroids that will be unlocked
		      asteroidsUnlocked+=1;
		      if(asteroidsUnlocked>=asteroids.length)
		    	  asteroidsUnlocked=asteroids.length-1;
		      
		      if(wave%2==0&&wave>1)
		      enemiesUnlocked+=1;
		      if(enemiesUnlocked>=enemies.length)
		    	    enemiesUnlocked=enemies.length-1;
		      
		      stopMusic=true;
		      
		      //change state to in between
		      state=2;
		      
		      //increase the wave delay to allow time  between the waves
		      waveDelay=500;
		      
		      powerUps.nuke.unlocked=false;
		      powerUps.shootFaster.unlocked=false;
		      powerUps.spreadShot.unlocked=false;
		      powerUps.slowMo.unlocked=false;
	    }
	    
	     public void startWave()
	    {    
	    	 Log.d(TAG, "starting wave");
	    	 
	    	  //release said number of asteroids
	    	
	    	 createBossHit();
	    	 
		          for(int k=0;k<asteroidsUnlocked;k++) {
	               asteroids[k].unlocked=true;    
	              }
		          for(int k=0;k<enemiesUnlocked;k++){
					     enemies[k].unlocked=true;
				  }
		          if(wave>0)
			          totalSpeed+=1;          

	          
	          //change game state to wave ongoing
	          state=1;	         
	         
	          //add one to wave
	          wave++;       
	          
	          if(wave<4)
	        	  gameSoundtrack.setIntensity(wave, this.getContext());
	          
	          waveDelay-=1;
	          
	          stopKick=true;
	          
	         
	          powerUps.nuke.unlock();
	          powerUps.shootFaster.unlock();
	          powerUps.spreadShot.unlock();
	          powerUps.slowMo.unlock();
	          astKilledThisWave=0;
	    }
	     	
	 	public void ResetGame()
		{   
	 		stopMusic=true;
	 		stopKick=true;
	 		startMusic=false;
	 		totalAsteroidsKilled=0;
	 		asteroidsUnlocked=2;
	 		totalAliensKilled=0;
	 		aliensKilledThisWave=0;
	 		astKilledThisWave=0;
			totalSpeed=-1;
			asteroidPassLimit=15; 
		    wave = 0;    
		    state =0;
		    waveDelay = 600;
		    asteroidPassLimit=15;   
		    asteroidsPassed = 0;  
		    score = 0; 	
		    life=3;
		    ship.x=100;
		    ship.y=100;
		    enemiesUnlocked=0;
			//shootingEnemies = false;
		    for(int k=0;k<asteroids.length;k++)
	        {
		     asteroids[k].moveBack();
	         asteroids[k].unlocked=false;
	        }
		    for(int k=0;k<enemies.length;k++)
	        {
		     enemies[k].moveBack();
	         enemies[k].unlocked=false;
	        }
		    powerUps.nuke.unlocked=false;
		    powerUps.shootFaster.unlocked=false;
		    powerUps.spreadShot.unlocked=false;
		    powerUps.slowMo.unlocked=false;
		}
	 	
	 	public void normalAudio()
	 	{
	 		soundEffects.playBackRate=1;
	 	}
	 	
		public void slowMoAudio()
	 	{
			soundEffects.playBackRate=(float)0.7;
	 	}
		
	    public void setAvgFps(String avgFps) 
	    {
	 		this.avgFps = avgFps;
	   	}
         
	     public void closeGame()
	     {
	    	 ResetGame();
	    	 updateAudio();
	 		//code to end activity if you want an exit button
				thread.setRunning(false);
				((Activity)getContext()).finish();
		
	     }
	 	
	     public void updateHits()
	     {	 
	    		if(bossHit!=null)
	    		{
	    		bossHit.Move(ship.cx,ship.cy);	
	    		} 
	     }
	     
	     public void drawHits(Canvas canvas)
	     {
	    	 if(bossHit!=null)	    
 	     	{
	    	 bossHit.Draw(canvas);
 	     	}
	     }
	     
	     public void createBossHit(){ 
//	    	 if(bossHit!=null)
//	    	 {
	    	  bossHit = new HitGiantBoss();	 
	    	  bossHit.setImages(BitmapFactory.decodeResource(getResources(), R.drawable.sheephead), BitmapFactory.decodeResource(getResources(), R.drawable.sheepleg), BitmapFactory.decodeResource(getResources(), R.drawable.sheepleg));
//	    	 }
	     }

	 	private void displayFps(Canvas canvas, String fps) 
	 	{
	 		if (canvas != null && fps != null) 
	 		{
	 			Paint paint = new Paint();
	 			paint.setARGB(255, 255, 255, 255);
	 			canvas.drawText(fps, this.getWidth() - 50, 20, paint);
	 		}
	 	}
	 	

		public void createBitmap(Canvas canvas)
	 	{
	 		//myBitmap = Bitmap.createBitmap((int)deviceWidth, (int)deviceHeight, Config.RGB_565);
	 		
	 		myBitmap = Bitmap.createBitmap(getDrawingCache ());
	 		destroyDrawingCache ();
	 		//canvas.setBitmap(myBitmap);
	 		
	 		createBitmap=false;
	 		Log.d("creating bitmap "," create bitmap");
	 		
	 	}
	 	
}
