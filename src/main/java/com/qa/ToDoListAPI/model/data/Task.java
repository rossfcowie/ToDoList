package com.qa.ToDoListAPI.model.data;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.sun.istack.NotNull;

@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	private String descrition;
	
	@OneToMany(mappedBy = "task", fetch = FetchType.LAZY, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Step> steps;

	public Task() {
		this.steps = new ArrayList<Step>();
	}
	
	public Task(String name, String descrition) {
		super();
		this.name = name;
		this.descrition = descrition;
		this.steps = new ArrayList<Step>();
	}

	
	public Task(int id, String name, String descrition, List<Step> steps) {
		super();
		this.id = id;
		this.name = name;
		this.descrition = descrition;
		this.steps = steps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrition == null) ? 0 : descrition.hashCode());
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
		if (descrition == null) {
			if (other.descrition != null)
				return false;
		} else if (!descrition.equals(other.descrition))
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
		return "Task [id=" + id + ", nameString=" + name + ", descrition=" + descrition + ", steps=" + steps
				+ "]";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescrition() {
		return descrition;
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

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
}