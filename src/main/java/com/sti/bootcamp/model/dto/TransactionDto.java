package com.sti.bootcamp.model.dto;


import com.sti.bootcamp.model.Account;

public class TransactionDto {
	private int id;
	private String type;
	private int amount;
	private String amountSign;
	private Account account;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAmountSign() {
		return amountSign;
	}
	public void setAmountSign(String amountSign) {
		this.amountSign = amountSign;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
