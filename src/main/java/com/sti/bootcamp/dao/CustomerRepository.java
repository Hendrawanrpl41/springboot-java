package com.sti.bootcamp.dao;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sti.bootcamp.model.Customer;



@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{

}
