package gr.hua.springrest.controllers;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.springrest.HomeController;
import gr.hua.springrest.HttpUnauthorizedException;
import gr.hua.springrest.dao.JwtService;
import gr.hua.springrest.dao.UserDAO;
import gr.hua.springrest.models.User;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private final UserDAO userDAO;
	private final JwtService jwtService;

	@SuppressWarnings("unused")
	public LoginController() {
		this(null, null);
	}

	@Autowired
	public LoginController(UserDAO userDAO, JwtService jwtService) {
		this.userDAO = userDAO;
		this.jwtService = jwtService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletResponse response) {
		logger.info("------");
		User user = new User();

		try {
			user = userDAO.login(username, password);
			logger.info(user.toString());
			if (user == null)
			{
				logger.info("---IN EXCEPTION NULL---");

				throw new HttpUnauthorizedException();
			}
			logger.info(user.getName());
			response.setHeader("Token", jwtService.tokenFor(user));
			logger.info(jwtService.tokenFor(user).toString());
		} catch (Exception e) {
			logger.info("---IN EXCEPTION---");

			logger.info(e.getMessage());
			throw new HttpUnauthorizedException();
		}
		return user;
	}

}
