package com.trainservice;

import java.util.List;

import com.bo.Train;

public interface ServiceImpl {
	public String registerTrain(Train train);
	public String registerAllTrains(List<Train> train);
	public String deleteBytrainNo(String trainNo);
	Train findTrainByTrainNo(String trainNo);
	public List<String> getAllTrainNos();
	public boolean isTrainNoExists(String trainNo);
}
