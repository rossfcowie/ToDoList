package com.qa.ToDoListAPI.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.ToDoListAPI.mapper.StepMapper;
import com.qa.ToDoListAPI.model.DTO.StepDTO;
import com.qa.ToDoListAPI.model.data.Step;
import com.qa.ToDoListAPI.model.data.Task;
import com.qa.ToDoListAPI.model.repository.StepRepository;
import com.qa.ToDoListAPI.model.repository.TaskRepository;

@SpringBootTest
public class StepServiceIntegrationTest {
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
	
@BeforeAll
public static void setup() {
	validTask = new Task("Take out the trash", "Remove rubbish");
}
	
	@BeforeEach
	public void init() {
		taskRepository.deleteAll();
		validTask =taskRepository.save(validTask);
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
	public void readAllStepsTest() {
	List<StepDTO> stepsindb =stepService.readStepsInId(validTask.getId());
	assertThat(validStepDtos).isEqualTo(stepsindb); // true or false
	}
	
	@Test
	public void createStepTest() {
		Step validStep2 = new Step(2,"Open bin",validTask, true);
		StepDTO validStepDTO2 = stepMapper.mapToDTO(validStep2);
		assertThat(validStepDTO2).isEqualTo(stepService.createStep(validStep2)); // true or false
	}
	@Test
	public void deleteStepTest() {
		assertThat(true).isEqualTo(stepService.deleteStep(validStep.getId())); // true or false
	}
	@Test
	public void updateStepTest() {
		Step validStep2 = new Step(validStep.getId(),"Attach Leash", false);
		validStep2.setTask(validStep.getTask());
		StepDTO validStepDTO2 = stepMapper.mapToDTO(validStep2);
		StepDTO toStepDTO = stepService.updateStep(validStep.getId(),validStep2);
		assertThat(validStepDTO2).isEqualTo(toStepDTO);
	}
	@Test
	public void flipStepTest() {
	
}
}
