package com.magicmetro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicmetro.entity.TrainStation;
import com.magicmetro.persistence.TrainStationDao;

@Service
public class TrainStationServiceImpl implements TrainStationService {

    @Autowired
    private TrainStationDao trainStationDao;


    @Override
    public TrainStation searchTrainStationById(int id) {
        return trainStationDao.searchTrainStationById(id);
    }

}
