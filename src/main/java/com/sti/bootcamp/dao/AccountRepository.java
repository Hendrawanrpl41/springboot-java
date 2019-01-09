package com.sti.bootcamp.dao;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Integer>{
	Account findByAccountNumber(String accountNumber);
	List<Account> findByCustomer(Customer customer);

}
