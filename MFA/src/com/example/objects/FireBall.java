package com.example.objects;


//this class is to be used to make multiple lights to move quickly 
//across the screen to create the appearence of motion
import java.util.Random;
import java.awt.*;

import com.example.mfa.gamepanel.MainGamePanel;

import android.graphics.Canvas;
import android.graphics.RectF;
public class FireBall
{
  Random generator = new Random();
   //width of Explosion
    int WIDTH;   
   //height of Explosion
    int HEIGHT;   
   //X coordinate
    int X;
   // Y coordinate
   int Y;
   int rSize,r;
   int type,dir,d;
   //1 for small, 2 for big, 3 for explosion lights 
    boolean explode = false;
   //public boolean thrust = false;
    boolean nuke;
    
   public int color = 1;
   // 1 = red   2 = orange   3 = magenta  4 = yellow  5 = white
   
   RectF  shape = new RectF(X,Y,X+100,Y+100);
   
   public FireBall(int t, int dr)
   {
       WIDTH = 0;
       HEIGHT = 0;
       rSize = generator.nextInt(10);
       dir=dr;
       type = t;
       X = 250;//(250) + rSize;
       Y =  250;//(250) + rSize;
       explode = false;
       shape.set(X,Y,X+HEIGHT,Y+WIDTH);
       
   }
  
   public FireBall()
   {
       WIDTH = 0;
       HEIGHT = 0;
       rSize = generator.nextInt(30); 
       type = 1;
       X = 250;//(250) + rSize;
       Y =  250;//(250) + rSize;
       explode = false;
   }
  
   public void ThrustersGo(double x, double y)
   {  
	   if(WIDTH<10)
        {WIDTH=10;
         HEIGHT=7;}
	   
	 if(X>x-35){
		 color=1;
		 Y -= generator.nextInt(2) ;
         Y += generator.nextInt(2) ;
	 }
	 else if(X>x-38){
		 color=2; 
		 WIDTH -= generator.nextInt(2) ;
         WIDTH += generator.nextInt(5) ;
         HEIGHT -= generator.nextInt(2) ; 
         HEIGHT += generator.nextInt(2); 
         Y -= generator.nextInt(3) ;
         Y += generator.nextInt(3) ;
	 }
     else if(X>x-50){
    	 color=3;
    	 WIDTH -= generator.nextInt(2) ;
         WIDTH += generator.nextInt(5) ;
         HEIGHT -= generator.nextInt(2) ; 
         HEIGHT += generator.nextInt(2); 
         Y -= generator.nextInt(5) ;
         Y += generator.nextInt(5) ;
     }
	 else if(X>x-58){
		 color=4;
		 WIDTH -= generator.nextInt(2) ;
         WIDTH += generator.nextInt(5) ;
         HEIGHT -= generator.nextInt(2) ; 
         HEIGHT += generator.nextInt(2); 
         Y -= generator.nextInt(5) ;
         Y += generator.nextInt(5) ;
	 }
	 else{
		 color=5;
		 WIDTH -= generator.nextInt(2) ;
         WIDTH += generator.nextInt(5) ;
         HEIGHT -= generator.nextInt(2) ; 
         HEIGHT += generator.nextInt(2); 
         Y -= generator.nextInt(5) ;
         Y += generator.nextInt(5) ;
	 }
	 
     X -= generator.nextInt(17) ;    
     
       if(WIDTH>27||HEIGHT>27||X<0||X-10>x)
           {
               HEIGHT=0;
               WIDTH=0;
               X = (int)x;
               Y = (int)y-(5);
               X-=24;
            }
          shape.set(X,Y,X+WIDTH,Y+HEIGHT);
   }
   
   public void drawThrusters(Canvas canvas)
    { 
	   if(color == 1)
		   canvas.drawOval(shape,MainGamePanel.blueT);   
	   else if(color == 2)
		   canvas.drawOval(shape,MainGamePanel.redT); 
	   else if(color== 3) 
		   canvas.drawOval(shape,MainGamePanel.orangeT); 
	   else if(color == 4)
		   canvas.drawOval(shape,MainGamePanel.yellowT); 
	   else if(color== 5) 
		   canvas.drawOval(shape,MainGamePanel.s1); 
    }  
  
   
   public void drawExplosions1(Canvas canvas)
    {
	   if(type!=3) 
      {
	   if(color == 1)
		   canvas.drawOval(shape,MainGamePanel.redPaint);   
	   else if(color == 2)
		   canvas.drawOval(shape,MainGamePanel.orangePaint); 
	   else if(color== 3) 
		   canvas.drawOval(shape,MainGamePanel.blackPaint); 
	   else if(color == 4)
		   canvas.drawOval(shape,MainGamePanel.yellowPaint); 
	   else if(color== 5) 
		   canvas.drawOval(shape,MainGamePanel.WPaint); 
	   }
  else {
	   canvas.drawOval(shape,MainGamePanel.WPaint);  
	   }  
    }
  public void drawExplosions2(Canvas canvas)
	    { 
		   if(type!=3) 
		      {
			   if(color == 1)
				   canvas.drawOval(shape,MainGamePanel.blackPaint);   
			   else if(color == 2)
				   canvas.drawOval(shape,MainGamePanel.WPaint); 
			   else if(color== 3) 
				   canvas.drawOval(shape,MainGamePanel.blackPaint); 
			   else if(color == 4)
				   canvas.drawOval(shape,MainGamePanel.WPaint); 
			   else if(color== 5) 
				   canvas.drawOval(shape,MainGamePanel.blackPaint); 
			   }
		   else 
		   {
			   canvas.drawOval(shape,MainGamePanel.WPaint); 
			   }  
	      }
  public void drawExplosions3(Canvas canvas)  
		    { 
			   if(type!=3) 
		      {
			   if(color == 1)
				   canvas.drawOval(shape,MainGamePanel.redPaint);   
			   else if(color == 2)
				   canvas.drawOval(shape,MainGamePanel.redPaint); 
			   else if(color== 3) 
				   canvas.drawOval(shape,MainGamePanel.redPaint); 
			   else if(color == 4)
				   canvas.drawOval(shape,MainGamePanel.redPaint); 
			   else if(color== 5) 
				   canvas.drawOval(shape,MainGamePanel.redPaint); }
		   else {
			   canvas.drawOval(shape,MainGamePanel.redPaint); 
			   } 
		    }
  public void drawExplosions4(Canvas canvas)
			    {  if(type!=3) 
			      {
					   if(color == 1)
						   canvas.drawOval(shape,MainGamePanel.s1);   
					   else if(color == 2)
						   canvas.drawOval(shape,MainGamePanel.s2); 
					   else if(color== 3) 
						   canvas.drawOval(shape,MainGamePanel.s1); 
					   else if(color == 4)
						   canvas.drawOval(shape,MainGamePanel.s2); 
					   else if(color== 5) 
						   canvas.drawOval(shape,MainGamePanel.s1); }
				   else {
					   canvas.drawOval(shape,MainGamePanel.s2);  
					   }  
			    }
	
public void drawExplosions5(Canvas canvas)
				    {  if(type!=3) 
				      {
						   if(color == 1)
							   canvas.drawOval(shape,MainGamePanel.bluePaint);   
						   else if(color == 2)
							   canvas.drawOval(shape,MainGamePanel.bluePaint); 
						   else if(color== 3) 
							   canvas.drawOval(shape,MainGamePanel.blackPaint); 
						   else if(color == 4)
							   canvas.drawOval(shape,MainGamePanel.blackPaint); 
						   else if(color== 5) 
							   canvas.drawOval(shape,MainGamePanel.WPaint); }
					   else {
						   canvas.drawOval(shape,MainGamePanel.bluePaint);
						   } 
				    }
public void drawExplosions6(Canvas canvas)
				    {  if(type!=3) 
				      {
						   if(color == 1)
							   canvas.drawOval(shape,MainGamePanel.pinkPaint);   
						   else if(color == 2)
							   canvas.drawOval(shape,MainGamePanel.redPaint); 
						   else if(color== 3) 
							   canvas.drawOval(shape,MainGamePanel.bluePaint); 
						   else if(color == 4)
							   canvas.drawOval(shape,MainGamePanel.pinkPaint); 
						   else if(color== 5) 
							   canvas.drawOval(shape,MainGamePanel.redPaint); }
					   else {
						   canvas.drawOval(shape,MainGamePanel.pinkPaint);
						   } 
				    }
public void drawExplosions7(Canvas canvas)
				    {  if(type!=3) 
				      {
						   if(color == 1)
							   canvas.drawOval(shape,MainGamePanel.purplePaint);   
						   else if(color == 2)
							   canvas.drawOval(shape,MainGamePanel.bluePaint); 
						   else if(color== 3) 
							   canvas.drawOval(shape,MainGamePanel.blackPaint); 
						   else if(color == 4)
							   canvas.drawOval(shape,MainGamePanel.purplePaint); 
						   else if(color== 5) 
							   canvas.drawOval(shape,MainGamePanel.purplePaint); }
					   else {
						   canvas.drawOval(shape,MainGamePanel.purplePaint);
						   } 
				    
				    
		     }   

public void drawExplosions8(Canvas canvas)
{  if(type!=3) 
  {
	   if(color == 1)
		   canvas.drawOval(shape,MainGamePanel.pinkPaint);   
	   else if(color == 2)
		   canvas.drawOval(shape,MainGamePanel.pinkPaint);
	   else if(color== 3) 
		   canvas.drawOval(shape,MainGamePanel.pinkPaint);
	   else if(color == 4)
		   canvas.drawOval(shape,MainGamePanel.pinkPaint); 
	   else if(color== 5) 
		   canvas.drawOval(shape,MainGamePanel.pinkPaint); }
   else {
	   canvas.drawOval(shape,MainGamePanel.pinkPaint);
	   } 


}  

public void drawExplosions9(Canvas canvas)
{  if(type!=3) 
  {
	   if(color == 1)
		   canvas.drawOval(shape,MainGamePanel.greenPaint);   
	   else if(color == 2)
		   canvas.drawOval(shape,MainGamePanel.redPaint); 
	   else if(color== 3) 
		   canvas.drawOval(shape,MainGamePanel.bluePaint); 
	   else if(color == 4)
		   canvas.drawOval(shape,MainGamePanel.purplePaint); 
	   else if(color== 5) 
		   canvas.drawOval(shape,MainGamePanel.orangePaint); }
   else {
	   canvas.drawOval(shape,MainGamePanel.purplePaint);
	   } 


}  

public void drawExplosions10(Canvas canvas)
{  if(type!=3) 
  {
	   if(color == 1)
		   canvas.drawOval(shape,MainGamePanel.WPaint);   
	   else if(color == 2)
		   canvas.drawOval(shape,MainGamePanel.WPaint); 
	   else if(color== 3) 
		   canvas.drawOval(shape,MainGamePanel.redPaint); 
	   else if(color == 4)
		   canvas.drawOval(shape,MainGamePanel.greenPaint); 
	   else if(color== 5) 
		   canvas.drawOval(shape,MainGamePanel.greenPaint);}
   else {
	   canvas.drawOval(shape,MainGamePanel.redPaint);
	   } 


}  
   public void drawExplosions0(Canvas canvas)
   { 
      if(type!=3) 
       {
        if(color == 1)
       	 canvas.drawOval(shape,MainGamePanel.greenPaint);   
        else if(color == 2)
       	 canvas.drawOval(shape,MainGamePanel.greenPaint); 
        else if(color== 3) 
       	 canvas.drawOval(shape,MainGamePanel.greenPaint); 
        else if(color == 4)
       	 canvas.drawOval(shape,MainGamePanel.greenPaint); 
        else if(color== 5) 
       	 canvas.drawOval(shape,MainGamePanel.greenPaint);  
       }
      else
      {
   	   canvas.drawOval(shape,MainGamePanel.greenPaint); 
      }  
   }
 
      public void ExplodeTrue(double x, double y)
   {
         explode=true;          
         X=(int)x;
         Y=(int)y;
   }
   
      public boolean GetExplodeStatus()
   {
         return explode;   
   }

  
   public void Explode()
   { 
	   if(nuke==true)
	   {
		   ExplodeNuke();
	   }
	   else
	   {
          switch(type)
          {   
           case(1):
                ExplodeHuge();
            break;
           case(2):
                ExplodeSmall();
            break;
           case(3):
               ExplodeLights();
             break;
           }   
	   }
   }
   
   
   public void ExplodeNuke()
   { 
	   if(explode)
       { 
        // WIDTH -=5;  //generator.nextInt(2) ;
        WIDTH +=20;  //generator.nextInt(5) ;
        //HEIGHT -=5; //generator.nextInt(2) ; 
        HEIGHT += 20; //generator.nextInt(2) ;
        color = generator.nextInt(5)+1;
        X -= generator.nextInt(150) ;    
        Y -= generator.nextInt(150) ;
        X += generator.nextInt(150) ;
        Y += generator.nextInt(150) ;
        X -= 8;    
        Y -= 8;
        
       if(HEIGHT>MainGamePanel.deviceHeight&&WIDTH>MainGamePanel.deviceWidth)
       {
             explode=false;
            HEIGHT=0;
             WIDTH=0;   
             nuke=false;
          }
         }
       shape.set(X,Y,X+HEIGHT,Y+WIDTH);
   }
    
//    public void ExplodeLights(double x, double y)
  public void ExplodeLights()
   {
     if(explode)
      {   
        HEIGHT=7;
        WIDTH=7;
       switch(dir)
      { 
           case(1):
           X+=10;
             break;
           case(2):
           X-=10;
             break;
           case(3):
           Y+=10;
             break;
           case(4):
           Y-=10;
             break;
           case(5):
            X+=10;Y+=15;
             break;
           case(6):
           X+=10;Y-=15;
             break;
           case(7):
           X-=10;Y+=15;
             break;
           case(8):
           X-=10;Y-=15;
             break;
        } 
       if(X<0||Y<0||Y>600||X>1200)
       {
            explode=false;
            HEIGHT=0;
            WIDTH=0;
        }
      }  
     shape.set(X,Y,X+HEIGHT,Y+WIDTH);
   }
    
//      public void ExplodeHuge(double x, double y)
     public void ExplodeHuge()
   {
          if(explode)
          { 
           // WIDTH -=5;  //generator.nextInt(2) ;
           WIDTH +=10;  //generator.nextInt(5) ;
           //HEIGHT -=5; //generator.nextInt(2) ; 
           HEIGHT += 10; //generator.nextInt(2) ;
           color = generator.nextInt(5)+1;
           X -= generator.nextInt(20);    
           Y -= generator.nextInt(20);
           X += generator.nextInt(20);
           Y += generator.nextInt(20);
           X -= 4;    
           Y -= 4;
           if(HEIGHT>100)
           {
                explode=false;
               HEIGHT=0;
                WIDTH=0;   
             }
            }
          shape.set(X,Y,X+HEIGHT,Y+WIDTH);
   }
   
   
     public void ExplodeSmall()
   {
        if(explode)
          { 
           X -= 2;    
           Y -= 2;
      // WIDTH -=5;  //generator.nextInt(2) ;
       WIDTH +=3;  //generator.nextInt(5) ;
       //HEIGHT -=5; //generator.nextInt(2) ; 
       HEIGHT +=3; //generator.nextInt(2) ;
       color = generator.nextInt(5)+1;
       X -= generator.nextInt(15) ;    
       Y -= generator.nextInt(15) ;
       X += generator.nextInt(15) ;
       Y += generator.nextInt(15) ;
       //X -= 1;    
       //Y -= 1;
       if(HEIGHT>50)
        {
           explode=false;
           HEIGHT=0;
           WIDTH=0;
        }
      
    }
        shape.set(X,Y,X+HEIGHT,Y+WIDTH);
   }
   
  public int getX()
   {   
       return X;
   }
   
   public int getY()
   {   
         return Y;
   }
   
    public int getHEIGHT()
   {   
         return HEIGHT;
   }
   
   public int getWIDTH()
   {   
         return WIDTH;
   }
   
   
   public void SetX(double x)
   {   
           X=(int)x;
   }
   
   public void SetY(double y)
   {   
           Y=(int)y;
   }
   
     public void Reset()
   { //AsteroidsGame.explodeGo=false;   
     explode=false;
     WIDTH = 0;
     HEIGHT = 0;
     shape.set(X,Y,X+HEIGHT,Y+WIDTH);
   }
}
