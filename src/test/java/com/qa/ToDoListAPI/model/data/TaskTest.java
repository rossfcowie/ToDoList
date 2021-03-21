package com.qa.todolistapi.model.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.todolistapi.model.DTO.TaskDTO;
import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.data.Task;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Task.class).withPrefabValues(Step.class, new Step("a", false), new Step("b", true)).verify();
	}
	@Test
	public void ConstructorTest() {
		Task newTask = new Task(0, null, null, new ArrayList<Step>());
		assertThat(newTask.toString().equals("Task [id=" + 0 + ", nameString=" + "" + ", descrition=" + "" + ", steps=" + "[]" + "]"));

	}
	
}
