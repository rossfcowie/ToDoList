package com.qa.ToDoListAPI.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.ToDoListAPI.model.DTO.TaskDTO;
import com.qa.ToDoListAPI.model.data.Task;
import com.qa.ToDoListAPI.service.TaskService;

@SpringBootTest
public class TaskControllerTest {

	@Autowired
	private TaskController taskController;
	
	@MockBean
	private TaskService taskService;
	
	private List<Task> validTasks;
	private List<TaskDTO> validTaskDtos;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		validTask = new Task("Take out the trash", "Remove rubbish");
		validTaskDTO = new TaskDTO("Take out the trash", "Remove rubbish",0);
		
		validTasks = new ArrayList<Task>();
		validTaskDtos = new ArrayList<TaskDTO>();
		validTasks.add(validTask);
		validTaskDtos.add(validTaskDTO);
	}
	@Test
	public void getAllTaskstest() {
		when(taskService.listAllTasks()).thenReturn(validTaskDtos);
		ResponseEntity<List<TaskDTO>> response = new ResponseEntity<List<TaskDTO>>(validTaskDtos,HttpStatus.OK);
		assertThat(response).isEqualTo(taskController.getAllTasks());
		verify(taskService, times(1)).listAllTasks();
	}
	@Test
	public void createTaskTest() {
		
		when(taskService.createTask(Mockito.any(Task.class))).thenReturn(validTaskDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validTaskDTO.getId()));
		ResponseEntity<TaskDTO> response = new ResponseEntity<TaskDTO>(validTaskDTO,headers,HttpStatus.CREATED);
		assertThat(response).isEqualTo(taskController.createTask(validTask));
		verify(taskService, times(1)).createTask(Mockito.any(Task.class));
	}
	@Test
	public void deleteTaskTest() {
		when(taskService.deleteTask(Mockito.any(Integer.class))).thenReturn(true);
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.OK);
		assertThat(response).isEqualTo(taskController.deleteTask(validTask.getId()));
		verify(taskService, times(1)).deleteTask(Mockito.any(Integer.class));
	}
	@Test
	public void updateTaskTest() {
		when(taskService.updateTask(Mockito.any(Integer.class),Mockito.any(Task.class))).thenReturn(validTaskDTO);
		
		ResponseEntity<TaskDTO> response = new ResponseEntity<TaskDTO>(validTaskDTO,HttpStatus.OK);
		assertThat(response).isEqualTo(taskController.updateTask(validTask.getId(),validTask));
		verify(taskService, times(1)).updateTask(Mockito.any(Integer.class),Mockito.any(Task.class));
		
	}
}
