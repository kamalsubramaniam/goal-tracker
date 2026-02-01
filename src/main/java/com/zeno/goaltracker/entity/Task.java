package com.zeno.goaltracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tasks")
public record Task(
        @Id String id,
        String userId,
        String title,
        boolean completed
) {}