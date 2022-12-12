package com.magicmetro.service;

import com.magicmetro.entity.User;

public interface UserService {

	User loginCheck(int userId, String password);

}
