package com.qa.ToDoListAPI.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.ToDoListAPI.model.DTO.StepDTO;
import com.qa.ToDoListAPI.model.data.Step;

@Component
public class StepMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public StepMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public StepDTO mapToDTO(Step step) {
		return this.modelMapper.map(step, StepDTO.class);
	}
}
