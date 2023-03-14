package database;

import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDb {
	List<User> usersList = new ArrayList<>();
	
	public UserDb() {
		usersList.add(new User("Sita", "sita@g.com", "123456"));
		usersList.add(new User("Raman", "raman@g.com", "12345678"));
	}
	
	public User getUserByEmail(String email) throws Exception {
		for(User user: usersList) {
			if(user.getEmail().equals(email)){
				return user;
			}
		}
		throw new Exception("No User found");
	}
	
	public boolean addUser(User user) throws Exception {
		if(getUserByEmail(user.getEmail()) == null) {
			throw new Exception("User already exisits");
		}
		usersList.add(user);
		return true;
	}
}
