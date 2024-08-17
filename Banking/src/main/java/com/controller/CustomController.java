package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bo.Customer;
import com.service.ServiceClass;

@RestController
public class CustomController {

	@Autowired
	ServiceClass service;
	@PostMapping("/register")
	public String regUser(@RequestBody Customer c) {
		String res = service.reg(c);
		return res;
	}
	@PostMapping("/withdraw/{accNo}/{withdrawAmt}")
	public String withdrawl(@PathVariable String accNo,@PathVariable double withdrawAmt) {
		Customer c = service.getCustomer(accNo);
		if(c==null) {
			return "Account Number is not found or incorrect.";
		}
		String res = service.withdraw(c, withdrawAmt);
		return res;
	}
	@PostMapping("/credit/{accNo}/{creditAmt}")
	public String credit(@PathVariable String accNo,@PathVariable double creditAmt) {
		Customer c = service.getCustomer(accNo);
		if(c==null) {
			return "Account number incorrect or not found.";
		}
		String res = service.credit(c, creditAmt);
		return res;
	}
	@PostMapping("/transfer/{senderNo}/{receiverNo}/{amt}")
	public String transferAmt(@PathVariable String senderNo,@PathVariable String receiverNo,@PathVariable double amt) {
		String res = service.transfer(senderNo, receiverNo, amt);
		return res;
	}
	
	@GetMapping("/check/{accNo}")
	public String checkBal(@PathVariable String accNo) {
		String res = service.checkBalance(accNo);
		return res;
	}
}
