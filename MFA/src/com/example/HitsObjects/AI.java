package com.example.HitsObjects;



import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;


public class AI
{
   public int radius;

   Random generator = new Random();
   public int width,height;  
   public int x,y,cy,cx;
   public Bitmap img;
   public boolean failed=false;
   public int following;
   
   public AI(Bitmap bitmap)
   {
	   img=bitmap;
	   width=img.getWidth();
	   height=img.getHeight();
	   radius=width/2;

       x = 150;
       y = 150;
     // ready to shoot
    }
    
   
	 public void draw(Canvas canvas){
		 if(img!=null)
			canvas.drawBitmap(img, x , y , null);
		 else
			 canvas.drawText("bitmap aint workin", cx, cy, MGP.redPaint);
	 }
   
   public void move(int shipX, int shipY)
    { 
//        if ((shipX-x)<0)
//             MoveAI2(shipY,shipX);
//        else
//        {     
//             if(enter&&x<40)
//                  x+=1;    
            
             if ((shipY-y)>10)
                 y+=2;
             else if ((shipY-y)<-10)
                 y-=2;
             
//             if(shipX<800)//shipX>150)
//             {
                  if ((shipX-x)>150)
                      x+=3;
                  else if ((shipX-x)<50)
                      x-=3;
//             }
//        }
                  
                  cx=x + (img.getWidth() / 2);
         	     cy=y + (img.getHeight() / 2);
   }

   
   public void moveWeird(int shipX, int shipY){
	   if ((shipX>MGP.deviceWidth/2)){
		   
	   }
	   
   }
   
   public void  followBelow(int shipX, int shipY){
       
	   
	    if ((shipY-y)>-20)
            y+=2;
        else if ((shipY-y)<-50)
             y-=2;

        if ((shipX-x)>75)
                x+=3;
        else if ((shipX-x)<25)
                x-=3;
            
  
            cx=x + (img.getWidth() / 2);
   	        cy=y + (img.getHeight() / 2);  
   }
   
   
   public void  followAbove(int shipX, int shipY){
       

	   if ((shipY-y)>20)
           y+=2;
        else if ((shipY-y)<50)
            y-=2;
	   
	   
        
            if ((shipX-x)>75)
                x+=3;
            else if ((shipX-x)<25)
                x-=3;
            
	   
            
            cx=x + (img.getWidth() / 2);
   	        cy=y + (img.getHeight() / 2);  
   }
   
      
    public void MoveAI2(int shipY, int shipX)
   {     
      if(shipY>200&&y>100)
          y-=4;
      else  if(shipY<200&&y<300)
          y+=4;
      if(shipX>300&&x>100)
          x-=4;
      else if(shipX<300&&x<400)
          x+=4; 
   }
   
   
}   