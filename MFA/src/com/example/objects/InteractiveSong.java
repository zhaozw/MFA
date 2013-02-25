package com.example.objects;




import com.example.mfa.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class InteractiveSong {

	public MediaPlayer kick,song;
	
	public InteractiveSong(Context context)
	
	{    Log.d("Music", "Creating kick");
		kick = MediaPlayer.create(context, R.raw.kick);
		Log.d("Music", "Creating Song");
		//song = MediaPlayer.create(context, R.raw.mc1);
		song=new MediaPlayer();
	} 
	
    public void setIntensity(int intensity,Context context)
    {
    	Log.d("Music", "Resetting Song");
    	
    	song.reset();
    	
    	Log.d("Music", "Intensity " + intensity);
    	switch(intensity)
    	{
    	case(1):
    		song = MediaPlayer.create(context, R.raw.mc1);
    		break;
		case(2):
			song = MediaPlayer.create(context, R.raw.mc2);
		    break;
		case(3):
			song = MediaPlayer.create(context, R.raw.mc3);
			break;
		case(4):
			song = MediaPlayer.create(context, R.raw.mc4);
			break;
    	}
   
    }
    
    public void endWave()
    {
    	 if(song.isPlaying()){

	    	 Log.d("Music", "stopping music");
	     	 song.stop();
	         song.setLooping(false);
    	 } 
    	 
    	 Log.d("Music", "starting kick");
	        kick.start();
            kick.setLooping(true);
    }

	public void startWave()
	{
		 Log.d("music", "stopping Kick");
		 if(kick.isPlaying())
	        kick.setLooping(false); 
	}
	
	public void startMusic()
	{
		Log.d("Music", "starting Music");
	
		 song.start();
	     song.setLooping(true);
	}
    
    public void releaseObjects()
    {
    	 kick.release();
    	 song.release();
    }
	
}
