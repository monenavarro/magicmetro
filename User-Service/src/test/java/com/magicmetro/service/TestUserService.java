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
//	@Test
//	void testAddUserTwo() {
//		
//		when(userDao.findById(101)).thenReturn(null);
//		
//		assertTrue(userServiceImpl.addUser(new User(101, "password1", "Neah Demi", "123 Elm Street",
//				"07846352853", 8.50, LocalDate.now(), LocalDate.now())));
//	
//		
//	}

}
