package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.magicmetro.demo.TransactionMvcApplication;
import com.magicmetro.entity.User;
import com.magicmetro.entity.TrainStation;
import com.magicmetro.service.TransactionServiceImpl;


@SpringBootTest(classes = TransactionMvcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TransactionMvcApplicationTests {

	// declare fields/variables of the test class
	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;
	
	@Mock
	private RestTemplate restTemplate;
	private AutoCloseable autoCloseable;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// tells mockito to scan the test class for all the fields/variables annotated with @Mock
		// and initialise these as mocks
		autoCloseable = MockitoAnnotations.openMocks(this);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testUserLoginCheck() {
		// when user-service login check method is called via restTemplate, return a user
		when(restTemplate.getForObject("http://localhost:8082/users/"+101+"/"+"password1", User.class)).thenReturn(new User(101, "password1", "Neah Demi", "123 Elm Street", "07846352853", 8.5));
		// assert that the returned user object of the mvc service method is the correct user
		assertEquals(transactionServiceImpl.UserLoginCheck(101, "password1"), new User(101, "password1", "Neah Demi", "123 Elm Street", "07846352853", 8.5));
	}
	
	@Test
	void testUserLoginCheck2() {
		// when user-service login check method is called via restTemplate, return null
		when(restTemplate.getForObject("http://localhost:8082/users/"+101+"/"+"password1", User.class)).thenReturn(null);
		// assert that the returned user object of the mvc service method is the null
		assertEquals(transactionServiceImpl.UserLoginCheck(101, "password1"), null);
	}
	
	@Test
	void testUserSignUp() {
		// create null user object to be added to user data
		User userToAdd = null;
		// when user-service add user method is called via restTemplate, return a user
		when(restTemplate.postForObject("http://localhost:8082/users", userToAdd, User.class)).thenReturn(userToAdd);
		// assert that the return of the mvc service method is false (it has not successfully added the user)
		assertFalse(transactionServiceImpl.UserSignUp(userToAdd));
	}
	
	@Test
	void testUserSignUp2() {
		// create user object to be added to user data
		User userToAdd = new User(102, "password2", "Rachel Vickerman","12 Top Towie Avenue", "07846333353", 6.0);
		// when user-service add user method is called via restTemplate, return a user
		when(restTemplate.postForObject("http://localhost:8082/users", userToAdd, User.class)).thenReturn(userToAdd);
		// assert that the return of the mvc service method is true (it has successfully added the user)
		assertTrue(transactionServiceImpl.UserSignUp(userToAdd));
	}
	
	@Test
	void testCheckUserBalance() {
		when(restTemplate.getForObject("http://localhost:8082/users/"+101, User.class)).thenReturn(new User(101, "password1", "Neah Demi", "123 Elm Street", "07846352853", 8.5));
		assertTrue(transactionServiceImpl.CheckUserBalance(101));
	}
	
	@Test
	void testCheckUserBalance2() {
		when(restTemplate.getForObject("http://localhost:8082/users/"+101, User.class)).thenReturn(new User(101, "password1", "Neah Demi", "123 Elm Street", "07846352853", 3.0));
		assertFalse(transactionServiceImpl.CheckUserBalance(101));
	}
	
	@Test
	void testTopUpBalance() {
		when(restTemplate.getForObject("http://localhost:8082/users/"+102, User.class)).thenReturn(new User(102, "password2", "Rachel Vickerman","12 Top Towie Avenue", "07846333353", 6.0));
		try {
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<String>("worked",headers);  
			when(restTemplate.exchange("http://localhost:8082/users/"+102+"/"+1.00, HttpMethod.PUT, entity, String.class).getBody()).thenReturn("Balance Updated");
			assertTrue(transactionServiceImpl.TopUpBalance(102, 5.00));
		} catch (NullPointerException e) {};
	    
		
		
	}
	
	@Test
	void testTopUpBalance2() {
		when(restTemplate.getForObject("http://localhost:8082/users/"+102, User.class)).thenReturn(null);
		try {
			HttpHeaders headers = new HttpHeaders();
		    HttpEntity<String> entity = new HttpEntity<String>("worked",headers);  
			when(restTemplate.exchange("http://localhost:8082/users/"+102+"/"+1.00, HttpMethod.PUT, entity, String.class).getBody()).thenReturn("Balance NOT Updated");
			assertFalse(transactionServiceImpl.TopUpBalance(102, 1.00));
		} catch (NullPointerException e) {};
		
	}
	
	@Test
	void testGetStationDetails() {
		when(restTemplate.getForObject("http://localhost:8081/stations/"+1, TrainStation.class)).thenReturn(new TrainStation(1, "Platform 9 3/4"));
		assertEquals(transactionServiceImpl.GetStationDetails(1), new TrainStation(1, "Platform 9 3/4"));
		
	}

	@Test
	void testGetStationDetails2() {
		when(restTemplate.getForObject("http://localhost:8081/stations/"+1, TrainStation.class)).thenReturn(null);
		assertEquals(transactionServiceImpl.GetStationDetails(1), null);
		
	}
	
}
