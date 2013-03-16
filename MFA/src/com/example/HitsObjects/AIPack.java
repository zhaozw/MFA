package com.example.HitsObjects;

import com.example.objects.Player;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class AIPack {

	
	AI[] aiPack;
	
	public AIPack(){
	}
	
	
    public AIPack(int amount,Bitmap bitmap){
		
		aiPack = new AI[amount];
		
		for(int k=0;k<aiPack.length;k++){
			aiPack[k]= new AI(bitmap);
		}
		
		for(int k=0;k<aiPack.length;k++){
			if(k==1||k==2)
				aiPack[k].following=0;
			else if(k>2)
				aiPack[k].following=k-2;	
		}
		
		
	}
	
	
    public void draw(Canvas canvas){
    	for(int k=0;k<aiPack.length;k++){
			aiPack[k].draw(canvas);	
		}
    }
    
    public void move(Player ship){

    	for(int k=0;k<aiPack.length;k++){
			if(k==0)
				aiPack[k].move(ship.x, ship.y);
			else if(k%2==0)
				aiPack[k].followBelow(aiPack[aiPack[k].following].x, aiPack[aiPack[k].following].y);
			else if(k%2==1)
				aiPack[k].followAbove(aiPack[aiPack[k].following].x, aiPack[aiPack[k].following].y);
		}
    	
    }
    
}
