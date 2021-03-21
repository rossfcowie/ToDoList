package com.qa.todolistapi.model.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

 class StepTest {
	Task validTask = new Task(3,"Take out the trash", "Remove rubbish",new ArrayList<Step>());
	
	@Test
	 void testEquals() {
		EqualsVerifier.simple().forClass(Step.class).withPrefabValues(Task.class, validTask, new Task()).verify();
	}
	@Test
	 void ConstructorTest() {
		Step newStep = new Step(0, null, validTask, false);
		assertEquals(newStep.toString(),("Step [id=" + 0 + ", name=" + null + ", task=" + validTask + ", complete=" + false + "]"));
	}
}
