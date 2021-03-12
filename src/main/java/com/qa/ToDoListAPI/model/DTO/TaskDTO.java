package com.qa.ToDoListAPI.model.DTO;

import java.util.List;

import com.qa.ToDoListAPI.model.data.Step;

public class TaskDTO {
	private int id;
	private String nameString;
	private String descrition;
	private int numSteps;
	
	
	public TaskDTO(int id, String nameString, String descrition, List<Step> steps) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.descrition = descrition;
		this.numSteps = steps.size();
	}
	
	public TaskDTO(int id, String nameString, String descrition, int numSteps) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.descrition = descrition;
		this.numSteps = numSteps;
	}
	public TaskDTO(String string, String string2, int i) {
		this.nameString = nameString;
		this.descrition = descrition;
		this.numSteps = numSteps;
	}

	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", nameString=" + nameString + ", descrition=" + descrition + ", numSteps="
				+ numSteps + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrition == null) ? 0 : descrition.hashCode());
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
		if (descrition == null) {
			if (other.descrition != null)
				return false;
		} else if (!descrition.equals(other.descrition))
			return false;
		if (id != other.id)
			return false;
		if (nameString == null) {
			if (other.nameString != null)
				return false;
		} else if (!nameString.equals(other.nameString))
			return false;
		if (numSteps != other.numSteps)
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public String getNameString() {
		return nameString;
	}
	public String getDescrition() {
		return descrition;
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
	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
	public void setNumSteps(List<Step> steps) {
		this.numSteps = steps.size();
	}
}
