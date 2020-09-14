package com.example.transactions.processing.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.transactions.processing.model.Item;


public interface ItemRepository extends CrudRepository<Item,Integer> {

}
