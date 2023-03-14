package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection getConnection() {
		Connection conn= null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/toDoDb","root","root123");

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
