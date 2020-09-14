package com.example.transactions.processing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Merchant {
	@Id
	@GeneratedValue
	private int merchantID;
	
	@OneToMany(targetEntity = Item.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_item_fk" , referencedColumnName = "merchantID")
	private List<Item> inventoryItemsCatalog;

	public int getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(int merchantID) {
		this.merchantID = merchantID;
	}

	public List<Item> getInventoryItemsCatalog() {
		return inventoryItemsCatalog;
	}

	public void setInventoryItemsCatalog(List<Item> inventoryItemsCatalog) {
		this.inventoryItemsCatalog = inventoryItemsCatalog;
	}

	@Override
	public String toString() {
		return "Merchant [merchantID=" + merchantID + ", inventoryItemsCatalog=" + inventoryItemsCatalog + "]";
	}
}
