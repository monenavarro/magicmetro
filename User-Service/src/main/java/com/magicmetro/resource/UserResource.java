package com.magicmetro.resource;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magicmetro.entity.User;
import com.magicmetro.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User addUserResource(@RequestBody User user) {
		if (userService.addUser(user))
			return user;
		else
			return null;
	}

	
	@GetMapping(path="/users/{uid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByIdResourse(@PathVariable("uid") int id) {
		return userService.getUserById(id);
	}

}
