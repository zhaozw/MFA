package com.example.HitsObjects;

import com.example.objects.NamesAndMessages;

public class HitsInfo {

	public int activationWave;
	public String message = "null", name = "null", HitID;
	public boolean active = false, failed = false, succeeded = false;
	public int hitType = -1;
	public boolean entered = false;

	public HitsInfo() {

	}

	public HitsInfo(int type) {
		hitType = type;
	}

	public HitsInfo(int type, boolean random) {
		hitType = type;
		if (random) {
			message = NamesAndMessages.randomMessage();
			name = NamesAndMessages.randomName();
		}
	}

}