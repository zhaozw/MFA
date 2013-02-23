package com.example.HitsObjects;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;
import com.example.objects.Shot;

public class HitGiantBossIndividualParts
{
   private int radius; 
   Random generator = new Random();
   public int width, height, x, y;
   public Bitmap shotImg,img;
   public boolean enter=true,attack,drawOutlines=true;
   public int cy, cx,biteDistance,shipBiteLocation,upperConstraint,bottomConstraint;
   
   
   public HitGiantBossIndividualParts()
   {
  
	   
	   
   }
   
   public HitGiantBossIndividualParts(int x, int y)
   {
       width = 400;
       height = 30;
       x = 1300;
       y = 150;
   }
   
   public HitGiantBossIndividualParts(int shotDelay)
   {
       width = 400;
       height = 30;
       x = 1300;
       y = 150;
   }
  
   public void draw(Canvas canvas)
   {
	   canvas.drawBitmap(img, x , y , null);
	   
	   if(drawOutlines)
	   {
		   canvas.drawLine(x,y,x+width,y,MGP.greenPaint);
		   canvas.drawLine(x,y+height,x+width,y+height,MGP.greenPaint); 
		   canvas.drawLine(x,y,x,y+height,MGP.greenPaint);
		   canvas.drawLine(x+width,y,x+width,y+height,MGP.greenPaint);
		   canvas.drawLine(x,cy,cx,cy,MGP.greenPaint);
	   }
	   
   }
   
   public void setImage(Bitmap bitmap)
   {
	   img = bitmap;
	   width=img.getWidth();
	   height=img.getHeight();
   }
   

   public void MoveUpperJaw(int shipx, int shipy)// int lowerJaw)
   {     
     if(enter&&x>MGP.deviceWidth-width)
          x-=5; 
     else 
         enter=false;
     

     
     if ((shipy-y)>120&&cy<MGP.deviceHeight/2)//&&Y<300)
         y+=3;
     else if ((shipy-y)<80&&y>0)//&&Y>100)
         y-=3;
     

     
     
//     if ((shipx-x)>-50&&x+(width/2)==MainGamePanel.deviceWidth)
//         attack = true;
            
//     if(attack)     
//     {
//         x-=120;
//         y+=120;   
//      attack=false;
//     }
     
     
     if(attack==false&&x<MGP.deviceWidth-(width/2))   
          x+=1;
     // y-=1;
     // }
     
     
      cy=y+(height/2);
      cx=x+(width/2);
      
   }
   
   public void MoveLowerJaw(int shipx, int shipy)// int upperJaw)
   {        
	   
	   
     if(enter&&x>MGP.deviceWidth-width)
          x-=5; 
     else 
         enter=false;
    
     
       if ((shipy-y)>-80&&y+height<MGP.deviceHeight)//&&y<300)
         y+=3;
     else if ((shipy-y)<-120&&cy>MGP.deviceHeight/2)//&&y>100)
         y-=3;
         
       
//     if ((shipx-x)>-50&&x==800)
//         attack = true;
//     if(attack)
//           {x-=120;
//            y-=120;   
//        attack=false;}
//     if(attack==false&&x<800)   
//          x+=1;
       
       if(attack==false&&x<MGP.deviceWidth-(width/2))   
           x+=1;
     
     cy=y+(height/2);
     cx=x+(width/2);
     
   }
   
   public void MoveEye(int shipx, int shipy)
   { 
	   
	   //EYEBALL ENTERING
     if(enter&&x>MGP.deviceWidth-width)
          x-=1; 
     else 
         enter=false;
     
     
      
       if (((shipy-cy)>10)&&cy<MGP.deviceHeight-100)
         y+=2;
       else if (((shipy-cy)<-10)&&cy>100)
         y-=2;
     
       
       
//     if ((shipx-x)>-275&&x==1025)
//         attack = true;
//     if(attack)
//           x+=120;
//          attack=false;
//     if(attack==false&&x>1025)   
//           x-=1;
//     
     cy=y+(height/2);
     cx=x+(width/2);
     
   }
   
	public boolean shotCollision(Shot shot)
    { 
        // Uses the distance formula to check for collisions with other shots.
        if(Math.pow(radius,2) > Math.pow(shot.x-x,2)+ Math.pow(shot.y-y,2))
          return true; 
        return false; 
    }
   
   public int getx()
   { 
        return x; 
   } 
       
   public int gety()
   { 
        return y; 
   }
   
  
   
 
}   