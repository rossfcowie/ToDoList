package com.qa.ToDoListAPI.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.ToDoListAPI.mapper.TaskMapper;
import com.qa.ToDoListAPI.model.DTO.TaskDTO;
import com.qa.ToDoListAPI.model.data.Task;
import com.qa.ToDoListAPI.model.repository.TaskRepository;

@SpringBootTest
public class TaskServiceTest {

	@Autowired
	private TaskService taskService;

	@MockBean
	private TaskRepository taskRepository;

	@MockBean
	private TaskMapper taskMapper;

	private List<Task> validTasks;
	private List<TaskDTO> validTaskDtos;

	private Task validTask;
	private TaskDTO validTaskDTO;

	@BeforeEach
	public void init() {
		validTask = new Task(3,"Take out the trash", "Remove rubbish",null);
		validTaskDTO = new TaskDTO(3,"Take out the trash", "Remove rubbish", 0);

		validTasks = new ArrayList<Task>();
		validTaskDtos = new ArrayList<TaskDTO>();
		validTasks.add(validTask);
		validTaskDtos.add(validTaskDTO);
	}

	@Test
	public void readAllTasksTest() {

		when(taskRepository.findAll()).thenReturn(validTasks);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);

		assertThat(validTaskDtos).isEqualTo(taskService.listAllTasks()); // true or false

		verify(taskRepository, times(1)).findAll();
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
	}

	@Test
	public void updateTaskTest() {
		Task newTask = new Task(3,"Walk dog", "Attach Leash",null);
		TaskDTO newTaskDTO = new TaskDTO(3,"Walk dog", "Attach Leash",0);
		when(taskRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validTask));
		when(taskRepository.save(Mockito.any(Task.class))).thenReturn(newTask);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(newTaskDTO);

		TaskDTO toTaskDTO = taskService.updateTask(validTask.getId(),newTask);
		
		assertThat(newTaskDTO).isEqualTo(toTaskDTO);

		verify(taskRepository, times(1)).findById(Mockito.any(Integer.class));
		verify(taskRepository, times(1)).save(Mockito.any(Task.class));
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
	}
	
	@Test
	public void createTaskTest() {
		
		when(taskRepository.save(Mockito.any(Task.class))).thenReturn(validTask);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);

		assertThat(validTaskDTO).isEqualTo(taskService.createTask(validTask)); // true or false

		
		verify(taskRepository, times(1)).save(Mockito.any(Task.class));
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
	}
	
	@Test
	public void deleteTaskTest() {
		
		when(taskRepository.existsById(Mockito.any(Integer.class))).thenReturn(true,false);
		
		assertThat(true).isEqualTo(taskService.deleteTask(validTask.getId())); // true or false
		
		verify(taskRepository, times(2)).existsById(Mockito.any(Integer.class));
	}
}
