package com.qa.todolistapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todolistapi.mapper.TaskMapper;
import com.qa.todolistapi.model.DTO.TaskDTO;
import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.repository.TaskRepository;
import com.qa.todolistapi.service.TaskService;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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
	static ExtentReports report = new ExtentReports("src/test/resources/reports/Task_Service_Unit_Report.html", true);
	static ExtentTest test;

	@BeforeEach
	public void init() {
		validTask = new Task(3, "Take out the trash", "Remove rubbish", null);
		validTaskDTO = new TaskDTO(3, "Take out the trash", "Remove rubbish", 0);

		validTasks = new ArrayList<Task>();
		validTaskDtos = new ArrayList<TaskDTO>();
		validTasks.add(validTask);
		validTaskDtos.add(validTaskDTO);
	}
    @AfterAll
    public static void teardown() {
    	report.flush();
    }
	@Test
	public void readAllTasksTest() {

		test = report.startTest("Get all tasks test");
		when(taskRepository.findAll()).thenReturn(validTasks);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);

		assertThat(validTaskDtos).isEqualTo(taskService.listAllTasks()); // true or false

		verify(taskRepository, times(1)).findAll();
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}

	@Test
	public void updateTaskTest() {
		test = report.startTest("Update task test");
		Task newTask = new Task(3, "Walk dog", "Attach Leash", null);
		TaskDTO newTaskDTO = new TaskDTO(3, "Walk dog", "Attach Leash", 0);
		when(taskRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validTask));
		when(taskRepository.save(Mockito.any(Task.class))).thenReturn(newTask);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(newTaskDTO);

		TaskDTO toTaskDTO = taskService.updateTask(validTask.getId(), newTask);

		assertThat(newTaskDTO).isEqualTo(toTaskDTO);

		verify(taskRepository, times(1)).findById(Mockito.any(Integer.class));
		verify(taskRepository, times(1)).save(Mockito.any(Task.class));
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}

	@Test
	public void createTaskTest() {

		test = report.startTest("Create task test");
		when(taskRepository.save(Mockito.any(Task.class))).thenReturn(validTask);
		when(taskMapper.mapToDTO(Mockito.any(Task.class))).thenReturn(validTaskDTO);

		assertThat(validTaskDTO).isEqualTo(taskService.createTask(validTask)); // true or false

		verify(taskRepository, times(1)).save(Mockito.any(Task.class));
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Task.class));
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}

	@Test
	public void deleteTaskTest() {

		test = report.startTest("Delete task test");
		when(taskRepository.existsById(Mockito.any(Integer.class))).thenReturn(true, false);

		assertThat(true).isEqualTo(taskService.deleteTask(validTask.getId())); // true or false

		verify(taskRepository, times(2)).existsById(Mockito.any(Integer.class));
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
}
