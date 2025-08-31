package com.charith.todobackend.repo;

import com.charith.todobackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM task WHERE completed = false ORDER BY created_at DESC LIMIT 5", nativeQuery = true)
    List<Task> findTop5IncompleteTasks();
}
