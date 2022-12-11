package com.magicmetro.persistence;

import com.magicmetro.entity.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainStationDao extends JpaRepository<TrainStation, Integer> {

    @Query("from User where stationId=:id")
    TrainStation searchTrainStationById(@Param("id") int id);

}
