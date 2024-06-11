package com.bo;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Train {
	@Id
	String trainNo;
	String departure;
	String arrival;
	String trainType;
	LocalDate travelDate;
	LocalTime travelTime;
	double price;
	
	public boolean isMatching(String from,String to) {
		return this.departure.equalsIgnoreCase(from) && this.arrival.equalsIgnoreCase(to);
	}
	
}