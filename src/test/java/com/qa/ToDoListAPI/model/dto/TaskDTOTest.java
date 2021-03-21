package com.qa.todolistapi.model.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.todolistapi.model.DTO.TaskDTO;
import com.qa.todolistapi.model.data.Step;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskDTOTest {
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(TaskDTO.class).verify();
	}
	@Test
	public void ConstructorTest() {
		TaskDTO newTaskDTO = new TaskDTO(0, null, null, new ArrayList<Step>());
		assertThat(newTaskDTO.toString().equals("TaskDTO [id=" + 0 + ", nameString=" + "" + ", description=" + "" + ", numSteps="+ 0 + "]"));

	}

}
