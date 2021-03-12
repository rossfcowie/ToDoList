package com.qa.ToDoListAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.ToDoListAPI.mapper.StepMapper;
import com.qa.ToDoListAPI.model.DTO.StepDTO;
import com.qa.ToDoListAPI.model.data.Step;
import com.qa.ToDoListAPI.model.repository.StepRepository;
import com.qa.ToDoListAPI.service.StepService;

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
	
}
