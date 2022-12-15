package com.magicmetro.service;

import com.magicmetro.entity.User;

public interface UserService {
	
	public User searchUserById(int id);
	public boolean updateUserBalance(int id, double inc);
	User loginCheck(int userId, String password);
	boolean addUser(User user);
}
