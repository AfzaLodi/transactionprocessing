package com.example.transactions.processing.services;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.transactions.processing.dao.ItemRepository;
import com.example.transactions.processing.dao.MerchantRepository;
import com.example.transactions.processing.dao.TransactionRepository;
import com.example.transactions.processing.model.Item;
import com.example.transactions.processing.model.Merchant;
import com.example.transactions.processing.model.Transaction;

@Service
public class TransactionProcessingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProcessingService.class);
	
	@Autowired
	public TransactionRepository transactionRepository;
	
	@Autowired
	public MerchantRepository merchantRepository;
	
	@Autowired
	public ItemRepository itemRepository;
	
	
	public Optional<Transaction> retrieveTransaction(int transactionID) {
		LOGGER.info("retrieveTransaction with ID: "+transactionID);
		Optional<Transaction> transaction = transactionRepository.findById(transactionID);
		return transaction;
	}
	
	public List<Transaction> retrieveAllTransactionsForCard(int cardNumber) {
		LOGGER.info("retrieveAllTransactions for cardNumber: "+cardNumber);
		List<Transaction> transactions = transactionRepository.findByCardDetailsCardNumber(cardNumber);
		return transactions;
	}
	
	public Transaction persistTransaction(Transaction transaction) {
		LOGGER.info("persistTransaction : "+transaction);
		return transactionRepository.save(transaction);		
	}
	
	public Merchant persistMerchant(Merchant merchant) {
		LOGGER.info("persistMerchant : "+merchant);
		return merchantRepository.save(merchant);		
	}
	
	public Item persistItem(Item item) {
		LOGGER.info("persistItem : "+item);
		return itemRepository.save(item);
			
	}
	
	public int checkStockInHand(int merchantID,int itemID) {
		LOGGER.info("checkStockInHand for merchantID: "+merchantID + " and itemID: "+itemID);
		Optional<Merchant> merchant = merchantRepository.findById(merchantID);
				
		List<Item> inventoryItems = merchant.get().getInventoryItemsCatalog();
		for (Item inventoryItem : inventoryItems) {
			if(inventoryItem.getItemID() == itemID) {
				return inventoryItem.getItemCount();
			}
		}
		return 0;		
	}

}
