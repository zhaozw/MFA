package com.example.listViewComponents;

public class ItemDetails {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getImageID() {
		return imageID;
	}

	public void setImageID(Integer imageID) {
		this.imageID = imageID;
	}

	public void setPurchased(Boolean purchased) {
		this.purchased = purchased;
	}

	public boolean getPurchased() {
		return purchased;
	}

	public void setListPosition(int pos) {
		listPosition = pos;
	}

	public int getListPosition() {
		return listPosition;
	}

	private boolean purchased;
	private String name;
	private String itemDescription;
	private String price;
	private Integer imageID;
	private int listPosition;

}
