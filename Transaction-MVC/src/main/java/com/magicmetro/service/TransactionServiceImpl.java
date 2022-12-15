package com.magicmetro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.magicmetro.entity.User;
import com.magicmetro.entity.TrainStation;

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
		
			// top up balance with update user balance resource from user-service api via exchange method of restTemplate
			// set headers and entity for exchange method parameters
			HttpHeaders headers = new HttpHeaders();
		    HttpEntity<String> entity = new HttpEntity<String>("worked",headers);
		    String exchange = restTemplate.exchange("http://localhost:8082/users/"+userId+"/"+increment, HttpMethod.PUT, entity, String.class).getBody();
		    
		    // if the returned string from the rt.exchange method is "Balance Updated" (the return of the user-service top up balance resource when it works)
		    if (exchange.equals("Balance Updated")) {
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

	@Override
	public TrainStation GetStationDetails(int stationId) {
		// use trainStation-service api search station by Id method to get station object 
		TrainStation station = restTemplate.getForObject("http://localhost:8084/stations/"+stationId, TrainStation.class);
		// return the object 
		return station;
	}


	

}