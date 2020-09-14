package com.example.transactions.processing.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;

@Entity
public class Transaction {
	
	@NotNull
	private long correlationID;
	
	@NotNull
	private int merchantID;
	
	@NotNull
	@Value("${spring.security.user.name}")
	private String apiUser;
	
	@NotNull
	@Value("${spring.security.user.password}")
	private String apiPassword;
	
	@NotNull
	private BigDecimal transactionAmount;
	@NotNull
	private String transactionCurrency;
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionID;
	
	@NotNull
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "cardNumber", column = @Column(name = "cardNumber")),
		  @AttributeOverride( name = "expiryDate", column = @Column(name = "expiryDate")),
		  @AttributeOverride( name = "csv", column = @Column(name = "csv"))
		})
	private CardDetails cardDetails;
	
	@OneToMany(targetEntity = Item.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "transaction_item_fk" , referencedColumnName = "transactionID")
	
	@Size(min=1)
	private List<Item> inventoryItems;
	
	public long getCorrelationID() {
		return correlationID;
	}
	public void setCorrelationID(long correlationID) {
		this.correlationID = correlationID;
	}
	public int getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(int merchantID) {
		this.merchantID = merchantID;
	}
	public String getApiUser() {
		return apiUser;
	}
	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}
	public String getApiPassword() {
		return apiPassword;
	}
	public void setApiPassword(String apiPassword) {
		this.apiPassword = apiPassword;
	}
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionCurrency() {
		return transactionCurrency;
	}
	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public CardDetails getCardDetails() {
		return cardDetails;
	}
	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}
	public List<Item> getInventoryItems() {
		return inventoryItems;
	}
	public void setInventoryItems(List<Item> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}
	
	@Override
	public String toString() {
		return "Transaction [correlationID=" + correlationID + ", merchantID=" + merchantID + ", apiUser=" + apiUser
				+ ", apiPassword=" + apiPassword + ", transactionAmount=" + transactionAmount + ", transactionCurrency="
				+ transactionCurrency + ", transactionID=" + transactionID + ", cardDetails=" + cardDetails
				+ ", inventoryItems=" + inventoryItems + "]";
	}
}
