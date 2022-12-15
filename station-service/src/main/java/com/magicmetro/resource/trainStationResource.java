package com.magicmetro.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.magicmetro.entity.TrainStation;
import com.magicmetro.service.TrainStationService;

@RestController
public class TrainStationResource {
	

	@Autowired
	private TrainStationService trainStationService;
	
	
	@GetMapping(path = "/stations/", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<TrainStation> listAllTrainStationsResource(){
		return trainStationService.listAllTrainStations();
	}
	
	
	  @GetMapping(path = "/stations/{sid}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public TrainStation searchTrainStationByIdResource(@PathVariable("sid") int id) {
	        return trainStationService.searchTrainStationById(id);

	    }


}
