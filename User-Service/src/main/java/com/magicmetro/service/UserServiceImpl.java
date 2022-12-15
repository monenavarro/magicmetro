package com.magicmetro.service;

//import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicmetro.entity.User;
import com.magicmetro.persistence.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	
	@Override 
	public User searchUserById(int id) {
		return userDao.searchUserById(id);
	}
	
	
	@Override
	public boolean updateUserBalance(int id, double inc) {
		
		User user = userDao.searchUserById(id);
		
		if (user != null) {
			userDao.updateBalance(id, inc);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public User loginCheck(int userId, String password) {
		User user = userDao.findUserByIdAndPassword(userId, password);
		
		return user;
	}	
	
	@Override
	public boolean addUser(User user) {
		User users = userDao.findById(user.getUserId()).orElse(null);
		if(users == null) {
			
			userDao.save(user);
			return true;			
		}
		else {
			return false;
		}
	

	}
	

	

}	
	
