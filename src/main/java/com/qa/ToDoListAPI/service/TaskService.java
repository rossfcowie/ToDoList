package com.qa.ToDoListAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public TaskDTO createTask(Task task) {
	TaskDTO taskDTO = taskMapper.mapToDTO(taskRepository.save(task));
	return taskDTO;
}

public TaskDTO updateTask(Integer id,Task task) {
	Optional<Task> taskinDbOptional = taskRepository.findById(id);
	Task taskInDb;
	if(taskinDbOptional.isPresent()) {
		taskInDb = taskinDbOptional.get();
	}else {
		throw new TaskNotFoundException();
	}
	taskInDb.setId(task.getId());
	taskInDb.setName(task.getName());
	taskInDb.setDescrition(task.getDescrition());
	taskInDb.setSteps(task.getSteps());
	
	return taskMapper.mapToDTO(taskInDb);
}
public boolean deleteTask(Integer id) {
	if(!taskRepository.existsById(id)) {
		throw new TaskNotFoundException();
	}
	taskRepository.deleteById(id);
	
	boolean exists = taskRepository.existsById(id);
	
	return !exists;
}
}
