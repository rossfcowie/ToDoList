package com.qa.ToDoListAPI.model.dto;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.ToDoListAPI.model.DTO.TaskDTO;
import com.qa.ToDoListAPI.model.data.Step;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskDTOTest {
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(TaskDTO.class).verify();
	}
	@Test
	public void ConstructorTest() {
		TaskDTO newTaskDTO = new TaskDTO(0, null, null, new ArrayList<Step>());
		newTaskDTO.toString();
	}

}
