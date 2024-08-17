package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bo.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer>{
	public Customer getCustomerByAccNo(String AccNo);
}
