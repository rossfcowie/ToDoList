package com.qa.todolistapi.model.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Step> steps;

	public Task(int i) {
		id = i;
		this.steps = new ArrayList<>();
	}
	public Task() {
		this.steps = new ArrayList<>();
	}
	public Task(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.steps = new ArrayList<>();
	}

	
	public Task(int id, String name, String description, List<Step> steps) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.steps = steps;
	}

	public Task(String name, String description, List<Step> steps) {
		this.name = name;
		this.description = description;
		this.steps = steps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((steps == null) ? 0 : steps.hashCode());
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
		Task other = (Task) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (steps == null) {
			if (other.steps != null)
				return false;
		} else if (!steps.equals(other.steps))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", nameString=" + name + ", descrition=" + description + ", steps=" + steps
				+ "]";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
}