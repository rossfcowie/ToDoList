package com.qa.ToDoListAPI.model.dto;

import org.junit.jupiter.api.Test;

import com.qa.ToDoListAPI.model.DTO.StepDTO;
import com.qa.ToDoListAPI.model.data.Step;

import nl.jqno.equalsverifier.EqualsVerifier;

public class StepDTOTest {

	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(StepDTO.class).verify();
	}
	@Test
	public void ConstructorTest() {
		StepDTO newStepDTO = new StepDTO(0, null, 0, false);
		newStepDTO = new StepDTO(null, 0, false);
		newStepDTO = new StepDTO(null, false);
		newStepDTO.toString();
	}
	
}
