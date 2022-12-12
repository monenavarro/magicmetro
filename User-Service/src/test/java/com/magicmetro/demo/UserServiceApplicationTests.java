package com.magicmetro.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserDao userDao;
	private AutoCloseable autoCloseable;
	
	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
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
