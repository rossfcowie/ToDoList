package com.qa.todolistapi.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.todolistapi.mapper.TaskMapper;
import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.TaskDTO;
import com.qa.todolistapi.model.repository.TaskRepository;
import com.qa.todolistapi.service.TaskService;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SpringBootTest
 class TaskServiceIntegrationTest {
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
    static ExtentReports  report = new ExtentReports("src/test/resources/reports/Task_Service_Integration_Report.html", true);
    static ExtentTest test;
	@BeforeEach
	 void init() {
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
    @AfterAll
     static void teardown() {
    	report.flush();
    }
    
	@Test
	 void readAllTasksTest() {
		test = report.startTest("Get all tasks test");
		List<TaskDTO> tasksInDb = taskService.listAllTasks();
		assertThat(validTaskDtos).isEqualTo(tasksInDb); // true or false
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	
	@Test
	 void updateTaskTest() {
		test = report.startTest("Update task test");
		Task newTask = new Task(validTask.getId(),"Walk dog", "Attach Leash", validTask.getSteps());
		TaskDTO newTaskDTO = taskMapper.mapToDTO(newTask);
		TaskDTO toTaskDTO = taskService.updateTask(validTask.getId(),newTask);
		assertThat(newTaskDTO).isEqualTo(toTaskDTO);
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	@Test
	 void deleteTaskTest() {
		test = report.startTest("Delete task test");
		assertThat(taskService.deleteTask(validTask.getId())).isTrue(); // true or false	
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);
	}
	@Test
	 void createStepTest() {
		test = report.startTest("Create task test");
		Task newTask = new Task(validTask.getId(),"Walk dog", "Attach Leash", validTask.getSteps());
		TaskDTO newTaskDTO = taskMapper.mapToDTO(newTask);
		assertThat(newTaskDTO).isEqualTo(taskService.createTask(newTask)); // true or false
		test.log(LogStatus.PASS, "Ok");
		report.endTest(test);

	}
	
}
