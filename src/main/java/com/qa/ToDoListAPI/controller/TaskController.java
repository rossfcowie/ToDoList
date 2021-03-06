package com.qa.todolistapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.TaskDTO;
import com.qa.todolistapi.service.TaskService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody Task task){
		TaskDTO newTaskDTO= taskService.createTask(task);
		HttpHeaders headers= new HttpHeaders();
		headers.add("Location", String.valueOf(newTaskDTO.getId()));
		return new ResponseEntity<>(newTaskDTO,headers,HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") int id,@RequestBody Task task) {
		task.setId(id);
		TaskDTO newTaskDTO= taskService.updateTask(id, task);
		
		return new ResponseEntity<>(newTaskDTO, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTask(@PathVariable("id") int id) {		
		return new ResponseEntity<>(taskService.deleteTask(id), HttpStatus.OK);
	}
	
}
