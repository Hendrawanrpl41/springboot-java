package com.sti.bootcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name  = "id")
	private int id;
	@Column(name = "type")
	private String type;
	@Column(name = "amount")
	private int amount;
	@Column(name = "amountsign")
	private String amountSign;
	@ManyToOne
	@JoinColumn(name = "accountnumber")
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
