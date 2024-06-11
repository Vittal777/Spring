package com.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long slno;
	UUID passengerId;
	String passengertrainNo;
	String passengerName;
	String passenger_Departure;
	String passenger_arrival;
	LocalDate bookedDate;
	LocalDate trainDate;
	double price;
	LocalTime time;
	
}
