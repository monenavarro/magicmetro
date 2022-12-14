package com.magicmetro.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.magicmetro.entity.User;
import com.magicmetro.entity.TrainStation;
import com.magicmetro.service.TransactionService;

@RestController
public class TransactionResource {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping(path ="/transactions/login/{uid}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User UserLoginCheckResource(@PathVariable("uid") int userId, @PathVariable("password") String password) {
		return transactionService.UserLoginCheck(userId, password);
		
	}
	
	@PostMapping(path ="/transactions/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean UserSignUpResource(@RequestBody User user) {
		return transactionService.UserSignUp(user);
		
	}
	
	@GetMapping(path ="/transactions/check/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean CheckUserBalanceResource(@PathVariable("uid") int userId) {
		return transactionService.CheckUserBalance(userId);
	}
	
	@PutMapping(path ="/transactions/top/{uid}/{inc}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean TopUpBalanceResource(@PathVariable("uid") int userId, @PathVariable("inc") double increment) {
		return transactionService.TopUpBalance(userId, increment);
	}
	
	@GetMapping(path="/transactions/stations/{sid}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public TrainStation GetStationDetails(@PathVariable("sid") int stationId) {
		return transactionService.GetStationDetails(stationId);
	}
	
}