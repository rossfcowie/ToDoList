package com.qa.ToDoListAPI.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.ToDoListAPI.model.data.Step;
@Repository
public interface StepRepository extends JpaRepository<Step, Integer> {
	

	@Query(value = "SELECT * FROM STEP s WHERE s.fk_task_id = ?1", nativeQuery = true)
	public List<Step> findForTask(int id);


}
