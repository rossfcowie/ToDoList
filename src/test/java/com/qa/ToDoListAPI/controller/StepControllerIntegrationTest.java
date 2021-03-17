package com.qa.ToDoListAPI.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.qa.ToDoListAPI.mapper.StepMapper;
import com.qa.ToDoListAPI.model.DTO.StepDTO;
import com.qa.ToDoListAPI.model.data.Step;
import com.qa.ToDoListAPI.model.data.Task;
import com.qa.ToDoListAPI.model.repository.StepRepository;
import com.qa.ToDoListAPI.service.StepService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql", "classpath:test-data.sql" },
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class StepControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired 
	private StepController stepController;

	@Autowired
	private StepService stepService;
	@Autowired
	private StepRepository stepRepository;
	@Autowired
	private ObjectMapper objectMapper; 
	@Autowired
	private StepMapper stepMapper;

	private Step validStep = new Step(1, "Remove Trash", new Task(1), false);
	private StepDTO validStepDTO = new StepDTO(1, "Remove Trash",1, false);

	private List<Step> validSteps = List.of(validStep);
	private List<StepDTO> validStepDtos = List.of(validStepDTO);
	private Task validTask= new Task(1,"Take out the trash", "Remove rubbish", validSteps);

	
	
	@Test
	public void createStepTest() throws Exception {
		Step stepToSave = new Step("Attach leash", false);
		stepToSave.setTask(validTask);
		StepDTO expectedStep = new StepDTO(2,"Attach leash",1, false);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/Step/1");
		mockRequest.contentType(MediaType.APPLICATION_JSON); 
		mockRequest.content(objectMapper.writeValueAsString(stepToSave));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedStep));
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher)
		   .andExpect(headerMatcher);
	}
	@Test
	public void getAllStepsInTaskTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/Step/1");
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validStepDtos));
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	@Test
	public void deleteStepTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/Step/1");
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	@Test
	public void updateStepTest() throws Exception {
		Step stepToSave = new Step(1,"Attach leash", new Task(1), false);
		StepDTO expectedStep = new StepDTO(1,"Attach leash",1, false);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/Step/1");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(stepToSave));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedStep));
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	@Test
	public void flipStepTest() throws Exception {
		StepDTO expectedStep = new StepDTO(1, "Remove Trash",1, true);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/Step/f/1");
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedStep));
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
}
