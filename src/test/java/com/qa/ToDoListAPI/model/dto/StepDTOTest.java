package com.qa.todolistapi.model.dto;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.qa.todolistapi.model.dto.StepDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

 class StepDTOTest {

	
	@Test
	 void testEquals() {
		EqualsVerifier.simple().forClass(StepDTO.class).verify();
	}
	@Test
	 void ConstructorTest() {
		StepDTO newStepDTO = new StepDTO(0, null, 0, false);
		assertEquals(newStepDTO.toString(),("StepDTO [id=" + 0 + ", name=" + null + ", task=" + 0 + ", complete=" + false + "]"));
		newStepDTO = new StepDTO(null, 0, false);
		assertEquals(newStepDTO.toString(),("StepDTO [id=" + 0 + ", name=" + null + ", task=" + 0 + ", complete=" + false + "]"));
		newStepDTO = new StepDTO(null, false);
		assertEquals(newStepDTO.toString(),("StepDTO [id=" + 0 + ", name=" + null + ", task=" + 0 + ", complete=" + false + "]"));
		
	}
	
}
