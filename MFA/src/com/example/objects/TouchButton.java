package com.example.objects;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class TouchButton {

	int size;	 
	public int x,y,h,w;
	public RectF touchLocation;
    private Bitmap bitmap;	// the actual bitmap
	public boolean active=false;
    
	public TouchButton(Bitmap b,int x,int y,int h,int w)
	{
		bitmap=b;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;	
		touchLocation = new RectF(x,y,h,w);
	}
	
	public TouchButton(Bitmap b,int x,int y,int size)
	{
		bitmap=b;
		this.x=x;
		this.y=y;
		this.w=x+size;
		this.h=y+size;
		this.size=size;
		touchLocation = new RectF(x,y,x+size,y+size);
	}
	
	public boolean getActiveState()
	{
		return active;
	}
	
	public void setActiveState(boolean a)
	{
		active=a;
	}
	
	public RectF getShape()
	{	
		return touchLocation;
	}
	
	public void draw(Canvas canvas) 
	{
			canvas.drawBitmap(bitmap, x , y, null);
	}

}
