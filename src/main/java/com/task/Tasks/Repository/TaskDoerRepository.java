package com.task.Tasks.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.Tasks.Models.TaskDoer;

public interface TaskDoerRepository extends JpaRepository<TaskDoer, Long> {
}
