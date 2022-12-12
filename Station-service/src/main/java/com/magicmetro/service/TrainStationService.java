package com.magicmetro.service;

import java.util.List;

import com.magicmetro.entity.TrainStation;

public interface TrainStationService {
	
	List<TrainStation> listAllTrainStations();
	
	TrainStation searchTrainStationById(int id);
	
}