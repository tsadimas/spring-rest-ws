package gr.hua.springrest.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.springrest.models.User;


@RestController
@RequestMapping(value = "/users")
public class UsersController {
	
	@RequestMapping(value = "user/{userId:\\d+}", method = RequestMethod.GET)
	public User getuser(@PathVariable("userId") int userId){
		 User user =  new User(userId, "","","", "","");
		 return user;
	}

}


