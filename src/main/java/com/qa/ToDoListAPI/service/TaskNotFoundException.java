package com.qa.ToDoListAPI.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No task was found at the given ID.")
public class TaskNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = -6705326510114331656L;

	public TaskNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}
