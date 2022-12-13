package com.magicmetro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
		// return this object - either a null or not null
		return user;
	}
	
	@Override
	public boolean UserSignUp(User userToAdd) {
		//User userToAdd = new User(uid, pass, name, adr, phone, bal);
		// use the add user method of the user-service api - returns boolean
		User addStatus = restTemplate.postForObject("http://localhost:8082/users", userToAdd, User.class); 
		// if method worked, return true if not return false
		if (addStatus != null) {
			return true;
		}
		else {
			return false;
		}
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
	public boolean TopUpBalance(int userId, double increment) {
		// get user object using id argument through search by user id resource of user-service api
		User userThen = restTemplate.getForObject("http://localhost:8082/users/"+userId, User.class);
		if (userThen != null) { //if user exists,
			// check balance before top up
			double balanceBefore = userThen.getBalance();
			
			// top up balance with update user balance method from user-service api
			restTemplate.put("http://localhost:8082/users/"+userId+"/"+increment, boolean.class);
			
			// get new instance of user object (with updated balance) using id argument through search by user id resource of user-service api
			User userNow = restTemplate.getForObject("http://localhost:8082/users/"+userId, User.class);
			// check balance after top up
			double balanceAfter = userNow.getBalance();

			// if the balance was successfully updated, return will be true otherwise false
			if (balanceAfter - balanceBefore != 0) {
				return true;
			}
			else {
				return false;
			}
		}
		// if user does not exist, return false
		else {
			return false;
		}
		
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
