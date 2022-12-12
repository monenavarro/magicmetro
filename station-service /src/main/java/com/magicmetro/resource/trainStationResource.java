package com.magicmetro.resource;

import com.magicmetro.entity.TrainStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TrainStationResource {

    @Autowired
    private com.magicmetro.service.TrainStationService TrainStationService;

    @RequestMapping(path = "/stations/{sid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TrainStation searchTrainStationByIdResource(@PathVariable("sid") int id) {
        return TrainStationService.searchTrainStationById(id);

    }

}
