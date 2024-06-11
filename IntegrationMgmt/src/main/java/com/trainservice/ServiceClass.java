package com.trainservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.Train;
import com.exception.TrainNotFoundException;
import com.repo.TrainRepo;

@Service("service")
public class ServiceClass implements ServiceImpl {

	@Autowired
	TrainRepo repo;
	
	public String registerTrain(Train train) {
		repo.save(train);
		return "Train registered successfully";
	}


	public String registerAllTrains(List<Train> train) {
		repo.saveAll(train);
		return "All Trains Data got stored successfully";
	}


	@Override
	public String deleteBytrainNo(String trainNo) {
		Optional<Train> opt = repo.findById(trainNo);
		if(opt.isPresent()) {
			repo.delete(opt.get());
			return "Data with Train number "+trainNo+" removed successfully";
		}else {
			throw new TrainNotFoundException("Train data not found or already got removed.");
		}
	}

	public Train findTrainByTrainNo(String trainNo) {
	    Optional<Train> opt = repo.findByTrainNo(trainNo);
	    return opt.orElse(null);
	}
    public List<String> getAllTrainNos() {
        List<Train> trains = repo.findAll();
        return trains.stream()
                .map(Train::getTrainNo)
                .collect(Collectors.toList());
    }
    public boolean isTrainNoExists(String trainNo) {
        List<String> allTrainNos = getAllTrainNos();
        return allTrainNos.contains(trainNo);
    }
}
