package com.puschiasis.Tasks.service;

import com.puschiasis.Tasks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();

    Optional<Task> getTaskById(Long id);

    Task createTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);
    List<Task> getTasksByAssignedUser(String assignedUser);
}
