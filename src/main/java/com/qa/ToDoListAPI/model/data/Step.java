package com.qa.todolistapi.model.data;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;


@Entity
@Table(name="step")
public class Step {
	
	@Id 
	@Column(name = "StepId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	@NonNull
	private String name;
	
	@ManyToOne(targetEntity = Task.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_task_ID")
	private Task task;
	
	@NotNull
	private boolean complete;
	
	public Step() {
		super();
		this.name = "";
	}
	
	
	public Step(int id, String name, Task task, boolean complete) {
		super();
		this.id = id;
		this.name = name;
		this.task = task;
		this.complete = complete;
	}
	public Step(String name,  boolean complete) {
		super();
		this.name = name;
		this.complete = complete;
	}
	public Step(int id, String name, boolean complete) {
		super();
		this.id = id;
		this.name = name;
		this.complete = complete;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Task getTask() {
		return task;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		return "Step [id=" + id + ", name=" + name + ", task=" + task + ", complete=" + complete + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (complete ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Step other = (Step) obj;
		if (complete != other.complete)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		return true;
	}


	
}
