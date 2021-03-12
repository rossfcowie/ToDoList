package com.qa.ToDoListAPI.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.data.repository.DuckRepository;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.exceptions.DuckNotFoundException;
import com.example.duckdemo.mappers.DuckMapper;

@Service // labelled as a bean (managed by Spring)
public class DuckService {
	
	// Data Access Object
	private DuckRepository duckRepository;
	
	private DuckMapper duckMapper;
	
	@Autowired
	public DuckService(DuckRepository duckRepository, DuckMapper duckMapper) {
		this.duckRepository = duckRepository;
		this.duckMapper = duckMapper;
	}

	public List<DuckDTO> readAllDucks() {
		List<Duck> ducks = duckRepository.findAll();
		List<DuckDTO> duckDTOs = new ArrayList<DuckDTO>();
		
		ducks.forEach(duck -> duckDTOs.add(duckMapper.mapToDTO(duck)));

		return duckDTOs;
	}
	
	public DuckDTO readById(Integer id) {
		Optional<Duck> duck = duckRepository.findById(id);
		
		if (duck.isPresent()) {
			return duckMapper.mapToDTO(duck.get());
		} else {
			throw new DuckNotFoundException("Duck is not here, QUACK!");
		}
	}
	
	public DuckDTO readByName(String name) {
		Duck duck = duckRepository.getDuckByNameJPQL(name);
		
		return duckMapper.mapToDTO(duck);
	}
	
	public DuckDTO createDuck(Duck duck) {
		Duck newDuck = duckRepository.save(duck);
		
		return duckMapper.mapToDTO(newDuck);
	}
	
	public DuckDTO updateDuck(Integer id, Duck duck) throws EntityNotFoundException {
		Optional<Duck> duckInDbOpt = duckRepository.findById(id);
		Duck duckInDb;
		
		if (duckInDbOpt.isPresent()) {
			duckInDb = duckInDbOpt.get();
		} else {
			throw new DuckNotFoundException("Duck is not here, QUACK!");
		}
		
		duckInDb.setName(duck.getName());
		duckInDb.setAge(duck.getAge());
		duckInDb.setHabitat(duck.getHabitat());
		duckInDb.setColour(duck.getColour());
		
		Duck updatedDuck = duckRepository.save(duckInDb);
		
		return duckMapper.mapToDTO(updatedDuck);
	}
	
	public boolean deleteDuck(Integer id) {
		if (!duckRepository.existsById(id)) {
			throw new DuckNotFoundException();
		}
		duckRepository.deleteById(id);
		
		boolean exists = duckRepository.existsById(id);
		
		return !exists;
	}
	
}
