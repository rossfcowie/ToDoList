package com.qa.todolistapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todolistapi.mapper.StepMapper;
import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.dto.StepDTO;
import com.qa.todolistapi.model.repository.StepRepository;
import com.qa.todolistapi.service.StepService;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SpringBootTest
 class StepServiceTest {

	@Autowired
	private StepService stepService;

	@MockBean
	private StepRepository stepRepository;

	@MockBean
	private StepMapper stepMapper;

	private List<Step> validSteps;
	private List<StepDTO> validStepDtos;

	private Step validStep;
	private StepDTO validStepDTO;
	static ExtentReports report = new ExtentReports("src/test/resources/reports/Step_Service_Unit_Report.html",
			true);
	static ExtentTest test;
	@AfterAll
	 static void teardown() {
		report.flush();
	}
	@BeforeEach
	 void init() {
		validStep = new Step(0,"Remove Trash", false);
		validStepDTO = new StepDTO(0,"Remove Trash", false);

		validSteps = new ArrayList<Step>();
		validStepDtos = new ArrayList<StepDTO>();
		validSteps.add(validStep);
		validStepDtos.add(validStepDTO);
	}
	@Test
	 void readAllStepsTest() {
		test = report.startTest("Get steps in task test");

		when(stepRepository.findForTask(0)).thenReturn(validSteps);
		when(stepMapper.mapToDTO(Mockito.any(Step.class))).thenReturn(validStepDTO);
		assertThat(validStepDtos).isEqualTo(stepService.readStepsInId(0)); // true or false
		verify(stepRepository, times(1)).findForTask(0);
		verify(stepMapper, times(1)).mapToDTO(Mockito.any(Step.class));
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
	@Test
	 void createStepTest() {
		test = report.startTest("Create step test");
		
		when(stepRepository.save(Mockito.any(Step.class))).thenReturn(validStep);
		when(stepMapper.mapToDTO(Mockito.any(Step.class))).thenReturn(validStepDTO);
		assertThat(validStepDTO).isEqualTo(stepService.createStep(validStep)); // true or false	
		verify(stepRepository, times(1)).save(Mockito.any(Step.class));
		verify(stepMapper, times(1)).mapToDTO(Mockito.any(Step.class));
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
	@Test
	 void deleteStepTest() {
		test = report.startTest("Delete step test");
		
		when(stepRepository.existsById(Mockito.any(Integer.class))).thenReturn(true,false);
		
		assertThat(stepService.deleteStep(validStep.getId())).isTrue(); // true or false
		
		verify(stepRepository, times(2)).existsById(Mockito.any(Integer.class));
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
	@Test
	 void updateStepTest() {
		test = report.startTest("Update step test");
		Step validStep2 = new Step(0,"Attach Leash", false);
		StepDTO validStepDTO2 = new StepDTO(0,"Attach Leash", false);
		when(stepRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validStep));
		when(stepRepository.save(Mockito.any(Step.class))).thenReturn(validStep2);
		when(stepMapper.mapToDTO(Mockito.any(Step.class))).thenReturn(validStepDTO2);

		StepDTO toStepDTO = stepService.updateStep(validStep.getId(),validStep2);
		
		assertThat(validStepDTO2).isEqualTo(toStepDTO);

		verify(stepRepository, times(1)).findById(Mockito.any(Integer.class));
		verify(stepRepository, times(1)).save(Mockito.any(Step.class));
		verify(stepMapper, times(1)).mapToDTO(Mockito.any(Step.class));
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
	@Test
	 void flipStepTest() {
		test = report.startTest("Flip step status test");
		Step validStep2 = new Step(0,"Remove Trash", true);
		StepDTO validStepDTO2 = new StepDTO(0,"Remove Trash", true);
		when(stepRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validStep));
		when(stepRepository.save(Mockito.any(Step.class))).thenReturn(validStep2);
		when(stepMapper.mapToDTO(Mockito.any(Step.class))).thenReturn(validStepDTO2);
		StepDTO toStepDTO = stepService.updateStep(validStep.getId());
		assertThat(validStepDTO2).isEqualTo(toStepDTO);
		verify(stepRepository, times(1)).findById(Mockito.any(Integer.class));
		verify(stepRepository, times(1)).save(Mockito.any(Step.class));
		verify(stepMapper, times(1)).mapToDTO(Mockito.any(Step.class));
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
}
