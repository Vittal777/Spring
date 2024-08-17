package com.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.Customer;
import com.bo.Transaction;
import com.repo.CustomerRepo;
import com.repo.TransactionRepo;

@Service("service")
public class ServiceClass implements IService {
	
	@Autowired
	CustomerRepo repo;
	@Autowired
	TransactionRepo trepo;
	@Override
	public String reg(Customer c) {
		repo.save(c);
		return "Customer registered succesfully with the account number : "+c.getAccNo();
	}
	@Override
	public String withdraw(Customer c,double withdrawAmt) {
		double amt=0;
		if(withdrawAmt>c.getBalance()) {
			return "Insufficient Balance";
		}
		else {
			amt=c.getBalance()-withdrawAmt;
			c.setBalance(amt);
			repo.save(c);
		}
		Transaction transaction = new Transaction();
		transaction.setAmount(withdrawAmt);
		transaction.setSender(c.getAccNo());
		transaction.setReceiver(null);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType("WithDraw");
		trepo.save(transaction);
		return "Rs. "+withdrawAmt+"has been withdraw from your account "+c.getAccNo();
	}
	@Override
	public String credit(Customer c, double creditAmt) {
		double afterCredit = c.getBalance()+creditAmt;
		c.setBalance(afterCredit);
		repo.save(c);
		Transaction transaction = new Transaction();
		transaction.setAmount(creditAmt);
		transaction.setSender("Self");
		transaction.setReceiver(c.getAccNo());
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType("Credit");
		trepo.save(transaction);
		return "Rs. "+creditAmt+" has been credited to your account "+c.getAccNo()+". Now, your balance is "+afterCredit;
	}
	public Customer getCustomer(String accNo) {
		return repo.getCustomerByAccNo(accNo);
	}
	
	public String transfer(String senderAccNo,String receiverAccNo,double amt) {
		Customer sender = repo.getCustomerByAccNo(senderAccNo);
		Customer receiver = repo.getCustomerByAccNo(receiverAccNo);
		if(sender==null) {
			return "Sender's account number not found, try again.";
		}
		if(receiver==null) {
			return "Receiver's account number not found, try again.";
		}
		if(sender.getBalance()<amt) {
			return "Insufficient amount in sender's account.";
		}
			sender.setBalance(sender.getBalance()-amt);
			receiver.setBalance(receiver.getBalance()+amt);
			repo.save(sender);
			repo.save(receiver);
			Transaction transaction = new Transaction();
			transaction.setAmount(amt);
			transaction.setReceiver(receiverAccNo);
			transaction.setSender(senderAccNo);
			transaction.setTransactionType("Transfer");
			transaction.setTransactionDate(LocalDateTime.now());
			trepo.save(transaction);
		return "Rs. "+amt+" has been transferred from account "+senderAccNo+" to "+receiverAccNo;
		
	}
	
	public String checkBalance(String accNo) {
		Customer c = repo.getCustomerByAccNo(accNo);
		if(c==null) {
			return "Account Number not found.";
		}
		return "Current Balance : "+c.getBalance();
	}
	

}
