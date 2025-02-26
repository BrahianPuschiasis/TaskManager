package com.puschiasis.Tasks;

import com.puschiasis.Tasks.entity.Task;
import com.puschiasis.Tasks.repository.TaskRepository;
import com.puschiasis.Tasks.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskServiceUnitTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1000L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus("Pending");
        task.setAssignedUser("user123");
    }

    @Test
    void getAllTasks_ShouldReturnEmptyList() {
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());

        List<Task> tasks = taskService.getAllTasks();

        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    void getTaskById_ShouldReturnTask() {
        when(taskRepository.findById(1000L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1000L);

        assertTrue(result.isPresent());
        assertEquals("Test Task", result.get().getTitle());
    }

    @Test
    void getTaskById_ShouldReturnEmptyOptional() {
        when(taskRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Task> result = taskService.getTaskById(2L);

        assertFalse(result.isPresent());
    }

    @Test
    void createTask_ShouldReturnCreatedTask() {
        when(taskRepository.save(task)).thenReturn(task);

        Task createdTask = taskService.createTask(task);

        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getTitle());
    }

    @Test
    void updateTask_ShouldReturnUpdatedTask() {
        Task updatedTask = new Task();
        updatedTask.setTitle("Updated Task");

        when(taskRepository.findById(1000L)).thenReturn(Optional.of(task));
        when(taskRepository.save(updatedTask)).thenReturn(updatedTask);

        Task result = taskService.updateTask(1000L, updatedTask);

        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
    }

    @Test
    void deleteTask_ShouldDeleteTask() {
        doNothing().when(taskRepository).deleteById(1000L);

        taskService.deleteTask(1000L);

        verify(taskRepository, times(1)).deleteById(1000L);
    }

    @Test
    void getTasksByAssignedUser_ShouldReturnTasks() {
        when(taskRepository.findByAssignedUser("user123")).thenReturn(Collections.singletonList(task));

        List<Task> tasks = taskService.getTasksByAssignedUser("user123");

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
    }
}
