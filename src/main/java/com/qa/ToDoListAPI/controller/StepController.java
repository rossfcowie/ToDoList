package com.qa.ToDoListAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.ToDoListAPI.model.DTO.StepDTO;
import com.qa.ToDoListAPI.model.data.Step;
import com.qa.ToDoListAPI.service.StepService;

@RestController
@RequestMapping(path="/Step")
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
		return new ResponseEntity<List<StepDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<StepDTO> createStep(@RequestParam("id") int id, @RequestBody Step step){
		StepDTO newStepDTO = stepService.createStep(step);
		HttpHeaders headers= new HttpHeaders();
		headers.add("Location", String.valueOf(newStepDTO.getId()));
		return new ResponseEntity<StepDTO>(newStepDTO,headers,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteStep(@PathVariable("id") int id) {
		return new ResponseEntity<Boolean>(stepService.deleteStep(id), HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<StepDTO> updateStep(@RequestParam("id") int id, @RequestBody Step step){
		StepDTO newStepDTO = stepService.updateStep(step);
	return new ResponseEntity<StepDTO>(newStepDTO,HttpStatus.OK);
}
}
