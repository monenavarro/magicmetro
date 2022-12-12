package com.magicmetro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.magicmetro.entity.Transaction;
import com.magicmetro.entity.User;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override 
	public User UserLoginCheck(int userId, String password) {
		// get user through login check resource of user-service api
		User user = restTemplate.getForObject("http://localhost:8082/users/"+userId+"/"+password, User.class);
		// return this object
		return user;
	}
	
	
	@Override
	public boolean CheckUserBalance(int userId) {
		// get user object using id argument through search by user id resource of user-service api
		User user = restTemplate.getForObject("http://localhost:8082/users/"+userId, User.class);
		// get balance field
		double balance = user.getBalance();
		// check if balance is sufficient to enter train 
		if (balance >= 5.00) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean TopUpBalance(int userId, int increment) {
		// get user object using id argument through search by user id resource of user-service api
		User user = restTemplate.getForObject("http://localhost:8082/users/"+userId, User.class);
		// if user exists, top up balance with update user balance method from user-service api
		boolean updatedBalance = restTemplate.getForObject("http://localhost:8082/users/"+userId+"/"+increment, boolean);
		// return false
		return false;
	}

//	@Override
//	public boolean SwipeIn(int userId, String startStationName) {
//		// validate balance 
//		
//		
//		// time-stamp
//		// true= in, false = no
//		return false;
//	}
//
//	@Override
//	public boolean SwipeOut(String endStationName) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public List<Transaction> TransactionDetails(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
