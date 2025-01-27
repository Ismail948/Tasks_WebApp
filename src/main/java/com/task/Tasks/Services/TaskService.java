package com.task.Tasks.Services;

import com.task.Tasks.Models.Task;
import com.task.Tasks.Models.TaskDoer;
import com.task.Tasks.Repository.TaskDoerRepository;
import com.task.Tasks.Repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskDoerRepository taskDoerRepository;

    public TaskService(TaskRepository taskRepository, TaskDoerRepository taskDoerRepository) {
        this.taskRepository = taskRepository;
        this.taskDoerRepository = taskDoerRepository;
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Add a new task
    public void addTask(String description, Long taskDoerId, String taskAddDate, String taskDeadlineDate) {
        Optional<TaskDoer> taskDoer = taskDoerRepository.findById(taskDoerId);
        if (taskDoer.isPresent()) {
            Task newTask = new Task();
            newTask.setTaskDescription(description);
            newTask.setTaskDoerId(taskDoerId);  // Set taskDoerId directly
            newTask.setTaskAddDate(LocalDate.parse(taskAddDate));
            newTask.setTaskDeadlineDate(LocalDate.parse(taskDeadlineDate));
            newTask.setPriority("LOW"); // Default priority, adjust as needed
            newTask.setLastUpdatedAt(LocalDateTime.now());
            taskRepository.save(newTask);
        }
    }

    // Toggle task completion
    public void toggleTaskStatus(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            Task currentTask = task.get();
            currentTask.setIsCompleted(!currentTask.getIsCompleted());
            currentTask.setLastUpdatedAt(LocalDateTime.now()); // Update timestamp on change
            taskRepository.save(currentTask);
        }
    }

    // Delete a task
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    // Get a task by ID
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    // Update task
    public void updateTask(Long taskId, String description, String taskDeadlineDate, String priority) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            Task currentTask = task.get();
            currentTask.setTaskDescription(description);
            currentTask.setTaskDeadlineDate(LocalDate.parse(taskDeadlineDate));
            currentTask.setPriority(priority);
            currentTask.setLastUpdatedAt(LocalDateTime.now());
            taskRepository.save(currentTask);
        }
    }
}
