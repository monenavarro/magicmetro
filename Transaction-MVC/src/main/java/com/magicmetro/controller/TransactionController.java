package com.magicmetro.controller;

//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.format.FormatStyle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.magicmetro.entity.Transaction;
import com.magicmetro.entity.User;
import com.magicmetro.entity.TrainStation;
import com.magicmetro.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

	// ==================Controllers for User Login, first page ========================
	@RequestMapping("/")
	public ModelAndView UserLoginInputController() {
		return new ModelAndView("InputUserIdAndPassword");
	}
	
	// ==================Controller for User Sign Up ========================
	
	@RequestMapping("/addUser")
	public ModelAndView UserAddController(@RequestParam("userId") int userId, @RequestParam("password") String pass, @RequestParam("fullName") String name, @RequestParam("address") String adr, @RequestParam("phoneNumber") String phone, @RequestParam("balance") double bal, HttpSession session) {
		// create empty MAV 
		ModelAndView modelAndView = new ModelAndView();
		// create user to be added to table in database of user-service api
		User userToAdd = new User(userId, pass, name, adr, phone, bal);
		// perform service method for user sign up
		boolean addStatus = transactionService.UserSignUp(userToAdd);
		
		if (addStatus == true) {
			modelAndView.addObject("message","Successfully Signed Up, you are now Logged In!");
			session.setAttribute("user", userToAdd);
			modelAndView.setViewName("MainMenu");
		}
		else {
			modelAndView.addObject("message", "Failed to Sign Up, User already exists! Please try logging in or signing up with different details");
			session.setAttribute("user", new User());
			modelAndView.setViewName("InputUserIdAndPassword");
		}
		
		return modelAndView;
	}
	
	// ==================Controllers for Main Menu ========================
	@RequestMapping("/menu")
	public ModelAndView mainMenuPageController() {
		return new ModelAndView("MainMenu");
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
			modelAndView.setViewName("InputUserIdAndPassword");
		}
		
		return modelAndView;
		
	}
	
	@RequestMapping("/logOut")
	public ModelAndView logOutController() {
		// create empty MAV 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "You have successfully Logged Out!");
		modelAndView.setViewName("InputUserIdAndPassword");
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
			modelAndView.addObject("message","Your Balance is Sufficient, it is currently : ??"+userBalance);
			modelAndView.setViewName("MainMenu");
		}
		else {
			modelAndView.addObject("message","Your Balance is Insufficient, it is currently : ??"+userBalance+" please Top Up!");
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
		double oldBalance = userObj.getBalance();
		// perform top up balance method of service (which uses update user balance method of user-service api)
		boolean toppedUp = transactionService.TopUpBalance(userId, topUp);
		// re-set the user object balance field 
		userObj.setBalance(oldBalance+topUp);
		
		if (toppedUp == true) {
			modelAndView.addObject("message","Your Balance was Successfully Topped Up by : ??"+topUp);
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
		public ModelAndView chooseStartStationController(HttpSession session, @RequestParam("stationId") int stationId){
		
		ModelAndView modelAndView = new ModelAndView();
		
		// use session to get user object of logged in user and hence their userId
		User userObj = (User) session.getAttribute("user");
		int userId = userObj.getUserId();
		double userBalance = userObj.getBalance();
		
		// get station object details, given the Id
		TrainStation startStation = transactionService.GetStationDetails(stationId);
		
		
		// check balance is sufficient 
		boolean balanceStatus = transactionService.CheckUserBalance(userId);
		if (balanceStatus == true) {
			
			// if balance is sufficient, record the transaction userId, start stationId and swipeIn time
			Transaction transaction =  new Transaction();
			transaction.setUserId(userId);
			transaction.setStartStationId(stationId);	
			// formatter, recording and formatting of timeStamp variable for swipeIn time
			// record and set swipeIn time in transaction object
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm yyyy MM dd");
			// get time as LDT object
			LocalDateTime timeLDT = LocalDateTime.now();
			// format as string
			String timeFormatted = timeLDT.format(formatter);
			// set swipe in time
			transaction.setSwipeInTime(LocalDateTime.parse(timeFormatted, formatter));			
			// set transaction as session attribute
			session.setAttribute("Transaction", transaction);
			// display message on subMenu view
			modelAndView.addObject("message", "It is currently "+transaction.getSwipeInTime().format(formatter)+" you are on route from "+startStation.getStationName());
			modelAndView.setViewName("subMenu");
		}
		else {
			modelAndView.addObject("message","Your Balance is Insufficient, it is currently : ??"+userBalance+" please Top Up!");
			modelAndView.setViewName("MainMenu");
		}
		
		return modelAndView;
	}
	
	
	// ==================Controllers for Swiping Out ========================
	@RequestMapping("/swipeOut")
		public ModelAndView swipeOutController(HttpSession session) {
		return new ModelAndView("swipeOut");
	}
	
	@RequestMapping("/swipeOutInput")
		public ModelAndView chooseEndStationController(HttpSession session, @RequestParam("stationId") int stationId) {
		
		// MAV object
		ModelAndView modelAndView = new ModelAndView();
		
		// get transaction and user object
		Transaction transaction = (Transaction)session.getAttribute("Transaction");
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		// get station objects for end and start, given their IDs
		TrainStation startStation = transactionService.GetStationDetails(transaction.getStartStationId());
		TrainStation endStation = transactionService.GetStationDetails(stationId);
		// set end station 
		transaction.setEndStationId(stationId);
		
		// record and set swipeOut time in transaction object
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm yyyy MM dd");
		// get time as LDT object
		LocalDateTime timeLDT = LocalDateTime.now();
		// format as string
		String timeFormatted = timeLDT.format(formatter);
		// set swipe out time
		transaction.setSwipeOutTime(LocalDateTime.parse(timeFormatted, formatter));
		
		// calculate price of train journey 
		double price = Math.abs(transaction.getEndStationId() - transaction.getStartStationId());
		
		// alter the balance of the user by taking away journey price via TopUp method(updates database) and setter (updates session attribute)
		transactionService.TopUpBalance(userId, -price);
		user.setBalance(user.getBalance()-price);
		
		// add message to MAV 
		modelAndView.addObject("message", "You have successfully swiped out at "+transaction.getSwipeOutTime().format(formatter)+"! Your new balance is : ??"+user.getBalance());
		// add necessary objects for user fare receipt 
		modelAndView.addObject("price", price);
		modelAndView.addObject("startStation", startStation);
		modelAndView.addObject("endStation", endStation);
		modelAndView.addObject("user", user);
		modelAndView.addObject("transaction", transaction);
		// display on summary view 
		modelAndView.setViewName("Summary");
		
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}