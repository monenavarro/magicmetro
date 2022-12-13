package com.magicmetro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.magicmetro.entity.User;
import com.magicmetro.service.TransactionService;


import java.util.List;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

	// ==================Controllers for User Login, first page ========================
	@RequestMapping("/")
	public ModelAndView UserLoginInputController() {
		return new ModelAndView("InputUserIdAndPassword");
	}
	
	// ==================Controller for checking User Login ========================
	@RequestMapping("/checkUser")
	public ModelAndView checkUserLoginController(@RequestParam("userId") int id, @RequestParam("password") String password,  HttpSession session) {
		// create empty MAV 
		ModelAndView modelAndView = new ModelAndView();
		
		// run login-check method of service 
		User logUser = transactionService.UserLoginCheck(id, password);
		
		
		// if the customer login check is passed
		if (logUser != null) {
			// add the customer object to MAV
			modelAndView.addObject("user", logUser);
			// set attribute in session
			session.setAttribute("user", logUser);
			// show the Main Menu view on the web app
			modelAndView.setViewName("MainMenu");
		}
		// if login check is failed
		else { 
			// display message on selected view
			modelAndView.addObject("message", "Unable to Login, User Details Incorrect.");
			// reset customer object for another login try
			modelAndView.addObject("user", new User());
			// show the Login page on the web app
			modelAndView.setViewName("/");
		}
		
		return modelAndView;
		
	}
	
	// ==================Controllers for Checking User Balance ========================
	@RequestMapping("/checkBalance")
	public ModelAndView checkBalanceController(HttpSession session) {
		// create empty MAV 
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = (User) session.getAttribute("user");
		int userId = userObj.getUserId();	
		double userBalance = userObj.getBalance();
		// use service method (which user user-service api) to check user balance
		boolean balanceStatus = transactionService.CheckUserBalance(userId);
		
		if (balanceStatus == true) {
			modelAndView.addObject("message","Your Balance is Sufficient, it is currently : £"+userBalance);
			modelAndView.setViewName("MainMenu");
		}
		else {
			modelAndView.addObject("message","Your Balance is Insufficient, it is currently : £"+userBalance+" please Top Up!");
			modelAndView.setViewName("MainMenu");
		}
		
		return modelAndView;
		
		
	}
	
	// ==================Controllers for Updating/Top Up User Balance ========================
	@RequestMapping("/topUpBalanceInputPage")
	public ModelAndView topUpBalanaceInputController() {
		return new ModelAndView("InputTopUp");
	}
	
	@RequestMapping("/topUpBalance")
	public ModelAndView topUpBalanceController(@RequestParam("topUp") double topUp, HttpSession session) {
		// create empty MAV 
		ModelAndView modelAndView = new ModelAndView();
		// use session to get user object of logged in user and hence their userId
		User userObj = (User) session.getAttribute("user");
		int userId = userObj.getUserId();
		// perform top up balance method of service (which uses update user balance method of user-service api)
		boolean toppedUp = transactionService.TopUpBalance(userId, topUp);
		
		if (toppedUp == true) {
			modelAndView.addObject("message","Your Balance is was Successfully Topped Up by "+topUp);
			modelAndView.setViewName("MainMenu");
		}
		else {
			modelAndView.addObject("message","Failed to Top Up your Balance, Please Try Again");
			modelAndView.setViewName("MainMenu");
		}
		
		return modelAndView;
	}
	
	// ==================Controllers for Swiping In ========================
	@RequestMapping("/swipeIn")
	public ModelAndView swipeInController(HttpSession session){
		//timestamp start here
		return new ModelAndView ("trainStationChoice");
		// model and view page option train stations
		// model and view back to main menu with swipe in chosen + time stamp
	}

@RequestMapping("/chooseStartStation")
	public ModelAndView chooseStartStationController(HttpSession session, @RequestParam("stationId") int stationId ){
	ModelAndView modelAndView = new ModelAndView();
	// use session to get user object of logged in user and hence their userId
	User userObj = (User) session.getAttribute("user");
	int userId = userObj.getUserId();

	return modelAndView;
}

	}
	// ==================Controllers for Swiping Out ========================
	

