package gr.hua.springrest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "user/all", method= RequestMethod.GET)
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

}
