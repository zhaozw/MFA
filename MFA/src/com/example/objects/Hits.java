package com.example.objects;

import java.util.HashMap;
import java.util.Random;

import com.example.mfa.gamepanel.MainGamePanel;

public class Hits {

    
    public HitsInfo[] hitsInfo;
    public int currentlyActivatedHit=-1;
    Random randomGenerator;
    
    public Hits(){
        hitsInfo = new HitsInfo[2];
        Random randomGenerator = new Random();
    }
    
    public void updateHits(){
        
        
        switch(currentlyActivatedHit){

            case(0):
                
                break;        
            case(1):
                
                break;
        }
    }
    
    
    public void initialize(HashMap map){
        
        for(int k=0;k<=hitsInfo.length;k++){
            
                if(map.get("Hit"+k+"Bool").toString()=="1"){
                    hitsInfo[k].active = true;
                    hitsInfo[k].name = (String) map.get("Hit"+k+"From");
                    hitsInfo[k].message = (String) map.get("Hit"+k+"Msg");
                    hitsInfo[k].activationWave = randomGenerator.nextInt(10)+1;
                    hitsInfo[k].type = k;
                }
           
                else if(map.get("Hit"+k+"Bool").toString()=="0")
                    hitsInfo[k].active=false;    
        }    
    }
    public void checkStartHit()
    {
    	for(int k=0;k<=hitsInfo.length;k++){
    	 if(hitsInfo[k].activationWave==MainGamePanel.wave)
    	 createHit(k);
    	}
    	 
    }
     
    public void createHit(int hit){
    	 
    	switch(hit){

    	case(0):
    	 //create this hit
    	 break; 
    	 
    	case(1):
    	 //create this hit instead
    	 break;
     }
     currentlyActivatedHit = hit;
    }
}