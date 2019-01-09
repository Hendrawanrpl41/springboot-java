package com.sti.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.bootcamp.dao.BaseImpl;
import com.sti.bootcamp.dao.CustomerDao;
import com.sti.bootcamp.dao.CustomerRepository;
import com.sti.bootcamp.model.Customer;
import com.sti.bootcamp.view.Excep;

public class CustomerDaoImpl extends BaseImpl implements CustomerDao  {

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public Customer getById(int id) throws Excep {
		

		return repository.findOne(Integer.valueOf(id));
		
	}

	@Override
	public Customer save(Customer customer) throws Excep {
		
		return repository.save(customer);
	}

	@Override
	public void delete(Customer customer) throws Excep {
		
		repository.delete(customer);
		
	}

	@Override
	public List<Customer> getList() throws Excep {
		CriteriaBuilder inivar = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query =inivar.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root);
		TypedQuery<Customer> q = em.createQuery(query);
		
		return q.getResultList();
	}


}
