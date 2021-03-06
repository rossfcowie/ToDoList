package com.qa.todolistapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todolistapi.exceptions.TaskNotFoundException;
import com.qa.todolistapi.mapper.TaskMapper;
import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.TaskDTO;
import com.qa.todolistapi.model.repository.TaskRepository;

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
	List<TaskDTO> taskDTOs = new ArrayList<>();
	tasks.forEach(task->taskDTOs.add(taskMapper.mapToDTO(task)));
	return taskDTOs;
}
public TaskDTO createTask(Task task) {
	return taskMapper.mapToDTO(taskRepository.save(task));

}

public TaskDTO updateTask(Integer id,Task task) {
	Optional<Task> taskinDbOptional = taskRepository.findById(id);
	Task taskInDb;
	if(taskinDbOptional.isPresent()) {
		taskInDb = taskinDbOptional.get();
	}else {
		throw new TaskNotFoundException();
	}

	taskInDb.setName(task.getName());
	taskInDb.setDescription(task.getDescription());
	taskRepository.save(taskInDb);
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
