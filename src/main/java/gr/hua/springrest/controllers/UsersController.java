package gr.hua.springrest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.springrest.models.User;
import gr.hua.springrest.models.UserList;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
	
	@Autowired
	UserList userList;

	@RequestMapping(value = "user/{userId:\\d+}", method = RequestMethod.GET)
	public User getuser(@PathVariable("userId") int userId) {
		User user = new User();
		user.setId(userId);
		user.setName("Argiris");

		return user;
	}

	@RequestMapping(value = "user/all", method= RequestMethod.GET,  produces = {"application/json","application/xml"})
	public UserList getUsers() {
		List<User> userlist = new ArrayList<User>();
		User user1 = new User();
		user1.setId(1);
		user1.setName("Argiris");
		userlist.add(user1);

		User user2 = new User();
		user2.setId(2);
		user2.setName("Iason");
		userlist.add(user2);

		this.userList.setUserList(userlist);
		return this.userList;

	}
	
	@RequestMapping(value = "user/create", method= RequestMethod.POST)
	public User createUser(
			@RequestParam("name") String name,
			@RequestParam("email") String email, 
			@RequestParam(value="country", required=false) String country,
			@RequestParam("password") String password,
			@RequestParam(value="phone", required=false) String phone){
		User user= new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setCountry(country);
		user.setPhone(phone);
		//create random number to simulate a database entry
		Random random = new Random();
		user.setId(random.nextInt(10 - 2 + 1) + 2);
		return user;
	}
	
	@RequestMapping(value = "user/createjson", method= RequestMethod.POST)
	public User createUserfromJson(@RequestBody User user){
		return user;
	}

}
