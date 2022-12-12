package com.magicmetro.resource;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.magicmetro.entity.User;
import com.magicmetro.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;

		// ============ resource for add new user  ==================
		@PostMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public User addUserResource(@RequestBody User user) {
			if (userService.addUser(user))
				return user;
			else
				return null;
		}
	

		// ============ resource for search user by Id ==================
		@GetMapping(path = "/users/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public User searchUserByIdResource(@PathVariable("uid") int id) {
			return userService.searchUserById(id);
				
			}
	
	
		// ============ resource for update user balance ==================
		@PutMapping(path = "/users/{uid}/{inc}", produces = MediaType.TEXT_PLAIN_VALUE)
		public String updateUserBalanceResource(@PathVariable("uid") int id, @PathVariable("inc") double inc) {
			if (userService.updateUserBalance(id, inc)) {
				return "Balance Updated";
			}
			else {
				return "Balance NOT Updated";
			}
			
		}
		
		// ============ resource for login check by id and password ==================
		@GetMapping(path = "/users/{userId}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
		public User loginCheckResource(@PathVariable("userId") int userId, @PathVariable("password") String password){
			return userService.loginCheck(userId, password);
		}

}	
