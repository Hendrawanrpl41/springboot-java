package com.sti.bootcamp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.bootcamp.dao.AccountDao;
import com.sti.bootcamp.dao.CustomerDao;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;
import com.sti.bootcamp.model.dto.CommonResponse;
import com.sti.bootcamp.model.dto.CustomerDto;
import com.sti.bootcamp.model.dto.AccountDto;
import com.sti.bootcamp.view.Excep;


@RestController
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping(value="account/{accountNumber}")
	public CommonResponse getById(@PathVariable("accountNumber") String accountNumber) throws Excep {
		LOGGER.info("accountNumber : {}", accountNumber);
		try {
			Account account = accountDao.getById(Integer.parseInt(accountNumber));
			
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (Excep e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	@PostMapping(value="/account")
	public CommonResponse insert(@RequestBody AccountDto accountDto) throws Excep {
		try {
			Account account = modelMapper.map(accountDto, Account.class);
			account.setAccountNumber(0);
			account =  accountDao.save(account);
			
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (Excep e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@PutMapping(value="/account")
	public CommonResponse update(@RequestBody AccountDto accountDto) throws Excep {
		try {
			Account account = modelMapper.map(accountDto, Account.class);
			account =  accountDao.save(account);
			
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (Excep e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	
	//
	@DeleteMapping(value="/account/{account}")
	public CommonResponse delete(@PathVariable("account") Integer accountNumber) throws Excep {
		try {
			Account checkAccount = accountDao.getById(accountNumber);
			if(checkAccount == null) {
				return new CommonResponse("06", "account tidak ditemukan");
			}
			accountDao.delete(checkAccount);
			return new CommonResponse();
		} catch (Excep e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@GetMapping(value="/accounts")
	public CommonResponse getList(@RequestParam(name="customer", defaultValue="") String customer) throws Excep{
		try {
			LOGGER.info("account get list, params : {}", customer);
			if(!StringUtils.isEmpty(customer)) {
				Customer checkCustomer = customerDao.getById(Integer.parseInt(customer));
				if(checkCustomer == null) {
					return new CommonResponse("06", "customer tidak ditemukan");
				}
				List<Account> accounts = accountDao.getListByCustomer(checkCustomer);
				return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc -> modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
			}else {
				List<Account> accounts = accountDao.getList();
				return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc -> modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
			
				
			}
			
		
			//return new CommonResponse<List<AccountDto>>(accounts.stream().map(cust -> modelMapper.map(cust, AccountDto.class)).collect(Collectors.toList()));
		} catch (Excep e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	

}
