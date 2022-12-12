package com.magicmetro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicmetro.entity.User;
import com.magicmetro.persistence.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User loginCheck(int userId, String password) {
		User user = userDao.findUserByIdAndPassword(userId, password);
		
		return user;
	}


}
