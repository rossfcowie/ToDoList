package com.qa.todolistapi.exceptions;

import javax.persistence.PersistenceException;

public class StepNotFoundException extends PersistenceException {

	private static final long serialVersionUID = -2132777297110402496L;

	public StepNotFoundException() {
		super();
	}

	public StepNotFoundException(String message) {
		super(message);
	}

}
