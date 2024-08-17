package com.bo;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;


	    @Column(name = "sender_accNo")
	    private String sender;

	    @Column(name = "receiver_accNo")
	    private String receiver;

	    private double amount;
	    private String transactionType; // e.g., "transfer", "withdrawal", "credit"
	    private LocalDateTime transactionDate;
}
