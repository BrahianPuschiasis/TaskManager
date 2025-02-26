package com.puschiasis.Tasks.service;

import com.puschiasis.Tasks.entity.Task;
import com.puschiasis.Tasks.notification.TaskNotificationService;
import com.puschiasis.Tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskNotificationService taskNotificationService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskNotificationService taskNotificationService) {
        this.taskRepository = taskRepository;
        this.taskNotificationService = taskNotificationService;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        task.setId(id);
        Task updatedTask = taskRepository.save(task);

        if ("Completed".equals(updatedTask.getStatus())) {
            String taskDetails = "Task with ID " + updatedTask.getId() + " has been completed.";
            taskNotificationService.sendTaskCompletionNotification(taskDetails);
        }

        return updatedTask;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByAssignedUser(String assignedUser) {
        return taskRepository.findByAssignedUser(assignedUser);
    }
}
