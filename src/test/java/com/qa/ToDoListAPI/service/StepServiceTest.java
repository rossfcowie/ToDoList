package com.qa.ToDoListAPI.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.ToDoListAPI.mapper.StepMapper;
import com.qa.ToDoListAPI.model.DTO.StepDTO;
import com.qa.ToDoListAPI.model.data.Step;
import com.qa.ToDoListAPI.model.repository.StepRepository;

@SpringBootTest
public class StepServiceTest {

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
	public void readAllStepsTest() {

		when(stepRepository.findForTask(0)).thenReturn(validSteps);
		when(stepMapper.mapToDTO(Mockito.any(Step.class))).thenReturn(validStepDTO);

		assertThat(validStepDtos).isEqualTo(stepService.readStepsInId(0)); // true or false

		verify(stepRepository, times(1)).findForTask(0);
		verify(stepMapper, times(1)).mapToDTO(Mockito.any(Step.class));
	}
	
	@Test
	public void createStepTest() {
		
		when(stepRepository.save(Mockito.any(Step.class))).thenReturn(validStep);
		when(stepMapper.mapToDTO(Mockito.any(Step.class))).thenReturn(validStepDTO);

		assertThat(validStepDTO).isEqualTo(stepService.createStep(validStep)); // true or false

		
		verify(stepRepository, times(1)).save(Mockito.any(Step.class));
		verify(stepMapper, times(1)).mapToDTO(Mockito.any(Step.class));
	}
	
	@Test
	public void deleteStepTest() {
		
		when(stepRepository.existsById(Mockito.any(Integer.class))).thenReturn(true,false);
		
		assertThat(true).isEqualTo(stepService.deleteStep(validStep.getId())); // true or false
		
		verify(stepRepository, times(2)).existsById(Mockito.any(Integer.class));
	}
	
	@Test
	public void updateStepTest() {
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
	}
	
	@Test
	public void flipStepTest() {
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
	}
}
