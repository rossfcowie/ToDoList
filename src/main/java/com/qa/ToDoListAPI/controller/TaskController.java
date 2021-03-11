package com.qa.ToDoListAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.ToDoListAPI.model.DTO.TaskDTO;
import com.qa.ToDoListAPI.service.TaskService;

@RestController
@RequestMapping(path="/Task")
public class TaskController {

	private TaskService taskService;
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAllTasks(){
		List<TaskDTO> dtos= taskService.listAllTasks();
		return new ResponseEntity<List<TaskDTO>>(dtos, HttpStatus.OK);
	}
	
}
