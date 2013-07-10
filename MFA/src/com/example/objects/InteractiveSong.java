package com.example.objects;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.mfa.R;

public class InteractiveSong {

	public MediaPlayer kick, song;

	public InteractiveSong(Context context)

	{
		Log.d("Music", "Creating kick");
		kick = MediaPlayer.create(context, R.raw.kick);
		Log.d("Music", "Creating Song");
		// song = MediaPlayer.create(context, R.raw.mc1);
		song = new MediaPlayer();
	}

	public void setIntensity(int intensity, Context context) {
		Log.d("Music", "Resetting Song");

		song.reset();

		Log.d("Music", "Intensity " + intensity);
		switch (intensity) {
		case (1):
			song = MediaPlayer.create(context, R.raw.g1);
			break;
		case (2):
			song = MediaPlayer.create(context, R.raw.g2);
			break;
		case (3):
			song = MediaPlayer.create(context, R.raw.g3);
			break;
		case (4):
			song = MediaPlayer.create(context, R.raw.g4);
			break;
		case (5):
			song = MediaPlayer.create(context, R.raw.g5);
			break;
		case (6):
			song = MediaPlayer.create(context, R.raw.g6);
			break;
		case (7):
			song = MediaPlayer.create(context, R.raw.g7);
			break;
		case (8):
			song = MediaPlayer.create(context, R.raw.g8);
			break;
		case (9):
			song = MediaPlayer.create(context, R.raw.g9);
			break;
		}

	}

	public void endWave() {
		if (song.isPlaying()) {

			Log.d("Music", "stopping music");
			song.stop();
			song.setLooping(false);
		}

		Log.d("Music", "starting kick");
		kick.start();
		kick.setLooping(true);
	}

	public void startWave() {
		Log.d("music", "stopping Kick");
		if (kick.isPlaying())
			kick.setLooping(false);
	}

	public void startMusic() {
		Log.d("Music", "starting Music");

		song.start();
		song.setLooping(true);
	}

	public void stopAudio() {

		if (song.isLooping() || song.isPlaying()) {
			song.stop();
			song.reset();
		}
		if (kick.isLooping() || kick.isPlaying()) {
			kick.stop();
			kick.reset();
		}
	}

	public void releaseObjects() {

		try {
			kick.release();
		} catch (Exception e) {

		}
		try {
			song.release();
		} catch (Exception e) {

		}

	}

}
