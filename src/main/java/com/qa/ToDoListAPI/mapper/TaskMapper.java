package com.qa.ToDoListAPI.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.ToDoListAPI.model.DTO.TaskDTO;
import com.qa.ToDoListAPI.model.data.Task;

@Component
public class TaskMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public TaskMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public TaskDTO mapToDTO(Task task) {
		TaskDTO taskDTO = new TaskDTO(task.getId(), task.getNameString(), task.getDescrition(), task.getSteps());
		
		return taskDTO;
	}
}
