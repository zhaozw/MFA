
	package com.example.HitsObjects;




	import java.util.Random;

	import android.graphics.Bitmap;
	import android.graphics.Canvas;

	import com.example.mfa.gamepanel.MGP;
	import com.example.objects.Player;
	import com.example.objects.Shot;

	public class MineField 
	{ 
	   public boolean failed;
		public static Mine[] mines;
	    
	    public MineField(int amount,Bitmap bitmap){ 
	    	mines = new Mine[amount];
	    	for(int k=0;k<mines.length;k++)
	    	     mines[k]= new Mine(bitmap);
	    }
	    
	    public MineField(Bitmap bitmap){ 
	    	mines = new Mine[10];
	    	
	    	for(int k=0;k<mines.length;k++)
	    	     mines[k]= new Mine(bitmap);
	    }
	    
	    
	    public void move(){ 
	    	 for(int k=0;k<mines.length;k++)
	    	     mines[k].move();
	    } 
	    
	    public void draw(Canvas canvas){ 
	    	 for(int k=0;k<mines.length;k++)
	    	     mines[k].draw(canvas);
	    } 
       
       
}
