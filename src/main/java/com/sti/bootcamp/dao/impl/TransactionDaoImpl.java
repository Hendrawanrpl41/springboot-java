package com.sti.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.bootcamp.dao.BaseImpl;
import com.sti.bootcamp.dao.TransactionDao;
import com.sti.bootcamp.dao.TransactionRepository;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;
import com.sti.bootcamp.view.Excep;

public class TransactionDaoImpl extends BaseImpl implements TransactionDao {
	@Autowired
	private TransactionRepository repository;
	
	@Override
	public Transaction getById(int id) throws Excep {
		
		return repository.findOne(Integer.valueOf(id));
	}

	@Override
	public Transaction save(Transaction transaction) throws Excep {
		
		return repository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) throws Excep {
		repository.delete(transaction);
		
		
	}

	@Override
	public List<Transaction> getList() throws Excep {
		CriteriaBuilder inivar = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query =inivar.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		TypedQuery<Transaction> q = em.createQuery(query);
		
		return q.getResultList();
	}
	@Override
	public List<Transaction> getListByAccount(Account account) throws Excep {
		return repository.findByAccount(account);
	}

	

}
