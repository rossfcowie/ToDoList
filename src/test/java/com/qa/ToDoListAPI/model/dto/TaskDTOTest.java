package com.qa.todolistapi.model.dto;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.dto.TaskDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

 class TaskDTOTest {
	@Test
	 void testEquals() {
		EqualsVerifier.simple().forClass(TaskDTO.class).verify();
	}
	@Test
	 void ConstructorTest() {
		TaskDTO newTaskDTO = new TaskDTO(0, null, null, new ArrayList<Step>());
		assertEquals(newTaskDTO.toString(),("TaskDTO [id=" + 0 + ", nameString=" + null + ", description=" + null + ", numSteps="+ 0 + "]"));
	}

}
