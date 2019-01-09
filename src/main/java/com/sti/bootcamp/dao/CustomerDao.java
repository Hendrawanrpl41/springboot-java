package com.sti.bootcamp.dao;

import java.util.List;

import com.sti.bootcamp.model.Customer;
import com.sti.bootcamp.view.Excep;

public interface CustomerDao {

	Customer getById(int id)throws Excep;
	Customer save(Customer customer)throws Excep;
	void delete(Customer customer)throws Excep;
	
	List<Customer> getList() throws Excep;
	
	
}
