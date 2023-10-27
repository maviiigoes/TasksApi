package com.hellotasks.tasks_api.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean done;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Task(String name) {
        this.name = name;
        this.done = false;
        this.createdAt = LocalDateTime.now();
    }
}
