package com.example.transactions.processing.dao;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.transactions.processing.model.Transaction;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction,Integer> {

	List<Transaction> findByCardDetailsCardNumber(int cardNumber);
	
}
