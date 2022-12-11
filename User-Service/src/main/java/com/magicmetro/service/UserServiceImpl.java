package com.magicmetro.service;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicmetro.entity.User;
import com.magicmetro.persistence.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean addUser(User user) {
		
		User users = userDao.findById(user.getUserId()).orElse(null);
		
		if(users == null) {
			
			try {
				
				userDao.insertUser(user.getUserId(), user.getPassword(), user.getFullName(),
				user.getAddress(), user.getPhoneNumber(), user.getBalance());
				
				return true;
			
		}
		catch(SQLIntegrityConstraintViolationException e) {
			return false;
		}
		catch(Exception e) {
			return false;
		}			
			
		}else {
			return false;
		}
	

	}
	
	@Override
	public User getUserById(int id) {
		return userDao.getById(id);
		
	}
}		
		
	
