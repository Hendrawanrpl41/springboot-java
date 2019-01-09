package com.sti.bootcamp.dao;

import java.util.List;

import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;
import com.sti.bootcamp.view.Excep;

public interface TransactionDao {

	Transaction getById(int id)throws Excep;
	Transaction save(Transaction transaction)throws Excep;
	void delete(Transaction transaction)throws Excep;
	
	List<Transaction> getList() throws Excep;
	List<Transaction> getListByAccount(Account account) throws Excep;
}
