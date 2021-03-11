package com.qa.ToDoListAPI.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.ToDoListAPI.model.data.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

}
