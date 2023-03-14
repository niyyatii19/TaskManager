package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDatabase {
	Connection dbConnection = DatabaseConnection.getConnection();
	
	public User getUserByEmail(String email) throws Exception {
		String sql = "Select * from user where email = ?";
		User user = null;
		try {
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setEmail(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
		}catch(SQLException e) {
			throw new Exception(e.getMessage());
		}
		return user;
	}
	
	public boolean addUser(User user) throws Exception {
		if(getUserByEmail(user.getEmail()) != null) {
			throw new Exception("User already exisits");
		}
		String sql = "insert into user values(?,?,?)";
		
		try {
			PreparedStatement statement = dbConnection.prepareStatement(sql);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getPassword());
			statement.execute();
		}
		catch(SQLException ex)
		{
			throw new Exception(ex.getMessage());
		}


		return true;
	}
}
