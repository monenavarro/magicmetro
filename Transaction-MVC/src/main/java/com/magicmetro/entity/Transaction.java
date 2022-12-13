package com.magicmetro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	private int userId;
	private int startStationId;
	private int endStationId;
	private LocalDateTime swipeInTime;
	private LocalDateTime swipeOutTime;
}
