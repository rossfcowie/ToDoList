package com.qa.todolistapi.exceptions;

import javax.persistence.PersistenceException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No task was found at the given ID.")

public class TaskNotFoundException extends PersistenceException  {

	private static final long serialVersionUID = 2603531513827157090L;


	public TaskNotFoundException() {
		super();
	}

	public TaskNotFoundException(String message) {
		super(message);
	}
}
