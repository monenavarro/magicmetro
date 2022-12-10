package com.magicmetro.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.magicmetro.entity.User;
import com.magicmetro.persistence.UserDao;
import com.magicmetro.service.UserServiceImpl;


@SpringBootTest
class UserServiceApplicationTests {

	// declare fields/variables of the test class
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	@Mock
	private UserDao userDao;
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
	void testSearchUserById() {
		// specify test user searched for my dao method
		User testUser = userDao.searchUserById(102);
		System.out.println(testUser);
		// assert that this test user equals the correct output
		assertEquals(testUser, new User(102, "Rachel Vickerman", "12 Top Towie Avenue", "07846333353", 6.00));
	}
	
	@Test 
	void testSearchUserById2() {
		// specify test user searched for my dao method
		//User test2User = userDao.findById(103).orElse(null);
		// assert that this test user equals the correct output
		//assertNotEquals(test2User, new User(102, "Rachel Vickerman", "12 Top Towie Avenue", "07846333353", 6.00));
		assertNull(userDao.searchUserById(1111));
	}
	
	
	@Test
	void testUpdateUserBalance() {
		// specify behaviour of mock serviceImpl method
		// if DAO search record method returns book object (not null)
		when(userDao.searchUserById(102)).thenReturn(new User(102, "Rachel Vickerman", "12 Top Towie Avenue", "07846333353", 6.00));
		// assert that the Service new Pages method returns True
		// test the method
		assertTrue(userServiceImpl.updateUserBalance(102, 1.00));
	
	}
	
	@Test
	void testUpdateUserBalance2() {
		// specify behaviour of mock serviceImpl method
		// if DAO search record method returns book object (not null)
		when(userDao.searchUserById(102)).thenReturn(null);
		// assert that the Service new Pages method returns True
		// test the method
		assertFalse(userServiceImpl.updateUserBalance(102, 1.00));
	
	}
	


}
