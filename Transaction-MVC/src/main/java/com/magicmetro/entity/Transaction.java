package com.magicmetro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	private int userId;
	private int stationId;
	private Timestamp swipeInTime;
	private Timestamp swipeOutTime;
}
