package com.service;

import com.bo.Customer;

public interface IService {
	public String reg(Customer c);
	public String withdraw(Customer c,double withdrawAmt);
	public String credit(Customer c,double creditAmt);
	public String transfer(String senderAccNo,String receiverAccNo,double amt);
	public String checkBalance(String accNo);
}
