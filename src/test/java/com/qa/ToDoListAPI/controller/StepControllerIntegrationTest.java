package com.qa.todolistapi.controller;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
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
import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.StepDTO;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql", "classpath:test-data.sql" },
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
 class StepControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;



	@Autowired
	private ObjectMapper objectMapper; 


	private Step validStep = new Step(1, "Remove Trash", new Task(1), false);
	private StepDTO validStepDTO = new StepDTO(1, "Remove Trash",1, false);

	private List<Step> validSteps = List.of(validStep);
	private List<StepDTO> validStepDtos = List.of(validStepDTO);
	private Task validTask= new Task(1,"Take out the trash", "Remove rubbish", validSteps);

    static ExtentReports  report = new ExtentReports("src/test/resources/reports/Step_Controller_Integration_Report.html", true);
    static ExtentTest test;
	
    @AfterAll
     static void teardown() {
    	report.flush();
    }
    
	@Test
	 void createStepTest() throws Exception {
		test = report.startTest("Create step test");
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
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	@Test
	 void getAllStepsInTaskTest() throws Exception {
		test = report.startTest("Get steps in task test");
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/Step/1");
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validStepDtos));
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	@Test
	 void deleteStepTest() throws Exception {
		test = report.startTest("Delete step test");
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/Step/1");
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	@Test
	 void updateStepTest() throws Exception {
		test = report.startTest("Update step test");
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
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	@Test
	 void flipStepTest() throws Exception {
		test = report.startTest("Flip step status test");
		StepDTO expectedStep = new StepDTO(1, "Remove Trash",1, true);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/Step/f/1");
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedStep));
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
}
