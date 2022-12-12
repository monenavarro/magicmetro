package com.magicmetro.service;

import java.util.List;

import com.magicmetro.entity.Transaction;
import com.magicmetro.entity.User;

public interface TransactionService {
	
	User UserLoginCheck(int userId, String password);
	
	boolean CheckUserBalance(int userId);

	boolean TopUpBalance(int userId, int increment);
	
//	boolean SwipeIn(int userId, String startStationName);
//	
//	boolean SwipeOut(String endStationName);
	
	List<Transaction> TransactionDetails(int userId);
	
	

}
