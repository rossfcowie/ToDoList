package com.qa.ToDoListAPI.model.data;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Task.class).withPrefabValues(Step.class, new Step("a", false), new Step("b", true)).verify();
	}
	
}
