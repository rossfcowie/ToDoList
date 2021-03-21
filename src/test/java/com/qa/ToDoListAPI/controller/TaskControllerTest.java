package com.qa.todolistapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.todolistapi.controller.TaskController;
import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.TaskDTO;
import com.qa.todolistapi.service.TaskService;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SpringBootTest
 class TaskControllerTest {

	@Autowired
	private TaskController taskController;
	
	@MockBean
	private TaskService taskService;
	
	private List<Task> validTasks;
	private List<TaskDTO> validTaskDtos;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	static ExtentReports  report = new ExtentReports("src/test/resources/reports/Task_Controller_Unit_Report.html", true);
    static ExtentTest test;
	@BeforeEach
	 void init() {
		validTask = new Task("Take out the trash", "Remove rubbish");
		validTaskDTO = new TaskDTO("Take out the trash", "Remove rubbish",0);
		
		validTasks = new ArrayList<Task>();
		validTaskDtos = new ArrayList<TaskDTO>();
		validTasks.add(validTask);
		validTaskDtos.add(validTaskDTO);
	}
	@Test
	 void getAllTaskstest() {
		test = report.startTest("Get all tasks test");
		when(taskService.listAllTasks()).thenReturn(validTaskDtos);
		ResponseEntity<List<TaskDTO>> response = new ResponseEntity<List<TaskDTO>>(validTaskDtos,HttpStatus.OK);
		assertThat(response).isEqualTo(taskController.getAllTasks());
		verify(taskService, times(1)).listAllTasks();
	}
	@Test
	 void createTaskTest() {
		test = report.startTest("Create task test");
		
		when(taskService.createTask(Mockito.any(Task.class))).thenReturn(validTaskDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validTaskDTO.getId()));
		ResponseEntity<TaskDTO> response = new ResponseEntity<TaskDTO>(validTaskDTO,headers,HttpStatus.CREATED);
		assertThat(response).isEqualTo(taskController.createTask(validTask));
		verify(taskService, times(1)).createTask(Mockito.any(Task.class));
	}
	@Test
	 void deleteTaskTest() {
		test = report.startTest("Delete task test");
		when(taskService.deleteTask(Mockito.any(Integer.class))).thenReturn(true);
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.OK);
		assertThat(response).isEqualTo(taskController.deleteTask(validTask.getId()));
		verify(taskService, times(1)).deleteTask(Mockito.any(Integer.class));
	}
	@Test
	 void updateTaskTest() {
		test = report.startTest("Update task test");
		when(taskService.updateTask(Mockito.any(Integer.class),Mockito.any(Task.class))).thenReturn(validTaskDTO);
		ResponseEntity<TaskDTO> response = new ResponseEntity<TaskDTO>(validTaskDTO,HttpStatus.OK);
		assertThat(response).isEqualTo(taskController.updateTask(validTask.getId(),validTask));
		verify(taskService, times(1)).updateTask(Mockito.any(Integer.class),Mockito.any(Task.class));
	}
	@AfterEach
	 void end() {
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
    @AfterAll
     static void teardown() {
    	report.flush();
    }
}
