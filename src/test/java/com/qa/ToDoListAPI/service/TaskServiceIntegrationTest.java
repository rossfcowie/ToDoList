package com.qa.ToDoListAPI.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.ToDoListAPI.mapper.TaskMapper;
import com.qa.ToDoListAPI.model.DTO.TaskDTO;
import com.qa.ToDoListAPI.model.data.Step;
import com.qa.ToDoListAPI.model.data.Task;
import com.qa.ToDoListAPI.model.repository.TaskRepository;

@SpringBootTest
public class TaskServiceIntegrationTest {
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskMapper taskMapper;
	
	
	private List<Task> validTasks;
	private List<TaskDTO> validTaskDtos;

	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		ArrayList<Step> arrlst =new ArrayList<Step>();
		validTask = new Task("Take out the trash", "Remove rubbish");
		
		taskRepository.deleteAll();
		validTask =taskRepository.save(validTask);
		validTaskDTO = taskMapper.mapToDTO(validTask);
		
		validTasks = new ArrayList<Task>();
		validTaskDtos = new ArrayList<TaskDTO>();
		validTasks.add(validTask);
		validTaskDtos.add(validTaskDTO);
	}
	
	@Test
	public void readAllTasksTest() {
		List<TaskDTO> tasksInDb = taskService.listAllTasks();
		assertThat(validTaskDtos).isEqualTo(tasksInDb); // true or false
	}
	
	@Test
	public void updateTaskTest() {
		Task newTask = new Task(validTask.getId(),"Walk dog", "Attach Leash", validTask.getSteps());
		TaskDTO newTaskDTO = taskMapper.mapToDTO(newTask);
		TaskDTO toTaskDTO = taskService.updateTask(validTask.getId(),newTask);
		assertThat(newTaskDTO).isEqualTo(toTaskDTO);
	}
	@Test
	public void deleteTaskTest() {
		assertThat(true).isEqualTo(taskService.deleteTask(validTask.getId())); // true or false	
	}
	@Test
	public void createStepTest() {
		Task newTask = new Task(validTask.getId(),"Walk dog", "Attach Leash", validTask.getSteps());
		TaskDTO newTaskDTO = taskMapper.mapToDTO(newTask);
		assertThat(newTaskDTO).isEqualTo(taskService.createTask(newTask)); // true or false

	}
	
}
