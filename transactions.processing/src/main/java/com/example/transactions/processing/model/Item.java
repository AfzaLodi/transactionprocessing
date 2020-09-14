package com.example.transactions.processing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
	
	@Id
	private int itemID;
	
	private int itemCount;
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@Override
	public String toString() {
		return "Item [itemID=" + itemID + ", itemCount=" + itemCount + "]";
	}
	
}
