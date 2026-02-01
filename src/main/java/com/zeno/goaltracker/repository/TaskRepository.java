package com.zeno.goaltracker.repository;

import com.zeno.goaltracker.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByUserId(String userId);
}
