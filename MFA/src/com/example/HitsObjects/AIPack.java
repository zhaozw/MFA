package com.example.HitsObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.objects.Player;

public class AIPack {

	public AI[] aiPack;
	public int topTotal, bottomTotal, topLast, bottomLast, front,
			patternTimer = 600, position = 0;
	public boolean failed;

	public AIPack() {
	}

	public AIPack(int amount, Bitmap bitmap, Bitmap sBitmap) {

		aiPack = new AI[amount];

		for (int k = 0; k < aiPack.length; k++) {
			aiPack[k] = new AI(bitmap, sBitmap);
		}

		front = 0;
		for (int k = 0; k < aiPack.length; k++) {
			if (k == 0)
				aiPack[k].following = aiPack[0];
			if (k == 1 || k == 2)
				aiPack[k].following = aiPack[0];
			else if (k > 2) {
				aiPack[k].following = aiPack[k - 2];

			}

			if (k % 2 == 0 && k != 0)
				aiPack[k].setFollowVariables(0, 0, 0, 0);
			if (k % 2 == 1)
				aiPack[k].setFollowVariables(0, 0, 0, 0);
		}

	}

	public void draw(Canvas canvas) {
		for (int k = 0; k < aiPack.length; k++) {
			if (aiPack[k].dead == false)
				aiPack[k].draw(canvas);

		}

	}

	public void move(Player ship) {

		for (int k = 0; k < aiPack.length; k++) {
			if (aiPack[k].dead == false && k == front)
				aiPack[k].move(ship.x, ship.y);
			else if (aiPack[k].dead == false)
				aiPack[k].moveVariables();

			aiPack[k].moveShots();
		}
		patternTimer--;
		if (patternTimer < 0) {
			patternTimer = 700;
			changePosition();
		}
	}

	private void changePosition() {

		position++;
		if (position == 5)
			position = 1;

		switch (position) {
		case (1):
			for (int k = 0; k < aiPack.length; k++) {
				if (k % 2 == 0 && k != 0)
					aiPack[k].setFollowVariables(-75, -20, 50, -30);
				if (k % 2 == 1)
					aiPack[k].setFollowVariables(-75, 20, 50, 30);
			}
			break;
		case (2):
			for (int k = 0; k < aiPack.length; k++) {
				if (k % 2 == 0 && k != 0)
					aiPack[k].setFollowVariables(-75, 0, 50, 10);
				if (k % 2 == 1)
					aiPack[k].setFollowVariables(-75, 0, 50, 10);
			}
			break;
		case (3):
			for (int k = 0; k < aiPack.length; k++) {
				if (k % 2 == 0 && k != 0)
					aiPack[k].setFollowVariables(50, -20, 50, -30);
				if (k % 2 == 1)
					aiPack[k].setFollowVariables(50, 20, 50, 30);
			}
			break;
		case (4):
			for (int k = 0; k < aiPack.length; k++) {
				if (k % 2 == 0 && k != 0)
					aiPack[k].setFollowVariables(0, -20, 0, -30);
				if (k % 2 == 1)
					aiPack[k].setFollowVariables(0, 20, 0, 30);
			}
			break;

		}
	}

	public void updateFollowers() {
		for (int k = 0; k < aiPack.length; k++) {
			if (aiPack[k].following == aiPack[front]
					&& aiPack[k].following.dead == true) {
				for (int j = 0; j < aiPack.length; j++) {
					if (aiPack[j].following == aiPack[front]
							&& aiPack[j].following.dead == true && j != k)
						aiPack[j].following = aiPack[k];
				}
				front = k;
			} else if (aiPack[k].following.dead == true)
				aiPack[k].following = aiPack[k].following.following;
		}
	}

	public void nextFollower(AI ai) {

		if (ai.following.dead == true)

			nextFollower(ai);

	}
	
   public void checkForFailure(){
	            failed=true;
		for (int k = 0; k < aiPack.length; k++) {
			if (aiPack[k].dead==false)
				failed=false;
         }
   }
	// public void move(Player ship){
	//
	// for(int k=0;k<aiPack.length;k++){
	// if(k==front)
	// aiPack[k].move(ship.x, ship.y);
	// else if(k%2==0)
	// aiPack[k].followBelow(aiPack[aiPack[k].following].x,
	// aiPack[aiPack[k].following].y);
	// else if(k%2==1)
	// aiPack[k].followAbove(aiPack[aiPack[k].following].x,
	// aiPack[aiPack[k].following].y);
	//
	//
	// aiPack[k].moveShots();
	// }
	// }

}
