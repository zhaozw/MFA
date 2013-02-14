package com.example.objects;
import java.util.Random;

import com.example.mfa.gamepanel.MainGamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;


public class BonusWaveTriggers
{
   double radius=10;
   public int x,y,cx,cy;
   int type=0;
   int width = 30;
   int height = 30;
   public boolean unlocked=false;
   Random generator =  new Random();
   public RectF shape;
   Bitmap img;
   
    public BonusWaveTriggers()
    {
      shape = new RectF(x,y, x+width, y+height);
    }
    
    public BonusWaveTriggers(int t,Bitmap img)
    {	
    	x+=generator.nextInt(MainGamePanel.deviceWidth)+(MainGamePanel.deviceWidth*2);
    	x+=MainGamePanel.deviceWidth;
        y=generator.nextInt(MainGamePanel.deviceHeight);
    	this.img=img;
        shape = new RectF(x,y, x+width, y+height);
    }
  
    public void move()
     { 

          if(unlocked) 
          {
       	          x-=5; 
       	          x-=MainGamePanel.totalSpeed;
       	          
       	          //if it is out of the screen move it back to the other side
                  if(x<=-100)
                   {
                    moveBack();
                   }
                  
           }//IF IT IS UNLOCKED THEN GET IT OUT OF THE SCREEN AND KEEP IT THERE
          else if(unlocked==false)
          {
        	  //if its unlocked but its already on screen we want to move it out of screen
        	  //before locking it
              if(x<MainGamePanel.deviceWidth+100)
            {
               	x-=MainGamePanel.totalSpeed;
                x-=10; //move the powerUp
               if(x<-100)
                    {
               	  moveBack();
                    }
             }
              
              cx=x + (width / 2);
     	      cy=y + (height/ 2);
           }
         
      	
         shape.set(x,y,x+width,y+height);
     }
    
    public boolean shipCollision(Player ship)
    { 
        //using the distance formula to check for collisions.
        if(Math.pow(radius+ship.radius,2) > Math.pow(ship.x-x,2) + Math.pow(ship.y-y,2))
        {
          return true; 
        }
        else
           return false; 
    }
    
    public void draw(Canvas canvas) 
    {
		canvas.drawBitmap(img, x , y , null);
	}
    
    public void draw2(Canvas canvas) 
    {
    		canvas.drawOval(shape,MainGamePanel.yellowPaint);
	} 

    public void moveBack()
    {
    	x+=generator.nextInt(MainGamePanel.deviceWidth*5)+MainGamePanel.deviceWidth;
        y=generator.nextInt(MainGamePanel.deviceHeight);
    } 
    

    
    
}
