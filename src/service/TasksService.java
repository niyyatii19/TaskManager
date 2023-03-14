package service;

import java.util.ArrayList;
import java.util.List;

import database.TaskDatabase;
import database.TasksDb;
import entity.Tasks;
import entity.User;

public class TasksService {

	private TaskDatabase taskDb;
	
	public TasksService(TaskDatabase taskDb) {
		this.taskDb = taskDb;
	}
	
	
	public List<Tasks> getAllTask(User user) throws Exception{
		List<Tasks>	list = null;
		try {
			list = this.taskDb.getAllTask(user);
			if(list.isEmpty()) {
				throw new Exception("No tasks added please add");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public int addTasks(Tasks tasks, User user) throws Exception {
		return this.taskDb.addTasks(tasks, user);
	}
	
	public void updateTasks(User user, String title) throws Exception {
		try {
			this.taskDb.updateTasks(user, title);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Tasks searchForTask(User user, String title) throws Exception {
		Tasks tasks= null;
		try {
			tasks = this.taskDb.searchForTask(user, title);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return tasks;
	}
	
	public void deleteTask(User user, String title) throws Exception{
		try {
			this.taskDb.deleteTask(user, title);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Tasks> getAllCompletTask(User user) throws Exception{
		List<Tasks> tasks = taskDb.getAllTask(user) ;
		List<Tasks> complete = new ArrayList<>();
		for(Tasks task : tasks) {
			if(task.isTaskCompleted()) {
				complete.add(task);
			}
		}
		
		if(complete.isEmpty()) {
			throw new Exception ("No task complete ");
		}
		return complete;
	}
	
	public List<Tasks> getAllIncompletTask(User user) throws Exception{
		List<Tasks> tasks = taskDb.getAllTask(user) ;
		List<Tasks> incomplete = new ArrayList<>();
		for(Tasks task : tasks) {
			if(!task.isTaskCompleted()) {
				incomplete.add(task);
			}
		}
		
		if(incomplete.isEmpty()) {
			throw new Exception ("All task are completed ");
		}
		return incomplete;
	}
	
}
