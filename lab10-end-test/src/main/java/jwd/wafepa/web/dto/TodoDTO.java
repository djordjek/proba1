package jwd.wafepa.web.dto;

import java.util.Date;

import jwd.wafepa.model.Todo;
import jwd.wafepa.model.enumeration.Status;

public class TodoDTO {
	
	private Long id;
	private String task;
	private Date date;
	private Status status;
	private UserTodoDTO user;
	

	public TodoDTO() {
	}

	public TodoDTO(Long id, String task, Date date, Status status, UserTodoDTO user) {
		super();
		this.id = id;
		this.task = task;
		this.date = date;
		this.status = status;
		this.user = user;
	}
	
	public TodoDTO(Todo todo){
		this.id = todo.getId();
		this.task = todo.getTask();
		this.date = todo.getDate();
		this.status = todo.getStatus();
		this.user = new UserTodoDTO(todo.getUser());
	}



	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public UserTodoDTO getUser() {
		return user;
	}
	public void setUser(UserTodoDTO user) {
		this.user = user;
	}

}
