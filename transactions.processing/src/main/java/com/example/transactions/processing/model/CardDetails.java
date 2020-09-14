package com.example.transactions.processing.model;

import java.util.Date;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class CardDetails {
	
	@NotNull
	@Size(min=16, max=16)
	private int cardNumber;
	
	@NotNull
	private Date expiryDate;
	
	@NotNull
	@Size(min=3, max=3)
	private int  csv;
	
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getCsv() {
		return csv;
	}
	public void setCsv(int csv) {
		this.csv = csv;
	}
	@Override
	public String toString() {
		return "CardDetails [cardNumber=" + cardNumber + ", expiryDate=" + expiryDate + ", csv=" + csv + "]";
	}
}
