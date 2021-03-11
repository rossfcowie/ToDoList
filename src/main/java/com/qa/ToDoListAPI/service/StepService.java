package com.qa.ToDoListAPI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ToDoListAPI.mapper.StepMapper;
import com.qa.ToDoListAPI.model.DTO.StepDTO;
import com.qa.ToDoListAPI.model.data.Step;
import com.qa.ToDoListAPI.model.repository.StepRepository;

@Service
public class StepService {

	private StepRepository stepRepository;

	private StepMapper stepMapper;

	@Autowired
	public StepService(StepRepository stepRepository, StepMapper stepMapper) {
		this.stepMapper = stepMapper;
		this.stepRepository = stepRepository;
	}
	
	public List<StepDTO> readStepsInId(Integer id){
		List<Step> steps = stepRepository.findForTask(id);
		List<StepDTO> stepDTOs = new ArrayList<StepDTO>();
		
		steps.forEach(step -> stepDTOs.add(stepMapper.mapToDTO(step)));
				
		return stepDTOs;
	}

}
