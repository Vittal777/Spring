package com.tktservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.Ticket;
import com.bo.Train;
import com.repo.TicketRepo;
import com.trainservice.ServiceClass;

@Service("tktservice")
public class TktService implements ServiceImpl {

	@Autowired
	TicketRepo repo;
	
	@Autowired
	ServiceClass trainservice;
	
	public String checkSame(String passengerNo) {
		boolean res = trainservice.isTrainNoExists(passengerNo);
		if(res) {
			return "Found";
		}return "Not Found";
	}


	public String generateTicket(Ticket tkt1,String pno,String name) {
		UUID passengerId = UUID.randomUUID();
		tkt1.setPassengerId(passengerId);
		LocalDateTime ldt = LocalDateTime.now();
		LocalDate date = ldt.toLocalDate();
		tkt1.setBookedDate(date);
		tkt1.setPassengerName(name);
		
		boolean res = trainservice.isTrainNoExists(pno);
		if(res){
			Train train = trainservice.findTrainByTrainNo(pno);
			tkt1.setPassenger_Departure(train.getDeparture());
			tkt1.setPassenger_arrival(train.getArrival());
			tkt1.setPassengertrainNo(pno);
			tkt1.setPrice(train.getPrice());
			tkt1.setTime(train.getTravelTime());
			tkt1.setTrainDate(train.getTravelDate());
			repo.save(tkt1);
			return "Passenger Details successfully persisted to DB";
		}
		return "Train Not Found";
		
	}
	public Ticket isPassengerExists(UUID id) {
		Ticket opt = repo.findPassengerByPassengerId(id);
		return opt;
	}
	
	   public String getTicketDetails(UUID passengerId) {
	        Ticket ticket = repo.findPassengerByPassengerId(passengerId);
	        return buildTicketDetails(ticket);
	    }
	
	
    public String buildTicketDetails(Ticket tkkt) {
        StringBuilder sb = new StringBuilder();
        sb.append("INDIAN RAILWAYS\n")
          .append("PASSENGER NAME : ").append(tkkt.getPassengerName()).append("\n")
          .append("PASSENGER UNIQUE ID : ").append(tkkt.getPassengerId()).append("\n")
          .append("PLACE OF DEPARTURE : ").append(tkkt.getPassenger_Departure()).append("\n")
          .append("PLACE OF ARRIVAL : ").append(tkkt.getPassenger_arrival()).append("\n")
          .append("PRICE (incl. GST) : ").append(tkkt.getPrice()).append("\n")
          .append("DATE OF TICKET BOOKED : ").append(tkkt.getBookedDate()).append("\n")
          .append("DATE & TIME OF TRAIN : ").append(tkkt.getTrainDate()).append(" at ").append(tkkt.getTime()).append("\n");
        return sb.toString();
    }
}
