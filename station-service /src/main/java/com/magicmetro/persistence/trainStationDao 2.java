package com.magicmetro.persistence;

import com.magicmetro.entity.trainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface trainStationDao extends JpaRepository<trainStation, Integer> {
}
