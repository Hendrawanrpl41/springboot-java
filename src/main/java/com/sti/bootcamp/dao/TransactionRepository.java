package com.sti.bootcamp.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {
	Transaction findById(int id);
	List<Transaction> findByAccount(Account account);


}
