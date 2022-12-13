package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.magicmetro.entity.User;
import com.magicmetro.service.TransactionServiceImpl;


@SpringBootTest
class TransactionMvcApplicationTests {

	// declare fields/variables of the test class
	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;
	@Mock
	
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
	void testUserLoginChcek() {
		assertEquals(transactionServiceImpl.UserLoginCheck(101, "password1"), new User(101, "password1", "Neah Demi", "123 Elm Street", "07846352853", 8.5));
	}
	
	@Test
	void testUserSignUp() {
	}
	
	@Test
	void testCheckUserBalance() {
	}
	
	@Test
	void testTopUpBalance() {
	}
	
	@Test
	void testGetStationDetails() {
	}

}
