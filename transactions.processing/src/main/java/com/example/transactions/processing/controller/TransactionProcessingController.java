/**
 * 
 */
package com.example.transactions.processing.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.transactions.processing.model.Item;
import com.example.transactions.processing.model.Merchant;
import com.example.transactions.processing.model.Transaction;
import com.example.transactions.processing.services.TransactionProcessingService;

/**
 * @author afzal
 *
 */

@RestController
public class TransactionProcessingController {

	@Autowired
	public TransactionProcessingService transactionProcessingService;
	
	@RequestMapping(method = RequestMethod.GET,path = "/transactions/{transactionID}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Transaction> getTransaction(@PathVariable(value = "transactionID") int transactionID) {
		//Receive and retrieve transaction
		Optional<Transaction> transaction = transactionProcessingService.retrieveTransaction(transactionID);
		return transaction;
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/allTransactionsForCard/{cardNumber}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getAllTransactions(@PathVariable(value = "cardNumber") int cardNumber) {
		//Receive and retrieve all transactions for card
		List<Transaction> transactions = transactionProcessingService.retrieveAllTransactionsForCard(cardNumber);
		return transactions;
	}
	
	@RequestMapping(method = RequestMethod.GET,path = "/stocks/checkStockInHand/{merchantID}/{itemID}",produces = MediaType.APPLICATION_JSON_VALUE)
	public int checkStockInHand(@PathVariable(value = "merchantID") int merchantID,@PathVariable(value = "itemID") int itemID) {
		// Check StockInHand
		return transactionProcessingService.checkStockInHand(merchantID,itemID);		
	}
	
	@RequestMapping(method = RequestMethod.POST,path = "/transactions/{transactionID}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction saveTransaction(@PathVariable(value = "transactionID") int transactionID , @RequestBody Transaction transaction) {
		// Persist received transaction
		transactionProcessingService.persistTransaction(transaction);
		return transaction;
	}
	
	@RequestMapping(method = RequestMethod.POST,path = "/merchants/{merchantID}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Merchant saveMerchant(@PathVariable(value = "merchantID") String merchantID , @RequestBody Merchant merchant) {
		// Persist merchant
		return transactionProcessingService.persistMerchant(merchant);
	}
	
	@RequestMapping(method = RequestMethod.POST,path = "/items/{itemID}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Item saveItem(@PathVariable(value = "itemID") String itemID , @RequestBody Item item) {
		// Persist item
		return transactionProcessingService.persistItem(item);
	}
}
