
	package com.example.HitsObjects;




	import java.util.Random;

	import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.graphics.Canvas;
    import com.example.mfa.R;
	import com.example.mfa.gamepanel.MGP;
	import com.example.objects.Player;
	import com.example.objects.Shot;
	

	public class SmallGroupAIs 
	{ 
		public AIPack groupAI;
	    public boolean failed=true;
		
	    public SmallGroupAIs(Bitmap img)     
	    { 
	    	groupAI = new AIPack(10,img,img);
	    }
	    
	   
	    public void move(Player ship)
	     { 
	       groupAI.move(ship);
	     } 
	    
	    public void draw(Canvas canvas)
	     { 
	       groupAI.draw(canvas);
	     } 
	    
	  
}
