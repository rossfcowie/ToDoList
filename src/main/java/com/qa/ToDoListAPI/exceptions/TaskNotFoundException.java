package com.qa.todolistapi.exceptions;

import javax.persistence.PersistenceException;

public class TaskNotFoundException extends PersistenceException  {

	private static final long serialVersionUID = 2603531513827157090L;


	public TaskNotFoundException() {
		super();
	}

	public TaskNotFoundException(String message) {
		super(message);
	}
}
