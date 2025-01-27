package com.task.Tasks.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.task.Tasks.Models.TaskDoer;
import com.task.Tasks.Services.TaskDoerService;

@Controller
@RequestMapping("/taskdoers")
public class TaskDoerController {

    private final TaskDoerService service;

    public TaskDoerController(TaskDoerService service) {
        this.service = service;
    }

    @GetMapping
    public String listTaskDoers(Model model) {
        model.addAttribute("taskDoers", service.getAllTaskDoers());
        return "taskdoers";
    }

    @PostMapping("/add")
    public String addTaskDoer(@ModelAttribute TaskDoer taskDoer) {
        service.addTaskDoer(taskDoer.getName(),taskDoer.getEmail(),taskDoer.getPhoneNumber());
        return "redirect:/taskdoers";
    }
}

