package com.example.objects;

//this class is to be used to make multiple lights to move quickly 
//across the screen to create the appearence of motion
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.mfa.gamepanel.MGP;

public class FireBall {
	Random generator = new Random();
	// width of Explosion
	int WIDTH;
	// height of Explosion
	int HEIGHT;
	// X coordinate
	int X;
	// Y coordinate
	int Y;
	int rSize, r;
	int type, dir, d;
	// 1 for small, 2 for big, 3 for explosion lights
	boolean explode = false;
	// public boolean thrust = false;
	boolean nuke;

	public int color = 1;
	// 1 = red 2 = orange 3 = magenta 4 = yellow 5 = white

	RectF shape = new RectF(X, Y, X + (int) MGP.dp[100], Y + (int) MGP.dp[100]);

	public FireBall(int t, int dr) {
		WIDTH = 0;
		HEIGHT = 0;
		rSize = generator.nextInt(10);
		dir = dr;
		type = t;
		X = 250;// (250) + rSize;
		Y = 250;// (250) + rSize;
		explode = false;
		shape.set(X, Y, X + HEIGHT, Y + WIDTH);

	}

	public FireBall() {
		WIDTH = 0;
		HEIGHT = 0;
		rSize = generator.nextInt(30);
		type = 1;
		X = 250;// (250) + rSize;
		Y = 250;// (250) + rSize;
		explode = false;
	}

	public void ThrustersGo(double x, double y) {
		if (WIDTH < MGP.dp[10]) {
			WIDTH = (int) MGP.dp[10];
			HEIGHT = (int) MGP.dp[10];
		}

		if (X > x - MGP.dp[35]) {
			color = 1;
			Y -= generator.nextInt((int) MGP.dp[2]);
			Y += generator.nextInt((int) MGP.dp[2]);
		} else if (X > x - 38) {
			color = 2;
			WIDTH -= generator.nextInt((int) MGP.dp[2]);
			WIDTH += generator.nextInt((int) MGP.dp[5]);
			HEIGHT -= generator.nextInt((int) MGP.dp[2]);
			HEIGHT += generator.nextInt((int) MGP.dp[2]);
			Y -= generator.nextInt((int) MGP.dp[3]);
			Y += generator.nextInt((int) MGP.dp[3]);
		} else if (X > x - 50) {
			color = 3;
			WIDTH -= generator.nextInt((int) MGP.dp[2]);
			WIDTH += generator.nextInt((int) MGP.dp[5]);
			HEIGHT -= generator.nextInt((int) MGP.dp[2]);
			HEIGHT += generator.nextInt((int) MGP.dp[2]);
			Y -= generator.nextInt((int) MGP.dp[5]);
			Y += generator.nextInt((int) MGP.dp[5]);
		} else if (X > x - 58) {
			color = 4;
			WIDTH -= generator.nextInt((int) MGP.dp[2]);
			WIDTH += generator.nextInt((int) MGP.dp[5]);
			HEIGHT -= generator.nextInt((int) MGP.dp[2]);
			HEIGHT += generator.nextInt((int) MGP.dp[2]);
			Y -= generator.nextInt((int) MGP.dp[5]);
			Y += generator.nextInt((int) MGP.dp[5]);
		} else {
			color = 5;
			WIDTH -= generator.nextInt((int) MGP.dp[2]);
			WIDTH += generator.nextInt((int) MGP.dp[5]);
			HEIGHT -= generator.nextInt((int) MGP.dp[2]);
			HEIGHT += generator.nextInt((int) MGP.dp[2]);
			Y -= generator.nextInt((int) MGP.dp[5]);
			Y += generator.nextInt((int) MGP.dp[5]);
		}

		X -= generator.nextInt((int) MGP.dp[17]);

		if (WIDTH > MGP.dp[27] || HEIGHT > MGP.dp[27] || X < 0
				|| X - MGP.dp[10] > x) {
			HEIGHT = 0;
			WIDTH = 0;
			X = (int) x;
			Y = (int) y - ((int) MGP.dp[5]);
			X -= MGP.dp[24];
		}
		shape.set(X, Y, X + WIDTH, Y + HEIGHT);
	}

	public void drawThrusters(Canvas canvas) {
		if (color == 1)
			canvas.drawOval(shape, Paints.darkBlue);
		else if (color == 2)
			canvas.drawOval(shape, Paints.red);
		else if (color == 3)
			canvas.drawOval(shape, Paints.orange);
		else if (color == 4)
			canvas.drawOval(shape, Paints.yellow);
		else if (color == 5)
			canvas.drawOval(shape, Paints.thrusters);
	}

	public void drawExplosionsCustom(Canvas canvas) {
		if (type != 3) {
			if (color == 1)
				canvas.drawOval(shape, Paints.ep1);
			else if (color == 2)
				canvas.drawOval(shape, Paints.ep2);
			else if (color == 3)
				canvas.drawOval(shape, Paints.ep3);
			else if (color == 4)
				canvas.drawOval(shape, Paints.ep4);
			else if (color == 5)
				canvas.drawOval(shape, Paints.ep5);
		} else
			canvas.drawOval(shape, Paints.ep6);
	}

	public void ExplodeTrue(double x, double y) {
		explode = true;
		X = (int) x;
		Y = (int) y;
	}

	public boolean GetExplodeStatus() {
		return explode;
	}

	public void Explode() {
		if (nuke == true) {
			ExplodeNuke();
		} else {
			switch (type) {
			case (1):
				ExplodeHuge();
				break;
			case (2):
				ExplodeSmall();
				break;
			case (3):
				ExplodeLights();
				break;
			}
		}
	}

	public void ExplodeNuke() {
		if (explode) {
			// WIDTH -=5; //generator.nextInt(2) ;
			WIDTH += MGP.dp[20]; // generator.nextInt(5) ;
			// HEIGHT -=5; //generator.nextInt(2) ;
			HEIGHT += MGP.dp[20]; // generator.nextInt(2) ;
			color = generator.nextInt(5) + 1;
			X -= generator.nextInt((int) MGP.dp[150]);
			Y -= generator.nextInt((int) MGP.dp[150]);
			X += generator.nextInt((int) MGP.dp[150]);
			Y += generator.nextInt((int) MGP.dp[150]);
			X -= MGP.dp[8];
			Y -= MGP.dp[8];

			if (HEIGHT > MGP.deviceHeight && WIDTH > MGP.deviceWidth) {
				explode = false;
				HEIGHT = 0;
				WIDTH = 0;
				nuke = false;
			}
		}
		shape.set(X, Y, X + HEIGHT, Y + WIDTH);
	}

	// public void ExplodeLights(double x, double y)
	public void ExplodeLights() {
		if (explode) {
			HEIGHT = (int) MGP.dp[7];
			WIDTH = (int) MGP.dp[7];
			switch (dir) {
			case (1):
				X += MGP.dp[10];
				break;
			case (2):
				X -= MGP.dp[10];
				break;
			case (3):
				Y += MGP.dp[10];
				break;
			case (4):
				Y -= MGP.dp[10];
				break;
			case (5):
				X += MGP.dp[10];
				Y += MGP.dp[15];
				break;
			case (6):
				X += MGP.dp[10];
				Y -= MGP.dp[15];
				break;
			case (7):
				X -= MGP.dp[10];
				Y += MGP.dp[15];
				break;
			case (8):
				X -= MGP.dp[10];
				Y -= MGP.dp[15];
				break;
			}
			if (X < 0 || Y < 0 || Y > MGP.deviceHeight || X > MGP.deviceWidth) {
				explode = false;
				HEIGHT = 0;
				WIDTH = 0;
			}
		}
		shape.set(X, Y, X + HEIGHT, Y + WIDTH);
	}

	// public void ExplodeHuge(double x, double y)
	public void ExplodeHuge() {
		if (explode) {
			// WIDTH -=5; //generator.nextInt(2) ;
			WIDTH += MGP.dp[7]; // generator.nextInt(5) ;
			// HEIGHT -=5; //generator.nextInt(2) ;
			HEIGHT += MGP.dp[7]; // generator.nextInt(2) ;
			color = generator.nextInt(5) + 1;
			X -= generator.nextInt((int) MGP.dp[15]);
			Y -= generator.nextInt((int) MGP.dp[15]);
			X += generator.nextInt((int) MGP.dp[15]);
			Y += generator.nextInt((int) MGP.dp[15]);
			X -= MGP.dp[4];
			Y -= MGP.dp[4];
			if (HEIGHT > MGP.dp[90]) {
				explode = false;
				HEIGHT = 0;
				WIDTH = 0;
			}
		}
		shape.set(X, Y, X + HEIGHT, Y + WIDTH);
	}

	public void ExplodeSmall() {
		if (explode) {
			X -= MGP.dp[3];
			Y -= MGP.dp[3];
			// WIDTH -=5; //generator.nextInt(2) ;
			WIDTH += MGP.dp[4]; // generator.nextInt(5) ;
			// HEIGHT -=5; //generator.nextInt(2) ;
			HEIGHT += MGP.dp[4]; // generator.nextInt(2) ;
			color = generator.nextInt(5) + 1;
			X -= generator.nextInt((int) MGP.dp[20]);
			Y -= generator.nextInt((int) MGP.dp[20]);
			X += generator.nextInt((int) MGP.dp[20]);
			Y += generator.nextInt((int) MGP.dp[20]);
			// X -= 1;
			// Y -= 1;
			if (HEIGHT > MGP.dp[50]) {
				explode = false;
				HEIGHT = 0;
				WIDTH = 0;
			}

		}
		shape.set(X, Y, X + HEIGHT, Y + WIDTH);
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void SetX(double x) {
		X = (int) x;
	}

	public void SetY(double y) {
		Y = (int) y;
	}

	public void Reset() { // AsteroidsGame.explodeGo=false;
		explode = false;
		WIDTH = 0;
		HEIGHT = 0;
		shape.set(X, Y, X + HEIGHT, Y + WIDTH);
	}
}
