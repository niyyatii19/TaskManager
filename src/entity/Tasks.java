package entity;

public class Tasks {

	private int id;
	private String title;
	private String text;
	private String assignedTo;
	private boolean taskCompleted;
	
	public Tasks() {
	
	}

	public Tasks(int id, String title, String text, String assignedTo, boolean taskCompleted) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.assignedTo = assignedTo;
		this.taskCompleted = taskCompleted;
	}

	
	public Tasks(String title, String text, boolean taskCompleted) {
		super();
		this.title = title;
		this.text = text;
		this.taskCompleted = taskCompleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public boolean isTaskCompleted() {
		return taskCompleted;
	}

	public void setTaskCompleted(boolean taskCompleted) {
		this.taskCompleted = taskCompleted;
	}

	@Override
	public String toString() {
		return "Tasks [id=" + id + ", title=" + title + ", text=" + text 
				+ ", taskCompleted=" + taskCompleted + "]";
	}
	
	
	
}
