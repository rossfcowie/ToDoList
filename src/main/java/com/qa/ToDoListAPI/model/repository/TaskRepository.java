package com.qa.todolistapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todolistapi.model.data.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
