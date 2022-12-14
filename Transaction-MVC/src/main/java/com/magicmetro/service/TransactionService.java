package com.magicmetro.service;

import com.magicmetro.entity.User;
import com.magicmetro.entity.TrainStation;

public interface TransactionService {
	
	User UserLoginCheck(int userId, String password);
	
	boolean UserSignUp(User userToAdd);
	
	boolean CheckUserBalance(int userId);

	boolean TopUpBalance(int userId, double increment);
	
	TrainStation GetStationDetails(int stationId);
	

}
	

