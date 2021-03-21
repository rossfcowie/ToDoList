package com.qa.todolistapi.model.dto;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.qa.todolistapi.model.DTO.StepDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class StepDTOTest {

	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(StepDTO.class).verify();
	}
	@Test
	public void ConstructorTest() {
		StepDTO newStepDTO = new StepDTO(0, null, 0, false);
		assertEquals(newStepDTO.toString(),("StepDTO [id=" + 0 + ", name=" + "" + ", task=" + null + ", complete=" + false + "]"));
		newStepDTO = new StepDTO(null, 0, false);
		assertEquals(newStepDTO.toString(),("StepDTO [id=" + 0 + ", name=" + "" + ", task=" + null + ", complete=" + false + "]"));
		newStepDTO = new StepDTO(null, false);
		assertEquals(newStepDTO.toString(),("StepDTO [id=" + 0 + ", name=" + "" + ", task=" + null + ", complete=" + false + "]"));
		
	}
	
}
