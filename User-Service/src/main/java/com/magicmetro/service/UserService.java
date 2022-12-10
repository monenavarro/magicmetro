package com.magicmetro.service;

import com.magicmetro.entity.User;

public interface UserService {
	
	public User searchUserById(int id);
	public boolean updateUserBalance(int id, double inc);

}
