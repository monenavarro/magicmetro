package com.magicmetro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.magicmetro.entity.User;
import com.magicmetro.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

	@RequestMapping("/")
	public ModelAndView UserLoginInputController() {
		return new ModelAndView("InputUserIdAndPassword");
	}
	
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
	
	@RequestMapping("/checkBalance")
	public ModelAndView checkBalanceController(HttpSession session) {
		// create empty MAV 
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = (User) session.getAttribute("user");
		int userId = userObj.getUserId();		// use service method (which user user-service api) to check user balance
		boolean balanceStatus = transactionService.CheckUserBalance(userId);
		
		if (balanceStatus == true) {
			modelAndView.addObject("message","Your Balance is Sufficient!");
			modelAndView.setViewName("MainMenu");
		}
		else {
			modelAndView.addObject("message","Your Balance is Insufficient, please Top Up!");
			modelAndView.setViewName("MainMenu");
		}
		
		return modelAndView;
		
		
	}
}
