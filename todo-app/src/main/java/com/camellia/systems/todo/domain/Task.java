package com.camellia.systems.todo.domain;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Task extends BasicEntity implements Serializable {

	private String title;
	
	private String description;

	private String taskStatus;

    private String priority;

    private String assignedUser;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public boolean isNew() {
		return (this.id == null);
	}

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Task task = (Task) o;

		if (!id.equals(task.id)) return false;
		if (title != null ? !title.equals(task.title) : task.title != null) return false;
		if (description != null ? !description.equals(task.description) : task.description != null) return false;
		return taskStatus != null ? taskStatus.equals(task.taskStatus) : task.taskStatus == null;

	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (taskStatus != null ? taskStatus.hashCode() : 0);
		return result;
	}


}
