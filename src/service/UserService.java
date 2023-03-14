package service;

import database.UserDatabase;
import database.UserDb;
import entity.User;

public class UserService {

	private UserDatabase userDb;
	
	public UserService(UserDatabase userDb) {
		this.userDb = userDb;
	}
	
	public User getUserByEmail(String email) throws Exception {
		User user = null;
		try {
			user = userDb.getUserByEmail(email);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(user == null) {
			throw new Exception("User does not exist please check");
		}
		return user;
	}
	
	public boolean addUser(User user) throws Exception {
		boolean flag = false;
		if(getUserByEmail(user.getEmail()) != null) {
			throw new Exception("User already exists");
		}
		try {
			flag = userDb.addUser(user);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	public boolean validateCredentials(String email, String password) throws Exception {
		User user;
		try {
			user = getUserByEmail(email);
			if(user.getPassword().equals(password)) {
				return true;
			}
		}
		catch(Exception e) {
			throw new Exception("Invalid credentials");
		}
		
		return false;
	}
}
