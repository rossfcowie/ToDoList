package com.qa.ToDoListAPI.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.ToDoListAPI.model.data.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
