package com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bo.Train;

public interface TrainRepo extends JpaRepository<Train,String>{

	public Train findTrainByDepartureAndArrival(String depart,String arrive);
	public Optional<Train> findByTrainNo(String trainNo);
}
