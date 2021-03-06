package com.qa.todolistapi.controller;

import java.util.List;

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

import com.qa.todolistapi.model.data.Step;
import com.qa.todolistapi.model.data.Task;
import com.qa.todolistapi.model.dto.StepDTO;
import com.qa.todolistapi.service.StepService;

@RestController
@RequestMapping(path="/Step")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StepController {

	private StepService stepService;
	
	@Autowired
	public StepController(StepService stepService) {
		super();
		this.stepService = stepService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<StepDTO>> getStepsFromId(@PathVariable("id") int id){
		List<StepDTO> dtos = stepService.readStepsInId(id);
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping("/{id}")
	public ResponseEntity<StepDTO> createStep(@PathVariable("id") int id, @RequestBody Step step){
		Task t = new Task(id);
		step.setTask(t);
		StepDTO newStepDTO = stepService.createStep(step);
		HttpHeaders headers= new HttpHeaders();
		headers.add("Location", String.valueOf(newStepDTO.getId()));
		return new ResponseEntity<>(newStepDTO,headers,HttpStatus.CREATED);
	}
	@CrossOrigin
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteStep(@PathVariable("id") int id) {
		return new ResponseEntity<>(stepService.deleteStep(id), HttpStatus.OK);
	}
	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<StepDTO> updateStep(@PathVariable("id") int id, @RequestBody Step step){
		StepDTO newStepDTO = stepService.updateStep(id,step);
	return new ResponseEntity<>(newStepDTO,HttpStatus.OK);
}
	@CrossOrigin
	@PutMapping("/f/{id}")
	public ResponseEntity<StepDTO> flipStep(@PathVariable("id") int id){
		StepDTO newStepDTO = stepService.updateStep(id);
	return new ResponseEntity<>(newStepDTO,HttpStatus.OK);
	}
	
}
