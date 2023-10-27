package com.hellotasks.tasks_api.controller;

import com.hellotasks.tasks_api.models.Task;
import com.hellotasks.tasks_api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getTasks(@RequestParam(value = "order_by", required = false) String orderBy) {
        List<Task> tasks = taskRepository.findAll();

        tasks.sort((t1, t2) -> {
            if (t1.isDone() == t2.isDone()) {
                if (t1.getName().equals(t2.getName())) {
                    return t2.getCreatedAt().compareTo(t1.getCreatedAt());
                }
                return t1.getName().compareTo(t2.getName());
            }
            return Boolean.compare(t2.isDone(), t1.isDone());
        });

        return tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        System.out.println("Received request: " + task.toString());
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok().body(savedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
