package ui;

import java.util.List;
import java.util.Scanner;

import database.TaskDatabase;
import database.TasksDb;
import database.UserDatabase;
import database.UserDb;
import entity.Tasks;
import entity.User;
import service.TasksService;
import service.UserService;

public class Menu {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		
//		UserDb userDb = new UserDb();
		UserDatabase userDb = new UserDatabase();
		UserService userService = new UserService(userDb);
		User user;
		Tasks tasks;
		
		String email, password, title, name;
		int choice = 0;
		
		boolean flag = true;
		
		do {
			System.out.println("****Welcome*****");
			System.out.println("Choose one of options");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			choice = scanner.nextInt();
			switch(choice) {
				case 1:
					System.out.println("Enter email : ");
					email = scanner.next();
					System.out.println("Enter password :");
					password = scanner.next();
					
				try {
					if(userService.validateCredentials(email, password)) {
						user = userService.getUserByEmail(email);
						System.out.println("*****Welcome " + " "+user.getUserName()+"*******");
						tasksDashboard(user);
					}else {
						System.out.println("Please enter valid credentials");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
					break;
				case 2:
					System.out.println("Enter name : ");
					name = scanner.next();
					System.out.println("Enter email : ");
					email = scanner.next();
					System.out.println("Enter password :");
					password = scanner.next();
					user = new User(name, email, password);
				try {
					userService.addUser(user);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
					break;
				default:
					System.out.println("Thank You for using the application");
				flag = false;
			}
		}while(flag);
		
	}

	private static void tasksDashboard(User user) {
		// TODO Auto-generated method stub
		
		boolean flag = true;
//		TasksDb tasksDb = new TasksDb();
		TaskDatabase tasksDb = new TaskDatabase();
		List<Tasks> task;
		TasksService taskService = new TasksService(tasksDb);
		int choice = 0;
		do {
			System.out.println();
			System.out.println();
			System.out.println("****  Task Dashboard *****");
			System.out.println("Please Choose you option: ");
			System.out.println("1. Get All task");
			System.out.println("2. Search for task");
			System.out.println("3. Update the task to set it complete");
			System.out.println("4. Add task");
			System.out.println("5. Delete any task");
			System.out.println("6. Get all complete/incomplete task");
			System.out.println("7. to exit");
			System.out.println("****************************");
			System.out.println();
			choice = scanner.nextInt();
			switch(choice) {
			case 1:
				try {
					task = taskService.getAllTask(user);
					System.out.println("** Show All tasks **");
					for(Tasks t: task) {
						System.out.println(t);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("** Search for task **");
				System.out.println("Enter the title to search: ");
				String searchTask = scanner.next();
				try {
					System.out.println(taskService.searchForTask(user, searchTask));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("** To set the Task to complete **");
				System.out.println("Enter the title of task to set it complete: ");
				String updateTask = scanner.next();
				try {
					taskService.updateTasks(user, updateTask);
					System.out.println(updateTask + " task completed");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("** To Add new Task **");
				System.out.println("Enter title of Task");
				String title = scanner.next();
				System.out.println("Enter Description for the task");
				scanner.nextLine();
				String desc = scanner.nextLine();
			
				Tasks tas = new Tasks(title, desc, false);
				try {
					System.out.println( " Added successfully with id " + taskService.addTasks(tas, user));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("** To Delete a Task **");
				System.out.println("Enter title of Task to delete");
				scanner.nextLine();
				String toDelete = scanner.next();
				System.out.println("Are you sure you want to delete the task  enter: Y/YES or N/NO");
				String confirm = scanner.next();
				if(confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
					try {
						taskService.deleteTask(user, toDelete);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 6:
				System.out.println("Please enter choice");
				System.out.println("1. Get all complete task");
				System.out.println("2. Get all incomplete task");
				int choice2 = scanner.nextInt();
				switch(choice2) {
				case 1:
					try {
						task = taskService.getAllCompletTask(user);
						System.out.println("** Showing All Complete Tasks **");
						for(Tasks t: task) {
							System.out.println(t);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						task = taskService.getAllIncompletTask(user);
						System.out.println("** Showing All Incomplete Tasks **");
						for(Tasks t: task) {
							System.out.println(t);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
					default:
						System.out.println("Invalid input");
				}
				break;
			default:
					flag = false;
					
			}
		}while(flag);
	}
}
