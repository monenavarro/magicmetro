package com.magicmetro.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magicmetro.service.UserService;
import com.magicmetro.entity.User;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService; 
	
	@RequestMapping(path = "/users/{userId}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User loginCheckResource(@PathVariable("userId") int userId, @PathVariable("password") String password){
		return userService.loginCheck(userId, password);
	}

}
