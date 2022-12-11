package com.magicmetro.service;

import com.magicmetro.entity.User;

public interface UserService {
	
	boolean addUser(User user);
	public User getUserById(int id);

}
