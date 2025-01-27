package com.task.Tasks.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.task.Tasks.Models.Task;
import com.task.Tasks.Services.TaskDoerService;
import com.task.Tasks.Services.TaskService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskDoerService taskDoerService;

    public TaskController(TaskService taskService,TaskDoerService taskDoerService) {
        this.taskService = taskService;
        this.taskDoerService=taskDoerService;
    }

    @GetMapping
    public String showTasks(Model model) {
        // Get all tasks
        var tasks = taskService.getAllTasks();
        
        // Create a map to hold the doer names by taskId
        Map<Long, String> taskDoerNames = new HashMap<>();
        
        // Fetch the task doer names and map them by task ID
        for (Task task : tasks) {
            String taskDoerName = taskDoerService.getTaskDoerNameById(task.getTaskDoerId());
            taskDoerNames.put(task.getId(), taskDoerName);  // Store the doer name in the map with task ID
        }

        // Add tasks and task doers to the model
        model.addAttribute("tasks", tasks);  // Add tasks to the model
        model.addAttribute("taskDoerNames", taskDoerNames);  // Add task-doer mapping to the model
        model.addAttribute("taskDoers", taskDoerService.getAllTaskDoers()); // Add list of task doers for the dropdown
        return "tasks";  // Return the view
    }



    // Add a new task
    @PostMapping("/add")
    public String addTask(@RequestParam String description,
                          @RequestParam Long taskDoerId,
                          @RequestParam String taskAddDate,
                          @RequestParam String taskDeadlineDate) {
        taskService.addTask(description, taskDoerId, taskAddDate, taskDeadlineDate);
        return "redirect:/tasks";
    }

    // Toggle task completion
    @PostMapping("/status/{id}")
    public String toggleTaskStatus(@PathVariable Long id) {
        taskService.toggleTaskStatus(id);
        return "redirect:/tasks";
    }

    // Delete a task
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    // Update task (GET request to display update page)
    @GetMapping("/update/{id}")
    public String showUpdateTaskPage(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "updateTask"; // Ensure you create updateTask.html for the update form
    }
}
