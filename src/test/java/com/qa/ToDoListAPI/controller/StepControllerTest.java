package com.qa.todolistapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.todolistapi.controller.StepController;
import com.qa.todolistapi.mapper.StepMapper;
import com.qa.todolistapi.model.DTO.StepDTO;
import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.service.StepService;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
@SpringBootTest
public class StepControllerTest {

	@Autowired
	private StepController stepController;

	@MockBean
	private StepService stepService;

	@MockBean
	private StepMapper stepMapper;

	private List<Step> validSteps;
	private List<StepDTO> validStepDtos;

	private Step validStep;
	private StepDTO validStepDTO;

    static ExtentReports  report = new ExtentReports("src/test/resources/reports/Step_Controller_Unit_Report.html", true);
    static ExtentTest test;
	@BeforeEach
	public void init() {
		validStep = new Step(0,"Remove Trash", false);
		validStepDTO = new StepDTO(0,"Remove Trash", false);

		validSteps = new ArrayList<Step>();
		validStepDtos = new ArrayList<StepDTO>();
		validSteps.add(validStep);
		validStepDtos.add(validStepDTO);
	}
	@Test
	public void getAllStepsTest() {
		test = report.startTest("Get steps in task test");
		when(stepService.readStepsInId(0)).thenReturn(validStepDtos);
		ResponseEntity<List<StepDTO>> response = new ResponseEntity<List<StepDTO>>(validStepDtos,HttpStatus.OK);
		assertThat(response).isEqualTo(stepController.getStepsFromId(0));
		verify(stepService, times(1)).readStepsInId(0);
	}
	
	@Test
	public void createStepTest() {
		test = report.startTest("Create step test");		
		when(stepService.createStep(Mockito.any(Step.class))).thenReturn(validStepDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validStep.getId()));
		ResponseEntity<StepDTO> response = new ResponseEntity<StepDTO>(validStepDTO,headers,HttpStatus.CREATED);
		assertThat(response).isEqualTo(stepController.createStep(0, validStep));
		verify(stepService, times(1)).createStep(Mockito.any(Step.class));
	}
	
	@Test
	public void deleteStepTest() {
		test = report.startTest("Delete step test");
		when(stepService.deleteStep(Mockito.any(Integer.class))).thenReturn(true);
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.OK);
		assertThat(response).isEqualTo(stepController.deleteStep(validStep.getId()));
		verify(stepService, times(1)).deleteStep(Mockito.any(Integer.class));
	}
	@Test
	public void updateStepTest() {
		test = report.startTest("Update step test");
		when(stepService.updateStep(Mockito.any(Integer.class),Mockito.any(Step.class))).thenReturn(validStepDTO);
		
		ResponseEntity<StepDTO> response = new ResponseEntity<StepDTO>(validStepDTO,HttpStatus.OK);
		assertThat(response).isEqualTo(stepController.updateStep(validStep.getId(),validStep));
		verify(stepService, times(1)).updateStep(Mockito.any(Integer.class),Mockito.any(Step.class));
	}
	
	@Test
	public void flipStepTest() {
		test = report.startTest("Flip step status test");
		StepDTO flipStepDTO = new StepDTO(0,"Remove Trash", true);
		when(stepService.updateStep(Mockito.any(Integer.class))).thenReturn(flipStepDTO);
		
		ResponseEntity<StepDTO> response = new ResponseEntity<StepDTO>(flipStepDTO,HttpStatus.OK);
		assertThat(response).isEqualTo(stepController.flipStep(validStep.getId()));
		verify(stepService, times(1)).updateStep(Mockito.any(Integer.class));
	}
	@AfterEach
	public void end() {
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
    @AfterAll
    public static void teardown() {
    	report.flush();
    }
    
}
