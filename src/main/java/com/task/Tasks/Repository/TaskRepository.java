package com.task.Tasks.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.task.Tasks.Models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
