package com.qa.ToDoListAPI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ToDoListAPI.mapper.TaskMapper;
import com.qa.ToDoListAPI.model.DTO.TaskDTO;
import com.qa.ToDoListAPI.model.data.Task;
import com.qa.ToDoListAPI.model.repository.TaskRepository;

@Service
public class TaskService {
private TaskRepository taskRepository;
private TaskMapper taskMapper;
@Autowired
public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
	super();
	this.taskRepository = taskRepository;
	this.taskMapper = taskMapper;
}

public List<TaskDTO> listAllTasks(){
	List<Task> tasks = taskRepository.findAll();
	List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();
	
	tasks.forEach(task->taskDTOs.add(taskMapper.mapToDTO(task)));
	return taskDTOs;
}

}
