package com.magicmetro.service;

import com.magicmetro.entity.User;

public interface UserService {
	
	boolean addUser(User user);
	public User searchUserById(int id);
	public boolean updateUserBalance(int id, double inc);

}
