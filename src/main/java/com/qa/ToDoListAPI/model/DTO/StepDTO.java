package com.qa.todolistapi.model.DTO;


public class StepDTO {
	
	private int id;
	private String name;
	private int task;
	private boolean complete;
	
	
	
	public StepDTO(int id, String name, int task, boolean complete) {
		super();
		this.id = id;
		this.name = name;
		this.task = task;
		this.complete = complete;
	}
	public StepDTO(String name, int task, boolean complete) {
		super();
		this.name = name;
		this.task = task;
		this.complete = complete;
	}
	public StepDTO(int id, String name, boolean complete) {
		super();
		this.id = id;
		this.name = name;
		this.complete = complete;
	}
	public StepDTO(String name, boolean complete) {
		super();
		this.name = name;
		this.complete = complete;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getTask() {
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
	public void setTask(int task) {
		this.task = task;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (complete ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + task;
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
		StepDTO other = (StepDTO) obj;
		if (complete != other.complete)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (task != other.task)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StepDTO [id=" + id + ", name=" + name + ", task=" + task + ", complete=" + complete + "]";
	}
}
