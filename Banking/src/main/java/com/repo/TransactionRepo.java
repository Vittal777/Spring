package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bo.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,Integer>{

}
