package com.qa.ToDoListAPI.service;

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
