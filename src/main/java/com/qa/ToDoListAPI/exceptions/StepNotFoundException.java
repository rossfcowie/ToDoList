package com.qa.ToDoListAPI.exceptions;

import javax.persistence.PersistenceException;

public class StepNotFoundException extends PersistenceException {

	public StepNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StepNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
