package com.example.objects;

import com.example.mfa.gamepanel.MainGamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Shot 
{

	private final double shotSpeed, angle; 
    public double x;
	public double y;
	private double xVelocity;
	private double yVelocity; 
    private int lifeLeft; 
    private Bitmap bitmap;	// the actual bitmap
    int ps, sine=15;
	
	public Shot(double x, double y, double angle, int lifeLeft, int shotSpeed, Bitmap bitmap) 
	{
		this.x=x; 
        this.y=y; 
        ps=(int)y;
        this.angle=angle;
        xVelocity=shotSpeed*Math.cos(angle); 
        yVelocity=shotSpeed*Math.sin(angle); 
        this.bitmap = bitmap;
        // the number of frames the shot will last for before disappearing if it doesn't hit anything 
        this.lifeLeft=lifeLeft;  
        this.shotSpeed = shotSpeed;
	}
	
	public void move()
    { 
        lifeLeft--; // used to make shot disappear if it goes too long 
                    // without hitting anything
        x+=xVelocity;
       // if(MainGamePanel.powerUps.spreadShot.active)
        y+=yVelocity;
        
        if(x>MainGamePanel.deviceWidth)
        	lifeLeft=0;
    }
	
	public void moveSin() 
	 {
	  lifeLeft-=0.5; 
	  sine+=5;   
	  x += 2;
	  y += sine * Math.sin(x);
	 }
	 

	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(bitmap,(float)x-(bitmap.getWidth()/2),(float)y-(bitmap.getHeight()/2),null);
	}
	
    public int getLifeLeft()
    { 
        return lifeLeft; 
    }
	

}
