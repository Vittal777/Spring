package com.tktservice;

import java.util.UUID;

import com.bo.Ticket;

public interface ServiceImpl {

	public String checkSame(String passengerNo);
	public String generateTicket(Ticket ticket,String pname,String name);
	public Ticket isPassengerExists(UUID id);
	public String buildTicketDetails(Ticket tkkt);
	public String getTicketDetails(UUID id);
}
