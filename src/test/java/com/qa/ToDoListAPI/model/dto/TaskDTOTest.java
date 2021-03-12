package com.qa.ToDoListAPI.model.dto;

import org.junit.jupiter.api.Test;

import com.qa.ToDoListAPI.model.DTO.TaskDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskDTOTest {
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(TaskDTO.class).verify();
	}
	

}
