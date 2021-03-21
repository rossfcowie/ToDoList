package com.qa.todolistapi.model.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.data.Task;

import nl.jqno.equalsverifier.EqualsVerifier;

public class StepTest {
	Task validTask = new Task(3,"Take out the trash", "Remove rubbish",new ArrayList<Step>());
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Step.class).withPrefabValues(Task.class, validTask, new Task()).verify();
	}
	@Test
	public void ConstructorTest() {
		Step newStep = new Step(0, null, validTask, false);
		assertThat(newStep.toString().equals("Step [id=" + 0 + ", name=" + "" + ", task=" + validTask + ", complete=" + false + "]"));
	}
}
