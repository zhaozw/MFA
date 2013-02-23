package com.example.HitsObjects;



import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.objects.Shot;


public class FollowAI
{
	
	
   public int radius;

   Random generator = new Random();

   
   public int width,height;  
   public int x,y,cy,cx;

   private boolean enter = true, shoot, active;
   private int shotDelay, shotDelayLeft;
   public Shot[] shots;
   public boolean shooting=true;
   public int numUserShots;
   public Bitmap img;

public boolean failed=false;
   
   public FollowAI(int shotDelay)
   {
       width = 25;
       height = 25;
       x = 500;
       y = 300;
       active=false;   
       this.shotDelay=shotDelay; // # of frames between shots
       shotDelayLeft=0;          // ready to shoot
       shots=new Shot[40];
    }
    
   public FollowAI(Bitmap bitmap)
   {
	   img=bitmap;
	   width=img.getWidth();
	   height=img.getHeight();
	   
       width = 25;
       height = 25;
       x = 500;
       y = 300;
       active=false;   
       this.shotDelay=shotDelay; // # of frames between shots
       shotDelayLeft=0;          // ready to shoot
    }
    
   
	 public void draw(Canvas canvas){
			canvas.drawBitmap(img, x , y , null);
	 }
   
   public void move(int shipX, int shipY)
    { 
        if ((shipX-x)<0)
             MoveAI2(shipY,shipX);
        else
        {     
//             if(enter&&x<40)
//                  x+=1;    
            
             if ((shipY-y)>35)
                 y+=2;
             else if ((shipY-y)<-35)
                 y-=3;
             
             if(shipX<800)//shipX>150)
             {
                  if ((shipX-x)>350)
                      x+=3;
                  else if ((shipX-x)<250)
                      x-=3;
             }
        }
   }

   public void exitScreen()
   { 
       x = -100;
       y = 300;
   } 
      
    public void MoveAI2(int shipY, int shipX)
   {     
      if(shipY>300&&y>200)
          y-=4;
      else  if(shipY<300&&y<400)
          y+=4;
      if(shipX>600&&x>500)
          x-=4;
      else if(shipX<600&&x<700)
          x+=4; 
   }
   
   public boolean shotCollision(Shot shot)
    { 
        // Same idea as shipCollision, but using shotRadius = 0
        if(Math.pow(radius,2) > Math.pow(shot.x-x,2)+ Math.pow(shot.y-y,2))
          return true; 
        return false; 
    }
   
   public int getX()
   { 
        return x; 
   } 
       
   public int getY()
   { 
        return y; 
   }
   
   public int getHEIGHT()
   { 
        return height; 
   }
   
   public int getWIDTH()
   { 
        return width; 
   }
   
   public double getRadius()
    { 
        return radius; 
    } 
     
    public boolean isActive()
   { 
        return active; 
   } 
      

   
   public boolean canShoot()
    { 
        if(shotDelayLeft>0) //checks to see if the ship is ready to 
          return false; //shoot again yet or needs to wait longer
        else
          return true; 
    } 
    
//   public Shot shoot()
//   { 
//       shotDelayLeft=shotDelay;
//
//       return new Shot(x,y,angle,90,8,laser,1);
//   }
}   