package com.qa.todolistapi.mapper;

import org.springframework.stereotype.Component;

import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.StepDTO;

@Component
public class StepMapper {
	
	public StepDTO mapToDTO(Step step) {
		StepDTO stepDTO;
		if(step.getTask()  != null) {
			stepDTO = new StepDTO(step.getId(),step.getName(),step.getTask().getId(), step.isComplete());
		}else {
			stepDTO = new StepDTO(step.getId(),step.getName(),0, step.isComplete());
		}
		
		return stepDTO;
	}
	
	public Step mapToStep(StepDTO stepDTO) {
		return new Step(stepDTO.getId(),stepDTO.getName(),new Task(stepDTO.getTask()),stepDTO.isComplete());
	}
}
