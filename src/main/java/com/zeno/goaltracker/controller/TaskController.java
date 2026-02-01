package com.zeno.goaltracker.controller;

import com.zeno.goaltracker.entity.Task;
import com.zeno.goaltracker.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return service.create(task);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Task> list(@RequestParam(required = false) String userId) {
        return (userId == null) ? service.getAll() : service.getByUserId(userId);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable String id, @RequestBody Task task) {
        return service.update(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}