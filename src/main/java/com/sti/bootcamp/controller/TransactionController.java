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
import com.sti.bootcamp.dao.TransactionDao;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;
import com.sti.bootcamp.model.Transaction;
import com.sti.bootcamp.model.dto.AccountDto;
import com.sti.bootcamp.model.dto.CommonResponse;
import com.sti.bootcamp.model.dto.TransactionDto;
import com.sti.bootcamp.view.Excep;

@RestController
//@RequestMapping("/trx")
public class TransactionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TransactionDao transactionDao;
	@Autowired
	private AccountDao accountDao;
	
	@GetMapping(value="/transaction/{id}")
	public CommonResponse getById(@PathVariable("id") String id) throws Excep {
		LOGGER.info("id : {}", id);
		try {
			Transaction transaction = transactionDao.getById(Integer.parseInt(id));
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
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
	
	@PostMapping(value="/transaction")
	public CommonResponse insert(@RequestBody TransactionDto transactionDto) throws Excep {
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
			transaction.setId(0);
			transaction =  transactionDao.save(transaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
		} catch (Excep e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@PutMapping(value="/transaction")
	public CommonResponse update(@RequestBody TransactionDto transactionDto) throws Excep {
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
			transaction.setId(0);
			transaction =  transactionDao.save(transaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
		} catch (Excep e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	@DeleteMapping(value="/transaction/{transaction}")
	public CommonResponse delete(@PathVariable("transaction") Integer id) throws Excep {
		try {
			Transaction checkTransaction = transactionDao.getById(id);
			if(checkTransaction == null) {
				return new CommonResponse("06", "transaction tidak ditemukan");
			}
			transactionDao.delete(checkTransaction);
			return new CommonResponse();
		} catch (Excep e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@GetMapping(value="/transactions")
	public CommonResponse getList(@RequestParam(name="account", defaultValue="") String account) throws Excep{
		try {
			LOGGER.info("account get list, params : {}", account);
			if(!StringUtils.isEmpty(account)) {
				Account checkAccount = accountDao.getById(Integer.parseInt(account));
				if(checkAccount == null) {
					return new CommonResponse("06", "account tidak ditemukan");
				}
				List<Transaction> transactions = transactionDao.getListByAccount(checkAccount);
				return new CommonResponse<List<TransactionDto>>(transactions.stream().map(acc -> modelMapper.map(acc, TransactionDto.class)).collect(Collectors.toList()));
			}else {
				List<Transaction> transactions = transactionDao.getList();
				return new CommonResponse<List<TransactionDto>>(transactions.stream().map(acc -> modelMapper.map(acc, TransactionDto.class)).collect(Collectors.toList()));
			
				
			}
		} catch (Excep e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	
	
	
	
//	//input
//	@PostMapping("/transaction")
//	public Transaction masuk (@RequestBody Transaction transaction ) {
//		try {
//			transactionDao.save(transaction);
//			return transaction;
//		} catch (Exception e) {
//			return null;
//		}
//		
//	}
//
//	//update
//	@PutMapping("/transaction")
//	public Transaction ubahcustomer (@RequestBody Transaction transaction )throws Excep {
//		transactionDao.save(transaction);
//		return transaction;
//	}
//	
//	//delete
//	@DeleteMapping("/transaction")
//	public Transaction delete(@RequestBody Transaction transaction)throws Excep {
//		transactionDao.delete(transaction);
//		return transaction;
//		
//	}
//	
//	//delete
//	@DeleteMapping("/transaction/{id}")
//	public Transaction deleteTransaction(@PathVariable("id") int id)throws Excep {
//		Transaction transaction = new Transaction();
//		transaction.setId(id);
//		transactionDao.delete(transaction);
//		return transaction;
//			
//	}
//	
//	//cari data
//	@GetMapping("/transaction")
//	public int dao(@RequestParam(value = "id",defaultValue = "")String id)throws Excep {
//			Transaction transaction = transactionDao.getById(Integer.valueOf(id));
//			if (transaction==null) {
//				return 0;
//			} else {
//				return transaction.getAmount();
//			}
//	}
//	//list
//	@GetMapping("/transaction/data")
//	public List<Transaction> allData()throws Excep{
//		
//		return transactionDao.getList();
//			
//		
//	}
//	
//	@GetMapping("/transactions")
//	public List<Transaction> getList(@RequestParam(name="account", defaultValue="") String accountNumber) throws Excep{
//		if(!StringUtils.isEmpty(accountNumber)) {
//			Account checkAccount = accountDao.getById(Integer.parseInt(accountNumber));
//			if(checkAccount==null) {
//				throw new Excep("account tidak ditemukan");
//			}
//			return transactionDao.getListByAccount(checkAccount);
//		}else {
//			return transactionDao.getList();
//		}
//	}

}
