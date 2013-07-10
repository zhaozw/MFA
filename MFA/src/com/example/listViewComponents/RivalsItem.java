package com.example.listViewComponents;

import android.widget.Button;

public class RivalsItem {

	private String userName;
	private String hitId;
	private String hitSentDate;
	private Button userStatsButton;

	public RivalsItem() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Button getUserStatsButton() {
		return userStatsButton;
	}

	public void setUserStatsButton(Button userStatsButton) {
		this.userStatsButton = userStatsButton;
	}

	public String getHitSentDate() {
		return hitSentDate;
	}

	public void setHitSentDate(String hitSentDate) {
		this.hitSentDate = hitSentDate;
	}

	public String gethitId() {
		return hitId;
	}

	public void sethitId(String hitId) {
		this.hitId = hitId;
	}

}
