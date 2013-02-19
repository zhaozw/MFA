package com.example.objects;

import android.graphics.Canvas;

import com.example.mfa.gamepanel.MGP;

public class LightBackground {
	
//	
//	   //array of array of lights
//    //because arrays that are embedded in an array, can have varying lengths
//    //you can only initialize the first value here
    Light[][] lights2 = new Light[5][];
    public int lightColor=0;
    
    public LightBackground(){
    	   //initialize the light arrays
  	    lights2[0] = new Light[20]; 
        lights2[1] = new Light[20]; 
        lights2[2] = new Light[15];
        lights2[3] = new Light[15];
        lights2[4] = new Light[10];
//        
//        //initialize lights speed and width
        for(int r=0; r<lights2.length; r++)
          {
              for(int c = 0; c < lights2[r].length; c++)
              { 
               if(r==0)
               lights2[r][c] = new Light(1,1);
               if(r==1)
               lights2[r][c] = new Light(2,1);
               if(r==2)
               lights2[r][c] = new Light(3,3);
               if(r==3)
               lights2[r][c] = new Light(4,8);
               if(r==4)
               lights2[r][c] = new Light(5,12);  
              }  
          }
    }
	
	public void draw(Canvas canvas)
	{
		 switch(MGP.lightColor)
		   {
		   case(0):
			   for(int r=0; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.WPaint);    
		            }   
		        }
			   break;
		   case(1):
			   for(int r=0; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.bluePaint);    
		            }   
		        }
			   break;
		   case(2):
			   for(int r=0; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.greenPaint);    
		            }   
		        }
			   break;
		   case(3):
			   for(int r=0; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.redPaint);    
		            }   
		        }
			   break; 
		   case(4):
			   for(int r=0; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.yellowPaint);    
		            }   
		        }
			   break;
		   case(5):
			   for(int r=0; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.orangePaint);    
		            }   
		        }
			   break;	
		   case(6):
			   for(int r=0; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.purplePaint);    
		            }   
		        }
			   break;
		   case(7):
			   for(int r=0; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.pinkPaint);    
		            }   
		        }
			   break;
		   }
	}
	
    public void updateLights()
    {
    	 for(int r=0; r<lights2.length; r++)
	          {
	              for(int c = 0; c < lights2[r].length; c++)
	              {    
	            	 lights2[r][c].MoveStar();  
	              }   
	          }
    }
    

	public void drawBack(Canvas canvas)
	{
		 switch(lightColor)
		   {
		   case(0):
			   for(int r=0; r<3; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.WPaint);    
		            }   
		        }
			   break;
		   case(1):
			   for(int r=0; r<3; r++)
		          {for(int c = 0; c < lights2[r].length; c++){     
		            canvas.drawOval(lights2[r][c].shape,MGP.bluePaint);    
		            }   
		        }
			   break;
		   case(2):
			   for(int r=0; r<3; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.greenPaint);    
		            }   
		        }
			   break;
		   case(3):
			   for(int r=0; r<3; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.redPaint);    
		            }   
		        }
			   break; 
		   case(4):
			   for(int r=0; r<3; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.yellowPaint);    
		            }   
		        }
			   break;
		   case(5):
			   for(int r=0; r<3; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.orangePaint);    
		            }   
		        }
			   break;	
		   case(6):
			   for(int r=0; r<3; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.purplePaint);    
		            }   
		        }
			   break;
		   case(7):
			   for(int r=0; r<3; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.pinkPaint);    
		            }   
		        }
			   break;
		   }
	}
	public void drawClose(Canvas canvas)
	{
		 switch(lightColor)
		   {
		   case(0):
			   for(int r=3; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.WPaint);    
		            }   
		        }
			   break;
		   case(1):
			   for(int r=3; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.bluePaint);    
		            }   
		        }
			   break;
		   case(2):
			   for(int r=3; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.greenPaint);    
		            }   
		        }
			   break;
		   case(3):
			   for(int r=3; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.redPaint);    
		            }   
		        }
			   break; 
		   case(4):
			   for(int r=3; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.yellowPaint);    
		            }   
		        }
			   break;
		   case(5):
			   for(int r=3; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.orangePaint);    
		            }   
		        }
			   break;	
		   case(6):
			   for(int r=3; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.purplePaint);    
		            }   
		        }
			   break;
		   case(7):
			   for(int r=3; r<lights2.length; r++)
		          {for(int c = 0; c < lights2[r].length; c++){    
		            canvas.drawOval(lights2[r][c].shape,MGP.pinkPaint);    
		            }   
		        }
			   break;
		   }
	}
	
	
}
