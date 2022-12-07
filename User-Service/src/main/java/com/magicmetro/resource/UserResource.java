package com.magicmetro.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.magicmetro.service.UserService;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService; 

}
