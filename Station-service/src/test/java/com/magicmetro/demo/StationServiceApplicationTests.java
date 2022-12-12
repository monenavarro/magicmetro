package com.magicmetro.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.magicmetro.entity.TrainStation;
import com.magicmetro.persistence.TrainStationDao;
import com.magicmetro.service.TrainStationServiceImpl;

@SpringBootTest
class StationServiceApplicationTests {
	
	@InjectMocks
	private TrainStationServiceImpl trainStationServiceImpl;
	@Mock
	private TrainStationDao trainStationDao;
	private AutoCloseable autoCloseable;

	@BeforeEach
	void setUp() throws Exception {

		autoCloseable = MockitoAnnotations.openMocks(this);

	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testSearchTrainStationById() {
		when(trainStationDao.searchTrainStationById(1)).thenReturn(new TrainStation(1, "Platform 9 3/4"));
		TrainStation testTrainStation = trainStationDao.searchTrainStationById(1);
		System.out.println(testTrainStation);
		assertEquals(testTrainStation, new TrainStation(1, "Platform 9 3/4"));
	}

	@Test
	void testSearchTrainStationById2() {
		assertNull(trainStationDao.searchTrainStationById(1234));
	}

}