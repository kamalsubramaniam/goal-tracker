package com.zeno.goaltracker.service;

import com.zeno.goaltracker.entity.Task;
import com.zeno.goaltracker.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repo;
    private final UserService userService;

    public TaskService(TaskRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    public Task create(Task task) {
        // ensure user exists
        userService.getById(task.userId());
        return repo.save(task);
    }

    public Task getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
    }

    public List<Task> getAll() {
        return repo.findAll();
    }

    public List<Task> getByUserId(String userId) {
        userService.getById(userId);
        return repo.findByUserId(userId);
    }

    public Task update(String id, Task updated) {
        Task existing = getById(id);
        Task merged = new Task(existing.id(),
                updated.userId() != null ? updated.userId() : existing.userId(),
                updated.title() != null ? updated.title() : existing.title(),
                updated.completed());
        return repo.save(merged);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}