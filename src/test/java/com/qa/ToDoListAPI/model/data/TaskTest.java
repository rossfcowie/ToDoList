package com.qa.todolistapi.model.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

 class TaskTest {

	@Test
	 void testEquals() {
		EqualsVerifier.simple().forClass(Task.class).withPrefabValues(Step.class, new Step("a", false), new Step("b", true)).verify();
	}
	@Test
	 void ConstructorTest() {
		Task newTask = new Task(0, null, null, new ArrayList<Step>());
		assertEquals(newTask.toString(),("Task [id=" + 0 + ", nameString=" + null + ", descrition=" + null + ", steps=" + "[]" + "]"));

	}
	
}
