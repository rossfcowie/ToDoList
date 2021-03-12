package com.qa.ToDoListAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ToDoListAPI.exceptions.StepNotFoundException;
import com.qa.ToDoListAPI.mapper.StepMapper;
import com.qa.ToDoListAPI.model.DTO.StepDTO;
import com.qa.ToDoListAPI.model.data.Step;
import com.qa.ToDoListAPI.model.repository.StepRepository;

@Service
public class StepService {
//
//	private StepRepository stepRepository;
//
//	private StepMapper stepMapper;
//
//	@Autowired
//	public StepService(StepRepository stepRepository, StepMapper stepMapper) {
//		this.stepMapper = stepMapper;
//		this.stepRepository = stepRepository;
//	}
//	
//	public List<StepDTO> readStepsInId(Integer id){
//		List<Step> steps = stepRepository.findForTask(id);
//		List<StepDTO> stepDTOs = new ArrayList<StepDTO>();
//		
//		steps.forEach(step -> stepDTOs.add(stepMapper.mapToDTO(step)));
//				
//		return stepDTOs;
//	}

	
	public StepDTO createStep(Step step) {
		Step newStep = stepRepository.save(step);
		
		return stepMapper.mapToDTO(newStep);
	}

	public Boolean deleteStep(int id) {
		if (!stepRepository.existsById(id)) {
			throw new StepNotFoundException();
		}
		stepRepository.deleteById(id);
		
		boolean exists = stepRepository.existsById(id);
		
		return !exists;
	}

	public StepDTO updateStep(Integer id,Step step) {
		Optional<Step> stepInDbOpt = stepRepository.findById(id);
		Step stepInDb;
		
		if (stepInDbOpt.isPresent()) {
			stepInDb = stepInDbOpt.get();
		} else {
			throw new StepNotFoundException();
		}
		
		stepInDb.setName(step.getName());
		stepInDb.setComplete(step.isComplete());

		
		Step updatedStep = stepRepository.save(stepInDb);
		
		return stepMapper.mapToDTO(updatedStep);
	}
	public StepDTO updateStep(Integer id) {
		Optional<Step> stepInDbOpt = stepRepository.findById(id);
		Step stepInDb;
		
		if (stepInDbOpt.isPresent()) {
			stepInDb = stepInDbOpt.get();
		} else {
			throw new StepNotFoundException();
		}
		
		stepInDb.setComplete(!stepInDb.isComplete());

		
		Step updatedStep = stepRepository.save(stepInDb);
		
		return stepMapper.mapToDTO(updatedStep);
	}

}
