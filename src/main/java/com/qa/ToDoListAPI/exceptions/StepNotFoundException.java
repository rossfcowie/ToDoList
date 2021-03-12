package com.qa.ToDoListAPI.exceptions;

import javax.persistence.EntityNotFoundException;

public class StepNotFoundException extends EntityNotFoundException {

	public StepNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StepNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
