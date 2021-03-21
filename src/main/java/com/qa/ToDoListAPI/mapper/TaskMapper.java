package com.qa.todolistapi.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.TaskDTO;

@Component
public class TaskMapper {
	
	public TaskDTO mapToDTO(Task task) {
		return new TaskDTO(task.getId(), task.getName(), task.getDescription(), task.getSteps());
	}
	
	public Task mapToStep(TaskDTO taskDTO) {
		return new Task(taskDTO.getId(),taskDTO.getNameString(),taskDTO.getDescription(),new ArrayList<>());
	}
}