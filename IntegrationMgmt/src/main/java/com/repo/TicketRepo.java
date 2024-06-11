package com.repo;



import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bo.Ticket;

public interface TicketRepo extends JpaRepository<Ticket,Long>{
	Ticket findPassengerByPassengerId(UUID id);
}