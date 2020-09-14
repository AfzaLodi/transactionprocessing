package com.example.transactions.processing.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.transactions.processing.model.Merchant;


@Repository
public interface MerchantRepository extends CrudRepository<Merchant,Integer> {

}
