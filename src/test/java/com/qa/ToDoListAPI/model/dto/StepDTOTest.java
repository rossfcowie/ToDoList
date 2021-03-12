package com.qa.ToDoListAPI.model.dto;

import org.junit.jupiter.api.Test;

import com.qa.ToDoListAPI.model.DTO.StepDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class StepDTOTest {

	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(StepDTO.class).verify();
	}
}
