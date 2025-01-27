package com.task.Tasks.Services;

import org.springframework.stereotype.Service;

import com.task.Tasks.Models.TaskDoer;
import com.task.Tasks.Repository.TaskDoerRepository;

import java.util.List;

@Service
public class TaskDoerService {

    private final TaskDoerRepository taskDoerRepository;

    public TaskDoerService(TaskDoerRepository taskDoerRepository) {
        this.taskDoerRepository = taskDoerRepository;
    }

    public String getTaskDoerNameById(Long id) {
        TaskDoer taskDoer = taskDoerRepository.findById(id).orElse(null);
        return taskDoer != null ? taskDoer.getName() : "Unknown"; // Return the name or default to "Unknown"
    }
    // Get all task doers
    public List<TaskDoer> getAllTaskDoers() {
        return taskDoerRepository.findAll();
    }

    // Get a task doer by ID
    public TaskDoer getTaskDoerById(Long id) {
        return taskDoerRepository.findById(id).orElse(null);
    }

    // Add a new task doer
    public void addTaskDoer(String name, String email, String phoneNumber) {
        TaskDoer taskDoer = new TaskDoer();
        taskDoer.setName(name);
        taskDoer.setEmail(email);
        taskDoer.setPhoneNumber(phoneNumber);
        taskDoerRepository.save(taskDoer);
    }

    // Update a task doer
    public void updateTaskDoer(Long id, String name, String email, String phoneNumber) {
        TaskDoer taskDoer = taskDoerRepository.findById(id).orElse(null);
        if (taskDoer != null) {
            taskDoer.setName(name);
            taskDoer.setEmail(email);
            taskDoer.setPhoneNumber(phoneNumber);
            taskDoerRepository.save(taskDoer);
        }
    }

    // Delete a task doer
    public void deleteTaskDoer(Long id) {
        taskDoerRepository.deleteById(id);
    }
}
