package com.sti.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.bootcamp.dao.AccountDao;
import com.sti.bootcamp.dao.AccountRepository;
import com.sti.bootcamp.dao.BaseImpl;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;
import com.sti.bootcamp.view.Excep;

public class AccountDaoImpl extends BaseImpl implements AccountDao {

	@Autowired
	private AccountRepository repository;
	
	@Override
	public Account getById(int id) throws Excep {
		
		return repository.findOne(Integer.valueOf(id));
	}

	@Override
	public Account save(Account account) throws Excep {
		
		return repository.save(account);
	}

	@Override
	public void delete(Account account) throws Excep {
		
		repository.delete(account);
	}

	@Override
	public List<Account> getList() throws Excep {
		CriteriaBuilder inivar = em.getCriteriaBuilder();
		CriteriaQuery<Account> query =inivar.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root);
		TypedQuery<Account> q = em.createQuery(query);
		return q.getResultList();
	}
	
	@Override
	public List<Account> getListByCustomer(Customer customer) throws Excep {
		return repository.findByCustomer(customer);
	}


}
