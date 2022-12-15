package com.magicmetro.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.magicmetro.entity.User;
import com.magicmetro.persistence.UserDao;
import com.magicmetro.service.UserService;
import com.magicmetro.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class TestUserService {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserDao userDao;
	private AutoCloseable autoCloseable;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable=MockitoAnnotations.openMocks(this);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testAddUserOne() {
		
		when(userDao.findById(101)).thenReturn(Optional.of(new User(101, "password1", "Neah Demi", "123 Elm Street",
				"07846352853", 8.50)));
		
		assertFalse(userServiceImpl.addUser(new User(101, "password1", "Neah Demi", "123 Elm Street",
				"07846352853", 8.50)));
	
		
	}
	@Test
	void testAddUserTwo() {
		
		when(userDao.searchUserById(101)).thenReturn(null);
		
		assertTrue(userServiceImpl.addUser(new User(101, "password1", "Neah Demi", "123 Elm Street",
				"07846352853", 8.50)));	
	}
	
	@Test
	void testSearchUserById() {
		when(userDao.searchUserById(102)).thenReturn(new User(102, "password2", "Rachel Vickerman", "12 Top Towie Avenue", "07846333353", 6.00));
		User testUser = userServiceImpl.searchUserById(102);
		assertEquals(testUser, new User(102, "password2", "Rachel Vickerman", "12 Top Towie Avenue", "07846333353", 6.00));
	}
	
	@Test 
	void testSearchUserById2() {
		// specify test user searched for my dao method
		when(userDao.searchUserById(1111)).thenReturn(null);
		// assert that this test user equals the correct output
		assertNull(userServiceImpl.searchUserById(1111));
	}
	
	
	@Test
	void testUpdateUserBalance() {
		// specify behaviour of mock serviceImpl method
		// if DAO search record method returns book object (not null)
		when(userDao.searchUserById(102)).thenReturn(new User(102, "password2", "Rachel Vickerman", "12 Top Towie Avenue", "07846333353", 6.00));
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
	
	@Test
	void testLoginCheckOne() {
		when(userDao.findUserByIdAndPassword(101, "password1")).thenReturn(new User(101, "password1", "Neah Demi", "123 Elm Street", "07846352853", 8.5));
		
		User user = new User(101, "password1", "Neah Demi", "123 Elm Street", "07846352853", 8.5);
		
		assertEquals(user, userServiceImpl.loginCheck(101, "password1"));
	}
	
	@Test
	void testLoginCheckTwo() {
		assertNull(userDao.findUserByIdAndPassword(102, "password1"));
	}
	
	

}
