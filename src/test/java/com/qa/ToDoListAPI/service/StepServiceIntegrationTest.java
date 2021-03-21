package com.qa.todolistapi.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.todolistapi.mapper.StepMapper;
import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.StepDTO;
import com.qa.todolistapi.model.repository.StepRepository;
import com.qa.todolistapi.model.repository.TaskRepository;
import com.qa.todolistapi.service.StepService;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SpringBootTest
 class StepServiceIntegrationTest {
	@Autowired
	private StepService stepService;

	@Autowired
	private StepRepository stepRepository;

	@Autowired
	private StepMapper stepMapper;

	@Autowired
	private TaskRepository taskRepository;

	private List<Step> validSteps;
	private List<StepDTO> validStepDtos;

	private Step validStep;
	private StepDTO validStepDTO;
	private static Task validTask;

	static ExtentReports report = new ExtentReports("Documentation/reports/Step_Service_Integration_Report.html",
			true);
	static ExtentTest test;

	@BeforeAll
	 static void setup() {
		validTask = new Task("Take out the trash", "Remove rubbish");
	}

	@AfterAll
	 static void teardown() {
		report.flush();
	}

	@BeforeEach
	 void init() {
		taskRepository.deleteAll();
		validTask = taskRepository.save(validTask);
		validStep = new Step("Remove Trash", false);
		validStep.setTask(validTask);
		validStepDTO = new StepDTO("Remove Trash", false);
		stepRepository.deleteAll();
		validStep = stepRepository.save(validStep);
		validStepDTO = stepMapper.mapToDTO(validStep);
		validSteps = new ArrayList<Step>();
		validStepDtos = new ArrayList<StepDTO>();
		validSteps.add(validStep);
		validStepDtos.add(validStepDTO);
	}

	@Test
	 void readAllStepsTest() {
		test = report.startTest("Get steps in task test");
		List<StepDTO> stepsindb = stepService.readStepsInId(validTask.getId());
		assertThat(validStepDtos).isEqualTo(stepsindb); 
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}

	@Test
	 void createStepTest() {
		test = report.startTest("Create step test");
		Step validStep2 = new Step(2, "Open bin", validTask, true);
		StepDTO validStepDTO2 = stepMapper.mapToDTO(validStep2);
		assertThat(validStepDTO2).isEqualTo(stepService.createStep(validStep2)); // true or false
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
	@Test
	 void deleteStepTest() {
		test = report.startTest("Delete step test");
		assertThat(stepService.deleteStep(validStep.getId())).isTrue(); // true or false
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}

	@Test
	 void updateStepTest() {
		test = report.startTest("Update step test");
		Step validStep2 = new Step(validStep.getId(), "Attach Leash", false);
		validStep2.setTask(validStep.getTask());
		StepDTO validStepDTO2 = stepMapper.mapToDTO(validStep2);
		StepDTO toStepDTO = stepService.updateStep(validStep.getId(), validStep2);
		assertThat(validStepDTO2).isEqualTo(toStepDTO);
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}

	@Test
	 void flipStepTest() {
		test = report.startTest("Flip step status test");
		Step validStep2 = new Step(validStep.getId(), validStep.getName(), validStep.getTask(),
				!validStep.isComplete());
		StepDTO validStepDTO2 = stepMapper.mapToDTO(validStep2);
		StepDTO toStepDTO = stepService.updateStep(validStep.getId());
		assertThat(validStepDTO2).isEqualTo(toStepDTO);
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
}
