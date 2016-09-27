package gr.hua.springrest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.InvalidResultSetAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import gr.hua.springrest.HomeController;
import gr.hua.springrest.models.User;

public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("insert into Users (name, email, phone, country, password) values (?, ?, ?, ?, ?)", user.getName(),
				user.getEmail(), user.getPhone(), user.getCountry(), user.getPassword());

	}

	@Override
	public User getById(int id) {
		String query = "select * from Users where id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Object queryForObject = jdbcTemplate.queryForObject(query, new Object[] { id },
				new BeanPropertyRowMapper<User>(User.class));
		User user = (User) queryForObject;

		return user;
	}

	@Override
	public void update(User user) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("update Users set  name = ?, email = ?, phone = ?, country = ?, password = ? where id = ?",
				user.getName(), user.getEmail(), user.getPhone(), user.getCountry(), user.getPassword(), user.getId());

	}

	@Override
	public boolean deleteById(int id) {
		Boolean state=false;
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update("delete from Users where id = ?", id);
			state=true;
		} catch (InvalidResultSetAccessException e) {
			state=false;
			logger.info("InvalidResultSetAccessException");
			
			
		} catch (DataAccessException e) {
			state=false;
			logger.info("DataAccessException " + e.getMessage());
		
		}
		
		return state;

	}

	@Override
	public List<User> getAll() {
		List<User> usersList = new ArrayList<User>();
		// JDBC Code - Start
		String query = "select * from Users";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> usersRows = jdbcTemplate.queryForList(query);

		for (Map<String, Object> userRow : usersRows) {
			User user = new User();
			user.setId(Integer.parseInt(String.valueOf(userRow.get("id"))));
			user.setName(String.valueOf(userRow.get("name")));
			user.setEmail(String.valueOf(userRow.get("email")));
			user.setPhone(String.valueOf(userRow.get("phone")));
			user.setCountry(String.valueOf(userRow.get("country")));
			usersList.add(user);
		}

		return usersList;
	}

	@Override
	public User login(String username, String password) {
		Boolean state=false;
		
		String query = "select * from Users where name = ? and password = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Object queryForObject = jdbcTemplate.queryForObject(query, new Object[] { username, password },
				new BeanPropertyRowMapper<User>(User.class));
		User user = (User) queryForObject;

		return user;
	}

}