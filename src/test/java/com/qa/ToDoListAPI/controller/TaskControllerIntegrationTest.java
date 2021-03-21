package com.qa.todolistapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todolistapi.controller.TaskController;
import com.qa.todolistapi.mapper.StepMapper;
import com.qa.todolistapi.mapper.TaskMapper;
import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.StepDTO;
import com.qa.todolistapi.model.dto.TaskDTO;
import com.qa.todolistapi.model.repository.StepRepository;
import com.qa.todolistapi.model.repository.TaskRepository;
import com.qa.todolistapi.service.StepService;
import com.qa.todolistapi.service.TaskService;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql", "classpath:test-data.sql" },
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
 class TaskControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	@Autowired 
	private TaskController taskController;

	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ObjectMapper objectMapper; 
	@Autowired
	private TaskMapper taskMapper;
	
	private Step validStep = new Step(1, "Remove Trash", new Task(1), false);
	private List<Step> validSteps = List.of(validStep);
	private Task validTask= new Task(1,"Take out the trash", "Remove rubbish", validSteps);
	private TaskDTO validTaskDTO = new TaskDTO(1,"Take out the trash", "Remove rubbish",1);
	private List<Task> validTasks = List.of(validTask);
	private List<TaskDTO> validTaskDtos = List.of(validTaskDTO);
	
	static ExtentReports  report = new ExtentReports("src/test/resources/reports/Task_Controller_Integration_Report.html", true);
    static ExtentTest test;
	
	@Test
	 void getAllTaskstest() throws Exception {
		test = report.startTest("Get all tasks test");
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/Task");
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validTaskDtos));
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	@Test
	 void createTaskTest() throws Exception {
		test = report.startTest("Create task test");
		 Step validStep2 = new Step(1, "Remove Trash", new Task(2), false);
		 List<Step> validSteps2 = List.of(validStep2);
		 Task validTask2= new Task(2,"Take out the trash", "Remove rubbish", validSteps2);
		 TaskDTO validTaskDTO2 = new TaskDTO(2,"Take out the trash", "Remove rubbish",1);
		 MockHttpServletRequestBuilder mockRequest = 
					MockMvcRequestBuilders.request(HttpMethod.POST, "/Task");
		 mockRequest.contentType(MediaType.APPLICATION_JSON); 
			mockRequest.content(objectMapper.writeValueAsString(validTask2));
			mockRequest.accept(MediaType.APPLICATION_JSON);
			ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
			ResultMatcher contentMatcher = MockMvcResultMatchers.content()
					.json(objectMapper.writeValueAsString(validTaskDTO2));
			ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");
			mvc.perform(mockRequest)
			   .andExpect(statusMatcher)
			   .andExpect(contentMatcher)
			   .andExpect(headerMatcher);
		
	}
	@Test
	 void deleteTaskTest() throws Exception {
		test = report.startTest("Delete task test");
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/Task/1");
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	@Test
	 void updateTaskTest() throws Exception {
		test = report.startTest("Update task test");
		 Step validStep2 = new Step(1, "Attach Leash", new Task(1), false);
		 List<Step> validSteps2 = List.of(validStep2);
		 Task validTask2= new Task(1,"Walk dog", "Attach Leash", validSteps2);
		 TaskDTO validTaskDTO2 = new TaskDTO(1,"Walk dog", "Attach Leash",1);
		 System.out.println(objectMapper.writeValueAsString(validTaskDTO2));
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/Task/1");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(validTask2));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validTaskDTO2));
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}

	@AfterEach
	 void end() {
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
    @AfterAll
     static void teardown() {
    	report.flush();
    }
}
