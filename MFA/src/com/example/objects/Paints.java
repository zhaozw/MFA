package com.example.objects;

import android.graphics.Paint;

public class Paints {

	
	// Colors
	public static Paint textPaint, white, red ,darkBlue, lightBlue,lightGreen,darkGreen,
			yellow, grey, black, orange, purple,
			pink, silver1, silver2, blueT, redT, orangeT, yellowT,messagePaint,
			ep1,ep2,ep3,ep4,ep5,ep6,thrusters,lights;
	

	// 0 white
	// 1 black
	// 2 red
	// 3 orange
	// 4 yellow
	// 5 lightGreen
	// 6 darkGreen
	// 7 lightBlue
	// 8 darkBlue
	// 9 purple 
	// 10 pink

	public Paints() {
		
		
				textPaint = new Paint(); 
				textPaint.setARGB(150, 255, 0, 0);
				textPaint.setTextSize(11); 
				textPaint.setAntiAlias(true); // red paint
				
				
				messagePaint= new Paint();
				
				white = new Paint();
				white.setARGB(255, 255, 255, 255);
				red = new Paint();
				red.setARGB(255, 255, 0, 0);
				red.setTextSize(11);
				red.setAntiAlias(true);
				lightBlue = new Paint();
				lightBlue.setARGB(255, 0, 206, 209);
				lightBlue.setTextSize(11);
				lightBlue.setAntiAlias(true);
				darkBlue = new Paint();
				darkBlue.setARGB(255, 0, 0, 255);
				darkBlue.setTextSize(11);
				darkBlue.setAntiAlias(true);
				lightGreen = new Paint();
				lightGreen.setARGB(255, 0, 255, 0);
				lightGreen.setTextSize(11);
				lightGreen.setAntiAlias(true);
				darkGreen = new Paint();
				darkGreen.setARGB(255, 34, 139, 34);
				darkGreen.setTextSize(11);
				darkGreen.setAntiAlias(true);
				grey = new Paint();
				grey.setARGB(255, 139, 137, 137);
				grey.setTextSize(11);
				grey.setAntiAlias(true);
				black = new Paint();
				black.setARGB(255, 0, 0, 0);
				black.setTextSize(11);
				black.setAntiAlias(true);
				yellow = new Paint();
				yellow.setARGB(255, 255, 255, 0);
				yellow.setTextSize(11);
				yellow.setAntiAlias(true);
				orange = new Paint();
				orange.setARGB(255, 255, 140, 0);
				orange.setTextSize(11);
				orange.setAntiAlias(true);
				yellowT = new Paint();
				yellowT.setARGB(100, 255, 255, 0);
				yellowT.setTextSize(11);
				yellowT.setAntiAlias(true);
				blueT = new Paint();
				blueT.setARGB(100, 0, 0, 255);
				blueT.setTextSize(11);
				blueT.setAntiAlias(true);
				orangeT = new Paint();
				orangeT.setARGB(100, 255, 140, 0);
				orangeT.setTextSize(11);
				orangeT.setAntiAlias(true);
				redT = new Paint();
				redT.setARGB(100, 255, 0, 0);
				redT.setTextSize(11);
				redT.setAntiAlias(true);
				purple = new Paint();
				purple.setARGB(255, 85, 26, 139);
				purple.setTextSize(11);
				purple.setAntiAlias(true);
				pink = new Paint();
				pink.setARGB(255, 224, 0, 224);
				pink.setTextSize(11);
				pink.setAntiAlias(true);
				silver1 = new Paint();
				silver1.setARGB(90, 224, 224, 224);
				silver1.setTextSize(11);
				silver1.setAntiAlias(true);
				silver2 = new Paint();
				silver2.setARGB(255, 143, 143, 143);
				silver2.setTextSize(11);
				silver2.setAntiAlias(true);
		
	}
	
	public void initalizeCustomColors(int lightColor,int expc1, int expc2,int expc3,int expc4,int expc5,int expc6,int thrust,int txtc){
		

		// 0 white
		// 1 black
		// 2 red
		// 3 orange
		// 4 yellow
		// 5 lightGreen
		// 6 darkGreen
		// 7 lightBlue
		// 8 darkBlue
		// 9 purple 
		// 10 pink
		
		
		switch(lightColor){
		
		case(0):
			lights=white;
	          break;
		case(1):
			lights=black;
		      break;
		case(2):
			lights=red;
		      break;
		case(3):
			lights=orange;
		      break;
		case(4):
			lights=yellow;
		      break;
		case(5):
			lights=lightGreen;
		      break;
		case(6):
			lights=darkGreen;
		      break;
		case(7):
			lights=lightBlue;
		      break;
		case(8):
			lights=darkBlue;
		      break;
		case(9):
			lights=purple;
		      break;
		case(10):
			lights=pink;
		      break;

		default:
			lights=white;
			break;
		}
	
 switch(expc1){
		
		case(0):
			ep1=white;
	          break;
		case(1):
			ep1=black;
		      break;
		case(2):
			ep1=red;
		      break;
		case(3):
			ep1=orange;
		      break;
		case(4):
			ep1=yellow;
		      break;
		case(5):
			ep1=lightGreen;
		      break;
		case(6):
			ep1=darkGreen;
		      break;
		case(7):
			ep1=lightBlue;
		      break;
		case(8):
			ep1=darkBlue;
		      break;
		case(9):
			ep1=purple;
		      break;
		case(10):
			ep1=pink;
		      break;


		default:
			ep1=white;
			break;
		}

	switch(expc2){
		
		case(0):
			ep2=white;
		      break;
		case(1):
			ep2=black;
		      break;
		case(2):
			ep2=red;
		      break;
		case(3):
			ep2=orange;
		      break;
		case(4):
			ep2=yellow;
		      break;
		case(5):
			ep2=lightGreen;
		      break;
		case(6):
			ep2=darkGreen;
		      break;
		case(7):
			ep2=lightBlue;
		      break;
		case(8):
			ep2=darkBlue;
		      break;
		case(9):
			ep2=purple;
		      break;
		case(10):
			ep2=pink;
		      break;

		
		default:
			ep2=red;
			break;
		}
		
	switch(expc3){
		
		case(0):
			ep3=white;
		      break;
		case(1):
			ep3=black;
		      break;
		case(2):
			ep3=red;
		      break;
		case(3):
			ep3=orange;
		      break;
		case(4):
			ep3=yellow;
		      break;
		case(5):
			ep3=lightGreen;
		      break;
		case(6):
			ep3=darkGreen;
		      break;
		case(7):
			ep3=lightBlue;
		      break;
		case(8):
			ep3=darkBlue;
		      break;
		case(9):
			ep3=purple;
		      break;
		case(10):
			ep3=pink;
		      break;

		
		default:
			ep3=orange;
			break;
		}
	
	switch(expc4){
	
	case(0):
		ep4=white;
	      break;
	case(1):
		ep4=black;
	      break;
	case(2):
		ep4=red;
	      break;
	case(3):
		ep4=orange;
	      break;
	case(4):
		ep4=yellow;
	      break;
	case(5):
		ep4=lightGreen;
	      break;
	case(6):
		ep4=darkGreen;
	      break;
	case(7):
		ep4=lightBlue;
	      break;
	case(8):
		ep4=darkBlue;
	      break;
	case(9):
		ep4=purple;
	      break;
	case(10):
		ep4=pink;
	      break;

	
	default:
		ep4=white;
		break;
	}
	
switch(expc5){
	
	case(0):
		ep5=white;
	      break;
	case(1):
		ep5=black;
	      break;
	case(2):
		ep5=red;
	      break;
	case(3):
		ep5=orange;
	      break;
	case(4):
		ep5=yellow;
	      break;
	case(5):
		ep5=lightGreen;
	      break;
	case(6):
		ep5=darkGreen;
	      break;
	case(7):
		ep5=lightBlue;
	      break;
	case(8):
		ep5=darkBlue;
	      break;
	case(9):
		ep5=purple;
	      break;
	case(10):
		ep5=pink;
	      break;

	
	default:
		ep5=white;
		break;
	}
	
switch(expc6){

case(0):
	ep6=white;
      break;
case(1):
	ep6=black;
      break;
case(2):
	ep6=red;
      break;
case(3):
	ep6=orange;
      break;
case(4):
	ep6=yellow;
      break;
case(5):
	ep6=lightGreen;
      break;
case(6):
	ep6=darkGreen;
      break;
case(7):
	ep6=lightBlue;
      break;
case(8):
	ep6=darkBlue;
      break;
case(9):
	ep6=purple;
      break;
case(10):
	ep6=pink;
      break;


default:
	ep6=white;
	break;
}

switch(thrust){

case(0):
	thrusters=white;
      break;
case(1):
	thrusters=black;
      break;
case(2):
	thrusters=red;
      break;
case(3):
	thrusters=orange;
      break;
case(4):
	thrusters=yellow;
      break;
case(5):
	thrusters=lightGreen;
      break;
case(6):
	thrusters=darkGreen;
      break;
case(7):
	thrusters=lightBlue;
      break;
case(8):
	thrusters=darkBlue;
      break;
case(9):
	thrusters=purple;
      break;
case(10):
	thrusters=pink;
      break;


default:
	thrusters=white;
	break;
}



switch(txtc){

case(0):
	messagePaint=white;
      break;
case(1):
	messagePaint=black;
      break;
case(2):
	messagePaint=red;
      break;
case(3):
	messagePaint=orange;
      break;
case(4):
	messagePaint=yellow;
      break;
case(5):
	messagePaint=lightGreen;
      break;
case(6):
	messagePaint=darkGreen;
      break;
case(7):
	messagePaint=lightBlue;
      break;
case(8):
	messagePaint=darkBlue;
      break;
case(9):
	messagePaint=purple;
      break;
case(10):
	messagePaint=pink;
      break;


default:
	ep4=white;
	break;
}


	
	}
	
}
