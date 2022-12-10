package com.magicmetro.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magicmetro.entity.User;
import com.magicmetro.service.UserService;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService; 
	
	
	// ============ resource for search user by Id ==================
			@RequestMapping(path = "/users/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public User serachUserByIdResource(@PathVariable("uid") int id) {
				return userService.searchUserById(id);
				
			}
	
	
	// ============ resource for update user balance ==================
		@RequestMapping(path = "/users/{uid}/{inc}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
		public String updateUserBalanceResource(@PathVariable("uid") int id, @PathVariable("inc") double inc) {
			if (userService.updateUserBalance(id, inc)) {
				return "Balance Updated";
			}
			else {
				return "Balance NOT Updated";
			}
			
		}
		

}
