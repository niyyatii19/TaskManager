package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Tasks;
import entity.User;

public class TasksDb {
	
	List<Tasks> tasks = new ArrayList<>();
	UserDb userDb;
	public TasksDb() {
		
		tasks.add(new Tasks(1, "DSA", "revise stack", "sita@g.com", false));
		tasks.add(new Tasks(2, "do", "grocery", "sita@g.com", false));
		tasks.add(new Tasks(3, "kitchen", "wash dishes", "sita@g.com", false));
	}
	
	public List<Tasks> getAllTask(User user) throws Exception{
		List<Tasks> list = new ArrayList<>();
		
		String email = user.getEmail();
		
		for(Tasks task : this.tasks) {
			if(task.getAssignedTo().equals(email)) {
				list.add(task);
			}
		}
		return list;	
	}
	
	public void addTasks(Tasks tasks, User user) throws Exception {
		List<Tasks> listOfTasks = getAllTask(user);
		int id;
		if(listOfTasks.isEmpty()) {
			id = 1;
			tasks.setId(id);
		}else {
			id = listOfTasks.get(listOfTasks.size()-1).getId()+1;
			tasks.setId(id);
		}
		tasks.setAssignedTo(user.getEmail());
		this.tasks.add(tasks);
	}
	
	public void updateTasks(User user, String title) throws Exception {
		Tasks task = searchForTask(user, title);
		task.setTaskCompleted(true);
	}
	
	public Tasks searchForTask(User user, String title) throws Exception {
		List<Tasks> list = getAllTask(user);
		
		for(Tasks task : list) {
			if(task.getTitle().equals(title)) {
				return task;
			}
		}
		
		throw new Exception("No such task found");
	}
	
	public void deleteTask(User user, String title) throws Exception{
		List<Tasks> list = getAllTask(user);
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getText().equals(title)) {
				list.remove(i);
				break;
			}
		}
		
	}
	
	
}
