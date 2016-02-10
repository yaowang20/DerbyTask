package com.example;

/**
 * Created by Yao on 12/16/2015.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class myTaskController {


    private TaskRepository taskRepository;

    @Autowired
    public myTaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(@Valid Task task, BindingResult bindingResult, Model model) {
        System.out.println("Submit xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ");

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        taskRepository.save(new Task(task.getTaskDate(), task.getTaskName(), task.getTaskDone()));


        return "redirect:/";
    }
}