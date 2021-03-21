package com.qa.todolistapi.model.dto;

import java.util.List;

import com.qa.todolistapi.model.data.Step;

public class TaskDTO {
	private int id;
	private String nameString;
	private String description;
	private int numSteps;
	
	
	public TaskDTO(int id, String nameString, String description, List<Step> steps) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.description = description;
		this.numSteps = steps.size();
	}
	
	public TaskDTO(int id, String nameString, String description, int numSteps) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.description = description;
		this.numSteps = numSteps;
	}
	public TaskDTO(String nameString, String description, int numSteps) {
		this.nameString = nameString;
		this.description = description;
		this.numSteps = numSteps;
	}

	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", nameString=" + nameString + ", description=" + description + ", numSteps="
				+ numSteps + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((nameString == null) ? 0 : nameString.hashCode());
		result = prime * result + numSteps;
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
		TaskDTO other = (TaskDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (nameString == null) {
			if (other.nameString != null)
				return false;
		} else if (!nameString.equals(other.nameString))
			return false;

			return numSteps == other.numSteps;

	}
	public int getId() {
		return id;
	}
	public String getNameString() {
		return nameString;
	}
	public String getDescription() {
		return description;
	}
	public int getNumSteps() {
		return numSteps;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setNumSteps(List<Step> steps) {
		this.numSteps = steps.size();
	}
}
