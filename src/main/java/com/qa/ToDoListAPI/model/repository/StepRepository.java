package com.qa.ToDoListAPI.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.ToDoListAPI.model.data.Step;

public interface StepRepository extends JpaRepository<Step, Integer> {

	
	@Query("SELECT s FROM Steps s WHERE s.Task_ID = ?1")
	public List<Step> findForTask(Integer id);


}
