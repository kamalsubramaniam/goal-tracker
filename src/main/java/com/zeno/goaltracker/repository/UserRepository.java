package com.zeno.goaltracker.repository;

import com.zeno.goaltracker.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {}

