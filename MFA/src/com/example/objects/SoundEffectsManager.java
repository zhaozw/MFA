package com.example.objects;

import java.util.Random;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.mfa.R;
import com.example.mfa.gamepanel.MGP;

public class SoundEffectsManager {

	// soundpool can hold multiple small audiofiles
	// this will be used to hold non all effects sounds
	SoundPool SP;

	// soundpool when registering a song with it returns an integer that will
	// then be used to play that song at a later time
	// this is where i will be creating those integers
	// because the each individual sound can only be played one at at time
	// there needs to be at least 3 explosion sounds to allow for asteroids to
	// be shot in quick succesion and have a sound play each time
	int exp1SID, exp2SID, exp3SID, laser1SID, laser2SID, laser3SID,
			shipHit1SID, shipHit2SID, shipHit3SID, nukeSID;

	// what the used to trigger an explosion sound instead of using a boolean
	// ST for sound trigger//exp cycle for switching through the different 3
	// explosions objects
	public int expST = -1;

	int expCycle = 1;

	int musicST = 1;

	int playTrack = 1;

	int playKick;
	boolean startMusic = false, stopMusic = false, musicPlaying = false,
			kickPlaying = false, stopKick = false;
	public int laserST = -1, playerHitST = -1, bonusWavePassLimit;

	public int nukeST = -1;

	// controls rate of audio playback from .5 to 1.5 i believe
	public float playBackRate = 1;

	// for random numbers
	Random Generator = new Random();
	int r;

	public SoundEffectsManager(Context context) {

		SP = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

		exp1SID = SP.load(context, R.raw.exp1, 1);
		exp2SID = SP.load(context, R.raw.exp2, 1);
		exp3SID = SP.load(context, R.raw.exp3, 1);

		laser1SID = SP.load(context, R.raw.la1, 1);
		laser2SID = SP.load(context, R.raw.la2, 1);
		laser3SID = SP.load(context, R.raw.la3, 1);

		shipHit1SID = SP.load(context, R.raw.sh1, 1);
		shipHit2SID = SP.load(context, R.raw.sh2, 1);
		shipHit3SID = SP.load(context, R.raw.sh3, 1);

		nukeSID = SP.load(context, R.raw.nuke, 1);
	}

	public void playSounds() {

		// player hit sounds
		if (playerHitST == 0) {
			if (MGP.life == 3)
				SP.play(shipHit1SID, 1, 1, 0, 0, playBackRate);
			else if (MGP.life == 2)
				SP.play(shipHit2SID, 1, 1, 0, 0, playBackRate);
			else
				SP.play(shipHit3SID, 1, 1, 0, 0, playBackRate);
			playerHitST = -1;
		}

		// laser sounds
		if (laserST == 0) {
			// play the clip and turn the trigger off
			laserST = -1;
			r = Generator.nextInt(3);
			switch (r) {
			case 0:
				SP.play(laser1SID, 1, 1, 0, 0, playBackRate);
				break;
			case 1:
				SP.play(laser2SID, 1, 1, 0, 0, playBackRate);
				break;
			case 2:
				SP.play(laser3SID, 1, 1, 0, 0, playBackRate);
				break;
			}
		}

		// explosion sounds
		if (expST == 0) {
			// play the clip and turn the trigger off
			expST = -1;
			r = Generator.nextInt(3);
			switch (r) {
			case 0:
				SP.play(exp1SID, 1, 1, 0, 0, playBackRate);
				break;
			case 1:
				SP.play(exp2SID, 1, 1, 0, 0, playBackRate);
				break;
			case 2:
				SP.play(exp3SID, 1, 1, 0, 0, playBackRate);
				break;
			}
		}

		// nuke sound
		if (nukeST == 0) {
			SP.play(nukeSID, 1, 1, 0, 0, playBackRate);
			nukeST = -1;
		}
	}

}
