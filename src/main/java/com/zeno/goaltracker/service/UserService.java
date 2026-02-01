package com.zeno.goaltracker.service;

import com.zeno.goaltracker.entity.User;
import com.zeno.goaltracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User create(User user) {
        return repo.save(user);
    }

    public User getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User update(String id, User updated) {
        User existing = getById(id);
        User merged = new User(existing.id(),
                updated.name() != null ? updated.name() : existing.name(),
                updated.email() != null ? updated.email() : existing.email());
        return repo.save(merged);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}