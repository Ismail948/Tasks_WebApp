package com.task.Tasks.Models;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private Long taskDoerId;

    private String taskDescription;
    private LocalDate taskAddDate = LocalDate.now();
    private LocalDate taskDeadlineDate;
    private Boolean isCompleted = false;
    private String priority; // LOW, MEDIUM, HIGH
    private LocalDateTime lastUpdatedAt = LocalDateTime.now();
    
	public Long getTaskDoerId() {
		return taskDoerId;
	}
	public void setTaskDoerId(Long taskDoerId) {
		this.taskDoerId = taskDoerId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public LocalDate getTaskAddDate() {
		return taskAddDate;
	}
	public void setTaskAddDate(LocalDate taskAddDate) {
		this.taskAddDate = taskAddDate;
	}
	public LocalDate getTaskDeadlineDate() {
		return taskDeadlineDate;
	}
	public void setTaskDeadlineDate(LocalDate taskDeadlineDate) {
		this.taskDeadlineDate = taskDeadlineDate;
	}
	public Boolean getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public LocalDateTime getLastUpdatedAt() {
		return lastUpdatedAt;
	}
	public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}
	public Task(Long id,  String taskDescription, LocalDate taskAddDate, LocalDate taskDeadlineDate,
			Boolean isCompleted, String priority, LocalDateTime lastUpdatedAt,Long taskDoerId) {
		super();
		this.id = id;
		this.taskDoerId = taskDoerId;
		this.taskDescription = taskDescription;
		this.taskAddDate = taskAddDate;
		this.taskDeadlineDate = taskDeadlineDate;
		this.isCompleted = isCompleted;
		this.priority = priority;
		this.lastUpdatedAt = lastUpdatedAt;
	}
	public Task() {
	
		// TODO Auto-generated constructor stub
	}

    // Getters and Setters
}
