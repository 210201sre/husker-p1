package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	public User addUser(User u) {
		MDC.put("event", "Add User");
		
		log.info("Registering new User");
		u = userDAO.save(u);
		MDC.put("userId", Integer.toString(u.getUserID()));
		log.info("Successfully registered User");
		return u;
	}
	
	public List<User> findAll(){
		MDC.put("event", "Find All Users");
		List<User> userList = userDAO.findAll();
		for (int i = 0; i < userList.size(); i++) {
			log.info("Found user with userID" + userList.get(i).getUserID());
		}
		
		log.info("All users successfully found");
		
		return userDAO.findAll();
	}
	
	public Optional<User> findSingleUser(int id) {
		
		MDC.put("event", "Find Single User");
		MDC.put("userId", Integer.toString(id));

		log.info("Successfully found User with ID = " + id);
		return userDAO.findById(id);
	}
	
	public void deleteUser(User u) {
		
		MDC.put("event", "Delete User");
		MDC.put("userId", Integer.toString(u.getUserID()));

		log.info("Delete successful!");

		userDAO.delete(u);
	}
	
	public User update(User u) {
		MDC.put("event", "Update User");
		MDC.put("userId", Integer.toString(u.getUserID()));

		log.info("Update successful!");	

		return userDAO.save(u);		
	}

}
