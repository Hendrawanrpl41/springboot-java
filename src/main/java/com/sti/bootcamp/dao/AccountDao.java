package com.sti.bootcamp.dao;

import java.util.List;

import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;
import com.sti.bootcamp.view.Excep;

public interface AccountDao {

	Account getById(int id)throws Excep;
	Account save(Account account)throws Excep;
	void delete(Account account)throws Excep;
	
	List<Account> getList() throws Excep;
	List<Account> getListByCustomer(Customer customer) throws Excep;
}
