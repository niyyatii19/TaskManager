package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Tasks;
import entity.User;

public class TaskDatabase {
	Connection dbConn = DatabaseConnection.getConnection();
	
	public List<Tasks> getAllTask(User user) throws Exception{
		List<Tasks> list = new ArrayList<>();
		String sql = "Select id, title, text, isComplete from tasks where assignedTo = ?  ";
		String assignedTo = user.getEmail();
		try {
			PreparedStatement statement = dbConn.prepareStatement(sql);
			
			statement.setString(1, assignedTo);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				
					Tasks tasks = new Tasks();
					tasks.setId(rs.getInt(1));
					tasks.setTitle(rs.getString(2));
					tasks.setText(rs.getString(3));
//					tasks.setAssignedTo(rs.getString(5));
					tasks.setTaskCompleted(rs.getBoolean(4));
					list.add(tasks);
			}
		}catch(SQLException e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}
	
	public int addTasks(Tasks tasks, User user) throws Exception {
		List<Tasks> list = getAllTask(user);
		int id;
		if(list.isEmpty()) {
			id = 1;
		}else {
			id = list.size()+1;
		}
		tasks.setId(id);
		tasks.setAssignedTo(user.getEmail());
		String sql = "insert into tasks values(?,?,?,?,?)";
		
		try {
			PreparedStatement statement = dbConn.prepareStatement(sql);
			//System.out.println(statement);
			statement.setInt(1, tasks.getId());
			statement.setString(2, tasks.getTitle());
			statement.setString(3, tasks.getText());
			statement.setBoolean(4, tasks.isTaskCompleted());
			statement.setString(5, tasks.getAssignedTo());
			
			statement.execute();
			
		}
		catch(SQLException ex)
		{
			throw new Exception(ex.getMessage());
		}
		return id;

	}
	
	public void updateTasks(User user, String title){
		String sql = "update tasks set isComplete = true"
				+ " where assignedTo =? and title = ?";
		try {
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, user.getEmail());
			statement.setString(2, title);
			statement.execute();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Tasks searchForTask(User user, String title) throws Exception {
		String sql = "Select * from tasks where assignedTo = ? and title = ?";
		Tasks tasks = new Tasks();
		try {
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, user.getEmail());
			statement.setString(2, title);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				tasks.setId(rs.getInt(1));
				tasks.setTitle(rs.getString(2));
				tasks.setText(rs.getString(3));
				tasks.setAssignedTo(rs.getString(5));
				tasks.setTaskCompleted(rs.getBoolean(4));
			}
			
		}catch(SQLException e) {
			throw new Exception(e.getMessage());
		}
		return tasks;
	}
	
	public void deleteTask(User user, String title) throws Exception{
		String sql = "delete from tasks where assignedTo=? and title = ?";
		try {
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setString(1, user.getEmail());
			statement.setString(2, title);
			statement.execute();
		}
		catch(SQLException ex)
		{
			throw new Exception(ex.getMessage());
		}
		
	}
	
}
