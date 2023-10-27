package com.hellotasks.tasks_api.repositories;


import com.hellotasks.tasks_api.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDone(boolean done);
}
