package com.example.objects;

import android.graphics.Paint;

public class ManyPaints {

	public static Paint textPaint, WPaint, redPaint, bluePaint, greenPaint,
			yellowPaint, greyPaint, blackPaint, orangePaint, s1, s2;

	//

	public ManyPaints() {
		redPaint = new Paint();
		redPaint.setARGB(255, 255, 0, 0);
		redPaint.setTextSize(11);
		redPaint.setAntiAlias(true);
		bluePaint = new Paint();
		bluePaint.setARGB(255, 0, 0, 255);
		bluePaint.setTextSize(11);
		bluePaint.setAntiAlias(true);
		greenPaint = new Paint();
		greenPaint.setARGB(255, 34, 139, 34);
		greenPaint.setTextSize(11);
		greenPaint.setAntiAlias(true);
		greyPaint = new Paint();
		greyPaint.setARGB(255, 139, 137, 137);
		greyPaint.setTextSize(11);
		greyPaint.setAntiAlias(true);
		blackPaint = new Paint();
		blackPaint.setARGB(255, 0, 0, 0);
		blackPaint.setTextSize(11);
		blackPaint.setAntiAlias(true);
		yellowPaint = new Paint();
		yellowPaint.setARGB(255, 255, 255, 0);
		yellowPaint.setTextSize(11);
		yellowPaint.setAntiAlias(true);
		orangePaint = new Paint();
		orangePaint.setARGB(255, 255, 140, 0);
		orangePaint.setTextSize(11);
		orangePaint.setAntiAlias(true);
		s1 = new Paint();
		s1.setARGB(90, 224, 224, 224);
		s1.setTextSize(11);
		s1.setAntiAlias(true);
		s2 = new Paint();
		s2.setARGB(255, 143, 143, 143);
		s2.setTextSize(11);
		s2.setAntiAlias(true);
	}
}
