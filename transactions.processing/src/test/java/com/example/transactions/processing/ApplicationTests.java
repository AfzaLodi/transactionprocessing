package com.example.transactions.processing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.transactions.processing.dao.ItemRepository;
import com.example.transactions.processing.dao.MerchantRepository;
import com.example.transactions.processing.dao.TransactionRepository;
import com.example.transactions.processing.model.CardDetails;
import com.example.transactions.processing.model.Item;
import com.example.transactions.processing.model.Merchant;
import com.example.transactions.processing.model.Transaction;

@SpringBootTest
class ApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Test
	void contextLoads() {
		
	}
	
	@BeforeEach()
	public void setUp() {
		LOGGER.info("This should get executed before every test");
		
		Item item1 = new Item();
		item1.setItemID(1);
		item1.setItemCount(100);
		
		Item item2 = new Item();
		item2.setItemID(2);
		item2.setItemCount(200);
		
		itemRepository.save(item1);
		itemRepository.save(item2);
		
		List<Item> items = new ArrayList<Item>();
		items.add(item1);
		items.add(item2);
		
		Merchant merchant1 = new Merchant();
		merchant1.setMerchantID(1);
		merchant1.setInventoryItemsCatalog(items);
		
		merchantRepository.save(merchant1);
		
		Transaction transaction = new Transaction();
		transaction.setTransactionID(1);
		transaction.setTransactionAmount(new BigDecimal(100));
		transaction.setTransactionCurrency("AUD");
		transaction.setApiUser("foo");
		transaction.setApiPassword("foo");
		transaction.setCorrelationID(1L);
		transaction.setMerchantID(merchant1.getMerchantID());
		
		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardNumber(1111);
		cardDetails.setCsv(111);
		cardDetails.setExpiryDate(new Date());
		transaction.setCardDetails(cardDetails);
		
		transaction.setInventoryItems(items);
		
		transactionRepository.save(transaction);
	}
	
	
	@Test
    public void whenFindByItemID_thenReturnItem() {
		LOGGER.info("... testByItem ...");
 
        //when
        Optional<Item> itemFound = itemRepository.findById(1);
       
        // then
        assertTrue(itemFound.get().getItemCount() == 100);
        
	}
	
	@Test
    public void whenFindByMerchantID_thenReturnItemsCatalogCount() {
		LOGGER.info("... testByMerchant ...");
 
        //when
        Optional<Merchant> merchantFound = merchantRepository.findById(1);
       
        // then
        assertTrue(merchantFound.get().getInventoryItemsCatalog().size()==2);
        
	}
	
	@Test
    public void whenFindByCardDetails_thenReturnAllTransactions() {
		LOGGER.info("... testByCardDetails ...");
 
        //when
        List<Transaction> transactions = transactionRepository.findByCardDetailsCardNumber(1111);
       
        // then
        assertTrue(transactions.get(0).getTransactionAmount().intValue() == 100);
        assertTrue(transactions.get(0).getTransactionCurrency().equalsIgnoreCase("AUD"));
        
	}

}
